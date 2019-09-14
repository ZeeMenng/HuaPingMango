package com.jusfoun.set.enumer;

public enum DictionaryModuleLevelEnum {

	// 功能模块级别
	FIRST((byte) 1, "第一级"),
	SECOND((byte) 2, "第二级"), 
	THIRD((byte) 3, "第三级"), 
	FOURTH((byte) 4, "第四级"), 
	FIFTH((byte) 5, "第五级"), 
	SIXTH((byte) 6, "第六级"),
	;

	private String text;
	private Byte code;

	private DictionaryModuleLevelEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DictionaryModuleLevelEnum c : DictionaryModuleLevelEnum.values()) {
			if (c.getCode() == code) {
				return c.text;
			}
		}
		return null;

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Byte getCode() {
		return code;
	}

	public void setCode(Byte code) {
		this.code = code;
	}
}
