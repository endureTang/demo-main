package com.service;

import com.base.BaseDao;
import com.mapper.UserMapper;
import com.model.exception.CommonException;
import com.model.generate.UserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Resource
    private BaseDao baseDao;

    public List getAll(Map condition) throws CommonException {
        try {
            UserExample example = new UserExample();
//            example.createCriteria().andNameLike(condition.get("name")+"");
            UserMapper userMapper = baseDao.getMapper(UserMapper.class);
            return  userMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error("系统错误，class:"+this.getClass().getName()+"，method:"+Thread.currentThread().getStackTrace()[2].getMethodName()+e);
           throw new CommonException("系统错误");
        }
    }

    public List getByname(String name) throws CommonException {
        try {
            UserExample example = new UserExample();
            example.createCriteria().andNameEqualTo(name);
            UserMapper userMapper = baseDao.getMapper(UserMapper.class);
            return  userMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error("系统错误，class:"+this.getClass().getName()+"，method:"+Thread.currentThread().getStackTrace()[2].getMethodName()+e);
            throw new CommonException("系统错误");
        }
    }
}
