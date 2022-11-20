package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlagButton extends Clickable{
	
	public static Texture unpressed = new Texture("flag_button.png");
	public static int posX = 550;
	public static int posY = 600;
	
	
	public FlagButton(int PositionX, int PositionY, SpriteBatch batch, Texture texture) {
		
		super(PositionX, PositionY, batch, texture);
	}
	
}
