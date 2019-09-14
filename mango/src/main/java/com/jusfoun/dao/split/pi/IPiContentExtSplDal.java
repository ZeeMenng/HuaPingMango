package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiContentExt;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContentExt>，可手动更改。CMS内容扩展表
 */

public interface IPiContentExtSplDal extends IBaseSplDal {

	public int add(PiContentExt piContentExt);

	public int addList(ArrayList<PiContentExt> piContentExtList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContentExt piContentExt);

	public PiContentExt getModel(String id);

	public List<PiContentExt> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





