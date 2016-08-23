package programm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileFilter;

    public class DrawingPane extends JPanel implements ActionListener {

    	
		private static final long serialVersionUID = 1L;
		//Variables
        Image bgImage = null;
        JPopupMenu popup = new JPopupMenu();
        JMenuItem removeItem;
        JMenuItem editItem;
        JMenuItem newItem;
        JMenuItem copyItem;
        JMenuItem pasteItem;
        JMenuItem sizeItem;
        JMenuItem moveItem;
        JMenuItem geduengtItem;
        JCheckBoxMenuItem kaputtItem;
        Controller controller;
        Filter filter = new Filter();

        

        //Constructor
        public DrawingPane(){
        	controller = Controller.getInstance();
            controller.setPlants(new ArrayList<Plant>());
            addMouseListener(GartenMouseListener.getInstance());
            addMouseMotionListener(GartenMouseListener.getInstance());
            setDoubleBuffered(true);
            createPopupMenu();
            this.setBackground(Color.white);
            controller.setDrawingPane(this);
        }
        
        protected static ImageIcon createImageIcon(String path) {
            java.net.URL imgURL = DrawingPane.class.getResource(path);
            if (imgURL != null) {
                return new ImageIcon(imgURL);
            } else {
                System.err.println("Resource not found: " + path);
                return null;
            }
        }

        public void createPopupMenu(){
        	ImageIcon newPlantIcon = createImageIcon("/images/list-add.png");
        	newItem = new JMenuItem("Neue Pflanze", newPlantIcon);
        	newItem.addActionListener(this);
        	newItem.setActionCommand("newPlant");
            popup.add(newItem);
            
            ImageIcon editPlantIcon = createImageIcon("/images/preferences-other.png");
            editItem = new JMenuItem("Bearbeiten", editPlantIcon);
            editItem.addActionListener(this);
            editItem.setActionCommand("editPlant");
            
            ImageIcon deletePlantIcon = createImageIcon("/images/edit-delete.png");
            removeItem = new JMenuItem("Löschen", deletePlantIcon);
            removeItem.addActionListener(this);
            removeItem.setActionCommand("deletePlant");
            
            ImageIcon copyPlantIcon = createImageIcon("/images/edit-copy.png");
            copyItem = new JMenuItem("Kopieren", copyPlantIcon);
            copyItem.addActionListener(this);
            copyItem.setActionCommand("copyPlant");
            
            ImageIcon pastePlantIcon = createImageIcon("/images/edit-paste.png");
            pasteItem = new JMenuItem("Pflanze einfügen", pastePlantIcon);
            pasteItem.addActionListener(this);
            pasteItem.setActionCommand("pastePlant");
            
            ImageIcon sizeIcon = createImageIcon("/images/view-fullscreen_small.png");
            sizeItem = new JMenuItem("Größe ändern", sizeIcon);
            sizeItem.addActionListener(this);
            sizeItem.setActionCommand("size");
            
            ImageIcon kaputtIcon = createImageIcon("/images/edit-clear_small.png");
            kaputtItem = new JCheckBoxMenuItem("Ist eingegangen", kaputtIcon);
            kaputtItem.setSelected(false);
            kaputtItem.addActionListener(this);
            kaputtItem.setActionCommand("kaputt");
            
            ImageIcon moveIcon = createImageIcon("/images/go-jump_small.png");
            moveItem = new JMenuItem("Verschieben", moveIcon);
            moveItem.addActionListener(this);
            moveItem.setActionCommand("move");
            
            ImageIcon geduengtIcon = createImageIcon("/images/view-calendar-day_small.png");
            geduengtItem = new JMenuItem("Heute Gedüngt", geduengtIcon);
            geduengtItem.addActionListener(this);
            geduengtItem.setActionCommand("heuteGeduengt");
            
            controller.setPopup(popup);
        } 
            
        public void save() throws IOException{
        	if (controller.getPlants().isEmpty()){
        		Object[] options = { "OK"};
			    int antwort = (JOptionPane.showOptionDialog(null, "Keine Pflanzen eingetragen, speichern nicht möglich!", "Warnung", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]));
        	} else {
        		writeFile(controller.getPlants());
        	}
        }
        
        public void open(String filePath) throws IOException, ClassNotFoundException {
            controller.setPlants(readFile(filePath));
            repaint();
        }

        private void writeFile(Collection<Plant> plants) throws IOException {
        	FileHandler fh = new FileHandler();
        	fh.writeFile(plants, controller.getFilePath());
        }
        
        private Collection<Plant> readFile(String filePath) throws IOException, ClassNotFoundException {
        	FileHandler fh = new FileHandler();
            return fh.readFile(filePath);
        }

        public void openImageAtStart(){
            try {
            	JFileChooser fc = new JFileChooser();
                FileFilter filter1 = new ExtensionFileFilter(".jpg, .jpeg, .png, .gif, .tif, .tiff", new String[] { "JPG", "JPEG", "PNG", "GIF", "TIF", "TIFF" });
                fc.setFileFilter(filter1);
                fc.setAccessory(new ImagePreview(fc));
            	fc.setAcceptAllFileFilterUsed(false);
            	
	            int returnVal = fc.showDialog(DrawingPane.this, "Grundriss Öffnen..");
	            File file = fc.getSelectedFile();
	            
	            if (JFileChooser.CANCEL_OPTION == returnVal || JFileChooser.ERROR_OPTION == returnVal){
	            	System.exit(0);
	            }
	            this.getFileImage(file.getAbsolutePath());
	            controller.setImageWidth(controller.getBgImage().getWidth(this));
	            controller.setImageHeight(controller.getBgImage().getHeight(this));
	            String filePath = file.getAbsolutePath() + "_pflanzendatenbank.txt";
	            File textFile = new File(filePath);
	            if (textFile.exists() == false){
	            	textFile.createNewFile();
	            } else {
	            	open(filePath);
	            }
	            controller.setFilePath(filePath);
	            controller.setFc(fc);
	            setPreferredSize(new Dimension(controller.getImageWidth(),controller.getImageHeight()));
            }
           catch (Exception ex) {
               System.out.println("Das Bild ging nich zu laden weil " + ex.getMessage());
           }
        }
        
        public void openImage(){
            try {
            	JFileChooser fc = new JFileChooser();
                FileFilter filter1 = new ExtensionFileFilter(".jpg, .jpeg, .png, .gif, .tif, .tiff", new String[] { "JPG", "JPEG", "PNG", "GIF", "TIF", "TIFF" });
                fc.setFileFilter(filter1);
                fc.setAccessory(new ImagePreview(fc));
            	fc.setAcceptAllFileFilterUsed(false);
            	
	            int returnVal = fc.showDialog(DrawingPane.this, "Grundriss Öffnen..");
	            File file = fc.getSelectedFile();
	            
	            if (JFileChooser.CANCEL_OPTION == returnVal || JFileChooser.ERROR_OPTION == returnVal){
	            	// TODO grundrissGeladen = false;
	            }
	            this.getFileImage(file.getAbsolutePath());
	            controller.setImageWidth(controller.getBgImage().getWidth(this));
	            controller.setImageHeight(controller.getBgImage().getHeight(this));
	            String filePath = file.getAbsolutePath() + "_pflanzendatenbank.txt";
	            File textFile = new File(filePath);
	            if (textFile.exists() == false){
	            	textFile.createNewFile();
	            	controller.getPlants().clear();
	            	repaint();
	            } else {
	            	open(filePath);
	            }
	            controller.setFilePath(filePath);
	            controller.setFc(fc);
	            setPreferredSize(new Dimension(controller.getImageWidth(),controller.getImageHeight()));
            }
           catch (Exception ex) {
               System.out.println("Das Bild ging nich zu laden weil " + ex.getMessage());
           }
        }
        
        public void getFileImage(String filePath) throws InterruptedException, IOException {
            controller.setBgImage(Toolkit.getDefaultToolkit().getImage(filePath));
            MediaTracker mt = new MediaTracker(this);
            mt.addImage(controller.getBgImage(),0);
            mt.waitForAll();
            repaint();
            revalidate();
        }
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            if (controller.getZoomstufe()!=0){
            	g.drawImage(controller.getBgImage(),0,0,controller.getNewImageWidth(),controller.getNewImageHeight(), this);
            } else {
            	g.drawImage(controller.getBgImage(),0,0,controller.getImageWidth(),controller.getImageHeight(), this);
            }
            Rectangle rect;
            if (controller.isFiltered() == false){
            	for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
            		Plant plant = (Plant) iterator.next();
            		rect = plant.getRectangle();
            		g.setColor(plant.getBluetenFarbe());
            		if (plant.isKaputt() == false){
            			g.fillRect((int)controller.convertFromBasis(rect.getX()), (int)controller.convertFromBasis(rect.getY()), (int)controller.convertFromBasis(rect.width), (int)controller.convertFromBasis(rect.height));
            			if (controller.getPlantsSelected().contains(plant) == false){
            				g.setColor(Color.black);
            				g.drawRect((int)controller.convertFromBasis(rect.getX()), (int)controller.convertFromBasis(rect.getY()), (int)controller.convertFromBasis(rect.width), (int)controller.convertFromBasis(rect.height));
            			} else {
            				//Grüne Markierung
            				g.setColor(new Color(0,255,0));
            				g.drawRect((int)controller.convertFromBasis(rect.getX()), (int)controller.convertFromBasis(rect.getY()), (int)controller.convertFromBasis(rect.width), (int)controller.convertFromBasis(rect.height));
            			}	
            		}
            	}
            	revalidate();
            } else {
            	for (Iterator<Plant> iterator = controller.getPlantsToShow().iterator(); iterator.hasNext();) {
            		Plant plant = (Plant) iterator.next();
            		rect = plant.getRectangle();
            		g.setColor(plant.getBluetenFarbe());
            		g.fillRect((int)controller.convertFromBasis(rect.getX()), (int)controller.convertFromBasis(rect.getY()), (int)controller.convertFromBasis(rect.width), (int)controller.convertFromBasis(rect.height));
            		if (controller.getPlantsSelected().contains(plant) == false){
            			g.setColor(Color.black);
            			g.drawRect((int)controller.convertFromBasis(rect.getX()), (int)controller.convertFromBasis(rect.getY()), (int)controller.convertFromBasis(rect.width), (int)controller.convertFromBasis(rect.height));
            		} else {
            			//Grüne Markierung
            			g.setColor(new Color(0,255,0));
            			g.drawRect((int)controller.convertFromBasis(rect.getX()), (int)controller.convertFromBasis(rect.getY()), (int)controller.convertFromBasis(rect.width), (int)controller.convertFromBasis(rect.height));
            		}	
            	}
            }
            //Auswahl Quadrat
            if (controller.getSelectRect() != null){
            	Rectangle selectRect = controller.getSelectRect();
            	g2d.setColor(new Color(0,0,1,0.18f));
            	g2d.fillRect(selectRect.x,selectRect.y,selectRect.width,selectRect.height);
            	g2d.setColor(new Color(0,0,1,0.8f));
            	g2d.drawRect(selectRect.x,selectRect.y,selectRect.width,selectRect.height);
            }
        } 
        public void rescale(){
        	if (controller.getZoomstufe()!=0){
        		setPreferredSize(new Dimension(controller.getNewImageWidth(),controller.getNewImageHeight()));
        	} else {
        		setPreferredSize(new Dimension(controller.getImageWidth(),controller.getImageHeight()));
        	}
        }

		public int getMouseX() {
			return controller.getMouseX();
		}

		public void setMouseX(int mouseX) {
			controller.setMouseX(mouseX);
		}

		public int getMouseY() {
			return controller.getMouseY();
		}

		public void setMouseY(int mouseY) {
			controller.setMouseY(mouseY);
		}

		public void actionPerformed(ActionEvent e) {
			if ("newPlant".equals(e.getActionCommand())){
				controller.getMw().showPlantDialog();
			}else if ("deletePlant".equals(e.getActionCommand())){
				Object[] options = { "Ja", "Nein"};
			    int antwort=-1;
			    antwort=(JOptionPane.showOptionDialog(null, "Pflanze wirklich löschen?", "Warnung", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]));
			    if(antwort==0){
			    	controller.getPlants().remove(controller.getPlantToEdit());
					controller.getDrawingPane().repaint();
			    }
				
			} else if ("editPlant".equals(e.getActionCommand())){
				Plant plantToEdit = controller.getPlantToEdit();
				System.out.println("Pflanze bearbeiten");
				new Dialog(controller.getMw().getFrame(), "Pflanze bearbeiten", true, 
						plantToEdit.getBotanischerName(), plantToEdit.getDeutscherName(), plantToEdit.getSorte(), 
						plantToEdit.getPflegehinweis(), plantToEdit.getComment(), plantToEdit.getBezug(), plantToEdit.getGepflanzt(), plantToEdit.getGeduengt(), 
						plantToEdit.getBluetenFarbe(), plantToEdit, plantToEdit.getImagePath(), plantToEdit.getMonatVon(), plantToEdit.getMonatBis());
			} else if ("copyPlant".equals(e.getActionCommand())){
				Rectangle rect = new Rectangle();
				for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					rect = plant.getRectangle();
					if (rect.contains((int)controller.convertToBasis(controller.getMouseX()), (int)controller.convertToBasis(controller.getMouseY()))) {
						controller.setPlantToCopy(plant);
					}
				}
			} else if ("pastePlant".equals(e.getActionCommand())){
				Plant insertedPlant = controller.getPlantToCopy();
				Plant plant = new Plant(insertedPlant.getBotanischerName(), insertedPlant.getDeutscherName(), insertedPlant.getSorte(), insertedPlant.getBluetenFarbe(), 
						insertedPlant.getPflegehinweis(), insertedPlant.getComment(), insertedPlant.getBezug(), insertedPlant.getGepflanzt(), insertedPlant.getGeduengt(), 
						insertedPlant.getImagePath(), new Rectangle((int)controller.convertToBasis(controller.getMouseX()), (int)controller.convertToBasis(controller.getMouseY()), insertedPlant.getRectangle().width, insertedPlant.getRectangle().height), insertedPlant.isKaputt(),
						insertedPlant.getMonatVon(), insertedPlant.getMonatBis());
				controller.getPlants().add(plant);
				controller.getDrawingPane().repaint();
			}
			else if("size".equals(e.getActionCommand())){
				Rectangle plant = controller.getPlantToResize().getRectangle();
				new DialogSize(controller.getMw().getFrame(), "Größe ändern...(in Pixel)", true, plant.width, plant.height);
			}
			else if("kaputt".equals(e.getActionCommand())){
				Rectangle rect = new Rectangle();
				for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					rect = plant.getRectangle();
					if (rect.contains((int)controller.convertToBasis(controller.getMouseX()), (int)controller.convertToBasis(controller.getMouseY()))) {
						if (plant.isKaputt() == false){
							plant.setKaputt(true);
							repaint();
						} else {
							plant.setKaputt(false);
							controller.getPlantsToShow().remove(plant);
							repaint();
						}
					}
				}
			}
			else if("move".equals(e.getActionCommand())){
				for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					Rectangle rect = plant.getRectangle();
					if (rect.contains((int)controller.convertToBasis(controller.getMouseX()), (int)controller.convertToBasis(controller.getMouseY()))) {
						controller.setPlantToMove(plant);
					}
				}
			}
			else if ("heuteGeduengt".equals(e.getActionCommand())){
				for (Iterator<Plant> iterator = controller.getPlantsSelected().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					Calendar c = Calendar.getInstance();
					plant.setGeduengt(c.getTime());
				}
			}
		}

		public void handleMouseInput(MouseEvent e) {
			
			controller.setMouseX(e.getX());
        	controller.setMouseY(e.getY());
        	
			Rectangle rect = new Rectangle();
			
			popup.remove(removeItem); 
        	popup.remove(editItem);
        	popup.remove(copyItem);
        	popup.remove(sizeItem);
        	popup.remove(kaputtItem);
        	popup.remove(moveItem);
        	popup.remove(geduengtItem);
        	popup.add(newItem);
        	if (controller.isFiltered() == true){
        		newItem.setEnabled(false);
        		newItem.setToolTipText("Neue Pflanze erstellen nur in der ungefilterten Ansicht möglich");
        	} else {
        		newItem.setEnabled(true);
        		newItem.setToolTipText("Öffnet den Dialog zum erstellen einer neuen Pflanze");
        	}
			if (controller.getPlantToCopy()!=null){
				pasteItem.setText("Pflanze einfügen ("+controller.getPlantToCopy().getDeutscherName()+")");
				popup.add(pasteItem);
			}
			if (controller.isFiltered() == false){
				for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					rect = plant.getRectangle();
					if (plant.isKaputt() == false){
						if (rect.contains(new Point((int)controller.convertToBasis(e.getX()), (int)controller.convertToBasis(e.getY())))) {
							controller.setPlantToEdit(plant);
							controller.setPlantToResize(plant);
							popup.add(geduengtItem);
							popup.add(editItem);
							popup.add(copyItem);
							popup.add(moveItem);
							popup.add(sizeItem);
							if (plant.isKaputt()){
								kaputtItem.setSelected(true);
							} else {
								kaputtItem.setSelected(false);
							}
							popup.add(kaputtItem);
							popup.add(removeItem);
							popup.remove(newItem);
							popup.remove(pasteItem);
							break;
						}
					}
				}
			}else {
				for (Iterator<Plant> iterator = controller.getPlantsToShow().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					rect = plant.getRectangle();
					if (rect.contains(new Point((int)controller.convertToBasis(e.getX()), (int)controller.convertToBasis(e.getY())))) {
						controller.setPlantToEdit(plant);
						controller.setPlantToResize(plant);
						popup.add(geduengtItem);
						popup.add(editItem);
						popup.add(copyItem);
						popup.add(moveItem);
						popup.add(sizeItem);
						if (plant.isKaputt()){
							kaputtItem.setSelected(true);
						} else {
							kaputtItem.setSelected(false);
						}
						popup.add(kaputtItem);
						popup.add(removeItem);
						popup.remove(newItem);
						popup.remove(pasteItem);
						break;
					}
				}
			}
			popup.show(e.getComponent(), e.getX(), e.getY());
        	popup.pack();
        	repaint();
        	revalidate();
		}
}