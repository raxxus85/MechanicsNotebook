package customclasses;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 *
 * @author Mark
 */
public class VehicleButton extends JButton {
    //BufferedImage bufferedImage;
    Image image;
    //ImageIcon imageIcon;
    String path;
    
    public VehicleButton(ButtonActions incomingButtonActions){
        switch (incomingButtonActions) {
            case ADD:
                path = "/vehicle32x32ADD.png";
                break;
            case REMOVE: 
                path = "/vehicle32x32REMOVE.png";
                break;
            case EDIT:
                path ="/vehicle32x32EDIT.png";
                break;
        }
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {

    try {
        image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            System.out.println(e.toString());
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
