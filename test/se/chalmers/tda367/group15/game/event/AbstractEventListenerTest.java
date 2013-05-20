package se.chalmers.tda367.group15.game.event;

import org.junit.Test;

public class AbstractEventListenerTest {

	/*
	 * Makes sure we get an exception when trying to create an
	 * AbstractEventListener with null as constructor parameter.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateAsNullClass() {
		new AbstractEventListener<Event>(null) {
			@Override
			public void onEvent(Event event) {
			}
		};
	}

}
