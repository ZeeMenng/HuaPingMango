package com.zee.dao.split.pe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pe.PeAerialBroadcast;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-27 10:25:42
 * @description 扩展自实体类IBaseUntDal<PeAerialBroadcast>，可手动更改。鸟瞰华坪轮播图
 */

public interface IPeAerialBroadcastSplDal extends IBaseSplDal {

	public int add(PeAerialBroadcast peAerialBroadcast);

	public int addList(ArrayList<PeAerialBroadcast> peAerialBroadcastList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PeAerialBroadcast peAerialBroadcast);

	public PeAerialBroadcast getModel(String id);

	public List<PeAerialBroadcast> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





