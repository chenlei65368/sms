package com.dzmsoft.sms.rest.base.dto.message;

public class MsgHeader {
	/**
	 * API接口名称
	 */
	private String method;
	/**
	 * 接入应用的AppKey
	 */
	private String appKey;
	/**
	 * API协议版本
	 */
	private String version;
	/**
	 * 接入应用的appId
	 */
	private String appId;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 签名算法, 
	 * 签名的摘要算法,可选值:md5
	 */
	private String signMethod = "md5";
	/**
	 * 时间戳，格式为yyyy-MM-dd HH:mm:ss, 服务端允许客户端请求最大时间误差为10分钟。
	 */
	private String timestamp;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSignMethod() {
		return signMethod;
	}
	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
