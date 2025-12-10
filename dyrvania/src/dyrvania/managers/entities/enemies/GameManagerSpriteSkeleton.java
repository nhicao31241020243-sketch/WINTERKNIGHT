package dyrvania.managers.entities.enemies;

import java.awt.image.BufferedImage;

import dyrvania.generics.GameColors;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.generics.GameUtil;
import dyrvania.resources.Spritesheet;

public class GameManagerSpriteSkeleton {

    // Sprites Normal
    private final static BufferedImage[] spriteRunRight;
    private final static BufferedImage[] spriteRunLeft;
    private final static BufferedImage[] spriteSpawnRight;
    private final static BufferedImage[] spriteSpawnLeft;

    // Sprites Damage
    private final static BufferedImage[] spriteRunRightDamage;
    private final static BufferedImage[] spriteRunLeftDamage;

    static {
        int spriteWidth = 90;
        int spriteHeight = 64;

        // Run Right
        spriteRunRight = new BufferedImage[8];
        spriteRunRightDamage = new BufferedImage[8];

        for (int i = 0; i < spriteRunRight.length; i++) {
            spriteRunRight[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 192, spriteWidth, spriteHeight);
            spriteRunRightDamage[i] = GameUtil.createSpriteColor(spriteRunRight[i], GameColors.DAMAGE);
        }

        // Run Left
        spriteRunLeft = new BufferedImage[8];
        spriteRunLeftDamage = new BufferedImage[8];

        for (int i = 0; i < spriteRunLeft.length; i++) {
            spriteRunLeft[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 128, spriteWidth, spriteHeight);
            spriteRunLeftDamage[i] = GameUtil.createSpriteColor(spriteRunLeft[i], GameColors.DAMAGE);
        }

        // Spawn Right
        spriteSpawnRight = new BufferedImage[6];

        for (int i = 0; i < spriteSpawnRight.length; i++) {
            spriteSpawnRight[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 64, spriteWidth, spriteHeight);
        }

        // Spawn Left
        spriteSpawnLeft = new BufferedImage[6];

        for (int i = 0; i < spriteSpawnLeft.length; i++) {
            spriteSpawnLeft[i] = Spritesheet.getSpriteSkeleton(spriteWidth * i, 0, spriteWidth, spriteHeight);
        }
    }

    public static GameSpriteAnimation createSpriteRunRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 13, GameManagerSpriteSkeleton.spriteRunRight, GameManagerSpriteSkeleton.spriteRunRightDamage);
    }

    public static GameSpriteAnimation createSpriteRunLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 13, GameManagerSpriteSkeleton.spriteRunLeft, GameManagerSpriteSkeleton.spriteRunLeftDamage);
    }

    public static GameSpriteAnimation createSpriteSpawnRight(GameRect rect) {
        return new GameSpriteAnimation(rect, 13, GameManagerSpriteSkeleton.spriteSpawnRight, GameManagerSpriteSkeleton.spriteSpawnRight);
    }

    public static GameSpriteAnimation createSpriteSpawnLeft(GameRect rect) {
        return new GameSpriteAnimation(rect, 13, GameManagerSpriteSkeleton.spriteSpawnLeft, GameManagerSpriteSkeleton.spriteSpawnLeft);
    }

}
