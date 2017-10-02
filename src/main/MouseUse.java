package main;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import objects.BaseDrawable;
import objects.Colision;

public class MouseUse {
	
	public void addSprite(GameContainer arg0, ArrayList<BaseDrawable> drawable, float x, float y, Image image){
		if(arg0.getInput().isMouseButtonDown(0)){
			boolean occupied = false;
			for(int i=0;i<drawable.size();i++){
			if(x==drawable.get(i).getX()&&y==drawable.get(i).getY()){
				occupied=true;
			}
		}
			if(!occupied){
				drawable.add(new Colision(x, y, image));
			}
		}
	}
	public void removeSprite(GameContainer arg0, ArrayList<BaseDrawable> drawable, float x, float y){
		if(arg0.getInput().isMouseButtonDown(1)){
			for(int i=0;i<drawable.size();i++){
				if(x==drawable.get(i).getX()&&y==drawable.get(i).getY()){
					drawable.remove(i);
				}
			}
		}
	}
	public int[] changeSprite(float x, float y){
		return new int[] {(int) (x/Options.GRIDWIDTH),(int) (y/Options.GRIDWIDTH)};
	}
}
