package server.springboot.servercatchjson.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import server.springboot.servercatchjson.services.Information;


@Controller
public class JsonCatchController {
    Logger logger = LoggerFactory.getLogger("JsonCatchController");

    @GetMapping("/")
    @ResponseBody
    public String welComePage(){
        return "welcome to JsonCatchController";
    }

    @GetMapping("/json")
    public Information getInformationTest(@RequestBody Information information) {
        logger.info("Json is {}", information.toString());
        return information;
    }

}
