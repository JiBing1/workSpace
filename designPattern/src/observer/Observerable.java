package observer;

public interface Observerable {
	public void addObserver(Observer o);
	public void deleteObserver(Observer o);
	public void deleteObservers();
	public void notifyObservers();
	public void notifyObservers(Object o);
}
