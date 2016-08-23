package programm;
import programm.StatusBar;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
//TODO irgendwie ist noch ein Bug im CustomDialog beim gedüngt Filter das Datum gibt manchmal nullPointer, aber wann genau kp
//TODO verhalten ändern wenn mehrere angewählt sind -> das menü wenn man auf eine pflanze klickt überall anzeigen
//TODO Statistiken

public class MainWindow extends JPanel implements ActionListener
    {
	private static final long serialVersionUID = 1L;
	private DrawingPane drawingPane;
    private StatusBar statusBar;
    private JScrollPane scroller;
    private JScrollPane informationScroller;
    private InformationPanel informationPanel;
    private JToolBar toolBar;
    public static JComponent mw;
    private Controller controller;
    private JMenuItem alleEingegangen;
    
    private JRadioButtonMenuItem customDisplay;
    private JRadioButtonMenuItem showAll;
    
    //musste hier oben rein damit der JDialog aufgerufen werden kann
    private static JFrame frame = new JFrame("Garten Planer");
    
    public MainWindow(boolean newDialog) {
    	
    }
    
    public MainWindow() {
        super(new BorderLayout());

        controller = Controller.getInstance();
        
        toolBar = new JToolBar("Iconleiste");
        toolBar.setFloatable(false);
        addButtons(toolBar);
        setDoubleBuffered(true);
        statusBar = new StatusBar();
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        //Set up the drawing area.
        drawingPane = new DrawingPane();
        drawingPane.openImageAtStart();
        
        //set up the informationn Panel
        informationPanel = new InformationPanel();
        
        //Put the drawing area in a scroll pane.
        scroller = new JScrollPane(drawingPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setPreferredSize(new Dimension(600,600));
        scroller.getVerticalScrollBar().setUnitIncrement(30);
        scroller.getHorizontalScrollBar().setUnitIncrement(30);
        scroller.getViewport().setSize(controller.getImageWidth(), controller.getImageHeight());
        scroller.setDoubleBuffered(true);
        
        informationScroller = new JScrollPane(informationPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        informationScroller.setPreferredSize(new Dimension(260,600));
        informationScroller.getVerticalScrollBar().setUnitIncrement(30);
        informationScroller.getHorizontalScrollBar().setUnitIncrement(30);
        
        JMenu datei = new JMenu("Datei");
        JMenu anzeige = new JMenu("Anzeige");
        JMenu auswahl = new JMenu("Auswahl");
        JMenu hilfe = new JMenu("Hilfe");
        menuBar.add(datei);
        menuBar.add(anzeige);
        menuBar.add(auswahl);
        menuBar.add(hilfe);
        
        // Datei-Menü aufbauen
        JMenuItem speichern = new JMenuItem("Speichern");
        JMenuItem open = new JMenuItem("Öffnen");
        JMenuItem beenden = new JMenuItem("Beenden");
        speichern.addActionListener(this);
        speichern.setActionCommand("speichern");
        speichern.setIcon(createImageIcon("document-save_small.png"));
        open.addActionListener(this);
        open.setActionCommand("grundriss");
        open.setIcon(createImageIcon("document-open_small.png"));
        beenden.addActionListener(this);
        beenden.setActionCommand("beenden");
        beenden.setIcon(createImageIcon("system-shutdown_small.png"));
        datei.add(open);
        datei.add(speichern);
        datei.addSeparator();
        datei.add(beenden);
        
        //Auswahl Menü aubauen
        JMenuItem nothing = new JMenuItem("Nichts auswählen");
        JMenuItem alleGeduengt = new JMenuItem("Ausgewählte heute gedüngt");
        JMenuItem alleLoeschen = new JMenuItem("Ausgewählte löschen");
        alleEingegangen = new JMenuItem("Ausgewählte sind eingegangen");
        nothing.addActionListener(this);
        nothing.setActionCommand("nothing");
        nothing.setIcon(createImageIcon("fileclose_small.png"));
        alleGeduengt.addActionListener(this);
        alleGeduengt.setActionCommand("alleGeduengt");
        alleGeduengt.setIcon(createImageIcon("view-calendar-day_small.png"));
        alleLoeschen.addActionListener(this);
        alleLoeschen.setActionCommand("alleLoeschen");
        alleLoeschen.setIcon(createImageIcon("edit-delete.png"));
        alleEingegangen.addActionListener(this);
        alleEingegangen.setActionCommand("alleEingegangen");
        alleEingegangen.setIcon(createImageIcon("edit-clear_small.png"));
        
        auswahl.add(nothing);
        auswahl.add(alleGeduengt);
        auswahl.add(alleEingegangen);
        auswahl.add(alleLoeschen);
        
        //Anzeige-Menü aufbauen
        JMenuItem zoomIn = new JMenuItem("Hereinzoomen");
        JMenuItem zoomOut = new JMenuItem("Herauszoomen");
        customDisplay = new JRadioButtonMenuItem("Nur Bestimmte Pflanzen...");
        showAll = new JRadioButtonMenuItem("Alle Pflanzen");
        ButtonGroup group = new ButtonGroup();
        group.add(customDisplay);
        group.add(showAll);
        zoomIn.addActionListener(this);
        zoomIn.setActionCommand("zoomIn");
        zoomIn.setIcon(createImageIcon("zoom-in_small.png"));
        zoomOut.addActionListener(this);
        zoomOut.setActionCommand("zoomOut");
        zoomOut.setIcon(createImageIcon("zoom-out_small.png"));
        customDisplay.addActionListener(this);
        customDisplay.setActionCommand("customDisplay");
        showAll.addActionListener(this);
        showAll.setActionCommand("showAll");
        
        anzeige.add(zoomIn);
        anzeige.add(zoomOut);
        anzeige.addSeparator();
        anzeige.add(customDisplay);
        anzeige.add(showAll);
        
        //Hilfe-Menü aufbauen
        JMenuItem kurzanleitung = new JMenuItem("Kurzanleitung");
        JMenuItem about = new JMenuItem("About");
        kurzanleitung.addActionListener(this);
        kurzanleitung.setActionCommand("kurzanleitung");
        kurzanleitung.setIcon(createImageIcon("help-browser_small.png"));
        about.addActionListener(this);
        about.setActionCommand("about");
        about.setIcon(createImageIcon("system_users_small.png"));
        hilfe.add(kurzanleitung);
        hilfe.add(about);
        
        //Lay out
        add(toolBar, BorderLayout.PAGE_START);
        add(scroller, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        add(informationScroller, BorderLayout.EAST);
        controller.setMw(this);
        
        //Menu Items Häkchen setzen
        showAll.setSelected(true);
        customDisplay.setSelected(false);
    }

    public void showPlantDialog(){
    	new Dialog(frame, "Neue Pflanze", true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if ("grundriss".equals(e.getActionCommand())) {
            drawingPane.openImage();
            statusBar.setMessageAndClear("Grundriss geladen");
        }
        
        else if("speichern".equals(e.getActionCommand())) {
            try {
                drawingPane.save();
                //andere Statusbar messages im Filehandler
            } catch (IOException ex) {
                statusBar.setMessageAndClear("Speichern gescheitert");
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if("beenden".equals(e.getActionCommand())){
			Object[] options = { "Nein", "Ja"};
		    int antwort=-1;
		    antwort=(JOptionPane.showOptionDialog(null, "Alle Daten gespeichert und wirklich verlassen?", "Warnung", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]));
		    if(antwort==1){
		    	System.exit(0);
		    }
        }
        else if("zoomIn".equals(e.getActionCommand())){
        	zoomIn();
        } 
        else if("zoomOut".equals(e.getActionCommand())){
        	zoomOut();
        }
        else if("customDisplay".equals(e.getActionCommand())){
    		new DialogCustomView(frame, "Auswahl für bestimmte Pflanzen", true);
        }
        else if ("showAll".equals(e.getActionCommand())){
        	controller.setFiltered(false);
        	showAll.setSelected(true);
        	alleEingegangen.setEnabled(true);
        	if (((BorderLayout)getLayout()).getLayoutComponent(BorderLayout.WEST) != null){
        		remove(((BorderLayout)getLayout()).getLayoutComponent(BorderLayout.WEST));
        	}
        	this.getStatusBar().setMessage("Aktuelle Ansicht: Alle Pflanzen werden angezeigt");
        	controller.getDrawingPane().repaint();
        }
        else if ("kurzanleitung".equals(e.getActionCommand())){
        	//TODO aktuell halten bei neuen Funktionen!
        	JOptionPane.showMessageDialog(frame,
        		    "Mit rechter Maustaste eine Pflanze erstellen \n" +
        		    "bzw. eine bestehende bearbeiten. \n\n" +
        		    "Mit linker Maustaste auf eine Pflanze klicken\n" +
        		    "für weitere Infos in der Seitenleiste. \n\n" +
        		    "Mit gehaltener Linker Maustaste eine Pflanze \n" +
        		    "verschieben. \n\n" +
        		    "Mit gehaltener mittlerer Maustaste den Grundriss\n" +
        		    "hin und her schieben. \n\n" +
        		    "Im Anzeige Menü kann man Filter für die anzeigten\n" +
        		    "Pflanzen setzen.",
        		    "Kurzanleitung",
        		    JOptionPane.PLAIN_MESSAGE);
        }
        else if ("about".equals(e.getActionCommand())){
        	JOptionPane.showMessageDialog(frame, 
        			"Programmiert von: \n" +
        			"Stephan und Johannes Rothe \n \n" +
        			"Mit freundlicher Unterstützung von: \n" +
        			"Paul Dingil \n \n" +
        			"Mit einer Library von: \n" +
        			"Kai Toedter (www.toedter.com)",
        			"About",
        			JOptionPane.PLAIN_MESSAGE);
        }
        else if ("nothing".equals(e.getActionCommand())){
        	if (controller.getPlantsSelected() != null){
				controller.getPlantsSelected().clear();
				controller.getDrawingPane().repaint();
			}
        }
        else if ("alleGeduengt".equals(e.getActionCommand())){
        	for (Iterator<Plant> iterator = controller.getPlantsSelected().iterator(); iterator.hasNext();) {
				Plant plant = (Plant) iterator.next();
				Calendar c = Calendar.getInstance();
				plant.setGeduengt(c.getTime());
			}
        }
        else if ("alleLoeschen".equals(e.getActionCommand())){
        	Object[] options = { "Ja", "Nein"};
		    int antwort=-1;
		    antwort=(JOptionPane.showOptionDialog(null, "Alle ausgewählten Pflanzen löschen?", "Warnung", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]));
		    if(antwort==0){
		    	for (Iterator<Plant> iterator = controller.getPlantsSelected().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					if (controller.getPlants().contains(plant)){
						controller.getPlants().remove(plant);
					}
				}
	        	controller.getDrawingPane().repaint();
		    }
        	
        }
        else if ("alleEingegangen".equals(e.getActionCommand())){
        	for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
				Plant plant = (Plant) iterator.next();
				if (controller.getPlantsSelected().contains(plant)){
					plant.setKaputt(true);
				}
			}
        }
        controller.getDrawingPane().repaint();
    }


	protected void addButtons(JToolBar toolBar) {
        JButton button = null;

        //öffnen button
        button = makeNavigationButton("document-open.png", "grundriss",
                                      "Öffnen",
                                      "Öffnen");
        toolBar.add(button);

        //speicher button
        button = makeNavigationButton("document-save.png", "speichern",
                                      "Speichern",
                                      "Speichern");
        toolBar.add(button);

        toolBar.addSeparator();
        //test button
        button = makeNavigationButton("zoom-in.png", "zoomIn",
                                      "Vergrößert das Bild",
                                      "Hereinzoomen");
        toolBar.add(button);
        
        button = makeNavigationButton("zoom-out.png", "zoomOut",
                "Verkleinert das Bild",
                "Herauszoomen");
        toolBar.add(button);
        toolBar.addSeparator();
    }

    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        //Look for the image.
        String imgLocation = "/images/"
                             + imageName;
        URL imageURL = MainWindow.class.getResource(imgLocation);

        //Create and initialize the button.
        JButton button = new JButton(altText);
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.setVerticalTextPosition(AbstractButton.BOTTOM);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.addActionListener(this);

        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                               + imgLocation);
        }
        return button;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainWindow.class.getResource("/images/" + path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Resource not found: " + path);
            return null;
        }
    }
    //Pflanzen um die das Recteck gezogen wurd in die Collection PlantsSelected hinzufügen
    public void addSelectedPlants(){
    	Rectangle rect = new Rectangle();
		if (controller.isFiltered() == false){
			//alle plants
			for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
				Plant plant = (Plant) iterator.next();
				rect = plant.getRectangle();
				Rectangle selectRect = new Rectangle((int)controller.convertToBasis(controller.getSelectRect().x), (int)controller.convertToBasis(controller.getSelectRect().y), (int)controller.convertToBasis(controller.getSelectRect().width), (int)controller.convertToBasis(controller.getSelectRect().height));
				if (selectRect.contains(new Point(rect.x + rect.width/2, rect.y + rect.height/2))) {
					controller.getPlantsSelected().add(plant);
					controller.getDrawingPane().repaint();
				}
			}
		} else {
			//wenn PlantsToShow(filter) aktiv ist
			for (Iterator<Plant> iterator = controller.getPlantsToShow().iterator(); iterator.hasNext();) {
				Plant plant = (Plant) iterator.next();
				rect = plant.getRectangle();
				if (controller.getSelectRect().contains(new Point((int)controller.convertToBasis(rect.x), (int)controller.convertToBasis(rect.y)))) {
					controller.getPlantsSelected().add(plant);
					controller.getDrawingPane().repaint();
				}
			}
		}
    }
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				Object[] options = { "Nein", "Ja"};
			    int antwort=-1;
			    antwort=(JOptionPane.showOptionDialog(null, "Alle Daten gespeichert und wirklich verlassen?", "Warnung", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]));
			    if(antwort==1){
			    	System.exit(0);
			    }
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
        
        //Create and set up the content pane.
        JComponent newContentPane = new MainWindow();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	public void zoomIn (){
		//Aktivere den zoomout button wieder
		toolBar.getComponent(4).setEnabled(true);
		
		Point posViewPort = scroller.getViewport().getViewPosition();
		Dimension sizeViewPort = scroller.getViewport().getSize();
		int imageWidth = controller.getImageWidth();
		int imageHeight = controller.getImageHeight();
		double zoomFactor = controller.getZoomFactor();

    	controller.setZoomstufe((controller.getZoomstufe() - 1));
    	System.out.println(controller.getZoomstufe());
		
    	double tempWidth = imageWidth;
    	double tempHeigth = imageHeight;
    	tempWidth = controller.convertFromBasis(imageWidth);
    	tempHeigth = controller.convertFromBasis(imageHeight);
    	controller.setNewImageWidth((int)tempWidth);
    	controller.setNewImageHeight((int)tempHeigth);
    	//VIEW ANPASSEN
    	Point newViewPos = new Point();
    	newViewPos.x = Math.max(0, (int)((posViewPort.x + sizeViewPort.width / 2)*zoomFactor) - sizeViewPort.width/2);
    	newViewPos.y = Math.max(0, (int)((posViewPort.y + sizeViewPort.height / 2)*zoomFactor) - sizeViewPort.height/2);
    	scroller.getViewport().setViewPosition(newViewPos);
    	drawingPane.rescale();
    	drawingPane.repaint();
    	drawingPane.revalidate();
    }
	
	public void zoomOut (){	
		Point posViewPort = scroller.getViewport().getViewPosition();
		Dimension sizeViewPort = scroller.getViewport().getSize();
		int imageWidth = controller.getImageWidth();
		int imageHeight = controller.getImageHeight();
		double zoomFactor = controller.getZoomFactor();
		
		//Begrenzung
		if (controller.getZoomstufe() != 0){
			if (controller.getNewImageHeight() <= scroller.getViewport().getHeight() || controller.getNewImageWidth() <= scroller.getViewport().getWidth()){
				controller.setZoomstufe(controller.getZoomstufe());
				toolBar.getComponent(4).setEnabled(false);
			} else {
				controller.setZoomstufe((controller.getZoomstufe() + 1));
			}
		} else {
			controller.setZoomstufe((controller.getZoomstufe() + 1));
		}
    	System.out.println(controller.getZoomstufe());
		
    	double tempWidth = imageWidth;
    	double tempHeigth = imageHeight;
    	tempWidth = controller.convertFromBasis(imageWidth);
    	tempHeigth = controller.convertFromBasis(imageHeight);
    	controller.setNewImageWidth((int)tempWidth);
    	controller.setNewImageHeight((int)tempHeigth);
    	//VIEW ANPASSEN
    	Point newViewPos = new Point();
    	newViewPos.x = Math.max(0, (int)((posViewPort.x + sizeViewPort.width / 2)/zoomFactor) - sizeViewPort.width/2);
    	newViewPos.y = Math.max(0, (int)((posViewPort.y + sizeViewPort.height / 2)/zoomFactor) - sizeViewPort.height/2);
    	scroller.getViewport().setViewPosition(newViewPos);
    	drawingPane.rescale();
    	drawingPane.repaint();
    	drawingPane.revalidate();
    }

	public void refreshInformationPanel(Plant plant){
		if (plant.getBotanischerName().equals("")){
			informationPanel.setBotanName("nicht angegeben");
		} else {
			informationPanel.setBotanName(plant.getBotanischerName());
		}
		informationPanel.setDeutschName(plant.getDeutscherName());
		if (plant.getSorte().equals("")){
			informationPanel.setSorte("nicht angegeben");
		} else {
			informationPanel.setSorte(plant.getSorte());
		}
		if (plant.getPflegehinweis().equals("")){
			informationPanel.setPflegehinweis("nicht angegeben");
		} else {
			informationPanel.setPflegehinweis(plant.getPflegehinweis());
		}
		if (plant.getBezug().equals("")){
			informationPanel.setBezug("nicht angegeben");
		} else {
			informationPanel.setBezug(plant.getBezug());
		}
		if (plant.getComment().equals("")){
			informationPanel.setComment("nicht angegeben");
		} else {
			informationPanel.setComment(plant.getComment());
		}
		if (plant.getGepflanzt() == null || controller.df.format(plant.getGepflanzt()).equals(controller.df.format(new Date(0)))){
			informationPanel.setGepflanzt("nicht angegeben");
		} else {
			informationPanel.setGepflanzt(controller.df.format(plant.getGepflanzt()));
		}
		if (plant.getGeduengt() == null || controller.df.format(plant.getGeduengt()).equals(controller.df.format(new Date(0)))){
			informationPanel.setGeduengt("nicht angegeben");
		} else {
		informationPanel.setGeduengt(controller.df.format(plant.getGeduengt()));
		}
		informationPanel.setColor(plant.getBluetenFarbe());
		informationPanel.setImagePanelPicture(plant.getImagePath());
		informationPanel.setMonatVon(plant.getMonatVon());
		informationPanel.setMonatBis(plant.getMonatBis());
	}
    public JFrame getFrame(){
    	return frame;
    }
    public JScrollPane getScroller() {
		return scroller;
	}

	public JRadioButtonMenuItem getCustomDisplay() {
		return customDisplay;
	}

	public void setCustomDisplay(JRadioButtonMenuItem customDisplay) {
		this.customDisplay = customDisplay;
	}

	public JRadioButtonMenuItem getShowAll() {
		return showAll;
	}

	public void setShowAll(JRadioButtonMenuItem showAll) {
		this.showAll = showAll;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}
	public JMenuItem getAlleEingegangen() {
		return alleEingegangen;
	}
}
