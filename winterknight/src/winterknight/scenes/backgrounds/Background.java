package winterknight.scenes.backgrounds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import winterknight.generics.GameRect;

public abstract class Background {

    private final GameRect rect;
    private final BufferedImage sprite;

    public Background(int x, int y, int width, int height, BufferedImage sprite) {
        this.rect = new GameRect(x, y, width, height);
        this.sprite = sprite;
    }

    public GameRect getRect() {
        return this.rect;
    }

    // ===== PARALLAX LOOP RENDER (FIX NỀN ĐEN) =====
    public void render(Graphics render) {
        int w = this.rect.getWidth();
        int h = this.rect.getHeight();
        int x = this.rect.getX();
        int y = this.rect.getY();

        // Đưa x về khoảng [-w, 0] để loop vô hạn
        x = x % w;
        if (x > 0) x -= w;

        // Vẽ 2–3 tấm để luôn phủ kín màn hình
        render.drawImage(this.sprite, x - w, y, w, h, null);
        render.drawImage(this.sprite, x, y, w, h, null);
        render.drawImage(this.sprite, x + w, y, w, h, null);
    }
}
