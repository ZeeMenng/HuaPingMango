package com.zee.ent.generate.pe;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:42
 * @description 实体类PeQueryMenuGenEnt，自动生成。数据版门户-数据资源-查询菜单
 */

public class PeQueryMenuGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应字典表的code字段,1年2季度3月4日5实时",allowableValues="0,1",hidden=false,required=false)
    private Byte dataTimeTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String dataTimeTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String fartherId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="对应字典表的code字段,0导航菜单 1，一级菜单2，二级菜单3，三级菜单",allowableValues="0,1",hidden=false,required=false)
    private Byte menuLevelCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String menuLevelText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String menuName;
    @ApiModelProperty(value="对应字典表的code字段,1，田头价2，批发价3，零售价4，电商价5，进口价6，出口价",allowableValues="0,1",hidden=false,required=false)
    private Byte priceTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String priceTypeText;
    @ApiModelProperty(value="对应字典表的code字段,1，元2，万元3，亿元4，美元",allowableValues="0,1",hidden=false,required=false)
    private Byte priceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String priceUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String queryType;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String regionId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String relationId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String strainsText;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。
	 */
	public String getDataTimeTypeText() {
		return this.dataTimeTypeText;
	}

	/**
	 * set方法。
	 */
	public void setDataTimeTypeText(String dataTimeTypeText) {
		this.dataTimeTypeText = dataTimeTypeText;
	}
    
	/**
	 * get方法。
	 */
	public String getFartherId() {
		return this.fartherId;
	}

	/**
	 * set方法。
	 */
	public void setFartherId(String fartherId) {
		this.fartherId = fartherId;
	}
    
	/**
	 * get方法。
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getMenuLevelText() {
		return this.menuLevelText;
	}

	/**
	 * set方法。
	 */
	public void setMenuLevelText(String menuLevelText) {
		this.menuLevelText = menuLevelText;
	}
    
	/**
	 * get方法。
	 */
	public String getMenuName() {
		return this.menuName;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getPriceTypeText() {
		return this.priceTypeText;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getPriceUnitText() {
		return this.priceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setPriceUnitText(String priceUnitText) {
		this.priceUnitText = priceUnitText;
	}
    
	/**
	 * get方法。
	 */
	public String getQueryType() {
		return this.queryType;
	}

	/**
	 * set方法。
	 */
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionId() {
		return this.regionId;
	}

	/**
	 * set方法。
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
    
	/**
	 * get方法。
	 */
	public String getRelationId() {
		return this.relationId;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    










}







