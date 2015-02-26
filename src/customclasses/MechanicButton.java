package customclasses;

import static customclasses.ButtonActions.ADD;
import static customclasses.ButtonActions.EDIT;
import static customclasses.ButtonActions.REMOVE;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Mark
 */
public class MechanicButton extends JButton {
    //BufferedImage bufferedImage;
    Image image;
    //ImageIcon imageIcon;
    String path;
    
    public MechanicButton(ButtonActions incomingButtonActions){
        switch (incomingButtonActions) {
            case ADD:
                path = "/mechanic32x32ADD.png";
                break;
            case REMOVE: 
                path = "/mechanic32x32REMOVE.png";
                break;
            case EDIT:
                path ="/mechanic32x32EDIT.png";
                break;
        }
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {

    try {
        image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        g.drawImage(image,0,0,this);
}

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.setSize(32, 32);
        return size;
    }
}