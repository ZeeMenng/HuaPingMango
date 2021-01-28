package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaEnterpriseUpdownstreamInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaEnterpriseUpdownstreamInfo>，可手动更改。企业关联企业表，企业上下游关系记录。
 */

public interface IDaEnterpriseUpdownstreamInfoSplDal extends IBaseSplDal {

	public int add(DaEnterpriseUpdownstreamInfo daEnterpriseUpdownstreamInfo);

	public int addList(ArrayList<DaEnterpriseUpdownstreamInfo> daEnterpriseUpdownstreamInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaEnterpriseUpdownstreamInfo daEnterpriseUpdownstreamInfo);

	public DaEnterpriseUpdownstreamInfo getModel(String id);

	public List<DaEnterpriseUpdownstreamInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





