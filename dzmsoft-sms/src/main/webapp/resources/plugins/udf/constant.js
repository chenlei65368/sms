/**
 * 数据字典中的类型
 */
var DICTIONARY_FIELD = {
	// 树型数据字典
	SEX : '0001',
	// 启用、禁用、删除状态
	STATUS : '0002',
	// 行政区域
	ADMIN_REGION : '0004',
	//收款单状态
	RECIPIENT_BILL:'0024',
	//订单状态
	ORDER_STATUS:'0015',
	// 用户类型
	USER_TYPE : '0005',
	// 供应商类型 
	SUPPLIER_TYPE:'0007',
	// 用户状态
	EMP_STATUS:'0008',
	// 平台用户角色
	PLATFORM_ROLE:'0009',
	// APK状态
	APK_STATUS:'0010',
	// APK产品
	APK_PRODUCT:'0011',
	// 保洁员工类型
	CLEAN_EMP_TYPE:'0012',
	// 商品状态
	COMMODITY_STATUS:'0014',
	// 排班状态
	SCHEDULE_STATUS:'0016',
	// 商品订单状态
	COMMODITY_ORDER_STATUS:'0020',
	// 合作伙伴状态
	PARTNER_STATUS:'0027',
	// 民族
	NATION:'0028',
	// 平台用户类型
	PLATFORM_USER_TYPE:'0032'
}
/**
 * http状态
 * 
 * @type
 */
var HTTP_STATUS = {
	SC_UNAUTHORIZED : '401',
	ERR_CONNECTION_REFUSED : '0'
}
/**
 * 应用状态
 */
var APK_STATUS = {
	// 初始
	INIT : '00'
	// 上线
	,
	ONLINE : '01'
	// 删除
	,
	DELETE : '02'
	// 下线
	,
	OFFLINE : '03'
}
/**
 * 数据状态
 */
var DATA_STATUS = {
	// 禁用
	DISABLED : '00'
	// 启用
	,
	ENABLE : '01'
	// 删除
	,
	DELETE : '02',
	// 初始化
	INIT:'03'
}
var PACKAGE_TYPE = {
	// 简单
	SIMPLE : '00',
	// 豪华
	LUXURY : '02',
	// 实用
	PRACTICAL : '01'
}
/**
 * 用户类型
 */
var USER_TYPE = {
	// 保洁员
	CLEANER:'13'
}
/**
 * 员工状态
 */
var EMP_STATUS = {
	// 在岗
	IN_JOB:'01',
	// 离职
	OUT_JOB:'00'
}
/**
 * 付款单状态
 */
var PAYMENT_BILL = {
	// 已支付
	PAID : '01',
	// 未支付
	NONPAYMENT : '00',

}
/**
 * 收款单状态
 */
var RECIPIENT_BILL = {
	// 已支付
	PAID : '02',
	// 待提现
	FORWITHDRAWAL : '00',
	//提现
	WITHDRAW:'01',
	//支付失败
	PAYMENTFAILED:'03'

}
/**
 * 方案类型
 */
var COMMODITY_STATUS = {
	// 初始
	INIT : '00',
	// 上架
	ONLINE : '01',
	// 下架
	OFFLINE : '02'

}


/**
 * 合作伙伴状态
 */
var PARTNER_STATUS = {
	// 加盟
	JOIN:'01',
	// 审核不通过
	RELEASE:'00'
}