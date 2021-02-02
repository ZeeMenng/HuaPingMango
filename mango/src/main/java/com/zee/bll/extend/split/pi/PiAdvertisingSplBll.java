package com.zee.bll.extend.split.pi;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.pi.PiAdvertisingGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pi.PiAdvertising;
import com.zee.set.enumer.OperResult;
import com.zee.set.enumer.OperType;
import com.zee.set.exception.GlobalException;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.Tools;

import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2018/7/12 14:46:55
 * @description CMS广告表 业务逻辑处理类，扩展自BaseSplBll<PiAdvertising>，可手动更改。
 */
@Service("piAdvertisingSplBll")
public class PiAdvertisingSplBll extends PiAdvertisingGenSplBll {

	public ResultModel getModelBySpace(String domainId, Byte spaceCode) {
		return getModelBySpace(domainId, spaceCode, isLogRead);
	}

	public ResultModel getModelBySpace(String domainId, Byte spaceCode, boolean isLog) {
		ResultModel result = new ResultModel();
		HashMap<String, Object> incomeValue = new HashMap<String, Object>();
		incomeValue.put("domainId", domainId);
		incomeValue.put("spaceCode", spaceCode);

		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(JSONObject.fromObject(incomeValue).toString());

			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.GETMODEL.getCode());
			result.setOperTypeText(OperType.GETMODEL.getText());
			result.setRemark("");

			PiAdvertising piAdvertising = piAdvertisingSplDal.getModelBySpace(domainId, spaceCode);

			if (piAdvertising == null) {
				result.setResultCode(OperResult.GETMODEL_S.getCode());
				result.setResultMessage(OperResult.GETMODEL_S.getText() + "：不存在相应记录！");
				result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			} else {
				result.setObjectId(piAdvertising.getId());
				result.setData(piAdvertising);
				result.setTotalCount(new Long(1));
				result.setResultCode(OperResult.GETMODEL_S.getCode());
				result.setResultMessage(OperResult.GETMODEL_S.getText());
				result.setReturnValue(JSONObject.fromObject(piAdvertising).toString());
				result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			}
		} catch (Exception e) {

			result.setReturnValue(e.getMessage());
			GlobalException globalException = new GlobalException();
			globalException.setResultModel(result);

			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
			result.setResultCode(OperResult.GETMODEL_F.getCode());
			result.setResultMessage(OperResult.GETMODEL_F.getText() + "：" + e.getMessage());
			throw globalException;

		} finally {
			if (isLog)
				operationLogDal.add(result);
		}
		return result;
	}

}
