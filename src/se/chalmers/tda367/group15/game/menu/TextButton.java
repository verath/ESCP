package se.chalmers.tda367.group15.game.menu;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

/**
 * A decorator class for Buttons, adding a changeable text string on top of the
 * image.
 * 
 * @author Peter
 * 
 */
public class TextButton implements MenuItem {
	/**
	 * The default font used to display the text.
	 */
	public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 32);

	/**
	 * The default color used to display the text.
	 */
	public static final Color DEFAULT_COLOR = Color.black;

	/**
	 * The button we are decorating
	 */
	private final Button button;

	/**
	 * The string currently displayed on the button
	 */
	private String text;

	/**
	 * The true typed font used to draw the text on the button.
	 */
	private TrueTypeFont textFont;

	/**
	 * The color used to draw the text.
	 */
	private Color color;

	/**
	 * The center x-coordinate of the button
	 */
	private final int centerX;

	/**
	 * The center Y-coordinate of the button
	 */
	private final int centerY;

	/**
	 * Creates a DynamicTextButton decorating the button. Using the provided
	 * string and the provided color and font.
	 * 
	 * @param button
	 * @param text
	 * @param color
	 * @param font
	 */
	public TextButton(Button button, String text, Color color, Font font) {
		this.button = button;
		setText(text);
		setFont(font);
		setColor(color);

		centerX = button.mouseOverArea.getX()
				+ (button.mouseOverArea.getWidth() / 2);
		centerY = button.mouseOverArea.getY()
				+ (button.mouseOverArea.getHeight() / 2);
	}

	/**
	 * Creates a DynamicTextButton decorating the button. Using the provided
	 * string, with provided color and default font.
	 * 
	 * @param button
	 * @param text
	 * @param color
	 */
	public TextButton(Button button, String text, Color color) {
		this(button, text, color, null);
	}

	/**
	 * Creates a DynamicTextButton decorating the button. Using the provided
	 * string, with default color and default font.
	 * 
	 * @param button
	 * @param text
	 */
	public TextButton(Button button, String text) {
		this(button, text, null);
	}

	@Override
	public void render(Graphics g) {
		button.render(g);

		if (text != null && !text.isEmpty()) {
			int textWidth = textFont.getWidth(text);
			int textHeight = textFont.getHeight(text);
			textFont.drawString(centerX - textWidth / 2, centerY - textHeight
					/ 2, text, color);
		}
	}

	@Override
	public void performAction() {
		button.performAction();
	}

	@Override
	public boolean isMouseOver() {
		return button.isMouseOver();
	}

	/**
	 * Sets the font used to draw the text on the button. If null, DEFAULT_FONT
	 * will be used.
	 * 
	 * @param font
	 */
	public final void setFont(Font font) {
		font = (font == null) ? TextButton.DEFAULT_FONT : font;
		textFont = new TrueTypeFont(font, true);
	}

	/**
	 * Sets the color used to draw the text. If null the DEFAULT_COLOR is used.
	 * 
	 * @param color
	 */
	public final void setColor(Color color) {
		this.color = (color == null) ? TextButton.DEFAULT_COLOR : color;
	}

	/**
	 * Sets the text displayed on the button. If null, an empty string will be
	 * used.
	 * 
	 * @param text
	 */
	public final void setText(final String text) {
		this.text = text;
	}

	/**
	 * Returns the currently displayed text.
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}
}
