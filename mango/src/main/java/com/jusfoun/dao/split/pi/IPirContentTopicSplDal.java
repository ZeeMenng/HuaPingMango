package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PirContentTopic;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PirContentTopic>，可手动更改。CMS专题内容关联表
 */

public interface IPirContentTopicSplDal extends IBaseSplDal {

	public int add(PirContentTopic pirContentTopic);

	public int addList(ArrayList<PirContentTopic> pirContentTopicList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirContentTopic pirContentTopic);

	public PirContentTopic getModel(String id);

	public List<PirContentTopic> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





