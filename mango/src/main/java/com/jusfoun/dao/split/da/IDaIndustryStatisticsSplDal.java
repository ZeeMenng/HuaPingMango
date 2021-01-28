package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaIndustryStatistics;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIndustryStatistics>，可手动更改。产业统计数据表
 */

public interface IDaIndustryStatisticsSplDal extends IBaseSplDal {

	public int add(DaIndustryStatistics daIndustryStatistics);

	public int addList(ArrayList<DaIndustryStatistics> daIndustryStatisticsList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIndustryStatistics daIndustryStatistics);

	public DaIndustryStatistics getModel(String id);

	public List<DaIndustryStatistics> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





