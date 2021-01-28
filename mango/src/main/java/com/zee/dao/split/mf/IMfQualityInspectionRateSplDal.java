package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfQualityInspectionRate;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfQualityInspectionRate>，可手动更改。质量检测表
 */

public interface IMfQualityInspectionRateSplDal extends IBaseSplDal {

	public int add(MfQualityInspectionRate mfQualityInspectionRate);

	public int addList(ArrayList<MfQualityInspectionRate> mfQualityInspectionRateList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfQualityInspectionRate mfQualityInspectionRate);

	public MfQualityInspectionRate getModel(String id);

	public List<MfQualityInspectionRate> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





