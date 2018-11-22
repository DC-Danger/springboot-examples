package com.hz.learnboot.es.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 测试控制器
 *
 * Created by hezhao on 2018-07-20 18:33
 */
@RestController
@RequestMapping("/es")
public class EsController {

    private Logger logger = LoggerFactory.getLogger(EsController.class);

    /** 测试索引 */
    private String indexName="test_rest";

    /** 类型 */
    private String typeName = "employee";

    @Autowired
    private RestHighLevelClient client;

    /**
     * 创建索引
     */
    @RequestMapping("/createIndex")
    public Object createIndex() {
        CreateIndexRequest request = new CreateIndexRequest(indexName); // index名必须全小写，否则报错
        try {
            CreateIndexResponse response = client.indices().create(request);
            logger.info(JSON.toJSONString(response));
            return response;
        } catch (IOException e) {
          e.printStackTrace();
        }
        return null;
    }

    /**
     * 插入记录
     */
    @RequestMapping("/insert")
    public String insert() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
            jsonObject.put("age", 25);
            jsonObject.put("name", "j-" + new Random(100).nextInt());
            jsonObject.put("date", new Date());

            IndexRequest indexRequest = new IndexRequest(indexName, typeName, jsonObject.getString("id")); // id为自定义
            indexRequest.source(jsonObject, XContentType.JSON);
            IndexResponse response = client.index(indexRequest);
            logger.info(JSON.toJSONString(response));
            return response.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除记录
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        if(StringUtils.isNotBlank(id)) {
            try{
                DeleteRequest deleteRequest = new DeleteRequest(indexName, typeName, id);
                DeleteResponse response = client.delete(deleteRequest);
                logger.info(JSON.toJSONString(response));
                return "删除id=" + response.getId();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return "id为空";
    }

    /**
     * 批量插入记录
     */
    @RequestMapping("/insertBatch")
    public Object insertBatch() {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            IndexRequest indexRequest;

            for (int i = 1; i < 4; i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + "-" + i);
                jsonObject.put("age", 25 + i);
                jsonObject.put("name", "j-" + i + "-" + new Random(100).nextInt());
                jsonObject.put("date", new Date());

                indexRequest = new IndexRequest(indexName, typeName, jsonObject.getString("id")); // id为自定义
                indexRequest.source(jsonObject, XContentType.JSON);
                bulkRequest.add(indexRequest);
            }

            BulkResponse bulkResponse = client.bulk(bulkRequest);
            logger.info(JSON.toJSONString(bulkResponse));
            return bulkResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量删除记录
     * @return
     */
    @RequestMapping("/deleteBatch")
    public Object deleteBatch(String[] ids) {
        if(ids != null && ids.length > 0) {
            try{
                BulkRequest bulkRequest = new BulkRequest();
                DeleteRequest deleteRequest;

                for (String id : ids) {
                    deleteRequest = new DeleteRequest(indexName, typeName, id);
                    bulkRequest.add(deleteRequest);
                }

                BulkResponse bulkResponse = client.bulk(bulkRequest);
                logger.info(JSON.toJSONString(bulkResponse));
                return bulkResponse;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return "id为空";
    }

    /**
     * 修改记录
     */
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") String id) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("age", 30);
            jsonObject.put("name", "j-" + new Random(100).nextInt() + "-update");
            jsonObject.put("date", new Date());

            UpdateRequest updateRequest = new UpdateRequest(indexName, typeName, jsonObject.getString("id"));
            updateRequest.doc(jsonObject);

            UpdateResponse response = client.update(updateRequest);
            logger.info(JSON.toJSONString(response));
            return response.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询文档
     */
    @RequestMapping("/getData/{id}")
    public Object getData(@PathVariable("id") String id) {
        GetRequest request = new GetRequest(indexName, typeName, id);
        try {
            GetResponse response = client.get(request);
            logger.info(JSON.toJSONString(response));
            return response.getSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询文档
     */
    @RequestMapping("/search")
    public Object search() {
        try {
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.types(typeName);

            SearchResponse response = client.search(searchRequest);
            logger.info(JSON.toJSONString(response));

            List<Map<String, Object>> sourceList = Arrays.stream(response.getHits().getHits())
                    .map(e -> e.getSourceAsMap())
                    .collect(Collectors.toList());
            return sourceList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
