package se.chalmers.tda367.group15.game.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EventLoggerTest {

	private class SubEvent extends Event {

	}

	/*
	 * Test setting up an event logger and making sure it logs events
	 */
	@Test
	public final void testSetupEventLogger() {
		SingleEventHandler evtHandler = new SingleEventHandler();
		EventLogger logger = new EventLogger(evtHandler);

		assertTrue(logger.getLoggedEvents().size() == 0);
		Event evt = new Event();
		evtHandler.publish(evt);
		assertTrue(logger.getLoggedEvents().size() == 1);
		assertEquals(logger.getLoggedEvents().get(0), evt);
	}

	/*
	 * Tests changing the event handler
	 */
	@Test
	public final void testChangeEventHandler() {
		SingleEventHandler evtHandler1 = new SingleEventHandler();
		SingleEventHandler evtHandler2 = new SingleEventHandler();
		EventLogger logger = new EventLogger(evtHandler1);

		logger.setEventHandler(evtHandler2);

		evtHandler1.publish(new Event());
		assertTrue(logger.getLoggedEvents().size() == 0);

		evtHandler2.publish(new Event());
		assertTrue(logger.getLoggedEvents().size() == 1);
	}

	/*
	 * Tests that all subclasses of Event are registered
	 */
	@Test
	public final void testSubClassesLogged() {
		SingleEventHandler evtHandler = new SingleEventHandler();
		EventLogger logger = new EventLogger(evtHandler);

		evtHandler.publish(new SubEvent());
		assertTrue(logger.getLoggedEvents().size() == 1);
		assertTrue(logger.getLoggedEvents().get(0).getClass() == SubEvent.class);
	}

	/*
	 * Tests iterating through logged events
	 */
	@Test
	public final void testIterating() {
		SingleEventHandler evtHandler = new SingleEventHandler();
		EventLogger logger = new EventLogger(evtHandler);

		Event evt1 = new Event();
		Event evt2 = new SubEvent();

		evtHandler.publish(evt1);
		evtHandler.publish(evt2);

		for (Event e : logger) {
			assertTrue(e.equals(evt1) || e.equals(evt2));
		}
	}

	/*
	 * Tests clearing the logger removes all logged events. Duh
	 */
	@Test
	public final void testClear() {
		SingleEventHandler evtHandler = new SingleEventHandler();
		EventLogger logger = new EventLogger(evtHandler);

		evtHandler.publish(new Event());
		logger.clear();

		assertTrue(logger.getLoggedEvents().size() == 0);

	}

}
