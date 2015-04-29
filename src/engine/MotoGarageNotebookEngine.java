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
import java.net.URISyntaxException;
import objectmodels.Garage;
import objectmodels.Mechanic;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectmodels.Customer;
import objectmodels.DragStripSlip;
import objectmodels.FuelEntry;
import objectmodels.MaintenanceAction;
import objectmodels.MaintenanceType;
import objectmodels.Modification;
import objectmodels.Vehicle;
import objectmodels.Warranty;
import org.parse4j.ParseException;
import org.parse4j.ParseUser;
import parse.ParseEngine;
import parse.TestClass;
import windows.AboutWindow;
import windows.CloudUserLoginCreationWindow;
//import windows.MainWindow;
//import windows.MainWindow;
import windows.MainWindow;
import windows.MaintenanceActionWindow;
import windows.CustomerWindow;
import windows.DragStripSlipWindow;
import windows.FuelEntryWindow;
import windows.NewMaintenanceActionWindow;
import windows.MaintenanceTypeWindow;
import windows.MaintenanceTypesMainWindow;
import windows.MechanicWindow;
import windows.ModificationWindow;
import windows.VehicleWindow;
import windows.UpdateMileageWindow;
import windows.VehicleInformationGraphs;
import windows.VehicleTrackersWindow;
import windows.WarrantyWindow;
import windows.WelcomeWindow;

/**
 * MAIN engine for Mechanic's Notebook
 * @author Mark
 */
public class MotoGarageNotebookEngine {
    
    //Window Variables
    private WelcomeWindow welcomeWindow;
    private MainWindow mainWindow;
    private MechanicWindow mechanicWindow;
    private CustomerWindow customerWindow;
    private AboutWindow aboutWindow;
    private VehicleWindow vehicleWindow;
    private MaintenanceTypeWindow maintenanceTypeWindow;
    private NewMaintenanceActionWindow newMaintenenaceActionWindow;
    private UpdateMileageWindow updateMileageWindow;
    private MaintenanceActionWindow maintenanceActionWindow;
    private MaintenanceTypesMainWindow maintenanceTypesMainWindow;
    private FuelEntryWindow fuelEntryWindow;
    private WarrantyWindow warrantyWindow;
    private ModificationWindow modificationWindow;
    private VehicleTrackersWindow vehicleTrackersWindow;
    private DragStripSlipWindow dragStripSlipWindow;
    private VehicleInformationGraphs vehicleInformationGraphs;
    private CloudUserLoginCreationWindow cloudUserLoginCreationWindow;
    
    //Other Variables
    private Garage currentGarage;
    private DialogFactory dialogFactory;
    private Boolean saved;
    
    // Parse.com variables
    ParseEngine parseEngine = new ParseEngine(this);
    Boolean cloudEnabled = false;
    ParseUser currentParseUser = null;
    
    public MotoGarageNotebookEngine(){
        // TESTING CODE NEW TO REMOVE ONCE WE IMPLEMENT CREATE/ OPEN/ SAVE
        //Garage testGarage = new Garage();
        //this.currentGarage = testGarage;
        this.dialogFactory = new DialogFactory();
    }
    
    /**
     * @param args the command line arguments
     * MAIN PROGRAM ENTRY POINT!
     */
    public static void main(String[] args) throws ParseException {
        //TESTING
        //TestClass testClass = new TestClass();
        //testClass.testRun();
        
        //TESTING
        // TODO code application logic here
        MotoGarageNotebookEngine mechanicsNotebookEngine = new MotoGarageNotebookEngine();
        // create a DEFAULT GARAGE as program just opened
        mechanicsNotebookEngine.createDefaultGarage();
        //add some maintenance TYPES
        MaintenanceType oilChange = new MaintenanceType("Oil Change", 3000,"Simple Oil Change");
        MaintenanceType rotateTires = new MaintenanceType("Tire Rotation", 5000,"Rotating the tires (LF-> LR, LR->LF, etc)");
        mechanicsNotebookEngine.addMaintenanceType(oilChange);
        mechanicsNotebookEngine.addMaintenanceType(rotateTires);

        mechanicsNotebookEngine.startWelcomeWindow();
        try{
            mechanicsNotebookEngine.startMainWindow();
        }catch(Exception e){
            mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.ERROR_MESSAGE, "Something horrible happened! " + e.toString());
            mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, e.toString());
            mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, e.getMessage());
            
        }
 
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
    
    public void setCurrentParseUser(ParseUser incomingParseUser){
        this.currentParseUser = incomingParseUser;
    }
    
    public ParseUser getCurrentParseUser(){
        return this.currentParseUser;
    }
    
    public ParseUser signUpUser(String username, String password){
        ParseUser newUser = this.parseEngine.signUpUser(username, password);
        this.currentParseUser = newUser;
        return newUser;
    }
    
    public ParseUser signInUser(String username, String password){
        ParseUser signedInUser = this.parseEngine.signInUser(username, password);
        this.currentParseUser = signedInUser;
        return signedInUser;
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
            // do this step to ensure vehicle trackers get displayed
            this.currentGarage.setVehicleTrackersChanged(true);
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
        String trimmedFilePath = "";
            if(fileToSaveAs.getAbsolutePath().contains(".mnb")){
                //trimmedFilePath = fileToSaveAs.getAbsolutePath();
                trimmedFilePath = fileToSaveAs.getAbsolutePath().substring(0,(fileToSaveAs.getAbsolutePath().indexOf(".mnb")));
                trimmedFilePath = trimmedFilePath + ".mnb";
            }else{
                trimmedFilePath = fileToSaveAs.getAbsolutePath() + ".mnb";
            }

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(trimmedFilePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.currentGarage);
            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Data saved to ...");
            System.out.println(trimmedFilePath);
        }catch(IOException ex){
            this.getDialogFactory().createDialogMessage(DialogType.ERROR_MESSAGE, "Something went terribly wrong attempting to save!");
            System.out.println("WE FAILED ATTEMPTING TO SAVE!");
            ex.printStackTrace();
        }
        return true;
    }
    
    public String getOdoImagePath(int incomingIndex){
        String path = "";
        switch(incomingIndex){
            case 0:
                path ="/zeroOdometer.png";
                break;
            case 1:
                path ="/oneOdometer.png";
                break;
            case 2:
                path ="/twoOdometer.png";
                break;
            case 3:
                path ="/threeOdometer.png";
                break;
            case 4:
                path ="/fourOdometer.png";
                break;    
            case 5:
                path ="/fiveOdometer.png";
                break;    
            case 6:
                path ="/sixOdometer.png";
                break;
            case 7:
                path ="/sevenOdometer.png";
                break;
            case 8:
                path ="/eightOdometer.png";
                break;
            case 9:
                path ="/nineOdometer.png";
                break;  
  
        }
        return path;
    }
    
    public void setVehicleTrackersChanged(Boolean incomingBoolean){
        this.currentGarage.setVehicleTrackersChanged(incomingBoolean);
    }
    
    public Boolean getVehicleTrackersChanged(){
        return this.currentGarage.getVehicleTrackersChanged();
    }
    
    public void setFuelEntriesEnabled(Boolean incomingBoolean){
        this.currentGarage.setFuelEntriesEnabled(incomingBoolean); 
        this.mainWindow.refresh();
    }
    
    public Boolean getFuelEntriesEnabled(){
        return this.currentGarage.getFuelEntriesEnabled();
    }
    
    public void setWarrantiesEnabled(Boolean incomingBoolean){
        this.currentGarage.setWarrantiesEnabled(incomingBoolean);
        this.mainWindow.refresh();
    }
    
    public Boolean getWarrantiesEnabled(){
        return this.currentGarage.getWarrantiesEnabled();
    }
    
    public void setModificationsEnabled(Boolean incomingBoolean){
        this.currentGarage.setModificationsEnabled(incomingBoolean);
        this.mainWindow.refresh();
    }
    
    public Boolean getModificationsEnabled(){
        return this.currentGarage.getModificationsEnabled();
    }
    
    public void setDragStripSlipsEnabled(Boolean incomingBoolean){
        this.currentGarage.setDragStripSlipsEnabled(incomingBoolean);
        this.mainWindow.refresh();
    }
    
    public Boolean getDragStripSlipsEnabled(){
        return this.currentGarage.getDragStripSlipsEnabled();
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
        
        // time to create the corresponding customer
        Customer newCustomer = new Customer(incomingMechanic.getFirstName(),incomingMechanic.getMiddleName(),incomingMechanic.getLastName());
        if(incomingMechanic.getImageIcon()!=null){
            newCustomer.setImageIcon(incomingMechanic.getImageIcon());
        }
        this.getGarage().addCustomer(newCustomer);
        // TIME TO REFRESH
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean updateMechanic(Mechanic incomingMechanic){
        this.getGarage().updateCurrentMechanic(incomingMechanic);
        // TIME TO REFRESH
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean updateVehicle(Vehicle incomingVehicle){
        this.getGarage().updateCurrentVehicle(incomingVehicle);
        this.mainWindow.refresh();
        return true;
    }
    
     
    
    public boolean updateCustomer(Customer incomingCustomer){
        this.getGarage().updateCurrentCustomer(incomingCustomer);
        this.mainWindow.refresh();
        return true;
    }
    
    /**
     * Method used to update a Maintenance Type
     * <li> different than Mech/Vech/Action as there is no "current" concept
     * @param originalMaintenanceType
     * @param updatedMaintenanceType
     * @return true if successful
     */
    public boolean updateMaintenanceType(MaintenanceType originalMaintenanceType, MaintenanceType updatedMaintenanceType){
        boolean updatedType =this.getGarage().updateMaintenanceType(originalMaintenanceType, updatedMaintenanceType);
        this.mainWindow.refresh();
        return updatedType;
    }
    
    public boolean editWarranty(Warranty originalWarranty, Warranty updatedWarranty){
        this.getGarage().getCurrentVehicle().editWarranty(originalWarranty, updatedWarranty);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean addWarranty(Warranty incomingWarranty){
        this.getGarage().getCurrentVehicle().addWarranty(incomingWarranty);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean addDragStripSlip(DragStripSlip incomingDragStripSlip){
        this.getGarage().getCurrentVehicle().addDragStripSlip(incomingDragStripSlip);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean editDragStripSlip(DragStripSlip originalDragStripSlip, DragStripSlip updatedDragStripSlip){
        this.getGarage().getCurrentVehicle().editDragStripSlip(originalDragStripSlip, updatedDragStripSlip);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean deleteDragStripSlip(DragStripSlip incomingDragStripSlip){
        this.getGarage().getCurrentVehicle().deleteDragStripSlip(incomingDragStripSlip);
        this.mainWindow.refresh();
        return true;
    }
    
    /**
     * Method to delete the current Mechanic
     * @return true if successful
     */
    public void deleteCurrentMechanic(){
        if(this.getCurrentMechanic()== null){
            this.getDialogFactory().createDialogMessage(DialogType.ERROR_MESSAGE, "Engine is attempting to delete current Mechanic, when there is none! Error!");
            return;
        }    
        this.getGarage().deleteCurrentMechanic();
        this.setCurrentMechanic(null);
        this.mainWindow.refresh();
    }
    
    /**
     * Method used to delete the current Customer
     * <li> should attempt to delete current Vehicle (throwing error if non current!)
     */
    public void deleteCurrentCustomer(){
        if(this.getCurrentCustomer()== null){
            this.getDialogFactory().createDialogMessage(DialogType.ERROR_MESSAGE, "Engine is attempting to delete current Customer, when there is none! Error!");
            return;
        }    
        this.getGarage().deleteCurrentCustomer();
        this.setCurrentCustomer(null);
        this.mainWindow.refresh();
    }
    
    /**
     * Method used to delete the current Vehicle
     * <li> should attempt to delete current Vehicle (throwing error if none current!)
     */
    public void deleteCurrentVehicle(){
        if(this.getCurrentVehicle()== null){
            this.getDialogFactory().createDialogMessage(DialogType.ERROR_MESSAGE, "Engine is attempting to delete current Vehicle, when there is none! Error!");
            return;
        }       
        this.getGarage().deleteCurrentVehicle();
        this.setCurrentVehicle(null);
        this.mainWindow.refresh();
    }
    
    /**
     * Method to check to see if we have any maintenance Types
     * @return true if we do
     */
    public boolean hasMaintenanceTypes(){
        if(this.getGarage().getMaintenanceTypeArrayList().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Method used to delete a Maintenance Type
     * @param incomingMaintenanceType
     * @return true if successful
     */
    public boolean deleteMaintenanceType(MaintenanceType incomingMaintenanceType){
        boolean deleted = this.getGarage().deleteMaintenanceType(incomingMaintenanceType);
        this.mainWindow.refresh();
        return deleted;
    }
    
    public boolean deleteMaintenaceAction(MaintenanceAction incomingMaintenanceAction){
        this.getCurrentVehicle().deleteMaintenanceAction(incomingMaintenanceAction);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean deleteFuelEntry(FuelEntry incomingFuelEntry){
        this.getCurrentVehicle().deleteFuelEntry(incomingFuelEntry);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean deleteWarranty(Warranty incomingWarranty){
        this.getCurrentVehicle().deleteWarranty(incomingWarranty);
        this.mainWindow.refresh();
        return true;
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
    
    public boolean editFuelEntry(FuelEntry existingFuelEntry, FuelEntry updatedFuelEntry){
        this.getCurrentVehicle().editFuelEntry(existingFuelEntry, updatedFuelEntry);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean addFuelEntry(FuelEntry incomingFuelEntry){
        this.currentGarage.getCurrentVehicle().addFuelEntry(incomingFuelEntry);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean addMaintenanceAction(MaintenanceAction incomingMaintenanceAction){
        this.currentGarage.getCurrentVehicle().addMaintenanceAction(incomingMaintenanceAction);
        // TIME TO REFRESH
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean addModification(Modification incomingModification){
        this.currentGarage.getCurrentVehicle().addModification(incomingModification);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean editModification(Modification existingModification, Modification updatedModification){
        this.getCurrentVehicle().editModification(existingModification,updatedModification);
        this.mainWindow.refresh();
        return true;
    }
    
    public boolean deleteModification(Modification incomingModification){
        this.getCurrentVehicle().deleteModification(incomingModification);
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
    

    

    
    public void startNewUpdateMileageWindow(){
        this.updateMileageWindow = new UpdateMileageWindow(this);
        this.updateMileageWindow.setVisible(true);
    }
    
    /**
     * Method to create the main window, essential for the program
     * <li> connection between main window and engine vital for program at this point
     */
    public void startMainWindow() throws URISyntaxException{
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
    
    public void startNewModificationWindow(){
        this.modificationWindow = new ModificationWindow(this);
        this.modificationWindow.setVisible(true);
    }
    
    public void startViewOrEditModificationWindow(Modification incomingModification){
        this.modificationWindow = new ModificationWindow(this, incomingModification);
        this.modificationWindow.setVisible(true);
    }
    
    /**
     * Method to create an Update Mechanic Window, which prompts the user for updated Mechanic details
     *      
     */
    public void startUpdateMechanicWindow(){
        this.mechanicWindow = new MechanicWindow(this, this.getCurrentMechanic());
        this.mechanicWindow.setVisible(true);
    }
    
    public void startUpdateVehicleWindow(){
        this.vehicleWindow = new VehicleWindow(this, this.getCurrentVehicle());
        this.vehicleWindow.setVisible(true);
    }
    
   
    
    /**
     * Method to create a new Customer Window, which prompts user for new Customer details
     */
    public void startNewCustomerWindow(){
        this.customerWindow = new CustomerWindow(this);
        this.customerWindow.setVisible(true);
    }
    
    public void startNewDragStripSlipWindow(){
        this.dragStripSlipWindow = new DragStripSlipWindow(this);
        this.dragStripSlipWindow.setVisible(true);
    }
    
    public void startVehicleInformationGraphs(){
        this.vehicleInformationGraphs = new VehicleInformationGraphs(this);
        this.vehicleInformationGraphs.setVisible(true);
    }
    
    public void startUpdateDragStripSlipWindow(DragStripSlip incomingDragStripSlip){
        this.dragStripSlipWindow = new DragStripSlipWindow(this, incomingDragStripSlip);
        this.dragStripSlipWindow.setVisible(true);
    }
    
    /**
     * Method to create a new Update Customer Window, which prompts the user for updated Customer details
     */
    public void startUpdateCustomerWindow(){
        this.customerWindow = new CustomerWindow(this, this.getCurrentCustomer());
        this.customerWindow.setVisible(true);
    }
    
    /**
     * Method to create a new Vehicle Window, which prompts the user for new Vehicle details
     */
    public void startNewVehicleWindow(){
        this.vehicleWindow = new VehicleWindow(this);
        this.vehicleWindow.setVisible(true);       
    }
        
     /**
     * Private method used to start the welcome window
     * <li> used in the beginning of the program to flash neat graphics, welcome user,  etc
     */
    private void startWelcomeWindow(){
        //this.welcomeWindow = new WelcomeWindow(this);
        //this.welcomeWindow.setVisible(true);
    }  
    
    public void startAboutWindow(){
        try {
            this.aboutWindow = new AboutWindow();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MotoGarageNotebookEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.aboutWindow.setVisible(true);
    }
    
    public void startVehicleTrackersWindow(){
        this.vehicleTrackersWindow = new VehicleTrackersWindow(this);
        this.vehicleTrackersWindow.setVisible(true);
    }
    
    /**
     * Method used to start the Maintenance Type Window, to create a new Type
     */
    public void startNewMaintenanceTypeWindow(MaintenanceTypesMainWindow incomingMaintenanceTypesMainWindow){
        this.maintenanceTypeWindow = new MaintenanceTypeWindow(incomingMaintenanceTypesMainWindow,this);
        this.maintenanceTypeWindow.setVisible(true);
    }
    
     /**
     * Method used to start the Maintenance Type Window, to Update a Type
     * 
     */
    public void startUpdateMaintenanceTypeWindow(MaintenanceTypesMainWindow incomingMaintenanceTypesMainWindow,MaintenanceType originalMaintenanceType){
        this.maintenanceTypeWindow = new MaintenanceTypeWindow(incomingMaintenanceTypesMainWindow,this,originalMaintenanceType);
        this.maintenanceTypeWindow.setVisible(true);
    }
    
    public void startNewMaintenanceActionWindow(){
        this.newMaintenenaceActionWindow = new NewMaintenanceActionWindow(this);
        this.newMaintenenaceActionWindow.setVisible(true);
    }
    
    public void startNewCloudUserLoginCreationWindow(){
        this.cloudUserLoginCreationWindow = new CloudUserLoginCreationWindow(this);
        this.cloudUserLoginCreationWindow.setVisible(true);
    }
    
    public void startNewFuelEntryWindow(){
        this.fuelEntryWindow = new FuelEntryWindow(this);
        this.fuelEntryWindow.setVisible(true);
    }
    
    public void startNewWarrantyWindow(){
        this.warrantyWindow = new WarrantyWindow(this);
        this.warrantyWindow.setVisible(true);
    }
    
    public void startViewOrEditWarrantyWindow(Warranty incomingWarranty){
        this.warrantyWindow = new WarrantyWindow(this,incomingWarranty);
        this.warrantyWindow.setVisible(true);
    }
    
    public void startViewOrEditFuelEntryWindow(FuelEntry incomingFuelEntry){
        this.fuelEntryWindow = new FuelEntryWindow(this, incomingFuelEntry);
        this.fuelEntryWindow.setVisible(true);
    }
    
    public void startMaintenanceActionWindow(MaintenanceAction incomingMaintenanceAction){
        this.maintenanceActionWindow = new MaintenanceActionWindow(this, incomingMaintenanceAction);
        this.maintenanceActionWindow.setVisible(true);
    }
    
    public void startMaintenanceTypesMainWindow(){
        this.maintenanceTypesMainWindow = new MaintenanceTypesMainWindow(this);
        this.maintenanceTypesMainWindow.setVisible(true);
    }
    
    // End Window Creation Methods
    
    /**
     * Method used to set the current Mechanic
     * @param incomingMechanic 
     */
    public void setCurrentMechanic(Mechanic incomingMechanic){
        this.currentGarage.setCurrentMechanic(incomingMechanic);
        this.mainWindow.refresh();
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
        this.mainWindow.refresh();
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
        this.mainWindow.refresh();
    }
    
    public Vehicle getCurrentVehicle(){
        return this.currentGarage.getCurrentVehicle();
    }
}
