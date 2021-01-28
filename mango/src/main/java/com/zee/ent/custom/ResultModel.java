package com.zee.ent.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zee.ent.extend.gp.GpOperLog;
import com.zee.utl.SymbolicConstant;

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
