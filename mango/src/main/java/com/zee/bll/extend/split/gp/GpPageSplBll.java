package com.zee.bll.extend.split.gp;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.gp.GpPageGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.set.enumer.OperResult;
import com.zee.set.enumer.OperType;
import com.zee.set.exception.GlobalException;
import com.zee.utl.DateUtils;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.Tools;

import net.sf.json.JSONArray;

/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2017/12/15 23:41:48
 * @description 系统页面。 业务逻辑处理类，扩展自BaseSplBll<GpPage>，可手动更改。
 */
@Service("gpPageSplBll")
public class GpPageSplBll extends GpPageGenSplBll {

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
			result.setRemark("根据应用领域主键，删除页面记录，同时级联删除页面相关表的记录。");

			int i = gpPageSplDal.delete(id);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETE_S.getCode());
			result.setResultMessage(OperResult.DELETE_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);

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
			result.setRemark("根据应用领域主键，删除页面记录，同时级联删除页面相关表的记录。");

			int i = gpPageSplDal.deleteByDomainId(domainId);

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
			result.setRemark("根据应用领域主键，删除页面记录，同时级联删除页面相关表的记录。");

			int i = gpPageSplDal.deleteByIdList(idList);

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
			result.setRemark("根据应用领域主键列表，删除页面记录，同时级联删除页面相关表的记录。");

			int i = gpPageSplDal.deleteByDomainIdList(domainIdList);

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
