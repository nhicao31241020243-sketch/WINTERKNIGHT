package dyrvania.managers.entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;

import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;

public class GameManagerSpritePlayer {

    // ========== SPRITE SHEET ==========
    private static BufferedImage spriteSheet = null;
    private static boolean spriteSheetLoaded = false;

    // ========== SPRITE DIMENSIONS ==========
    private static final int SPRITE_HEIGHT = 86;
    private static final int SPRITE_WIDTH = 86; // Mỗi frame rộng 86px

    // ========== PLAYER SPRITE ARRAYS ==========
    // Idle: 290 width = 3-4 frames
    private final static BufferedImage[] spritePlayerIdleRight;
    private final static BufferedImage[] spritePlayerIdleLeft;

    // Walk: 580 width = 6-7 frames
    private final static BufferedImage[] spritePlayerWalkRight;
    private final static BufferedImage[] spritePlayerWalkLeft;

    // Run: 500 width = 5-6 frames
    private final static BufferedImage[] spritePlayerRunRight;
    private final static BufferedImage[] spritePlayerRunLeft;

    // Jump: 480 width = 5-6 frames
    private final static BufferedImage[] spritePlayerJumpRight;
    private final static BufferedImage[] spritePlayerJumpLeft;

    // Attack1: 430 width = 5 frames
    private final static BufferedImage[] spritePlayerAttack1Right;
    private final static BufferedImage[] spritePlayerAttack1Left;

    // Attack2: 430 width = 5 frames
    private final static BufferedImage[] spritePlayerAttack2Right;
    private final static BufferedImage[] spritePlayerAttack2Left;

    // Attack3: 400 width = 4-5 frames
    private final static BufferedImage[] spritePlayerAttack3Right;
    private final static BufferedImage[] spritePlayerAttack3Left;

    // Run+Attack: 460 width = 5 frames
    private final static BufferedImage[] spritePlayerRunAttackRight;
    private final static BufferedImage[] spritePlayerRunAttackLeft;

    // Hurt: 140 width = 1-2 frames
    private final static BufferedImage[] spritePlayerHurtRight;
    private final static BufferedImage[] spritePlayerHurtLeft;

    // Dead: 480 width = 6 frames
    private final static BufferedImage[] spritePlayerDeath;

    // Defend: 400 width = 5 frames
    private final static BufferedImage[] spritePlayerDefendRight;
    private final static BufferedImage[] spritePlayerDefendLeft;

    // Protect: 86 width = 1 frame
    private final static BufferedImage[] spritePlayerProtectRight;
    private final static BufferedImage[] spritePlayerProtectLeft;

    // Dust: hiệu ứng bụi (placeholder)
    private final static BufferedImage[] spriteDustRight;
    private final static BufferedImage[] spriteDustLeft;

    // Khởi tạo static block
    static {
        System.out.println("=== INITIALIZING PLAYER SPRITE MANAGER ===");

        // Khởi tạo mảng với số frame CHÍNH XÁC
        spritePlayerIdleRight = new BufferedImage[4];      // 290/86 = 3.37 -> 4 frames
        spritePlayerIdleLeft = new BufferedImage[4];

        spritePlayerWalkRight = new BufferedImage[6];      // 580/86 = 6.74 -> 6 frames
        spritePlayerWalkLeft = new BufferedImage[6];

        spritePlayerRunRight = new BufferedImage[6];       // 500/86 = 5.81 -> 6 frames
        spritePlayerRunLeft = new BufferedImage[6];

        spritePlayerJumpRight = new BufferedImage[6];      // 480/86 = 5.58 -> 6 frames
        spritePlayerJumpLeft = new BufferedImage[6];

        spritePlayerAttack1Right = new BufferedImage[5];   // 430/86 = 5 frames
        spritePlayerAttack1Left = new BufferedImage[5];

        spritePlayerAttack2Right = new BufferedImage[5];   // 430/86 = 5 frames
        spritePlayerAttack2Left = new BufferedImage[5];

        spritePlayerAttack3Right = new BufferedImage[5];   // 400/86 = 4.65 -> 5 frames
        spritePlayerAttack3Left = new BufferedImage[5];

        spritePlayerRunAttackRight = new BufferedImage[5]; // 460/86 = 5.35 -> 5 frames
        spritePlayerRunAttackLeft = new BufferedImage[5];

        spritePlayerHurtRight = new BufferedImage[2];      // 140/86 = 1.63 -> 2 frames
        spritePlayerHurtLeft = new BufferedImage[2];

        spritePlayerDeath = new BufferedImage[6];          // 480/86 = 5.58 -> 6 frames

        spritePlayerDefendRight = new BufferedImage[5];    // 400/86 = 4.65 -> 5 frames
        spritePlayerDefendLeft = new BufferedImage[5];

        spritePlayerProtectRight = new BufferedImage[1];   // 86/86 = 1 frame
        spritePlayerProtectLeft = new BufferedImage[1];

        // Dust effect - 4 frames
        spriteDustRight = new BufferedImage[4];
        spriteDustLeft = new BufferedImage[4];

        // Thử load sprite sheet
        loadSpriteSheet();

        // Nếu không load được, tạo placeholder
        if (!spriteSheetLoaded) {
            System.out.println("✗ Failed to load player sprite sheet. Using placeholder sprites.");
            createPlaceholderSprites();
        } else {
            System.out.println("✓ Player sprite sheet loaded successfully!");
            loadSpritesFromSheet();
        }
    }

    // ========== LOAD SPRITE SHEET ==========
    private static void loadSpriteSheet() {
        try {
            System.out.println("Looking for player sprite sheet...");

            String[] possiblePaths = {
                    "/sprites/player.png",
                    "/player.png",
                    "sprites/player.png",
                    "resources/sprites/player.png",
                    "player.png",
                    // Keep knight.png as fallback for compatibility
                    "/sprites/knight.png",
                    "sprites/knight.png"
            };

            for (String path : possiblePaths) {
                try {
                    System.out.println("  Trying: " + path);
                    URL resource = GameManagerSpritePlayer.class.getResource(path);

                    if (resource != null) {
                        System.out.println("  ✓ Found resource: " + resource);
                        spriteSheet = ImageIO.read(resource);

                        if (spriteSheet != null) {
                            spriteSheetLoaded = true;
                            System.out.println("  ✓ SUCCESS! Loaded sprite sheet: " +
                                    spriteSheet.getWidth() + "x" + spriteSheet.getHeight());
                            System.out.println("  Path: " + path);
                            return;
                        }
                    }
                } catch (Exception e) {
                    System.err.println("  ✗ Error: " + e.getMessage());
                }
            }

            // Try file system
            System.out.println("Trying to load from file system...");
            String[] filePaths = {
                    "resources/sprites/player.png",
                    "sprites/player.png",
                    "player.png",
                    "resources/sprites/knight.png", // Fallback
                    "sprites/knight.png" // Fallback
            };

            for (String filePath : filePaths) {
                try {
                    java.io.File file = new java.io.File(filePath);
                    if (file.exists()) {
                        System.out.println("  Found file: " + file.getAbsolutePath());
                        spriteSheet = ImageIO.read(file);
                        spriteSheetLoaded = true;
                        System.out.println("  ✓ Loaded from file: " +
                                spriteSheet.getWidth() + "x" + spriteSheet.getHeight());
                        return;
                    }
                } catch (Exception e) {
                    // Skip
                }
            }

        } catch (Exception e) {
            System.err.println("✗ ERROR loading sprite sheet: " + e.getMessage());
            spriteSheetLoaded = false;
        }
    }

    // ========== LOAD SPRITES FROM SHEET ==========
    private static void loadSpritesFromSheet() {
        if (!spriteSheetLoaded || spriteSheet == null) {
            System.err.println("Cannot load sprites: No sprite sheet available");
            return;
        }

        System.out.println("Loading player animations from sprite sheet...");

        try {
            // DỰA VÀO COORDINATES ĐÃ CHO

            // Walk: (0, 0, 580, 86) - hàng 0
            loadAnimationFromRegion(spritePlayerWalkRight, 0, 0, 580, 6);

            // Run: (0, 87, 500, 86) - hàng 1 (y = 87)
            loadAnimationFromRegion(spritePlayerRunRight, 87, 0, 500, 6);

            // Jump: (0, 261, 480, 86) - hàng 3 (y = 261)
            loadAnimationFromRegion(spritePlayerJumpRight, 261, 0, 480, 6);

            // Dead: (0, 174, 480, 86) - hàng 2 (y = 174)
            loadAnimationFromRegion(spritePlayerDeath, 174, 0, 480, 6);

            // Run+Attack: (0, 348, 460, 86) - hàng 4 (y = 348)
            loadAnimationFromRegion(spritePlayerRunAttackRight, 348, 0, 460, 5);

            // Attack1: (0, 435, 430, 86) - hàng 5 (y = 435)
            loadAnimationFromRegion(spritePlayerAttack1Right, 435, 0, 430, 5);

            // Attack2: (0, 522, 430, 86) - hàng 6 (y = 522)
            loadAnimationFromRegion(spritePlayerAttack2Right, 522, 0, 430, 5);

            // Attack3: (0, 609, 400, 86) - hàng 7 (y = 609)
            loadAnimationFromRegion(spritePlayerAttack3Right, 609, 0, 400, 5);

            // Defend: (0, 696, 400, 86) - hàng 8 (y = 696)
            loadAnimationFromRegion(spritePlayerDefendRight, 696, 0, 400, 5);

            // Hurt: (291, 783, 140, 86) - hàng 9 (y = 783), x = 291
            loadAnimationFromRegion(spritePlayerHurtRight, 783, 291, 140, 2);

            // Idle: (0, 783, 290, 86) - hàng 9 (y = 783), x = 0
            loadAnimationFromRegion(spritePlayerIdleRight, 783, 0, 290, 4);

            // Protect: (432, 783, 86, 86) - hàng 9 (y = 783), x = 432
            loadAnimationFromRegion(spritePlayerProtectRight, 783, 432, 86, 1);

            // Tạo flipped versions (left)
            createFlippedVersions();

            System.out.println("✓ All player animations loaded successfully!");

        } catch (Exception e) {
            System.err.println("✗ ERROR loading animations: " + e.getMessage());
            e.printStackTrace();
            createPlaceholderSprites();
        }
    }

    private static void loadAnimationFromRegion(BufferedImage[] array, int y, int startX, int totalWidth, int frames) {
        int frameWidth = totalWidth / frames;

        for (int i = 0; i < frames; i++) {
            int x = startX + (i * frameWidth);

            if (x + frameWidth <= spriteSheet.getWidth() && y + SPRITE_HEIGHT <= spriteSheet.getHeight()) {
                array[i] = spriteSheet.getSubimage(x, y, frameWidth, SPRITE_HEIGHT);
            } else {
                array[i] = createPlaceholderFrame(frameWidth, SPRITE_HEIGHT, "FRAME " + i);
            }
        }
    }

    private static void createFlippedVersions() {
        // Flip tất cả animation right thành left
        flipAnimation(spritePlayerIdleRight, spritePlayerIdleLeft);
        flipAnimation(spritePlayerWalkRight, spritePlayerWalkLeft);
        flipAnimation(spritePlayerRunRight, spritePlayerRunLeft);
        flipAnimation(spritePlayerJumpRight, spritePlayerJumpLeft);
        flipAnimation(spritePlayerAttack1Right, spritePlayerAttack1Left);
        flipAnimation(spritePlayerAttack2Right, spritePlayerAttack2Left);
        flipAnimation(spritePlayerAttack3Right, spritePlayerAttack3Left);
        flipAnimation(spritePlayerRunAttackRight, spritePlayerRunAttackLeft);
        flipAnimation(spritePlayerHurtRight, spritePlayerHurtLeft);
        flipAnimation(spritePlayerDefendRight, spritePlayerDefendLeft);
        flipAnimation(spritePlayerProtectRight, spritePlayerProtectLeft);
        flipAnimation(spriteDustRight, spriteDustLeft);
    }

    private static void flipAnimation(BufferedImage[] source, BufferedImage[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null) {
                target[i] = flipImageHorizontally(source[i]);
            }
        }
    }

    // ========== TẠO PLACEHOLDER SPRITES ==========
    private static void createPlaceholderSprites() {
        System.out.println("Creating player placeholder sprites...");

        // Tạo placeholder cho tất cả animation
        createPlaceholderAnimation(4, spritePlayerIdleRight, spritePlayerIdleLeft, "IDLE");
        createPlaceholderAnimation(6, spritePlayerWalkRight, spritePlayerWalkLeft, "WALK");
        createPlaceholderAnimation(6, spritePlayerRunRight, spritePlayerRunLeft, "RUN");
        createPlaceholderAnimation(6, spritePlayerJumpRight, spritePlayerJumpLeft, "JUMP");
        createPlaceholderAnimation(5, spritePlayerAttack1Right, spritePlayerAttack1Left, "ATK1");
        createPlaceholderAnimation(5, spritePlayerAttack2Right, spritePlayerAttack2Left, "ATK2");
        createPlaceholderAnimation(5, spritePlayerAttack3Right, spritePlayerAttack3Left, "ATK3");
        createPlaceholderAnimation(5, spritePlayerRunAttackRight, spritePlayerRunAttackLeft, "RUNATK");
        createPlaceholderAnimation(2, spritePlayerHurtRight, spritePlayerHurtLeft, "HURT");
        createPlaceholderAnimation(6, spritePlayerDeath, null, "DEATH");
        createPlaceholderAnimation(5, spritePlayerDefendRight, spritePlayerDefendLeft, "DEFEND");
        createPlaceholderAnimation(1, spritePlayerProtectRight, spritePlayerProtectLeft, "PROTECT");

        // Tạo placeholder cho dust effect
        createPlaceholderAnimation(4, spriteDustRight, spriteDustLeft, "DUST");

        System.out.println("✓ Created player placeholder sprites");
    }

    private static void createPlaceholderAnimation(int frames, BufferedImage[] rightArray,
                                                   BufferedImage[] leftArray, String label) {
        for (int i = 0; i < frames; i++) {
            BufferedImage frame = createPlaceholderFrame(86, 86, label + i);

            if (rightArray != null && i < rightArray.length) {
                rightArray[i] = frame;
            }

            if (leftArray != null && i < leftArray.length) {
                leftArray[i] = flipImageHorizontally(frame);
            }
        }
    }

    private static BufferedImage createPlaceholderFrame(int width, int height, String text) {
        BufferedImage frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = frame.createGraphics();

        // Màu khác nhau cho từng animation type
        if (text.contains("IDLE")) {
            g.setColor(new java.awt.Color(100, 100, 255)); // Blue
        } else if (text.contains("WALK")) {
            g.setColor(new java.awt.Color(100, 255, 100)); // Green
        } else if (text.contains("RUN")) {
            g.setColor(new java.awt.Color(255, 255, 100)); // Yellow
        } else if (text.contains("JUMP")) {
            g.setColor(new java.awt.Color(255, 150, 50));  // Orange
        } else if (text.contains("ATK")) {
            g.setColor(new java.awt.Color(255, 50, 50));   // Red
        } else if (text.contains("HURT")) {
            g.setColor(new java.awt.Color(255, 0, 255));   // Purple
        } else if (text.contains("DEATH")) {
            g.setColor(new java.awt.Color(50, 50, 50));    // Dark Gray
        } else if (text.contains("DEFEND") || text.contains("PROTECT")) {
            g.setColor(new java.awt.Color(50, 150, 255));  // Light Blue
        } else if (text.contains("DUST")) {
            g.setColor(new java.awt.Color(200, 180, 100)); // Brownish yellow for dust
        } else {
            g.setColor(new java.awt.Color(200, 200, 200)); // Gray
        }

        g.fillRect(0, 0, width, height);

        // Viền
        g.setColor(java.awt.Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        // Text
        g.setColor(java.awt.Color.WHITE);
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 10));
        g.drawString(text, 5, 15);

        // Vẽ nhân vật đơn giản
        if (!text.contains("DUST")) {
            g.setColor(new java.awt.Color(150, 150, 150)); // Gray armor
            g.fillRect(20, 20, 46, 46); // Thân giáp

            // Mũ giáp hoặc đầu
            g.setColor(new java.awt.Color(100, 100, 100));
            g.fillRect(30, 10, 26, 15);

            // Khiên (nếu là knight) hoặc tay
            g.setColor(new java.awt.Color(200, 150, 50)); // Gold shield
            g.fillRect(10, 30, 15, 30);

            // Vũ khí
            g.setColor(new java.awt.Color(180, 180, 180)); // Silver weapon
            g.fillRect(60, 30, 5, 25);
            g.fillRect(55, 35, 15, 5);
        } else {
            // Vẽ hiệu ứng bụi đơn giản
            g.setColor(new java.awt.Color(150, 120, 80, 180)); // Nâu trong suốt
            g.fillOval(20, 20, 46, 46);
            g.setColor(new java.awt.Color(180, 150, 100, 150));
            g.fillOval(30, 30, 26, 26);
        }

        g.dispose();
        return frame;
    }

    // ========== HELPER METHODS ==========
    private static BufferedImage flipImageHorizontally(BufferedImage image) {
        if (image == null) return null;

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(), 0);

        BufferedImage flipped = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g = flipped.createGraphics();
        g.drawImage(image, tx, null);
        g.dispose();

        return flipped;
    }

    // ========== PUBLIC METHODS FOR PLAYER ANIMATIONS ==========

    // Idle
    public static GameSpriteAnimation createSpritePlayerIdleRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, spritePlayerIdleRight, null);
    }

    public static GameSpriteAnimation createSpritePlayerIdleLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, spritePlayerIdleLeft, null);
    }

    // Walk
    public static GameSpriteAnimation createSpritePlayerWalkRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerWalkRight, null);
    }

    public static GameSpriteAnimation createSpritePlayerWalkLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerWalkLeft, null);
    }

    // Run
    public static GameSpriteAnimation createSpritePlayerRunRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 8, spritePlayerRunRight, null);
    }

    public static GameSpriteAnimation createSpritePlayerRunLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 8, spritePlayerRunLeft, null);
    }

    // Jump
    public static GameSpriteAnimation createSpritePlayerJumpRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 6, spritePlayerJumpRight, null);
    }

    public static GameSpriteAnimation createSpritePlayerJumpLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 6, spritePlayerJumpLeft, null);
    }

    // Attack1
    public static GameSpriteAnimation createSpritePlayerAttack1Right(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack1Right, null);
    }

    public static GameSpriteAnimation createSpritePlayerAttack1Left(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack1Left, null);
    }

    // Attack2
    public static GameSpriteAnimation createSpritePlayerAttack2Right(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack2Right, null);
    }

    public static GameSpriteAnimation createSpritePlayerAttack2Left(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack2Left, null);
    }

    // Attack3
    public static GameSpriteAnimation createSpritePlayerAttack3Right(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack3Right, null);
    }

    public static GameSpriteAnimation createSpritePlayerAttack3Left(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack3Left, null);
    }

    // Run+Attack
    public static GameSpriteAnimation createSpritePlayerRunAttackRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 6, spritePlayerRunAttackRight, null);
    }

    public static GameSpriteAnimation createSpritePlayerRunAttackLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 6, spritePlayerRunAttackLeft, null);
    }

    // Hurt
    public static GameSpriteAnimation createSpritePlayerHurtRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 3, spritePlayerHurtRight, null);
    }

    public static GameSpriteAnimation createSpritePlayerHurtLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 3, spritePlayerHurtLeft, null);
    }

    // Death
    public static GameSpriteAnimation createSpritePlayerDeath(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerDeath, null);
    }

    // Defend
    public static GameSpriteAnimation createSpritePlayerDefendRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 8, spritePlayerDefendRight, null);
    }

    public static GameSpriteAnimation createSpritePlayerDefendLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 8, spritePlayerDefendLeft, null);
    }

    // Protect
    public static GameSpriteAnimation createSpritePlayerProtectRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerProtectRight, null);
    }

    public static GameSpriteAnimation createSpritePlayerProtectLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerProtectLeft, null);
    }

    // ========== DUST EFFECT METHOD ==========
    public static GameSpriteAnimation createSpriteDust(GameRect rect, boolean facingRight) {
        if (facingRight) {
            return new GameSpriteAnimation(rect, 3, spriteDustRight, null);
        } else {
            return new GameSpriteAnimation(rect, 3, spriteDustLeft, null);
        }
    }

    // ========== COMPATIBILITY METHODS (dùng trong Player.java) ==========
    public static GameSpriteAnimation createSpriteIdleRight(GameRect rect) {
        return createSpritePlayerIdleRight(rect);
    }

    public static GameSpriteAnimation createSpriteIdleLeft(GameRect rect) {
        return createSpritePlayerIdleLeft(rect);
    }

    public static GameSpriteAnimation createSpriteJumpRight(GameRect rect) {
        return createSpritePlayerJumpRight(rect);
    }

    public static GameSpriteAnimation createSpriteJumpLeft(GameRect rect) {
        return createSpritePlayerJumpLeft(rect);
    }

    public static GameSpriteAnimation createSpriteRunRight(GameRect rect) {
        return createSpritePlayerRunRight(rect);
    }

    public static GameSpriteAnimation createSpriteRunLeft(GameRect rect) {
        return createSpritePlayerRunLeft(rect);
    }

    public static GameSpriteAnimation createSpriteAttackRight(GameRect rect) {
        return createSpritePlayerAttack1Right(rect);
    }

    public static GameSpriteAnimation createSpriteAttackLeft(GameRect rect) {
        return createSpritePlayerAttack1Left(rect);
    }

    // ========== ALIAS METHODS FOR EASY MIGRATION ==========
    // Giữ lại các method cũ để tương thích với code hiện có
    public static GameSpriteAnimation createSpriteKnightIdleRight(GameRect rect) {
        return createSpritePlayerIdleRight(rect);
    }

    public static GameSpriteAnimation createSpriteKnightIdleLeft(GameRect rect) {
        return createSpritePlayerIdleLeft(rect);
    }

    public static GameSpriteAnimation createSpriteKnightRunRight(GameRect rect) {
        return createSpritePlayerRunRight(rect);
    }

    public static GameSpriteAnimation createSpriteKnightRunLeft(GameRect rect) {
        return createSpritePlayerRunLeft(rect);
    }

    public static GameSpriteAnimation createSpriteKnightAttack1Right(GameRect rect) {
        return createSpritePlayerAttack1Right(rect);
    }

    public static GameSpriteAnimation createSpriteKnightAttack1Left(GameRect rect) {
        return createSpritePlayerAttack1Left(rect);
    }
}