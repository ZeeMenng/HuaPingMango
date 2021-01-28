package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaCorpDisaster;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaCorpDisaster>，可手动更改。作物受灾数据表
 */

public interface IDaCorpDisasterSplDal extends IBaseSplDal {

	public int add(DaCorpDisaster daCorpDisaster);

	public int addList(ArrayList<DaCorpDisaster> daCorpDisasterList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaCorpDisaster daCorpDisaster);

	public DaCorpDisaster getModel(String id);

	public List<DaCorpDisaster> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





