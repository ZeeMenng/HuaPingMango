package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PirProductResource;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/7/11 17:38:33
 * @description 扩展自实体类IBaseUntDal<PirProductResource>，可手动更改。产品和资源中间表
 */

public interface IPirProductResourceSplDal extends IBaseSplDal {

	public int add(PirProductResource pirProductResource);

	public int addList(ArrayList<PirProductResource> pirProductResourceList);

	public int delete(String id);
	
	public int deleteByProductId(String productId);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirProductResource pirProductResource);

	public PirProductResource getModel(String id);

	public List<PirProductResource> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





