package winterknight.screens;

import java.awt.Graphics;

import winterknight.Game;
import winterknight.generics.GameColors;
import winterknight.generics.GameStatus;
import winterknight.gui.GameButton;
import winterknight.gui.GameText;
import winterknight.resources.GameFont;
import winterknight.strings.StringGame;
import winterknight.strings.StringScreen;

public class ConfirmMainMenu extends Screen {

	public ConfirmMainMenu(Game game) {
		super(game, StringGame.TITLE.getValue());

		Graphics render = game.getRender();

		render.setFont(GameFont.getDefault());

		int centerX = render.getFontMetrics().stringWidth(StringScreen.CONFIRM_MAIN_MENU.getValue());

		super.texts.add(new GameText(StringScreen.CONFIRM_MAIN_MENU.getValue(), (game.getGameWidth() - centerX) / 2, 160, GameColors.WHITE, GameFont.getDefault()));

		int leftX = game.getGameWidth() / 2 - 25 - GameButton.getWidth();
		int rightX = game.getGameWidth() / 2 + 25;

		super.buttons.add(new GameButton(game, StringScreen.YES.getValue(), leftX, 220, () -> {
			game.setGameStatus(GameStatus.MAIN_MENU);
		}));
		super.buttons.add(new GameButton(game, StringScreen.NO.getValue(), rightX, 220, () -> game.setGameStatus(game.getLastGameStatus())));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.CONFIRM_MAIN_MENU;
	}

}