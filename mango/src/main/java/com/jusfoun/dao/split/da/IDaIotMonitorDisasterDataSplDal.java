package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaIotMonitorDisasterData;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIotMonitorDisasterData>，可手动更改。da_iot_monitor_data表中满足预警条件的数据
 */

public interface IDaIotMonitorDisasterDataSplDal extends IBaseSplDal {

	public int add(DaIotMonitorDisasterData daIotMonitorDisasterData);

	public int addList(ArrayList<DaIotMonitorDisasterData> daIotMonitorDisasterDataList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIotMonitorDisasterData daIotMonitorDisasterData);

	public DaIotMonitorDisasterData getModel(String id);

	public List<DaIotMonitorDisasterData> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





