package com.jusfoun.bll.extend.split.gp;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.jusfoun.bll.generate.split.gp.GprResourceGenSplBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GpUser;
import com.jusfoun.set.enumer.OperResult;
import com.jusfoun.set.enumer.OperType;
import com.jusfoun.set.exception.GlobalException;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2020/7/4 16:32:20
 * @description 附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。 业务逻辑处理类，扩展自BaseSplBll<GprResource>，可手动更改。
 */
@Service("gprResourceSplBll")
public class GprResourceSplBll extends GprResourceGenSplBll {


	
	public ResultModel deleteByBusinessId(String businessId) {
		return deleteByBusinessId(businessId, isLogWrite);
	}


	public ResultModel deleteByBusinessId(String businessId, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(businessId);
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("");

			int i = gprResourceSplDal.deleteByBusinessId(businessId);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETEBYBUSINESSID_S.getCode());
			result.setResultMessage(OperResult.DELETEBYBUSINESSID_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETEBYBUSINESSID_F.getCode());
			result.setResultMessage(OperResult.DELETEBYBUSINESSID_F.getText() + "：" + e.getMessage());
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


	
	
	
	public ResultModel deleteByBusinessIdList(ArrayList<String> businessIdList) {
		return deleteByBusinessIdList(businessIdList, isLogWrite);
	}

	public ResultModel deleteByBusinessIdList(ArrayList<String> businessIdList, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(businessIdList).toString());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据业务ID列表，批量删除附件中间表。");

			int i = gprResourceSplDal.deleteByBusinessIdList(businessIdList);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETELIST_S.getCode());
			result.setResultMessage(OperResult.DELETELIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETELIST_F.getCode());
			result.setResultMessage(OperResult.DELETELIST_F.getText() + "：" + e.getMessage());
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




