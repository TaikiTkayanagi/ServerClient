package spring.rest.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

//DIに登録する
@Configuration
public class Config {
  @Bean
  public SseEmitter sseEmitter() {
    //引数にMaximum timeoutを設定する
    //application.propertiesの設定よりもこっちの引数の時間が優先される
    //0を渡すと無期限になる
    //1000 -> 1秒
    return new SseEmitter((long)0);
  }

  @Bean
  public ExecutorService executorService() {
    return Executors.newSingleThreadExecutor();
  }
}
