package com.food.core.pack;

/**
 * 定义业务产生的代码。
 *
 */
public enum BizCode implements ResponseCode {

	//资源上传提示语
	FILE_NOT_EXIST("上传的资源不存在"),
	FILE_NOT_MATCH("请上传PNG、JPG格式的文件"),
	FILE_NOT_LEGAL("非法的上传资源"),
	FILE_UPLOAD_FAIL("图片上传失败"),
	FILE_TO_LARGE("上传的图片资源过大"),
	//支付
	ALIPAY_NO_FAIL("支付预订单生成失败"),
	ALIPAY_ORDER_TIMEOUT("订单超时，请您重新下单"),
	WX_NO_FAIL("支付预订单生成失败"),
	//用户反馈
	FEED_BACK_ERROR("意见反馈失败"),
	PARAMS_ERROR("请求参数不不完整"),
	ILLEGAL_REQUEST("非法请求"),
	//登录相关
	CODE_EXIST("账号不存在"),
	CODE_FREEZE("账号已被禁用"),
	LOGIN_TIME("登陆超时"),
	CODE_IS_EXIST("账号已存在"),
	PWD_ERROR("密码错误"),
	//关注相关
	FOLLOW_NOT_ERROR("只能关注导师"),
	FOLLOW_AGAIN_ERROR("您已经关注过这位导师了"),
	FOLLOW_ERROR("操作失败，请稍后再试"),
	EXPERT_ERROR("入驻失败，请稍后再试"),
	EXPERT__NOT_ERROR("您还未入驻，请先入驻"),
	SPECIAL_ERROR("特价不允许修改"),
	EXPERT_YES_ERROR("您已经入驻了呢，请耐心等待审核"),
	//收藏相关
	COLLECT_NOT_ERROR("商品不存在"),
	YIN_NOT_ERROR("商品不存在"),
	COLLECT_AGAIN_ERROR("这件商品您已经关注过了"),
	COLLECT_ERROR("操作失败，请稍后再试"),
	//课程相关
	CLASS_BUY_ERROR("请先进行商品购买"),
	CLASS_EXIST_ERROR("商品不存在"),
	CLASS_TIME_ERROR("请选择正确的时间"),
	CLASS_ERROR("预约失败请稍后再试"),
	CONFRM_ERROR("非法操作"),
	CONFRM_NOT_ERROR("驳回失败请稍后再试"),
	//回复
	REPLAY_ERROR("回复失败，请稍后再试"),
	//点赞
	GOO_ERROR("您已经点过赞了，请稍后再试"),
	//邮箱相关
	EMAIL_PARAMS_ERROR("请输入正确的邮箱"),
	EMAIL_EXIST_ERROR("邮箱已绑定账号"),
	//短信相关
	SMS_ERROR("短信发送失败"),
	SMS_CODE_INVALID("短信验证码失效,请重新发送"),
	SMS_EXPCTION("短信验证码错误"),
	REG_ERROR("系统繁忙请稍后再试");
	private String msg;

	private BizCode(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String code() {
		return null;
	}

	@Override
	public String msg() {
		return this.msg;
	}

}
