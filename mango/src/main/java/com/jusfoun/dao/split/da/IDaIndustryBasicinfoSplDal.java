package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaIndustryBasicinfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIndustryBasicinfo>，可手动更改。产业基本情况表
 */

public interface IDaIndustryBasicinfoSplDal extends IBaseSplDal {

	public int add(DaIndustryBasicinfo daIndustryBasicinfo);

	public int addList(ArrayList<DaIndustryBasicinfo> daIndustryBasicinfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIndustryBasicinfo daIndustryBasicinfo);

	public DaIndustryBasicinfo getModel(String id);

	public List<DaIndustryBasicinfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





