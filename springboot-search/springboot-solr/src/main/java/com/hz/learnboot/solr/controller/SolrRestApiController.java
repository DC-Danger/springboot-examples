package com.hz.learnboot.solr.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 优化：抽取 Id、text 为一个 JavaBean
 *
 * @Author hezhao
 * @Time 2018-07-22 23:00
 */
@RestController
@RequestMapping("/restapi")
public class SolrRestApiController {

    @Autowired
    private SolrClient client;

    private String coreName = "new_core";

    /**
     * 1、增
     * @param message
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    @PostMapping("/insert")
    public String insert(String message) throws IOException, SolrServerException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String dateString = sdf.format(new Date());
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", dateString);
            doc.setField("text", message);

            /*
             * 如果 spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数 下面都是一样的 即
             * client.commit();
             */

            client.add(coreName, doc);
            client.commit(coreName);
            return dateString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 2、查 id
     * @param id
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    @GetMapping("/get/{id}")
    public String getDocumentById(@PathVariable String id) throws SolrServerException, IOException {
        SolrDocument document = client.getById(coreName, id);
        System.out.println(document);
        return document.toString();

    }

    /**
     * 3、删 id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public String getAllDocuments(@PathVariable String id) {
        try {
            client.deleteById(coreName, id);
            client.commit(coreName);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 4、删 all
     * @return
     */
    @DeleteMapping("deleteAll")
    public String deleteAll() {
        try {

            client.deleteByQuery(coreName, "*:*");
            client.commit(coreName);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 5、改
     * @param message
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    @PutMapping("/update")
    public String update(String id, String message) throws IOException, SolrServerException {
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id", id);
            doc.setField("text", message);

            /*
             * 如果 spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 itaem 这个参数 下面都是一样的 即
             * client.commit();
             */
            client.add(coreName, doc);
            client.commit(coreName);
            return doc.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 6、全：还没实现，也感觉没有必要实现
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    @GetMapping("/get/all")
    public Map<String, Object> getAll()
            throws SolrServerException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        return map;
    }

    /**
     * 7、查  ++:关键字、高亮、分页  ✔
     * @return
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    @GetMapping("/select/{q}/{page}/{size}")
    public Map<String, Object> select(@PathVariable String q, @PathVariable Integer page, @PathVariable Integer size)
            throws SolrServerException, IOException {
        SolrQuery params = new SolrQuery();

        // 查询条件
        params.set("q", q);

        // 排序
        params.addSort("id", SolrQuery.ORDER.desc);

        // 分页
        params.setStart(page);
        params.setRows(size);

        // 默认域
        params.set("df", "text");

        // 只查询指定域
        params.set("fl", "id,text");

        // 开启高亮
        params.setHighlight(true);
        // 设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
        // 设置后缀
        params.setHighlightSimplePost("</span>");

        // solr数据库是 itaem
        QueryResponse queryResponse = client.query(coreName, params);
        SolrDocumentList results = queryResponse.getResults();

        // 数量，分页用
        long total = results.getNumFound();// JS 使用 size=MXA 和 data.length 即可知道长度了（但不合理）

        // 获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
        Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("data", highlight);
        return map;

    }

}
