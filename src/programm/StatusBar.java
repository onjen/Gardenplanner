package programm;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;



public class StatusBar extends JLabel implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8758138003029669038L;
	String oldMessage;

	/** Creates a new instance of StatusBar */
    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 16));
        setMessage("Aktuelle Ansicht: Alle Pflanzen werden angezeigt");
    }

    public void setMessage(String message) {
        setText(" "+message);
    }
    public void setMessageAndClear(String message)
	{
    	
    	if (this.getText() != null) {
    		oldMessage = this.getText();
    	}
		setMessage(message);

		Timer tempTimer = new Timer(0, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if(isShowing())
					setMessage(oldMessage);
			}
		});

		tempTimer.setInitialDelay(3000);
		tempTimer.setRepeats(false);
		tempTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
