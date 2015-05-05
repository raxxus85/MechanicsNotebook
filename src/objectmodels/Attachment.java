/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import javax.swing.ImageIcon;

/**
 * This class is used for attachments
 * Can be for Mechanics, Customers, Vehicles, Maintenance Actions, etc etc
 * @author Mark
 */
public class Attachment {
    private String attachmentName;
    private ImageIcon image;
    
    public Attachment(String incomingAttachmentName, ImageIcon incomingImageIcon){
        this.attachmentName = incomingAttachmentName;
        this.image = incomingImageIcon;
    }
    
    /**
     * Method used for the sole purpose of filling out a JTable in MainWindow
     * @return 
     */
    public Object[] getAttachmentObject(){
        String[] stringArray = {this.attachmentName};
        return stringArray;
    }
    
    public void setImageIcon(ImageIcon incomingImageIcon){
        this.image = incomingImageIcon;
    }
    
    public ImageIcon getImageIcon(){
        return this.image;
    }
    
    
    public void setAttachmentName(String incomingAttachmentName){
        this.attachmentName = incomingAttachmentName;
    }
    
    public String getAttachmentName(){
        return this.attachmentName;
    }
}
