package winterknight.screens;

import java.awt.Graphics;

import winterknight.Game;
import winterknight.generics.GameColors;
import winterknight.generics.GameStatus;
import winterknight.gui.GameButton;
import winterknight.gui.GameText;
import winterknight.resources.GameFont;
import winterknight.saves.GameSaveManager;
import winterknight.scenes.levels.SaveLeft;
import winterknight.scenes.levels.SaveRight;
import winterknight.scenes.levels.Tutorial;
import winterknight.scenes.objects.Teleport;
import winterknight.strings.StringGame;
import winterknight.strings.StringScreen;

public class MainMenu extends Screen {

	public MainMenu(Game game) {
		super(game, StringGame.TITLE.getValue());

		int centerX = (game.getGameWidth() - GameButton.getWidth()) / 2;
		int leftX = game.getGameWidth() / 2 - 25 - GameButton.getWidth();
		int rightX = game.getGameWidth() / 2 + 25;

		super.buttons.add(new GameButton(game, StringScreen.NEW_GAME.getValue(), leftX, 120, () -> {
			GameSaveManager.loadData();

			if (GameSaveManager.saveIsEmpty()) {
				game.initializeScene(new Tutorial(game, null));
				game.setGameStatus(GameStatus.LORE);
			} else {
				game.setGameStatus(GameStatus.CONFIRM_NEW_GAME);
			}
		}));

		super.buttons.add(new GameButton(game, StringScreen.LOAD_GAME.getValue(), rightX, 120, () -> {
			GameSaveManager.loadData();

			if (GameSaveManager.saveIsEmpty()) {
				game.setGameStatus(GameStatus.NO_DATA);
			} else {
				if (GameSaveManager.getSave().isSceneSaveRight()) {
					game.initializeScene(new SaveRight(game, new Teleport(0, 0, 0xFF0000FF, false), GameSaveManager.getSave().getLastScene()));
				} else {
					game.initializeScene(new SaveLeft(game, new Teleport(0, 0, 0xFFFF006C, false), GameSaveManager.getSave().getLastScene()));
				}

				game.setTransition(GameStatus.RUN);
			}
		}));

		super.buttons.add(new GameButton(game, StringScreen.CREDITS.getValue(), leftX, 220, () -> game.setGameStatus(GameStatus.CREDITS)));

		super.buttons.add(new GameButton(game, StringScreen.OPTIONS.getValue(), rightX, 220, () -> {
			game.setGameStatus(GameStatus.OPTIONS);
		}));

		super.buttons.add(new GameButton(game, StringScreen.EXIT.getValue(), centerX, 320, () -> game.setGameStatus(GameStatus.EXIT)));

		Graphics render = game.getRender();

		render.setFont(GameFont.getSmall());

		int fullScreenWidth = render.getFontMetrics().stringWidth(StringScreen.TUTORIAL_FULL_SCREEN.getValue());

		super.texts.add(new GameText(StringScreen.TUTORIAL_FULL_SCREEN.getValue(), (game.getGameWidth() - fullScreenWidth) / 2, 420, GameColors.WHITE, GameFont.getSmall()));
	}

	@Override
	public GameStatus getGameStatus() {
		return GameStatus.MAIN_MENU;
	}

}