package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiInteraction;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-7-5 11:35:44
 * @description 扩展自实体类IBaseUntDal<PiInteraction>，可手动更改。pi_interaction
 */

public interface IPiInteractionSplDal extends IBaseSplDal {

	public int add(PiInteraction piInteraction);

	public int addList(ArrayList<PiInteraction> piInteractionList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiInteraction piInteraction);

	public PiInteraction getModel(String id);

	public List<PiInteraction> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





