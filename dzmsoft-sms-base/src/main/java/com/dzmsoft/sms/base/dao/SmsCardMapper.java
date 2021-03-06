package com.dzmsoft.sms.base.dao;

import com.dzmsoft.sms.base.pojo.SmsCard;
import com.dzmsoft.sms.base.pojo.SmsCardExample;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SmsCardMapper {
    public PageList<SmsCard> selectByExample(SmsCardExample example,PageBounds pageBounds);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    int countByExample(SmsCardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    int deleteByExample(SmsCardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    int insert(SmsCard record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    int insertSelective(SmsCard record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    List<SmsCard> selectByExample(SmsCardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    SmsCard selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    int updateByExampleSelective(@Param("record") SmsCard record,
            @Param("example") SmsCardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    int updateByExample(@Param("record") SmsCard record, @Param("example") SmsCardExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    int updateByPrimaryKeySelective(SmsCard record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sms_card
     * @mbggenerated  Wed May 04 17:07:43 CST 2016
     */
    int updateByPrimaryKey(SmsCard record);
}