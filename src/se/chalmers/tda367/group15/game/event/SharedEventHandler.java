package se.chalmers.tda367.group15.game.event;

/**
 * A singleton implementation of the EventHandler interface, allowing for a
 * shared EventHandler.
 * 
 * @author Peter
 * @see EventHandler
 * 
 */
public enum SharedEventHandler implements EventHandler {
	/**
	 * The SharedEventHandler instance.
	 */
	INSTANCE;

	/**
	 * A single instance of a SingleEventHandler. Used as the back-end of this
	 * implementation.
	 */
	private final static EventHandler eventHandler = new SingleEventHandler();

	private SharedEventHandler() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Event> void publish(T event) {
		eventHandler.publish(event);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Event> void addListener(EventListener<T> listener) {
		eventHandler.addListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(EventListener<?> listener) {
		eventHandler.removeListener(listener);
	}
}
