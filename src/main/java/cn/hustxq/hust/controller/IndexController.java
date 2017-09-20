package cn.hustxq.hust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author hustxq.
 * @Date 2017/9/20 16:41
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Map json(){
        Map map = new HashMap<>();
        map.put("name","xq");
        return map;
    }

    @RequestMapping("/dev")
    public String dev(){
        return "weui/dev/index";
    }

}
