package winterknight.scenes.levels;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import winterknight.Game;
import winterknight.Main;
import winterknight.scenes.objects.Teleport;
import winterknight.strings.StringError;

public class SaveLeft extends Save {

	public SaveLeft(Game game, Teleport teleport, String lastScene) {
		super(game, teleport, lastScene);
	}

	@Override
	public String currentLevelString() {
		return "save-left";
	}

	@Override
	protected BufferedImage loadLevel() {
		try {
			return ImageIO.read(this.getClass().getResource("/levels/save-01.png"));
		} catch (Exception e) {
			Main.exitWithError(StringError.ERROR_LOADING_FILES.getValue());
		}

		return null;
	}

	@Override
	protected boolean isSceneSaveRight() {
		return false;
	}

}
