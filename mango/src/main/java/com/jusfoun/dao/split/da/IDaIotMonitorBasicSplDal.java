package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaIotMonitorBasic;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIotMonitorBasic>，可手动更改。物联网设备基本信息
 */

public interface IDaIotMonitorBasicSplDal extends IBaseSplDal {

	public int add(DaIotMonitorBasic daIotMonitorBasic);

	public int addList(ArrayList<DaIotMonitorBasic> daIotMonitorBasicList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIotMonitorBasic daIotMonitorBasic);

	public DaIotMonitorBasic getModel(String id);

	public List<DaIotMonitorBasic> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





