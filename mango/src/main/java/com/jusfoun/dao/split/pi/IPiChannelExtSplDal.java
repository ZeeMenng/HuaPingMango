package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiChannelExt;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiChannelExt>，可手动更改。CMS栏目内容表
 */

public interface IPiChannelExtSplDal extends IBaseSplDal {

	public int add(PiChannelExt piChannelExt);

	public int addList(ArrayList<PiChannelExt> piChannelExtList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiChannelExt piChannelExt);

	public PiChannelExt getModel(String id);

	public List<PiChannelExt> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





