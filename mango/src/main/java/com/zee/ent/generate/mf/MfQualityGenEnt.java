package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:38
 * @description 实体类MfQualityGenEnt，自动生成。质量安全综合指数表,建模
 */

public class MfQualityGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String dateTime;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="指数,数字",hidden=false,required=false)
    private Integer indexNum;
    @ApiModelProperty(value="指数,1:低，2：中，3：高",allowableValues="0,1",hidden=false,required=false)
    private Byte levelCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String levelText;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * set方法。
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
	 * get方法。指数,数字
	 */
	public Integer getIndexNum() {
		return this.indexNum;
	}

	/**
	 * set方法。指数,数字
	 */
	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}
    
	/**
	 * get方法。指数,1:低，2：中，3：高
	 */
	public Byte getLevelCode() {
		return this.levelCode;
	}

	/**
	 * set方法。指数,1:低，2：中，3：高
	 */
	public void setLevelCode(Byte levelCode) {
		this.levelCode = levelCode;
	}
    
	/**
	 * get方法。
	 */
	public String getLevelText() {
		return this.levelText;
	}

	/**
	 * set方法。
	 */
	public void setLevelText(String levelText) {
		this.levelText = levelText;
	}
    










}







