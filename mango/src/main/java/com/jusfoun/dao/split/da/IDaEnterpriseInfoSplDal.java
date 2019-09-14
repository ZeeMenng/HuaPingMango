package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DaEnterpriseInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-9-19 11:30:20
 * @description 扩展自实体类IBaseUntDal<DaEnterpriseInfo>，可手动更改。企业基本信息表
 */

public interface IDaEnterpriseInfoSplDal extends IBaseSplDal {

	public int add(DaEnterpriseInfo daEnterpriseInfo);

	public int addList(ArrayList<DaEnterpriseInfo> daEnterpriseInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterpriseInfo daEnterpriseInfo);

	public DaEnterpriseInfo getModel(String id);

	public List<DaEnterpriseInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





