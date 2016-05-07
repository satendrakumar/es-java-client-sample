package com.es.service;


import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

public class DataService {


    Client client;

    public DataService(Client client) {
        this.client = client;
    }

    public List<String> getMatchAllQueryData() {
        QueryBuilder query = matchAllQuery();
        System.out.println("getMatchAllQueryCount query =>" + query.toString());
        SearchHit[] hits = client.prepareSearch("test").setQuery(query).execute().actionGet().getHits().getHits();
        List<String> list = new ArrayList<String>();
        for (SearchHit hit : hits) {
            // hit.sourceAsMap()
            list.add(hit.getSourceAsString());
        }
        return list;
    }

    public List<String> getBoolQueryData() {
        QueryBuilder query = boolQuery().must(
                termQuery("name", "satendra")
        ).must(termQuery("location", "india"));
        System.out.println("getBoolQueryCount query =>" + query.toString());
        SearchHit[] hits = client.prepareSearch("test").setQuery(query).execute().actionGet().getHits().getHits();
        List<String> list = new ArrayList<String>();
        for (SearchHit hit : hits) {
            // hit.sourceAsMap()
            list.add(hit.getSourceAsString());
        }
        return list;
    }

    public List<String> getPhraseQueryData() {
        QueryBuilder query = matchPhraseQuery("name", "satendra");
        System.out.println("getPhraseQueryCount query =>" + query.toString());
        SearchHit[] hits = client.prepareSearch("test").setQuery(query).execute().actionGet().getHits().getHits();
        List<String> list = new ArrayList<String>();
        for (SearchHit hit : hits) {
            // hit.sourceAsMap()
            list.add(hit.getSourceAsString());
        }
        return list;
    }


}
