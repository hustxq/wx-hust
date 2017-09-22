package cn.hustxq.hust.controller;

import cn.hustxq.hust.bean.EmployInfo;
import cn.hustxq.hust.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hustxq.
 * @Date 2017/9/22 9:40
 */
@RestController
public class EmployController {
//    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmployService employService;

    @RequestMapping("/feedlist")
    public List<EmployInfo> feedlist(){
        Map<String,Object> map = new HashMap<>();
        List<EmployInfo> list = employService.feedlist();
        return list;
    }

    @RequestMapping("/fresh-employ")
    public List<EmployInfo> freshEmploy(HttpServletRequest request){
        String time = request.getParameter("time");
        System.out.println(time);
        return employService.freshEmploy(time);
    }

    @RequestMapping(value = "/employ-info/{id}", method = RequestMethod.GET)
    public Map employInfoById(@PathVariable("id") int id){
        System.out.println(id);
        String note = employService.employInfoById(id);
        Map<String,Object> map = new HashMap<>();
        map.put("note",note);
        return map;
    }

    @RequestMapping("/employee")
    public Map employee(HttpServletRequest request){
        String name = request.getParameter("name");
        String pos = request.getParameter("pos");
        String note = request.getParameter("note");
        System.out.println(name+","+pos+","+note);
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name",name);
        map.put("pos",pos);
        map.put("note",note);
        int res = employService.submit(map);
        if (res>0){
            System.out.println(res);
            map = new HashMap<>();
            map.put("result","success");
        }else {
            map = new HashMap<>();
            map.put("result","fail");
        }
        return map;
    }

}
