package userside;

import java.util.Scanner;

public class AddImage {
	public String addImage(){
		String string = new String();
		Scanner reader = new Scanner(System.in);
		string = reader.nextLine();
		return "data/" + string + ".png";
	}
}
