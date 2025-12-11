package dyrvania.resources;




import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import dyrvania.Main;
import dyrvania.strings.StringError;




public class Spritesheet {




    private static final BufferedImage spritesheetGUI;
    private static final BufferedImage spritesheetTiles;
    private static final BufferedImage spritesheetIcons;
    private static final BufferedImage spritesheetDeath;
    private static final BufferedImage spritesheetDeathBlue;
    private static final BufferedImage spritesheetPlayer;
    private static final BufferedImage spritesheetThing;
    private static final BufferedImage spritesheetSkull;
    private static final BufferedImage spritesheetSkeleton;
    private static final BufferedImage spritesheetBoss;
    private static final BufferedImage spritesheetBackground;




    // Counter để lấy Christmas icon theo thứ tự
    private static int christmasIconCounter = 1;




    static {
        System.out.println("🎄 ĐANG LOAD SPRITESHEET VỚI CHRISTMAS ICONS...");




        BufferedImage auxSpritesheetGUI = null;
        BufferedImage auxSpritesheetTiles = null;
        BufferedImage auxSpritesheetIcons = null;
        BufferedImage auxSpritesheetDeath = null;
        BufferedImage auxSpritesheetDeathBlue = null;
        BufferedImage auxSpritesheetPlayer = null;
        BufferedImage auxSpritesheetThing = null;
        BufferedImage auxSpritesheetSkull = null;
        BufferedImage auxSpritesheetSkeleton = null;
        BufferedImage auxSpritesheetBoss = null;
        BufferedImage auxSpritesheetBackground = null;




        try {
            // LOAD TẤT CẢ FILE NHƯ CŨ
            auxSpritesheetGUI = ImageIO.read(Spritesheet.class.getResource("/sprites/guioldoldtoo.png"));
            auxSpritesheetTiles = ImageIO.read(Spritesheet.class.getResource("/sprites/tiles.png"));




            // ⭐⭐⭐ QUAN TRỌNG: ĐỔI icons2.png → icons.png (FILE CỦA BẠN) ⭐⭐⭐
            auxSpritesheetIcons = ImageIO.read(Spritesheet.class.getResource("/sprites/icons.png"));




            // DEBUG: KIỂM TRA ICONS
            if (auxSpritesheetIcons != null) {
                System.out.println("✅ icons.png: " + auxSpritesheetIcons.getWidth() + "x" + auxSpritesheetIcons.getHeight());
                System.out.println("🎯 Christmas icons sẽ thay thế tất cả icon trong game!");
            } else {
                System.err.println("❌ KHÔNG LOAD ĐƯỢC icons.png!");
                System.err.println("📍 Kiểm tra file: /sprites/icons.png");
            }




            // CÁC FILE KHÁC
            auxSpritesheetDeath = ImageIO.read(Spritesheet.class.getResource("/sprites/death.png"));
            auxSpritesheetDeathBlue = ImageIO.read(Spritesheet.class.getResource("/sprites/death-blue.png"));
            auxSpritesheetPlayer = ImageIO.read(Spritesheet.class.getResource("/sprites/HELLO.png"));
            auxSpritesheetThing = ImageIO.read(Spritesheet.class.getResource("/sprites/thing.png"));
            auxSpritesheetSkull = ImageIO.read(Spritesheet.class.getResource("/sprites/skull.png"));
            auxSpritesheetSkeleton = ImageIO.read(Spritesheet.class.getResource("/sprites/skeleton.png"));
            auxSpritesheetBoss = ImageIO.read(Spritesheet.class.getResource("/sprites/boss.png"));
            auxSpritesheetBackground = ImageIO.read(Spritesheet.class.getResource("/sprites/backgroundOLD.png"));
        } catch (Exception e) {
            Main.exitWithError(StringError.ERROR_LOADING_SPRITES.getValue());
        }




        // GÁN VÀO FINAL
        spritesheetGUI = auxSpritesheetGUI;
        spritesheetTiles = auxSpritesheetTiles;
        spritesheetIcons = auxSpritesheetIcons;
        spritesheetDeath = auxSpritesheetDeath;
        spritesheetDeathBlue = auxSpritesheetDeathBlue;
        spritesheetPlayer = auxSpritesheetPlayer;
        spritesheetThing = auxSpritesheetThing;
        spritesheetSkull = auxSpritesheetSkull;
        spritesheetSkeleton = auxSpritesheetSkeleton;
        spritesheetBoss = auxSpritesheetBoss;
        spritesheetBackground = auxSpritesheetBackground;




        System.out.println("✨ SPRITESHEET LOAD HOÀN TẤT!");
    }




    // ========== TẤT CẢ METHOD CŨ GIỮ NGUYÊN ==========




    public static BufferedImage getSpriteGUI(int x, int y, int width, int height) {
        return spritesheetGUI.getSubimage(x, y, width, height);
    }




    public static BufferedImage getSpriteTiles(int x, int y, int width, int height) {
        return spritesheetTiles.getSubimage(x, y, width, height);
    }




    // ⭐⭐⭐ METHOD QUAN TRỌNG: getSpriteIcons - SẼ TRẢ VỀ CHRISTMAS ICONS ⭐⭐⭐
    public static BufferedImage getSpriteIcons(int x, int y, int width, int height) {
        // BỎ QUA TỌA ĐỘ CŨ, LUÔN TRẢ VỀ CHRISTMAS ICON
        BufferedImage christmasIcon = getChristmasIcon(christmasIconCounter);




        // Tăng counter, quay vòng 1-23
        christmasIconCounter++;
        if (christmasIconCounter > 23) {
            christmasIconCounter = 1;
        }




        return christmasIcon;
    }




    public static BufferedImage getSpriteDeath(int x, int y, int width, int height) {
        return spritesheetDeath.getSubimage(x, y, width, height);
    }




    public static BufferedImage getSpriteDeathBlue(int x, int y, int width, int height) {
        return spritesheetDeathBlue.getSubimage(x, y, width, height);
    }




    public static BufferedImage getSpritePlayer(int x, int y, int width, int height) {
        return spritesheetPlayer.getSubimage(x, y, width, height);
    }




    public static BufferedImage getSpriteThing(int x, int y, int width, int height) {
        return spritesheetThing.getSubimage(x, y, width, height);
    }




    public static BufferedImage getSpriteSkull(int x, int y, int width, int height) {
        return spritesheetSkull.getSubimage(x, y, width, height);
    }




    public static BufferedImage getSpriteSkeleton(int x, int y, int width, int height) {
        return spritesheetSkeleton.getSubimage(x, y, width, height);
    }




    public static BufferedImage getSpriteBoss(int x, int y, int width, int height) {
        return spritesheetBoss.getSubimage(x, y, width, height);
    }




    public static BufferedImage getSpriteBackground(int x, int y, int width, int height) {
        return spritesheetBackground.getSubimage(x, y, width, height);
    }




    // ========== METHOD CHRISTMAS ICON VỚI KIỂM TRA AN TOÀN ==========




    public static BufferedImage getChristmasIcon(int number) {
        if (number < 1) number = 1;
        if (number > 23) number = 23;




        // TỌA ĐỘ 23 ICON CỦA BẠN
        int[][] coordinates = {
                {0, 0}, {0, 33}, {33, 0}, {33, 33},
                {0, 66}, {33, 66}, {66, 0}, {66, 33},
                {66, 66}, {0, 99}, {33, 99}, {66, 99},
                {99, 0}, {99, 33}, {99, 66}, {99, 99},
                {0, 132}, {33, 132}, {66, 132}, {99, 132},
                {132, 0}, {132, 33}, {132, 66}
        };




        int index = number - 1;
        int x = coordinates[index][0];
        int y = coordinates[index][1];




        // KIỂM TRA NULL
        if (spritesheetIcons == null) {
            System.err.println("❌ spritesheetIcons là NULL! Tạo icon mặc định...");
            return createDefaultIcon(number);
        }




        // KIỂM TRA KÍCH THƯỚC
        int imgW = spritesheetIcons.getWidth();
        int imgH = spritesheetIcons.getHeight();




        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x + 32 > imgW) x = imgW - 32;
        if (y + 32 > imgH) y = imgH - 32;
        if (x < 0) x = 0;
        if (y < 0) y = 0;




        try {
            return spritesheetIcons.getSubimage(x, y, 32, 32);
        } catch (Exception e) {
            System.err.println("⚠️ Lỗi cắt Christmas icon #" + number + ": " + e.getMessage());
            return createDefaultIcon(number);
        }
    }




    // TẠO ICON MẶC ĐỊNH NẾU LỖI
    private static BufferedImage createDefaultIcon(int number) {
        BufferedImage icon = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = icon.createGraphics();




        // MÀU XANH GIÁNG SINH
        g.setColor(new java.awt.Color(0, 150, 0));
        g.fillRect(0, 0, 32, 32);




        // VIỀN ĐỎ
        g.setColor(java.awt.Color.RED);
        g.drawRect(0, 0, 31, 31);




        // SỐ ICON
        g.setColor(java.awt.Color.WHITE);
        g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        g.drawString("C" + number, 10, 20);




        g.dispose();
        return icon;
    }




    // ========== METHOD TIỆN ÍCH ==========




    public static BufferedImage getRandomChristmasIcon() {
        int randomNum = (int) (Math.random() * 23) + 1;
        return getChristmasIcon(randomNum);
    }




    public static void resetChristmasIconCounter() {
        christmasIconCounter = 1;
    }




    public static int getCurrentChristmasIconNumber() {
        return christmasIconCounter;
    }
}



