package com.zee.bll.extend.split.pi;

import org.springframework.stereotype.Service;

import com.zee.bll.generate.split.pi.PirContentImageGenSplBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpUser;
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
 * @updateDate 2018/5/7 15:00:36
 * @description CMS文章内容所包含图片信息表 业务逻辑处理类，扩展自BaseSplBll<PirContentImage>，可手动更改。
 */
@Service("pirContentImageSplBll")
public class PirContentImageSplBll extends PirContentImageGenSplBll {


	public ResultModel deleteTitleImageByContentId(String contentId) {
		return deleteTitleImageByContentId(contentId, isLogRead);
	}

	public ResultModel deleteTitleImageByContentId(String contentId, boolean isLog) {
		ResultModel result = new ResultModel();
		try {
			result.setAddTime(DateUtils.getCurrentTime());
			result.setId(Tools.getUUID());
			result.setIncomeValue(contentId);
			result.setObjectId(contentId);
			result.setTableName(this.getClass().getSimpleName());
			result.setOperTypeCode(OperType.DELETE.getCode());
			result.setOperTypeText(OperType.DELETE.getText());
			result.setRemark("");

			int i = pirContentImageSplDal.deleteTitleImageByContentId(contentId);

			result.setReturnValue(String.valueOf(i));
			result.setData(i);
			result.setTotalCount(new Long(i));
			result.setResultCode(OperResult.DELETE_S.getCode());
			result.setResultMessage(OperResult.DELETE_S.getText());
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			if (i != 1) {
				result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
				result.setResultCode(OperResult.DELETE_F.getCode());
				result.setResultMessage(OperResult.DELETE_F.getText() + "：不存在相应记录！");
			}
		} catch (Exception e) {
			result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_F);
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





