/*
	 Map.java (java)
	 
	 Purpose: To load game map sprites.
	 
	 Version 0.1
	 
	 Site: http://www.dirackslounge.online
	 
	 Programer: Rodolfo A. C. Neves (Dirack) 16/10/2019
	 
	 Email: rodolfo_profissional@hotmail.com
	 
	 License: GPL-3.0 <https://www.gnu.org/licenses/gpl-3.0.txt>.
*/

package Zelda;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Zelda.FileNotFoundException;
import java.net.URL; 
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Map extends SpriteSheet{

	protected int[] pixels;
	protected BufferedImage tileFloor;
	protected BufferedImage tileGrass;
	protected BufferedImage tileEnemy;
	protected BufferedImage tileGun;
	protected BufferedImage tilePack;
	protected BufferedImage tileLife;

	public Map(String path, SpriteSheet tiles) throws FileNotFoundException{

		super(path);
		this.tileFloor = tiles.getSprite(16,0,16,16);
		this.tileGrass = tiles.getSprite(0,0,16,16);
		this.tileEnemy = tiles.getSprite(7*16,16,16,16);
		this.tileGun = tiles.getSprite(7*16,0,16,16);
		this.tilePack = tiles.getSprite(6*16,16,16,16);
		this.tileLife = tiles.getSprite(6*16,0,16,16);

		this.pixels = new int[this.spritesheet.getWidth() * this.spritesheet.getHeight()];
		this.loadMap();
	}

	public void loadMap(){

		int width = this.spritesheet.getWidth();
		int height = this.spritesheet.getHeight();

		this.spritesheet.getRGB(0,0,width,height,this.pixels,0,width);
	}

	public void draw(Graphics g, int SCALE){

		int width = this.spritesheet.getWidth();
		int height = this.spritesheet.getHeight();
		int xx, yy, currentPixel;

		Graphics2D g2 = (Graphics2D) g;

		for(xx=0; xx < width; xx++){
			for(yy=0; yy < height; yy++){

				currentPixel = pixels[xx + yy * width];

				if(currentPixel == 0xFF000000){ 
					g2.drawImage(this.tileGrass,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
				}else if(currentPixel == 0xFFFFFFFF){
					g2.drawImage(this.tileFloor,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
				}else if(currentPixel == 0xFFFF0000){
					g2.drawImage(this.tileGrass,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
					g2.drawImage(this.tileEnemy,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
				}else if(currentPixel == 0xFF6A00){
					g2.drawImage(this.tileGrass,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
					g2.drawImage(this.tileGun,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
				}else if(currentPixel == 0xFF00FF21){
					g2.drawImage(this.tileGrass,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
					g2.drawImage(this.tilePack,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
				}else if(currentPixel == 0xFFFFD800){
					g2.drawImage(this.tileGrass,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
					g2.drawImage(this.tileLife,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
				}else{
					g2.drawImage(this.tileGrass,xx*16*SCALE,yy*16*SCALE,16*SCALE,16*SCALE,null);
				}
			}
		}
	}
}
