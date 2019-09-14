package com.jusfoun.dao.split.pe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pe.PeAerialView;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-26 15:20:30
 * @description 扩展自实体类IBaseUntDal<PeAerialView>，可手动更改。鸟瞰图
 */

public interface IPeAerialViewSplDal extends IBaseSplDal {

	public int add(PeAerialView peAerialView);

	public int addList(ArrayList<PeAerialView> peAerialViewList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PeAerialView peAerialView);

	public PeAerialView getModel(String id);

	public List<PeAerialView> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





