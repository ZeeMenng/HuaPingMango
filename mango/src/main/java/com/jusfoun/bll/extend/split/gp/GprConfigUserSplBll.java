﻿package com.jusfoun.bll.extend.split.gp;

import org.springframework.stereotype.Service;

import com.jusfoun.bll.generate.split.gp.GprConfigUserGenSplBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GprConfigUser;
import com.jusfoun.set.enumer.OperResult;
import com.jusfoun.set.enumer.OperType;
import com.jusfoun.set.exception.GlobalException;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.Tools;

import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2021/1/19 11:24:03
 * @description 用户配置信息。 业务逻辑处理类，扩展自BaseSplBll<GprConfigUser>，可手动更改。
 */
@Service("gprConfigUserSplBll")
public class GprConfigUserSplBll extends GprConfigUserGenSplBll {

	public ResultModel updateByCompositeId(GprConfigUser gprConfigUser) {
		return updateByCompositeId(gprConfigUser, isLogWrite);
	}

	public ResultModel updateByCompositeId(GprConfigUser gprConfigUser, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(gprConfigUser);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATE.getCode());
			result.setOperTypeText(OperType.UPDATE.getText());
			result.setRemark("");
			gprConfigUser.setUpdateTime(DateUtils.getCurrentTime());
			int i = gprConfigUserSplDal.updateByCompositeId(gprConfigUser);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATE_S.getCode());
			result.setResultMessage(OperResult.UPDATE_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i == 0) {
				result.setResultCode(OperResult.UPDATE_F.getCode());
				result.setResultMessage(OperResult.UPDATE_F.getText() + "要修改的记录中有些已被删除。");
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			}
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATE_F.getCode());
			result.setResultMessage(OperResult.UPDATE_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				operationLogDal.add(result);
		}

		return result;
	}

}