package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaIndustryProcess;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIndustryProcess>，可手动更改。加工品产值数据表
 */

public interface IDaIndustryProcessSplDal extends IBaseSplDal {

	public int add(DaIndustryProcess daIndustryProcess);

	public int addList(ArrayList<DaIndustryProcess> daIndustryProcessList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIndustryProcess daIndustryProcess);

	public DaIndustryProcess getModel(String id);

	public List<DaIndustryProcess> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





