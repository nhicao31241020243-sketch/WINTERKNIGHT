package winterknight.scenes.objects;

import winterknight.generics.GameRect;
import winterknight.resources.Spritesheet;

public class Life extends GameObject {

	public Life() {
		super(new GameRect(0, 0, 524 / 20, 499 / 20), Spritesheet.getSpriteIcons(1655, 42, 524, 499));
	}

}
