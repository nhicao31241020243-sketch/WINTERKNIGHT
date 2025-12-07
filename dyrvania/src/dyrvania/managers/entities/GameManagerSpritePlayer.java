package dyrvania.managers.entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import dyrvania.generics.GameColors;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameUtil;
import dyrvania.resources.Spritesheet;

public class GameManagerSpritePlayer {

    // ========== KNIGHT SPRITE ARRAYS (86x86) ==========
    private final static BufferedImage[] spritePlayerIdleRight;
    private final static BufferedImage[] spritePlayerIdleLeft;
    private final static BufferedImage[] spritePlayerIdleRightPoisoned;
    private final static BufferedImage[] spritePlayerIdleLeftPoisoned;

    private final static BufferedImage[] spritePlayerWalkRight;
    private final static BufferedImage[] spritePlayerWalkLeft;
    private final static BufferedImage[] spritePlayerWalkRightPoisoned;
    private final static BufferedImage[] spritePlayerWalkLeftPoisoned;

    private final static BufferedImage[] spritePlayerRunRight;
    private final static BufferedImage[] spritePlayerRunLeft;
    private final static BufferedImage[] spritePlayerRunRightPoisoned;
    private final static BufferedImage[] spritePlayerRunLeftPoisoned;

    private final static BufferedImage[] spritePlayerJumpRight;
    private final static BufferedImage[] spritePlayerJumpLeft;
    private final static BufferedImage[] spritePlayerJumpRightPoisoned;
    private final static BufferedImage[] spritePlayerJumpLeftPoisoned;

    private final static BufferedImage[] spritePlayerAttack1Right;
    private final static BufferedImage[] spritePlayerAttack1Left;
    private final static BufferedImage[] spritePlayerAttack1RightPoisoned;
    private final static BufferedImage[] spritePlayerAttack1LeftPoisoned;

    private final static BufferedImage[] spritePlayerAttack2Right;
    private final static BufferedImage[] spritePlayerAttack2Left;
    private final static BufferedImage[] spritePlayerAttack2RightPoisoned;
    private final static BufferedImage[] spritePlayerAttack2LeftPoisoned;

    private final static BufferedImage[] spritePlayerAttack3Right;
    private final static BufferedImage[] spritePlayerAttack3Left;
    private final static BufferedImage[] spritePlayerAttack3RightPoisoned;
    private final static BufferedImage[] spritePlayerAttack3LeftPoisoned;

    private final static BufferedImage[] spritePlayerWalkAttackRight;
    private final static BufferedImage[] spritePlayerWalkAttackLeft;
    private final static BufferedImage[] spritePlayerWalkAttackRightPoisoned;
    private final static BufferedImage[] spritePlayerWalkAttackLeftPoisoned;

    private final static BufferedImage[] spritePlayerHurtRight;
    private final static BufferedImage[] spritePlayerHurtLeft;
    private final static BufferedImage[] spritePlayerHurtRightPoisoned;
    private final static BufferedImage[] spritePlayerHurtLeftPoisoned;

    private final static BufferedImage[] spritePlayerDeath;
    private final static BufferedImage[] spritePlayerDeathPoisoned;

    private final static BufferedImage[] spritePlayerDefendRight;
    private final static BufferedImage[] spritePlayerDefendLeft;
    private final static BufferedImage[] spritePlayerDefendRightPoisoned;
    private final static BufferedImage[] spritePlayerDefendLeftPoisoned;

    private final static BufferedImage[] spritePlayerProtectRight;
    private final static BufferedImage[] spritePlayerProtectLeft;
    private final static BufferedImage[] spritePlayerProtectRightPoisoned;
    private final static BufferedImage[] spritePlayerProtectLeftPoisoned;

    private final static BufferedImage[] spritePlayerDust;
    private final static BufferedImage[] spritePlayerWalkDust;

    static {
        System.out.println("=== INITIALIZING PLAYER SPRITE MANAGER (KNIGHT) ===");

        // Kích thước KNIGHT: 86x86
        int frameWidth = 86;
        int frameHeight = 86;

        // Khởi tạo mảng với số frame cho KNIGHT
        // Idle: 4 frames
        spritePlayerIdleRight = new BufferedImage[4];
        spritePlayerIdleLeft = new BufferedImage[4];
        spritePlayerIdleRightPoisoned = new BufferedImage[4];
        spritePlayerIdleLeftPoisoned = new BufferedImage[4];

        // Walk: 6 frames
        spritePlayerWalkRight = new BufferedImage[6];
        spritePlayerWalkLeft = new BufferedImage[6];
        spritePlayerWalkRightPoisoned = new BufferedImage[6];
        spritePlayerWalkLeftPoisoned = new BufferedImage[6];

        // Run: 6 frames
        spritePlayerRunRight = new BufferedImage[6];
        spritePlayerRunLeft = new BufferedImage[6];
        spritePlayerRunRightPoisoned = new BufferedImage[6];
        spritePlayerRunLeftPoisoned = new BufferedImage[6];

        // Jump: 6 frames
        spritePlayerJumpRight = new BufferedImage[6];
        spritePlayerJumpLeft = new BufferedImage[6];
        spritePlayerJumpRightPoisoned = new BufferedImage[6];
        spritePlayerJumpLeftPoisoned = new BufferedImage[6];

        // Attack1: 5 frames
        spritePlayerAttack1Right = new BufferedImage[5];
        spritePlayerAttack1Left = new BufferedImage[5];
        spritePlayerAttack1RightPoisoned = new BufferedImage[5];
        spritePlayerAttack1LeftPoisoned = new BufferedImage[5];

        // Attack2: 5 frames
        spritePlayerAttack2Right = new BufferedImage[5];
        spritePlayerAttack2Left = new BufferedImage[5];
        spritePlayerAttack2RightPoisoned = new BufferedImage[5];
        spritePlayerAttack2LeftPoisoned = new BufferedImage[5];

        // Attack3: 5 frames
        spritePlayerAttack3Right = new BufferedImage[5];
        spritePlayerAttack3Left = new BufferedImage[5];
        spritePlayerAttack3RightPoisoned = new BufferedImage[5];
        spritePlayerAttack3LeftPoisoned = new BufferedImage[5];

        // Walk+Attack: 5 frames
        spritePlayerWalkAttackRight = new BufferedImage[5];
        spritePlayerWalkAttackLeft = new BufferedImage[5];
        spritePlayerWalkAttackRightPoisoned = new BufferedImage[5];
        spritePlayerWalkAttackLeftPoisoned = new BufferedImage[5];

        // Hurt: 2 frames
        spritePlayerHurtRight = new BufferedImage[2];
        spritePlayerHurtLeft = new BufferedImage[2];
        spritePlayerHurtRightPoisoned = new BufferedImage[2];
        spritePlayerHurtLeftPoisoned = new BufferedImage[2];

        // Death: 6 frames
        spritePlayerDeath = new BufferedImage[6];
        spritePlayerDeathPoisoned = new BufferedImage[6];

        // Defend: 5 frames
        spritePlayerDefendRight = new BufferedImage[5];
        spritePlayerDefendLeft = new BufferedImage[5];
        spritePlayerDefendRightPoisoned = new BufferedImage[5];
        spritePlayerDefendLeftPoisoned = new BufferedImage[5];

        // Protect: 1 frame
        spritePlayerProtectRight = new BufferedImage[1];
        spritePlayerProtectLeft = new BufferedImage[1];
        spritePlayerProtectRightPoisoned = new BufferedImage[1];
        spritePlayerProtectLeftPoisoned = new BufferedImage[1];

        // Dust effects
        spritePlayerDust = new BufferedImage[5];
        spritePlayerWalkDust = new BufferedImage[6];

        // Thử load KNIGHT sprite sheet
        loadKnightSpriteSheet();

        // Nếu không load được, tạo placeholder
        if (!checkSpritesLoaded()) {
            System.out.println("✗ Failed to load knight sprite sheet. Using placeholder.");
            createKnightPlaceholderSprites();
        } else {
            System.out.println("✓ Knight sprite sheets loaded successfully!");
        }
    }

    // ========== LOAD KNIGHT SPRITE SHEET ==========
    private static void loadKnightSpriteSheet() {
        try {
            System.out.println("Looking for knight.png...");
            InputStream is = GameManagerSpritePlayer.class.getResourceAsStream("/sprites/knight.png");

            if (is != null) {
                BufferedImage knightSheet = ImageIO.read(is);
                System.out.println("✓ Loaded knight.png: " + knightSheet.getWidth() + "x" + knightSheet.getHeight());

                // Dựa trên coordinates đã biết: 580x869px, mỗi hàng 86px
                loadKnightAnimationsFromSheet(knightSheet);
            } else {
                System.out.println("✗ knight.png not found in /sprites/");
            }

        } catch (Exception e) {
            System.err.println("Error loading knight sprites: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadKnightAnimationsFromSheet(BufferedImage sheet) {
        // Dựa trên coordinates: (x, y, width, height)
        // Walk: (0, 0, 580, 86) - hàng 0
        loadAnimationFromRegion(sheet, spritePlayerWalkRight, 0, 0, 580, 6);

        // Run: (0, 87, 500, 86) - hàng 1
        loadAnimationFromRegion(sheet, spritePlayerRunRight, 87, 0, 500, 6);

        // Jump: (0, 261, 480, 86) - hàng 3
        loadAnimationFromRegion(sheet, spritePlayerJumpRight, 261, 0, 480, 6);

        // Dead: (0, 174, 480, 86) - hàng 2
        loadAnimationFromRegion(sheet, spritePlayerDeath, 174, 0, 480, 6);

        // Run+Attack: (0, 348, 460, 86) - hàng 4
        loadAnimationFromRegion(sheet, spritePlayerWalkAttackRight, 348, 0, 460, 5);

        // Attack1: (0, 435, 430, 86) - hàng 5
        loadAnimationFromRegion(sheet, spritePlayerAttack1Right, 435, 0, 430, 5);

        // Attack2: (0, 522, 430, 86) - hàng 6
        loadAnimationFromRegion(sheet, spritePlayerAttack2Right, 522, 0, 430, 5);

        // Attack3: (0, 609, 400, 86) - hàng 7
        loadAnimationFromRegion(sheet, spritePlayerAttack3Right, 609, 0, 400, 5);

        // Defend: (0, 696, 400, 86) - hàng 8
        loadAnimationFromRegion(sheet, spritePlayerDefendRight, 696, 0, 400, 5);

        // Hurt: (291, 783, 140, 86) - hàng 9, x=291
        loadAnimationFromRegion(sheet, spritePlayerHurtRight, 783, 291, 140, 2);

        // Idle: (0, 783, 290, 86) - hàng 9, x=0
        loadAnimationFromRegion(sheet, spritePlayerIdleRight, 783, 0, 290, 4);

        // Protect: (432, 783, 86, 86) - hàng 9, x=432
        loadAnimationFromRegion(sheet, spritePlayerProtectRight, 783, 432, 86, 1);

        // Tạo flipped versions và poisoned versions
        createKnightAllVariants();
    }

    private static void loadAnimationFromRegion(BufferedImage sheet, BufferedImage[] array,
                                                int y, int startX, int totalWidth, int frames) {
        int frameWidth = totalWidth / frames;

        for (int i = 0; i < frames; i++) {
            int x = startX + (i * frameWidth);

            if (x + frameWidth <= sheet.getWidth() && y + 86 <= sheet.getHeight()) {
                array[i] = sheet.getSubimage(x, y, frameWidth, 86);
            } else {
                array[i] = createKnightPlaceholderFrame(frameWidth, 86, "K" + i);
            }
        }
    }

    private static void createKnightAllVariants() {
        // Tạo flipped versions (left)
        flipAnimation(spritePlayerIdleRight, spritePlayerIdleLeft);
        flipAnimation(spritePlayerWalkRight, spritePlayerWalkLeft);
        flipAnimation(spritePlayerRunRight, spritePlayerRunLeft);
        flipAnimation(spritePlayerJumpRight, spritePlayerJumpLeft);
        flipAnimation(spritePlayerAttack1Right, spritePlayerAttack1Left);
        flipAnimation(spritePlayerAttack2Right, spritePlayerAttack2Left);
        flipAnimation(spritePlayerAttack3Right, spritePlayerAttack3Left);
        flipAnimation(spritePlayerWalkAttackRight, spritePlayerWalkAttackLeft);
        flipAnimation(spritePlayerHurtRight, spritePlayerHurtLeft);
        flipAnimation(spritePlayerDefendRight, spritePlayerDefendLeft);
        flipAnimation(spritePlayerProtectRight, spritePlayerProtectLeft);

        // Tạo poisoned versions
        createPoisonedVersions(spritePlayerIdleRight, spritePlayerIdleRightPoisoned);
        createPoisonedVersions(spritePlayerIdleLeft, spritePlayerIdleLeftPoisoned);
        createPoisonedVersions(spritePlayerWalkRight, spritePlayerWalkRightPoisoned);
        createPoisonedVersions(spritePlayerWalkLeft, spritePlayerWalkLeftPoisoned);
        createPoisonedVersions(spritePlayerRunRight, spritePlayerRunRightPoisoned);
        createPoisonedVersions(spritePlayerRunLeft, spritePlayerRunLeftPoisoned);
        createPoisonedVersions(spritePlayerJumpRight, spritePlayerJumpRightPoisoned);
        createPoisonedVersions(spritePlayerJumpLeft, spritePlayerJumpLeftPoisoned);
        createPoisonedVersions(spritePlayerAttack1Right, spritePlayerAttack1RightPoisoned);
        createPoisonedVersions(spritePlayerAttack1Left, spritePlayerAttack1LeftPoisoned);
        createPoisonedVersions(spritePlayerAttack2Right, spritePlayerAttack2RightPoisoned);
        createPoisonedVersions(spritePlayerAttack2Left, spritePlayerAttack2LeftPoisoned);
        createPoisonedVersions(spritePlayerAttack3Right, spritePlayerAttack3RightPoisoned);
        createPoisonedVersions(spritePlayerAttack3Left, spritePlayerAttack3LeftPoisoned);
        createPoisonedVersions(spritePlayerWalkAttackRight, spritePlayerWalkAttackRightPoisoned);
        createPoisonedVersions(spritePlayerWalkAttackLeft, spritePlayerWalkAttackLeftPoisoned);
        createPoisonedVersions(spritePlayerHurtRight, spritePlayerHurtRightPoisoned);
        createPoisonedVersions(spritePlayerHurtLeft, spritePlayerHurtLeftPoisoned);
        createPoisonedVersions(spritePlayerDeath, spritePlayerDeathPoisoned);
        createPoisonedVersions(spritePlayerDefendRight, spritePlayerDefendRightPoisoned);
        createPoisonedVersions(spritePlayerDefendLeft, spritePlayerDefendLeftPoisoned);
        createPoisonedVersions(spritePlayerProtectRight, spritePlayerProtectRightPoisoned);
        createPoisonedVersions(spritePlayerProtectLeft, spritePlayerProtectLeftPoisoned);
    }

    private static void flipAnimation(BufferedImage[] source, BufferedImage[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null) {
                target[i] = flipImageHorizontally(source[i]);
            }
        }
    }

    private static void createPoisonedVersions(BufferedImage[] source, BufferedImage[] target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null) {
                target[i] = GameUtil.createSpriteColor(source[i], GameColors.POISONED);
            }
        }
    }

    // ========== TẠO PLACEHOLDER CHO KNIGHT ==========
    private static void createKnightPlaceholderSprites() {
        System.out.println("Creating knight placeholder sprites...");

        createKnightPlaceholderAnimation(4, spritePlayerIdleRight, spritePlayerIdleLeft,
                spritePlayerIdleRightPoisoned, spritePlayerIdleLeftPoisoned, "IDLE");

        createKnightPlaceholderAnimation(6, spritePlayerWalkRight, spritePlayerWalkLeft,
                spritePlayerWalkRightPoisoned, spritePlayerWalkLeftPoisoned, "WALK");

        createKnightPlaceholderAnimation(6, spritePlayerRunRight, spritePlayerRunLeft,
                spritePlayerRunRightPoisoned, spritePlayerRunLeftPoisoned, "RUN");

        createKnightPlaceholderAnimation(6, spritePlayerJumpRight, spritePlayerJumpLeft,
                spritePlayerJumpRightPoisoned, spritePlayerJumpLeftPoisoned, "JUMP");

        createKnightPlaceholderAnimation(5, spritePlayerAttack1Right, spritePlayerAttack1Left,
                spritePlayerAttack1RightPoisoned, spritePlayerAttack1LeftPoisoned, "ATK1");

        createKnightPlaceholderAnimation(5, spritePlayerAttack2Right, spritePlayerAttack2Left,
                spritePlayerAttack2RightPoisoned, spritePlayerAttack2LeftPoisoned, "ATK2");

        createKnightPlaceholderAnimation(5, spritePlayerAttack3Right, spritePlayerAttack3Left,
                spritePlayerAttack3RightPoisoned, spritePlayerAttack3LeftPoisoned, "ATK3");

        createKnightPlaceholderAnimation(5, spritePlayerWalkAttackRight, spritePlayerWalkAttackLeft,
                spritePlayerWalkAttackRightPoisoned, spritePlayerWalkAttackLeftPoisoned, "WKATK");

        createKnightPlaceholderAnimation(2, spritePlayerHurtRight, spritePlayerHurtLeft,
                spritePlayerHurtRightPoisoned, spritePlayerHurtLeftPoisoned, "HURT");

        createKnightPlaceholderAnimation(6, spritePlayerDeath, null,
                spritePlayerDeathPoisoned, null, "DEATH");

        createKnightPlaceholderAnimation(5, spritePlayerDefendRight, spritePlayerDefendLeft,
                spritePlayerDefendRightPoisoned, spritePlayerDefendLeftPoisoned, "DEFEND");

        createKnightPlaceholderAnimation(1, spritePlayerProtectRight, spritePlayerProtectLeft,
                spritePlayerProtectRightPoisoned, spritePlayerProtectLeftPoisoned, "PROTECT");

        // Dust effects
        for (int i = 0; i < 5; i++) {
            spritePlayerDust[i] = createKnightPlaceholderFrame(86, 86, "DUST" + i);
        }
        for (int i = 0; i < 6; i++) {
            spritePlayerWalkDust[i] = createKnightPlaceholderFrame(86, 86, "WKDUST" + i);
        }

        System.out.println("✓ Created knight placeholder sprites");
    }

    private static void createKnightPlaceholderAnimation(int frames, BufferedImage[] rightArray,
                                                         BufferedImage[] leftArray,
                                                         BufferedImage[] rightPoisonedArray,
                                                         BufferedImage[] leftPoisonedArray,
                                                         String label) {
        for (int i = 0; i < frames; i++) {
            BufferedImage frame = createKnightPlaceholderFrame(86, 86, label + i);

            if (rightArray != null && i < rightArray.length) {
                rightArray[i] = frame;
                if (rightPoisonedArray != null && i < rightPoisonedArray.length) {
                    rightPoisonedArray[i] = GameUtil.createSpriteColor(frame, GameColors.POISONED);
                }
            }

            if (leftArray != null && i < leftArray.length) {
                leftArray[i] = flipImageHorizontally(frame);
                if (leftPoisonedArray != null && i < leftPoisonedArray.length) {
                    leftPoisonedArray[i] = GameUtil.createSpriteColor(leftArray[i], GameColors.POISONED);
                }
            }
        }
    }

    private static BufferedImage createKnightPlaceholderFrame(int width, int height, String text) {
        BufferedImage frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = frame.createGraphics();

        // Màu nền khác nhau cho từng animation type
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
        g.drawString("KNIGHT", 5, 15);
        g.drawString(text, 5, 30);

        // Vẽ hiệp sĩ đơn giản
        g.setColor(new java.awt.Color(150, 150, 150)); // Gray armor
        g.fillRect(20, 20, 46, 46); // Thân giáp

        // Mũ giáp
        g.setColor(new java.awt.Color(100, 100, 100));
        g.fillRect(30, 10, 26, 15);

        // Khiên
        g.setColor(new java.awt.Color(200, 150, 50)); // Gold shield
        g.fillRect(10, 30, 15, 30);

        // Kiếm
        g.setColor(new java.awt.Color(180, 180, 180)); // Silver sword
        g.fillRect(60, 30, 5, 25);
        g.fillRect(55, 35, 15, 5);

        g.dispose();
        return frame;
    }

    // ========== HELPER METHODS ==========
    private static boolean checkSpritesLoaded() {
        // Kiểm tra nếu đã load được sprite đầu tiên
        return spritePlayerIdleRight[0] != null;
    }

    private static BufferedImage flipImageHorizontally(BufferedImage image) {
        if (image == null) return null;

    // ... (các sprite array hiện tại vẫn giữ) ...

    // THÊM SPRITE CHO MÈO
    private final static BufferedImage[] spriteCatIdleRight;
    private final static BufferedImage[] spriteCatIdleLeft;
    private final static BufferedImage[] spriteCatIdleRightPoisoned;
    private final static BufferedImage[] spriteCatIdleLeftPoisoned;

    static {
        // ... (phần static block hiện tại) ...

        // === THÊM PHẦN MÈO - LOAD TỪ FILE ===
        int catWidth = 32;
        int catHeight = 32;

        // Cat Idle Right
        spriteCatIdleRight = new BufferedImage[10];
        spriteCatIdleRightPoisoned = new BufferedImage[10];

        // Cat Idle Left
        spriteCatIdleLeft = new BufferedImage[10];
        spriteCatIdleLeftPoisoned = new BufferedImage[10];

        try {
            // Load file cat.png từ resources
            InputStream is = GameManagerSpritePlayer.class.getResourceAsStream("/sprites/cat.png");

            if (is != null) {
                BufferedImage catSpriteSheet = ImageIO.read(is);

                // Cắt 10 frame cho spriteCatIdleRight
                for (int i = 0; i < 10; i++) {
                    spriteCatIdleRight[i] = catSpriteSheet.getSubimage(i * catWidth, 0, catWidth, catHeight);
                    spriteCatIdleRightPoisoned[i] = GameUtil.createSpriteColor(spriteCatIdleRight[i], GameColors.POISONED);

                    // Tạo left bằng cách flip ngang
                    spriteCatIdleLeft[i] = flipImageHorizontally(spriteCatIdleRight[i]);
                    spriteCatIdleLeftPoisoned[i] = GameUtil.createSpriteColor(spriteCatIdleLeft[i], GameColors.POISONED);
                }
            } else {
                // Nếu không tìm thấy file, tạo placeholder
                createCatPlaceholderSprites();
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Nếu lỗi, tạo placeholder
            createCatPlaceholderSprites();
        }
    }

    // Method tạo placeholder nếu không load được ảnh
    private static void createCatPlaceholderSprites() {
        int catWidth = 32;
        int catHeight = 32;

        for (int i = 0; i < 10; i++) {
            // Tạo frame màu đơn giản
            BufferedImage frame = new BufferedImage(catWidth, catHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = frame.createGraphics();

            // Mèo màu cam
            g.setColor(new java.awt.Color(255, 165, 0)); // Cam
            g.fillRect(0, 0, catWidth, catHeight);

            // Vẽ tai mèo
            g.setColor(java.awt.Color.BLACK);
            g.fillPolygon(new int[]{5, 10, 15}, new int[]{5, 0, 5}, 3); // Tai trái
            g.fillPolygon(new int[]{17, 22, 27}, new int[]{5, 0, 5}, 3); // Tai phải

            // Vẽ mắt mèo
            g.setColor(java.awt.Color.GREEN);
            g.fillOval(8, 10, 6, 8);  // Mắt trái
            g.fillOval(18, 10, 6, 8); // Mắt phải

            // Vẽ mũi
            g.setColor(java.awt.Color.PINK);
            g.fillOval(14, 18, 4, 3);

            // Vẽ râu
            g.setColor(java.awt.Color.WHITE);
            g.drawLine(8, 15, 2, 13);  // Râu trái 1
            g.drawLine(8, 17, 2, 17);  // Râu trái 2
            g.drawLine(8, 19, 2, 21);  // Râu trái 3
            g.drawLine(24, 15, 30, 13); // Râu phải 1
            g.drawLine(24, 17, 30, 17); // Râu phải 2
            g.drawLine(24, 19, 30, 21); // Râu phải 3

            g.dispose();

            spriteCatIdleRight[i] = frame;
            spriteCatIdleRightPoisoned[i] = GameUtil.createSpriteColor(frame, GameColors.POISONED);

            // Tạo left bằng cách flip
            spriteCatIdleLeft[i] = flipImageHorizontally(frame);
            spriteCatIdleLeftPoisoned[i] = GameUtil.createSpriteColor(spriteCatIdleLeft[i], GameColors.POISONED);
        }
    }

    // Method flip ảnh ngang (giữ nguyên)
    private static BufferedImage flipImageHorizontally(BufferedImage image) {
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

    // ========== PUBLIC METHODS FOR PLAYER (KNIGHT) ANIMATIONS ==========

    // Idle
    public static GameSpriteAnimation createSpritePlayerIdleRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, spritePlayerIdleRight, spritePlayerIdleRightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerIdleLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, spritePlayerIdleLeft, spritePlayerIdleLeftPoisoned);
    }

    // Walk
    public static GameSpriteAnimation createSpritePlayerWalkRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerWalkRight, spritePlayerWalkRightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerWalkLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerWalkLeft, spritePlayerWalkLeftPoisoned);
    }

    // Run
    public static GameSpriteAnimation createSpritePlayerRunRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 8, spritePlayerRunRight, spritePlayerRunRightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerRunLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 8, spritePlayerRunLeft, spritePlayerRunLeftPoisoned);
    }

    // Jump
    public static GameSpriteAnimation createSpritePlayerJumpRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 6, spritePlayerJumpRight, spritePlayerJumpRightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerJumpLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 6, spritePlayerJumpLeft, spritePlayerJumpLeftPoisoned);
    }

    // Attack1
    public static GameSpriteAnimation createSpritePlayerAttack1Right(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack1Right, spritePlayerAttack1RightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerAttack1Left(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack1Left, spritePlayerAttack1LeftPoisoned);
    }

    // Attack2
    public static GameSpriteAnimation createSpritePlayerAttack2Right(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack2Right, spritePlayerAttack2RightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerAttack2Left(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack2Left, spritePlayerAttack2LeftPoisoned);
    }

    // Attack3
    public static GameSpriteAnimation createSpritePlayerAttack3Right(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack3Right, spritePlayerAttack3RightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerAttack3Left(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerAttack3Left, spritePlayerAttack3LeftPoisoned);
    }

    // Walk+Attack
    public static GameSpriteAnimation createSpritePlayerWalkAttackRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 6, spritePlayerWalkAttackRight, spritePlayerWalkAttackRightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerWalkAttackLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 6, spritePlayerWalkAttackLeft, spritePlayerWalkAttackLeftPoisoned);
    }

    // Hurt
    public static GameSpriteAnimation createSpritePlayerHurtRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 3, spritePlayerHurtRight, spritePlayerHurtRightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerHurtLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 3, spritePlayerHurtLeft, spritePlayerHurtLeftPoisoned);
    }

    // Death
    public static GameSpriteAnimation createSpritePlayerDeath(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerDeath, spritePlayerDeathPoisoned);
    }

    // Defend
    public static GameSpriteAnimation createSpritePlayerDefendRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 8, spritePlayerDefendRight, spritePlayerDefendRightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerDefendLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 8, spritePlayerDefendLeft, spritePlayerDefendLeftPoisoned);
    }

    // Protect
    public static GameSpriteAnimation createSpritePlayerProtectRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerProtectRight, spritePlayerProtectRightPoisoned);
    }

    public static GameSpriteAnimation createSpritePlayerProtectLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, spritePlayerProtectLeft, spritePlayerProtectLeftPoisoned);
    }

    // Dust effects
    public static GameSpriteAnimation createSpriteDust(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, spritePlayerDust, null);
    }

    public static GameSpriteAnimation createSpriteWalkDust(GameRect rect) {
        return new GameSpriteAnimation(rect, 6, spritePlayerWalkDust, null);
    }

    // ========== CÁC METHOD CŨ (GIỮ LẠI CHO TƯƠNG THÍCH) ==========
    // Đổi tên method thành dùng cho Knight
    public static GameSpriteAnimation createSpriteOwletIdleRight(GameRect rect) {
        return createSpritePlayerIdleRight(rect);
    }

    public static GameSpriteAnimation createSpriteOwletIdleLeft(GameRect rect) {
        return createSpritePlayerIdleLeft(rect);
    }

    public static GameSpriteAnimation createSpriteOwletRunRight(GameRect rect) {
        return createSpritePlayerRunRight(rect);
    }

    public static GameSpriteAnimation createSpriteOwletRunLeft(GameRect rect) {
        return createSpritePlayerRunLeft(rect);
    }

    public static GameSpriteAnimation createSpriteOwletWalkRight(GameRect rect) {
        return createSpritePlayerWalkRight(rect);
    }

    public static GameSpriteAnimation createSpriteOwletWalkLeft(GameRect rect) {
        return createSpritePlayerWalkLeft(rect);
    }

    public static GameSpriteAnimation createSpriteOwletJumpRight(GameRect rect) {
        return createSpritePlayerJumpRight(rect);
    }

    public static GameSpriteAnimation createSpriteOwletJumpLeft(GameRect rect) {
        return createSpritePlayerJumpLeft(rect);
    }

    public static GameSpriteAnimation createSpriteOwletAttack1Right(GameRect rect) {
        return createSpritePlayerAttack1Right(rect);
    }

    public static GameSpriteAnimation createSpriteOwletAttack1Left(GameRect rect) {
        return createSpritePlayerAttack1Left(rect);
    }

    public static GameSpriteAnimation createSpriteOwletAttack2Right(GameRect rect) {
        return createSpritePlayerAttack2Right(rect);
    }

    public static GameSpriteAnimation createSpriteOwletAttack2Left(GameRect rect) {
        return createSpritePlayerAttack2Left(rect);
    }

    public static GameSpriteAnimation createSpriteOwletWalkAttackRight(GameRect rect) {
        return createSpritePlayerWalkAttackRight(rect);
    }

    public static GameSpriteAnimation createSpriteOwletWalkAttackLeft(GameRect rect) {
        return createSpritePlayerWalkAttackLeft(rect);
    }

    public static GameSpriteAnimation createSpriteOwletHurtRight(GameRect rect) {
        return createSpritePlayerHurtRight(rect);
    }

    public static GameSpriteAnimation createSpriteOwletHurtLeft(GameRect rect) {
        return createSpritePlayerHurtLeft(rect);
    }

    public static GameSpriteAnimation createSpriteOwletDeath(GameRect rect) {
        return createSpritePlayerDeath(rect);
    }

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
    // ... (các method createSprite hiện tại) ...

    // THÊM METHOD CHO MÈO
    public static GameSpriteAnimation createSpriteCatIdleRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteCatIdleRight, GameManagerSpritePlayer.spriteCatIdleRightPoisoned);
    }

    public static GameSpriteAnimation createSpriteCatIdleLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteCatIdleLeft, GameManagerSpritePlayer.spriteCatIdleLeftPoisoned);
    }
}