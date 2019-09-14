package com.jusfoun.bll.extend.split.gp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jusfoun.bll.generate.split.gp.GpDomainGenSplBll;
import com.jusfoun.dao.split.gp.IGpControlSplDal;
import com.jusfoun.dao.split.gp.IGpInterfaceSplDal;
import com.jusfoun.dao.split.gp.IGpMenuSplDal;
import com.jusfoun.dao.split.gp.IGpModuleSplDal;
import com.jusfoun.dao.split.gp.IGpPageSplDal;
import com.jusfoun.dao.split.gp.IGpRoleSplDal;
import com.jusfoun.dao.split.gp.IGprDomainMessageSplDal;
import com.jusfoun.dao.split.gp.IGprDomainUserSplDal;
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
 * @updateDate 2017/12/15 23:41:48
 * @description 应用领域。 业务逻辑处理类，扩展自BaseSplBll<GpDomain>，可手动更改。
 */
@Service("gpDomainSplBll")
public class GpDomainSplBll extends GpDomainGenSplBll {

	@Autowired
	protected IGprDomainUserSplDal gprDomainUserSplDal;
	@Autowired
	protected IGprDomainMessageSplDal gprDomainMessageSplDal;
	@Autowired
	protected IGpModuleSplDal gpModuleSplDal;
	@Autowired
	protected IGpMenuSplDal gpMenuSplDal;
	@Autowired
	protected IGpPageSplDal gpPageSplDal;
	@Autowired
	protected IGpControlSplDal gpControlSplDal;
	@Autowired
	protected IGpInterfaceSplDal gpInterfaceSplDal;
	@Autowired
	protected IGpRoleSplDal gpRoleSplDal;

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
			result.setRemark("根据主键删除应用领域记录，同时级联删除其相关表的记录。");
			int i, j, k, l, m;

			i = gpRoleSplDal.deleteByDomainId(id);
			j = gpModuleSplDal.deleteByDomainId(id);
			k = gpPageSplDal.deleteByDomainId(id);
			l = gpInterfaceSplDal.deleteByDomainId(id);
			m = gpDomainSplDal.delete(id);

			int deleteCounts = i + j + k + l + m;

			result.setReturnValue(String.valueOf(deleteCounts));
			result.setData(deleteCounts);
			result.setTotalCount(new Long(deleteCounts));
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
			result.setRemark("根据主键列表删除应用领域记录，同时级联删除其相关表的记录。");
			int i, j, k, l, m;
			i = gpRoleSplDal.deleteByDomainIdList(idList);
			j = gpModuleSplDal.deleteByDomainIdList(idList);
			k = gpPageSplDal.deleteByDomainIdList(idList);
			l = gpInterfaceSplDal.deleteByDomainIdList(idList);

			m = gpDomainSplDal.deleteByIdList(idList);

			int deleteCounts = i + j + k + l + m;

			result.setReturnValue(String.valueOf(deleteCounts));
			result.setData(deleteCounts);
			result.setTotalCount(new Long(deleteCounts));
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
