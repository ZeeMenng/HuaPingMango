package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PiFile;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiFile>，可手动更改。CMS文章相关文件表
 */

public interface IPiFileSplDal extends IBaseSplDal {

	public int add(PiFile piFile);

	public int addList(ArrayList<PiFile> piFileList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiFile piFile);

	public PiFile getModel(String id);

	public List<PiFile> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





