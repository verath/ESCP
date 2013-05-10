package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.models.ScoreModel;

public class HUDView implements View {
	private final ScoreModel scoreModel;

	public HUDView(ScoreModel scoreModel) {
		this.scoreModel = scoreModel;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		int currentScore = scoreModel.getScore();

		// Draw the score string, adjust for width of the text
		String scoreStr = "Score: " + currentScore;
		int scoreStrWidth = g.getFont().getWidth(scoreStr);

		g.setColor(Color.black);
		g.drawString(scoreStr, Constants.GAME_WIDTH - (scoreStrWidth + 6), 6);

		g.setColor(Color.white);
		g.drawString(scoreStr, Constants.GAME_WIDTH - (scoreStrWidth + 5), 5);

	}

}
