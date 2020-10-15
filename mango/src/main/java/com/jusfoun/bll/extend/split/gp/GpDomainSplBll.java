package com.jusfoun.bll.extend.split.gp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jusfoun.bll.extend.unity.gp.GprRoleDomainUntBll;
import com.jusfoun.bll.generate.split.gp.GpDomainGenSplBll;
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
	protected GprDomainUserSplBll gprDomainUserSplBll;
	@Autowired
	protected GprDomainMessageSplBll gprDomainMessageSplBll;
	@Autowired
	protected GprRoleDomainUntBll gprRoleDomainUntBll;
	@Autowired
	protected GpModuleSplBll gpModuleSplBll;
	@Autowired
	protected GpMenuSplBll gpMenuSplBll;
	@Autowired
	protected GpPageSplBll gpPageSplBll;
	@Autowired
	protected GpControlSplBll gpControlSplBll;
	@Autowired
	protected GpInterfaceSplBll gpInterfaceSplBll;
	@Autowired
	protected GpRoleSplBll gpRoleSplBll;
	@Autowired
	protected GpResourceSplBll gpResourceSplBll;

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

			gprDomainUserSplBll.deleteByDomainId(id);
			gprDomainMessageSplBll.deleteByDomainId(id);
			gprRoleDomainUntBll.deleteByDomainId(id);
			gpControlSplBll.deleteByDomainId(id);
			gpModuleSplBll.deleteByDomainId(id);
			gpPageSplBll.deleteByDomainId(id);
			gpInterfaceSplBll.deleteByDomainId(id);
			gpResourceSplBll.deleteByDomainId(id);

			int deleteCounts = gpDomainSplDal.delete(id);

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

			gprDomainUserSplBll.deleteByDomainIdList(idList);
			gprDomainMessageSplBll.deleteByDomainIdList(idList);
			gprRoleDomainUntBll.deleteByDomainIdList(idList);

			gpModuleSplBll.deleteByDomainIdList(idList);
			gpPageSplBll.deleteByDomainIdList(idList);
			gpControlSplBll.deleteByDomainIdList(idList);
			gpInterfaceSplBll.deleteByDomainIdList(idList);
			gpResourceSplBll.deleteByDomainIdList(idList);

			int deleteCounts = gpDomainSplDal.deleteByIdList(idList);

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
