package com.mapper;

import com.base.BaseMapper;
import com.model.generate.Orderinfo;
import com.model.generate.OrderinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderinfoMapper extends BaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    int countByExample(OrderinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    int deleteByExample(OrderinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    int insert(Orderinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    int insertSelective(Orderinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    List<Orderinfo> selectByExample(OrderinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    Orderinfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    int updateByExampleSelective(@Param("record") Orderinfo record, @Param("example") OrderinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    int updateByExample(@Param("record") Orderinfo record, @Param("example") OrderinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    int updateByPrimaryKeySelective(Orderinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table orderinfo
     *
     * @mbggenerated Fri Jan 10 13:46:49 CST 2020
     */
    int updateByPrimaryKey(Orderinfo record);
}