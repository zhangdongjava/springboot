package xyz.zzzupd.personal.rest;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAspectJAutoProxy
public class IndexRestController {

    @GetMapping("/index")
    public Object index(){
        return "hello!";
    }
}
