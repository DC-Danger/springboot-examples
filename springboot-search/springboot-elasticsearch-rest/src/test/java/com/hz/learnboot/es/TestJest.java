package com.hz.learnboot.es;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.hz.learnboot.es.domain.CsdnBlog;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.cluster.Health;
import io.searchbox.cluster.NodesInfo;
import io.searchbox.cluster.NodesStats;
import io.searchbox.core.*;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.indices.*;
import io.searchbox.indices.mapping.PutMapping;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 测试Jest客户端操作
 *
 * @Author hezhao
 * @Time 2018-07-22 12:11
 */
public class TestJest {

    private static JestClient jestClient;
    private static String indexName = "test_jest";
    private static String typeName = "article";

    // 这里用的是HTTP REST API 端口
    private String uri = "http://127.0.0.1:9200";

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * 获取链接
     * @throws Exception
     */
    @Before
    public void getClient() throws Exception{
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(uri)
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create())
                .connTimeout(1500)
                .readTimeout(3000)
                .multiThreaded(true)
                .build());
        jestClient = factory.getObject();
    }

    @After
    public void tearDown() throws Exception {
        closeJestClient(jestClient);
    }

    /**
     * 关闭JestClient客户端
     * @param jestClient
     * @throws Exception
     */
    public void closeJestClient(JestClient jestClient) throws Exception {
        if (jestClient != null) {
            jestClient.close();
        }
    }

    /**
     * ---------------------------测试--------------------------
     */

    /**
     * 创建索引
     * @throws Exception
     */
    @Test
    public void createIndex() throws Exception {
        JestResult jr = jestClient.execute(new CreateIndex.Builder(indexName).build());
        System.out.println(jr.isSucceeded());
    }

    /**
     * Put映射
     * @throws Exception
     */
    @Test
    public void createIndexMapping() throws Exception {
        String source = "{\"" + typeName + "\":{\"properties\":{"
                + "\"author\":{\"type\":\"string\",\"index\":\"not_analyzed\"}"
                + ",\"title\":{\"type\":\"string\"}"
                + ",\"content\":{\"type\":\"string\"}"
                + ",\"price\":{\"type\":\"string\"}"
                + ",\"view\":{\"type\":\"string\"}"
                + ",\"tag\":{\"type\":\"string\"}"
                + ",\"date\":{\"type\":\"date\",\"format\":\"yyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\"}"
                + "}}}";
        System.out.println(source);

        PutMapping putMapping = new PutMapping.Builder(indexName, typeName, source).build();
        JestResult jr = jestClient.execute(putMapping);
        System.out.println(jr.isSucceeded());
    }

    /**
     * 插入多个
     * @throws IOException
     */
    @Test
    public void insertMultiple() throws IOException{
        String[] authors = {"AAAA", "BBBB", "CCCC", "DDDD"};
        int count = 1;
        CsdnBlog csdnBlog;
        Index index;
        JestResult jestResult;

        for (String author : authors) {
            csdnBlog = CsdnBlog.builder()
                    .author(author)
                    .title("中国获租巴基斯坦瓜达尔港2000亩土地 为期43年")
                    .content("据了解，瓜达尔港务局于今年6月完成了1500亩土地的征收工作，另外500亩的征收工作也将很快完成")
                    .view(100 * count)
                    .tag("JAVA,ANDROID,C++,LINUX")
                    .date(getCurrentDateTime()).build();
            count++;

            index = new Index.Builder(csdnBlog).index(indexName).type(typeName).build();
            jestResult = jestClient.execute(index);
            System.out.println(jestResult.getJsonString());
        }
    }

    /**
     * Bulk插入数据
     * @throws IOException
     */
    @Test
    public void bulkIndex() throws IOException{
        String[] authors = {"AAAA", "BBBB", "CCCC", "DDDD"};
        List<CsdnBlog> blogs = new ArrayList<>();
        int count = 1;
        CsdnBlog csdnBlog;

        for (String author : authors) {
            csdnBlog = CsdnBlog.builder()
                    .author(author)
                    .title("中国获租巴基斯坦瓜达尔港2000亩土地 为期43年")
                    .content("据了解，瓜达尔港务局于今年6月完成了1500亩土地的征收工作，另外500亩的征收工作也将很快完成")
                    .view(100 * count)
                    .tag("JAVA,ANDROID,C++,LINUX")
                    .date(getCurrentDateTime()).build();
            count++;
            blogs.add(csdnBlog);
        }

        List<Index> indices = blogs.stream()
                .map(e -> new Index.Builder(e).build())
                .collect(Collectors.toList());

        Bulk bulk = new Bulk.Builder()
                .defaultIndex(indexName)
                .defaultType(typeName)
                .addAction(indices).build();
        JestResult jestResult=jestClient.execute(bulk);
        System.out.println(jestResult.getJsonString());

    }

    /**
     * 单值完全匹配查询
     * @throws Exception
     */
    @Test
    public void termQuery() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .termQuery("author", "T:o\"m-");//单值完全匹配查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);


        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        SearchResult result = jestClient.execute(search);

        List<Hit<Object, Void>> hits = result.getHits(Object.class);
        System.out.println("Size:" + hits.size());
        for (Hit<Object, Void> hit : hits) {
            Object news = hit.source;
            System.out.println(news.toString());
        }
    }

    /**
     * 多值完全匹配查询
     * @throws Exception
     */
    @Test
    public void termsQuery() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .termsQuery("author", new String[]{ "T:o\"m-", "J,e{r}r;y:" });//多值完全匹配查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        SearchResult result = jestClient.execute(search);

        List<Hit<Object, Void>> hits = result.getHits(Object.class);
        System.out.println("Size:" + hits.size());
        for (Hit<Object, Void> hit : hits) {
            Object news = hit.source;
            System.out.println(news.toString());
        }
    }

    /**
     * 通配符和正则表达式查询
     * @throws Exception
     */
    @Test
    public void wildcardQuery() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("title", "*:*");//通配符和正则表达式查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        SearchResult result = jestClient.execute(search);

        List<Hit<Object, Void>> hits = result.getHits(Object.class);
        System.out.println("Size:" + hits.size());
        for (Hit<Object, Void> hit : hits) {
            Object news = hit.source;
            System.out.println(news.toString());
        }
    }

    /**
     * 前缀查询
     * @throws Exception
     */
    @Test
    public void prefixQuery() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .prefixQuery("name", "T:o");//前缀查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        SearchResult result = jestClient.execute(search);

        List<Hit<Object, Void>> hits = result.getHits(Object.class);
        System.out.println("Size:" + hits.size());
        for (Hit<Object, Void> hit : hits) {
            Object news = hit.source;
            System.out.println(news.toString());
        }
    }

    /**
     * 区间查询
     * @throws Exception
     */
    @Test
    public void rangeQuery() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .rangeQuery("birth")
                .gte("2016-09-01T00:00:00")
                .lte("2016-10-01T00:00:00")
                .includeLower(true)
                .includeUpper(true);//区间查询
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        SearchResult result = jestClient.execute(search);

        List<Hit<Object, Void>> hits = result.getHits(Object.class);
        System.out.println("Size:" + hits.size());
        for (Hit<Object, Void> hit : hits) {
            Object news = hit.source;
            System.out.println(news.toString());
        }
    }

    /**
     * 文本检索，应该是将查询的词先分成词库中存在的词，然后分别去检索，存在任一存在的词即返回，查询词分词后是OR的关系。需要转义特殊字符
     * @throws Exception
     */
    @Test
    public void queryString() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //QueryBuilder queryBuilder = QueryBuilders
        //    .queryStringQuery(QueryParser.escape("T:o\""));//文本检索，应该是将查询的词先分成词库中存在的词，然后分别去检索，存在任一存在的词即返回，查询词分词后是OR的关系。需要转义特殊字符
        //searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(10);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Search search = new Search.Builder(query)
                .addIndex(indexName)
                .addType(typeName)
                .build();
        SearchResult result = jestClient.execute(search);

        List<Hit<Object, Void>> hits = result.getHits(Object.class);
        System.out.println("Size:" + hits.size());
        for (Hit<Object, Void> hit : hits) {
            Object news = hit.source;
            System.out.println(news.toString());
        }
    }

    /**
     * Count文档
     * @throws Exception
     */
    @Test
    public void count() throws Exception {
        String[] name = new String[]{ "T:o\"m-", "Jerry" };
        String from = "2016-09-01T00:00:00";
        String to = "2016-10-01T00:00:00";
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termsQuery("name", name))
                .must(QueryBuilders.rangeQuery("birth").gte(from).lte(to));
        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        System.out.println(query);

        Count count = new Count.Builder()
                .addIndex(indexName)
                .addType(typeName)
                .query(query)
                .build();
        CountResult results = jestClient.execute(count);

        Double counts = results.getCount();
        System.out.println("Count:" + counts);
    }

    /**
     * 根据IDGet文档
     * @throws Exception
     */
    @Test
    public void get() throws Exception {
        String id = "2";

        Get get = new Get.Builder(indexName, id).type(typeName).build();
        JestResult result = jestClient.execute(get);

        if (result.isSucceeded()) {
            Object news = result.getSourceAsObject(Object.class);
            System.out.println(news.toString());
        }
    }

    /**
     * 根据ID删除文档
     * @throws Exception
     */
    @Test
    public void deleteIndexDocument() throws Exception {
        String id = "2";
        DocumentResult dr = jestClient.execute(new Delete.Builder(id).index(indexName).type(typeName).build());
        boolean result = dr.isSucceeded();
        System.out.println(result);
    }

    /**
     * 删除索引
     * @throws Exception
     */
    @Test
    public void deleteIndex() throws Exception {
        JestResult jr = jestClient.execute(new DeleteIndex.Builder(indexName).build());
        boolean result = jr.isSucceeded();
        System.out.println(result);
    }

    /**
     * 将删除所有的索引
     * @throws Exception
     */
    @Test
    public void deleteIndexAll() throws Exception {
        DeleteIndex deleteIndex = new DeleteIndex.Builder(indexName).build();
        JestResult result = jestClient.execute(deleteIndex);
        System.out.println(result.getJsonString());
    }

    /**
     * 清缓存
     * @throws Exception
     */
    @Test
    public void clearCache() throws Exception {
        ClearCache closeIndex = new ClearCache.Builder().build();
        JestResult result = jestClient.execute(closeIndex);
        System.out.println(result.getJsonString());
    }

    /**
     * 关闭索引
     * @throws Exception
     */
    @Test
    public void closeIndex() throws Exception {
        CloseIndex closeIndex = new CloseIndex.Builder(indexName).build();
        JestResult result = jestClient.execute(closeIndex);
        System.out.println(result.getJsonString());
    }

    /**
     * 优化索引
     * @throws Exception
     */
    @Test
    public void optimize() throws Exception {
        Optimize optimize = new Optimize.Builder().build();
        JestResult result = jestClient.execute(optimize);
        System.out.println(result.getJsonString());
    }

    /**
     * 刷新索引
     * @throws Exception
     */
    @Test
    public void flush() throws Exception {
        Flush flush = new Flush.Builder().build();
        JestResult result = jestClient.execute(flush);
        System.out.println(result.getJsonString());
    }

    /**
     * 判断索引目录是否存在
     * @throws Exception
     */
    @Test
    public void indicesExists() throws Exception {
        IndicesExists indicesExists = new IndicesExists.Builder(indexName).build();
        JestResult result = jestClient.execute(indicesExists);
        System.out.println(result.getJsonString());
    }

    /**
     * 查看节点信息
     * @throws Exception
     */
    @Test
    public void nodesInfo() throws Exception {
        NodesInfo nodesInfo = new NodesInfo.Builder().build();
        JestResult result = jestClient.execute(nodesInfo);
        System.out.println(result.getJsonString());
    }

    /**
     * 查看集群健康信息
     * @throws Exception
     */
    @Test
    public void health() throws Exception {
        Health health = new Health.Builder().build();
        JestResult result = jestClient.execute(health);
        System.out.println(result.getJsonString());
    }

    /**
     * 节点状态
     * @throws Exception
     */
    @Test
    public void nodesStats() throws Exception {
        NodesStats nodesStats = new NodesStats.Builder().build();
        JestResult result = jestClient.execute(nodesStats);
        System.out.println(result.getJsonString());
    }

    /**
     * 分页查询
     * @throws Exception
     */
    @Test
    public void searchPage()throws Exception{

    }

    /**
     * 更新Document
     * @throws Exception
     */
    @Test
    public void updateDocument() throws Exception {
        CsdnBlog article = CsdnBlog.builder()
                .author("匿名")
                .title("中国3颗卫星拍到阅兵现场高清照")
                .content("据中国资源卫星应用中心报道，9月3日，纪念中国人民抗日战争暨世界反法西斯战争胜利70周年大阅兵在天安门广场举行。资源卫星中心针对此次盛事，综合调度在轨卫星，9月1日至3日连续三天持续观测首都北京天安门附近区域，共计安排5次高分辨率卫星成像。在阅兵当日，高分二号卫星、资源三号卫星及实践九号卫星实现三星联合、密集观测，捕捉到了阅兵现场精彩瞬间。为了保证卫星准确拍摄天安门及周边区域，提高数据处理效率，及时制作合格的光学产品，资源卫星中心运行服务人员从卫星观测计划制定、复核、优化到系统运行保障、光学产品图像制作，提前进行了周密部署，并拟定了应急预案，为圆满完成既定任务奠定了基础。")
                .view(110)
                .tag("java,,android")
                .date(getCurrentDateTime()).build();

        JSONObject json = new JSONObject();
        json.put("doc", article);

        Update update = new Update.Builder(json.toJSONString()).index(indexName).type(typeName).id("").build();
        JestResult result = jestClient.execute(update);
        System.out.println(result.getJsonString());
    }


    /**
     * 删除Document
     * @throws Exception
     */
    @Test
    public void deleteDocument() throws Exception {

        Delete delete = new Delete.Builder("").index(indexName).type(typeName).build();
        JestResult result = jestClient.execute(delete);
        System.out.println(result.getJsonString());
    }

    /**
     * 获取Document
     * @throws Exception
     */
    @Test
    public void getDocument() throws Exception {
        Get get = new Get.Builder(indexName,"").type(typeName).build();
        JestResult result = jestClient.execute(get);
        CsdnBlog article = result.getSourceAsObject(CsdnBlog.class);
        System.out.println(article.getTitle()+","+article.getContent());
    }

    /**
     * Suggestion
     * @throws Exception
     */
    @Test
    public void suggest() throws Exception{
        String suggestionName = "my-suggestion";
        Suggest suggest = new Suggest.Builder("{" +
                "  \"" + suggestionName + "\" : {" +
                "    \"text\" : \"the amsterdma meetpu\"," +
                "    \"term\" : {" +
                "      \"field\" : \"body\"" +
                "    }" +
                "  }" +
                "}").build();
        SuggestResult suggestResult = jestClient.execute(suggest);
        System.out.println(suggestResult.isSucceeded());
        List<SuggestResult.Suggestion> suggestionList = suggestResult.getSuggestions(suggestionName);
        System.out.println(suggestionList.size());
        for(SuggestResult.Suggestion suggestion:suggestionList){
            System.out.println(suggestion.text);
        }
    }

    /**
     * 查询全部
     * @throws Exception
     */
    @Test
    public void searchAll() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(indexName)
                .build();
        SearchResult result = jestClient.execute(search);
        System.out.println("本次查询共查到："+result.getTotal()+"篇文章！");
        List<Hit<CsdnBlog,Void>> hits = result.getHits(CsdnBlog.class);
        for (Hit<CsdnBlog, Void> hit : hits) {
            CsdnBlog source = hit.source;
            source.print();
        }
    }

    /**
     * 搜索高亮显示
     * @throws Exception
     */
    @Test
    public void createSearch() throws Exception {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("view", "200"));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("view");//高亮title
        highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
        highlightBuilder.fragmentSize(500);//高亮内容长度
        searchSourceBuilder.highlighter(highlightBuilder);

        System.out.println(searchSourceBuilder.toString());

        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(indexName)
                .build();

        SearchResult result = jestClient.execute(search);
        System.out.println(result.getJsonString());
        System.out.println("本次查询共查到："+result.getTotal()+"篇文章！");
        List<Hit<CsdnBlog,Void>> hits = result.getHits(CsdnBlog.class);

        System.out.println(hits.size());
        for (Hit<CsdnBlog, Void> hit : hits) {

            CsdnBlog source = hit.source;
            //获取高亮后的内容
            Map<String, List<String>> highlight = hit.highlight;

            List<String> views = highlight.get("view");//高亮后的title
            if(views != null){
                source.setView(Integer.valueOf(views.get(0)));
            }
            source.print();
        }
    }

    /**
     * 分页
     * @throws IOException
     */
    @Test
    public void page() throws IOException{

        int pageNumber=1;
        int pageSize=10;

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryStringQuery("JAVA"));
        searchSourceBuilder.from((pageNumber - 1) * pageSize);//设置起始页
        searchSourceBuilder.size(pageSize);//设置页大小
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(indexName)// 索引名称
                .build();
        SearchResult result = jestClient.execute(search);
        // 自动解析
        JsonObject jsonObject = result.getJsonObject();
        JsonObject hitsobject = jsonObject.getAsJsonObject("hits");
        long took = jsonObject.get("took").getAsLong();
        long total = hitsobject.get("total").getAsLong();
        System.out.println("took:"+took+"  "+"total:"+total);
    }

    private String getCurrentDateTime() {
        return sdf.format(new Date());
    }

}
