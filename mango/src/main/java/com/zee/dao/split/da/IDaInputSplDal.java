package com.zee.dao.split.da;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.da.DaInput;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类IBaseUntDal<DaInput>，可手动更改。投入品信息表
 */

public interface IDaInputSplDal extends IBaseSplDal {

	public int add(DaInput daInput);

	public int addList(ArrayList<DaInput> daInputList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(DaInput daInput);

	public DaInput getModel(String id);

	public List<DaInput> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





