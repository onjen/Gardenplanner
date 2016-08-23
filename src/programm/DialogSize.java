package programm;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DialogSize extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2619771069857949336L;
	private JTextField widthField;
	Controller controller = Controller.getInstance();
	private JTextField heightField;
	public DialogSize(JFrame frame, String title, boolean modal, int width, int height) {
		super(frame, modal);
		this.setTitle(title);
		getContentPane().setLayout(null);
		
		JLabel lblDeutscherName = new JLabel("Breite (in Pixeln):");
		lblDeutscherName.setBounds(10, 11, 89, 14);
		getContentPane().add(lblDeutscherName);
		
		widthField = new JTextField();
		widthField.setBounds(104, 8, 47, 20);
		widthField.setText(Integer.toString(width));
		getContentPane().add(widthField);
		widthField.setColumns(10);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(67, 84, 84, 23);
		getContentPane().add(btnAbbrechen);
		btnAbbrechen.addActionListener(this);
		btnAbbrechen.setActionCommand("abbrechen");
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(10, 84, 47, 23);
		getContentPane().add(btnOk);
		btnOk.addActionListener(this);
		btnOk.setActionCommand("ok");
		
		//Position in Relation zum MainWindow
		Dimension frameSize = new Dimension();
		frameSize = frame.getSize(); 
		Point p = frame.getLocation(); 
		setLocation(p.x + frameSize.width / 3 , p.y + frameSize.height / 6 );
		
		this.setSize(170, 140);
		
		getRootPane().setDefaultButton(btnOk);
		
		JLabel lblHheinPixeln = new JLabel("Höhe (in Pixeln):");
		lblHheinPixeln.setBounds(10, 46, 89, 14);
		getContentPane().add(lblHheinPixeln);
		
		heightField = new JTextField();
		heightField.setColumns(10);
		heightField.setBounds(104, 43, 47, 20);
		heightField.setText(Integer.toString(height));
		getContentPane().add(heightField);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) throws NumberFormatException{
		if ("ok".equals(e.getActionCommand())){
			if (widthField.getText() == "" || heightField.getText() == ""){
				JOptionPane.showMessageDialog(controller.getMw().getFrame(),
					    "Bitte gültige Werte angeben!",
					    "Achtung!",
					    JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					int height = Integer.valueOf(heightField.getText());
					int width = Integer.valueOf(widthField.getText());
					if (height > 1 && width > 1){
						Rectangle rect = controller.getPlantToResize().getRectangle();
						rect.height = height;
						rect.width = width;
					} else {
						throw new NumberFormatException();
					}
					controller.getDrawingPane().repaint();
					this.setVisible(false);
				} catch (NumberFormatException e1){
					JOptionPane.showMessageDialog(controller.getMw().getFrame(),
						    "Bitte gültige Werte angeben!\n" +
						    "(müssen größer als 1 sein)",
						    "Achtung!",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
		} 
		else if ("abbrechen".equals(e.getActionCommand())){
	    	this.setVisible(false);
		}
	}
}
