package proxy.dynamicProxy;

import java.lang.reflect.Proxy;

import proxy.Proxyable;
import proxy.Target;

public class TargetProxy {
	public static Proxyable newProxyInstance(){
		return (Proxyable)Proxy.newProxyInstance(Proxyable.class.getClassLoader(), new Class[]{Proxyable.class},new MyInvocationHandler(new Target()));
	}
	
}
