package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaCheckTesting;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaCheckTesting>，可手动更改。检测数据表
 */

public interface IDaCheckTestingSplDal extends IBaseSplDal {

	public int add(DaCheckTesting daCheckTesting);

	public int addList(ArrayList<DaCheckTesting> daCheckTestingList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaCheckTesting daCheckTesting);

	public DaCheckTesting getModel(String id);

	public List<DaCheckTesting> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





