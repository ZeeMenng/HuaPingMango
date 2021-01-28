package com.zee.dao.split.mf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.mf.MfFormatrix;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类IBaseUntDal<MfFormatrix>，可手动更改。波士顿矩阵
 */

public interface IMfFormatrixSplDal extends IBaseSplDal {

	public int add(MfFormatrix mfFormatrix);

	public int addList(ArrayList<MfFormatrix> mfFormatrixList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(MfFormatrix mfFormatrix);

	public MfFormatrix getModel(String id);

	public List<MfFormatrix> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





