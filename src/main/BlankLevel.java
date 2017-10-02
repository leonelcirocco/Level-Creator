package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import hud.Hud;
import objects.BaseDrawable;
import objects.Colision;
import objects.RenderClass;
import objects.Sprites;
import userside.AddImage;

public class BlankLevel extends BasicGameState{
	private RenderClass renderClass = new RenderClass();
	private ArrayList<BaseDrawable>colision = new ArrayList<>();
	private ArrayList<BaseDrawable>background = new ArrayList<>();
	private ArrayList<BaseDrawable>decoration = new ArrayList<>();
	private ArrayList<BaseDrawable>decorationFront = new ArrayList<>();
	private ArrayList<BaseDrawable>currentArray;
	private MouseUse mouseUse = new MouseUse();
	private Sprites sprites;
	private AddImage addImage = new AddImage();
	private Hud hud;
	private Camera camera = new Camera();
	private PrintWriter print;
	private File file = new File("levela.txt");
	private Scanner fileIn = new Scanner(file);
	public BlankLevel() throws FileNotFoundException {
		print = new PrintWriter("level.txt");
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		hud = new Hud();
		sprites = new Sprites();
		currentArray=colision;
		hud.setSpriteSheet(sprites.getCurrentSpriteSheet());
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		camera.render(arg0, arg1, arg2);
		for(int i=0;i<background.size();i++){
			renderClass.draw(background.get(i));
		}
		for(int i=0;i<decoration.size();i++){
			renderClass.draw(decoration.get(i));
		}
		for(int i=0;i<colision.size();i++){
			renderClass.draw(colision.get(i));
		}
		for(int i=0;i<decorationFront.size();i++){
			renderClass.draw(decorationFront.get(i));
		}
		hud.render();
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		camera.update(arg0, arg1, arg2);
		hud.setX((int) camera.getX());
		hud.setY((int) camera.getY());
		useMap(arg0);
		useHud(arg0);
		try {
			save(arg0);
			load(arg0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(arg0.getInput().isKeyPressed(Input.KEY_B)){
			currentArray = background;
		}
			
		
	}
	public float roundNumber(float number){
		float finalNumber =  number%Options.GRIDWIDTH;
		return number - finalNumber;
	}
	@Override
	public int getID() {
		return 0;
	}
	public void useHud(GameContainer arg0) throws SlickException{
		if(arg0.getInput().getMouseY()<hud.getMaxY()){
			if(arg0.getInput().getMouseX()<hud.getMaxX()){
				if(arg0.getInput().isMousePressed(0)){
					sprites.setImage(mouseUse.changeSprite(arg0.getInput().getMouseX(), roundNumber(arg0.getInput().getMouseY())));
				}
			}else{
				if(arg0.getInput().isMousePressed(0)){
					if(arg0.getInput().getMouseX()>hud.getChangeSpriteSheetX()&&arg0.getInput().getMouseX()<hud.getChangeSpriteSheetX()+hud.getChangePng().getWidth()){
						if(arg0.getInput().getMouseY()<hud.getChangeSpriteSheetY()+hud.getChangePng().getHeight()&&arg0.getInput().getMouseY()>hud.getChangeSpriteSheetY()){
							SpriteSheet spriteSheet = new SpriteSheet(addImage.addImage(), Options.GRIDWIDTH, Options.GRIDWIDTH);
							sprites.setCurrentSpriteSheet(spriteSheet);
							hud.setSpriteSheet(spriteSheet);
							System.out.println("Add Sheet");
						}
					}
					if(arg0.getInput().getMouseX()>hud.getChangeArrayX()&&arg0.getInput().getMouseX()<hud.getChangeArrayX()+hud.getChangePng().getWidth()){
						if(arg0.getInput().getMouseY()<hud.getChangeArrayY()+hud.getChangePng().getHeight()/1.5&&arg0.getInput().getMouseY()>hud.getChangeArrayY()){
							System.out.println("Change Array deco back");
							currentArray = decoration;
						}
					}
					if(arg0.getInput().getMouseX()>hud.getChangeArrayX()&&arg0.getInput().getMouseX()<hud.getChangeArrayX()+hud.getChangePng().getWidth()){
						if(arg0.getInput().getMouseY()<hud.getChangeDecoFrontY()+hud.getChangePng().getHeight()/2&&arg0.getInput().getMouseY()>hud.getChangeDecoFrontY()*1.5f){
							System.out.println("Change Array deco front");
							currentArray = decorationFront;
						}
					}
					if(arg0.getInput().getMouseX()>hud.getChangeArrayX()&&arg0.getInput().getMouseX()<hud.getChangeArrayX()+hud.getChangePng().getWidth()){
						if(arg0.getInput().getMouseY()<hud.getChangeFloorY()+hud.getChangePng().getHeight()&&arg0.getInput().getMouseY()>hud.getChangeFloorY()*1.1f){
							System.out.println("Change Array floor ");
							currentArray = colision;
						}
					}
					
				}
			}
		}
	}
	public void useMap(GameContainer arg0){
		if(arg0.getInput().getMouseY()>hud.getMaxY()){
			int x= (int) roundNumber(arg0.getInput().getMouseX()+hud.getX());
			int y = (int) roundNumber(arg0.getInput().getMouseY()+hud.getY());
			mouseUse.addSprite(arg0, currentArray, x,y , sprites.getImage());
			mouseUse.removeSprite(arg0, currentArray, x, y);
		}
	}
	public void save(GameContainer arg0) throws FileNotFoundException{
		if(arg0.getInput().isKeyPressed(Input.KEY_Q)){
			print = new PrintWriter("level.txt");
			for(int i=0;i<colision.size();i++){
				print.println("x" + colision.get(i).getX() + "y" +colision.get(i).getY() + "r" 
			+ colision.get(i).getImage().getResourceReference() + "c"+ colision.get(i).getImage().getName() + "|");
			}
			print.close();
			print = new PrintWriter("background.txt");
			for(int i=0;i<background.size();i++){
				print.println("x" + background.get(i).getX() + "y" +background.get(i).getY() + "r" 
						+ background.get(i).getImage().getResourceReference() + "c"+ background.get(i).getImage().getName() + "|");
			}
			print.close();
			print = new PrintWriter("decoration.txt");
			for(int i=0;i<decoration.size();i++){
				print.println("x" + decoration.get(i).getX() + "y" +decoration.get(i).getY() + "r" 
						+ decoration.get(i).getImage().getResourceReference() + "c"+ decoration.get(i).getImage().getName() + "|");
			}
			print.close();
			print = new PrintWriter("decorationFront.txt");
			for(int i=0;i<decorationFront.size();i++){
				print.println("x" + decorationFront.get(i).getX() + "y" +decorationFront.get(i).getY() + "r" 
						+ decorationFront.get(i).getImage().getResourceReference() + "c"+ decorationFront.get(i).getImage().getName() + "|");
			}
			print.close();
		}
	}
	public void load(GameContainer arg0) throws SlickException, FileNotFoundException{
		if(arg0.getInput().isKeyPressed(Input.KEY_E)){
			colision.clear();
			fileIn = new Scanner(file);
			while(fileIn.hasNextLine()){
				String string = fileIn.nextLine();
				String xstring = string.substring(string.indexOf('x')+1, string.indexOf('y'));
				String ystring = string.substring(string.indexOf('y')+1, string.indexOf('r'));
				String imageRoute = string.substring(string.indexOf('r')+1, string.indexOf('c'));
				String imageCoor = string.substring(string.indexOf('c')+1, string.indexOf('|'));
				float x = Float.parseFloat(xstring);
				float y = Float.parseFloat(ystring);
				int coorx = Integer.valueOf(imageCoor.substring(0, imageCoor.indexOf(',')));
				int coory = Integer.valueOf(imageCoor.substring(imageCoor.indexOf(',')+1, imageCoor.length()));
				SpriteSheet spriteSheet = new SpriteSheet(imageRoute, Options.GRIDWIDTH, Options.GRIDWIDTH);
				Image image = spriteSheet.getSubImage(coorx, coory);
				image.setName(coorx+","+coory);
				colision.add(new Colision(x, y, image));
			}
			fileIn = new Scanner(new File("background.txt"));
			while(fileIn.hasNextLine()){
				String string = fileIn.nextLine();
				String xstring = string.substring(string.indexOf('x')+1, string.indexOf('y'));
				String ystring = string.substring(string.indexOf('y')+1, string.indexOf('r'));
				String imageRoute = string.substring(string.indexOf('r')+1, string.indexOf('c'));
				String imageCoor = string.substring(string.indexOf('c')+1, string.indexOf('|'));
				float x = Float.parseFloat(xstring);
				float y = Float.parseFloat(ystring);
				int coorx = Integer.valueOf(imageCoor.substring(0, imageCoor.indexOf(',')));
				int coory = Integer.valueOf(imageCoor.substring(imageCoor.indexOf(',')+1, imageCoor.length()));
				SpriteSheet spriteSheet = new SpriteSheet(imageRoute, Options.GRIDWIDTH, Options.GRIDWIDTH);
				Image image = spriteSheet.getSubImage(coorx, coory);
				image.setName(coorx+","+coory);
				background.add(new Colision(x, y, image));
			}
			fileIn = new Scanner(new File("decoration.txt"));
			while(fileIn.hasNextLine()){
				String string = fileIn.nextLine();
				String xstring = string.substring(string.indexOf('x')+1, string.indexOf('y'));
				String ystring = string.substring(string.indexOf('y')+1, string.indexOf('r'));
				String imageRoute = string.substring(string.indexOf('r')+1, string.indexOf('c'));
				String imageCoor = string.substring(string.indexOf('c')+1, string.indexOf('|'));
				float x = Float.parseFloat(xstring);
				float y = Float.parseFloat(ystring);
				int coorx = Integer.valueOf(imageCoor.substring(0, imageCoor.indexOf(',')));
				int coory = Integer.valueOf(imageCoor.substring(imageCoor.indexOf(',')+1, imageCoor.length()));
				SpriteSheet spriteSheet = new SpriteSheet(imageRoute, Options.GRIDWIDTH, Options.GRIDWIDTH);
				Image image = spriteSheet.getSubImage(coorx, coory);
				image.setName(coorx+","+coory);
				decoration.add(new Colision(x, y, image));
			}
			fileIn = new Scanner(new File("decorationFront.txt"));
			while(fileIn.hasNextLine()){
				String string = fileIn.nextLine();
				String xstring = string.substring(string.indexOf('x')+1, string.indexOf('y'));
				String ystring = string.substring(string.indexOf('y')+1, string.indexOf('r'));
				String imageRoute = string.substring(string.indexOf('r')+1, string.indexOf('c'));
				String imageCoor = string.substring(string.indexOf('c')+1, string.indexOf('|'));
				float x = Float.parseFloat(xstring);
				float y = Float.parseFloat(ystring);
				int coorx = Integer.valueOf(imageCoor.substring(0, imageCoor.indexOf(',')));
				int coory = Integer.valueOf(imageCoor.substring(imageCoor.indexOf(',')+1, imageCoor.length()));
				SpriteSheet spriteSheet = new SpriteSheet(imageRoute, Options.GRIDWIDTH, Options.GRIDWIDTH);
				Image image = spriteSheet.getSubImage(coorx, coory);
				image.setName(coorx+","+coory);
				decorationFront.add(new Colision(x, y, image));
			}
		}
	}
}
