﻿package com.zee.bll.generate.unity.pi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zee.bll.generate.unity.base.BaseUntBll;
import com.zee.dao.unity.pi.IPiAdvertisingUntDal;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pi.PiAdvertising;
import com.zee.ent.parameter.pi.PiAdvertisingParameter;
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
 * @updateDate 2021/1/29 17:01:03
 * @description CMS广告表 业务逻辑处理类，扩展自BaseUntBll<PiAdvertising>，自动生成。
 */
public class PiAdvertisingGenUntBll extends BaseUntBll<PiAdvertising> {

	@Autowired
	protected IPiAdvertisingUntDal piAdvertisingUntDal;


	public ResultModel updateList(PiAdvertisingParameter.UpdateList updateListParam) {
		return updateList(updateListParam, isLogWrite);
	}

	public ResultModel updateList(PiAdvertisingParameter.UpdateList updateListParam, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(updateListParam);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
            result.setIncomeCount(updateListParam.getIdList().size());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATELIST.getCode());
			result.setOperTypeText(OperType.UPDATELIST.getText());
			result.setRemark("");

			int i = baseUntDal.updateList(updateListParam.getIdList(),updateListParam.getEntity());

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATELIST_S.getCode());
			result.setResultMessage(OperResult.UPDATELIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);

		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATE_F.getCode());
			result.setResultMessage(OperResult.UPDATE_F.getText() );
			result.setReturnValue(e.getMessage());
            result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
		}

		return result;
	}

	public ResultModel getList(PiAdvertisingParameter.GetList getListParam) {
		return getList(getListParam, isLogRead);
	}

	public ResultModel getList(PiAdvertisingParameter.GetList getListParam, boolean isLog) {
		ResultModel result = new ResultModel();

		try {

			JSONObject jsonObject = JSONObject.fromObject(getListParam);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
            result.setIncomeCount(0);
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLIST.getCode());
            result.setOperTypeText(OperType.GETLIST.getText());
			result.setRemark("");
			

			PiAdvertisingParameter.GetList.EntityRelated entityRelated = getListParam.getEntityRelated();
			PiAdvertisingParameter.GetList.Page page = getListParam.getPage();
			ArrayList<PiAdvertisingParameter.GetList.Order> orderList = getListParam.getOrderList();

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

			List<PiAdvertising> list = baseUntDal.getList(map);
			PageInfo<PiAdvertising> pageInfo = new PageInfo<PiAdvertising>(list);
			List<PiAdvertising> modelList = pageInfo.getList();

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(pageInfo.getTotal());
			result.setResultCode(OperResult.GETLIST_S.getCode());
			result.setResultMessage(OperResult.GETLIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETLIST_F.getCode());
			result.setResultMessage(OperResult.GETLIST_F.getText() );
			result.setReturnValue(e.getMessage());
            result.setOriginException(e);
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				addOperationLog(result);
		}
		return result;
	}
  

}




