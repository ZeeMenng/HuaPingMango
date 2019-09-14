package com.jusfoun.dao.split.pe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pe.PeQueryMenu;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-7-2 16:58:02
 * @description 扩展自实体类IBaseUntDal<PeQueryMenu>，可手动更改。数据版门户-数据资源-查询菜单
 */

public interface IPeQueryMenuSplDal extends IBaseSplDal {

	public int add(PeQueryMenu peQueryMenu);

	public int addList(ArrayList<PeQueryMenu> peQueryMenuList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PeQueryMenu peQueryMenu);

	public PeQueryMenu getModel(String id);

	public List<PeQueryMenu> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





