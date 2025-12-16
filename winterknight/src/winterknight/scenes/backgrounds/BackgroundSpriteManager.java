package winterknight.scenes.backgrounds;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class BackgroundSpriteManager {

    private static BackgroundSpriteManager instance;
    private BufferedImage spriteSheet;
    private boolean isLoaded = false;

    // MAP LƯU TỌA ĐỘ CÁC SPRITE
    private static class SpriteInfo {
        int x, y, width, height;

        SpriteInfo(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private final Map<String, SpriteInfo> spriteMap;

    private BackgroundSpriteManager() {
        spriteMap = new HashMap<>();
        initializeSpriteMap();
    }

    public static BackgroundSpriteManager getInstance() {
        if (instance == null) {
            instance = new BackgroundSpriteManager();
        }
        return instance;
    }

    // KHỞI TẠO MAP TỌA ĐỘ TỪ DỮ LIỆU BẠN CUNG CẤP
    private void initializeSpriteMap() {
        // Background chính
        spriteMap.put("BG_01", new SpriteInfo(4609, 2593, 1920, 1080));
        spriteMap.put("BG_02", new SpriteInfo(6530, 2593, 1920, 1080));
        spriteMap.put("BG_03", new SpriteInfo(0, 2994, 1920, 1080));
        spriteMap.put("BG_04", new SpriteInfo(1921, 2994, 1920, 1080));

        // Frame GIF 15720 (640x400)
        spriteMap.put("15720", new SpriteInfo(0, 2593, 640, 400));

        // Các frame nhỏ khác
        spriteMap.put("3", new SpriteInfo(3842, 3644, 576, 324));
        spriteMap.put("5", new SpriteInfo(641, 2593, 576, 324)); // Từ dòng đầu tiên
        spriteMap.put("6", new SpriteInfo(3842, 3319, 576, 324));
        spriteMap.put("10", new SpriteInfo(3842, 2994, 576, 324));

        // Preview images (nếu cần)
        spriteMap.put("Preview1", new SpriteInfo(0, 4075, 4608, 2592));
        spriteMap.put("Preview2", new SpriteInfo(0, 0, 4608, 2592));
        spriteMap.put("Preview3", new SpriteInfo(4609, 0, 4608, 2592));
        spriteMap.put("Preview4", new SpriteInfo(4609, 3674, 4608, 2592));
    }

    // LOAD SPRITE SHEET MỘT LẦN DUY NHẤT
    private void loadIfNeeded() {
        if (isLoaded) return;

        try {
            System.out.println("🚀 LOADING BACKGROUND SPRITE SHEET...");

            File file = null;

            // THỬ CÁC ĐƯỜNG DẪN CÓ THỂ
            String[] possiblePaths = {
                    "backgrounds.png",
                    "sprites/backgrounds.png",
                    "resources/backgrounds.png",
                    "src/resources/backgrounds.png",
                    System.getProperty("user.dir") + "/backgrounds.png",
                    System.getProperty("user.dir") + "/resources/backgrounds.png"
            };

            for (String path : possiblePaths) {
                file = new File(path);
                if (file.exists()) {
                    System.out.println("✅ Found at: " + file.getAbsolutePath());
                    break;
                }
            }

            if (file == null || !file.exists()) {
                System.err.println("❌ CRITICAL: Cannot find backgrounds.png!");
                System.err.println("📁 Current dir: " + System.getProperty("user.dir"));
                System.err.println("🔍 Tried paths:");
                for (String path : possiblePaths) {
                    System.err.println("   - " + path);
                }
                return;
            }

            // LOAD ẢNH
            spriteSheet = ImageIO.read(file);
            System.out.println("✅ SPRITE SHEET LOADED!");
            System.out.println("📊 Size: " + spriteSheet.getWidth() + "x" + spriteSheet.getHeight());
            System.out.println("🎯 Available sprites: " + spriteMap.keySet());

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

        // KIỂM TRA SPRITE CÓ TRONG MAP KHÔNG
        SpriteInfo info = spriteMap.get(spriteName);
        if (info == null) {
            System.err.println("❌ Unknown sprite: " + spriteName);
            System.err.println("   Available: " + spriteMap.keySet());
            return createErrorSprite(spriteName);
        }

        // KIỂM TRA TỌA ĐỘ
        if (info.x + info.width > spriteSheet.getWidth() || info.y + info.height > spriteSheet.getHeight()) {
            System.err.println("❌ COORDINATES OUT OF BOUNDS!");
            System.err.println("   Sprite: " + spriteName);
            System.err.println("   Requested: (" + info.x + "," + info.y + ") size " +
                    info.width + "x" + info.height);
            System.err.println("   Image size: " + spriteSheet.getWidth() + "x" + spriteSheet.getHeight());
            return createErrorSprite(spriteName);
        }

        try {
            BufferedImage sprite = spriteSheet.getSubimage(info.x, info.y, info.width, info.height);
            System.out.println("✂️  Cut " + spriteName + ": " + info.width + "x" + info.height +
                    " at (" + info.x + "," + info.y + ")");
            return sprite;

        } catch (Exception e) {
            System.err.println("❌ Error cutting " + spriteName + ": " + e.getMessage());
            return createErrorSprite(spriteName);
        }
    }

    // Lấy frame GIF 15720 và các frame animation liên quan
    public BufferedImage[] getGifFrames(String baseName, int frameCount) {
        BufferedImage[] frames = new BufferedImage[frameCount];

        try {
            // Frame đầu tiên là 15720
            frames[0] = getSprite("15720");

            // Tìm các frame tiếp theo (giả sử chúng nằm liền kề)
            for (int i = 1; i < frameCount; i++) {
                String frameName = baseName + "_" + i;
                SpriteInfo info = spriteMap.get(frameName);

                if (info != null) {
                    frames[i] = spriteSheet.getSubimage(info.x, info.y, info.width, info.height);
                } else {
                    // Nếu không có frame riêng, dùng frame đầu
                    frames[i] = frames[0];
                }
            }

            System.out.println("🎬 Created " + frameCount + " frames for GIF animation");

        } catch (Exception e) {
            System.err.println("❌ Error creating GIF frames: " + e.getMessage());
            // Tạo frame mặc định nếu lỗi
            for (int i = 0; i < frameCount; i++) {
                frames[i] = getSprite("15720");
            }
        }

        return frames;
    }

    // Kiểm tra sprite có tồn tại không
    public boolean hasSprite(String spriteName) {
        return spriteMap.containsKey(spriteName);
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

    // DEBUG: In tất cả sprite có sẵn
    public void printAvailableSprites() {
        System.out.println("📋 AVAILABLE BACKGROUND SPRITES:");
        for (String key : spriteMap.keySet()) {
            SpriteInfo info = spriteMap.get(key);
            System.out.println("   " + key + ": " + info.width + "x" + info.height +
                    " at (" + info.x + "," + info.y + ")");
        }
    }
}