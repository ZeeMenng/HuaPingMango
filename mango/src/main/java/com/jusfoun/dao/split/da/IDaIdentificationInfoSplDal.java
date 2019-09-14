package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaIdentificationInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaIdentificationInfo>，可手动更改。三品一标认证表
 */

public interface IDaIdentificationInfoSplDal extends IBaseSplDal {

	public int add(DaIdentificationInfo daIdentificationInfo);

	public int addList(ArrayList<DaIdentificationInfo> daIdentificationInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaIdentificationInfo daIdentificationInfo);

	public DaIdentificationInfo getModel(String id);

	public List<DaIdentificationInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





