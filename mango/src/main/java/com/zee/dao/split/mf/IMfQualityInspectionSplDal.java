package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfQualityInspection;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfQualityInspection>，可手动更改。质量抽检表
 */

public interface IMfQualityInspectionSplDal extends IBaseSplDal {

	public int add(MfQualityInspection mfQualityInspection);

	public int addList(ArrayList<MfQualityInspection> mfQualityInspectionList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfQualityInspection mfQualityInspection);

	public MfQualityInspection getModel(String id);

	public List<MfQualityInspection> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





