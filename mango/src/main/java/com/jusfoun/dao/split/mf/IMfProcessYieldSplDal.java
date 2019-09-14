package com.jusfoun.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.mf.MfProcessYield;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfProcessYield>，可手动更改。加工品产量建模表
 */

public interface IMfProcessYieldSplDal extends IBaseSplDal {

	public int add(MfProcessYield mfProcessYield);

	public int addList(ArrayList<MfProcessYield> mfProcessYieldList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfProcessYield mfProcessYield);

	public MfProcessYield getModel(String id);

	public List<MfProcessYield> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





