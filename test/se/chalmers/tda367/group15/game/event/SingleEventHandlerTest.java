package se.chalmers.tda367.group15.game.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test for the SharedEventHandler class. Not as thorough as the test for the
 * SingleEventHandler, as the SharedEventHandler is using the SingleEventHandler
 * in its implementation.
 * 
 * @author Peter
 * 
 */
public class SingleEventHandlerTest {

	/*
	 * Set up a custom event. Sub-classing events is tested in the
	 * SingleEventHandlerTest
	 */
	// The super event is directly extending the Event superclass
    private class SuperEvent extends Event {
	}

	/*
	 * Testing that a listener for SuperEvents receive SuperEvents when
	 * published.
	 */
	@Test
	public final void testEventPublish() {
		EventHandler eh = SharedEventHandler.INSTANCE;

		final SuperEvent event = new SuperEvent();

		EventListener<SuperEvent> el = new AbstractEventListener<SuperEvent>(
				SuperEvent.class) {
			@Override
			public void onEvent(SuperEvent evt) {
				// Make sure the event we get here is the same as we sent
				assertEquals(event, evt);
			}
		};

		eh.addListener(el);
		eh.publish(event);

		// Using a singleton here, have to un-set listeners
		// after each test!
		eh.removeListener(el);
	}
	
	/*
	 * Test that listeners to the SharedEH doesn't receive events published by other handlers 
	 */
	@Test
	public final void testOnlyReceiveOwnPublishedEvents(){
		EventHandler eh = SharedEventHandler.INSTANCE;
		EventHandler eh2 = new SingleEventHandler();

		final SuperEvent event = new SuperEvent();

		EventListener<SuperEvent> el = new AbstractEventListener<SuperEvent>(
				SuperEvent.class) {
			@Override
			public void onEvent(SuperEvent evt) {
				// We should not get an event here!
				fail();
			}
		};

		eh.addListener(el);
		eh2.publish(event);

		// Using a singleton here, have to un-set listeners
		// after each test!
		eh.removeListener(el);
	}

}
