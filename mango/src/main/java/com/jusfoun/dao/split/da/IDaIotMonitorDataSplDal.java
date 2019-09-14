package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaIotMonitorData;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIotMonitorData>，可手动更改。物联网设备监控数据
 */

public interface IDaIotMonitorDataSplDal extends IBaseSplDal {

	public int add(DaIotMonitorData daIotMonitorData);

	public int addList(ArrayList<DaIotMonitorData> daIotMonitorDataList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIotMonitorData daIotMonitorData);

	public DaIotMonitorData getModel(String id);

	public List<DaIotMonitorData> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





