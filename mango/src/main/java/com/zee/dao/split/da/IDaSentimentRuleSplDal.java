package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaSentimentRule;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-7-22 16:33:32
 * @description 扩展自实体类IBaseUntDal<DaSentimentRule>，可手动更改。舆情采集规则表
 */

public interface IDaSentimentRuleSplDal extends IBaseSplDal {

	public int add(DaSentimentRule daSentimentRule);

	public int addList(ArrayList<DaSentimentRule> daSentimentRuleList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSentimentRule daSentimentRule);

	public DaSentimentRule getModel(String id);

	public List<DaSentimentRule> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





