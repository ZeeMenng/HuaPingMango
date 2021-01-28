package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfInputSubject;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfInputSubject>，可手动更改。投入品主体监管表
 */

public interface IMfInputSubjectSplDal extends IBaseSplDal {

	public int add(MfInputSubject mfInputSubject);

	public int addList(ArrayList<MfInputSubject> mfInputSubjectList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfInputSubject mfInputSubject);

	public MfInputSubject getModel(String id);

	public List<MfInputSubject> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





