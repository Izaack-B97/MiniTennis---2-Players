package minitennis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	int x = 100;
	int y = 100;
	int xa = 1;
	int ya = 1;
	private Game game;

	public Ball(Game game) {
		this.game= game;
	}

	void move() {
		if (x + xa < 0)
			xa = game.speed1;
		if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.speed1;
		if (y + ya < 0)
			ya = game.speed1;
		if (y + ya > game.getHeight() - DIAMETER)
                    game.gameOver(); 
                if (y + ya == 0)
                    game.gameOver();
		if (collision1()){
			ya = -game.speed1;
			y = game.racquet1.getTopY() - DIAMETER;
                        game.speed1++;
                }else if (collision2()){
                        ya = game.speed1;
                        y = game.racquet2.getTopY() + DIAMETER;
                        game.speed2++;
                }
		x = x + xa;
		y = y + ya;
	}

	private boolean collision1() {
		return game.racquet1.getBounds().intersects(getBounds());
	}
        private boolean collision2() {
		return game.racquet2.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
                g.setPaint(Color.black);
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}