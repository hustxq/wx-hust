package cn.hustxq.hust.dao;

import cn.hustxq.hust.bean.EmployInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author hustxq.
 * @Date 2017/9/22 9:57
 */
public interface EmployDao {
    int submit(Map map);
    int total();
    List<EmployInfo> feedlist(Map map);
    List<EmployInfo> freshEmploy(Map map);
    EmployInfo employInfoById(int id);

}
