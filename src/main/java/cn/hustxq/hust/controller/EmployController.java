package cn.hustxq.hust.controller;

import cn.hustxq.hust.bean.EmployInfo;
import cn.hustxq.hust.service.EmployService;
import cn.hustxq.hust.utils.FileUtils;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public Map feedlist(@RequestParam(value = "start",required = false,defaultValue = "0") int start){
        int total = employService.total();
        int tp=1;
        int nums = 5;
        if(total>nums){
            if (total%nums == 0){
                tp = total/nums;
            }else {
                tp = total / nums + 1;
            }
        }
//        System.out.println("total:"+total);
        Map<String,Object> map = new HashMap<>();
        map.put("start",start*nums);
        map.put("offset",nums);
        List<EmployInfo> list = employService.feedlist(map);
        /*for(EmployInfo info:list){
            System.out.println(info);
        }*/
        map = new HashMap<>();
        map.put("total",tp);
        map.put("res",list);
        return map;
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

    //处理文件上传
    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
    public Map testuploadimg(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request){
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        System.out.println(filePath);
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
//        return "uploadimg success";
        Map map = new HashMap();
        map.put("msg","success");
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
