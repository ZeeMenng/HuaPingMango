package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiContentCheck;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContentCheck>，可手动更改。CMS内容审核信息表
 */

public interface IPiContentCheckSplDal extends IBaseSplDal {

	public int add(PiContentCheck piContentCheck);

	public int addList(ArrayList<PiContentCheck> piContentCheckList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContentCheck piContentCheck);

	public PiContentCheck getModel(String id);

	public List<PiContentCheck> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





