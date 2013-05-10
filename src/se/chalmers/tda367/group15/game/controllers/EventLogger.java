package se.chalmers.tda367.group15.game.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.database.InsertableEvent;
import se.chalmers.tda367.group15.game.database.PsychoHeroDatabase;
import se.chalmers.tda367.group15.game.event.AbstractEventListener;
import se.chalmers.tda367.group15.game.event.Event;
import se.chalmers.tda367.group15.game.event.EventHandler;

/**
 * A class for listening to events and inserting them into the database.
 * 
 * @author Peter
 * 
 */
public class EventLogger {
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
	private final List<InsertableEvent> loggedEvents;

	/**
	 * Creates a new EventLogger, listening to event on the provided
	 * EventHandler
	 * 
	 * @param eventHandler
	 */
	public EventLogger(EventHandler eventHandler) {
		this.loggedEvents = new LinkedList<InsertableEvent>();
		this.eventListener = new EventListener();
		this.setEventHandler(eventHandler);
	}

	/**
	 * Sets the EventHander this EventLogger is listening to.
	 * 
	 * @param eventHandler
	 */
	public void setEventHandler(EventHandler eventHandler) {
		if (this.eventHandler != null && eventListener != null) {
			this.eventHandler.removeListener(eventListener);
		}
		eventHandler.addListener(eventListener);
		this.eventHandler = eventHandler;
	}

	/**
	 * Saves all logged events to the database. Should be called when the game
	 * is not currently active, as it might take some time.
	 */
	public void saveEvents() {
		PsychoHeroDatabase db;
		try {
			db = new PsychoHeroDatabase();
		} catch (ClassNotFoundException e) {
			System.err
					.println("Could not connect to the database. Make sure you have the org.sqlite.JDBC library.");
			return;
		}

		if (Constants.DEBUG) {
			System.out.println("=== Saving " + loggedEvents.size()
					+ " events ===");
			Map<String, Integer> eventMap = new HashMap<>();
			for (InsertableEvent evt : loggedEvents) {
				int numEvents = 1;
				if (eventMap.containsKey(evt.getEventType())) {
					numEvents += eventMap.get(evt.getEventType());
				}
				eventMap.put(evt.getEventType(), numEvents);
			}

			System.out.println("Num\t\tEvt Type");
			for (String evtType : eventMap.keySet()) {
				System.out.println(String.format("%d\t\t%s",
						eventMap.get(evtType), evtType));
			}

		}

		db.addEvents(loggedEvents);
		loggedEvents.clear();

		if (Constants.DEBUG) {
			System.out.println("=== Events saved ===");
			System.out.println();
		}
	}

	/**
	 * An inner class listening for Event.
	 * 
	 * @author Peter
	 * 
	 */
	private class EventListener extends AbstractEventListener<Event> {
		public EventListener() {
			super(Event.class);
		}

		@Override
		public void onEvent(Event event) {
			loggedEvents.add(new InsertableEvent(event));
		}

	}
}
