package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaMainProductionArea;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-25 10:47:17
 * @description 扩展自实体类IBaseUntDal<DaMainProductionArea>，可手动更改。芒果主产区
 */

public interface IDaMainProductionAreaSplDal extends IBaseSplDal {

	public int add(DaMainProductionArea daMainProductionArea);

	public int addList(ArrayList<DaMainProductionArea> daMainProductionAreaList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaMainProductionArea daMainProductionArea);

	public DaMainProductionArea getModel(String id);

	public List<DaMainProductionArea> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





