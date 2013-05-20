package se.chalmers.tda367.group15.game.database;

import org.junit.Test;
import se.chalmers.tda367.group15.game.event.Event;

import static org.junit.Assert.assertTrue;

public class InsertableEventTest {
	private class TestEvent extends Event {
	}

	/*
	 * Tests creating an event without a type.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateNullTypeEvent() {
		String s = null;
		new InsertableEvent(s);
	}

	/*
	 * Tests creating an InsertableEvent from an Event
	 */
	@Test
	public final void testCreateInsertableEventFromEvent() {
		TestEvent evt = new TestEvent();
		InsertableEvent inEvt = new InsertableEvent(evt.getClass()
				.getSimpleName());
		assertTrue(inEvt.getEventType().equals("TestEvent"));
	}

}
