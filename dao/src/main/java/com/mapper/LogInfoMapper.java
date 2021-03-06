package com.mapper;

import com.base.BaseMapper;
import com.model.generate.LogInfo;
import com.model.generate.LogInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogInfoMapper extends BaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    int countByExample(LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    int deleteByExample(LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    int insert(LogInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    int insertSelective(LogInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    List<LogInfo> selectByExample(LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    LogInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    int updateByExampleSelective(@Param("record") LogInfo record, @Param("example") LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    int updateByExample(@Param("record") LogInfo record, @Param("example") LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    int updateByPrimaryKeySelective(LogInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbggenerated Mon Jan 13 16:46:43 CST 2020
     */
    int updateByPrimaryKey(LogInfo record);
}