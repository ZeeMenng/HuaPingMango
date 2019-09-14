package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaIotMonitorQueryDate;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIotMonitorQueryDate>，可手动更改。物联网数据最新查询时间表
 */

public interface IDaIotMonitorQueryDateSplDal extends IBaseSplDal {

	public int add(DaIotMonitorQueryDate daIotMonitorQueryDate);

	public int addList(ArrayList<DaIotMonitorQueryDate> daIotMonitorQueryDateList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIotMonitorQueryDate daIotMonitorQueryDate);

	public DaIotMonitorQueryDate getModel(String id);

	public List<DaIotMonitorQueryDate> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





