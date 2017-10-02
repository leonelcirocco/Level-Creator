package objects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Sprites {
	private SpriteSheet floor;
	private SpriteSheet currentSpriteSheet;
	private Image image;

	public Sprites() throws SlickException {
		floor = new SpriteSheet("data/SpriteSheetPiso.png", 32, 32);
		currentSpriteSheet = floor;
		image = currentSpriteSheet.getSubImage(0, 0);
		image.setName("0,0");
	}
	public SpriteSheet getFloor() {
		return floor;
	}
	public void setFloor(SpriteSheet floor) {
		this.floor = floor;
	}
	public Image getImage() {
		return image;
	}
	public SpriteSheet getCurrentSpriteSheet() {
		return currentSpriteSheet;
	}
	public void setCurrentSpriteSheet(SpriteSheet currentSpriteSheet) {
		this.currentSpriteSheet = currentSpriteSheet;
	}
	public void setImage(int[] changeSprite) {
		if(!(currentSpriteSheet.getHorizontalCount()-1<changeSprite[0])&&!(currentSpriteSheet.getVerticalCount()-1<changeSprite[1])){
			image = currentSpriteSheet.getSubImage(changeSprite[0], changeSprite[1]);
			image.setName(changeSprite[0]+","+changeSprite[1]);
		}
	}
}
