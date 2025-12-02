package dyrvania.managers.entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import dyrvania.generics.GameColors;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameUtil;
import dyrvania.resources.Spritesheet;

public class GameManagerSpritePlayer {

    // Sprites Normal
    private final static BufferedImage[] spriteIdleRight;
    private final static BufferedImage[] spriteIdleLeft;

    private final static BufferedImage[] spriteJumpRight;
    private final static BufferedImage[] spriteJumpLeft;

    private final static BufferedImage[] spriteRunRight;
    private final static BufferedImage[] spriteRunLeft;

    private final static BufferedImage[] spriteAttackRight;
    private final static BufferedImage[] spriteAttackLeft;

    // Sprites Poisoned
    private final static BufferedImage[] spriteIdleRightPoisoned;
    private final static BufferedImage[] spriteIdleLeftPoisoned;

    private final static BufferedImage[] spriteJumpRightPoisoned;
    private final static BufferedImage[] spriteJumpLeftPoisoned;

    private final static BufferedImage[] spriteRunRightPoisoned;
    private final static BufferedImage[] spriteRunLeftPoisoned;

    private final static BufferedImage[] spriteAttackRightPoisoned;
    private final static BufferedImage[] spriteAttackLeftPoisoned;

    // THÊM SPRITE CHO MÈO
    private final static BufferedImage[] spriteCatIdleRight;
    private final static BufferedImage[] spriteCatIdleLeft;
    private final static BufferedImage[] spriteCatIdleRightPoisoned;
    private final static BufferedImage[] spriteCatIdleLeftPoisoned;

    static {
        int spriteWidth = 100;
        int spriteHeight = 59;

        // Idle Right
        spriteIdleRight = new BufferedImage[4];
        spriteIdleRightPoisoned = new BufferedImage[4];

        for (int i = 0; i < spriteIdleRight.length; i++) {
            spriteIdleRight[i] = Spritesheet.getSpritePlayer(spriteWidth * i, 0, spriteWidth, spriteHeight);
            spriteIdleRightPoisoned[i] = GameUtil.createSpriteColor(spriteIdleRight[i], GameColors.POISONED);
        }

        // Idle Left
        spriteIdleLeft = new BufferedImage[4];
        spriteIdleLeftPoisoned = new BufferedImage[4];

        for (int i = 0; i < spriteIdleLeft.length; i++) {
            spriteIdleLeft[i] = Spritesheet.getSpritePlayer(spriteWidth * i, 59, spriteWidth, spriteHeight);
            spriteIdleLeftPoisoned[i] = GameUtil.createSpriteColor(spriteIdleLeft[i], GameColors.POISONED);
        }

        // Jump Right
        spriteJumpRight = new BufferedImage[2];
        spriteJumpRightPoisoned = new BufferedImage[2];

        for (int i = 0; i < spriteJumpRight.length; i++) {
            spriteJumpRight[i] = Spritesheet.getSpritePlayer(spriteWidth * i + 400, 0, spriteWidth, spriteHeight);
            spriteJumpRightPoisoned[i] = GameUtil.createSpriteColor(spriteJumpRight[i], GameColors.POISONED);
        }

        // Jump Left
        spriteJumpLeft = new BufferedImage[2];
        spriteJumpLeftPoisoned = new BufferedImage[2];

        for (int i = 0; i < spriteJumpLeft.length; i++) {
            spriteJumpLeft[i] = Spritesheet.getSpritePlayer(spriteWidth * i + 400, 59, spriteWidth, spriteHeight);
            spriteJumpLeftPoisoned[i] = GameUtil.createSpriteColor(spriteJumpLeft[i], GameColors.POISONED);
        }

        // Run Right
        spriteRunRight = new BufferedImage[6];
        spriteRunRightPoisoned = new BufferedImage[6];

        for (int i = 0; i < spriteRunRight.length; i++) {
            spriteRunRight[i] = Spritesheet.getSpritePlayer(spriteWidth * i, 118, spriteWidth, spriteHeight);
            spriteRunRightPoisoned[i] = GameUtil.createSpriteColor(spriteRunRight[i], GameColors.POISONED);
        }

        // Run Left
        spriteRunLeft = new BufferedImage[6];
        spriteRunLeftPoisoned = new BufferedImage[6];

        for (int i = 0; i < spriteRunLeft.length; i++) {
            spriteRunLeft[i] = Spritesheet.getSpritePlayer(spriteWidth * i, 177, spriteWidth, spriteHeight);
            spriteRunLeftPoisoned[i] = GameUtil.createSpriteColor(spriteRunLeft[i], GameColors.POISONED);
        }

        // Attack Right
        spriteAttackRight = new BufferedImage[5];
        spriteAttackRightPoisoned = new BufferedImage[5];

        for (int i = 0; i < spriteAttackRight.length; i++) {
            spriteAttackRight[i] = Spritesheet.getSpritePlayer(spriteWidth * i, 236, spriteWidth, spriteHeight);
            spriteAttackRightPoisoned[i] = GameUtil.createSpriteColor(spriteAttackRight[i], GameColors.POISONED);
        }

        // Attack Left
        spriteAttackLeft = new BufferedImage[5];
        spriteAttackLeftPoisoned = new BufferedImage[5];

        for (int i = 0; i < spriteAttackLeft.length; i++) {
            spriteAttackLeft[i] = Spritesheet.getSpritePlayer(spriteWidth * i, 295, spriteWidth, spriteHeight);
            spriteAttackLeftPoisoned[i] = GameUtil.createSpriteColor(spriteAttackLeft[i], GameColors.POISONED);
        }

        // === THÊM PHẦN MÈO ===
        int catWidth = 32;
        int catHeight = 32;

        // Cat Idle Right
        spriteCatIdleRight = new BufferedImage[10];
        spriteCatIdleRightPoisoned = new BufferedImage[10];

        for (int i = 0; i < spriteCatIdleRight.length; i++) {
            spriteCatIdleRight[i] = createCatPlaceholder(i, catWidth, catHeight);
            spriteCatIdleRightPoisoned[i] = GameUtil.createSpriteColor(spriteCatIdleRight[i], GameColors.POISONED);
        }

        // Cat Idle Left
        spriteCatIdleLeft = new BufferedImage[10];
        spriteCatIdleLeftPoisoned = new BufferedImage[10];

        for (int i = 0; i < spriteCatIdleLeft.length; i++) {
            spriteCatIdleLeft[i] = flipImageHorizontally(spriteCatIdleRight[i]);
            spriteCatIdleLeftPoisoned[i] = GameUtil.createSpriteColor(spriteCatIdleLeft[i], GameColors.POISONED);
        }
    }

    // Method tạo placeholder cho mèo
    private static BufferedImage createCatPlaceholder(int frameIndex, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        // Màu sắc cho 10 frame mèo
        java.awt.Color[] colors = {
                new java.awt.Color(255, 100, 100),   // Đỏ
                new java.awt.Color(255, 150, 50),    // Cam
                new java.awt.Color(255, 255, 100),   // Vàng
                new java.awt.Color(100, 255, 100),   // Xanh lá
                new java.awt.Color(100, 200, 255),   // Xanh dương
                new java.awt.Color(200, 100, 255),   // Tím
                new java.awt.Color(255, 100, 200),   // Hồng
                new java.awt.Color(255, 200, 100),   // Cam nhạt
                new java.awt.Color(150, 255, 200),   // Xanh ngọc
                new java.awt.Color(200, 200, 255)    // Xanh nhạt
        };

        java.awt.Color color = colors[frameIndex % colors.length];

        // Vẽ hình vuông màu
        g.setColor(color);
        g.fillRect(0, 0, width, height);

        // Vẽ viền đen
        g.setColor(java.awt.Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        // Vẽ số frame
        g.setColor(java.awt.Color.BLACK);
        g.drawString("C" + frameIndex, 5, 20);

        g.dispose();
        return img;
    }

    // Method flip ảnh ngang
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

    public static GameSpriteAnimation createSpriteIdleRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteIdleRight, GameManagerSpritePlayer.spriteIdleRightPoisoned);
    }

    public static GameSpriteAnimation createSpriteIdleLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteIdleLeft, GameManagerSpritePlayer.spriteIdleLeftPoisoned);
    }

    public static GameSpriteAnimation createSpriteJumpRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteJumpRight, GameManagerSpritePlayer.spriteJumpRightPoisoned);
    }

    public static GameSpriteAnimation createSpriteJumpLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteJumpLeft, GameManagerSpritePlayer.spriteJumpLeftPoisoned);
    }

    public static GameSpriteAnimation createSpriteRunRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, GameManagerSpritePlayer.spriteRunRight, GameManagerSpritePlayer.spriteRunRightPoisoned);
    }

    public static GameSpriteAnimation createSpriteRunLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 10, GameManagerSpritePlayer.spriteRunLeft, GameManagerSpritePlayer.spriteRunLeftPoisoned);
    }

    public static GameSpriteAnimation createSpriteAttackRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, GameManagerSpritePlayer.spriteAttackRight, GameManagerSpritePlayer.spriteAttackRightPoisoned);
    }

    public static GameSpriteAnimation createSpriteAttackLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 5, GameManagerSpritePlayer.spriteAttackLeft, GameManagerSpritePlayer.spriteAttackLeftPoisoned);
    }

    // THÊM METHOD CHO MÈO
    public static GameSpriteAnimation createSpriteCatIdleRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteCatIdleRight, GameManagerSpritePlayer.spriteCatIdleRightPoisoned);
    }

    public static GameSpriteAnimation createSpriteCatIdleLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteCatIdleLeft, GameManagerSpritePlayer.spriteCatIdleLeftPoisoned);
    }
}