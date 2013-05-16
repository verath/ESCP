package se.chalmers.tda367.group15.game.database;


/**
 * A class representing an event that can be inserted into the database
 * 
 * @author Peter
 * 
 */
public class InsertableEvent {
	/**
	 * The name of the type of event this object is representing.
	 */
	private final String eventType;

	/**
	 * Creates a new InsertableEvent.
	 * 
	 * @param eventType
	 *            The string name of the type of event. Must not be null.
	 */
	public InsertableEvent(final String eventType) {
		if (eventType == null) {
			throw new IllegalArgumentException("eventType can not be null");
		}
		this.eventType = eventType;
	}

	/**
	 * Getter for the event type this object is representing.
	 * 
	 * @return The String representation of the type of the Event.
	 */
	public String getEventType() {
		return eventType;
	}
}
