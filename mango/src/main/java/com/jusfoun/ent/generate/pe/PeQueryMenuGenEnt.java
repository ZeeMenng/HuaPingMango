package com.jusfoun.ent.generate.pe;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-7-2 16:58:02
 * @description 实体类PeQueryMenuGenEnt，自动生成。数据版门户-数据资源-查询菜单
 */

public class PeQueryMenuGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应字典表的code字段,1年2季度3月4日5实时",allowableValues="0,1",hidden=false,required=false)
    private Byte dataTimeTypeCode;
    @ApiModelProperty(value="对应字典表的text字段,1年2季度3月4日5实时 ",hidden=false,required=false)
    private String dataTimeTypeText;
    @ApiModelProperty(value="用以记录各菜单的父级菜单的id",hidden=false,required=false)
    private String fartherId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="对应字典表的code字段,0导航菜单 1，一级菜单2，二级菜单3，三级菜单",allowableValues="0,1",hidden=false,required=false)
    private Byte menuLevelCode;
    @ApiModelProperty(value="对应字典表的text字段,0导航菜单 1，一级菜单2，二级菜单3，三级菜单",hidden=false,required=false)
    private String menuLevelText;
    @ApiModelProperty(value="菜单名称",hidden=false,required=true)
    private String menuName;
    @ApiModelProperty(value="对应字典表的code字段,1，田头价2，批发价3，零售价4，电商价5，进口价6，出口价",allowableValues="0,1",hidden=false,required=false)
    private Byte priceTypeCode;
    @ApiModelProperty(value="对应字典表的text字段,1，田头价2，批发价3，零售价4，电商价5，进口价6，出口价",hidden=false,required=false)
    private String priceTypeText;
    @ApiModelProperty(value="对应字典表的code字段,1，元2，万元3，亿元4，美元",allowableValues="0,1",hidden=false,required=false)
    private Byte priceUnitCode;
    @ApiModelProperty(value="对应字典表的text字段,1，元2，万元3，亿元4，美元 ",hidden=false,required=false)
    private String priceUnitText;
    @ApiModelProperty(value="指定需要查询的具体内容，如金额(volume)、数量(amount)、面积(area)",hidden=false,required=false)
    private String queryType;
    @ApiModelProperty(value="地理区域 :对应区域表的code",hidden=false,required=false)
    private String regionId;
    @ApiModelProperty(value="用以表示各条件之间的关系",hidden=false,required=false)
    private String relationId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种 ",hidden=false,required=false)
    private String strainsText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。对应字典表的code字段,1年2季度3月4日5实时
	 */
	public Byte getDataTimeTypeCode() {
		return this.dataTimeTypeCode;
	}

	/**
	 * set方法。对应字典表的code字段,1年2季度3月4日5实时
	 */
	public void setDataTimeTypeCode(Byte dataTimeTypeCode) {
		this.dataTimeTypeCode = dataTimeTypeCode;
	}
    
	/**
	 * get方法。对应字典表的text字段,1年2季度3月4日5实时 
	 */
	public String getDataTimeTypeText() {
		return this.dataTimeTypeText;
	}

	/**
	 * set方法。对应字典表的text字段,1年2季度3月4日5实时 
	 */
	public void setDataTimeTypeText(String dataTimeTypeText) {
		this.dataTimeTypeText = dataTimeTypeText;
	}
    
	/**
	 * get方法。用以记录各菜单的父级菜单的id
	 */
	public String getFartherId() {
		return this.fartherId;
	}

	/**
	 * set方法。用以记录各菜单的父级菜单的id
	 */
	public void setFartherId(String fartherId) {
		this.fartherId = fartherId;
	}
    
	/**
	 * get方法。主键id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键id
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。对应字典表的code字段,0导航菜单 1，一级菜单2，二级菜单3，三级菜单
	 */
	public Byte getMenuLevelCode() {
		return this.menuLevelCode;
	}

	/**
	 * set方法。对应字典表的code字段,0导航菜单 1，一级菜单2，二级菜单3，三级菜单
	 */
	public void setMenuLevelCode(Byte menuLevelCode) {
		this.menuLevelCode = menuLevelCode;
	}
    
	/**
	 * get方法。对应字典表的text字段,0导航菜单 1，一级菜单2，二级菜单3，三级菜单
	 */
	public String getMenuLevelText() {
		return this.menuLevelText;
	}

	/**
	 * set方法。对应字典表的text字段,0导航菜单 1，一级菜单2，二级菜单3，三级菜单
	 */
	public void setMenuLevelText(String menuLevelText) {
		this.menuLevelText = menuLevelText;
	}
    
	/**
	 * get方法。菜单名称
	 */
	public String getMenuName() {
		return this.menuName;
	}

	/**
	 * set方法。菜单名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
    
	/**
	 * get方法。对应字典表的code字段,1，田头价2，批发价3，零售价4，电商价5，进口价6，出口价
	 */
	public Byte getPriceTypeCode() {
		return this.priceTypeCode;
	}

	/**
	 * set方法。对应字典表的code字段,1，田头价2，批发价3，零售价4，电商价5，进口价6，出口价
	 */
	public void setPriceTypeCode(Byte priceTypeCode) {
		this.priceTypeCode = priceTypeCode;
	}
    
	/**
	 * get方法。对应字典表的text字段,1，田头价2，批发价3，零售价4，电商价5，进口价6，出口价
	 */
	public String getPriceTypeText() {
		return this.priceTypeText;
	}

	/**
	 * set方法。对应字典表的text字段,1，田头价2，批发价3，零售价4，电商价5，进口价6，出口价
	 */
	public void setPriceTypeText(String priceTypeText) {
		this.priceTypeText = priceTypeText;
	}
    
	/**
	 * get方法。对应字典表的code字段,1，元2，万元3，亿元4，美元
	 */
	public Byte getPriceUnitCode() {
		return this.priceUnitCode;
	}

	/**
	 * set方法。对应字典表的code字段,1，元2，万元3，亿元4，美元
	 */
	public void setPriceUnitCode(Byte priceUnitCode) {
		this.priceUnitCode = priceUnitCode;
	}
    
	/**
	 * get方法。对应字典表的text字段,1，元2，万元3，亿元4，美元 
	 */
	public String getPriceUnitText() {
		return this.priceUnitText;
	}

	/**
	 * set方法。对应字典表的text字段,1，元2，万元3，亿元4，美元 
	 */
	public void setPriceUnitText(String priceUnitText) {
		this.priceUnitText = priceUnitText;
	}
    
	/**
	 * get方法。指定需要查询的具体内容，如金额(volume)、数量(amount)、面积(area)
	 */
	public String getQueryType() {
		return this.queryType;
	}

	/**
	 * set方法。指定需要查询的具体内容，如金额(volume)、数量(amount)、面积(area)
	 */
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
    
	/**
	 * get方法。地理区域 :对应区域表的code
	 */
	public String getRegionId() {
		return this.regionId;
	}

	/**
	 * set方法。地理区域 :对应区域表的code
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
    
	/**
	 * get方法。用以表示各条件之间的关系
	 */
	public String getRelationId() {
		return this.relationId;
	}

	/**
	 * set方法。用以表示各条件之间的关系
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的作物品种
	 */
	public Byte getStrainsCode() {
		return this.strainsCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的作物品种
	 */
	public void setStrainsCode(Byte strainsCode) {
		this.strainsCode = strainsCode;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的作物品种 
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的作物品种 
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    

    //一对多关系中，多端数据列表

}







