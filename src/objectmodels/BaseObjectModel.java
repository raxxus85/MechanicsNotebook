/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectmodels;

import java.util.ArrayList;

/**
 * Abstract class to implement attachments across object models and other pertinent information
 * @author Mark
 */
public abstract class BaseObjectModel {
    private ArrayList<Attachment> attachements = new ArrayList<>();

    public void addAttachment(Attachment incomingAttachment){
        this.attachements.add(incomingAttachment);
    }
    
    public ArrayList<Attachment> getAttachments(){
        return this.attachements;
    }
    
    public Attachment[] getAttachmentArray(){
        ArrayList<Attachment> attachments = this.attachements;
        Attachment[] attachmentArray = attachments.toArray(new Attachment[attachments.size()]);
        return attachmentArray;
    }
}
