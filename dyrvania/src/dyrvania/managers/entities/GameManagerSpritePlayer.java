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

    // ... (các method createSprite hiện tại) ...

    // THÊM METHOD CHO MÈO
    public static GameSpriteAnimation createSpriteCatIdleRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteCatIdleRight, GameManagerSpritePlayer.spriteCatIdleRightPoisoned);
    }

    public static GameSpriteAnimation createSpriteCatIdleLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 15, GameManagerSpritePlayer.spriteCatIdleLeft, GameManagerSpritePlayer.spriteCatIdleLeftPoisoned);
    }
}