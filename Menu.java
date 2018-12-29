import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	Font fnt = new Font("helvetica", 1, 50);
	Font fnt2 = new Font("arial", 1, 30);
	Font fnt3 = new Font("Trebuchet", 1, 20);
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		if(game.gameState == Game.STATE.Menu){
			//play button
			if(mouseOver(mx, my, 210, 150, 200, 64)){
				game.gameState = Game.STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
			//help button
			if(mouseOver(mx, my, 210, 250, 200, 64)){
				game.gameState = Game.STATE.Help;
			}
			
			//quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				System.exit(1);
			}
		}
		//back button in help
		if(game.gameState == Game.STATE.Help){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = Game.STATE.Menu;
				return;
			}
		}
		//try again button in end screen
		if(game.gameState == Game.STATE.End || game.gameState == Game.STATE.Win){
			if(mouseOver(mx, my, 210, 350, 200, 64)){
				game.gameState = Game.STATE.Game;
				hud.setScore(0);
				hud.setLevel(1);
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
			}
		}

		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick(){
		
	}
	public void render(Graphics g){
		if (game.gameState == Game.STATE.Menu){
			
			g.setFont(fnt);
			
			g.setColor(Color.WHITE);
			g.drawString("Wave", 240, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
		}
		else if(game.gameState == Game.STATE.Help){
			g.setFont(fnt);
			
			g.setColor(Color.WHITE);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("Use arrow keys to move and dodge enemies", 10, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		}
		else if(game.gameState == Game.STATE.End){
			g.setFont(fnt);
			
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 170, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 245, 390);
		}
		else if (game.gameState == Game.STATE.Win){
			g.setFont(fnt);
			
			g.setColor(Color.WHITE);
			g.drawString("You win", 215, 70);
			
			g.setFont(fnt3);
			g.drawString("You have beat the game. Nice job.", 150, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 245, 390);
		}
	}
}
