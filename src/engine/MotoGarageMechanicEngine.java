/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import informationwindows.DialogFactory;
import informationwindows.DialogType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import objectmodels.Garage;
import objectmodels.Mechanic;
import java.util.ArrayList;
import windows.MainWindow;
import windows.NewMechanicWindow;
import windows.WelcomeWindow;

/**
 * MAIN engine for Mechanic's Notebook
 * @author Mark
 */
public class MotoGarageMechanicEngine {
    //Window Variables
    private WelcomeWindow welcomeWindow;
    private MainWindow mainWindow;
    private NewMechanicWindow newMechanicWindow;
    //Other Variables
    private Garage currentGarage;
    private DialogFactory dialogFactory;
    private Boolean saved;
    
    public MotoGarageMechanicEngine(){
        // TESTING CODE NEW TO REMOVE ONCE WE IMPLEMENT CREATE/ OPEN/ SAVE
        //Garage testGarage = new Garage();
        //this.currentGarage = testGarage;
        this.dialogFactory = new DialogFactory();
    }
    
    /**
     * @param args the command line arguments
     * MAIN PROGRAM ENTRY POINT!
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MotoGarageMechanicEngine motoGarageMechanicEngine = new MotoGarageMechanicEngine();
        // create a DEFAULT GARAGE as program just opened
        motoGarageMechanicEngine.createDefaultGarage();
        motoGarageMechanicEngine.startWelcomeWindow();
    }
    
    /**
     * Method used when creating a garage, which is just a container
     * <li> used when the program first opens, to as a "NEW" garage
     * <li> used when user hits "NEW" under FILE
     */
    public void createDefaultGarage(){
        Garage newGarage = new Garage();
        this.currentGarage = newGarage;
    }
    

    /**
     * Private method to open a Garage. Performs:
     * <li> ensures user saved their progress
     * <li> Opens a file
     * <li> loads that file as the main garage
     * 
     */
    public void openGarage(File fileToOpen){
        // DO THIS
        String pathOfFileToOpen = fileToOpen.getAbsolutePath();
        try{
            FileInputStream fileInputStream = new FileInputStream(pathOfFileToOpen);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            Garage garage = (Garage)object;
            this.currentGarage = garage;
            this.mainWindow.refresh();
            System.out.println("ATTEMPTING TO OPEN.." + pathOfFileToOpen);
            
        }catch(IOException ex){
            System.out.println("WE FAILED ATTEMPTING TO OPEN! SHIT!IO EXCEPTION UP IN HUR");
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
            System.out.println("WE FAILED ATTEMPTING TO OPEN! SHIT! CLASS NOT FOUND UP IN HUR");
            ex.printStackTrace();
        }
        
    }
    
    /**
     * Method used to SAVE the Garage and all it's contents
     * <li> VERY IMPORTANT
     * <li> must save with extension .mnb (MechanicsNoteBook)
     * <li> get rid of anything else (other .'s, etc)
     * @return true if successful
     */
    public boolean saveGarage(File fileToSaveAs){
        // time to attempt to SAVE!
        
        try{
            //FileOutputStream fileOutputStream = new FileOutputStream("C:\test");
            FileOutputStream fileOutputStream = new FileOutputStream(fileToSaveAs.getAbsolutePath() + ".mnb");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.currentGarage);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Data saved...");
        }catch(IOException ex){
            this.getDialogFactory().createDialogMessage(DialogType.ERROR_MESSAGE, "Something went terribly wrong attempting to save!");
            System.out.println("WE FAILED ATTEMPTING TO SAVE!");
            ex.printStackTrace();
        }
        return true;
    }
    
    /**
     * Private method used to start the welcome window
     * <li> used in the beginning of the program to flash neat graphics, welcome user,  etc
     */
    private void startWelcomeWindow(){
        this.welcomeWindow = new WelcomeWindow(this);
        this.welcomeWindow.setVisible(true);
    }  
    /**
     * Method called to create a new mechanic
     * <li> should we check to see if mechanic with that name exists?
     * @param incomingMechanic
     * @return true if successful? (NOT IMPLEMENTED YET)
     */
    public boolean createNewMechanic(Mechanic incomingMechanic){       
        // insert logic here to check to see if mechanic exists?
        this.getGarage().addMechanic(incomingMechanic);
        for(Mechanic object: this.currentGarage.getMechanics()){
            System.out.println(object.getFullName());
        }
        // TIME TO REFRESH
        this.mainWindow.refresh();
        return true;
    }
    
    //ACCESSORS and GETTORS
    private Garage getGarage(){
        return this.currentGarage;
    }
    

    /**
     * Method to return an array of the current mechanics by NAME (firstName + lastName)
     * @return String[] of mechanic names
     */
    public String[] getMechanicNameArray(){

        if(this.getMechanicArray() == null){
            String[] emptyArray = new String[0];
            return emptyArray;
        }
        Mechanic[] currentMechanicArray = this.getMechanicArray();
        String[] mechanicNameArray = new String[currentMechanicArray.length];
        
        //int counter = currentMechanicArray.length;
        int counter = 0;
        while(counter < currentMechanicArray.length){
            mechanicNameArray[counter] =  currentMechanicArray[counter].getFullName();
            counter++;
        }
        
        return mechanicNameArray;
    }
    
    /**
     * Method used to return
     * @return 
     */
    public Mechanic[] getMechanicArray(){
        if(this.currentGarage!= null){
            return this.currentGarage.getMechanicsArray();
        }else{
            return null;
        }
    }
    
    public DialogFactory getDialogFactory(){
        return this.dialogFactory;
    }
    
    // Window Creation Methods
    
    /**
     * Method to create the main window, essential for the program
     * <li> connection between main window and engine vital for program at this point
     */
    public void startMainWindow(){
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setVisible(true);
    }
    
    /**
     * Method to create a new mechanic window, which prompts user for new mechanic details
     */
    public void startNewMechanicWindow(){

        this.newMechanicWindow = new NewMechanicWindow(this);
        this.newMechanicWindow.setVisible(true);
        
        //this.newMechanicWindow = new NewMechanicWindow(this);
        //this.newMechanicWindow.setVisible(true);
    }
    
        
    // End Window Creation Methods
}
