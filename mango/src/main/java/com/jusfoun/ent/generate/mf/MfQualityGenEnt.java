package com.jusfoun.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:44:02
 * @description 实体类MfQualityGenEnt，自动生成。质量安全综合指数表,建模
 */

public class MfQualityGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="按年，存YYYY，如2018",hidden=false,required=true)
    private String dateTime;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="指数,数字",hidden=false,required=false)
    private Integer indexNum;
    @ApiModelProperty(value="指数,1:低，2：中，3：高",allowableValues="0,1",hidden=false,required=false)
    private Byte levelCode;
    @ApiModelProperty(value="指数,1:低，2：中，3：高",hidden=false,required=false)
    private String levelText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。按年，存YYYY，如2018
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * set方法。按年，存YYYY，如2018
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
	 * get方法。指数,1:低，2：中，3：高
	 */
	public String getLevelText() {
		return this.levelText;
	}

	/**
	 * set方法。指数,1:低，2：中，3：高
	 */
	public void setLevelText(String levelText) {
		this.levelText = levelText;
	}
    

    //一对多关系中，多端数据列表

}







