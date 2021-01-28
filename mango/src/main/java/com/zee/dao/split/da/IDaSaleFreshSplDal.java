package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaSaleFresh;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaSaleFresh>，可手动更改。鲜果销售数据表
 */

public interface IDaSaleFreshSplDal extends IBaseSplDal {

	public int add(DaSaleFresh daSaleFresh);

	public int addList(ArrayList<DaSaleFresh> daSaleFreshList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSaleFresh daSaleFresh);

	public DaSaleFresh getModel(String id);

	public List<DaSaleFresh> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





