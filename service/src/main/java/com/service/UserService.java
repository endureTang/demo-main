package com.service;

import com.base.BaseDao;
import com.mapper.UserMapper;
import com.model.exception.CommonException;
import com.model.generate.User;
import com.model.generate.UserExample;
import com.utils.string.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value = "userService")
@Transactional(readOnly = true)
public class UserService {
    private final String USER_CACHE = "user_cache";
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Resource
    private BaseDao baseDao;


    public List getAll(User user) throws CommonException {
        try {
            UserExample example = new UserExample();
            if(user.getName() != null){
                example.createCriteria().andNameLike(user.getName());
            }
            UserMapper userMapper = baseDao.getMapper(UserMapper.class);
            return  userMapper.selectByExample(example);
        } catch (Exception e) {
            logger.error("系统错误，class:"+this.getClass().getName()+"，method:"+Thread.currentThread().getStackTrace()[2].getMethodName()+e);
            throw new CommonException("系统错误");
        }
    }
    @Cacheable(value = USER_CACHE,key = "#name")
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

    @Transactional(rollbackFor = Exception.class)
    public void insertUser(User user) throws Exception {
        UserMapper userMapper = baseDao.getMapper(UserMapper.class);
        int count = userMapper.countByExample(null);
        user.setId(UuidUtil.get32UUID());
        user.setPassword(count+"");
        user.setCreatedate(new Date());
        userMapper.insertSelective(user);
    }
}
