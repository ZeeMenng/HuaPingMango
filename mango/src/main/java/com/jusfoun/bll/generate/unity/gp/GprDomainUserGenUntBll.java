﻿package com.zee.bll.generate.unity.gp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zee.bll.generate.unity.base.BaseUntBll;
import com.zee.dao.unity.gp.IGprDomainUserUntDal;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GprDomainUser;
import com.zee.ent.parameter.gp.GprDomainUserParameter;
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
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2021/1/28 16:07:03
 * @description 应用领域拥有的用户。 业务逻辑处理类，扩展自BaseUntBll<GprDomainUser>，自动生成。
 */
public class GprDomainUserGenUntBll extends BaseUntBll<GprDomainUser> {

	@Autowired
	protected IGprDomainUserUntDal gprDomainUserUntDal;


	public ResultModel updateList(GprDomainUserParameter.UpdateList updateListParam) {
		return updateList(updateListParam, isLogWrite);
	}

	public ResultModel updateList(GprDomainUserParameter.UpdateList updateListParam, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(updateListParam);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATELIST.getCode());
			result.setOperTypeText(OperType.UPDATELIST.getText());
			result.setRemark("");

			int i = baseUntDal.updateList(updateListParam.getIdList(),updateListParam.getEntity());

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATELIST_S.getCode());
			result.setResultMessage(OperResult.UPDATELIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != updateListParam.getIdList().size()) {
				result.setResultCode(OperResult.UPDATELIST_F.getCode());
				result.setResultMessage(OperResult.UPDATELIST_F.getText() + "要修改的记录中有些已被删除。");
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

	public ResultModel getList(GprDomainUserParameter.GetList getListParam) {
		return getList(getListParam, isLogRead);
	}

	public ResultModel getList(GprDomainUserParameter.GetList getListParam, boolean isLog) {
		ResultModel result = new ResultModel();

		try {

			JSONObject jsonObject = JSONObject.fromObject(getListParam);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLIST.getCode());
            result.setOperTypeText(OperType.GETLIST.getText());
			result.setRemark("");
			

			GprDomainUserParameter.GetList.EntityRelated entityRelated = getListParam.getEntityRelated();
			GprDomainUserParameter.GetList.Page page = getListParam.getPage();
			ArrayList<GprDomainUserParameter.GetList.Order> orderList = getListParam.getOrderList();

			Map<String, Object> map = new HashMap<String, Object>();
			if (entityRelated != null)
				map.put("EntityRelated", entityRelated);
			if (page != null) {
				map.put("Page", page);
			}
			if (orderList != null) {
				map.put("OrderList", orderList);
			}

			PageHelper.startPage(page.getPageIndex(), page.getPageSize());

			List<GprDomainUser> list = baseUntDal.getList(map);
			PageInfo<GprDomainUser> pageInfo = new PageInfo<GprDomainUser>(list);
			List<GprDomainUser> modelList = pageInfo.getList();

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(pageInfo.getTotal());
			result.setResultCode(OperResult.GETLIST_S.getCode());
			result.setResultMessage(OperResult.GETLIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLIST_F.getCode());
			result.setResultMessage(OperResult.GETLIST_F.getText() + "：" + e.getMessage());
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
		return deleteByDomainId(domainId, isLogRead);
	}

	public ResultModel deleteByDomainId(String domainId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(domainId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据外键，删除中间表数据。");

			int i = gprDomainUserUntDal.deleteByDomainId( domainId);

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


 	public ResultModel deleteByDomainIdList(ArrayList<String> domainIdList) {
		return deleteByDomainIdList(domainIdList, isLogRead);
	}

	public ResultModel deleteByDomainIdList(ArrayList<String> domainIdList, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject( domainIdList).toString());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据外键列表，批量删除本表数据。");

			int i = gprDomainUserUntDal.deleteByDomainIdList(domainIdList);

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


	public ResultModel getListByDomainId(String domainId) {
		return getListByDomainId(domainId, isLogRead);
	}

	public ResultModel getListByDomainId(String domainId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(domainId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLISTBYFOREIGNKEY.getCode());
			result.setOperTypeText(OperType.GETLISTBYFOREIGNKEY.getText());
			result.setRemark("根据外键，查询中间表。");

			List<GprDomainUser> modelList = gprDomainUserUntDal.getListByDomainId(domainId);

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(modelList.size());
			result.setResultCode(OperResult.GETLISTBYFOREIGNKEY_S.getCode());
			result.setResultMessage(OperResult.GETLISTBYFOREIGNKEY_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (modelList.isEmpty()) {
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.GETLISTBYFOREIGNKEY_F.getCode());
				result.setResultMessage(OperResult.GETLISTBYFOREIGNKEY_F.getText() + "：不存在相应记录！");
			}
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLISTBYFOREIGNKEY_F.getCode());
			result.setResultMessage(OperResult.GETLISTBYFOREIGNKEY_F.getText() + "：" + e.getMessage());
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
 
 	public ResultModel deleteByUserId(String userId) {
		return deleteByUserId(userId, isLogRead);
	}

	public ResultModel deleteByUserId(String userId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据外键，删除中间表数据。");

			int i = gprDomainUserUntDal.deleteByUserId( userId);

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


 	public ResultModel deleteByUserIdList(ArrayList<String> userIdList) {
		return deleteByUserIdList(userIdList, isLogRead);
	}

	public ResultModel deleteByUserIdList(ArrayList<String> userIdList, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject( userIdList).toString());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据外键列表，批量删除本表数据。");

			int i = gprDomainUserUntDal.deleteByUserIdList(userIdList);

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


	public ResultModel getListByUserId(String userId) {
		return getListByUserId(userId, isLogRead);
	}

	public ResultModel getListByUserId(String userId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(userId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLISTBYFOREIGNKEY.getCode());
			result.setOperTypeText(OperType.GETLISTBYFOREIGNKEY.getText());
			result.setRemark("根据外键，查询中间表。");

			List<GprDomainUser> modelList = gprDomainUserUntDal.getListByUserId(userId);

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(modelList.size());
			result.setResultCode(OperResult.GETLISTBYFOREIGNKEY_S.getCode());
			result.setResultMessage(OperResult.GETLISTBYFOREIGNKEY_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (modelList.isEmpty()) {
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.GETLISTBYFOREIGNKEY_F.getCode());
				result.setResultMessage(OperResult.GETLISTBYFOREIGNKEY_F.getText() + "：不存在相应记录！");
			}
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLISTBYFOREIGNKEY_F.getCode());
			result.setResultMessage(OperResult.GETLISTBYFOREIGNKEY_F.getText() + "：" + e.getMessage());
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
 
 	public ResultModel deleteByCompositeIdList(ArrayList<GprDomainUser> gprDomainUserList) {
		return deleteByCompositeIdList(gprDomainUserList, isLogWrite);
	}

	public ResultModel deleteByCompositeIdList(ArrayList<GprDomainUser> gprDomainUserList, boolean isLog) {
		ResultModel result = new ResultModel();
		if (gprDomainUserList == null || gprDomainUserList.isEmpty()) {
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}

		try {
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(gprDomainUserList).toString());
			result.setAddTime(DateUtils.getCurrentTime());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("");

			int i = gprDomainUserUntDal.deleteByCompositeIdList(gprDomainUserList);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETELIST_S.getCode());
			result.setResultMessage(OperResult.DELETELIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != gprDomainUserList.size()) {
				result.setResultMessage(OperResult.DELETELIST_S.getText() + "要删除的记录中有些已被删除。");
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
   
  

}





