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

    private final GameDamage damage;  // Sửa thành final
    private final boolean isPushing = false;  // Sửa thành final

    private boolean isAttacking;
    private boolean canDealDamage;
    private int attackAnimationCounter;

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

    // Kích thước cho Player
    private static final int HITBOX_WIDTH = 60;
    private static final int HITBOX_HEIGHT = 80;
    private static final int ATTACK_WIDTH = 70;
    private static final int ATTACK_HEIGHT = 50;
    private static final int SPRITE_OFFSET_X = -13;
    private static final int SPRITE_OFFSET_Y = -3;

    // Constants
    private static final float MAX_FALL_SPEED = 7f;
    private static final float MOVEMENT_STEP = 0.5f;
    private static final float FLOOR_CHECK_DISTANCE = 5f;
    private static final int POISON_DAMAGE_INTERVAL = 300;
    private static final int HURT_DURATION = 15;
    private static final int ATTACK_MAX_FRAMES = 40;
    private static final int ATTACK_DAMAGE_FRAME_START = 2;
    private static final int ATTACK_DAMAGE_FRAME_END = 4;

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

        // Tạo dust sprite - SỬA: dùng createSpriteDust với 2 parameters
        this.dustSprite = createDustSprite();

        this.setSpritePosition();
    }

    private GameSpriteAnimation createDustSprite() {
        try {
            return GameManagerSpritePlayer.createSpriteDust(new GameRect(0, 0, 86, 86), true);
        } catch (Exception e) {
            return null;
        }
    }

    private void initializeAnimations() {
        // Kích thước sprite cho Player (86x86)
        GameRect spriteRect = new GameRect(0, 0, 86, 86);

        // Sử dụng đúng tên phương thức từ GameManagerSpritePlayer
        this.animations.put("idle_right", GameManagerSpritePlayer.createSpritePlayerIdleRight(spriteRect));
        this.animations.put("idle_left", GameManagerSpritePlayer.createSpritePlayerIdleLeft(spriteRect));

        this.animations.put("run_right", GameManagerSpritePlayer.createSpritePlayerRunRight(spriteRect));
        this.animations.put("run_left", GameManagerSpritePlayer.createSpritePlayerRunLeft(spriteRect));

        this.animations.put("walk_right", GameManagerSpritePlayer.createSpritePlayerWalkRight(spriteRect));
        this.animations.put("walk_left", GameManagerSpritePlayer.createSpritePlayerWalkLeft(spriteRect));

        this.animations.put("jump_right", GameManagerSpritePlayer.createSpritePlayerJumpRight(spriteRect));
        this.animations.put("jump_left", GameManagerSpritePlayer.createSpritePlayerJumpLeft(spriteRect));

        this.animations.put("attack1_right", GameManagerSpritePlayer.createSpritePlayerAttack1Right(spriteRect));
        this.animations.put("attack1_left", GameManagerSpritePlayer.createSpritePlayerAttack1Left(spriteRect));

        this.animations.put("attack2_right", GameManagerSpritePlayer.createSpritePlayerAttack2Right(spriteRect));
        this.animations.put("attack2_left", GameManagerSpritePlayer.createSpritePlayerAttack2Left(spriteRect));

        // SỬA: Dùng createSpritePlayerRunAttack thay vì createSpritePlayerWalkAttack
        this.animations.put("walk_attack_right", GameManagerSpritePlayer.createSpritePlayerRunAttackRight(spriteRect));
        this.animations.put("walk_attack_left", GameManagerSpritePlayer.createSpritePlayerRunAttackLeft(spriteRect));

        this.animations.put("hurt_right", GameManagerSpritePlayer.createSpritePlayerHurtRight(spriteRect));
        this.animations.put("hurt_left", GameManagerSpritePlayer.createSpritePlayerHurtLeft(spriteRect));

        this.animations.put("death", GameManagerSpritePlayer.createSpritePlayerDeath(spriteRect));
    }

    // Các phương thức khác giữ nguyên...
    // (Phần còn lại của code giữ nguyên như trước)

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
            this.hurtTimer = HURT_DURATION;

            if (this.hp < 0) {
                this.hp = 0;
            }

            if (damage.getType() == GameDamageType.POISON) {
                this.isPoisoning = true;
            }
        }
    }

    public void setHp(int hp) {
        if (hp < 0) hp = 0;
        if (hp > this.hpMax) hp = this.hpMax;
        this.hp = hp;
    }

    public void setHpMax(int hpMax) {
        if (hpMax < 0) hpMax = 0;
        if (hpMax > 999) hpMax = 999;
        this.hpMax = hpMax;
        if (this.hp > this.hpMax) {
            this.hp = this.hpMax;
        }
    }

    public void setDamage(int damageValue) {
        if (damageValue < 0) damageValue = 0;
        if (damageValue > 99) damageValue = 99;
        this.damage.setDamage(damageValue);
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
            this.hp = this.hpMax; // Heal to full when increasing max HP
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
        if (this.speedY > MAX_FALL_SPEED) {
            this.speedY = MAX_FALL_SPEED;
        }

        // Kiểm tra từng bước nhỏ để tránh xuyên qua tile
        for (float i = 0f; i <= this.speedY; i += MOVEMENT_STEP) {
            if (!this.isOnTheFloor()) {
                this.rect.setY(this.rect.getY() + MOVEMENT_STEP);
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
            moveInDirection(0.5f);
        }

        if (this.keyLeft && !this.isAttacking && !this.isThrowing) {
            this.isDirRight = false;
            moveInDirection(-0.5f);
        }
    }

    private void moveInDirection(float direction) {
        for (float i = 0f; i <= this.speedX; i += MOVEMENT_STEP) {
            if (this.scene.isFree(new GameRectEntity(this.rect.getX() + direction, this.rect.getY(),
                    this.rect.getWidth(), this.rect.getHeight()).getRect())) {
                this.rect.setX(this.rect.getX() + direction);

                if (!this.isJump && !this.isOnTheFloor()) {
                    this.rect.setY(this.rect.getY() + MOVEMENT_STEP);
                }
            } else {
                break;
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
        for (float i = 0f; i <= this.speedY; i += MOVEMENT_STEP) {
            if (this.jumpFrames < this.jumpHeight &&
                    this.scene.isFree(new GameRectEntity(this.rect.getX(), this.rect.getY() - MOVEMENT_STEP,
                            this.rect.getWidth(), this.rect.getHeight()).getRect())) {
                this.rect.setY(this.rect.getY() - MOVEMENT_STEP);
                this.jumpFrames += MOVEMENT_STEP;
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
        // Kiểm tra dưới chân nhân vật
        for (float i = MOVEMENT_STEP; i <= FLOOR_CHECK_DISTANCE; i += MOVEMENT_STEP) {
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
            // Update dust sprite direction
            updateDustSpriteDirection();
        }
    }

    private void updateDustSpriteDirection() {
        if (this.dustSprite != null) {
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
            handleDeathAnimation();
            return;
        }

        updateTimers();
        handleStatusEffects();
        handleShield();
        handleMovement();
        handleAnimation();
        handleAttack();
        updateCamera();
    }

    private void handleDeathAnimation() {
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
    }

    private void updateTimers() {
        // Update hurt timer
        if (this.hurtTimer > 0) {
            this.hurtTimer--;
            if (this.hurtTimer <= 0) {
                this.isHurt = false;
            }
        }
    }

    private void handleStatusEffects() {
        // Handle poison
        if (this.isPoisoning && this.hp > 1) {
            this.poisonControl++;

            if (this.poisonControl == POISON_DAMAGE_INTERVAL) {
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
    }

    private void handleShield() {
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
    }

    private void handleMovement() {
        // Handle jump
        if (this.isJump) {
            this.jump();
        } else {
            this.applyGravity();
        }

        // Handle movement
        this.toMove();
    }

    private void handleAnimation() {
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
    }

    private void handleAttack() {
        // Handle attacking
        if (this.isAttacking) {
            this.attackAnimationCounter++;

            // Allow dealing damage ở frame giữa
            if (this.currentSprite != null &&
                    this.currentSprite.getIndex() >= ATTACK_DAMAGE_FRAME_START &&
                    this.currentSprite.getIndex() <= ATTACK_DAMAGE_FRAME_END) {
                this.canDealDamage = true;
            } else {
                this.canDealDamage = false;
            }

            // Kết thúc attack sau khi animation xong hoặc quá thời gian
            if ((this.currentSprite != null && this.currentSprite.finishedAnimation()) ||
                    this.attackAnimationCounter > ATTACK_MAX_FRAMES) {
                this.isAttacking = false;
                this.canDealDamage = false;
                this.attackAnimationCounter = 0;
                this.wasAttacking = false;
            }
        }
    }

    private void updateCamera() {
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