package dyrvania.scenes.backgrounds;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BackgroundSpriteManager {

    private static BackgroundSpriteManager instance;
    private BufferedImage spriteSheet;
    private boolean isLoaded = false;

    private BackgroundSpriteManager() {}

    public static BackgroundSpriteManager getInstance() {
        if (instance == null) {
            instance = new BackgroundSpriteManager();
        }
        return instance;
    }

    // LOAD SPRITE SHEET MỘT LẦN DUY NHẤT
    private void loadIfNeeded() {
        if (isLoaded) return;

        try {
            System.out.println("🚀 LOADING SPRITE SHEET...");

            // THỬ CÁC ĐƯỜNG DẪN - QUAN TRỌNG: CHỈNH CHO ĐÚNG
            File file = null;

            // THỬ TRỰC TIẾP FILE HIỆN TẠI
            file = new File("backgrounds.png");

            if (!file.exists()) {
                // Thử đường dẫn của bạn
                file = new File("resources/sprites/backgrounds.png");
            }

            if (!file.exists()) {
                // Thử trong thư mục project
                file = new File(System.getProperty("user.dir") + "/backgrounds.png");
            }

            System.out.println("📁 File path: " + file.getAbsolutePath());
            System.out.println("📁 Exists: " + file.exists());

            if (!file.exists()) {
                System.err.println("❌ CRITICAL: Cannot find backgrounds.png!");
                System.err.println("   Place backgrounds.png in: " + System.getProperty("user.dir"));
                return;
            }

            // LOAD ẢNH
            spriteSheet = ImageIO.read(file);
            System.out.println("✅ SPRITE SHEET LOADED!");
            System.out.println("   Size: " + spriteSheet.getWidth() + "x" + spriteSheet.getHeight());
            System.out.println("   Type: " + spriteSheet.getType());

            isLoaded = true;

        } catch (Exception e) {
            System.err.println("❌ LOAD FAILED: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(String spriteName) {
        // ĐẢM BẢO ĐÃ LOAD
        loadIfNeeded();

        if (spriteSheet == null) {
            System.err.println("❌ Sprite sheet is NULL!");
            return createErrorSprite(spriteName);
        }

        // LẤY TỌA ĐỘ
        int x = 0, y = 0, width = 0, height = 0;

        if (spriteName.equals("BG_01")) { x = 0; y = 1081; width = 1920; height = 1080; }
        else if (spriteName.equals("BG_02")) { x = 0; y = 2162; width = 1920; height = 1080; }
        else if (spriteName.equals("BG_03")) { x = 0; y = 3243; width = 1920; height = 1080; }
        else if (spriteName.equals("BG_04")) { x = 0; y = 0; width = 1920; height = 1080; }
        else if (spriteName.equals("3")) { x = 1921; y = 3568; width = 576; height = 324; }
        else if (spriteName.equals("10")) { x = 1921; y = 3243; width = 576; height = 324; }
        else if (spriteName.equals("5")) { x = 1921; y = 0; width = 576; height = 324; }
        else {
            System.err.println("❌ Unknown sprite: " + spriteName);
            return createErrorSprite(spriteName);
        }

        // KIỂM TRA TỌA ĐỘ
        if (x + width > spriteSheet.getWidth() || y + height > spriteSheet.getHeight()) {
            System.err.println("❌ COORDINATES OUT OF BOUNDS!");
            System.err.println("   Sprite: " + spriteName);
            System.err.println("   Requested: (" + x + "," + y + ") to (" + (x+width) + "," + (y+height) + ")");
            System.err.println("   Image size: " + spriteSheet.getWidth() + "x" + spriteSheet.getHeight());
            return createErrorSprite(spriteName);
        }

        try {
            BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
            System.out.println("✅ Cut " + spriteName + ": " + width + "x" + height);
            return sprite;

        } catch (Exception e) {
            System.err.println("❌ Error cutting " + spriteName + ": " + e.getMessage());
            return createErrorSprite(spriteName);
        }
    }

    private BufferedImage createErrorSprite(String name) {
        BufferedImage img = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g = img.createGraphics();

        g.setColor(java.awt.Color.RED);
        g.fillRect(0, 0, 200, 100);

        g.setColor(java.awt.Color.WHITE);
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        g.drawString("ERROR: " + name, 10, 30);
        g.drawString("Check console", 10, 60);

        g.dispose();
        return img;
    }

    public boolean isLoaded() {
        return isLoaded;
    }
}