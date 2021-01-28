package com.zee.dao.split.pi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.pi.PiContentPicture;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<PiContentPicture>，可手动更改。CMS内容图片表
 */

public interface IPiContentPictureSplDal extends IBaseSplDal {

	public int add(PiContentPicture piContentPicture);

	public int addList(ArrayList<PiContentPicture> piContentPictureList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(PiContentPicture piContentPicture);

	public PiContentPicture getModel(String id);

	public List<PiContentPicture> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





