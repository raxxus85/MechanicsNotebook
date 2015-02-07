package informationwindows;

import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author mark.milford
 */
public class ErrorDialog implements DialogInterface{
    
    /**
     * //Method to create an error dialog box!
     * 
     * @param message 
     */
    public void create(String message){
        JOptionPane jOptionPane = new JOptionPane(message,JOptionPane.ERROR_MESSAGE);
        JDialog dialog = jOptionPane.createDialog("Error");
        dialog.setAlwaysOnTop(true);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mechanicIcon.png")));
        dialog.setVisible(true);
    }
}
