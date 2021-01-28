package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaPeasantInfo;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-21 10:22:07
 * @description 扩展自实体类IBaseUntDal<DaPeasantInfo>，可手动更改。农户信息表
 */

public interface IDaPeasantInfoSplDal extends IBaseSplDal {

	public int add(DaPeasantInfo daPeasantInfo);

	public int addList(ArrayList<DaPeasantInfo> daPeasantInfoList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaPeasantInfo daPeasantInfo);

	public DaPeasantInfo getModel(String id);

	public List<DaPeasantInfo> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





