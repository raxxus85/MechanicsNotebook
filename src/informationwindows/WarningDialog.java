package informationwindows;

import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author mark.milford
 */
public class WarningDialog implements DialogInterface{
    
    /**
     * //Method to create a warning dialog box!
     * 
     * @param message 
     */
    public void create(Component incomingParent,String message){
        JOptionPane jOptionPane = new JOptionPane(message,JOptionPane.WARNING_MESSAGE);
        JDialog dialog = jOptionPane.createDialog("Warning!");
        dialog.setAlwaysOnTop(true);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/motoLogIcon16x16.png")));
        //dialog.setLocationRelativeTo(incomingParent);
        dialog.setLocationRelativeTo(incomingParent);
        dialog.setVisible(true);
        
    }
}
