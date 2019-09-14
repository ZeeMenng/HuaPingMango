package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaSentimentContent;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaSentimentContent>，可手动更改。舆情文章内容表
 */

public interface IDaSentimentContentSplDal extends IBaseSplDal {

	public int add(DaSentimentContent daSentimentContent);

	public int addList(ArrayList<DaSentimentContent> daSentimentContentList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSentimentContent daSentimentContent);

	public DaSentimentContent getModel(String id);

	public List<DaSentimentContent> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





