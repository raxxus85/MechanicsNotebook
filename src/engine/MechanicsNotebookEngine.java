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
import objectmodels.Customer;
import objectmodels.MaintenanceAction;
import objectmodels.MaintenanceType;
import objectmodels.Vehicle;
import windows.AboutWindow;
import windows.DeleteCustomerWindow;
import windows.DeleteMechanicWindow;
import windows.MainWindow;
import windows.MaintenanceActionWindow;
import windows.NewCustomerWindow;
import windows.NewMaintenanceActionWindow;
import windows.NewMaintenanceTypeWindow;
import windows.MechanicWindow;
import windows.NewVehicleWindow;
import windows.UpdateMileageWindow;
import windows.WelcomeWindow;

/**
 * MAIN engine for Mechanic's Notebook
 * @author Mark
 */
public class MechanicsNotebookEngine {
    
    //Window Variables
    private WelcomeWindow welcomeWindow;
    private MainWindow mainWindow;
    private MechanicWindow mechanicWindow;
    private NewCustomerWindow newCustomerWindow;
    private AboutWindow aboutWindow;
    private NewVehicleWindow newVehicleWindow;
    private NewMaintenanceTypeWindow newMaintenanceTypeWindow;
    private NewMaintenanceActionWindow newMaintenenaceActionWindow;
    private UpdateMileageWindow updateMileageWindow;
    private DeleteMechanicWindow deleteMechanicWindow;
    private MaintenanceActionWindow maintenanceActionWindow;
    private DeleteCustomerWindow deleteCustomerWindow;

    //Other Variables
    private Garage currentGarage;
    private DialogFactory dialogFactory;
    private Boolean saved;
    
    public MechanicsNotebookEngine(){
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
        MechanicsNotebookEngine motoGarageMechanicEngine = new MechanicsNotebookEngine();
        // create a DEFAULT GARAGE as program just opened
        motoGarageMechanicEngine.createDefaultGarage();
        //add some maintenance TYPES
        MaintenanceType oilChange = new MaintenanceType("Oil Change", 3000,"Simple Oil Change");
        MaintenanceType rotateTires = new MaintenanceType("Tire Rotation", 5000,"Rotating the tires (LF-> LR, LR->LF, etc)");
        motoGarageMechanicEngine.addMaintenanceType(oilChange);
        motoGarageMechanicEngine.addMaintenanceType(rotateTires);
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
            
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
            this.dialogFactory.createDialogMessage(DialogType.ERROR_MESSAGE, "We encountered a critical error attempting to open that file!" );
        }catch(IOException ex){
            ex.printStackTrace();
            this.dialogFactory.createDialogMessage(DialogType.ERROR_MESSAGE, "We encountered a critical error attempting to open that file! Version mismatch!");
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
     * Method called to create a new mechanic
     * <li> should we check to see if mechanic with that name exists!
     * @param incomingMechanic
     * @return true if successful? (NOT IMPLEMENTED YET)
     */
    public boolean createNewMechanic(Mechanic incomingMechanic){       
        //TODO insert logic here to check to see if mechanic exists?
        this.getGarage().addMechanic(incomingMechanic);
        // TIME TO REFRESH
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean updateMechanic(Mechanic incomingMechanic){
        System.out.println(this.getGarage().getCurrentMechanic().toString());
        this.getGarage().updateCurrentMechanic(incomingMechanic);
        System.out.println(this.getGarage().getCurrentMechanic().toString());

        // TIME TO REFRESH
        this.mainWindow.refresh();
        return true;
    }
    
    /**
     * Method to delete the current Mechanic
     * @return true if successful
     */
    public boolean deleteCurrentMechanic(){
        if(this.getCurrentMechanic()!=null){
            Mechanic mechanicToDelete = this.getCurrentMechanic();
            this.getGarage().deleteMechanic(mechanicToDelete);
            this.setCurrentMechanic(null);
        // implement checks?
            this.mainWindow.refresh();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean deleteCurrentCustomer(){
        if(this.getCurrentCustomer()!=null){
            Customer customerToDelete = this.getCurrentCustomer();
            this.getGarage().deleteCustomer(customerToDelete);
            this.setCurrentCustomer(null);
            // TESTING
            this.setCurrentVehicle(null);
            this.mainWindow.refresh();
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Method called to create a new Maintenance Type
     * @param incomingMaintenanceType
     * @return true if successful (not implemented YET!)
     */
    public boolean createNewMaintenanceType(MaintenanceType incomingMaintenanceType){
        this.currentGarage.addMaintenanceType(incomingMaintenanceType);
        // TIME TO REFRESH
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean editMaintenanceAction(MaintenanceAction existingMaintenanceAction, MaintenanceAction newMaintenanceAction){
        this.getCurrentVehicle().editMaintenanceAction(existingMaintenanceAction, newMaintenanceAction); 
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean addMaintenanceAction(MaintenanceAction incomingMaintenanceAction){
        this.currentGarage.getCurrentVehicle().addMaintenanceAction(incomingMaintenanceAction);
        // TIME TO REFRESH
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean updateVehicleMileage(Integer incomingMileage){
        this.currentGarage.getCurrentVehicle().updateMileage(incomingMileage);
        this.mainWindow.refresh();
        return true;
    }
    
    /**
     * Method called to create a new customer
     * <li> should check to see if customer with same name exists!
     * @param incomingCustomer
     * @return true if successful (not implemented YET!)
     */
    public boolean createNewCustomer(Customer incomingCustomer){
        //TODO insert logic here to check to see if customer already exists?
        this.getGarage().addCustomer(incomingCustomer);
        // TIME TO REFRESH
        this.mainWindow.refresh();
        return true;
    }
    
    /**
     * Method used to create a new Vehicle, picks current Customer by default
     * @param incomingVehicle
     * @return true if added, false if not (typically when there is no current customer)
     */
    public boolean createNewVehicle(Vehicle incomingVehicle){
        // 
        boolean vehicleAdded = this.getGarage().addVehicle(incomingVehicle);
        this.mainWindow.refresh();
        return vehicleAdded;
    }
    
    //ACCESSORS and GETTORS
    
    public MaintenanceType[] getMaintenaceTypeArray(){
        return this.currentGarage.getMaintenaceTypeArray();
    }
    
    public void addMaintenanceType(MaintenanceType incomingMaintenanceType){
        this.currentGarage.addMaintenanceType(incomingMaintenanceType);
    }
    
    private Garage getGarage(){
        return this.currentGarage;
    }
    
    /**
     * Method used to return mechanic array, used for JComboBox
     * @return 
     */
    public Mechanic[] getMechanicArray(){
            return this.currentGarage.getMechanicArray();
    }
    
    /**
     * Method used to return Customer array, used for JComboBox
     * @return 
     */
    public Customer[] getCustomerArray(){
        return this.currentGarage.getCustomerArray();
    }
    
    /**
     * Method to get Vehicle Array, used for JComboBox
     * <li> Will get the Vehicle Array for the "current" Customer
     * @return Vehicle[] from the current Customer
     */
    public Vehicle[] getVehicleArray(){
        return this.currentGarage.getVehicleArray();
    }
    
    /**
     * Method used to return customers
     */
    public ArrayList<Customer> getCustomers(){
        return this.currentGarage.getCustomers();
    }
    
    
    public DialogFactory getDialogFactory(){
        return this.dialogFactory;
    }
    
    // Window Creation Methods
    
    public void startDeleteMechanicWindow(){
        this.deleteMechanicWindow = new DeleteMechanicWindow(this);
        this.deleteMechanicWindow.setVisible(true);
    }
    
    public void startDeleteCustomerWindow(){
        this.deleteCustomerWindow = new DeleteCustomerWindow(this);
        this.deleteCustomerWindow.setVisible(true);
    }
    
    public void startNewUpdateMileageWindow(){
        this.updateMileageWindow = new UpdateMileageWindow(this);
        this.updateMileageWindow.setVisible(true);
    }
    
    /**
     * Method to create the main window, essential for the program
     * <li> connection between main window and engine vital for program at this point
     */
    public void startMainWindow(){
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setVisible(true);
        this.mainWindow.refresh();
    }
    
    /**
     * Method to create a new mechanic window, which prompts user for new mechanic details
     */
    public void startNewMechanicWindow(){
        this.mechanicWindow = new MechanicWindow(this);
        this.mechanicWindow.setVisible(true);
    }
    
    public void startUpdateMechanicWindow(Mechanic incomingMechanic){
        this.mechanicWindow = new MechanicWindow(this, incomingMechanic);
        this.mechanicWindow.setVisible(true);
    }
    
    /**
     * Method to create a new Customer Window, which prompts user for new Customer details
     */
    public void startNewCustomerWindow(){
        this.newCustomerWindow = new NewCustomerWindow(this);
        this.newCustomerWindow.setVisible(true);
    }
    
    /**
     * Method to create a new Vehicle Window, which prompts the user for new Vehicle details
     */
    public void startNewVehicleWindow(){
        this.newVehicleWindow = new NewVehicleWindow(this);
        this.newVehicleWindow.setVisible(true);       
    }
        
     /**
     * Private method used to start the welcome window
     * <li> used in the beginning of the program to flash neat graphics, welcome user,  etc
     */
    private void startWelcomeWindow(){
        this.welcomeWindow = new WelcomeWindow(this);
        this.welcomeWindow.setVisible(true);
    }  
    
    public void startAboutWindow(){
        this.aboutWindow = new AboutWindow();
        this.aboutWindow.setVisible(true);
    }
    
    public void startNewMaintenanceTypeWindow(){
        this.newMaintenanceTypeWindow = new NewMaintenanceTypeWindow(this);
        this.newMaintenanceTypeWindow.setVisible(true);
    }
    
    public void startNewMaintenanceActionWindow(){
        this.newMaintenenaceActionWindow = new NewMaintenanceActionWindow(this);
        this.newMaintenenaceActionWindow.setVisible(true);
    }
    
    public void startMaintenanceActionWindow(MaintenanceAction incomingMaintenanceAction){
        this.maintenanceActionWindow = new MaintenanceActionWindow(this, incomingMaintenanceAction);
        this.maintenanceActionWindow.setVisible(true);
    }
    // End Window Creation Methods
    
    /**
     * Method used to set the current Mechanic
     * @param incomingMechanic 
     */
    public void setCurrentMechanic(Mechanic incomingMechanic){
        this.currentGarage.setCurrentMechanic(incomingMechanic);
    }
    
    /**
     * Method used to return the current Mechanic
     * @return 
     */
    public Mechanic getCurrentMechanic(){
        return this.currentGarage.getCurrentMechanic();
    }
    
    /**
     * Method to set the current Customer
     * @param incomingCustomer 
     */
    public void setCurrentCustomer(Customer incomingCustomer){
        this.currentGarage.setCurrentCustomer(incomingCustomer);
    }
    
    /**
     * Method to get the current Customer
     * @return current Customer
     */
    public Customer getCurrentCustomer(){
        return this.currentGarage.getCurrentCustomer();
    }
    
    public void setCurrentVehicle(Vehicle incomingVehicle){
        this.currentGarage.setCurrentVehicle(incomingVehicle);
    }
    
    public Vehicle getCurrentVehicle(){
        return this.currentGarage.getCurrentVehicle();
    }
}
