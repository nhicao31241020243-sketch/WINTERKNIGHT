package dyrvania.screens;

import java.awt.Graphics;

import dyrvania.Game;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameStatus;
import dyrvania.gui.GameButton;
import dyrvania.gui.GameText;
import dyrvania.resources.GameFont;
import dyrvania.strings.StringScreen;

public class Credits extends Screen {

	public Credits(Game game) {
		super(game, StringScreen.CREDITS.getValue());

		this.addText("Cao Ha Nhi", 120);
		this.addText("GitHub: https://github.com/JulioEvencio", 150);
		this.addText("Phan Le Hieu", 190);
		this.addText("Tran Trung Nghia", 230);
		this.addText("Dinh Sy Hoang Quan", 270);
		this.addText("Huynh Gia Tin", 310);
		this.addText("DAI HOC KINH TE TP. HCM - UEH", 350);
		this.addText("ClassOverFlow", 390);

		int rightX = game.getGameWidth() - GameButton.getWidth() - 25;

		super.buttons.add(new GameButton(game, StringScreen.BACK.getValue(), rightX, 380, () -> game.setGameStatus(game.getLastGameStatus())));
	}

	private void addText(String text, int y) {
		Graphics render = super.game.getRender();

		render.setFont(GameFont.getSmall());

		int centerX = 25;

		super.texts.add(new GameText(text, centerX, y, GameColors.WHITE, GameFont.getSmall()));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.CREDITS;
	}

}