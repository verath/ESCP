package se.chalmers.tda367.group15.game.controllers;

import java.util.LinkedList;
import java.util.List;

import se.chalmers.tda367.group15.game.database.GameDatabase;
import se.chalmers.tda367.group15.game.database.InsertableEvent;
import se.chalmers.tda367.group15.game.event.Event;
import se.chalmers.tda367.group15.game.event.EventHandler;
import se.chalmers.tda367.group15.game.event.EventLogger;
import se.chalmers.tda367.group15.game.settings.Constants;

/**
 * A wrapper class around the EventLogger providing a method to save the
 * currently logged events to the database
 * 
 * @author Peter
 * 
 */
public class EventLoggerController extends EventLogger {

	public EventLoggerController(EventHandler eventHandler) {
		super(eventHandler);
	}

	/**
	 * Saves the currently logged events to the database and resets the logger.
	 */
	public void saveEvents() {
		// If this is a new game. Save and clear events instead
		List<InsertableEvent> insertEvts = new LinkedList<InsertableEvent>();
		for (Event evt : this) {
			insertEvts.add(new InsertableEvent(evt.getClass().getSimpleName()));
		}

		try {
			GameDatabase db = new GameDatabase();
			db.addEvents(insertEvts);
		} catch (ClassNotFoundException e) {
			if (Constants.DEBUG) {
				System.err.println("Could not connect to the database. "
						+ "Make sure you have the org.sqlite.JDBC library.");
			}
		}

		this.clear();
	}

}
