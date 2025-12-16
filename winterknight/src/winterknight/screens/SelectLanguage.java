package winterknight.screens;

import java.awt.Graphics;

import winterknight.Game;
import winterknight.generics.GameColors;
import winterknight.generics.GameStatus;
import winterknight.gui.GameButton;
import winterknight.gui.GameText;
import winterknight.resources.GameFont;
import winterknight.resources.Translation;
import winterknight.resources.Translation.Language;
import winterknight.strings.StringGame;
import winterknight.strings.StringScreen;

public class SelectLanguage extends Screen {

	public SelectLanguage(Game game) {
		super(game, StringGame.TITLE.getValue());

		int centerX = (game.getGameWidth() - GameButton.getWidth()) / 2;

		super.buttons.add(new GameButton(game, StringScreen.ENGLISH.getValue(), centerX, 120, () -> this.selectLanguage(Language.ENGLISH, game)));
		super.buttons.add(new GameButton(game, StringScreen.VIETNAM.getValue(), centerX, 220, () -> this.selectLanguage(Language.VIETNAM, game)));
		Graphics render = game.getRender();

		render.setFont(GameFont.getSmall());

		int fullScreenWidth = render.getFontMetrics().stringWidth(StringScreen.TUTORIAL_FULL_SCREEN.getValue());

		super.texts.add(new GameText(StringScreen.TUTORIAL_FULL_SCREEN.getValue(), (game.getGameWidth() - fullScreenWidth) / 2, 420, GameColors.WHITE, GameFont.getSmall()));
	}

	private void selectLanguage(Language language, Game game) {
		Translation.changeTheLanguage(language);

		game.initializeScreen();
		game.setGameStatus(GameStatus.MAIN_MENU);
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.SELECT_LANGUAGE;
	}

}