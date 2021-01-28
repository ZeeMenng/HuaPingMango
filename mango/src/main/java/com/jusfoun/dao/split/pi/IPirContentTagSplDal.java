package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PirContentTag;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PirContentTag>，可手动更改。CMS内容标签关联表
 */

public interface IPirContentTagSplDal extends IBaseSplDal {

	public int add(PirContentTag pirContentTag);

	public int addList(ArrayList<PirContentTag> pirContentTagList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirContentTag pirContentTag);

	public PirContentTag getModel(String id);

	public List<PirContentTag> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





