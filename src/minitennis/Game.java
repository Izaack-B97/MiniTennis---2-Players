package minitennis;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this);
	Racket1 racquet1 = new Racket1(this);
        Racket2 racquet2 = new Racket2(this);
        int speed1 = 1;
        int speed2 = 0;

	private int getScore1() {
		return speed1 - 1;
	}
        private int getScore2() {
		return speed2;
	}
        
        
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet1.keyReleased(e);
                                racquet2.keyReleased(e);
                        }

			@Override
			public void keyPressed(KeyEvent e) {
				racquet1.keyPressed(e);
                                racquet2.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	private void move() {
		ball.move();
		racquet1.move();
                racquet2.move();
	}
        
        public void gameOver() {
                if(getScore1() > getScore2())
                    JOptionPane.showMessageDialog(this, "\t Player 1: " + getScore1() + "\n" + "\t Player 2: " + getScore2() + "\n\tPLAYER 1 WINNER IS CONGRULATIONS :D !!! ", "Game Over", JOptionPane.YES_NO_OPTION);
                else if(getScore2()>getScore1())
                   JOptionPane.showMessageDialog(this, "\t Player 1: " + getScore1() + "\n" + "\t Player 2: " + getScore2() + "\n\tPLAYER 2 WINNER IS CONGRULATIONS :D !!! ", "Game Over", JOptionPane.YES_NO_OPTION);
                else
                    JOptionPane.showMessageDialog(this, "\t Player 1: " + getScore1() + "\n" + "\t Player 2: " + getScore2() + "\n\tSORRY, THEY TIED :( !!! ", "Game Over", JOptionPane.YES_NO_OPTION);
                System.exit(ABORT);
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.white);
                g2d.fillRect(0, 180, 300, 10);//Red
                
		ball.paint(g2d);
		racquet1.paint(g2d);
                racquet2.paint(g2d);
                
                g2d.setColor(Color.white);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore1()), 250, 350);
                
                g2d.setColor(Color.white);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore2()), 10, 30);
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
                game.setBackground(Color.green);
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}