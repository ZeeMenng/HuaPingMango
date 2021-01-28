package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaGrowYield;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-26 18:49:08
 * @description 扩展自实体类IBaseUntDal<DaGrowYield>，可手动更改。种植数据表
 */

public interface IDaGrowYieldSplDal extends IBaseSplDal {

	public int add(DaGrowYield daGrowYield);

	public int addList(ArrayList<DaGrowYield> daGrowYieldList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaGrowYield daGrowYield);

	public DaGrowYield getModel(String id);

	public List<DaGrowYield> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





