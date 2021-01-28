package com.zee.bll.extend.split.pi;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.pi.PirProductResourceGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.set.enumer.OperResult;
import com.zee.set.enumer.OperType;
import com.zee.set.exception.GlobalException;
import com.zee.utl.DateUtils;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.Tools;


/**
 * @author Zee
 * @createDate 2017/05/22 14:39:59
 * @updateDate 2018/7/11 17:38:33
 * @description 产品和资源中间表 业务逻辑处理类，扩展自BaseSplBll<PirProductResource>，可手动更改。
 */
@Service("pirProductResourceSplBll")
public class PirProductResourceSplBll extends PirProductResourceGenSplBll {


	public ResultModel deleteTitleImageByContentId(String productId) {
		return deleteTitleImageByContentId(productId, isLogRead);
	}

	public ResultModel deleteTitleImageByContentId(String productId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(productId);
			result.setObjectId(productId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("");

			int i = pirProductResourceSplDal.deleteByProductId(productId);

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


}





