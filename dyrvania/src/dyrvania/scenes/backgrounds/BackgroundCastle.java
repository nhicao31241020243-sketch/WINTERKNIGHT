package dyrvania.scenes.backgrounds;

import dyrvania.Game;

public class BackgroundCastle extends Background {

    public BackgroundCastle(Game game, int x, int y) {
        super(x, y, game.getGameWidth(), game.getGameHeight(), debugGetMenuBG());
    }

    private static java.awt.image.BufferedImage debugGetMenuBG() {
        System.out.println("\n=== DEBUG MENU BACKGROUND ===");

        // 1. Kiểm tra manager
        BackgroundSpriteManager manager = BackgroundSpriteManager.getInstance();
        System.out.println("1. Manager: " + (manager != null ? "OK" : "NULL"));

        // 2. Kiểm tra đã load chưa
        System.out.println("2. Is loaded: " + manager.isLoaded());

        // 3. Thử lấy BG_01
        System.out.println("3. Getting BG_01...");
        java.awt.image.BufferedImage bg01 = manager.getSprite("BG_01");

        // 4. Kết quả
        System.out.println("4. BG_01 result: " +
                (bg01 != null ? bg01.getWidth() + "x" + bg01.getHeight() : "NULL"));

        if (bg01 == null) {
            System.err.println("❌ BG_01 IS NULL!");
            System.err.println("   Checking other sprites...");

            // Thử các sprite khác
            String[] testSprites = {"BG_02", "BG_03", "BG_04", "3", "10", "5"};
            for (String name : testSprites) {
                java.awt.image.BufferedImage test = manager.getSprite(name);
                System.out.println("   " + name + ": " +
                        (test != null ? test.getWidth() + "x" + test.getHeight() : "NULL"));
            }

            return createErrorBG();
        }

        System.out.println("✅ BG_01 loaded successfully!");
        return bg01;
    }

    private static java.awt.image.BufferedImage createErrorBG() {
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
                800, 600, java.awt.image.BufferedImage.TYPE_INT_RGB
        );

        java.awt.Graphics2D g = img.createGraphics();
        g.setColor(java.awt.Color.RED);
        g.fillRect(0, 0, 800, 600);

        g.setColor(java.awt.Color.WHITE);
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        g.drawString("MENU BG ERROR", 250, 200);
        g.drawString("BG_01 not loaded", 250, 250);

        g.dispose();
        return img;
    }
}