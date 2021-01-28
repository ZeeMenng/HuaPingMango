package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaIndustryFresh;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIndustryFresh>，可手动更改。鲜果产值数据表
 */

public interface IDaIndustryFreshSplDal extends IBaseSplDal {

	public int add(DaIndustryFresh daIndustryFresh);

	public int addList(ArrayList<DaIndustryFresh> daIndustryFreshList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIndustryFresh daIndustryFresh);

	public DaIndustryFresh getModel(String id);

	public List<DaIndustryFresh> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





