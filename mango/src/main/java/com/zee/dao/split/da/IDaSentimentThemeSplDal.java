package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaSentimentTheme;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaSentimentTheme>，可手动更改。舆情主题名称
 */

public interface IDaSentimentThemeSplDal extends IBaseSplDal {

	public int add(DaSentimentTheme daSentimentTheme);

	public int addList(ArrayList<DaSentimentTheme> daSentimentThemeList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSentimentTheme daSentimentTheme);

	public DaSentimentTheme getModel(String id);

	public List<DaSentimentTheme> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





