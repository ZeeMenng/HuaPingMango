package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiChannel;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiChannel>，可手动更改。CMS栏目表
 */

public interface IPiChannelSplDal extends IBaseSplDal {

	public int add(PiChannel piChannel);

	public int addList(ArrayList<PiChannel> piChannelList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiChannel piChannel);

	public PiChannel getModel(String id);

	public List<PiChannel> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





