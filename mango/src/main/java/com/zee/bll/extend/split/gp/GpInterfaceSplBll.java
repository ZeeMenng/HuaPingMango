package com.zee.bll.extend.split.gp;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GpInterfaceGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpInterface;
import com.zee.set.enumer.OperResult;
import com.zee.set.enumer.OperType;
import com.zee.set.exception.GlobalException;
import com.zee.utl.DateUtils;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2017/12/15 23:41:48
 * @description 系统接口。 业务逻辑处理类，扩展自BaseSplBll<GpInterface>，可手动更改。
 */
@Service("gpInterfaceSplBll")
public class GpInterfaceSplBll extends GpInterfaceGenSplBll {
	
	public ResultModel delete(String id) {
		return delete(id, isLogWrite);
	}

	public ResultModel delete(String id, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(id);
			result.setObjectId(id);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据应用领域主键列表，删除接口记录，同时级联删除接口相关表的记录。");

			int i = gpInterfaceSplDal.delete(id);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETE_S.getCode());
			result.setResultMessage(OperResult.DELETE_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != 1) {
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.DELETE_F.getCode());
				result.setResultMessage(OperResult.DELETE_F.getText() + "：不存在相应记录！");
			}
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETE_F.getCode());
			result.setResultMessage(OperResult.DELETE_F.getText() + "：" + e.getMessage());
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

	public ResultModel deleteByDomainId(String domainId) {
		return deleteByDomainId(domainId, isLogWrite);
	}

	public ResultModel deleteByDomainId(String domainId, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(domainId);
			result.setObjectId(domainId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据应用领域主键列表，删除接口记录，同时级联删除接口相关表的记录。");

			int i = gpInterfaceSplDal.deleteByDomainId(domainId);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETE_S.getCode());
			result.setResultMessage(OperResult.DELETE_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != 1) {
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.DELETE_F.getCode());
				result.setResultMessage(OperResult.DELETE_F.getText() + "：不存在相应记录！");
			}
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.DELETE_F.getCode());
			result.setResultMessage(OperResult.DELETE_F.getText() + "：" + e.getMessage());
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

	public ResultModel deleteByIdList(ArrayList<String> idList) {
		return deleteByIdList(idList, isLogWrite);
	}

	public ResultModel deleteByIdList(ArrayList<String> idList, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(idList).toString());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据应用领域主键列表，删除接口记录，同时级联删除接口相关表的记录。");

			int i = gpInterfaceSplDal.deleteByIdList(idList);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETELIST_S.getCode());
			result.setResultMessage(OperResult.DELETELIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != idList.size()) {
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.DELETELIST_F.getCode());
				result.setResultMessage(OperResult.DELETELIST_F.getText() + "：" + "某些记录已不存在！");
			}
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

	public ResultModel deleteByDomainIdList(ArrayList<String> domainIdList) {
		return deleteByDomainIdList(domainIdList, isLogWrite);
	}

	public ResultModel deleteByDomainIdList(ArrayList<String> domainIdList, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(domainIdList).toString());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据应用领域主键列表，删除接口记录，同时级联删除接口相关表的记录。");

			int i = gpInterfaceSplDal.deleteByDomainIdList(domainIdList);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETELIST_S.getCode());
			result.setResultMessage(OperResult.DELETELIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != domainIdList.size()) {
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.DELETELIST_F.getCode());
				result.setResultMessage(OperResult.DELETELIST_F.getText() + "：" + "某些记录已不存在！");
			}
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

	public ResultModel getModelByUrl(String url) {
		return getModelByUrl(url, isLogRead);
	}

	public ResultModel getModelByUrl(String url, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(url);

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			GpInterface gpInterface = gpInterfaceSplDal.getModelByUrl(url);
			if (gpInterface == null) {
				result.setResultCode(OperResult.GETMODEL_S.getCode());
				result.setResultMessage(OperResult.GETMODEL_S.getText() + "：不存在相应记录！");
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			} else {
				result.setObjectId(gpInterface.getId());
				result.setReturnValue(JSONObject.fromObject(gpInterface).toString());
				result.setData(gpInterface);
				result.setTotalCount(new Long(1));
				result.setResultCode(OperResult.GETMODEL_S.getCode());
				result.setResultMessage(OperResult.GETMODEL_S.getText());
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			}
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODEL_F.getCode());
			result.setResultMessage(OperResult.GETMODEL_F.getText() + "：" + e.getMessage());
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

	public ResultModel isPermitted(String userId, String url) {
		return isPermitted(userId, url, isLogRead);
	}

	public ResultModel isPermitted(String userId, String url, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(url);

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			int i = gpInterfaceSplDal.isPermitted(userId, url);
			if (i == 0) {
				result.setResultCode(OperResult.CUSTOM_S.getCode());
				result.setResultMessage(OperResult.CUSTOM_S.getText() + "：不存在相应记录！");
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			} else {
				result.setReturnValue(String.valueOf(i));
				result.setData(i);
				result.setTotalCount(new Long(1));
				result.setResultCode(OperResult.CUSTOM_S.getCode());
				result.setResultMessage(OperResult.CUSTOM_S.getText());
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			}
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.CUSTOM_F.getCode());
			result.setResultMessage(OperResult.CUSTOM_F.getText() + "：" + e.getMessage());
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
