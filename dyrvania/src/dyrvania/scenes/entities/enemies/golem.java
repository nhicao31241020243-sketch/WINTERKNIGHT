package dyrvania.scenes.entities.enemies;

import dyrvania.generics.GameDamage;
import dyrvania.generics.GameDamage.GameDamageType;
import dyrvania.generics.GameRect;
import dyrvania.managers.GameManagerSpriteDeath;
import dyrvania.managers.entities.enemies.GameManagerSpriteSkeleton;
import dyrvania.scenes.Scene;

public class Golem extends Enemy {

    public Golem(Scene scene, int x, int y) {
        super(scene, x, y, 55, 60, 30, new GameDamage(6, GameDamageType.NORMAL), 1.0f);
    }

    @Override
    public void loadSprites() {
        GameRect spriteRect = new GameRect(0, 0, 64, 64); // Golem lớn hơn

        // DÙNG SPRITE CỦA SKELETON (tạm thời để tránh lỗi)
        super.spriteRunRight = GameManagerSpriteSkeleton.createSpriteRunRight(spriteRect);
        super.spriteRunLeft = GameManagerSpriteSkeleton.createSpriteRunLeft(spriteRect);

        super.spriteDeath = GameManagerSpriteDeath.createSpriteDeath(spriteRect);

        // Khởi tạo sprite hiện tại
        if (super.isDirRight) {
            super.currentSprite = super.spriteRunRight;
        } else {
            super.currentSprite = super.spriteRunLeft;
        }
    }

    @Override
    protected void setSpritePosition() {
        // Offset cho Golem (lớn hơn Skeleton)
        if (super.isDirRight) {
            super.currentSprite.setPosition(super.rect.getRect().getX() - 20, super.rect.getRect().getY() - 20);
        } else {
            super.currentSprite.setPosition(super.rect.getRect().getX() - 30, super.rect.getRect().getY() - 20);
        }
    }

    @Override
    public void tick() {
        super.tick();
    }
}