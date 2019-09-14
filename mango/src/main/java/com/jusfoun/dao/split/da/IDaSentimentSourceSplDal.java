package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaSentimentSource;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-7-19 11:37:09
 * @description 扩展自实体类IBaseUntDal<DaSentimentSource>，可手动更改。舆情数据源表
 */

public interface IDaSentimentSourceSplDal extends IBaseSplDal {

	public int add(DaSentimentSource daSentimentSource);

	public int addList(ArrayList<DaSentimentSource> daSentimentSourceList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSentimentSource daSentimentSource);

	public DaSentimentSource getModel(String id);

	public List<DaSentimentSource> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





