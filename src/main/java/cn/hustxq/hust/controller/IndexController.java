package cn.hustxq.hust.controller;

import cn.hustxq.hust.bean.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/hust")
public class IndexController {
    @RequestMapping("")
    public String index(ModelMap map){
//        map.addAttribute("head",new Header()); // 添加对象
        return "home";
    }

    @RequestMapping("/include")
    public String include(ModelMap map){
        map.addAttribute("head",new Header()); // 添加对象
        return "include";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Map json(){
        Map map = new HashMap<>();
        map.put("name","xq");
        return map;
    }

    @RequestMapping("/tabs")
    public String dev(){
        return "tabbar";
    }
    @RequestMapping("/e")
    public String employee(){
        return "employee";
    }
    @RequestMapping("/u")
    public String upRefresh(){
        return "ptr";
    }
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}
