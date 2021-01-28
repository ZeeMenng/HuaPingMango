package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiChannelAttr;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiChannelAttr>，可手动更改。CMS栏目扩展属性表
 */

public interface IPiChannelAttrSplDal extends IBaseSplDal {

	public int add(PiChannelAttr piChannelAttr);

	public int addList(ArrayList<PiChannelAttr> piChannelAttrList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiChannelAttr piChannelAttr);

	public PiChannelAttr getModel(String id);

	public List<PiChannelAttr> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





