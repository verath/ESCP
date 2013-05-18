package se.chalmers.tda367.group15.game.states;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.database.DatabaseScore;
import se.chalmers.tda367.group15.game.database.GameDatabase;
import se.chalmers.tda367.group15.game.menu.Button;
import se.chalmers.tda367.group15.game.settings.Constants;

/**
 * State for showing the high scores
 * 
 * @author Peter
 * 
 */
public class MenuStateHighScore extends AbstractMenuBasedState {

	/*
	 * the upper left corner of the highscore list
	 */
	private static final int HIGH_SCORE_LIST_X = 40;
	private static final int HIGH_SCORE_LIST_Y = 170;

	/**
	 * Horizontal spacing between entries
	 */
	private static final int HIGH_SCORE_LIST_SPACING = 30;

	/**
	 * The spacing vertically between the name and the score.
	 */
	private static final int HIGH_SCORE_LIST_WIDTH = 200;

	/**
	 * The number of high score entries to show
	 */
	private static final int NUM_HIGH_SCORE_ENTRIES = 10;

	/**
	 * A placeholder name for when the high score is less than
	 * NUM_HIGH_SCORE_ENTIES.
	 */
	private final static String DEFAULT_NAME = "<No One>";

	/**
	 * A placeholder score for when the high score is less than
	 * NUM_HIGH_SCORE_ENTIES.
	 */
	private final static String DEFAULT_SCORE = "----";

	/**
	 * A list of sorted scores from the database
	 */
	private List<DatabaseScore> highScores;

	/**
	 * Creates a new MenuStateHighScore.
	 * 
	 * @param id
	 *            The int used to identify the state.
	 */
	public MenuStateHighScore(int id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		try {
			setBackground(new Image("res/menu/backgroundScores.png"));
			addBackButton();
		} catch (SlickException e) {
			if (Constants.DEBUG) {
				e.printStackTrace();
			}
		}
        updateHighScoreList();
	}

    @Override
    protected void initMenuItems() {
    }

    @Override
	public void render(Graphics g) {
		super.render(g);

		// Print the highscores
		if (highScores != null) {
			for (int i = 0; i < NUM_HIGH_SCORE_ENTRIES; i++) {
				String score, name;

				// Get the name or a placeholder value if not existing
				if (i < highScores.size()) {
					score = Integer.toString(highScores.get(i).getScore());
					name = highScores.get(i).getName();
				} else {
					score = DEFAULT_SCORE;
					name = DEFAULT_NAME;
				}

				// Don't allow too long names
				if (name.length() > 15) {
					name = name.substring(0, 15) + "...";
				}

				// Add ranking to the name, easier to align than if it were a
				// separate text string
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

	private void addBackButton() throws SlickException {

		Image backImage = new Image("res/menu/returnButton.png");
		Image backImageMO = new Image("res/menu/returnButtonMO.png");

		// Button for returning to main menu.
		Button returnButton = new Button(container, backImage, backImageMO,
				Constants.MENU_UPPER_X+50, Constants.MENU_UPPER_Y) {
			@Override
			public void performAction() {
				game.enterState(Constants.GAME_STATE_MENU_MAIN);
			}
		};

		addMenuItem(returnButton);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void escpAction() {
		// escape returns you to main menu.
		game.enterState(Constants.GAME_STATE_MENU_MAIN);
	}

	@Override
	public void gameOver(boolean win) {
		updateHighScoreList();
	}

    /**
     * Forces a refresh of the displayed high score list
     */
    public void updateHighScoreList() {
        GameDatabase db;
        try {
            db = new GameDatabase();
            highScores = db.getHighscores(NUM_HIGH_SCORE_ENTRIES);
        } catch (ClassNotFoundException e) {
            if (Constants.DEBUG) {
                e.printStackTrace();
            }
        }
    }

}
