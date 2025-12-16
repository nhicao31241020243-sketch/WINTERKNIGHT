package winterknight.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import winterknight.Game;
import winterknight.generics.GameColors;
import winterknight.generics.GameStatus;
import winterknight.gui.GameText;
import winterknight.resources.GameFont;
import winterknight.scenes.backgrounds.BackgroundSpriteManager;
import winterknight.strings.StringGame;

public class OpeningScreen {

    private final Game game;

    private float alpha;
    private boolean sum;

    private final GameText title;

    // BIẾN LƯU BACKGROUND "3"
    private BufferedImage background;

    public OpeningScreen(Game game) {
        this.game = game;

        this.alpha = 1.0f;
        this.sum = false;

        // LẤY BACKGROUND "3"
        this.loadBackground();

        Graphics render = game.getRender();

        render.setFont(GameFont.getTitleLarge());

        int textWidth = render.getFontMetrics().stringWidth(StringGame.TITLE.getValue());
        int textHeight = render.getFontMetrics().getHeight();

        int textX = (game.getGameWidth() - textWidth) / 2;
        int textY = (game.getGameHeight() - textHeight) / 2 + render.getFontMetrics().getAscent();

        this.title = new GameText(StringGame.TITLE.getValue(), textX, textY, GameColors.WHITE, GameFont.getTitleLarge());
    }

    // LOAD BACKGROUND TÊN "3"
    private void loadBackground() {
        try {
            BackgroundSpriteManager manager = BackgroundSpriteManager.getInstance();

            // LẤY SPRITE "3" - chính xác tên bạn muốn
            this.background = manager.getSprite("3");

            if (this.background != null) {
                System.out.println("✅ OpeningScreen: Loaded background '3'");
                System.out.println("📏 Size: " + this.background.getWidth() + "x" + this.background.getHeight());
            } else {
                System.out.println("⚠️ Background '3' not found, trying fallback...");
                // THỬ LẤY BG_01 NẾU "3" KHÔNG CÓ
                this.background = manager.getSprite("BG_01");
                if (this.background == null) {
                    // FALLBACK CUỐI CÙNG: MÀU ĐEN
                    this.background = createBlackBackground();
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Error loading background '3': " + e.getMessage());
            this.background = createBlackBackground();
        }
    }

    // TẠO BACKGROUND MÀU ĐEN DỰ PHÒNG
    private BufferedImage createBlackBackground() {
        BufferedImage img = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        g.dispose();
        return img;
    }

    public void tick() {
        if (this.sum) {
            this.alpha += 0.01f;
        } else {
            this.alpha -= 0.01f;
        }

        if (this.alpha <= 0.0f) {
            this.alpha = 0.0f;
            this.sum = true;
        } else if (this.alpha >= 1.0f) {
            this.alpha = 1.0f;
            this.game.setGameStatus(GameStatus.SELECT_LANGUAGE);
        }
    }

    public void render(Graphics render) {
        // VẼ BACKGROUND "3"
        if (this.background != null) {
            // Scale background để fill toàn màn hình
            render.drawImage(this.background,
                    0, 0,
                    this.game.getGameWidth(), this.game.getGameHeight(),
                    null);
        } else {
            // FALLBACK: MÀU ĐEN
            render.setColor(Color.BLACK);
            render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());
        }

        this.title.render(render);

        // LỚP FADE ĐEN (giữ nguyên)
        render.setColor(new Color(0.0f, 0.0f, 0.0f, this.alpha));
        render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());
    }

    // METHOD ĐỂ THAY ĐỔI BACKGROUND (nếu muốn đổi sau)
    public void changeBackground(String bgName) {
        try {
            BufferedImage newBg = BackgroundSpriteManager.getInstance().getSprite(bgName);
            if (newBg != null) {
                this.background = newBg;
                System.out.println("🔄 OpeningScreen background changed to '" + bgName + "'");
            }
        } catch (Exception e) {
            System.err.println("❌ Cannot change background to '" + bgName + "'");
        }
    }

}