package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaCrawler;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaCrawler>，可手动更改。爬虫
 */

public interface IDaCrawlerSplDal extends IBaseSplDal {

	public int add(DaCrawler daCrawler);

	public int addList(ArrayList<DaCrawler> daCrawlerList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaCrawler daCrawler);

	public DaCrawler getModel(String id);

	public List<DaCrawler> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





