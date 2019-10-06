package proxy.staticProxy;

import proxy.Proxyable;
import proxy.Target;

public class TargetProxy implements Proxyable{
	private Target target;
	private ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
	
	public TargetProxy(Target target){
		this.target = target;
	}

	@Override
	public void proxy() {
		threadLocal.set(System.currentTimeMillis());
		target.proxy();
		System.out.println("被代理的方法耗时：" + getMethodRunTime() + "ms");
	}
	
	public long getMethodRunTime(){
		return System.currentTimeMillis() - threadLocal.get();
	}
}
