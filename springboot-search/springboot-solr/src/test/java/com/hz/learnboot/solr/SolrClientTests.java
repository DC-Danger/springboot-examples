package com.hz.learnboot.solr;

import com.hz.learnboot.solr.domain.User;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrClientTests {

    private SolrClient client;

    @Before
    public void initialClient() {
        client = (new HttpSolrClient.Builder("http://127.0.0.1:8983/solr/new_core")).build();
    }

    @Test
    public void test1() {
        try {
            // 创建索引
            SolrInputDocument solrInputFields=new SolrInputDocument();
            solrInputFields.addField("id", "1");
            solrInputFields.addField("username", "admin");
            solrInputFields.addField("password", "11111");
            client.add(solrInputFields);
            client.commit();

            // 基于实体类创建索引
            List<User> users=new ArrayList<User>();
            users.add(new User(7,"admin7","111111","你好"));
            users.add(new User(8,"admin8","222222","你不好"));
            client.addBeans(users);
            client.commit();

            // 查询第一种方式
            ModifiableSolrParams params =new ModifiableSolrParams();
            params.add("q","username:admin");
            params.add("ws","json");
            params.add("start","0");
            params.add("rows","10");
            QueryResponse queryResponse=client.query(params);
            System.out.println(queryResponse);

            // 查询第二种方式
            int page = 1;
            int rows = 10;

            SolrQuery solrQuery = new SolrQuery(); // 构造搜索条件
            solrQuery.setQuery("username:7");// 搜索关键词
            // 设置分页
            solrQuery.setStart((Math.max(page, 1) - 1) * rows);
            solrQuery.setRows(rows);
            QueryResponse  docs = client.query(solrQuery);
            SolrDocumentList solrDocuments=docs.getResults();
            for(SolrDocument sd : solrDocuments){
                System.out.println(sd.get("id")+"#"+sd.get("username")+"#"+sd.get("password")+"#"+sd.get("vsername"));
            }

            //List<User> userList=docs.getBeans(User.class); // 直接转实体(!这个B有问题，只有用上面的循环赋值比较靠谱)

            // 删除索引
            client.deleteByQuery("id:2");
            client.commit();

            // 通过版本号删除索引
            client.deleteById("123123");
            client.commit();

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
