package com.es.util;


import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.util.Optional;

public class ESManager {

    public Optional<Client> getClient(String host, int port) {
        try {
            Client client = TransportClient.builder().build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
            return Optional.of(client);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
