package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiChannelCount;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiChannelCount>，可手动更改。CMS栏目访问量计数表
 */

public interface IPiChannelCountSplDal extends IBaseSplDal {

	public int add(PiChannelCount piChannelCount);

	public int addList(ArrayList<PiChannelCount> piChannelCountList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiChannelCount piChannelCount);

	public PiChannelCount getModel(String id);

	public List<PiChannelCount> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





