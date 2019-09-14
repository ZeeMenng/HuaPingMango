package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaSentimentArticle;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-7-25 10:44:14
 * @description 扩展自实体类IBaseUntDal<DaSentimentArticle>，可手动更改。舆情文章表
 */

public interface IDaSentimentArticleSplDal extends IBaseSplDal {

	public int add(DaSentimentArticle daSentimentArticle);

	public int addList(ArrayList<DaSentimentArticle> daSentimentArticleList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSentimentArticle daSentimentArticle);

	public DaSentimentArticle getModel(String id);

	public List<DaSentimentArticle> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





