package com.jusfoun.bll.extend.split.gp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jusfoun.bll.generate.split.gp.GpResourceGenSplBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GpResource;
import com.jusfoun.set.enumer.OperResult;
import com.jusfoun.set.enumer.OperType;
import com.jusfoun.set.exception.GlobalException;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.Tools;

import net.sf.json.JSONArray;

/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2018/5/7 15:00:36
 * @description 通用文件信息存储表。 业务逻辑处理类，扩展自BaseSplBll<GpResource>，可手动更改。
 */
@Service("gpResourceSplBll")
public class GpResourceSplBll extends GpResourceGenSplBll {

	public ResultModel getListByBusinessId(String businessId) {
		return getListByBusinessId(businessId, isLogRead);
	}

	public ResultModel getListByBusinessId(String businessId, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(businessId);

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLIST.getCode());
			result.setOperTypeText(OperType.GETLIST.getText());
			result.setRemark("");

			List<GpResource> grResourceList = gpResourceSplDal.getListByBusinessId(businessId);

			result.setReturnValue(JSONArray.fromObject(grResourceList).toString());
			result.setData(grResourceList);
			result.setTotalCount(grResourceList.size());
			result.setResultCode(OperResult.GETLISTBYSQL_S.getCode());
			result.setResultMessage(OperResult.GETLISTBYSQL_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (grResourceList.isEmpty()) {
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.GETLISTBYSQL_F.getCode());
				result.setResultMessage(OperResult.GETLISTBYSQL_F.getText() + "：不存在相应记录！");
			}
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLISTBYSQL_F.getCode());
			result.setResultMessage(OperResult.GETLISTBYSQL_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			e.printStackTrace();
			throw globalException;
		} finally {
			if (isLog)
				operationLogDal.add(result);
		}
		return result;
	}

	
}
