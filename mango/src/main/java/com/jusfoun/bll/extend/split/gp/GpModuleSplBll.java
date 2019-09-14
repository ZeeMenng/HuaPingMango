package com.jusfoun.bll.extend.split.gp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jusfoun.bll.generate.split.gp.GpModuleGenSplBll;
import com.jusfoun.dao.split.gp.IGpMenuSplDal;
import com.jusfoun.dao.split.gp.IGprModulePageSplDal;
import com.jusfoun.dao.split.gp.IGprRoleModuleSplDal;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GpMenu;
import com.jusfoun.ent.extend.gp.GpModule;
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
 * @updateDate 2017/12/15 23:41:48
 * @description 功能模块。 业务逻辑处理类，扩展自BaseSplBll<GpModule>，可手动更改。
 */
@Service("gpModuleSplBll")
public class GpModuleSplBll extends GpModuleGenSplBll {

	@Autowired
	protected IGprModulePageSplDal gprModulePageSplDal;

	@Autowired
	protected IGprRoleModuleSplDal gprRoleModuleSplDal;

	@Autowired
	protected IGpMenuSplDal gpMenuSplDal;

	public ResultModel add(GpModule gpModule) {
		return add(gpModule, isLogWrite);
	}

	public ResultModel add(GpModule gpModule, boolean isLog) {

		ResultModel result = new ResultModel();

		try {
			JSONObject jsonObject = JSONObject.fromObject(gpModule);
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(jsonObject.toString());

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ADD.getCode());
			result.setOperTypeText(OperType.ADD.getText());
			result.setRemark("填加功能模块，同时添加对应菜单。");

			if (StringUtils.isEmpty(gpModule.getId()))
				gpModule.setId(Tools.getUUID());

			if (gpModule.getAddTime() == null)
				gpModule.setAddTime(DateUtils.getCurrentTime());
			if (gpModule.getUpdateTime() == null)
				gpModule.setUpdateTime(DateUtils.getCurrentTime());

			int i, j;

			i = gpModuleSplDal.add(gpModule);

			GpMenu gpMenu = new GpMenu();
			gpMenu.setId(Tools.getUUID());
			gpMenu.setAddTime(DateUtils.getCurrentTime());
			gpMenu.setDomainId(gpModule.getDomainId());
			gpMenu.setDomainName(gpModule.getDomainName());
			gpMenu.setModuleId(gpModule.getId());
			gpMenu.setModuleName(gpModule.getName());
			gpMenu.setName(gpModule.getName());
			gpMenu.setPriority(gpModule.getPriority());
			gpMenu.setUpdateTime(DateUtils.getCurrentTime());
			j = gpMenuSplDal.add(gpMenu);

			int addCounts = i + j;
			result.setReturnValue(String.valueOf(addCounts));
			result.setData(null);
			result.setTotalCount(new Long(addCounts));
			result.setResultCode(OperResult.ADD_S.getCode());
			result.setResultMessage(OperResult.ADD_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != 1) {
				result.setResultCode(OperResult.ADD_F.getCode());
				result.setResultMessage(OperResult.ADD_F.getText());
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			}
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
		}

		return result;
	}

	public ResultModel add(ArrayList<GpModule> gpModuleList) {
		return add(gpModuleList, isLogWrite);
	}

	public ResultModel add(ArrayList<GpModule> gpModuleList, boolean isLog) {

		ResultModel result = new ResultModel();

		try {

			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(gpModuleList).toString());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.ADDLIST.getCode());
			result.setOperTypeText(OperType.ADDLIST.getText());
			result.setRemark("批量填加功能模块，同时批量添加对应菜单。");

			ArrayList<GpMenu> gpMenuList = new ArrayList<GpMenu>();
			for (GpModule gpModule : gpModuleList) {
				if (StringUtils.isEmpty(gpModule.getId()))
					gpModule.setId(Tools.getUUID());
				if (gpModule.getAddTime() == null)
					gpModule.setAddTime(DateUtils.getCurrentTime());
				if (gpModule.getUpdateTime() == null)
					gpModule.setUpdateTime(DateUtils.getCurrentTime());

				GpMenu gpMenu = new GpMenu();
				gpMenu.setId(Tools.getUUID());
				gpMenu.setAddTime(DateUtils.getCurrentTime());
				gpMenu.setDomainId(gpModule.getDomainId());
				gpMenu.setDomainName(gpModule.getDomainName());
				gpMenu.setModuleId(gpModule.getId());
				gpMenu.setModuleName(gpModule.getName());
				gpMenu.setName(gpModule.getName());
				gpMenu.setPriority(gpModule.getPriority());
				gpMenu.setUpdateTime(DateUtils.getCurrentTime());
				gpMenuList.add(gpMenu);
			}

			int i, j;

			i = gpModuleSplDal.addList(gpModuleList);
			j = gpMenuSplDal.addList(gpMenuList);

			int addCounts = i + j;

			result.setReturnValue(String.valueOf(addCounts));
			result.setData(addCounts);
			result.setTotalCount(new Long(addCounts));
			result.setResultCode(OperResult.ADDLIST_S.getCode());
			result.setResultMessage(OperResult.ADDLIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != gpModuleList.size()) {
				result.setResultCode(OperResult.ADDLIST_F.getCode());
				result.setResultMessage(OperResult.ADDLIST_F.getText());
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			}
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
			result.setRemark("根据主键，删除功能模块记录，同时级联删除功能模块相关表的记录。");

			int i = gpModuleSplDal.delete(id);

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
			result.setRemark("根据应用领域主键，删除功能模块记录，同时级联删除功能模块相关表的记录。");

			int i = gpModuleSplDal.deleteByDomainId(domainId);

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
			result.setRemark("根据主键列表，删除功能模块记录，同时级联删除功能模块相关表的记录。");

			int i = gpModuleSplDal.deleteByIdList(idList);

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
			result.setRemark("根据应用领域主键列表，删除功能模块记录，同时级联删除功能模块相关表的记录。");

			int i = gpModuleSplDal.deleteByDomainIdList(domainIdList);

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

	public ResultModel getListByDomainId(String domainId) {
		return getListByDomainId(domainId, isLogRead);
	}

	public ResultModel getListByDomainId(String domainId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(domainId);
			result.setObjectId(domainId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据应用领域ID，查询相应功能模块。");

			List<GpModule> modelList = gpModuleSplDal.getListByDomainId(domainId);

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(modelList.size());
			result.setResultCode(OperResult.GETLIST_S.getCode());
			result.setResultMessage(OperResult.GETLIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (modelList.isEmpty()) {
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.GETLIST_F.getCode());
				result.setResultMessage(OperResult.GETLIST_F.getText() + "：不存在相应记录！");
			}
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

	
	public ResultModel getListByRoleId(String roleId) {
		return getListByRoleId(roleId, isLogRead);
	}

	public ResultModel getListByRoleId(String roleId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(roleId);
			result.setObjectId(roleId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("根据角色ID，查询相应功能模块。");

			List<GpModule> modelList = gpModuleSplDal.getListByRoleId(roleId);

			result.setReturnValue(JSONArray.fromObject(modelList).toString());
			result.setData(modelList);
			result.setTotalCount(modelList.size());
			result.setResultCode(OperResult.GETLIST_S.getCode());
			result.setResultMessage(OperResult.GETLIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (modelList.isEmpty()) {
				result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.GETLIST_F.getCode());
				result.setResultMessage(OperResult.GETLIST_F.getText() + "：不存在相应记录！");
			}
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

	
	
	
	
	
	public ResultModel updateDomainModules(ArrayList<GpModule> gpModuleList) {
		return updateDomainModules(gpModuleList, isLogWrite);
	}

	public ResultModel updateDomainModules(ArrayList<GpModule> gpModuleList, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setId(Tools.getUUID());
			result.setAddTime(DateUtils.getCurrentTime());
			result.setIncomeValue(JSONArray.fromObject(gpModuleList).toString());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.UPDATELIST.getCode());
			result.setOperTypeText(OperType.UPDATELIST.getText());
			result.setRemark("修改某个应用领域下的所有功能模块。");

			int i = 0;
			if (gpModuleList.size() > 0) {
				gpModuleSplDal.deleteInvalidDomainModules(gpModuleList.get(0).getDomainId(), gpModuleList);
				i = gpModuleSplDal.updateDomainModules(gpModuleList);
			}
			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.UPDATELIST_S.getCode());
			result.setResultMessage(OperResult.UPDATELIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);

		} catch (Exception e) {
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.UPDATELIST_F.getCode());
			result.setResultMessage(OperResult.UPDATELIST_F.getText() + "：" + e.getMessage());
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
