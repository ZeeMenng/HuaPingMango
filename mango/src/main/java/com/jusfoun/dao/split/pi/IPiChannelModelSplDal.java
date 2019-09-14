package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiChannelModel;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiChannelModel>，可手动更改。CMS栏目可选内容模型表
 */

public interface IPiChannelModelSplDal extends IBaseSplDal {

	public int add(PiChannelModel piChannelModel);

	public int addList(ArrayList<PiChannelModel> piChannelModelList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiChannelModel piChannelModel);

	public PiChannelModel getModel(String id);

	public List<PiChannelModel> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





