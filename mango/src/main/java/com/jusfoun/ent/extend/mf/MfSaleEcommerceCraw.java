package com.zee.ent.extend.mf;

import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.generate.mf.MfSaleEcommerceCrawGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:07:53
 * @description 扩展自实体类MfSaleEcommerceCrawGenEnt，可手动更改。电商数据表,采集
 */

@ApiModel(value = "MfSaleEcommerceCraw", description = "电商数据表,采集")
public class MfSaleEcommerceCraw extends MfSaleEcommerceCrawGenEnt {
  
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}
}







