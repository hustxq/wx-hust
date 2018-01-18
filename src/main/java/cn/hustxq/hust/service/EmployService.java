package cn.hustxq.hust.service;

import cn.hustxq.hust.bean.EmployInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author hustxq.
 * @Date 2017/9/22 9:54
 */

public interface EmployService {
    int submit(Map map);
    int total();
    List<EmployInfo> feedlist(Map map);
    List<EmployInfo> freshEmploy(String time);
    String employInfoById(int id);
}
