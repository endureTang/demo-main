package com.model.responseDto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;

/**
 * 响应消息
 * @author TangRenjian
 *
 */
public class ResponseMsgDto {
	public static final Integer FAIL = -1;
	public static final Integer SUCCESS = 0;
	
	public ResponseMsgDto(){
		this.resultStatus = SUCCESS;
	}

	public ResponseMsgDto(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}

	/**
	 * 状态 -1：异常；0：成功
	 */
	private Integer resultStatus = SUCCESS;
	/**
	 * 错误消息
	 */
	@JSONField(name="errorMsg") 
	private String msg = "";
	/**
	 * 自定义错误码
	 */
	private String errorCode;
	
	/**
	 * 返回列表数据
	 */
	private Map<String, Object> data;
	/**
	 * 返回列表总数
	 */
	private Long total;

	public Integer getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}

	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}

	public void tfcfResp(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "返回代码：" + this.errorCode + ", 消息：" + this.msg;
	}
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
