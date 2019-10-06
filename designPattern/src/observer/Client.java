package observer;

public class Client {
	public static void main(String[] args){
		ObserverList ol = new ObserverList();
		ol.addObserver(new ListObserver());
		
		ol.added(1);
		ol.added(2);
		ol.deleted(1);
	}
}
