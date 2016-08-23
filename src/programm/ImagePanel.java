package programm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1021107801691559856L;
	Image image;
	boolean pathFilled = false;
	public ImagePanel(){
		this.setPreferredSize(new Dimension(200,200));
		this.setBackground(Color.white);
	}
	public void showImage(String filePath) throws InterruptedException, IOException {
		if (!filePath.equals("")){
			image = Toolkit.getDefaultToolkit().getImage(filePath);
	        MediaTracker mt = new MediaTracker(this);
	        mt.addImage(image,0);
	        mt.waitForAll();
		} else {
			image = null;
		}
		repaint();
    }
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,this.getWidth(),this.getHeight(), this);
    }
}
