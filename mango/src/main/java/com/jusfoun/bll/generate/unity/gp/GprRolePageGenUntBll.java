﻿package com.jusfoun.bll.generate.unity.gp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jusfoun.bll.generate.unity.base.BaseUntBll;
import com.jusfoun.dao.unity.gp.IGprRolePageUntDal;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GprRolePage;
import com.jusfoun.ent.parameter.gp.GprRolePageParameter;
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
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2020/8/27 11:19:15
 * @description 角色拥有的页面权限。 业务逻辑处理类，扩展自BaseUntBll<GprRolePage>，自动生成。
 */
public class GprRolePageGenUntBll extends BaseUntBll<GprRolePage> {

	@Autowired
	protected IGprRolePageUntDal gprRolePageUntDal;


	public ResultModel updateList(GprRolePageParameter.UpdateList updateListParam) {
		return updateList(updateListParam, isLogWrite);
	}

	public ResultModel updateList(GprRolePageParameter.UpdateList updateListParam, boolean isLog) {

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

	public ResultModel getList(GprRolePageParameter.GetList getListParam) {
		return getList(getListParam, isLogRead);
	}

	public ResultModel getList(GprRolePageParameter.GetList getListParam, boolean isLog) {
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
			

			GprRolePageParameter.GetList.EntityRelated entityRelated = getListParam.getEntityRelated();
			GprRolePageParameter.GetList.Page page = getListParam.getPage();
			ArrayList<GprRolePageParameter.GetList.Order> orderList = getListParam.getOrderList();

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

			List<GprRolePage> list = baseUntDal.getList(map);
			PageInfo<GprRolePage> pageInfo = new PageInfo<GprRolePage>(list);
			List<GprRolePage> modelList = pageInfo.getList();

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
 
 	public ResultModel deleteByPageId(String pageId) {
		return deleteByPageId(pageId, isLogRead);
	}

	public ResultModel deleteByPageId(String pageId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(pageId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据外键，删除中间表数据。");

			int i = gprRolePageUntDal.deleteByPageId( pageId);

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

	public ResultModel getListByPageId(String pageId) {
		return getListByPageId(pageId, isLogRead);
	}

	public ResultModel getListByPageId(String pageId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(pageId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLISTBYFOREIGNKEY.getCode());
			result.setOperTypeText(OperType.GETLISTBYFOREIGNKEY.getText());
			result.setRemark("根据外键，查询中间表。");

			List<GprRolePage> modelList = gprRolePageUntDal.getListByPageId(pageId);

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
 
 	public ResultModel deleteByRoleId(String roleId) {
		return deleteByRoleId(roleId, isLogRead);
	}

	public ResultModel deleteByRoleId(String roleId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(roleId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据外键，删除中间表数据。");

			int i = gprRolePageUntDal.deleteByRoleId( roleId);

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

	public ResultModel getListByRoleId(String roleId) {
		return getListByRoleId(roleId, isLogRead);
	}

	public ResultModel getListByRoleId(String roleId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(roleId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLISTBYFOREIGNKEY.getCode());
			result.setOperTypeText(OperType.GETLISTBYFOREIGNKEY.getText());
			result.setRemark("根据外键，查询中间表。");

			List<GprRolePage> modelList = gprRolePageUntDal.getListByRoleId(roleId);

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


}





