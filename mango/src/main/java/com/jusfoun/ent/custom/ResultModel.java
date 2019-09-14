package com.jusfoun.ent.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jusfoun.ent.extend.gp.GpOperLog;
import com.jusfoun.utl.SymbolicConstant;

@JsonIgnoreProperties({ "addTime", "domainId", "id", "isSuccessValue", "incomeValue", "operTypeCode", "operTypeText", "remark", "returnValue", "tableName" })
public class ResultModel extends GpOperLog {

	// 查询操作是否成功
	private boolean isSuccess = false;

	private Object data;

	public boolean getIsSuccess() {

		isSuccess = this.getIsSuccessCode() == null ? false : this.getIsSuccessCode() == SymbolicConstant.DCODE_BOOLEAN_T ? true : false;
		return isSuccess;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
