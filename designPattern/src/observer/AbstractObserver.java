package observer;

import java.util.Vector;

public abstract class AbstractObserver implements Observerable{
	protected Vector<Observer> observers = new Vector<Observer>();
	
	public synchronized void addObserver(Observer o){
		if(!observers.contains(o))
			observers.add(o);
	}
	
	public synchronized void deleteObserver(Observer o){
		observers.remove(o);
	}
	
	public synchronized void deleteObservers(){
		observers.removeAllElements();
	}
	
	public void notifyObservers(){
		notifyObservers(null);
	}
	
	public void notifyObservers(Object arg){
		Object[] arr = null;
		arr = observers.toArray();
		for(int i = 0; i < arr.length; i++){
			((Observer)arr[i]).update(this, arg);
		}
	}
}
