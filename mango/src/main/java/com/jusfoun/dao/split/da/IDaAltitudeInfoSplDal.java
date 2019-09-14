package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaAltitudeInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaAltitudeInfo>，可手动更改。海拔数据表
 */

public interface IDaAltitudeInfoSplDal extends IBaseSplDal {

	public int add(DaAltitudeInfo daAltitudeInfo);

	public int addList(ArrayList<DaAltitudeInfo> daAltitudeInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaAltitudeInfo daAltitudeInfo);

	public DaAltitudeInfo getModel(String id);

	public List<DaAltitudeInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





