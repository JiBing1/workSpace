package proxy.dynamicProxy;

import proxy.Proxyable;

public class Client {
	public static void main(String[] args){
		Proxyable proxy = TargetProxy.newProxyInstance();
		proxy.proxy();
	}
}
