package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaSaleProcess;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaSaleProcess>，可手动更改。加工销售数据表
 */

public interface IDaSaleProcessSplDal extends IBaseSplDal {

	public int add(DaSaleProcess daSaleProcess);

	public int addList(ArrayList<DaSaleProcess> daSaleProcessList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSaleProcess daSaleProcess);

	public DaSaleProcess getModel(String id);

	public List<DaSaleProcess> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





