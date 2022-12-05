package Entities;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Textures.ButtonTextures;
import Textures.TileTextures;


public class ClickableGrid {
	
	//Generates a 2D grid of tile objects
	
	public Clickable[][] grid; 
	public int row, col;
	public SpriteBatch batch;
	public float[][][] clickingGrid;
	
	
	
	//Flag Button
	public FlagButton fg = new FlagButton(FlagButton.posX, FlagButton.posY, batch, FlagButton.unpressed);
	public boolean flagToggled = false;
	

	public ClickableGrid(int rows, int columns, SpriteBatch batch){
		
		this.row = rows;
		this.col = columns;
		this.batch = batch;
		this.grid = new Clickable[row][col];
		this.clickingGrid = new float[row][col][4];

		int startX = 0;
		int startY = 0;
		
		
		//Nested for loop populates grid with clickable tiles
		for (int i = 0; i < col; i++) 
		{	
			startX = 0;
			for (int j = 0; j < row; j++)
			{
				this.grid[i][j] = new Clickable(startX, startY, this.batch, TileTextures.returnTexture(9)); 
				startX += this.grid[i][j].sprite.getWidth(); 				
			}																
			startY += this.grid[0][0].sprite.getHeight();
			
		}
		
		
		//Mines are set and each tile counts its neighboring mines
		setMines(8);
		mineTouchingCounter();
	
		
		//A Positional grid to handle polling for user click events
		//This is a 3d array containing the bounds for each tile.
		for (int i = 0; i < col; i++) 
		{
			for (int j = 0; j < row; j++)
			{
				clickingGrid[i][j][0] =this.grid[i][j].boundMaxX;
				clickingGrid[i][j][1] =this.grid[i][j].boundMinX;
				clickingGrid[i][j][2] =this.grid[i][j].boundMaxY;
				clickingGrid[i][j][3] =this.grid[i][j].boundMinY;
			}
		}
		
	}
	
	
	//Takes in the number of mines, sets them randomly
	public void setMines(int numberOfMines) {
		
		int currentMineCounter = 0;
		int range = this.row * this.col;
		ArrayList<Integer> minePlacement = new ArrayList<Integer>(numberOfMines);
		
		while (currentMineCounter <= numberOfMines) 
		{
			Random random = new Random();
			int addMine = random.nextInt(0 , range);
			
			//Makes sure there isn't duplicates
			if(!minePlacement.contains(addMine))
				minePlacement.add(addMine);
				currentMineCounter++;
		}
		
		int counter = 0;
		for (int i = 0; i < col; i++) 
		{
			for (int j = 0; j < row; j++) 
			{
				if (minePlacement.contains(counter))
				this.grid[i][j].setMine(true);
				counter++;
			}
				
		}
		
	}
	
	
	public void mineTouchingCounter() {
		
		for (int i = 0; i < col; i++)
		{
			for (int j = 0; j < row; j++) 
			{
				if (this.grid[i][j].isMine())
				{	
					
					//Top Bottom
					if(tileExists(i+1, j)) {this.grid[i+1][j].numberOfMinesTouching++;}
					if(tileExists(i-1, j)) {this.grid[i-1][j].numberOfMinesTouching++;}
					
					//Left Right
					if(tileExists(i, j-1)) {this.grid[i][j-1].numberOfMinesTouching++;}
					if(tileExists(i, j+1)) {this.grid[i][j+1].numberOfMinesTouching++;}
					
					//Top && Bottom Left corners
					if(tileExists(i+1,j-1)) {this.grid[i+1][j-1].numberOfMinesTouching++;}
					if(tileExists(i-1,j-1)) {this.grid[i-1][j-1].numberOfMinesTouching++;}
					
					//Top && Bottom Right corners
					if(tileExists(i+1, j+1)) {this.grid[i+1][j+1].numberOfMinesTouching++;}
					if(tileExists(i-1, j+1)) {this.grid[i-1][j+1].numberOfMinesTouching++;}
					
					
					
				}
			}
		}
	}
	
	
	
	
	//A try catch block used in mineTouchingCounter() to make sure a tile exists 
	public boolean tileExists(int i, int j) {
		
		try { int x = this.grid[i][j].numberOfMinesTouching;}
		catch (IndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}

	
	//The Clicker is an event poller that gets user input (clicking) and returns the tile clicked on.
	public void Clicker() {
		
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		
		//Is flag button touched
		if(mouseX < this.fg.boundMaxX && mouseX > this.fg.boundMinX
		&& mouseY > this.fg.boundMaxY && mouseY < this.fg.boundMinY) 
		{	
			if (Gdx.input.isTouched()) 
			{
				this.flagToggle();
				wait(250);
				
			}
			
		}
		

		
		for (int i = 0; i < col; i++) 
		{
			for (int j = 0; j < row; j++) 
			{	
				if(mouseX < this.clickingGrid[i][j][0] && mouseX > this.clickingGrid[i][j][1]
				&& mouseY > this.clickingGrid[i][j][2] && mouseY < this.clickingGrid[i][j][3])
				{
					if (Gdx.input.isTouched())
					{
						if (this.flagToggled && !this.grid[i][j].isRevealed && !this.grid[i][j].isFlagged) 
						{
							this.grid[i][j].isFlagged = true;
							this.grid[i][j].sprite.setTexture(TileTextures.flagged);
						} else if (this.flagToggled && this.grid[i][j].isFlagged)
						{
							this.grid[i][j].isFlagged = false;
							this.grid[i][j].sprite.setTexture(TileTextures.returnTexture(9));
						} else
						{
							this.grid[i][j].setRevealed(true);
							this.grid[i][j].Update();
						}
						wait(250);
					}
				}
			}
		}
	
	}
	
	
	public void flagToggle(){
		
		if(this.flagToggled) 
		{
			this.flagToggled = false;
			this.fg.sprite.setTexture(ButtonTextures.flagbuttonunpressed);
		} else 
		{
			this.flagToggled = true;
			this.fg.sprite.setTexture(ButtonTextures.flagbuttonpressed);
		}
		
	}
	
	
	public void wait(int TimeMs) {
		try {
			Thread.sleep(TimeMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	
	
	
	

}
