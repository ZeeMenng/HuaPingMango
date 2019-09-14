package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiContent;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContent>，可手动更改。CMS内容表
 */

public interface IPiContentSplDal extends IBaseSplDal {

	public int add(PiContent piContent);

	public int addList(ArrayList<PiContent> piContentList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContent piContent);

	public PiContent getModel(String id);

	public List<PiContent> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





