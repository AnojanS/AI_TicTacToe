import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Dimension;

public class Game {
	Position position;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				JFrame frame = new JFrame ("Tic-tac-toe in Java");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new GridLayout(3,3));
				
				Game game = new Game();
				
				for (int i = 0; i < 9; i++) {
					final int idx = i;
					JButton button  = new JButton();
					button.setPreferredSize(new Dimension(100,100));
					button.setBackground(Color.BLACK);
					button.setOpaque(true);
					button.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							button.setText("" + idx);
							
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

}
