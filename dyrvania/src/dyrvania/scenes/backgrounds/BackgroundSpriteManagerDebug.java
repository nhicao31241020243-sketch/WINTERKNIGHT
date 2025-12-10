package dyrvania.scenes.backgrounds;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BackgroundSpriteManagerDebug {

    public static void main(String[] args) {
        System.out.println("🐛 DEBUG SPRITE SHEET");

        // Thử tất cả đường dẫn
        String[] paths = {
                "backgrounds.png",
                "resources/sprites/backgrounds.png",
                "sprites/backgrounds.png",
                new File(".").getAbsolutePath() + "/backgrounds.png"
        };

        for (String path : paths) {
            File file = new File(path);
            System.out.println("\n🔍 Testing: " + path);
            System.out.println("   Absolute: " + file.getAbsolutePath());
            System.out.println("   Exists: " + file.exists());

            if (file.exists()) {
                try {
                    BufferedImage img = ImageIO.read(file);
                    System.out.println("   ✅ Loaded: " + img.getWidth() + "x" + img.getHeight());
                    System.out.println("   Type: " + img.getType());

                    // Kiểm tra tọa độ BG_02
                    int x = 0, y = 2162, w = 1920, h = 1080;
                    if (x + w <= img.getWidth() && y + h <= img.getHeight()) {
                        System.out.println("   ✅ BG_02 coordinates are VALID");
                        BufferedImage bg02 = img.getSubimage(x, y, w, h);
                        System.out.println("   ✅ BG_02 cut successfully: " + bg02.getWidth() + "x" + bg02.getHeight());
                    } else {
                        System.out.println("   ❌ BG_02 coordinates OUT OF BOUNDS!");
                        System.out.println("      Image: " + img.getWidth() + "x" + img.getHeight());
                        System.out.println("      Need: (" + x + "," + y + ") to (" + (x+w) + "," + (y+h) + ")");
                    }

                } catch (Exception e) {
                    System.out.println("   ❌ Error: " + e.getMessage());
                }
            }
        }
    }
}