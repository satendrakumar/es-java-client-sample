package com.es.app;


import com.es.service.CountService;
import com.es.service.DataService;
import com.es.service.DeleteService;
import com.es.service.IngestService;
import com.es.util.ESManager;
import org.elasticsearch.client.Client;


public class ESApp {

    public static void main(String[] args) {
        ESManager esManager = new ESManager();
        Client client = esManager.getClient("localhost", 9300).get();

        CountService countService = new CountService(client);
        DataService dataService = new DataService(client);
        IngestService ingestService = new IngestService(client);
        DeleteService deleteService = new DeleteService(client);

        //count
        System.out.println("\ngetMatchAllQueryCount from ES::: " + countService.getMatchAllQueryCount());
        System.out.println("\ngetBoolQueryCount from ES::: " + countService.getBoolQueryCount());
        System.out.println("\ngetPhraseQueryCount from ES::: " + countService.getPhraseQueryCount());


        // Data
        System.out.println("\ngetMatchAllQueryData from ES::: ");
        dataService.getMatchAllQueryData().forEach(item -> System.out.println(item));

        System.out.println("\ngetBoolQueryData from ES::: ");
        dataService.getBoolQueryData().forEach(item -> System.out.println(item));

        System.out.println("\ngetPhraseQueryData from ES::: ");
        dataService.getPhraseQueryData().forEach(item -> System.out.println(item));

        //Ingest
        String json1 = "{" +
                "\"name\":\"skyji\"," +
                "\"job\":\"Admin\"," +
                "\"location\":\"India\"" +
                "}";

        String json2 = "{" +
                "\"name\":\"jom\"," +
                "\"job\":\"assiant\"," +
                "\"location\":\"Meana\"" +
                "}";
        // ingest single record
        // System.out.println("\nIngestService response::: " +ingestService.ingest("tweet",json) );

        // ingest batch of records
        //  System.out.println("\nIngestService response::: " + ingestService.ingest("tweet", Arrays.asList(json1, json2)));


        // Delete
        // delete one record by id
        //System.out.println("delete by id " + deleteService.delete("AVSMh1LBWlqOklhqtVNs"));
        //delete record by query
        System.out.println("delete by query " + deleteService.deleteByQuery("satendra"));
        client.close();

    }

}
