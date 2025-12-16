package winterknight.screens;

import winterknight.Game;
import winterknight.generics.GameColors;
import winterknight.generics.GameStatus;
import winterknight.gui.GameButton;
import winterknight.gui.GameText;
import winterknight.resources.GameFont;
import winterknight.strings.StringLevel;
import winterknight.strings.StringScreen;

public class Options extends Screen {

	public Options(Game game) {
		super(game, StringScreen.OPTIONS.getValue());

		super.texts.add(new GameText(StringScreen.TUTORIAL_FULL_SCREEN.getValue(), 20, 150, GameColors.WHITE, GameFont.getSmall()));
		super.texts.add(new GameText(StringLevel.TUTORIAL_FPS.getValue(), 20, 180, GameColors.WHITE, GameFont.getSmall()));
		super.texts.add(new GameText(StringLevel.TUTORIAL_MUSIC.getValue(), 20, 210, GameColors.WHITE, GameFont.getSmall()));

		int centerX = (game.getGameWidth() - GameButton.getWidth()) / 2;

		super.buttons.add(new GameButton(game, StringScreen.BACK.getValue(), centerX, 350, () -> game.setGameStatus(game.getLastGameStatus())));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.OPTIONS;
	}

}