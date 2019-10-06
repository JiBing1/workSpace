package proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import proxy.Target;

public class MyInvocationHandler implements InvocationHandler{
	private Target target;
	public MyInvocationHandler(Target target){
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		long startTime = System.currentTimeMillis();
		method.invoke(target, args);
		long endTime = System.currentTimeMillis();
		System.out.println("被代理方法执行耗时：" + (endTime - startTime) + "ms");
		return null;
	}

}
