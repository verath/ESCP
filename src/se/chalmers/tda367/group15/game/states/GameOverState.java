package se.chalmers.tda367.group15.game.states;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.database.DatabaseScore;
import se.chalmers.tda367.group15.game.database.PsychoHeroDatabase;
import se.chalmers.tda367.group15.game.menu.Button;

/**
 * A game over state. Displays a high score and allows the user to either replay
 * or go back to menu.
 * 
 * @author Peter
 * 
 */
public class GameOverState extends AbstractMenuBasedState {

	/**
	 * the upper left corner of button group
	 */
	private int MENUX = 200;
	private int MENUY = 100;

	/**
	 * Creates a new GameOverState.
	 * 
	 * @param id
	 *            The int used to identify the state.
	 */
	public GameOverState(int id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() {

	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		PsychoHeroDatabase db;
		try {
			db = new PsychoHeroDatabase();
			List<DatabaseScore> scores = db.getHighscores(5);
			for (int i = 0; i < scores.size(); i++) {
				DatabaseScore score = scores.get(i);
				g.getFont().drawString(200, 300 + 30 * i,
						score.getName() + " " + score.getScore(), Color.white);
			}
		} catch (ClassNotFoundException e) {
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initMenuItems() {
		initButtons();
	}

	/**
	 * Create the buttons used in this state.
	 */
	private void initButtons() {
		try {
			Image backImage = new Image("res/menu/returnButton.png");
			Image backImageMO = new Image("res/menu/returnButtonMO.png");

			// Button for returning to main menu.
			Button returnButton = new Button(container, backImage, backImageMO,
					MENUX, MENUY) {
				@Override
				public void performAction() {
					game.enterState(Constants.GAME_STATE_MAIN_MENU);
				}
			};
			this.addMenuItem(returnButton);

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void escpAction() {
		// escape returns you to main menu.
		game.enterState(Constants.GAME_STATE_MAIN_MENU);
	}

}
