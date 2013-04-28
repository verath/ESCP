package se.chalmers.tda367.group15.game.event;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for handling sending and listening for events.
 * 
 * @author Peter
 * 
 */
public class EventHandler {
	/**
	 * A list of eventListeners that should be notified of new events.
	 */
	private List<EventListener<?>> eventListeners = new LinkedList<>();

	/**
	 * Publish a new event to all listeners.
	 * 
	 * @param event
	 */
	public <T extends Event> void publish(T event) {
		for (EventListener<?> listener : eventListeners) {
			listener.onEvent(event);
		}
	}

	/**
	 * Adds a listener that should receive events.
	 * 
	 * @param listener
	 *            The listener to add. Should not be null.
	 */
	public <T extends Event> void addListener(EventListener<T> listener) {
		eventListeners.add(listener);
	}

	/**
	 * Removes a listener, making it no longer receive events.
	 * 
	 * @param listener
	 */
	public void removeListener(EventListener<?> listener) {
		eventListeners.remove(listener);
	}

}
