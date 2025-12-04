package dyrvania.scenes.entities;

import java.awt.Graphics;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import dyrvania.generics.Camera;
import dyrvania.generics.GameColors;
import dyrvania.generics.GameDamage;
import dyrvania.generics.GameDamage.GameDamageType;
import dyrvania.generics.GameRect;
import dyrvania.generics.GameRectEntity;
import dyrvania.generics.GameSpriteAnimation;
import dyrvania.managers.GameManagerAudio;
import dyrvania.managers.entities.GameManagerSpritePlayer;
import dyrvania.resources.GameFont;
import dyrvania.saves.GameSaveManager;
import dyrvania.scenes.Scene;

public class Player {

    private Scene scene;

    private final GameRectEntity rect;
    private final GameRect rectAttack;

    private int hp;
    private int hpMax;

    private int poisonControl;
    private boolean isPoisoning;

    protected long shieldTime;
    protected boolean shieldActive;
    protected LocalDateTime shieldDamage;

    private GameDamage damage;

    private boolean isAttacking;
    private boolean canDealDamage;
    private int attackAnimationCounter;

    private boolean isPushing;
    private boolean isClimbing;
    private boolean isThrowing;
    private boolean isHurt;
    private int hurtTimer;

    private float speedX;
    private float speedY;

    private boolean keyRight;
    private boolean keyLeft;
    private boolean keyJump;
    private boolean keyAttack;

    private boolean isJump;
    private boolean canDoubleJump;
    private final float jumpHeight;
    private float jumpFrames;

    private boolean isDirRight;
    private GameSpriteAnimation currentSprite;
    private GameSpriteAnimation dustSprite;
    private boolean showDust;

    // Animation map
    private final Map<String, GameSpriteAnimation> animations;
    private String currentAnimationKey;
    private boolean wasJumping;
    private boolean wasAttacking;

    // Kích thước cho KNIGHT
    private static final int HITBOX_WIDTH = 60;
    private static final int HITBOX_HEIGHT = 80;
    private static final int ATTACK_WIDTH = 70;
    private static final int ATTACK_HEIGHT = 50;
    private static final int SPRITE_OFFSET_X = -13;
    private static final int SPRITE_OFFSET_Y = -3;

    public Player() {
        this.rect = new GameRectEntity(0, 0, HITBOX_WIDTH, HITBOX_HEIGHT);
        this.rectAttack = new GameRect(0, 0, ATTACK_WIDTH, ATTACK_HEIGHT);

        this.hpMax = 9;
        this.hp = this.hpMax;

        this.poisonControl = 0;
        this.isPoisoning = false;

        this.shieldTime = 2;
        this.shieldActive = false;
        this.shieldDamage = LocalDateTime.now().minusSeconds(this.shieldTime);

        this.damage = new GameDamage(0, GameDamageType.FIRE);
        this.isAttacking = false;
        this.canDealDamage = false;
        this.attackAnimationCounter = 0;

        this.isPushing = false;
        this.isClimbing = false;
        this.isThrowing = false;
        this.isHurt = false;
        this.hurtTimer = 0;

        this.speedX = 3f;
        this.speedY = 0f;

        this.keyRight = false;
        this.keyLeft = false;
        this.keyJump = false;
        this.keyAttack = false;

        this.isJump = false;
        this.canDoubleJump = false;
        this.jumpHeight = 100f;
        this.jumpFrames = 0f;

        this.isDirRight = true;
        this.showDust = false;

        this.animations = new HashMap<>();
        this.currentAnimationKey = "idle_right";
        this.wasJumping = false;
        this.wasAttacking = false;

        initializeAnimations();

        this.currentSprite = this.animations.get("idle_right");

        // Tạo dust sprite
        this.dustSprite = createDustSprite();

        this.setSpritePosition();
    }

    private GameSpriteAnimation createDustSprite() {
        try {
            return GameManagerSpritePlayer.createSpriteDust(new GameRect(0, 0, 86, 86));
        } catch (Exception e) {
            return null;
        }
    }

    private void initializeAnimations() {
        // Kích thước sprite cho KNIGHT (86x86)
        GameRect spriteRect = new GameRect(0, 0, 86, 86);

        this.animations.put("idle_right", GameManagerSpritePlayer.createSpriteOwletIdleRight(spriteRect));
        this.animations.put("idle_left", GameManagerSpritePlayer.createSpriteOwletIdleLeft(spriteRect));

        this.animations.put("run_right", GameManagerSpritePlayer.createSpriteOwletRunRight(spriteRect));
        this.animations.put("run_left", GameManagerSpritePlayer.createSpriteOwletRunLeft(spriteRect));

        this.animations.put("walk_right", GameManagerSpritePlayer.createSpriteOwletWalkRight(spriteRect));
        this.animations.put("walk_left", GameManagerSpritePlayer.createSpriteOwletWalkLeft(spriteRect));

        this.animations.put("jump_right", GameManagerSpritePlayer.createSpriteOwletJumpRight(spriteRect));
        this.animations.put("jump_left", GameManagerSpritePlayer.createSpriteOwletJumpLeft(spriteRect));

        this.animations.put("attack1_right", GameManagerSpritePlayer.createSpriteOwletAttack1Right(spriteRect));
        this.animations.put("attack1_left", GameManagerSpritePlayer.createSpriteOwletAttack1Left(spriteRect));

        this.animations.put("attack2_right", GameManagerSpritePlayer.createSpriteOwletAttack2Right(spriteRect));
        this.animations.put("attack2_left", GameManagerSpritePlayer.createSpriteOwletAttack2Left(spriteRect));

        this.animations.put("walk_attack_right", GameManagerSpritePlayer.createSpriteOwletWalkAttackRight(spriteRect));
        this.animations.put("walk_attack_left", GameManagerSpritePlayer.createSpriteOwletWalkAttackLeft(spriteRect));

        this.animations.put("hurt_right", GameManagerSpritePlayer.createSpriteOwletHurtRight(spriteRect));
        this.animations.put("hurt_left", GameManagerSpritePlayer.createSpriteOwletHurtLeft(spriteRect));

        this.animations.put("death", GameManagerSpritePlayer.createSpriteOwletDeath(spriteRect));
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void resetJump() {
        this.jumpFrames = 0f;
        this.speedY = 0f;
    }

    public int getHp() {
        return this.hp;
    }

    public int getHpMax() {
        return this.hpMax;
    }

    public int getDamage() {
        return this.damage.getDamage();
    }

    public boolean isPoisoning() {
        return this.isPoisoning;
    }

    public void setPoisoning(boolean isPoisoning) {
        this.isPoisoning = isPoisoning;
    }

    public GameRect getRect() {
        return this.rect.getRect();
    }

    public boolean isDead() {
        return this.hp <= 0 && this.currentSprite.finishedAnimation();
    }

    public boolean isAttacking() {
        return this.isAttacking && this.canDealDamage;
    }

    public GameRect getAreaAttack() {
        return this.rectAttack;
    }

    public GameDamage dealDamage() {
        return this.damage;
    }

    public boolean finishedAnimation() {
        return this.currentSprite.getIndex() == 0;
    }

    public void takeDamage(GameDamage damage) {
        if (!this.shieldActive && this.hp > 0) {
            GameManagerAudio.getAudioPlayerHit().play();

            this.hp -= damage.getDamage();
            this.shieldDamage = LocalDateTime.now().plusSeconds(this.shieldTime);
            this.isHurt = true;
            this.hurtTimer = 15;

            if (this.hp < 0) {
                this.hp = 0;
            }

            if (damage.getType() == GameDamageType.POISON) {
                this.isPoisoning = true;
            }
        }
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    public void setDamage(int damage) {
        this.damage.setDamage(damage);
    }

    public void setPosition(int x, int y) {
        this.rect.setX(x);
        this.rect.setY(y);
        this.setSpritePosition();
    }

    public void moveRight() {
        this.keyRight = true;
    }

    public void stopRight() {
        this.keyRight = false;
    }

    public void moveLeft() {
        this.keyLeft = true;
    }

    public void stopLeft() {
        this.keyLeft = false;
    }

    public void toJump() {
        if (!this.isJump && !this.keyJump && this.isOnTheFloor() && this.hp > 0) {
            GameManagerAudio.getAudioPlayerJump().play();

            this.isJump = true;
            this.keyJump = true;
            this.canDoubleJump = true;
            this.wasJumping = true;

            // Show dust effect
            this.showDustEffect();
        } else if (GameSaveManager.getSave().isBossDefeated() && this.canDoubleJump && this.hp > 0) {
            GameManagerAudio.getAudioPlayerJump().play();

            this.canDoubleJump = false;
            this.toJumpSpecial();

            // Show dust effect for double jump
            this.showDustEffect();
        }
    }

    private void showDustEffect() {
        this.showDust = true;
        if (this.dustSprite != null) {
            this.dustSprite.reset();
            this.dustSprite.setPosition(
                    (int)(this.rect.getX() + (this.isDirRight ? -10 : HITBOX_WIDTH + 10)),
                    (int)(this.rect.getY() + HITBOX_HEIGHT - 20)
            );
        }
    }

    public void toJumpSpecial() {
        this.resetJump();
        this.isJump = true;
    }

    public void keyJumpReleased() {
        this.keyJump = false;
    }

    public void toAttack() {
        if (!this.keyAttack && !this.isAttacking && this.hp > 0) {
            GameManagerAudio.getAudioPlayerAttack().play();

            this.keyAttack = true;
            this.isAttacking = true;
            this.wasAttacking = true;
            this.attackAnimationCounter = 0;

            // Reset attack animation khi bắt đầu tấn công
            String attackKey = this.isDirRight ? "attack1_right" : "attack1_left";
            if (this.animations.containsKey(attackKey)) {
                this.animations.get(attackKey).reset();
            }
        }
    }

    public void keyAttackReleased() {
        this.keyAttack = false;
    }

    public void toHeal() {
        this.isPoisoning = false;
        this.hp = this.hpMax;
    }

    public void increaseHp() {
        if (this.hpMax < 999) {
            this.hpMax++;
        }
    }

    public void increaseAttack() {
        if (this.damage.getDamage() < 99) {
            this.damage.setDamage(this.damage.getDamage() + 1);
        }
    }

    public boolean isDirRight() {
        return this.isDirRight;
    }

    public void setDir(boolean dir) {
        this.isDirRight = dir;
    }

    private void applyGravity() {
        this.speedY += this.scene.getGravity();

        // Giới hạn tốc độ rơi
        if (this.speedY > 7f) {
            this.speedY = 7f;
        }

        // Kiểm tra từng bước nhỏ để tránh xuyên qua tile
        float step = 0.5f;
        for (float i = 0f; i <= this.speedY; i += step) {
            if (!this.isOnTheFloor()) {
                this.rect.setY(this.rect.getY() + step);
            } else {
                // Khi chạm đất, reset speedY và đặt nhân vật lên trên tile
                this.speedY = 0f;
                this.canDoubleJump = false;

                // Điều chỉnh vị trí để không bị lún xuống tile
                while (!this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + 0.1f,
                        this.rect.getWidth(), this.rect.getHeight()).getRect())) {
                    this.rect.setY(this.rect.getY() - 0.1f);
                }
                break;
            }
        }
    }

    private void toMove() {
        if (this.keyRight && !this.isAttacking && !this.isThrowing) {
            this.isDirRight = true;

            for (float i = 0f; i <= this.speedX; i += 0.5f) {
                if (this.scene.isFree(new GameRectEntity(this.rect.getX() + 0.5f, this.rect.getY(),
                        this.rect.getWidth(), this.rect.getHeight()).getRect())) {
                    this.rect.setX(this.rect.getX() + 0.5f);

                    if (!this.isJump && !this.isOnTheFloor()) {
                        this.rect.setY(this.rect.getY() + 0.5f);
                    }
                } else {
                    break;
                }
            }
        }

        if (this.keyLeft && !this.isAttacking && !this.isThrowing) {
            this.isDirRight = false;

            for (float i = 0f; i <= this.speedX; i += 0.5f) {
                if (this.scene.isFree(new GameRectEntity(this.rect.getX() - 0.5f, this.rect.getY(),
                        this.rect.getWidth(), this.rect.getHeight()).getRect())) {
                    this.rect.setX(this.rect.getX() - 0.5f);

                    if (!this.isJump && !this.isOnTheFloor()) {
                        this.rect.setY(this.rect.getY() + 0.5f);
                    }
                } else {
                    break;
                }
            }
        }
    }

    private void jump() {
        // Tốc độ nhảy theo frame
        if (this.jumpFrames < 10f) {
            this.speedY = 8f;
        } else if (this.jumpFrames < 20f) {
            this.speedY = 7f;
        } else if (this.jumpFrames < 30f) {
            this.speedY = 6f;
        } else if (this.jumpFrames < 40f) {
            this.speedY = 5f;
        } else if (this.jumpFrames < 50f) {
            this.speedY = 4f;
        } else {
            this.speedY = 3f;
        }

        // Di chuyển lên trên
        for (float i = 0f; i <= this.speedY; i += 0.5f) {
            if (this.jumpFrames < this.jumpHeight &&
                    this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() - 0.5f,
                            this.rect.getWidth(), this.rect.getHeight()).getRect())) {
                this.rect.setY(this.rect.getY() - 0.5f);
                this.jumpFrames += 0.5f;
            } else {
                // Chạm trần hoặc đạt độ cao tối đa
                this.speedY = 0f;
                this.jumpFrames = 0f;
                this.isJump = false;
                this.wasJumping = false;
                break;
            }
        }

        // Kiểm tra nếu đã chạm đất
        if (this.isOnTheFloor()) {
            this.isJump = false;
            this.jumpFrames = 0f;
            this.wasJumping = false;

            // Show dust effect when landing
            this.showDustEffect();
        }
    }

    private boolean isOnTheFloor() {
        // Kiểm tra 5 pixel dưới chân nhân vật
        for (float i = 0.5f; i <= 5f; i += 0.5f) {
            if (!this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() + i,
                    this.rect.getWidth(), this.rect.getHeight()).getRect())) {
                return true;
            }
        }
        return false;
    }

    private void setSpritePosition() {
        int x = (int) this.rect.getRect().getX() + SPRITE_OFFSET_X;
        int y = (int) this.rect.getRect().getY() + SPRITE_OFFSET_Y;

        this.currentSprite.setPosition(x, y);

        if (this.showDust && this.dustSprite != null) {
            this.dustSprite.setPosition(
                    (int)(this.rect.getX() + (this.isDirRight ? -10 : HITBOX_WIDTH + 10)),
                    (int)(this.rect.getY() + HITBOX_HEIGHT - 20)
            );
        }
    }

    private String determineAnimationKey() {
        if (this.hp <= 0) {
            return "death";
        }

        // Hurt animation
        if (this.isHurt && this.hurtTimer > 0) {
            return this.isDirRight ? "hurt_right" : "hurt_left";
        }

        if (this.isAttacking) {
            // Khi đang tấn công, luôn giữ attack animation
            this.wasAttacking = true;

            if (this.keyRight || this.keyLeft) {
                return this.isDirRight ? "walk_attack_right" : "walk_attack_left";
            } else {
                return this.isDirRight ? "attack1_right" : "attack1_left";
            }
        } else if (this.isJump || !this.isOnTheFloor()) {
            // Khi nhảy, luôn giữ jump animation
            this.wasJumping = true;
            return this.isDirRight ? "jump_right" : "jump_left";
        } else if (this.keyRight && this.keyLeft) {
            return this.isDirRight ? "idle_right" : "idle_left";
        } else if (this.keyRight || this.keyLeft) {
            if (this.speedX > 2.5f) {
                return this.isDirRight ? "run_right" : "run_left";
            } else {
                return this.isDirRight ? "walk_right" : "walk_left";
            }
        } else {
            return this.isDirRight ? "idle_right" : "idle_left";
        }
    }

    public void tick() {
        if (this.hp <= 0) {
            String newKey = "death";
            if (!newKey.equals(this.currentAnimationKey)) {
                this.currentAnimationKey = newKey;
                this.currentSprite = this.animations.get(newKey);
                if (this.currentSprite != null) {
                    this.currentSprite.reset();
                    this.setSpritePosition();
                }
            }
            if (this.currentSprite != null) {
                this.currentSprite.tick();
            }
            return;
        }

        // Update hurt timer
        if (this.hurtTimer > 0) {
            this.hurtTimer--;
            if (this.hurtTimer <= 0) {
                this.isHurt = false;
            }
        }

        // Handle poison
        if (this.isPoisoning && this.hp > 1) {
            this.poisonControl++;

            if (this.poisonControl == 300) {
                GameManagerAudio.getAudioPlayerHit().play();
                this.hp--;
                this.poisonControl = 0;

                this.isHurt = true;
                this.hurtTimer = 10;
            }
        } else {
            this.poisonControl = 0;
            this.isPoisoning = false;
        }

        // Handle shield
        if (this.shieldDamage.isBefore(LocalDateTime.now())) {
            this.shieldActive = false;
            if (this.currentSprite != null) {
                this.currentSprite.setAlpha(1f);
            }
        } else {
            this.shieldActive = true;
            if (this.currentSprite != null) {
                this.currentSprite.setAlpha(0.5f);
            }
        }

        // Handle jump
        if (this.isJump) {
            this.jump();
        } else {
            this.applyGravity();
        }

        // Handle movement
        this.toMove();

        // Xác định animation mới
        String newAnimationKey = this.determineAnimationKey();

        // Chỉ đổi animation nếu khác với hiện tại và tồn tại trong map
        if (!newAnimationKey.equals(this.currentAnimationKey) && this.animations.containsKey(newAnimationKey)) {
            // Đặc biệt: không đổi animation nếu đang trong jump/attack và chưa kết thúc
            if ((this.currentAnimationKey.contains("jump") && !this.isOnTheFloor()) ||
                    (this.currentAnimationKey.contains("attack") && this.isAttacking)) {
                // Giữ nguyên animation hiện tại
            } else {
                this.currentAnimationKey = newAnimationKey;
                GameSpriteAnimation newSprite = this.animations.get(newAnimationKey);

                if (newSprite != null) {
                    this.currentSprite = newSprite;
                    this.setSpritePosition();
                }
            }
        }

        // Update attack position
        if (this.isDirRight) {
            this.rectAttack.setX((int)(this.rect.getX() + HITBOX_WIDTH));
        } else {
            this.rectAttack.setX((int)(this.rect.getX() - ATTACK_WIDTH));
        }
        this.rectAttack.setY((int)(this.rect.getY() + 20));

        this.setSpritePosition();

        // Update animation
        if (this.currentSprite != null) {
            this.currentSprite.tick();
        }

        // Handle dust effect
        if (this.showDust && this.dustSprite != null) {
            this.dustSprite.tick();

            if (this.dustSprite.finishedAnimation()) {
                this.showDust = false;
                this.dustSprite.reset();
            }
        }

        // Handle attacking
        if (this.isAttacking) {
            this.attackAnimationCounter++;

            // Allow dealing damage ở frame giữa
            if (this.currentSprite != null &&
                    this.currentSprite.getIndex() >= 2 && this.currentSprite.getIndex() <= 4) {
                this.canDealDamage = true;
            } else {
                this.canDealDamage = false;
            }

            // Kết thúc attack sau khi animation xong hoặc quá thời gian
            if ((this.currentSprite != null && this.currentSprite.finishedAnimation()) ||
                    this.attackAnimationCounter > 40) {
                this.isAttacking = false;
                this.canDealDamage = false;
                this.attackAnimationCounter = 0;
                this.wasAttacking = false;
            }
        }

        this.setCamera();
    }

    private void setCamera() {
        Camera.x = Camera.clamp(this.rect.getRect().getX() - (this.scene.getGame().getGameWidth() / 2),
                0, this.scene.getWidth() * this.scene.getSizeBaseTiles() - this.scene.getGame().getGameWidth());
        Camera.y = Camera.clamp(this.rect.getRect().getY() - (this.scene.getGame().getGameHeight() / 2),
                0, this.scene.getHeight() * this.scene.getSizeBaseTiles() - this.scene.getGame().getGameHeight());
    }

    public void render(Graphics render) {
        // Render dust effect
        if (this.showDust && this.dustSprite != null) {
            this.dustSprite.render(render);
        }

        // Render player
        if (this.currentSprite != null) {
            if (this.isPoisoning) {
                this.currentSprite.renderSpritesSecondary(render);
            } else {
                this.currentSprite.render(render);
            }
        }

        // Render UI
        this.renderHp(render);
        this.renderAttack(render);
    }

    private void renderHp(Graphics render) {
        render.setColor(GameColors.BLACK);
        render.fillRect(5, 5, 150, 20);

        render.setColor(GameColors.WHITE);
        render.setFont(GameFont.getTinyFont());
        render.drawString(String.format("HP: %03d | %03d", this.hp, this.hpMax), 15, 20);

        render.drawRect(5, 5, 150, 20);
    }

    private void renderAttack(Graphics render) {
        render.setColor(GameColors.BLACK);
        render.fillRect(165, 5, 90, 20);

        render.setColor(GameColors.WHITE);
        render.setFont(GameFont.getTinyFont());
        render.drawString(String.format("ATK: %02d", this.damage.getDamage()), 175, 20);

        render.drawRect(165, 5, 90, 20);
    }
}