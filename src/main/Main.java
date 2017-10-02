package main;

import java.io.FileNotFoundException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Main extends StateBasedGame{

	private static AppGameContainer app;
	public Main(String name) {
		super(name);
	}
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer((Game)new Main("lala"));
		app.setDisplayMode(Options.WIDTH, Options.HEIGTH, false);
		app.setAlwaysRender(true);
		app.setShowFPS(false);
		app.setTargetFrameRate(60);
		app.setVSync(false);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		try {
			addState(new BlankLevel());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
