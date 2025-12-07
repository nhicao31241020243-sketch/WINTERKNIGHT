package dyrvania.scenes.backgrounds;


import dyrvania.Game;


public class BackgroundMoon extends Background {


    public BackgroundMoon(Game game, int x, int y) {
        super(x, y, game.getGameWidth(), game.getGameHeight(), loadFrame15720());
    }


    private static java.awt.image.BufferedImage loadFrame15720() {
        System.out.println("🎯 Loading frame 15720...");


        // Lấy frame 15720 (640x400)
        java.awt.image.BufferedImage frame = BackgroundSpriteManager.getInstance().getSprite("15720");


        if (frame == null || frame.getWidth() <= 10) {
            System.err.println("❌ Failed to load frame 15720! Using BG_02 instead...");


            // Fallback: dùng BG_02
            java.awt.image.BufferedImage bg02 = BackgroundSpriteManager.getInstance().getSprite("BG_02");
            if (bg02 != null && bg02.getWidth() > 10) {
                System.out.println("✅ Using BG_02 as fallback: " + bg02.getWidth() + "x" + bg02.getHeight());
                return bg02;
            }


            System.err.println("❌ BG_02 also failed! Creating simple background");
            return createSimpleBG();
        }


        System.out.println("✅ Frame 15720 ready: " + frame.getWidth() + "x" + frame.getHeight());
        return frame;
    }


    private static java.awt.image.BufferedImage createSimpleBG() {
        // Background đơn giản để test
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(
                800, 600, java.awt.image.BufferedImage.TYPE_INT_RGB
        );


        java.awt.Graphics2D g = img.createGraphics();


        // Nền đêm với mặt trăng
        g.setColor(new java.awt.Color(10, 20, 40)); // Xanh đêm
        g.fillRect(0, 0, 800, 600);


        // Mặt trăng
        g.setColor(java.awt.Color.YELLOW);
        g.fillOval(600, 100, 80, 80);


        // Sao
        g.setColor(java.awt.Color.WHITE);
        for (int i = 0; i < 50; i++) {
            int x = (int)(Math.random() * 800);
            int y = (int)(Math.random() * 400);
            int size = 1 + (int)(Math.random() * 3);
            g.fillOval(x, y, size, size);
        }


        g.setColor(java.awt.Color.WHITE);
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        g.drawString("MOON BACKGROUND", 250, 300);


        g.dispose();
        return img;
    }
}

