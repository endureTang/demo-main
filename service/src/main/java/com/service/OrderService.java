package com.service;

import com.base.BaseDao;
import com.mapper.OrderinfoMapper;
import com.mapper.ProductMapper;
import com.model.exception.CommonException;
import com.model.generate.Orderinfo;
import com.model.generate.OrderinfoExample;
import com.model.generate.Product;
import com.utils.redis.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service(value = "orderService")
@Transactional(readOnly = true)
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private RedisLock redisLock; //注入redis加锁服务
    @Resource
    private BaseDao baseDao;

    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void spikeOrder(Orderinfo order) throws Exception {


        //订单处理
        OrderinfoMapper orderMapper = baseDao.getMapper(OrderinfoMapper.class);
        ProductMapper productMapper = baseDao.getMapper(ProductMapper.class);
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
        List<Orderinfo> orders = orderMapper.selectByExample(orderExample);
        if(orders!=null && orders.size()>0){
            throw new CommonException("重复秒杀");
        }
        orderMapper.insertSelective(order);//添加订单
        product.setInventory(product.getInventory()-1);
        productMapper.updateByPrimaryKeySelective(product);//秒杀成功，库存减一

    }
}
