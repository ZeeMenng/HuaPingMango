package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiChannelTxt;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiChannelTxt>，可手动更改。CMS栏目文本表
 */

public interface IPiChannelTxtSplDal extends IBaseSplDal {

	public int add(PiChannelTxt piChannelTxt);

	public int addList(ArrayList<PiChannelTxt> piChannelTxtList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiChannelTxt piChannelTxt);

	public PiChannelTxt getModel(String id);

	public List<PiChannelTxt> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





