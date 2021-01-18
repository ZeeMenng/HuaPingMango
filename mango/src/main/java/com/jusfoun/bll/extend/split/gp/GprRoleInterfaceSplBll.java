package com.jusfoun.bll.extend.split.gp;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import com.jusfoun.bll.generate.split.gp.GprRoleInterfaceGenSplBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GprRoleInterface;
import com.jusfoun.set.enumer.OperResult;
import com.jusfoun.set.enumer.OperType;
import com.jusfoun.set.exception.GlobalException;
import com.jusfoun.utl.ClassUtil;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SnowFlakeSerialNoWorkerUtl;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2017/12/15 23:41:48
 * @description 角色拥有的接口权限。 业务逻辑处理类，扩展自BaseSplBll<GprRoleInterface>，可手动更改。
 */
@Service("gprRoleInterfaceSplBll")
public class GprRoleInterfaceSplBll extends GprRoleInterfaceGenSplBll {


	public ResultModel deleteByCompositeIdList(ArrayList<GprRoleInterface> gprRoleInterfaceList) {
		return deleteByCompositeIdList(gprRoleInterfaceList, isLogWrite);
	}

	public ResultModel deleteByCompositeIdList(ArrayList<GprRoleInterface> gprRoleInterfaceList, boolean isLog) {
		ResultModel result = new ResultModel();
		if (gprRoleInterfaceList == null || gprRoleInterfaceList.isEmpty()) {
			result.setResultCode(OperResult.ADDLIST_F.getCode());
			result.setResultMessage("传入的为空数组！");
			return result;
		}

		try {
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(gprRoleInterfaceList).toString());
			result.setAddTime(DateUtils.getCurrentTime());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("");

			int i = gprRoleInterfaceSplDal.deleteByCompositeIdList(gprRoleInterfaceList);

			result.setReturnValue(String.valueOf(i));
			result.setData(null);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETELIST_S.getCode());
			result.setResultMessage(OperResult.DELETELIST_S.getText());
			result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
			if (i != gprRoleInterfaceList.size()) {
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





