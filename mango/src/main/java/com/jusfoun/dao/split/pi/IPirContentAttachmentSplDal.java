package com.jusfoun.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jusfoun.dao.split.base.IBaseSplDal;
import com.jusfoun.ent.extend.pi.PirContentAttachment;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/5/7 15:00:36
 * @description 扩展自实体类IBaseUntDal<PirContentAttachment>，可手动更改。CMS文章内容包含附件信息表。
 */

public interface IPirContentAttachmentSplDal extends IBaseSplDal {

	public int add(PirContentAttachment pirContentAttachment);

	public int addList(ArrayList<PirContentAttachment> pirContentAttachmentList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PirContentAttachment pirContentAttachment);

	public PirContentAttachment getModel(String id);

	public List<PirContentAttachment> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





