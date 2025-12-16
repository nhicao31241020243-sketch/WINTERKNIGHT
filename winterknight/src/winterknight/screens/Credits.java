package winterknight.screens;

import java.awt.Graphics;

import winterknight.Game;
import winterknight.generics.GameColors;
import winterknight.generics.GameStatus;
import winterknight.gui.GameButton;
import winterknight.gui.GameText;
import winterknight.resources.GameFont;
import winterknight.strings.StringScreen;

public class Credits extends Screen {

	public Credits(Game game) {
		super(game, StringScreen.CREDITS.getValue());

		this.addText("GitHub: https://github.com/nhicao31241020243-sketch/ICEAGE2025.git", 120);
		this.addText("Cao Hà Nhi - 31241020243", 150);
		this.addText("Huỳnh Gia Tín - 31241020795", 190);
		this.addText("Phan Lê Hiếu - 31241020557", 230);
		this.addText("Đinh Sỹ Hoàng Quân - 31241024009", 270);
		this.addText("Trần Trung Nghĩa - 31241024610", 310);
		this.addText("ĐẠI HỌC KINH TẾ TP. HCM - UEH", 350);
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