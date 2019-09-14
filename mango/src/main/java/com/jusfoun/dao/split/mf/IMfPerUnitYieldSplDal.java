package com.jusfoun.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.mf.MfPerUnitYield;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfPerUnitYield>，可手动更改。单产预测建模表
 */

public interface IMfPerUnitYieldSplDal extends IBaseSplDal {

	public int add(MfPerUnitYield mfPerUnitYield);

	public int addList(ArrayList<MfPerUnitYield> mfPerUnitYieldList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfPerUnitYield mfPerUnitYield);

	public MfPerUnitYield getModel(String id);

	public List<MfPerUnitYield> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





