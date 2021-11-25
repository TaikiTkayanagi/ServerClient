package spring.rest.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTestController {
  @GetMapping("/")
  public Information infoTest(){
    return new Information("Taiki", 20);
  }
}
