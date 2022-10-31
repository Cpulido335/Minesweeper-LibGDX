package Textures;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class TileTextures {
	
	//Stores a static array of file names, was planning to use enums, but this was simpler
	
	public static String[] TileTexture = {
			"empty_tile_b.png", //0
			"tile1_b.png", 		//1
			"tile2_b.png", 		//2
			"tile3_b.png",		//3
			"tile4_b.png", 		//4
			"tile5_b.png", 		//5
			"tile6_b.png", 		//6
			"tile7_b.png", 		//7
			"tile8_b.png", 		//8
			
			"empty_tile.png",   //9
			"tileM_b.png"};		//10
	
	
	public static Texture returnTexture(int num) {
		Texture texture = new Texture(TileTexture[num]);
		return texture;
	}
	
	//For testing 
	public Texture randomTexture() {
		Random random = new Random();
		int num = random.nextInt(0,11);
		
		Texture texture = new Texture(TileTextures.TileTexture[num]);
		return texture;
	}

}