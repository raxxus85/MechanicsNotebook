/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package customclasses;

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
public class VehicleButton extends JButton {
    //BufferedImage bufferedImage;
    Image image;
    //ImageIcon imageIcon;
    String path;
    
    public VehicleButton(ButtonActions incomingButtonActions){
        switch (incomingButtonActions) {
            case ADD:
            //..statements // they are executed if variable == c1
                path = "/vehicle32x32ADD.png";
                break;
            case REMOVE: 
            //statements // they are executed if variable == c2
                path = "/vehicle32x32REMOVE.png";
                break;
            case EDIT:
                path ="/vehicle32x32EDIT.png";
                //image =getClass().getResource("/mechanicShrunk - Copy.png"))
                //imageIcon =new javax.swing.ImageIcon(getClass().getResource("/mechanicShrunk - Copy.png")); // NOI18N
                //ImageIcon icon =new javax.swing.ImageIcon(getClass().getResource("/mechanicShrunk - Copy.png")); // NOI18N
                //Image image = new javax.swing.Image(getClass().getResource("/mechanicShrunk - Copy.png"));
                break;
        }
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {

    try {
        image = ImageIO.read(getClass().getResource(path));
        //imageIcon = new javax.swing.ImageIcon(getClass().getResource(path));
        //image = new java.awt.Image(getClass().getResource(path));
        
        
        //image = ImageIO.read(getClass().getResource("/motorCycleStandAlone30x30.png"));
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
