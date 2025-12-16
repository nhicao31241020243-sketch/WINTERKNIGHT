package winterknight.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import winterknight.Game;
import winterknight.generics.GameColors;
import winterknight.generics.GameStatus;
import winterknight.gui.GameButton;
import winterknight.gui.GameText;
import winterknight.resources.GameFont;
import winterknight.resources.Spritesheet;

// THÊM IMPORT
import winterknight.scenes.backgrounds.BackgroundSpriteManager;

public abstract class Screen {

    protected final Game game;

    private static BufferedImage background;
    private static boolean backgroundLoaded = false;

    private final GameText title;

    protected final List<GameText> texts;
    protected final List<GameButton> buttons;

    private boolean mousePressed;
    private boolean mouseReleased;

    private int mouseX;
    private int mouseY;

    // KHỞI TẠO BACKGROUND VỚI BG_04
    private static void initializeBackground() {
        if (!backgroundLoaded) {
            try {
                System.out.println("🎮 Initializing menu background (BG_04)...");

                // LẤY BG_04 TỪ SPRITEMAP
                BackgroundSpriteManager manager = BackgroundSpriteManager.getInstance();
                BufferedImage bg04 = manager.getSprite("BG_04");

                if (bg04 != null && bg04.getWidth() > 100 && bg04.getHeight() > 100) {
                    background = bg04;
                    System.out.println("✅ Menu background: BG_04 (" +
                            bg04.getWidth() + "x" + bg04.getHeight() + ")");
                } else {
                    // NẾU BG_04 KHÔNG CÓ, THỬ BG_01
                    System.out.println("⚠️ BG_04 not available, trying BG_01...");
                    BufferedImage bg01 = manager.getSprite("BG_01");

                    if (bg01 != null && bg01.getWidth() > 100) {
                        background = bg01;
                        System.out.println("✅ Menu background: BG_01 (fallback)");
                    } else {
                        // FALLBACK VỀ BACKGROUND CŨ
                        background = Spritesheet.getSpriteBackground(12, 332, 512, 224);
                        System.out.println("⚠️ Using default menu background");
                    }
                }
            } catch (Exception e) {
                System.err.println("❌ Error loading menu background: " + e.getMessage());
                background = Spritesheet.getSpriteBackground(12, 332, 512, 224);
            }

            backgroundLoaded = true;
        }
    }

    public Screen(Game game, String title) {
        // KHỞI TẠO BACKGROUND
        initializeBackground();

        this.game = game;

        Graphics render = game.getRender();

        render.setFont(GameFont.getTitle());

        int titleWidth = render.getFontMetrics().stringWidth(title);

        this.title = new GameText(title, (game.getGameWidth() - titleWidth) / 2, 80, GameColors.WHITE, GameFont.getTitle());

        this.texts = new ArrayList<>();
        this.buttons = new ArrayList<>();

        this.mousePressed = false;
        this.mouseReleased = false;

        this.mouseX = 0;
        this.mouseY = 0;

        this.texts.add(new GameText(String.format("v %s", this.game.getVersion()), 25, 25, GameColors.WHITE, GameFont.getSmall()));
    }

    public abstract GameStatus getGameStatus();

    public void tick() {
        if (this.mousePressed) {
            for (GameButton button : this.buttons) {
                if (button.wasClicked(this.mouseX, this.mouseY)) {
                    button.setButtonPressed();
                }
            }

            this.mousePressed = false;
        }

        if (this.mouseReleased) {
            for (GameButton button : this.buttons) {
                if (button.wasClicked(this.mouseX, this.mouseY)) {
                    button.onClick();
                }

                button.setButtonReleased();
            }

            this.mouseReleased = false;
        }
    }

    public void render(Graphics render) {
        // ĐẢM BẢO BACKGROUND ĐÃ LOAD
        if (background == null) {
            initializeBackground();
        }

        // VẼ BACKGROUND (BG_04 hoặc fallback)
        render.drawImage(background, 0, 0, this.game.getGameWidth(), this.game.getGameHeight(), null);

        // LỚP PHỦ ĐEN TRONG SUỐT (giữ nguyên)
        render.setColor(new Color(0, 0, 0, 0.5f));
        render.fillRect(0, 0, this.game.getGameWidth(), this.game.getGameHeight());

        // VẼ CÁC THÀNH PHẦN KHÁC
        this.title.render(render);

        for (GameText text : this.texts) {
            text.render(render);
        }

        for (GameButton button : this.buttons) {
            button.render(render);
        }
    }

    // METHOD ĐỂ THAY ĐỔI BACKGROUND (TUỲ CHỌN)
    public static void setMenuBackground(BufferedImage newBackground) {
        background = newBackground;
        System.out.println("🔄 Menu background changed");
    }

    // METHOD ĐỂ RESET VỀ BG_04 (TUỲ CHỌN)
    public static void resetToBG04() {
        try {
            BufferedImage bg04 = BackgroundSpriteManager.getInstance().getSprite("BG_04");
            if (bg04 != null) {
                background = bg04;
                System.out.println("🔄 Menu background reset to BG_04");
            }
        } catch (Exception e) {
            System.err.println("❌ Cannot reset to BG_04");
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.mousePressed = true;

            this.mouseX = e.getX();
            this.mouseY = e.getY();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.mouseReleased = true;

            this.mouseX = e.getX();
            this.mouseY = e.getY();
        }
    }

}