package com.dzmsoft.sms.base.pojo;

public class SmsPersonRequireLine {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sms_person_require_line.id
     * @mbggenerated  Mon Jun 13 13:04:36 CST 2016
     */
    private String id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sms_person_require_line.requirement
     * @mbggenerated  Mon Jun 13 13:04:36 CST 2016
     */
    private String requirement;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column sms_person_require_line.require_id
     * @mbggenerated  Mon Jun 13 13:04:36 CST 2016
     */
    private String requireId;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sms_person_require_line.id
     * @return  the value of sms_person_require_line.id
     * @mbggenerated  Mon Jun 13 13:04:36 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sms_person_require_line.id
     * @param id  the value for sms_person_require_line.id
     * @mbggenerated  Mon Jun 13 13:04:36 CST 2016
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sms_person_require_line.requirement
     * @return  the value of sms_person_require_line.requirement
     * @mbggenerated  Mon Jun 13 13:04:36 CST 2016
     */
    public String getRequirement() {
        return requirement;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sms_person_require_line.requirement
     * @param requirement  the value for sms_person_require_line.requirement
     * @mbggenerated  Mon Jun 13 13:04:36 CST 2016
     */
    public void setRequirement(String requirement) {
        this.requirement = requirement == null ? null : requirement.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column sms_person_require_line.require_id
     * @return  the value of sms_person_require_line.require_id
     * @mbggenerated  Mon Jun 13 13:04:36 CST 2016
     */
    public String getRequireId() {
        return requireId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column sms_person_require_line.require_id
     * @param requireId  the value for sms_person_require_line.require_id
     * @mbggenerated  Mon Jun 13 13:04:36 CST 2016
     */
    public void setRequireId(String requireId) {
        this.requireId = requireId == null ? null : requireId.trim();
    }
}