package objects;

import org.newdawn.slick.Image;

public abstract class BaseDrawable {
	protected float x;
	protected float y;
	protected Image image;
	public BaseDrawable(float x, float y, Image image) {
		this.x=x;
		this.y=y;
		this.image=image;
	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Image getImage() {
		return image;
	}
}
