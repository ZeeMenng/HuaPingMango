package com.jusfoun.bll.extend.split.gp;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.jusfoun.bll.generate.split.gp.GprCatalogInterfaceGenSplBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GprCatalogInterface;
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
 * @updateDate 2020/10/21 21:21:11
 * @description 后台接口所属分类。 业务逻辑处理类，扩展自BaseSplBll<GprCatalogInterface>，可手动更改。
 */
@Service("gprCatalogInterfaceSplBll")
public class GprCatalogInterfaceSplBll extends GprCatalogInterfaceGenSplBll {

	public ResultModel deleteInvalidRecord(ArrayList<GprCatalogInterface> gprCatalogInterfaceList) {
		return deleteInvalidRecord(gprCatalogInterfaceList, isLogRead);
	}

	public ResultModel deleteInvalidRecord(ArrayList<GprCatalogInterface> gprCatalogInterfaceList, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONArray.fromObject(gprCatalogInterfaceList).toString());
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETELIST.getCode());
			result.setOperTypeText(OperType.DELETELIST.getText());
			result.setRemark("批量删除某分类方式下的某接口的分类信息");

			int i = gprCatalogInterfaceSplDal.deleteInvalidRecord(gprCatalogInterfaceList);

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
