package objects;


public class RenderClass {
	
	public void draw(BaseDrawable drawable){
		drawable.getImage().draw(drawable.getX(), drawable.getY());
	}

}
