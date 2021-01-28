package com.zee.utl.crawler;

import us.codecraft.webmagic.proxy.Proxy;

public class GenerateProxy {

	// 端口
	public static final int PORT_INT = 80;

	/**
	 * 获取代理ip 192.168.10.10-10.254		192.168.11.10-11.254	192.168.13.3-13.154		192.168.14.155-14.254
	 */
	public static Proxy[] getRandomProxyIP() {
		Proxy[] proxiesTemp = new Proxy[100];
		int length = 0;
		for(int i=3; i<=254; i++){
//			if(i>=10){
//				proxiesTemp[length++] = new Proxy("192.168.10."+i, PORT_INT);
//				proxiesTemp[length++] = new Proxy("192.168.11."+i, PORT_INT);
//			}
//			if(i<=154){
//				proxiesTemp[length++] = new Proxy("192.168.13."+i, PORT_INT);
//			}
			if(i>=155){
				proxiesTemp[length++] = new Proxy("192.168.14."+i, PORT_INT);
			}
		}
		return proxiesTemp;
	}

//	public static void main(String[] args) {
//		getRandomProxyIP();
//	}
}
