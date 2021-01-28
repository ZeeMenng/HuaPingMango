package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiContentTxt;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContentTxt>，可手动更改。CMS内容文本表
 */

public interface IPiContentTxtSplDal extends IBaseSplDal {

	public int add(PiContentTxt piContentTxt);

	public int addList(ArrayList<PiContentTxt> piContentTxtList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContentTxt piContentTxt);

	public PiContentTxt getModel(String id);

	public List<PiContentTxt> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





