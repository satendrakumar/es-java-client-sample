package com.es.service;


import org.elasticsearch.action.deletebyquery.DeleteByQueryAction;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

/***
 * add delete by query plugin to Elastisearch
 * $ bin/plugin install delete-by-query
 */
public class DeleteService {


    Client client;

    public DeleteService(Client client) {
        this.client = client;
    }


    public boolean delete(String id) {
        return client.prepareDelete("test", "tweet", id).get().isFound();
    }

    public long deleteByQuery(String name) {
        return new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE).setQuery(QueryBuilders.termQuery("name", name))
                .execute().actionGet().getTotalDeleted();
    }
}
