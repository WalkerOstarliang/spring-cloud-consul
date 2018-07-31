package cn.com.ostar.consul.provider1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tim.zhang
 * @program: provider1
 * @create: 2018-07-31 16:02
 * @modified:
 * @description:
 **/
@RestController
@Slf4j
public class HelloWord {

    @RequestMapping("/")
    public String hello() {
        log.info("provider 1 hello word!");

        return "this is first consul service! hello world! provider 1";
    }

    @RequestMapping("/hello")
    public String helloWord() {
        log.info("hello word!  provider health check!");
        return "hello word!  provider health check!";
    }
}
