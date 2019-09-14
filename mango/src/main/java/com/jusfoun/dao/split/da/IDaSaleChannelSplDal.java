package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaSaleChannel;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaSaleChannel>，可手动更改。销售渠道数据表
 */

public interface IDaSaleChannelSplDal extends IBaseSplDal {

	public int add(DaSaleChannel daSaleChannel);

	public int addList(ArrayList<DaSaleChannel> daSaleChannelList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaSaleChannel daSaleChannel);

	public DaSaleChannel getModel(String id);

	public List<DaSaleChannel> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





