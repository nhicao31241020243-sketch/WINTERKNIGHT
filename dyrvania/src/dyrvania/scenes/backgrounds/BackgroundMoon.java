package dyrvania.scenes.backgrounds;

import dyrvania.Game;

public class BackgroundMoon extends Background {

    public BackgroundMoon(Game game, int x, int y) {
        super(x, y, game.getGameWidth(), game.getGameHeight(), loadBG02());
    }

    private static java.awt.image.BufferedImage loadBG02() {
        System.out.println("🎯 Loading BG_02...");

        // Lấy BG_02 trực tiếp
        java.awt.image.BufferedImage bg02 = BackgroundSpriteManager.getInstance().getSprite("BG_02");

        if (bg02 == null || bg02.getWidth() <= 10) {
            System.err.println("❌ Failed! Creating simple background");
            return createSimpleBG();
        }

        System.out.println("✅ BG_02 ready: " + bg02.getWidth() + "x" + bg02.getHeight());
        return bg02;
    }

    private static java.awt.image.BufferedImage createSimpleBG() {
        // Background đơn giản để test
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
                800, 600, java.awt.image.BufferedImage.TYPE_INT_RGB
        );

        java.awt.Graphics2D g = img.createGraphics();
        g.setColor(java.awt.Color.BLUE);
        g.fillRect(0, 0, 800, 600);

        g.setColor(java.awt.Color.YELLOW);
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        g.drawString("TEST BACKGROUND", 250, 300);

        g.dispose();
        return img;
    }
}