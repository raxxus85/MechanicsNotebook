package informationwindows;

import java.awt.Component;


/**
 * The DialogFactory class. Creates specific dialogs based on input.
 * @author mark.milford
 */
public class DialogFactory {
        
    public void createDialogMessage(Component incomingParent,DialogType dialogType,String message){
        
        if(dialogType ==dialogType.WARNING_MESSAGE){
            WarningDialog newWarningDialog = new WarningDialog();
            newWarningDialog.create(incomingParent,message);
  
        }else if(dialogType == dialogType.ERROR_MESSAGE){
            ErrorDialog newErrorDialog = new ErrorDialog();
            newErrorDialog.create(incomingParent,message);
        }else if(dialogType == dialogType.INFORMATION_MESSAGE){
            InformationDialog newInformationDialog = new InformationDialog();
            newInformationDialog.create(incomingParent,message);
        }
    }
    
    public boolean createConfirmMessage(Component incomingParent, String message){
        ConfirmDeleteDialog newConfirmDialog = new ConfirmDeleteDialog();
        return newConfirmDialog.createConfirmDelete(incomingParent,message);
    }
}
