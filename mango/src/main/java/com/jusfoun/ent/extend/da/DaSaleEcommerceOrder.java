package com.jusfoun.ent.extend.da;

import com.jusfoun.ent.generate.da.DaSaleEcommerceOrderGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaSaleEcommerceOrderGenEnt，可手动更改。电商数据表，用于存放直报的电商数据
 */

@ApiModel(value = "DaSaleEcommerceOrder", description = "电商数据表，用于存放直报的电商数据")
public class DaSaleEcommerceOrder extends DaSaleEcommerceOrderGenEnt {
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







