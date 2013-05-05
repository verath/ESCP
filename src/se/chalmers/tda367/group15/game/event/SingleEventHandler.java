package se.chalmers.tda367.group15.game.event;

import java.util.LinkedList;
import java.util.List;

/**
 * An instantiated implementation of the EventHandler interface.
 * 
 * @author Peter
 * @see EventHandler
 */
public class SingleEventHandler implements EventHandler {
	/**
	 * A list of eventListeners that should be notified of new events.
	 */
	private List<EventListener<?>> eventListeners = new LinkedList<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Event> void publish(T event) {
		for (EventListener<?> listener : eventListeners) {
			listener.onEvent(event);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Event> void addListener(EventListener<T> listener) {
		eventListeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(EventListener<?> listener) {
		eventListeners.remove(listener);
	}

}
