package com.mayikt.constants;

/** @author zsl
 * @date 2017年5月3日 下午4:10:25
 * @Description: */

import org.apache.commons.lang3.StringUtils;

/** <br>
 * 标题: <br>
 * 描述: <br>
 * 公司: www.tydic.com<br>
 * 
 * @autho linyujia
 * @time 2018年4月24日 下午8:29:04 */
public interface Constants {

	/** 成功返回码 */
	String  RESPCODE_SUCCESS                    = "0000";
	/** 失败返回码 */
	String  RESPCODE_ERROR                      = "0001";
	/** 位置异常 */
	String  RESPCODE_UNKNOWN                    = "8888";

	/** 操作成功 */
	String  RESPCODE_SUCCESS_NAME               = "操作成功";
	/** 操作失败 */
	String  RESPCODE_ERROR_NAME                 = "操作失败";
	/** 未知错误 */
	String  RESPCODE_UNKNOWN_NAME               = "未知错误";

	/** 生失效标识：有效 */
	Integer IS_VALID                            = 1;
	/** 无效 **/
	Integer NOT_IS_VALID                            = 0;
	/** 生失效标识：无效 */
	Integer CREATE_TYPE                         = 1;
	
	Integer UPDATE_TYPE                         = 2;

	/** 入参为空code */
	String  RESPCODE_ERROR_PARAMS_IS_EMPTY      = "0002";

	/** 入参%s为空 */
	String  RESPCODE_ERROR_PARAMS_IS_EMPTY_NAME = "入参%s为空";

	/** 调用发送短信接口成功返回码 */
	String  COMPETEN_CECENTRE_REQ_STATUS        = "1";
	
	/** 不在白名单内 */
	String  RESPCODE_ERROR_NOTIN_WHITE_LIST      = "0003";
	
	/** 在黑名单内 */
	String  RESPCODE_ERROR_IN_BLACK_LIST      = "0004";
	String IS_RETURN = "1";
	/** 推广活动ID加密key */
	String ENCRYPT_KEY = "PROMOTE_ACTIVITY_ID";
	
	/** 数据不存在code */
	String  RESPCODE_ERROR_DATA_IS_EMPTY      = "0005";

	/** 数据不存在%s */
	String  RESPCODE_ERROR_DATA_IS_EMPTY_NAME = "%s数据不存在";
	
	/** 号码透传 */
	Integer NUMBER_PASSTHROUGH          = 1;
	
	/** 失败返回码 */
	String  RESPCODE_ERROR_REQ                      = "9999";
	String  LOCK_ERROR                      = "0050";

	/**
	 * 派劵状态,0:校验失败；1:待派劵；2：派券成功；3：派券失败
	 */
	Integer STATUS_0                            = 0;
	Integer STATUS_1                            = 1;
	Integer STATUS_2                            = 2;
	Integer STATUS_3                            = 3;
	String  RESPCODE_ERROR_21      = "21";
	String  RESPCODE_ERROR_NAME_21      = "你未参加活动";
	/** respCode */
	String  RESPCODE_STRING_RESP_CODE = "respCode";
	
	/** respDesc */
	String  RESPCODE_STRING_RESP_DESC = "respDesc";
	Integer BLACK_STATUS = 9;
	Integer WHITE_STATUS = 8;
	String CMB_SEND_SMS_SUCC = "2";
	String CMB_SEND_SMS_FAIL = "4";
	String CMB_SUCC_SEND_SMS_FAIL = "3";
	int REDIS_LOCK_EXPIRE = 2;//redis 锁超时时间
	int REDIS_LOCK_RETRY_COUNT = 20;//redis 锁重试次数
	long REDIS_LOCK_RETRY_INTERVAL_TIME = 100L;//redis 锁每次重试时间间隔

	public static boolean isSuccess(String code) {
		String str=code.replaceAll("0", "");
		return StringUtils.isBlank(str)?true:false;
	}
	
	Integer STATUS_ON                            = 2; //已上架
	Integer MOBILE_ACCESS                            = 1; //手厅进入
}
