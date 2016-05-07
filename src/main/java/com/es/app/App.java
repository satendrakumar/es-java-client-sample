package com.es.app;


import com.es.util.ESManager;
import com.es.service.CountService;
import com.es.service.DataService;
import org.elasticsearch.client.Client;


public class App {

    public static void main(String[] args) {
        ESManager esManager = new ESManager();
        Client client = esManager.getClient("localhost", 9300).get();

        CountService countService = new CountService(client);
        DataService dataService = new DataService(client);

        //count
        System.out.println("\ngetMatchAllQueryCount from ES::: " + countService.getMatchAllQueryCount());
        System.out.println("\ngetBoolQueryCount from ES::: " + countService.getBoolQueryCount());
        System.out.println("\ngetPhraseQueryCount from ES::: " + countService.getPhraseQueryCount());


        // Data
        System.out.println("\ngetMatchAllQueryData from ES::: " );
        dataService.getMatchAllQueryData().forEach(item->System.out.println(item));

        System.out.println("\ngetBoolQueryData from ES::: " );
        dataService.getBoolQueryData().forEach(item->System.out.println(item));

        System.out.println("\ngetPhraseQueryData from ES::: " );
        dataService.getPhraseQueryData().forEach(item->System.out.println(item));

        client.close();




    }


}
