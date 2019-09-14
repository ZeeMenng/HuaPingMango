package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiContentCount;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContentCount>，可手动更改。CMS内容计数表
 */

public interface IPiContentCountSplDal extends IBaseSplDal {

	public int add(PiContentCount piContentCount);

	public int addList(ArrayList<PiContentCount> piContentCountList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContentCount piContentCount);

	public PiContentCount getModel(String id);

	public List<PiContentCount> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





