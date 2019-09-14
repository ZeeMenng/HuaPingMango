package com.jusfoun.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.da.DarEnterpriseResource;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/7/11 17:38:33
 * @description 扩展自实体类IBaseUntDal<DarEnterpriseResource>，可手动更改。企业和资源中间表
 */

public interface IDarEnterpriseResourceSplDal extends IBaseSplDal {

	public int add(DarEnterpriseResource darEnterpriseResource);

	public int addList(ArrayList<DarEnterpriseResource> darEnterpriseResourceList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DarEnterpriseResource darEnterpriseResource);

	public DarEnterpriseResource getModel(String id);

	public List<DarEnterpriseResource> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





