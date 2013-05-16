package se.chalmers.tda367.group15.game.event;

/**
 * An interface describing an event listener for a specific event. One does
 * probably not want to implement this directly, but rather extend the
 * AbstractEventListener class.
 *
 * @param <T> The event type to listen for.
 * @author Peter
 * @see AbstractEventListener
 */
interface EventListener<T extends Event> {

    /**
     * The method called by the EventHandler whenever an event occurs.
     *
     * @param event An event object that extends or is the class Event.
     */
    public abstract <E> void onEvent(E event);

}