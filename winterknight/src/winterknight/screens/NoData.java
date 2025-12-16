package winterknight.screens;

import java.awt.Graphics;

import winterknight.Game;
import winterknight.generics.GameColors;
import winterknight.generics.GameStatus;
import winterknight.gui.GameButton;
import winterknight.gui.GameText;
import winterknight.resources.GameFont;
import winterknight.strings.StringScreen;

public class NoData extends Screen {

	public NoData(Game game) {
		super(game, StringScreen.LOAD_GAME.getValue());

		Graphics render = game.getRender();

		render.setFont(GameFont.getDefault());

		int fullScreenWidth = render.getFontMetrics().stringWidth(StringScreen.NO_DATA.getValue());

		super.texts.add(new GameText(StringScreen.NO_DATA.getValue(), (game.getGameWidth() - fullScreenWidth) / 2, 180, GameColors.WHITE, GameFont.getDefault()));

		int centerX = (game.getGameWidth() - GameButton.getWidth()) / 2;

		super.buttons.add(new GameButton(game, StringScreen.BACK.getValue(), centerX, 220, () -> game.setGameStatus(game.getLastGameStatus())));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.NO_DATA;
	}

}