package Draw;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import Entities.ClickableGrid;

public class Draw {
	
	//Contains static draw functions for the render method of the screen class
	
	
	//For testing
	public static void draw2dTextureRegionArray(TextureRegion[][] TexReg2Darray, SpriteBatch batch)
	{
		int startX = 400;
		int startY = 400;
		for (int row = 0; row < TexReg2Darray.length; row++) {
			startX = 400;
			for (int col = 0; col < TexReg2Darray.length; col++) {
				batch.draw(TexReg2Darray[row][col], startX, startY);
				startX += 128;
			}
			startY +=128;
		}
	}
	
	
	// A method to draw the grid, It iterates through the grid object from the ClickableGrid class, drawing each tile to the Sprite Batch.
	public static void drawClickableGrid(ClickableGrid cg, SpriteBatch batch) {
		
		for (int i = 0; i < cg.row; i++)
		{
			for (int j = 0; j < cg.col; j++)
			{
				batch.draw(cg.grid[i][j].sprite, cg.grid[i][j].sprite.getX(), cg.grid[i][j].sprite.getY(), cg.grid[i][j].posX, cg.grid[i][j].posY,
						cg.grid[i][j].sprite.getWidth(), cg.grid[i][j].sprite.getHeight(),1,1,
						cg.grid[i][j].sprite.getRotation());

				//TextureRegion region, float x, float y, float originX, float originY, float width, float height,
				//float scaleX, float scaleY, float rotation
			}
			
		}
		
	}


}
 