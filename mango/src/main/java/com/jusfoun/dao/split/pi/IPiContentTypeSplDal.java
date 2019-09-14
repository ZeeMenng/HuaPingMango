package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiContentType;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContentType>，可手动更改。CMS内容类型表
 */

public interface IPiContentTypeSplDal extends IBaseSplDal {

	public int add(PiContentType piContentType);

	public int addList(ArrayList<PiContentType> piContentTypeList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContentType piContentType);

	public PiContentType getModel(String id);

	public List<PiContentType> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





