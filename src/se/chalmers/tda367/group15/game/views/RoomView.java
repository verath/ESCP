package se.chalmers.tda367.group15.game.views;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.AnimatedModel;
import se.chalmers.tda367.group15.game.models.Room;
import se.chalmers.tda367.group15.game.models.room.BasicRoom;
import se.chalmers.tda367.group15.game.models.room.RoomManager;

public class RoomView implements Renderable {

	private RoomManager roomManager;
	private TiledMap map;
	private List<Renderable> renderables = new ArrayList<Renderable>();
	private List<AnimatedModel> animatedObjs = new ArrayList<AnimatedModel>();
	private Renderable heroView;

	/**
	 * Create a new room view
	 * @param the room assigned to the view
	 * @param the path to the tiled map
	 */
	public RoomView() {
		roomManager = new RoomManager();
		roomManager.addStartingRoom(new BasicRoom());
		try {
			map = new TiledMap(roomManager.getCurrentRoom().getTiledMapPath());
		} catch (SlickException e) {
			System.out.println("HEJ");
			e.printStackTrace();
		}
		
		heroView = new HeroView();
		renderables.add(this);
		renderables.add(heroView);
		animatedObjs.add(((HeroView) heroView).getAnimatedModel());
	}

	/**
	 * Method for getting the TiledMap of a room
	 * 
	 * @return the TiledMap
	 */
	public TiledMap getTiledMap() {
		return map;
	}

	public void render() {
		map.render(0, 0);
	}
	
	/**
	 * Method for getting all the renderable object in the game.
	 * 
	 * @return renderable objects
	 */
	public List<Renderable> getRenderables() {
		return renderables;
	}

	/**
	 * Method for getting all the animated models in the game.
	 * 
	 * @return animated models
	 */
	public List<AnimatedModel> getAnimatedModels() {
		return animatedObjs;
	}
}
