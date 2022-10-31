package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TitleBorder {
	
	static Texture borderTexture = new Texture("top_border_title2.png");
	
	public static int originX = borderTexture.getWidth() / 2;
	public static int originY = borderTexture.getHeight() / 2;
	public static int posX = 600 / 2;
	public static int posY = 600;
		
	public static Sprite titleBorder = new Sprite(borderTexture);
	
	
	
	
}
