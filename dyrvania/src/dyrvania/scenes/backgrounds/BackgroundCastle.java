package dyrvania.scenes.backgrounds;

import dyrvania.Game;

public class BackgroundCastle extends Background {

    public BackgroundCastle(Game game, int x, int y) {
        super(x, y, game.getGameWidth(), game.getGameHeight(),
                getCastleBackground());
    }

    private static java.awt.image.BufferedImage getCastleBackground() {
        // Lấy từ sprite manager
        BackgroundSpriteManager manager = BackgroundSpriteManager.getInstance();
        return manager.getSprite("BG_01"); // Hoặc BG_02, BG_03...
    }
}
