package cn.com.ostar.consul.provider2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tim.zhang
 * @program: provider2
 * @create: 2018-07-31 15:48
 * @modified:
 * @description:
 **/
@RestController
@Slf4j
public class HelloWorld {

    @RequestMapping("/")
    public String hello() {
        log.info("provider 2 hello word!");

        return "this is first consul service! hello world! provider 2";
    }

    @RequestMapping("/hello")
    public String helloWord() {
        log.info("hello word!  provider health check!");
        return "hello word!  provider health check!";
    }
}
