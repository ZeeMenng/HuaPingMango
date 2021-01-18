package com.jusfoun.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.gp.GprConfig;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2021/1/18 19:49:24
 * @description 扩展自实体类IBaseUntDal<GprConfig>，可手动更改。应用领域配置信息。
 */

public interface IGprConfigSplDal extends IBaseSplDal {

	public int add(GprConfig gprConfig);

	public int addList(ArrayList<GprConfig> gprConfigList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GprConfig gprConfig);

	public GprConfig getModel(String id);

	public List<GprConfig> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





