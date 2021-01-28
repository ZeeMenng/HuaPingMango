package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaBaseDetail;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-21 11:38:36
 * @description 扩展自实体类IBaseUntDal<DaBaseDetail>，可手动更改。基地细分表-地块表
 */

public interface IDaBaseDetailSplDal extends IBaseSplDal {

	public int add(DaBaseDetail daBaseDetail);

	public int addList(ArrayList<DaBaseDetail> daBaseDetailList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaBaseDetail daBaseDetail);

	public DaBaseDetail getModel(String id);

	public List<DaBaseDetail> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





