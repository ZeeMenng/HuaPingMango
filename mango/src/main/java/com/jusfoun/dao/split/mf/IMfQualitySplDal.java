package com.jusfoun.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.mf.MfQuality;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfQuality>，可手动更改。质量安全综合指数表,建模
 */

public interface IMfQualitySplDal extends IBaseSplDal {

	public int add(MfQuality mfQuality);

	public int addList(ArrayList<MfQuality> mfQualityList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfQuality mfQuality);

	public MfQuality getModel(String id);

	public List<MfQuality> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





