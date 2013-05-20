package se.chalmers.tda367.group15.game.event;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A utility class for listening to all events sent via an event handler and
 * storing them for later use.
 * 
 * @author Peter
 */
public class EventLogger implements Iterable<Event> {
	/**
	 * The EventHandler this logger is listening to
	 */
	private EventHandler eventHandler;

	/**
	 * The listener for all events.
	 */
	private final EventListener eventListener;

	/**
	 * A list of events that has occurred but hasn't been inserted into the
	 * database.
	 */
	private final List<Event> loggedEvents;

	/**
	 * Creates a new EventLogger, listening to event on the provided
	 * EventHandler
	 * 
	 * @param eventHandler
	 *            The event handler to listen to events from.
	 */
	public EventLogger(EventHandler eventHandler) {
		this.loggedEvents = new LinkedList<Event>();
		this.eventListener = new EventListener();
		this.setEventHandler(eventHandler);
	}

	/**
	 * Sets the EventHandler this EventLogger is listening to.
	 * 
	 * @param eventHandler
	 *            The new EventHandler to use for listening to events.
	 */
	public void setEventHandler(EventHandler eventHandler) {
		if (this.eventHandler != null && eventListener != null) {
			this.eventHandler.removeListener(eventListener);
		}
		eventHandler.addListener(eventListener);
		this.eventHandler = eventHandler;
	}

	/**
	 * Clears the list of all logged events.
	 */
	public void clear() {
		loggedEvents.clear();
	}

	/**
	 * Returns a list of all logged events.
	 * 
	 * @return A list of all logged events.
	 */
	public List<Event> getLoggedEvents() {
		return new LinkedList<Event>(loggedEvents);
	}

	/**
	 * An inner class listening for Event.
	 * 
	 * @author Peter
	 */
	private class EventListener extends AbstractEventListener<Event> {
		public EventListener() {
			super(Event.class);
		}

		@Override
		public void onEvent(Event event) {
			loggedEvents.add(event);
		}

	}

	@Override
	public Iterator<Event> iterator() {
		return loggedEvents.iterator();
	}
}
