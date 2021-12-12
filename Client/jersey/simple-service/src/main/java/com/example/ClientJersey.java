package com.example;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.sse.SseEventSource;

public class ClientJersey {
  public static void main(String[] args) {
    Client client = ClientBuilder.newBuilder().build();
    WebTarget target = client.target("http://localhost:8080/test");
    SseEventSource sseEventSource = SseEventSource.target(target).build();
    sseEventSource.register(event -> System.out.println(event));
    sseEventSource.open();
    //通信を切断できる
    sseEventSource.close();
  }
}
