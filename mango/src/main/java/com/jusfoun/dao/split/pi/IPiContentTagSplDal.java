package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiContentTag;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContentTag>，可手动更改。CMS内容TAG表
 */

public interface IPiContentTagSplDal extends IBaseSplDal {

	public int add(PiContentTag piContentTag);

	public int addList(ArrayList<PiContentTag> piContentTagList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContentTag piContentTag);

	public PiContentTag getModel(String id);

	public List<PiContentTag> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





