package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiContentAttr;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContentAttr>，可手动更改。CMS内容扩展属性表
 */

public interface IPiContentAttrSplDal extends IBaseSplDal {

	public int add(PiContentAttr piContentAttr);

	public int addList(ArrayList<PiContentAttr> piContentAttrList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContentAttr piContentAttr);

	public PiContentAttr getModel(String id);

	public List<PiContentAttr> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





