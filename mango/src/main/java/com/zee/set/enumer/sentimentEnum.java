package com.zee.set.enumer;

public enum sentimentEnum {
	
	QUANBU((byte)0, "全部"),
	FUMIAN((byte)1, "负面"),
	ZHONGXING((byte)2, "中性"),
	ZHENGMIAN((byte)3, "正面"),
	;
	private String text;
	private Byte code;
	private sentimentEnum(Byte code, String text) {
		this.text = text;
		this.code = code;
	}

	public static String getText(Byte code) {
		for (sentimentEnum c : sentimentEnum.values()) {
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
