package proxy;

import java.util.concurrent.TimeUnit;

public class Target implements Proxyable{

	@Override
	public void proxy() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
