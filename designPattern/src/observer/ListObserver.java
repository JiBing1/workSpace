package observer;

public class ListObserver implements Observer{

	@Override
	public void update(Observerable o, Object arg) {
		Integer i = (Integer)arg;
		System.out.println("被观察者改变了list的内容,向list中添加或删除了元素：" + i);
	}

}
