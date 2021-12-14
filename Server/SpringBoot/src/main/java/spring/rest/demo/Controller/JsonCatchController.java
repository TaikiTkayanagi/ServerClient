package spring.rest.demo.Controller;

import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.demo.Information;


@RestController
public class JsonCatchController {

    org.slf4j.Logger logger = LoggerFactory.getLogger("BridgestoneController");

    @PostMapping("/")
    @ResponseBody
    public Information getJson(@RequestBody Information information, Model model){
        logger.info("Json is {}", information.toString());
        return information;
    }
}
