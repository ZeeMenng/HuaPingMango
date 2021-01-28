package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaTarget;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaTarget>，可手动更改。指标数据
 */

public interface IDaTargetSplDal extends IBaseSplDal {

	public int add(DaTarget daTarget);

	public int addList(ArrayList<DaTarget> daTargetList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaTarget daTarget);

	public DaTarget getModel(String id);

	public List<DaTarget> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





