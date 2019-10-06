package proxy.staticProxy;

import proxy.Proxyable;
import proxy.Target;

public class Client {
	public static void main(String[] args){
		Proxyable proxy = new TargetProxy(new Target());
		proxy.proxy();
	}
}
