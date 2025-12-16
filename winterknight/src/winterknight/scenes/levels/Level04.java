package winterknight.scenes.levels;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import winterknight.Game;
import winterknight.Main;
import winterknight.scenes.Scene;
import winterknight.scenes.objects.Teleport;
import winterknight.strings.StringError;

public class Level04 extends Scene {

	public Level04(Game game, Teleport teleport) {
		super(game, teleport, new ArrayList<>());
	}

	@Override
	public String currentLevelString() {
		return "level-04";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/level-04.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected Scene nextScene() {
		Teleport teleport = super.getTeleportCurrent();

		if (teleport.getColor() == 0xFFFF006C) {
			return new Level03(super.getGame(), teleport);
		}

		return new Level05(super.getGame(), teleport);
	}

}
