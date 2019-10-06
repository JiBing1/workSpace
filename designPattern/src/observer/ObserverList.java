package observer;

import java.util.ArrayList;

public class ObserverList extends AbstractObserver{
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	public void added(Integer i){
		if(list.add(i)){
			notifyObservers(i);
		}
	}
	
	public void deleted(Integer i){
		if(list.remove((Integer)i)){
			notifyObservers(i);
		}
	}
	
	public void deleteAll(){
		if(list.removeAll(list)){
			notifyObservers(list);
		}
	}
}
