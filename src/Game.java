import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Dimension;

public class Game {
	Position position = new Position();
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				JFrame frame = new JFrame ("Tic-tac-toe in Java");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new GridLayout(3,3));
				
				final Game game = new Game();
				final JButton[] buttons = new JButton[9];
				
				for (int i = 0; i < 9; i++) {
					final int idx = i;
					final JButton button  = new JButton();
					buttons[i] = button;
					button.setPreferredSize(new Dimension(100,100));
					button.setBackground(Color.BLACK);
					button.setOpaque(true);
					button.setFont(new Font(null,Font.ROMAN_BASELINE,75));
					button.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							button.setText("" + game.position.turn);
							game.move(idx);
							
							if (!game.position.gameIsOver()) {
								buttons[game.position.bestMove()].setText("" + game.position.turn);
								game.move(game.position.bestMove());
							
							}
							
							if (game.position.gameIsOver()) {
								String finalMessage = "";
								if (game.position.win('x')) {
									finalMessage = "Congratualtions, you've won!";
								} else if (game.position.win('o')) {
									finalMessage = "Looks like the computer wins";
								} else {
									finalMessage = "It's a draw";
								}
								JOptionPane.showMessageDialog(null, finalMessage);
							}
						}
						
						public void mousePressed(MouseEvent e) {}
						public void mouseReleased(MouseEvent e) {}
						public void mouseEntered(MouseEvent e) {}
						public void mouseExited(MouseEvent e) {}
						
					});
					frame.add(button);
				}
				frame.pack();
				frame.setVisible(true);
			}
		});

	}

	protected void move(int idx) {
		position = position.move(idx);
	}

}
