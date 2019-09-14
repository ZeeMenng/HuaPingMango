package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiExpert;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/7/11 17:38:33
 * @description 扩展自实体类IBaseUntDal<PiExpert>，可手动更改。专家表
 */

public interface IPiExpertSplDal extends IBaseSplDal {

	public int add(PiExpert piExpert);

	public int addList(ArrayList<PiExpert> piExpertList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiExpert piExpert);

	public PiExpert getModel(String id);

	public List<PiExpert> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





