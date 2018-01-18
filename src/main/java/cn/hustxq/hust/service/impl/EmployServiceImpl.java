package cn.hustxq.hust.service.impl;

import cn.hustxq.hust.bean.EmployInfo;
import cn.hustxq.hust.dao.EmployDao;
import cn.hustxq.hust.service.EmployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author hustxq.
 * @Date 2017/9/22 9:56
 */
@Service
public class EmployServiceImpl implements EmployService {
    @Autowired
    EmployDao employDao;

    @Override
    public int submit(Map map) {
        return employDao.submit(map);
    }

    @Override
    public int total() {
        return employDao.total();
    }

    @Override
    public List<EmployInfo> feedlist(Map map) {
        return employDao.feedlist(map);
    }

    @Override
    public List<EmployInfo> freshEmploy(String time) {
        Map<String,Object> map = new HashMap<>();
        map.put("time",time);
        return employDao.freshEmploy(map);
    }

    @Override
    public String employInfoById(int id) {
        return employDao.employInfoById(id).getNote();
    }
}
