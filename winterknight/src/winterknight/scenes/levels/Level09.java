package winterknight.scenes.levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import winterknight.Game;
import winterknight.Main;
import winterknight.scenes.Scene;
import winterknight.scenes.backgrounds.Background;
import winterknight.scenes.backgrounds.BackgroundMoon;
import winterknight.scenes.objects.Teleport;
import winterknight.strings.StringError;
import winterknight.strings.StringLevel;

public class Level09 extends Scene {

	public Level09(Game game, Teleport teleport) {
		super(game, teleport, Level09.getBackgrounds(game));

		super.addText(StringLevel.INFO_THANK_YOU.getValue(), 300);
		// super.addText(StringLevel.INFO_SOON.getValue(), 330);
	}

	private static List<Background> getBackgrounds(Game game) {
		List<Background> backgrounds = new ArrayList<>();

		backgrounds.add(new BackgroundMoon(game, 0, 0));

		return backgrounds;
	}

	@Override
	public String currentLevelString() {
		return "level-09";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/level-09.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected Scene nextScene() {
		Teleport teleport = super.getTeleportCurrent();

		return new Level08(super.getGame(), teleport);
	}

}
