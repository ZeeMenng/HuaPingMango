package com.jusfoun.bll.generate.unity.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jusfoun.bll.base.BaseBll;
import com.jusfoun.dao.unity.base.IBaseUntDal;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.set.enumer.OperResult;
import com.jusfoun.set.enumer.OperType;
import com.jusfoun.set.exception.GlobalException;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.PageHelperUtil;
import com.jusfoun.utl.SnowFlakeSerialNoWorkerUtl;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseUntBll<T extends Serializable> extends BaseBll {

	@Autowired
	protected IBaseUntDal<T> baseUntDal;

	// @Autowired
	// private RedisUtil redisUtil;

	public ResultModel add(T t) {
		return add(t, isLogWrite);
	}

	public ResultModel add(T t, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(t);
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ADD.getCode());
			result.setOperTypeText(OperType.ADD.getText());
			result.setRemark("");

			//给主键统一赋值
			Class<?> cla = t.getClass().getSuperclass();
			String tId = Tools.getUUID();
			Field idField = cla.getDeclaredField(SymbolicConstant.TABLE_ID);
			idField.setAccessible(true);
			Object idObject = idField.get(t);
			if (idObject == null || StringUtils.isEmpty(idObject.toString())) {
				idField.set(t, tId);
				result.setObjectId(tId);
			} else {
				result.setObjectId(idObject.toString());
			}
			
			//给编号统一赋值
			String tSerialNo = String.valueOf(new SnowFlakeSerialNoWorkerUtl(SymbolicConstant.SNOWFLAKE_SERIAL_NO_DATACENTER_ID, SymbolicConstant.SNOWFLAKE_SERIAL_NO_WORKDER_ID).nextId());
			Field serialNoField = cla.getDeclaredField(SymbolicConstant.TABLE_SERIAL_NO);
			serialNoField.setAccessible(true);
			Object serialNoObject = serialNoField.get(t);
			if (serialNoObject == null || StringUtils.isEmpty(serialNoObject.toString())) 
				serialNoField.set(t, tSerialNo);
		
			
			
			// 统一新增时间begin
			try {
				Field addTimeField = cla.getDeclaredField(SymbolicConstant.ADD_TIME);
				Field updateTimeField = cla.getDeclaredField(SymbolicConstant.UPDATE_TIME);
				addTimeField.setAccessible(true);
				updateTimeField.setAccessible(true);
				addTimeField.set(t, DateUtils.getCurrentTime());
				updateTimeField.set(t, DateUtils.getCurrentTime());
			} catch (NoSuchFieldException e) {
				// 不是所有类都会有新增时间字段，捕获异常继续执行
				logger.error(e.getMessage());
			}
			// 统一新增时间end

			int i = baseUntDal.add(t);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.ADD_S.getCode());
			result.setResultMessage(OperResult.ADD_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != 1) {
				result.setResultCode(OperResult.ADD_F.getCode());
				result.setResultMessage(OperResult.ADD_F.getText());
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			}
		} catch (NoSuchFieldException e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADD_F.getCode());
			result.setResultMessage(OperResult.ADD_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (IllegalAccessException e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADD_F.getCode());
			result.setResultMessage(OperResult.ADD_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (Exception e) {
			e.printStackTrace();
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADD_F.getCode());
			result.setResultMessage(OperResult.ADD_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				operationLogDal.add(result);
			insertRedis(result);
		}

		return result;
	}

	public ResultModel add(ArrayList<T> tList) {
		return add(tList, isLogWrite);
	}

	public ResultModel add(ArrayList<T> tList, boolean isLog) {
		ResultModel result = new ResultModel();
		if (tList == null || tList.isEmpty())
		{
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}
		

		try {

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(tList).toString());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ADDLIST.getCode());
			result.setOperTypeText(OperType.ADDLIST.getText());
			result.setRemark("");

			for (T t : tList) {
				Class<?> cla = t.getClass().getSuperclass();
				
				//给主键统一赋值
				String tId = Tools.getUUID();
				Field idField = cla.getDeclaredField(SymbolicConstant.TABLE_ID);
				idField.setAccessible(true);
				Object idObject = idField.get(t);
				if (idObject == null || StringUtils.isEmpty(idObject.toString()))
					idField.set(t, tId);
				
				//给编号统一赋值
				String tSerialNo = String.valueOf(new SnowFlakeSerialNoWorkerUtl(SymbolicConstant.SNOWFLAKE_SERIAL_NO_DATACENTER_ID, SymbolicConstant.SNOWFLAKE_SERIAL_NO_WORKDER_ID).nextId());
				Field serialNoField = cla.getDeclaredField(SymbolicConstant.TABLE_SERIAL_NO);
				serialNoField.setAccessible(true);
				Object serialNoObject = serialNoField.get(t);
				if (serialNoObject == null || StringUtils.isEmpty(serialNoObject.toString())) 
					serialNoField.set(t, tSerialNo);
				
				// 统一新增时间begin
				try {
					Field addTimeField = cla.getDeclaredField(SymbolicConstant.ADD_TIME);
					Field updateTimeField = cla.getDeclaredField(SymbolicConstant.UPDATE_TIME);
					addTimeField.setAccessible(true);
					updateTimeField.setAccessible(true);
					addTimeField.set(t, DateUtils.getCurrentTime());
					updateTimeField.set(t, DateUtils.getCurrentTime());
				} catch (NoSuchFieldException e) {
					// 不是所有类都会有新增时间字段，捕获异常继续执行
					logger.error(e.getMessage());
				}
				// 统一新增时间end

			}
			int i = baseUntDal.addList(tList);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.ADDLIST_S.getCode());
			result.setResultMessage(OperResult.ADDLIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != tList.size()) {
				result.setResultCode(OperResult.ADDLIST_F.getCode());
				result.setResultMessage(OperResult.ADDLIST_F.getText());
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			}
		} catch (NoSuchFieldException e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage(OperResult.ADDLIST_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (IllegalAccessException e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage(OperResult.ADDLIST_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage(OperResult.ADDLIST_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				operationLogDal.add(result);
			insertRedis(result);
		}

		return result;
	}

	public ResultModel updateListWithDff(ArrayList<T> tList) {
		return updateListWithDff(tList, isLogWrite);
	}

	public ResultModel updateListWithDff(ArrayList<T> tList, boolean isLog) {

		ResultModel result = new ResultModel();
		if (tList == null || tList.isEmpty())
		{
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}
		

		try {

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(tList).toString());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATELISTWIDTHDFF.getCode());
			result.setOperTypeText(OperType.UPDATELISTWIDTHDFF.getText());
			result.setRemark("");

			for (T t : tList) {
				Class<?> cla = t.getClass().getSuperclass();
				String tId = Tools.getUUID();
				Field field = cla.getDeclaredField(SymbolicConstant.TABLE_ID);
				field.setAccessible(true);
				Object idObject = field.get(t);
				if (idObject == null || StringUtils.isEmpty(idObject.toString())) {
					GlobalException globalException = new GlobalException("主键不能为空！");
					globalException.setResultModel(result);
					throw globalException;
				}

				// 统一修改时间begin
				try {
					Field timeField = cla.getDeclaredField(SymbolicConstant.UPDATE_TIME);
					timeField.setAccessible(true);
					timeField.set(t, DateUtils.getCurrentTime());
				} catch (NoSuchFieldException e) {
					// 不是所有类都会有修改时间字段，捕获异常继续执行
					logger.error(e.getMessage());
				}

			}

			int i = baseUntDal.updateListWithDff(tList);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATELISTWIDTHDFF_S.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFF_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != tList.size()) {
				result.setResultCode(OperResult.UPDATELISTWIDTHDFF_S.getCode());
				result.setResultMessage(OperResult.UPDATELISTWIDTHDFF_S.getText());
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			}
		} catch (NoSuchFieldException e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFF_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFF_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (IllegalAccessException e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFF_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFF_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFF_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFF_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				operationLogDal.add(result);
			insertRedis(result);
		}

		return result;
	}

	public ResultModel updateListWithDffOrAdd(ArrayList<T> tList) {
		return updateListWithDffOrAdd(tList, isLogWrite);
	}

	public ResultModel updateListWithDffOrAdd(ArrayList<T> tList, boolean isLog) {

		ResultModel result = new ResultModel();
		if (tList == null || tList.isEmpty())
		{
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}
		
		try {

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(tList).toString());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATELISTWIDTHDFFORADD.getCode());
			result.setOperTypeText(OperType.UPDATELISTWIDTHDFFORADD.getText());
			result.setRemark("");

			for (T t : tList) {
				Class<?> cla = t.getClass().getSuperclass();
				String tId = Tools.getUUID();
				Field field = cla.getDeclaredField(SymbolicConstant.TABLE_ID);
				field.setAccessible(true);
				Object idObject = field.get(t);
				if (idObject == null || StringUtils.isEmpty(idObject.toString()))
					field.set(t, tId);

				// 统一修改时间begin
				try {
					Field timeField = cla.getDeclaredField(SymbolicConstant.UPDATE_TIME);
					timeField.setAccessible(true);
					timeField.set(t, DateUtils.getCurrentTime());
				} catch (NoSuchFieldException e) {
					// 不是所有类都会有修改时间字段，捕获异常继续执行
					logger.error(e.getMessage());
				}

			}

			int i = baseUntDal.updateListWithDffOrAdd(tList);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATELISTWIDTHDFFORADD_S.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFFORADD_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != tList.size()) {
				result.setResultCode(OperResult.UPDATELISTWIDTHDFFORADD_S.getCode());
				result.setResultMessage(OperResult.UPDATELISTWIDTHDFFORADD_S.getText());
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			}
		} catch (NoSuchFieldException e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFFORADD_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFFORADD_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (IllegalAccessException e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFFORADD_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFFORADD_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELISTWIDTHDFFORADD_F.getCode());
			result.setResultMessage(OperResult.UPDATELISTWIDTHDFFORADD_F.getText() + "：" + e.getMessage());
			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);
			throw globalException;
		} finally {
			if (isLog)
				operationLogDal.add(result);
			insertRedis(result);
		}

		return result;
	}

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
			result.setRemark("");

			int i = baseUntDal.delete(id);

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
			insertRedis(result);
		}

		return result;
	}

	public ResultModel deleteByIdList(ArrayList<String> idList) {
		return deleteByIdList(idList, isLogWrite);
	}

	public ResultModel deleteByIdList(ArrayList<String> idList, boolean isLog) {
		ResultModel result = new ResultModel();
		if (idList == null || idList.isEmpty())
		{
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}
		
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(idList).toString());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("");

			int i = baseUntDal.deleteByIdList(idList);

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
			insertRedis(result);
		}

		return result;
	}

	public ResultModel update(T t) {
		return update(t, isLogWrite);
	}

	public ResultModel update(T t, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(t);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
			if (jsonObject.containsKey("id"))
				result.setObjectId(jsonObject.getString("id"));
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATE.getCode());
			result.setOperTypeText(OperType.UPDATE.getText());
			result.setRemark("");

			// 统一增加修改时间begin
			try {
				Class<?> cla = t.getClass().getSuperclass();
				Field timeField = cla.getDeclaredField(SymbolicConstant.UPDATE_TIME);
				timeField.setAccessible(true);
				timeField.set(t, DateUtils.getCurrentTime());
			} catch (NoSuchFieldException e) {
				// 不是所有类都会有新增时间字段，捕获异常继续执行
				logger.error(e.getMessage());
			}
			// 统一增加修改时间end

			int i = baseUntDal.update(t);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATE_S.getCode());
			result.setResultMessage(OperResult.UPDATE_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != 1) {
				result.setResultCode(OperResult.UPDATE_F.getCode());
				result.setResultMessage(OperResult.UPDATE_F.getText() + "：不存在相应记录！");
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
			insertRedis(result);
		}

		return result;
	}

	public ResultModel getModel(String id) {
		return getModel(id, isLogRead);
	}

	public ResultModel getModel(String id, boolean isLog) {
		ResultModel result = new ResultModel();

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(id);
			result.setObjectId(id);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			T t = baseUntDal.getModel(id);

			result.setData(t);
			result.setTotalCount(new Long(1));
			result.setResultCode(OperResult.GETMODEL_S.getCode());
			result.setResultMessage(OperResult.GETMODEL_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (t == null) {
				result.setResultCode(OperResult.GETMODEL_S.getCode());
				result.setResultMessage(OperResult.GETMODEL_S.getText() + "：不存在相应记录！");
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			}
			result.setReturnValue(JSONObject.fromObject(t).toString());
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);

			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODEL_F.getCode());
			result.setResultMessage(OperResult.GETMODEL_F.getText() + "：" + e.getMessage());
			throw globalException;

		} finally {
			if (isLog)
				operationLogDal.add(result);
			insertRedis(result);
		}
		return result;
	}

	public ResultModel getListBySQL(Map<String, Object> map) {
		return getListBySQL(map, isLogRead);
	}

	public ResultModel getListBySQL(Map<String, Object> map, boolean isLog) {
		ResultModel result = new ResultModel();

		try {

			JSONObject jsonObject = JSONObject.fromObject(map);

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());
			result.setObjectId("");
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETLISTBYSQL.getCode());
			result.setOperTypeText(OperType.GETLISTBYSQL.getText());
			result.setRemark("");

			String sql = (String) map.get("Sql");
			PageHelper.startPage(PageHelperUtil.getPageNum(map), PageHelperUtil.getPageSize(map));

			List<Map<String, Object>> list = baseUntDal.getListBySQL(sql);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
			List<Map<String, Object>> modelList = pageInfo.getList();

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(pageInfo.getTotal());
			result.setResultCode(OperResult.GETLISTBYSQL_S.getCode());
			result.setResultMessage(OperResult.GETLISTBYSQL_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (modelList.isEmpty()) {
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
			insertRedis(result);
		}
		return result;
	}

	private void insertRedis(ResultModel result) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Field[] fields = result.getClass().getSuperclass().getSuperclass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(result));
			}
			String gp_oper_log_id = "gp_oper_log:id:" + result.getId();
			// 暂时注掉，以后设计日志插入redis
			// redisUtil.lSet("gp_oper_log:add_time:"+
			// DateUtils.getCurrentDateStr(), gp_oper_log_id);
			// redisUtil.hmset(gp_oper_log_id, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
