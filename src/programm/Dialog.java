package programm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;




public class Dialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3210145479429698896L;
	//Variablen für die Zwischenspeicherung
	private String botanischerName;
	private String deutscherName;
	private String sorte;
	private Color bluetenFarbe = Color.black;
	private String pflegehinweis;
	private String comment;
	private String bezug;
	private Date gepflanzt;
	private Date geduengt;
	private String imagePath = "";
	private int monatVon;
	private int monatBis;
	
	private boolean jDateChooser1ok = false;
	private boolean deutschOK = false;
	private boolean geduengtChooserOk = false;
	private boolean editMode;
	
	private JColorChooser jColorChooser;
	private Plant plantToEdit;
	Controller controller = Controller.getInstance();
	Filter filter = new Filter();
	
	
	//Variablen für die GUI

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JTextField botanField;
	private javax.swing.JTextField deutschField;
	private javax.swing.JTextField sorteField;
	private javax.swing.JTextField pflegehinweisField;
	private javax.swing.JTextField bezugsField;
	private javax.swing.JTextField commentField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser gepflanztChooser;
    private com.toedter.calendar.JDateChooser geduengtChooser;
    private com.toedter.calendar.JMonthChooser monthChooser1;
    private com.toedter.calendar.JMonthChooser monthChooser2;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton pictureButton;
    private ImagePanel imagePanel;
    private JLabel label;
    private JCheckBox chckbxDiesePflanzeBlht;
	
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public Dialog(JFrame frame, String title, boolean modal){
		
		super(frame, modal);
		this.setTitle(title);
		getContentPane().setLayout(new GridLayout(8, 2));
		
		editMode = false;
		
		//Position in Relation zum MainWindow
		Dimension frameSize = new Dimension();
		frameSize = frame.getSize(); 
	    Point p = frame.getLocation(); 
	    setLocation(p.x + frameSize.width / 3 , p.y + frameSize.height / 6 );
	    
	    botanField = new javax.swing.JTextField();
	    deutschField = new javax.swing.JTextField();
        sorteField = new javax.swing.JTextField();
        pflegehinweisField = new javax.swing.JTextField();
        bezugsField = new javax.swing.JTextField();
        commentField = new javax.swing.JTextField();
	    
	    initComponents();
	    
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    getRootPane().setDefaultButton(jButton1);
 
	    setResizable(false);
	    setVisible(true);
	}
	public Dialog(JFrame frame, String title, boolean modal, String botanischerName,String deutscherName, 
			String sorte, String pflegehinweis, String comment, String bezug, Date gepflanzt, Date geduengt, Color color, Plant plant, String path,
			int monatVon, int monatBis){
		super(frame, modal);
		this.setTitle(title);
		getContentPane().setLayout(new GridLayout(8, 2));
		
		//Position in Relation zum MainWindow
		Dimension frameSize = new Dimension();
		frameSize = frame.getSize(); 
	    Point p = frame.getLocation(); 
	    setLocation(p.x + frameSize.width / 3 , p.y + frameSize.height / 6 );
	    
	    editMode = true;
	    plantToEdit = plant;
	    bluetenFarbe = plant.getBluetenFarbe();
	    
	    imagePath = path;
	    
	    botanField = new javax.swing.JTextField(botanischerName);
	    deutschField = new javax.swing.JTextField(deutscherName);
        sorteField = new javax.swing.JTextField(sorte);
        pflegehinweisField = new javax.swing.JTextField(pflegehinweis);
        commentField = new javax.swing.JTextField(comment);
        bezugsField = new javax.swing.JTextField(bezug);
	    
	    initComponents();
	    
	    Controller controller = Controller.getInstance();
	    if (gepflanzt != null) {
	    	if (!controller.df.format(gepflanzt).equals(controller.df.format(new Date(0)))) {
			    gepflanztChooser.setDate(gepflanzt);
		    }
	    }
	    if (geduengt != null) {
	    	if (!controller.df.format(geduengt).equals(controller.df.format(new Date(0)))) {
		    	geduengtChooser.setDate(geduengt);
		    	jCheckBox2.setSelected(true);
		    	geduengtChooser.setEnabled(true);
		    }
	    }
	    
	    jPanel1.setBackground(color);
	    if (monatVon == 12){
	    	chckbxDiesePflanzeBlht.setSelected(false);
	    	monthChooser1.setEnabled(false);
	    	monthChooser2.setEnabled(false);
	    } else {
	    	monthChooser1.setMonth(monatVon);
	    	monthChooser2.setMonth(monatBis);
	    }
	    
	    if (imagePath.equals("")){
	    	imagePanel.setBackground(Color.white);
	    } else {
		    try {
				imagePanel.showImage(imagePath);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    getRootPane().setDefaultButton(jButton1);
 
	    setResizable(false);
	    setVisible(true);
	}
	private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jCheckBox2.isSelected() == false){
        	geduengtChooser.setEnabled(false);
        } else {
        	geduengtChooser.setEnabled(true);
        }
    }
	private void chckbxDiesePflanzeBlhtActionPerformed(java.awt.event.ActionEvent evt) {
		if (chckbxDiesePflanzeBlht.isSelected() == false){
			monthChooser1.setEnabled(false);
			monthChooser2.setEnabled(false);
		} else {
			monthChooser1.setEnabled(true);
			monthChooser2.setEnabled(true);
		}
	}
	
	
	private void initComponents(){
		
		jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10= new javax.swing.JLabel();
        
        
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pictureButton = new javax.swing.JButton();
        gepflanztChooser = new com.toedter.calendar.JDateChooser();
        geduengtChooser = new com.toedter.calendar.JDateChooser();
        monthChooser1 = new com.toedter.calendar.JMonthChooser();
        monthChooser2 = new com.toedter.calendar.JMonthChooser();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        imagePanel = new ImagePanel();
        
        Date date = Calendar.getInstance().getTime();
        gepflanztChooser.setDate(date);
        monthChooser1.setLocale(Locale.GERMANY);
        monthChooser2.setLocale(Locale.GERMANY);
        monthChooser1.setMonth(4);
        monthChooser2.setMonth(7);
        jCheckBox2.setSelected(false);
        geduengtChooser.setEnabled(false);
        
        jButton1.addActionListener(this);
        jButton2.addActionListener(this);
        jButton3.addActionListener(this);
        pictureButton.addActionListener(this);
        jButton1.setActionCommand("ok");
        jButton2.setActionCommand("abbrechen");
        jButton3.setActionCommand("farbe");
        pictureButton.setActionCommand("picture");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Botanischer Name:");
        jLabel2.setText("Deutscher Name:");
        jLabel3.setText("Sorte:");
        jLabel4.setText("Blütenfarbe:");
        jLabel5.setText("Pflegehinweise:");
        jLabel6.setText("gepflanzt am:");
        jLabel7.setText("zuletzt gedüngt:");
        jLabel8.setText("Bezugsquelle:");
        jLabel9.setText("Kommentar:");	
        jLabel10.setText("Blütezeit:");

        
        jButton1.setText("OK");
        jButton2.setText("Abbrechen");    
        jButton3.setText("Farbe auswählen...");
        pictureButton.setText("Bild auswählen...");
        jCheckBox2.setText("Bekannt");
        
        jPanel1.setBackground(Color.BLACK);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        imagePanel.setBackground(Color.WHITE);

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );

        jButton1.setText("OK");

        jButton2.setText("Abbrechen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createParallelGroup(Alignment.LEADING)
        							.addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        							.addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        							.addComponent(pictureButton, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
        						.addComponent(jLabel9)
        						.addComponent(jLabel8)
        						.addComponent(jLabel5)
        						.addComponent(jLabel3)
        						.addComponent(jLabel2)
        						.addComponent(jLabel4)
        						.addComponent(jLabel1)
        						.addComponent(jLabel10))
        					.addGap(34)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(imagePanel, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        								.addComponent(geduengtChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(gepflanztChooser, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
        							.addGap(18)
        							.addComponent(jCheckBox2))
        						.addComponent(botanField, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        						.addComponent(deutschField, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        						.addComponent(sorteField, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        						.addComponent(pflegehinweisField, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        						.addComponent(bezugsField, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        						.addComponent(commentField, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
        						.addComponent(monthChooser1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(jButton3, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jButton1)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jButton2)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(botanField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel1))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(deutschField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel2))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(sorteField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel3))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(pflegehinweisField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel5))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(bezugsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel8))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(commentField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel9))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(monthChooser1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel10))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 5, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jLabel4)
        					.addGap(20))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(jButton3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        						.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.UNRELATED)))
        			.addGap(3)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(gepflanztChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel6))
        			.addGap(12)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel7, Alignment.TRAILING)
        				.addComponent(geduengtChooser, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jCheckBox2, Alignment.TRAILING))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pictureButton)
        				.addComponent(imagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jButton1)
        				.addComponent(jButton2))
        			.addContainerGap())
        );
        
        label = new JLabel("New label");
        getContentPane().add(label);
        jLabel11= new javax.swing.JLabel();
        monthChooser1.add(jLabel11, BorderLayout.CENTER);
        monthChooser1.add(monthChooser2, BorderLayout.EAST);
        jLabel11.setText(" - ");
        
        chckbxDiesePflanzeBlht = new JCheckBox("Diese Pflanze blüht");
        chckbxDiesePflanzeBlht.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	chckbxDiesePflanzeBlhtActionPerformed(evt);
            }
        });
        chckbxDiesePflanzeBlht.setSelected(true);
        monthChooser1.add(chckbxDiesePflanzeBlht, BorderLayout.NORTH);
        getContentPane().setLayout(layout);

        pack();
    }

	public void auslesen(){
		if (editMode == false){
			if (deutschField.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bitte wenigstens einen deutschen Namen angeben");
				deutschOK = false;
			} else {
				deutschOK = true;
			}
			if (gepflanztChooser.getDate() == null || controller.df.format(gepflanztChooser.getDate()).equals(controller.df.format(new Date(0)))) {
				JOptionPane.showMessageDialog(this, "Bitte ein gültiges Einpflanzdatum eintragen");
				jDateChooser1ok = false;
			} else {
				jDateChooser1ok = true;
			}
			if (jCheckBox2.isSelected()){
				if (geduengtChooser.getDate() == null || controller.df.format(geduengtChooser.getDate()).equals(controller.df.format(new Date(0)))){
					JOptionPane.showMessageDialog(this, "Bitte ein gültiges Düngungsdatum eintragen oder deaktivieren!");
					geduengtChooserOk = false;
				} else {
					geduengtChooserOk = true;
				}
			} else {
				geduengtChooserOk = true;
			}
				botanischerName = botanField.getText();
				deutscherName = deutschField.getText();
				sorte = sorteField.getText();
				pflegehinweis = pflegehinweisField.getText();
				comment = commentField.getText();
				bezug = bezugsField.getText();
				gepflanzt = gepflanztChooser.getDate();
				geduengt = geduengtChooser.getDate();
				if (chckbxDiesePflanzeBlht.isSelected()){
					monatVon = monthChooser1.getMonth();
				} else {
					// 12 heißt die Pflanze blüht nicht
					monatVon = 12;
				}
				monatBis = monthChooser2.getMonth();
				
			} 
			
			else {
				if (deutschField.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Bitte wenigstens einen deutschen Namen angeben!");
						deutschOK = false;
					} else {
						deutschOK = true;
					}
					if (gepflanztChooser.getDate() == null || controller.df.format(gepflanztChooser.getDate()).equals(controller.df.format(new Date(0)))) {
						JOptionPane.showMessageDialog(this, "Bitte ein gültiges Einpflanzdatum eintragen!");
						jDateChooser1ok = false;
					} else {
						jDateChooser1ok = true;
					}
					if (jCheckBox2.isSelected()){
						if (geduengtChooser.getDate() == null || controller.df.format(geduengtChooser.getDate()).equals(controller.df.format(new Date(0)))){
							JOptionPane.showMessageDialog(this, "Bitte ein gültiges Düngungsdatum eintragen oder deaktivieren!");
							geduengtChooserOk = false;
						} else {
							geduengtChooserOk = true;
						}
					} else {
						geduengtChooserOk = true;
					}
						plantToEdit.setBotanischerName(botanField.getText());
						plantToEdit.setDeutscherName(deutschField.getText());
						plantToEdit.setSorte(sorteField.getText());
						plantToEdit.setPflegehinweis(pflegehinweisField.getText());
						plantToEdit.setComment(commentField.getText());
						plantToEdit.setBezug(bezugsField.getText());
						plantToEdit.setGepflanzt(gepflanztChooser.getDate());
						plantToEdit.setGeduengt(geduengtChooser.getDate());
						plantToEdit.setBluetenFarbe(bluetenFarbe);
						plantToEdit.setImagePath(imagePath);
						if (chckbxDiesePflanzeBlht.isSelected()){
							plantToEdit.setMonatVon(monthChooser1.getMonth());
						} else {
							//12 heißt, dass die Pflanze nicht blüht!!
							plantToEdit.setMonatVon(12);
						}
						plantToEdit.setMonatBis(monthChooser2.getMonth());
						controller.getMw().refreshInformationPanel(plantToEdit);
			}
	}
	public void createPlant(){
		if (jDateChooser1ok == true && geduengtChooserOk == true && deutschOK == true){
			this.setVisible(false);
			int posx = (int)controller.convertToBasis(controller.getMouseX());
			int posy = (int)controller.convertToBasis(controller.getMouseY());
			Color color = bluetenFarbe;
			if (color == null) {
				bluetenFarbe = Color.black;
			}
			if (editMode == false){
				Rectangle rect = new Rectangle(posx, posy, controller.getQuadratBreite(), controller.getQuadratBreite());
				Plant plant = new Plant(botanischerName, deutscherName, sorte, color, pflegehinweis, comment, bezug, gepflanzt, geduengt, imagePath, rect, false, monatVon, monatBis);
				controller.getPlants().add(plant);
			}
			controller.getDrawingPane().repaint();
		}
	}
	
	public String toString() {
		return  "Botanischer Name: " + this.botanischerName + "\n" + 
				"Deutscher Name: " + this.deutscherName + "\n" + 
				"Sorte: " + this.sorte + " \n" + 
				"Blütenfarbe: " + this.bluetenFarbe + " \n" + 
				"Pflegehinweis: " + this.pflegehinweis + "\n" + 
				"Gepflanzt am: " + gepflanzt + "\n" + 
				"zuletzt gedüngt: " + this.geduengt + "\n" +
				"Path: " + imagePath + "\n";
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	       if ("ok".equals(e.getActionCommand())) {
	          auslesen();
	          createPlant();
	       }
	       else if ("abbrechen".equals(e.getActionCommand()))
	       {
	    	   this.setVisible(false);
	       } 
	       else if ("farbe".equals(e.getActionCommand())){
	    	   
	    	   jColorChooser = new JColorChooser();
	    	   jColorChooser.setPreviewPanel(new JPanel());
	    	   JDialog dialog = JColorChooser.createDialog(Dialog.this,
	    	            "Farbe Wählen", true, jColorChooser, new ActionListener() {
	    	              public void actionPerformed(ActionEvent e) {
	    	                bluetenFarbe = jColorChooser.getColor();
	    	              }
	    	            }, null);
	    	        dialog.setVisible(true);
	    	        jPanel1.setBackground(bluetenFarbe);
	       }
	       else if ("picture".equals(e.getActionCommand())){
	    	   JFileChooser fc = new JFileChooser();
               FileFilter filter1 = new ExtensionFileFilter(".jpg, .jpeg, .png, .gif, .tif, .tiff", new String[] { "JPG", "JPEG", "PNG", "GIF", "TIF", "TIFF" });
               fc.setFileFilter(filter1);
               fc.setAccessory(new ImagePreview(fc));
           	   fc.setAcceptAllFileFilterUsed(false);
           	
	           int returnVal = fc.showDialog(this, "Bild hinzufügen");
	           File file = fc.getSelectedFile();
	           
	           if (JFileChooser.CANCEL_OPTION == returnVal || JFileChooser.ERROR_OPTION == returnVal){
	        	   //TODO
	           }
	           
	           this.imagePath = file.getPath();
	           try {
	        	   imagePanel.showImage(file.getPath());
	           } catch (InterruptedException e1) {
	        	   e1.printStackTrace();
	           } catch (IOException e1) {
	        	   e1.printStackTrace();
	           }
	       }
	       
	}

	public String getBotanischerName() {
		return botanischerName;
	}

	public void setBotanischerNameField(String botanischerName) {
		botanField.setText(botanischerName);
	}

	public String getDeutscherName() {
		return deutscherName;
	}

	public void setDeutscherNameField(String deutscherName) {
		deutschField.setText(deutscherName);
	}

	public String getSorte() {
		return sorte;
	}

	public void setSorteField(String sorte) {
		sorteField.setText(sorte);
	}

	public Color getBluetenFarbe() {
		return bluetenFarbe;
	}

	public void setBluetenFarbeField(Color bluetenFarbe) {
		jPanel1.setBackground(bluetenFarbe);
	}

	public String getPflegehinweis() {
		return pflegehinweis;
	}

	public void setPflegehinweisField(String pflegehinweis) {
		pflegehinweisField.setText(pflegehinweis);
	}

	public Date getGepflanzt() {
		return gepflanzt;
	}

	public void setGepflanztField(Date gepflanzt) {
		gepflanztChooser.setDate(gepflanzt);
	}

	public Date getGeduengt() {
		return geduengt;
	}

	public void setGeduengtField(Date geduengt) {
		geduengtChooser.setDate(geduengt);
	}
}
