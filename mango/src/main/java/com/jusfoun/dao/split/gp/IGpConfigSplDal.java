package com.jusfoun.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.gp.GpConfig;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2021/1/18 19:49:24
 * @description 扩展自实体类IBaseUntDal<GpConfig>，可手动更改。配置项信息。
 */

public interface IGpConfigSplDal extends IBaseSplDal {

	public int add(GpConfig gpConfig);

	public int addList(ArrayList<GpConfig> gpConfigList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpConfig gpConfig);

	public GpConfig getModel(String id);

	public List<GpConfig> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





