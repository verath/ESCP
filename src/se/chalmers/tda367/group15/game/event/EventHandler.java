package se.chalmers.tda367.group15.game.event;

/**
 * An interface describing an EventHandler that can add and remove listeners,
 * and publish events to said listeners.
 * 
 * @author Peter
 */
public interface EventHandler {

	/**
	 * Publish a new event to all listeners associated to this handler.
	 * 
	 * @param event
	 *            An event that should be published. Must extend Event (@see
	 *            Event)
	 */
	public abstract <T extends Event> void publish(T event);

	/**
	 * Adds a listener that should receive events.
	 * 
	 * @param listener
	 *            The listener to add. Should not be null.
	 */
	public abstract <T extends Event> void addListener(EventListener<T> listener);

	/**
	 * Removes a listener, making it no longer receive events.
	 * 
	 * @param listener
	 *            The listener to remove.
	 */
	public abstract void removeListener(EventListener<?> listener);

}
