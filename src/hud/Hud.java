package hud;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import main.Options;

public class Hud {
	private int x=0;
	private int y;
	private int maxY;
	private int maxX;
	private int changeSpriteSheetX;
	private int changeSpriteSheetY;
	private int changeArrayX;
	private int changeArrayY;
	private int changeDecoFrontY;
	private int changeFloorY;
	private Image changePng;
	private Image changeFloor;
	private Image changeDecoFr;
	private Image changeDecoBk;
	private SpriteSheet spriteSheet;
	public Hud() throws SlickException {
		changePng = new Image("data/Buttons.png").getSubImage(0, 0, 32, 32).getScaledCopy(2);
		changeFloor = new Image("data/ButtonFloor.png").getSubImage(0, 0, 32, 32).getScaledCopy(2);
		changeDecoBk = new Image("data/ButtonDecoBk.png").getSubImage(0, 0, 32, 32).getScaledCopy(2);
		changeDecoFr = new Image("data/ButtonDecoFr.png").getSubImage(0, 0, 32, 32).getScaledCopy(2);
		maxY=4*Options.GRIDWIDTH;
		maxX = 10*Options.GRIDWIDTH;
		changeSpriteSheetX = 332;
		changeSpriteSheetY = 0;
		changeArrayX = 420;
		changeSpriteSheetY=0;
		changeDecoFrontY = 32;
		changeFloorY = 64;
	}
	public int getChangeDecoFrontY() {
		return changeDecoFrontY;
	}
	public int getChangeFloorY() {
		return changeFloorY;
	}
	public void render(){
		for(int i=0;i<spriteSheet.getVerticalCount();i++){
			for(int l=0;l<spriteSheet.getHorizontalCount();l++){
				spriteSheet.getSubImage(l, i).draw(l*Options.GRIDWIDTH+x,i*Options.GRIDWIDTH+y);
			}
		}
		changePng.draw(changeSpriteSheetX+x, changeSpriteSheetY+y);
		changeDecoBk.draw(changeArrayX+x, changeArrayY+y);
		changeDecoFr.draw(changeArrayX+x, changeDecoFrontY+y);
		changeFloor.draw(changeArrayX+x, changeFloorY+y);
	}
	public int getChangeArrayX() {
		return changeArrayX;
	}
	public int getChangeArrayY() {
		return changeArrayY;
	}
	public Image getChangePng() {
		return changePng;
	}
	public int getChangeSpriteSheetX() {
		return changeSpriteSheetX;
	}
	public int getChangeSpriteSheetY() {
		return changeSpriteSheetY;
	}
	public int getMaxX() {
		return maxX;
	}
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}
	public SpriteSheet getSpriteSheet() {
		return spriteSheet;
	}
	public void setSpriteSheet(SpriteSheet spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getMaxY() {
		return maxY;
	}
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
}
