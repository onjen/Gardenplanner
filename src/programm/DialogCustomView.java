package programm;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.toedter.calendar.JYearChooser;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class DialogCustomView extends javax.swing.JDialog implements ActionListener {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JButton okButton;
	private JButton chancelButton;
	private JLabel onlyPlantsLabel;
	private JYearChooser yearBox;
	private JDateChooser dateChooser;
	private JRadioButton rdbtnAusDemJahr;
	private JRadioButton rdbtnMitDerFarbe;
	private JRadioButton rdbtnMitDemDeutschen;
	private JRadioButton rdbtnDieNichtMehr;
	private JRadioButton rdbtnDieKaputtGegangen;
	private JRadioButton rdbtnNachBltezeit;
	private JComboBox comboBox;
	private String[] colors = { "Rot", "Grün", "Blau", "Gelb", "Orange", "Lila", "Türkis", "Hell" };
	Controller controller = Controller.getInstance();
	private JTextField textField;
	private JLabel lblNichtGedngtWurden;
	Filter filter = new Filter();


	/**
	* Auto-generated main method to display this JDialog
	*/
		
	public DialogCustomView(JFrame frame, String title, boolean modal) {
		super(frame, modal);
		this.setTitle(title);
		controller.setFilter(filter);
		getContentPane().setLayout(null);
		{
			onlyPlantsLabel = new JLabel();
			onlyPlantsLabel.setBounds(19, 8, 120, 14);
			getContentPane().add(onlyPlantsLabel);
			onlyPlantsLabel.setText("Nur Pflanzen Anzeigen...");
		}
		{
			yearBox = new JYearChooser();
			yearBox.setBounds(227, 29, 78, 21);
			yearBox.setEnabled(true);
			getContentPane().add(yearBox);
			yearBox.setPreferredSize(new java.awt.Dimension(78, 21));
		}
		{
			okButton = new JButton();
			okButton.setBounds(116, 196, 68, 23);
			getContentPane().add(okButton);
			okButton.setText("OK");
			okButton.setPreferredSize(new java.awt.Dimension(54, 23));
			okButton.addActionListener(this);
			okButton.setActionCommand("ok");
		}
		{
			chancelButton = new JButton();
			chancelButton.setBounds(194, 196, 111, 23);
			getContentPane().add(chancelButton);
			chancelButton.setText("Abbrechen");
			chancelButton.setPreferredSize(new java.awt.Dimension(111, 23));
			chancelButton.addActionListener(this);
			chancelButton.setActionCommand("abbrechen");
		}
		
		rdbtnAusDemJahr = new JRadioButton("aus dem Jahr");
		rdbtnAusDemJahr.setBounds(19, 29, 109, 23);
		rdbtnAusDemJahr.addActionListener(this);
		rdbtnAusDemJahr.setActionCommand("jahr");
		rdbtnAusDemJahr.setSelected(true);
		getContentPane().add(rdbtnAusDemJahr);
		
		rdbtnMitDerFarbe = new JRadioButton("mit der Blütenfarbe");
		rdbtnMitDerFarbe.setBounds(19, 55, 166, 23);
		rdbtnMitDerFarbe.addActionListener(this);
		rdbtnMitDerFarbe.setActionCommand("farbe");
		rdbtnMitDerFarbe.setSelected(false);
		getContentPane().add(rdbtnMitDerFarbe);
		
		rdbtnMitDemDeutschen = new JRadioButton("mit dem deutschen Namen");
		rdbtnMitDemDeutschen.setBounds(19, 81, 155, 23);
		rdbtnMitDemDeutschen.addActionListener(this);
		rdbtnMitDemDeutschen.setActionCommand("name");
		rdbtnMitDemDeutschen.setSelected(false);
		getContentPane().add(rdbtnMitDemDeutschen);
		
		rdbtnDieNichtMehr = new JRadioButton("die nach dem");
		rdbtnDieNichtMehr.setBounds(19, 107, 90, 23);
		rdbtnDieNichtMehr.addActionListener(this);
		rdbtnDieNichtMehr.setActionCommand("geduengt");
		rdbtnDieNichtMehr.setSelected(false);
		getContentPane().add(rdbtnDieNichtMehr);
		
		rdbtnDieKaputtGegangen = new JRadioButton("die eingegangen sind");
		rdbtnDieKaputtGegangen.setBounds(19, 133, 172, 23);
		rdbtnDieKaputtGegangen.setSelected(false);
		getContentPane().add(rdbtnDieKaputtGegangen);
		
		rdbtnNachBltezeit = new JRadioButton("nach Blütezeit");
		rdbtnNachBltezeit.setBounds(19, 159, 109, 23);
		rdbtnNachBltezeit.setSelected(false);
		getContentPane().add(rdbtnNachBltezeit);
		
	    ButtonGroup group = new ButtonGroup();
	    group.add(rdbtnAusDemJahr);
	    group.add(rdbtnMitDerFarbe);
	    group.add(rdbtnMitDemDeutschen);
	    group.add(rdbtnDieNichtMehr);
	    group.add(rdbtnDieKaputtGegangen);
	    group.add(rdbtnNachBltezeit);
		
		comboBox = new JComboBox(colors);
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(227, 56, 78, 20);
		comboBox.setEnabled(false);
		getContentPane().add(comboBox);
		//Position in Relation zum MainWindow
		Dimension frameSize = new Dimension();
		frameSize = frame.getSize(); 
		Point p = frame.getLocation(); 
		setLocation(p.x + frameSize.width / 3 , p.y + frameSize.height / 6 );
		getRootPane().setDefaultButton(okButton);
		
		textField = new JTextField();
		textField.setBounds(174, 82, 131, 20);
		textField.setEnabled(false);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(109, 107, 82, 20);
		dateChooser.setEnabled(false);
		getContentPane().add(dateChooser);
		
		lblNichtGedngtWurden = new JLabel("nicht gedüngt wurden");
		lblNichtGedngtWurden.setBounds(194, 111, 111, 14);
		getContentPane().add(lblNichtGedngtWurden);
		
		initGUI();
		setResizable(false);
		setVisible(true);
	}
	
	private void initGUI() {
		try {
			this.setSize(335, 255);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("ok".equals(e.getActionCommand())){
			//Datum slider entfernen falls vorhanden
			if (((BorderLayout) controller.getMw().getLayout()).getLayoutComponent(BorderLayout.WEST) != null){
        		controller.getMw().remove(((BorderLayout) controller.getMw().getLayout()).getLayoutComponent(BorderLayout.WEST));
        	}
			if (rdbtnAusDemJahr.isSelected()){
				filter.filterPlantsYear(yearBox.getYear());
				this.setVisible(false);
			} else if (rdbtnMitDerFarbe.isSelected()){
				filter.filterPlantsColor(colors[comboBox.getSelectedIndex()]);
				controller.setActualFilterColor(colors[comboBox.getSelectedIndex()]);
				this.setVisible(false);
			} else if (rdbtnMitDemDeutschen.isSelected()){
				if (textField.getText().equals("")){
					JOptionPane.showMessageDialog(this, "Bitte einen gültigen Namen eintragen!");
				} else {
					filter.filterName(textField.getText());
					this.setVisible(false);
				}
			} else if (rdbtnDieNichtMehr.isSelected()){
				if (dateChooser.getDate() == null || controller.df.format(dateChooser.getDate()).equals(controller.df.format(new Date(0)))){
					JOptionPane.showMessageDialog(this, "Bitte ein gültiges Datum eintragen!");
				}
				else {
					filter.filterGeduengt(dateChooser.getDate());
					this.setVisible(false);
				}
			} else if (rdbtnDieKaputtGegangen.isSelected()){
				filter.filterKaputt();
				this.setVisible(false);
			} else if (rdbtnNachBltezeit.isSelected()){
				filter.filterBluetezeit();
				this.setVisible(false);
			}
		} 
		else if ("abbrechen".equals(e.getActionCommand())){
			if (controller.isFiltered()){
				controller.getMw().getCustomDisplay().setSelected(true);
			} else {
				controller.getMw().getShowAll().setSelected(true);
			}
	    	this.setVisible(false);
		}
		else if ("jahr".equals(e.getActionCommand())){
			comboBox.setEnabled(false);
			yearBox.setEnabled(true);
			textField.setEnabled(false);
			dateChooser.setEnabled(false);
		}
		else if ("farbe".equals(e.getActionCommand())){
			yearBox.setEnabled(false);
			comboBox.setEnabled(true);
			textField.setEnabled(false);
			dateChooser.setEnabled(false);
		}
		else if ("name".equals(e.getActionCommand())){
			yearBox.setEnabled(false);
			comboBox.setEnabled(false);
			textField.setEnabled(true);
			dateChooser.setEnabled(false);
		}
		else if ("geduengt".equals(e.getActionCommand())){
			yearBox.setEnabled(false);
			comboBox.setEnabled(false);
			textField.setEnabled(false);
			dateChooser.setEnabled(true);
		}
	}
}
