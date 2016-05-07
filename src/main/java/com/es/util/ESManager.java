package com.es.util;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.plugin.deletebyquery.DeleteByQueryPlugin;

import java.net.InetAddress;
import java.util.Optional;

public class ESManager {



    public Optional<Client> getClient(String host, int port) {
        try {
            Settings.Builder setting =Settings.builder().put("client.transport.sniff", false);
            TransportClient client = TransportClient.builder().settings(setting).addPlugin(DeleteByQueryPlugin.class).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
            return Optional.of(client);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


}
