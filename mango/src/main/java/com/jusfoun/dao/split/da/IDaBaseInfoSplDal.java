package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaBaseInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-20 14:12:54
 * @description 扩展自实体类IBaseUntDal<DaBaseInfo>，可手动更改。基地信息表
 */

public interface IDaBaseInfoSplDal extends IBaseSplDal {

	public int add(DaBaseInfo daBaseInfo);

	public int addList(ArrayList<DaBaseInfo> daBaseInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaBaseInfo daBaseInfo);

	public DaBaseInfo getModel(String id);

	public List<DaBaseInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





