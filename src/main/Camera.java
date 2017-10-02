package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Camera extends BasicGameState{
	private float x = 0;
	private float y = 500;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.translate(-x, -y);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		if(arg0.getInput().isKeyDown(Input.KEY_W)){
			if(y>0){
				y=y-Options.GRIDWIDTH;
			}
		}
		if(arg0.getInput().isKeyDown(Input.KEY_S)){
			y=y+Options.GRIDWIDTH;
		}
		if(arg0.getInput().isKeyDown(Input.KEY_A)){
			if(x>0){
				x=x-Options.GRIDWIDTH;
			}
		}
		if(arg0.getInput().isKeyDown(Input.KEY_D)){
			x=x+Options.GRIDWIDTH;
		}
	}

	@Override
	public int getID() {
		return 0;
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
}
