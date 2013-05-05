package se.chalmers.tda367.group15.game.event;

/**
 * An abstract implementation of the EventListener interface, forwarding only
 * the events of type T to the extending class.
 * 
 * @author Peter
 * @see EventListener
 * 
 * @param <T>
 *            The type of events to listen for.
 */
public abstract class AbstractEventListener<T extends Event> implements
		EventListener<T> {
	/**
	 * The type of class this object is a listener for.
	 */
	private Class<T> clazz;

	/**
	 * Creates a new EventListener for the provided class.
	 * 
	 * @param clazz
	 *            The class for which the EventListener should listen for. Must
	 *            not be null.
	 */
	public AbstractEventListener(Class<T> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException();
		}
		this.clazz = clazz;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <E> void onEvent(E event) {
		// Check if this event has the type we are looking for. If so, forward
		// it to the specific onEvent handler
		if (clazz.isInstance(event)) {
			onEvent((T) event);
		}
	}

	/**
	 * The method for the event of type T, that this class is listening for.
	 * 
	 * @param event
	 */
	public abstract void onEvent(T event);
}
