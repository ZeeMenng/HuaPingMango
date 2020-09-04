﻿package com.jusfoun.bll.extend.split.gp;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.jusfoun.bll.generate.split.gp.GprRoleDomainGenSplBll;
import com.jusfoun.ent.custom.ResultModel;
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
 * @updateDate 2020/8/11 11:39:55
 * @description 角色拥有的功能模块权限。 业务逻辑处理类，扩展自BaseSplBll<GprRoleDomain>，可手动更改。
 */
@Service("gprRoleDomainSplBll")
public class GprRoleDomainSplBll extends GprRoleDomainGenSplBll {

	public ResultModel deleteByRoleId(String roleId) {
		return deleteByRoleId(roleId, isLogRead);
	}

	public ResultModel deleteByRoleId(String roleId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(roleId);
			result.setObjectId(roleId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据角色ID列表，删除角色应用领域中间表。");

			int i = gprRoleDomainSplDal.deleteByRoleId(roleId);

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


	public ResultModel deleteByRoleIdList(ArrayList<String> roleIdList) {
		return deleteByRoleIdList(roleIdList, isLogWrite);
	}

	public ResultModel deleteByRoleIdList(ArrayList<String> roleIdList, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(roleIdList).toString());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("根据角色ID列表，批量删除角色应用领域中间表。");

			int i = gprRoleDomainSplDal.deleteByRoleIdList(roleIdList);

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




