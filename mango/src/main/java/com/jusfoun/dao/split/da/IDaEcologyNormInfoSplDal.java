package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaEcologyNormInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaEcologyNormInfo>，可手动更改。种植生态指标表
 */

public interface IDaEcologyNormInfoSplDal extends IBaseSplDal {

	public int add(DaEcologyNormInfo daEcologyNormInfo);

	public int addList(ArrayList<DaEcologyNormInfo> daEcologyNormInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEcologyNormInfo daEcologyNormInfo);

	public DaEcologyNormInfo getModel(String id);

	public List<DaEcologyNormInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





