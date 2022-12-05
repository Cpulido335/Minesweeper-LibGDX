package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import Textures.TileTextures;


public class Clickable{
	
	//The main tile class,
	
	public Texture texture;
	public Sprite sprite;
	SpriteBatch batch;
	
	public int posX;
	public int posY;
	float boundMinX, boundMinY, boundMaxX, boundMaxY;
	
	//Tile Data
	public int numberOfMinesTouching = 0;
	public boolean isMine;
	public boolean isRevealed;
	public boolean isFlagged = false;


	public Clickable(int PositionX, int PositionY, SpriteBatch batch, Texture texture)
	{
		this.batch = batch;
		this.sprite = new Sprite(texture);
		this.sprite.setPosition(PositionX, PositionY);
		this.boundMaxX = this.sprite.getX() + sprite.getWidth();
		this.boundMaxY = 650 - PositionY - sprite.getHeight(); //Different coordinate systems warrant annoying math
		this.boundMinX = this.sprite.getX();
		this.boundMinY = 650 - PositionY;

	}
	
	//Updates the current texture of a mine in the render method 
	public void Update()
	{	
		if (isMine() && isRevealed())
			this.sprite.setTexture(TileTextures.returnTexture(10));
		else if (isRevealed())
			this.sprite.setTexture(TileTextures.returnTexture(this.numberOfMinesTouching));
	}
	
	
	public int getNumberOfMinesTouching() {
		return numberOfMinesTouching;
	}
	
	
	public void setNumberOfMinesTouching(int numberOfMinesTouching) {
		this.numberOfMinesTouching = numberOfMinesTouching;
	}
	
	//For testing 
	public void isTouched() 
	{
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		if(mouseX < boundMaxX && mouseX > boundMinX 
		&& mouseY > boundMaxY && mouseY < boundMinY)
		{
			this.sprite.setRotation(100);;
			this.sprite.draw(batch);
		}
	}
	
	//For testing
	public static void printmouse() {
		int mouseX, mouseY;
		
		mouseY = Gdx.input.getY();
		mouseX = Gdx.input.getX();
		System.out.println("X: " + mouseX + "    Y: " + mouseY);
	}


	public boolean isMine() {
		return isMine;
	}


	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}


	public boolean isRevealed() {
		return isRevealed;
	}


	public void setRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;
	}
	
		
}