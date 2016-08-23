package programm;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

public class GartenMouseListener implements MouseListener, MouseMotionListener {

	private Controller controller = Controller.getInstance();
	private Point pp = new Point();
	private Point start;

	
	private static GartenMouseListener instance = null;
	public static GartenMouseListener getInstance() {
		if (instance == null) {
			instance = new GartenMouseListener();
		}
		return instance;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		//an und abwählen mit shift
		if (SwingUtilities.isLeftMouseButton(e) && e.isShiftDown()){
    		//Seitenleiste Refreshen und grüner Rahmen um aktive pflanze(durchs Repaint)
			Rectangle recta = new Rectangle();
			if (controller.isFiltered() == false){
				//alle plants
				for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					recta = plant.getRectangle();
					if (recta.contains(new Point((int)controller.convertToBasis(e.getX()), (int)controller.convertToBasis(e.getY())))) {
						if (controller.getPlantsSelected().contains(plant)){
							controller.getPlantsSelected().remove(plant);
						} else {
							controller.getPlantsSelected().add(plant);
						}
						controller.getDrawingPane().repaint();
					}
				}
			} else {
				//wenn PlantsToShow aktiv ist
				for (Iterator<Plant> iterator = controller.getPlantsToShow().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					recta = plant.getRectangle();
					if (recta.contains(new Point((int)controller.convertToBasis(e.getX()), (int)controller.convertToBasis(e.getY())))) {
						if (controller.getPlantsSelected().contains(plant)){
							controller.getPlantsSelected().remove(plant);
						} else {
							controller.getPlantsSelected().add(plant);
						}
						controller.getDrawingPane().repaint();
					}
				}
			}
		}
		//für Maus Drag bewegung
		JComponent jc = (JComponent)e.getSource();
		if (SwingUtilities.isMiddleMouseButton(e)){
        	Container c = jc.getParent();
        	if(c instanceof JViewport) {
        		controller.getMw().getScroller().getViewport().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            	JViewport vport = (JViewport)c;
            	Point cp = SwingUtilities.convertPoint(jc,e.getPoint(),vport);
            	pp.setLocation(cp);
        	}
		}
    	if (SwingUtilities.isLeftMouseButton(e) && e.isShiftDown() == false){
    		start = e.getPoint();
    		if (controller.getPlantsSelected() != null){
				controller.getPlantsSelected().clear();
			}
    		//Seitenleiste Refreshen und grüner Rahmen um aktive pflanze(durchs Repaint)
			Rectangle recta = new Rectangle();
			if (controller.isFiltered() == false){
				//alle plants
				for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					recta = plant.getRectangle();
					if (recta.contains(new Point((int)controller.convertToBasis(e.getX()), (int)controller.convertToBasis(e.getY())))) {
						controller.getMw().refreshInformationPanel(plant);
						if (controller.getPlantsSelected() != null){
							controller.getPlantsSelected().clear();
						}
						controller.getPlantsSelected().add(plant);
						controller.getDrawingPane().repaint();
					}
				}
			} else {
				//wenn PlantsToShow aktiv ist
				for (Iterator<Plant> iterator = controller.getPlantsToShow().iterator(); iterator.hasNext();) {
					Plant plant = (Plant) iterator.next();
					recta = plant.getRectangle();
					if (recta.contains(new Point((int)controller.convertToBasis(e.getX()), (int)controller.convertToBasis(e.getY())))) {
						controller.getMw().refreshInformationPanel(plant);
						if (controller.getPlantsSelected() != null){
							controller.getPlantsSelected().clear();
						}
						controller.getPlantsSelected().add(plant);
						controller.getDrawingPane().repaint();
					}
				}
			}
	    }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e) && e.isShiftDown() == false){
			controller.setPlantToMove(null);
			//damit das auswahl rechteck verschwindet und Methode aufrufen die die Pflanzen hinzufügt
			if (controller.getSelectRect() != null){
				controller.getMw().addSelectedPlants();
			} 
			controller.setSelectRect(null);
			controller.getDrawingPane().repaint();
		}
		
		
		if (SwingUtilities.isRightMouseButton(e)) {
			controller.getDrawingPane().handleMouseInput(e);
		} else if (SwingUtilities.isMiddleMouseButton(e)){
			controller.getMw().getScroller().getViewport().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		//Quadrat aufziehen
		if (SwingUtilities.isLeftMouseButton(e) && e.isShiftDown() == false){
			int width = this.start.x - e.getX();
	        int height = this.start.y - e.getY();

	        int w = Math.abs( width );
	        int h = Math.abs( height );
	        int x = width < 0 ? this.start.x : e.getX();
	        int y = height < 0 ? this.start.y : e.getY();
	        controller.setSelectRect(new Rectangle(x,y,w,h));
	        controller.getDrawingPane().repaint();
		}
		//Maus Drag Bewegung
		if (SwingUtilities.isMiddleMouseButton(e)){
			final JComponent jc = (JComponent)e.getSource();
			Container c = jc.getParent();
			if(c instanceof JViewport) {
				JViewport vport = (JViewport)c;
				Point cp = SwingUtilities.convertPoint(jc,e.getPoint(),vport);
				Point vp = vport.getViewPosition();
				vp.translate(pp.x-cp.x, pp.y-cp.y);
				jc.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
				pp.setLocation(cp);
			}
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if (controller.getPlantToMove() != null){
			//Pflanze bewegen
			controller.getPlantToMove().getRectangle().x = (int)controller.convertToBasis(e.getX()) - controller.getPlantToMove().getRectangle().width/2;
			controller.getPlantToMove().getRectangle().y = (int)controller.convertToBasis(e.getY()) - controller.getPlantToMove().getRectangle().height/2;
			controller.getDrawingPane().repaint();
		}
		Rectangle rect = new Rectangle();
		JViewport vport = controller.getMw().getScroller().getViewport();
		vport.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		//alle plants
		if (controller.isFiltered() == false){
			for (Iterator<Plant> iterator = controller.getPlants().iterator(); iterator.hasNext();) {
				Plant plant = (Plant) iterator.next();
				rect = plant.getRectangle();
				if (plant.isKaputt() == false){
					if (rect.contains(new Point((int)controller.convertToBasis(e.getX()), (int)controller.convertToBasis(e.getY())))) {
						vport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					}
				}
			}
		} else{
			//PlantsToShow
			for (Iterator<Plant> iterator = controller.getPlantsToShow().iterator(); iterator.hasNext();) {
				Plant plant = (Plant) iterator.next();
				rect = plant.getRectangle();
				if (rect.contains(new Point((int)controller.convertToBasis(e.getX()), (int)controller.convertToBasis(e.getY())))) {
					vport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
			}
		}
	}
}
