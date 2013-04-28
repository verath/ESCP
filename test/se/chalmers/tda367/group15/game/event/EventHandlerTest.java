package se.chalmers.tda367.group15.game.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test for the event handling class, and by doing so also testing Events and
 * EventListener.
 * 
 * @author Peter
 * 
 */
public class EventHandlerTest {

	/*
	 * Set up two custom events
	 */
	// The super event is directly extending the Event superclass
	class SuperEvent extends Event {
	}

	// The sub event is extending the SuperEvent
	class SubEvent extends SuperEvent {
	}

	/*
	 * Testing that a listener for SuperEvents receive SuperEvents when
	 * published.
	 * 
	 * Also indirectly tests the addListener method (it doesn't get it's own
	 * test as it is only adding items to a list).
	 */
	@Test
	public final void testEventPublish() {
		EventHandler eh = new EventHandler();

		final SuperEvent event = new SuperEvent();

		eh.addListener(new AbstractEventListener<SuperEvent>(SuperEvent.class) {
			@Override
			public void onEvent(SuperEvent evt) {
				// Make sure the event we get here is the same as we sent
				assertEquals(event, evt);
			}
		});

		eh.publish(event);
	}

	/*
	 * Testing that removing an eventListener stops it from receiving calls.
	 */
	@Test
	public final void testRemoveEventListener() {
		EventHandler eh = new EventHandler();

		final SuperEvent event = new SuperEvent();

		EventListener<SuperEvent> el = new AbstractEventListener<SuperEvent>(
				SuperEvent.class) {
			@Override
			public void onEvent(SuperEvent evt) {
				// Should never get this, as we are supposed to be removed
				fail();
			}
		};

		eh.addListener(el);
		eh.removeListener(el);

		eh.publish(event);
	}

	/*
	 * Testing that a listener for SuperEvent also receives SubEvent
	 */
	@Test
	public final void testEventPublishSubEventTriggersSuperEventListener() {
		EventHandler eh = new EventHandler();

		final SubEvent event = new SubEvent();

		eh.addListener(new AbstractEventListener<SuperEvent>(SuperEvent.class) {
			@Override
			public void onEvent(SuperEvent evt) {
				// Make sure the event we get here is the same as we sent
				assertEquals(event, evt);
				// And make sure that it actually is a SubEvent
				assertTrue(evt instanceof SubEvent);
			}
		});

		eh.publish(event);
	}

	/*
	 * Testing that a listener for SubEvent doesn't receives SuperEvents
	 */
	@Test
	public final void testEventPublishSuperEventDoesNotTriggersSubEventListener() {
		EventHandler eh = new EventHandler();

		final SuperEvent event = new SuperEvent();

		eh.addListener(new AbstractEventListener<SubEvent>(SubEvent.class) {
			@Override
			public void onEvent(SubEvent evt) {
				// We shouldn't get an event here, as the event we are sending
				// is of type SuperEvent
				fail();
			}
		});

		eh.publish(event);
	}

}
