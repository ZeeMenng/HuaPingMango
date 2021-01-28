package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaGrowCost;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaGrowCost>，可手动更改。种植成本
 */

public interface IDaGrowCostSplDal extends IBaseSplDal {

	public int add(DaGrowCost daGrowCost);

	public int addList(ArrayList<DaGrowCost> daGrowCostList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaGrowCost daGrowCost);

	public DaGrowCost getModel(String id);

	public List<DaGrowCost> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





