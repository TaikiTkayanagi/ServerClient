package com.example;

import java.io.IOException;

import com.Person;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseBroadcaster;
import jakarta.ws.rs.sse.SseEventSink;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to the
     * client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces("application/json")
    public Person getIt() {
        return new Person("Taiki", 20);
    }

    public static int getNum(){
        return (int)(Math.random() * 100);
    }

    //Jerseyライブラリを利用した、SSE
    //非同期が実行されてから、値を返す
    @Path("event")
    public static class SseResource {

        @GET
        @Produces(SseFeature.SERVER_SENT_EVENTS)
        public EventOutput getServerSentEvents() {
            final EventOutput eventOutput = new EventOutput();
            while(true){
                new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < 10; i++) {
                            // ... code that waits 1 second
                            final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                            eventBuilder.name("Test");
                            int num2 = getNum();
                            eventBuilder.data(String.class, "Hello World" + num2 + "!");
                            final OutboundEvent event = eventBuilder.build();
                            eventOutput.write(event);

                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Error when writing the event.", e);
                    } finally {
                        try {
                            eventOutput.close();
                        } catch (IOException ioClose) {
                            throw new RuntimeException("Error when closing the event output.", ioClose);
                        }
                    }
                }
            }).start();
            return eventOutput;
            }

        }
    }

    @Singleton
    @Path("broadcast")
    public static class BroadcasterResource {
        private Sse sse;
        private SseBroadcaster broadcaster;

        public BroadcasterResource(@Context final Sse sse) {
            this.sse = sse;
            this.broadcaster = sse.newBroadcaster();
        }

        @POST
        @Produces(MediaType.TEXT_PLAIN)
        @Consumes(MediaType.TEXT_PLAIN)
        public String broadcastMessage(String message) {
            final OutboundSseEvent event = sse.newEventBuilder().name("message").mediaType(MediaType.TEXT_PLAIN_TYPE)
                    .data(String.class, message).build();

            broadcaster.broadcast(event);

            return "Message '" + message + "' has been broadcast.";
        }

        @GET
        @Produces(MediaType.SERVER_SENT_EVENTS)
        public void listenToBroadcast(@Context SseEventSink eventSink) {
            this.broadcaster.register(eventSink);
        }
    }
}
