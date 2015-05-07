/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowListener;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mark
 */
public abstract class BaseJDialog extends javax.swing.JDialog{
    
    
    
    private void setIcon(){
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mechanicIcon.png")));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/MGFavicon.png")));
    }
}
