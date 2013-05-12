package se.chalmers.tda367.group15.game.states;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.database.DatabaseScore;
import se.chalmers.tda367.group15.game.database.PsychoHeroDatabase;

/**
 * A game over state. Displays a high score and allows the user to either replay
 * or go back to menu.
 * 
 * @author Peter
 * 
 */
public class GameOverState extends AbstractMenuBasedState {

	/*
	 * the upper left corner of the highscore list
	 */
	private static final int HIGH_SCORE_LIST_X = 40;
	private static final int HIGH_SCORE_LIST_Y = 170;

	/*
	 * Horizontal spacing between entries
	 */
	private static final int HIGH_SCORE_LIST_SPACING = 30;

	/*
	 * 
	 */
	private static final int HIGH_SCORE_LIST_WIDTH = 200;

	/*
	 * The number of high score entries to show
	 */
	private static final int NUM_HIGH_SCORE_ENTRIES = 5;

	private long last_db_query = 0;

	private List<DatabaseScore> highScores;

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
		try {
			background = new Image("res/menu/gameOver.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		// Don't query the database more than once every 20 sec or so.
		long now = System.currentTimeMillis();
		if (now - last_db_query > 1000 * 20) {
			last_db_query = now;
			PsychoHeroDatabase db;
			try {
				db = new PsychoHeroDatabase();
				highScores = db.getHighscores(5);
			} catch (ClassNotFoundException e) {
			}
		}

		// Print the highscores
		if (highScores != null) {
			for (int i = 0; i < NUM_HIGH_SCORE_ENTRIES; i++) {
				String score = "----";
				String name = "<No One>";
				if (i < highScores.size()) {
					score = Integer.toString(highScores.get(i).getScore());
					name = highScores.get(i).getName();
				}

				if (name.length() > 15) {
					name = name.substring(0, 15) + "...";
				}

				name = Integer.toString(i + 1) + ". " + name;
				g.getFont().drawString(HIGH_SCORE_LIST_X,
						HIGH_SCORE_LIST_Y + HIGH_SCORE_LIST_SPACING * i, name,
						Color.white);
				g.getFont().drawString(
						HIGH_SCORE_LIST_X + HIGH_SCORE_LIST_WIDTH,
						HIGH_SCORE_LIST_Y + HIGH_SCORE_LIST_SPACING * i, score,
						Color.white);
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initMenuItems() {
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
