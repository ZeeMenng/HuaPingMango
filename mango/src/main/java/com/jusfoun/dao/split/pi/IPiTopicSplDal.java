package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiTopic;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiTopic>，可手动更改。CMS专题表
 */

public interface IPiTopicSplDal extends IBaseSplDal {

	public int add(PiTopic piTopic);

	public int addList(ArrayList<PiTopic> piTopicList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiTopic piTopic);

	public PiTopic getModel(String id);

	public List<PiTopic> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





