package com.service;

import com.aop.annotation.LogAnno;
import com.base.BaseDao;
import com.mapper.OrderinfoMapper;
import com.mapper.ProductMapper;
import com.model.exception.CommonException;
import com.model.generate.Orderinfo;
import com.model.generate.OrderinfoExample;
import com.model.generate.Product;
import com.utils.redis.RedisCacheUtils;
import com.utils.redis.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "orderService")
@Transactional(readOnly = true)
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private RedisLock redisLock; //注入redis加锁服务
    @Resource
    private BaseDao baseDao;
    @Resource
    ProductMapper productMapper;
    @Resource
    private OrderinfoMapper orderinfoMapper;

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @LogAnno(operateType = "秒杀订单")
    public void spikeOrder(Orderinfo order,String stockKey) throws Exception {
        //订单处理
        String productId = order.getProductid();
        Product product = productMapper.selectByPrimaryKey(productId);
        logger.info("剩余库存：{}",product.getInventory());
        //校验商品是否抢光了
        if(product.getInventory() <= 0){
            throw new CommonException("商品已经被抢完");
        }
        //校验用户是否已经秒杀过
        OrderinfoExample orderExample = new OrderinfoExample();
        orderExample.createCriteria().andUseridEqualTo(order.getUserid()).andProductidEqualTo("1");
        List<Orderinfo> orders = orderinfoMapper.selectByExample(orderExample);
        if(orders!=null && orders.size()>0){
            throw new CommonException("重复秒杀");
        }
        orderinfoMapper.insertSelective(order);//添加订单
        product.setInventory(product.getInventory()-1);
        productMapper.updateByPrimaryKeySelective(product);//秒杀成功，库存减一
        RedisCacheUtils.getAndInitRedisValueByKey(redisLock, stockKey,60*60);
        RedisCacheUtils.increment(redisLock,stockKey);//秒杀成功数
    }

    /** 
    * @Description: 校验库存
    * @Param:  
    * @return:  
    * @Author: endure
    * @Date: 2020/1/13 
    */
    public Integer checkInventory(String productId){
        Product product = productMapper.selectByPrimaryKey(productId);
        return product.getInventory();
    }
}
