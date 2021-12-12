package spring.rest.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;

@RestController
public class SseTestController {
  @Autowired
  ExecutorService executorService;
  //SseEmitterがシングルトンになってしまうため、Completeの時点で2回目の処理を受け付けない
  /*@Autowired
  SseEmitter sseEmitter
  */

  @GetMapping("/test")
  public SseEmitter sseResponse() {
    SseEmitter sseEmitter = new SseEmitter((long) 0);
    executorService.execute(() -> {
      try {
        Thread.sleep(3000);
        sseEmitter.send(10);
        // complete
        // the request and be freed up for the next response to handle.
        sseEmitter.complete();
      } catch (IOException e) {
        // In case of any error while processing the request, complete the process and
        // pass the
        // exception through the normal exception handling of Spring MVC framework and
        // after that
        // response is completed.
        sseEmitter.completeWithError(e);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });

    // executorService.shutdown();
    return sseEmitter;
  }

  @ResponseBody
  @GetMapping("/sam")
  public String test() {
    int i = 0;
    while (true) {
      if (i == 100) {
        break;
      }
      i++;
    }

    return "test";
  }
}
