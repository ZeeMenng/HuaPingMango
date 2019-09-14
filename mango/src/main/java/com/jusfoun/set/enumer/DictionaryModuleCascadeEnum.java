package com.jusfoun.set.enumer;

public enum DictionaryModuleCascadeEnum {

	// 功能模块是否级联更新菜单
	ALL((byte) 0, "全部级联"), //
	ADD((byte) 1, "仅级联增"), //
	DELETE((byte) 2, "仅级联删"), //
	UPDATE((byte) 3, "仅级联改"), //
	NONE((byte) 4, "不做级联"),
	;

	private String text;
	private Byte code;

	private DictionaryModuleCascadeEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (DictionaryModuleCascadeEnum c : DictionaryModuleCascadeEnum.values()) {
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
