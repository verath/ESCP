package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.constants.Constants;

public class StateController extends StateBasedGame {

	public StateController(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		PlayState playState = new PlayState(Constants.GAME_STATE_PLAYING);
		MainMenuState mainMenu = new MainMenuState( Constants.GAME_STATE_MAIN_MENU );
		this.addState(playState);
		this.addState(mainMenu);
		this.enterState( Constants.GAME_STATE_MAIN_MENU );
	}

}
