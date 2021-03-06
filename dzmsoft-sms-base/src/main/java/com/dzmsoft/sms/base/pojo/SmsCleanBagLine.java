package com.dzmsoft.sms.base.pojo;

import java.math.BigDecimal;

public class SmsCleanBagLine {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_clean_bag_line.id
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_clean_bag_line.clean_bag_id
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    private String cleanBagId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_clean_bag_line.item
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    private String item;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_clean_bag_line.price
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    private BigDecimal price;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_clean_bag_line.id
     *
     * @return the value of sms_clean_bag_line.id
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_clean_bag_line.id
     *
     * @param id the value for sms_clean_bag_line.id
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_clean_bag_line.clean_bag_id
     *
     * @return the value of sms_clean_bag_line.clean_bag_id
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    public String getCleanBagId() {
        return cleanBagId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_clean_bag_line.clean_bag_id
     *
     * @param cleanBagId the value for sms_clean_bag_line.clean_bag_id
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    public void setCleanBagId(String cleanBagId) {
        this.cleanBagId = cleanBagId == null ? null : cleanBagId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_clean_bag_line.item
     *
     * @return the value of sms_clean_bag_line.item
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    public String getItem() {
        return item;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_clean_bag_line.item
     *
     * @param item the value for sms_clean_bag_line.item
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_clean_bag_line.price
     *
     * @return the value of sms_clean_bag_line.price
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_clean_bag_line.price
     *
     * @param price the value for sms_clean_bag_line.price
     *
     * @mbggenerated Mon Jun 13 13:04:36 CST 2016
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}