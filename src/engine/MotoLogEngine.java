/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import informationwindows.DialogFactory;
import informationwindows.DialogType;
import informationwindows.ProgressDialog;
import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import objectmodels.Garage;
import objectmodels.Mechanic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import objectmodels.Customer;
import objectmodels.DragStripSlip;
import objectmodels.FuelEntry;
import objectmodels.MaintenanceAction;
import objectmodels.MaintenanceType;
import objectmodels.Modification;
import objectmodels.Vehicle;
import objectmodels.VehicleMaintenanceType;
import objectmodels.VehicleModel;
import objectmodels.VehicleType;
import objectmodels.Warranty;
import org.parse4j.ParseException;
import org.parse4j.ParseUser;
import parse.ParseEngine;
import windows.AboutWindow;
import windows.CloudCreateUserWindow;
import windows.CloudUserLoginCreationWindow;
import windows.MainWindow;
import windows.MaintenanceActionWindow;
import windows.CustomerWindow;
import windows.DragStripSlipWindow;
import windows.FuelEntryWindow;
import windows.MaintenanceActionsOverdueWindow;
import windows.NewMaintenanceActionWindow;
import windows.MaintenanceTypeWindow;
import windows.MaintenanceTypesMainWindow;
import windows.MechanicWindow;
import windows.ModificationWindow;
import windows.VehicleWindow;
import windows.UpdateMileageWindow;
import windows.VehicleInformationGraphs;
import windows.VehicleMaintenanceTypesMainWindow;
import windows.VehicleTrackersWindow;
import windows.VehicleModelsMainWindow;
import windows.VehicleModelsWindow;
import windows.WarrantyWindow;
import windows.WelcomeWindow;

/**
 * MAIN engine for Mechanic's Notebook
 * @author Mark
 */
public class MotoLogEngine {
    
    //Window Variables
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
    private VehicleModelsWindow vehicleModelsWindow;
    //private VehicleModelsMainWindow vehicleModelsMainWindow;
    private VehicleModelsMainWindow vehicleModelsMainWindow;
    private ProgressDialog progressDialogWindow;
    private VehicleMaintenanceTypesMainWindow vehicleMaintenanceTypesMainWindow;
    private MaintenanceActionsOverdueWindow maintenanceActionsOverdueWindow;
    private CloudCreateUserWindow cloudCreateUserWindow;
    
    //Other Variables
    private Garage currentGarage;
    private final DialogFactory dialogFactory;
    private Boolean saved;
    private double version;
    
    // Parse.com variables
    ParseEngine parseEngine = new ParseEngine(this);
    Boolean cloudEnabled = false;
    String garageObjectId = null;

    
    public MotoLogEngine(){
        this.dialogFactory = new DialogFactory();
        this.version = 0.4;
    }
    
    public void setGarageObjectId(String incomingGarageObjectId){
        this.garageObjectId = incomingGarageObjectId;
    }
    
    public String getGarageObjectId(){
        return this.garageObjectId;
    }
    
    public String getMotoLogIcon(){
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/MGFavicon.png")));
        String icon = "/motoLogIcon16x16.png";
        return icon;
    }
    
    public double getVersion(){
        return this.version;
    }
    
    public String getTitle(){
        return "MotoLog Version " + this.getVersion();
    }
    
    /**
     * @param args the command line arguments
     * MAIN PROGRAM ENTRY POINT!
     * @throws org.parse4j.ParseException
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        MotoLogEngine mechanicsNotebookEngine = new MotoLogEngine();
        // create a DEFAULT GARAGE as program just opened
        mechanicsNotebookEngine.createDefaultGarage();
        //add some maintenance TYPES
        MaintenanceType oilChange = new MaintenanceType("Oil Change", 3000,"Simple Oil Change",VehicleType.CARORTRUCK);
        MaintenanceType rotateTires = new MaintenanceType("Tire Rotation", 5000,"Rotating the tires (LF-> LR, LR->LF, etc)",VehicleType.CARORTRUCK);
        mechanicsNotebookEngine.addMaintenanceType(oilChange);
        mechanicsNotebookEngine.addMaintenanceType(rotateTires);

        try {
            //mechanicsNotebookEngine.startWelcomeWindow();
            
            mechanicsNotebookEngine.startMainWindow();
        } catch (URISyntaxException ex) {
            System.out.println("Please report to Dev the following bug!");
            System.out.println(ex.toString());
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
    
    public void signOutUser() throws ParseException{
        this.parseEngine.signOutUser();
        this.mainWindow.setTitle(this.getTitle());
        this.mainWindow.refresh();
    }
    
    public ParseUser signUpUser(Component incomingComponent,String username, String password){
        ParseUser newUser = this.parseEngine.signUpUser(incomingComponent,username, password);

        //this.currentParseUser = newUser;
        return newUser;
    }
    
    public int forgotCloudPassword(Component incomingComponent, String incomingUserName){
        return this.parseEngine.forgotCloudPassword(incomingComponent, incomingUserName);
    }
    
    public ParseUser signInUser(Component incomingComponent, String username, String password){
        ParseUser signedInUser = this.parseEngine.signInUser(incomingComponent,username, password);
        return signedInUser;
    }

    public ParseUser getCurrentParseUser(){
        return this.parseEngine.getParseUser();
    }
    
    public void setCurrentParseUser(ParseUser incomingParseUser){
        this.parseEngine.setParseUser(incomingParseUser);
        this.mainWindow.setTitle(this.getTitle() + " | Cloud User : " + incomingParseUser.getUsername());
        this.mainWindow.refresh();
    }
    


    
    public File getSaveFile(){
        
        File currentSaveFile = this.currentGarage.getSaveFile();
        return currentSaveFile;
    }
    
        /**
     * Private method to open a Garage. Performs:
     * <li> ensures user saved their progress
     * <li> Opens a file
     * <li> loads that file as the main garage
     * 
     * @param fileToOpen
     */
    public void openGarage(File fileToOpen){
        // DO THIS
        String pathOfFileToOpen = fileToOpen.getAbsolutePath();
        //System.out.println("!!!!!! engine line 181 - absolute path is..." + fileToOpen.getAbsolutePath());
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
            this.dialogFactory.createDialogMessage(this.mainWindow,DialogType.ERROR_MESSAGE, "We encountered a critical error attempting to open that file!" );
        }catch(IOException ex){
            this.dialogFactory.createDialogMessage(this.mainWindow,DialogType.ERROR_MESSAGE, "We encountered a critical error attempting to open that file! Version mismatch!");
        }
        
    }
    
    public void openFromCloud(){
        this.parseEngine.openUsersGarage();
    }
    
    public void saveToCloud() throws FileNotFoundException, IOException, ParseException, ClassNotFoundException{
        //create a temp file
    	File tempFile = File.createTempFile("motoGarageNotebookTempFile", ".tmp"); 
        //System.out.println("Temp file : " + tempFile.getAbsolutePath());
        //TEST 
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile.getAbsoluteFile());
        //FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Mark\\Documents\\test\\test.mnb");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.currentGarage);
        objectOutputStream.close();
        fileOutputStream.close();
        // turn object into byte data for cloud transfer...
        //File testFile = new File("C:\\Users\\Mark\\Documents\\test\\test.mnb");
        //System.out.println("LENGTH IS :" + tempFile.length());
        byte[] data = serializeTest(tempFile);
        this.parseEngine.saveGarage(data);      
    }
    
    /**
     * Method to take an Object (in this case, a .mnb file), and serialize it to byte[]
     * @param obj
     * @return
     * @throws IOException 
     */
    @Deprecated
    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }
    
    private static byte[] serializeTest(File file) throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(file);
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }
        
    public static File deserializeTest(byte[] bytes) throws FileNotFoundException, IOException{
        //below is the different part
        //File someFile = new File("C:\\Users\\Mark\\Documents\\test\\testDESERIALIZE.mnb");
        File tempFile = File.createTempFile("motoGarageNotebookTempFile", ".tmp");         
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
        return tempFile;
    }

    /**
     * Method to take byte[], and turn it into an object...
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @Deprecated
    private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
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
            // set garage save file
            this.currentGarage.setSaveFile(fileToSaveAs);
            this.getDialogFactory().createDialogMessage(this.mainWindow,DialogType.INFORMATION_MESSAGE, "File saved successfully.");

            long fileSizeInKB = (fileToSaveAs.length() / 1024 ) ;
            this.getDialogFactory().createDialogMessage(this.mainWindow, DialogType.INFORMATION_MESSAGE, "File size : " + fileSizeInKB + " kilobytes.");
            System.out.print("Garage saved locally to:");
            System.out.println(trimmedFilePath);
        }catch(IOException ex){
            this.getDialogFactory().createDialogMessage(this.mainWindow,DialogType.ERROR_MESSAGE, "Something went terribly wrong attempting to save!");
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
    
    public boolean updateVehicleMaintenanceType(VehicleMaintenanceType originalVehicleMaintenanceType, VehicleMaintenanceType updatedVehicleMaintenanceType, VehicleModel incomingVehicleModel){
        boolean updatedType = this.currentGarage.updateVehicleMaintenanceType(originalVehicleMaintenanceType, updatedVehicleMaintenanceType, incomingVehicleModel);
        this.mainWindow.refresh();
        return updatedType;
    }
    
    public boolean updateVehicleModel(VehicleModel originalVehicleModel, VehicleModel updatedVehicleModel){
        boolean updatedType =this.getGarage().updateVehicleModel(originalVehicleModel, updatedVehicleModel);
        
        // this is where we might want to go through each CUSTOMER and update vehicle models?
        //TODO        
        
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
            this.getDialogFactory().createDialogMessage(this.mainWindow,DialogType.ERROR_MESSAGE, "Engine is attempting to delete current Mechanic, when there is none! Error!");
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
            this.getDialogFactory().createDialogMessage(this.mainWindow,DialogType.ERROR_MESSAGE, "Engine is attempting to delete current Customer, when there is none! Error!");
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
            this.getDialogFactory().createDialogMessage(this.mainWindow,DialogType.ERROR_MESSAGE, "Engine is attempting to delete current Vehicle, when there is none! Error!");
            return;
        }       
        this.getGarage().deleteCurrentVehicle();
        this.setCurrentVehicle(null);
        this.mainWindow.refresh();
    }
    
    public boolean hasVehicleModels(){
        if(this.getGarage().getVehicleModelArray().length>0){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Method to check to see if we have any maintenance Types
     * @return true if we do
     */
    public boolean hasMaintenanceTypes(){
        Vehicle currentVehicle = this.getCurrentVehicle();
        
        
        if(currentVehicle.getVehicleModel()!=null && !currentVehicle.getVehicleModel().getVehicleMaintenanceTypesList().isEmpty()){
            return true;
            
        }else if(!this.getGarage().getMaintenanceTypeArrayList().isEmpty()){
            return true;
        }else{
            return false;
        }
        
//        if(this.getGarage().getMaintenanceTypeArrayList().isEmpty()){
//            return false;
//        }else{
//            return true;
//        }
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
    
    public boolean deleteVehicleMaintenanceType(VehicleMaintenanceType incomingVehicleMaintenanceType, VehicleModel incomingModel){
        boolean deleted = this.getGarage().deleteVehicleMaintenanceType(incomingVehicleMaintenanceType, incomingModel);
        return deleted;
    }
    
    public boolean deleteVehicleModel(VehicleModel incomingVehicleModel){
        boolean deleted = this.getGarage().deleteVehicleModel(incomingVehicleModel);
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
    
    public boolean createNewVehicleMaintenanceType(VehicleMaintenanceType incomingVehicleMaintenanceType, VehicleModel incomingVehicleModel){
        return this.currentGarage.addVehicleMaintenanceType(incomingVehicleMaintenanceType, incomingVehicleModel);
    }
    
    public boolean createNewVehicleModel(VehicleModel incomingVehicleModel){
        this.currentGarage.addVehicleModel(incomingVehicleModel);
        this.mainWindow.refresh();
        return true;
    }
    
  
    /**
     * Method to return true if the incoming customer has a vehicle with over due maintenance actions!
     * <li> used to indicate the "alert" button on or off
     * @return true if ANY Vehicle has an overdue maintenance action
     */
    public boolean hasOverDueMaintenanceActions(){
        //boolean hasOverDueMaintenanceActions = false;
        if(this.getCurrentCustomer()==null || this.getCurrentCustomer().getVehicles().isEmpty()){
            return false;
        }
        ArrayList<Vehicle> vehicles = this.getCurrentCustomer().getVehicles();
        // iterante over each vehicle
        for(Vehicle temp: vehicles){
            if(this.vehicleHasOverDueMaintenanceActions(temp)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method to take a Vehicle, and return an ArrayList of Maintenance Actions that are the latest
     * <li> Three oil changes (3000 miles, 6000 miles, 9000 miles) would return only the 9000 miles oil change
     * <li> Used to determine if 'overdue' 
     * @param incomingVehicle
     * @return ArrayList of the Latest Maintenance Actions
     */
    public ArrayList<MaintenanceAction> getLatestMaintenanceActions(Vehicle incomingVehicle){
        ArrayList<MaintenanceType> currentVehicleMaintenanceTypes = new ArrayList<>();
        // find all the types...
        for(MaintenanceAction maintenanceAction : incomingVehicle.getMaintenanceActions()){    
           if(!currentVehicleMaintenanceTypes.contains(maintenanceAction.getMaintenanceType())){
               currentVehicleMaintenanceTypes.add(maintenanceAction.getMaintenanceType());   
               //System.out.println("We are adding maintenance type..." + maintenanceAction.getMaintenanceType());
           }           
        }
        

        // go back over list and get the 'latest' one 
        ArrayList<MaintenanceAction> latestVehicleMaintenanceActions = new ArrayList<>();
       
        for(MaintenanceType currentType: currentVehicleMaintenanceTypes){
            latestVehicleMaintenanceActions.add(this.getLatestAction(incomingVehicle.getMaintenanceActions(), currentType));
        }
        
        return latestVehicleMaintenanceActions;
    }
    
    /**
     * Helpful internal method to take a list of actions for a vehicle and the type, and return the latest completed action based on that type
     * @param actionList
     * @param incomingType
     * @return 
     */
    private MaintenanceAction getLatestAction(ArrayList<MaintenanceAction> actionList, MaintenanceType incomingType){
        MaintenanceAction currentLatest = null;
        for(MaintenanceAction tempAction : actionList){
            if(tempAction.getMaintenanceType().equals(incomingType)){
                if(currentLatest == null){
                    currentLatest = tempAction;
                }else{
                    if(tempAction.getOdometer()>currentLatest.getOdometer()){
                        currentLatest = tempAction;
                    }
                }
            }          
        }
        return currentLatest;
    }
    
    /**
     * Method used to determine IF a vehicle has over due maintenance actions
     * <li> used by MaintenanceActionsOverdueWindow
     * @param incomingVehicle
     * @return true if vehicle has over due maintenance actions
     */
    public boolean vehicleHasOverDueMaintenanceActions(Vehicle incomingVehicle){
        boolean hasOverDueMaintenanceActions = false;
        int currentOdo = incomingVehicle.getOdometer();
       
        ArrayList<MaintenanceAction> newestVehicleMaintenanceActions = this.getLatestMaintenanceActions(incomingVehicle);
       
       
        // iterate over each maintenance action to see if it's over due (in the list of the 'newest' maintenance actions
        for(MaintenanceAction maintenanceAction : newestVehicleMaintenanceActions){    
            if((maintenanceAction.getOdometer() + maintenanceAction.getMaintenanceType().getMileageInterval()) < currentOdo){
                if(maintenanceAction.getMaintenanceType().getMileageInterval()>0){
                    hasOverDueMaintenanceActions = true;
                }
                
                // go ahead and end here
                return hasOverDueMaintenanceActions;
            }
        }
        return hasOverDueMaintenanceActions;
    }
    
    /**
     * Method to take a Vehicle and return a list of OVERDUE maintenance Actions
     * <li> used by MaintenanceActionsOverdueWindow
     * @param incomingVehicle
     * @return ArrayList<MaintenanceAction> overdue items
     */
    public ArrayList<MaintenanceAction> getOverDueMaintenanceActions(Vehicle incomingVehicle){
        ArrayList<MaintenanceAction> overDueMaintenanceActions = new ArrayList<MaintenanceAction>();
        // iterate over list and add over due ones to it
        int currentOdo = incomingVehicle.getOdometer();

        ArrayList<MaintenanceAction> newestVehicleMaintenanceActions = this.getLatestMaintenanceActions(incomingVehicle);
        
        // iterate over each maintenance action to see if it's over due (in the list of the 'newest' maintenance actions
        for(MaintenanceAction maintenanceAction : newestVehicleMaintenanceActions){                
            if((maintenanceAction.getOdometer() + maintenanceAction.getMaintenanceType().getMileageInterval()) < currentOdo){ 
                if(maintenanceAction.getMaintenanceType().getMileageInterval()>0){
                    overDueMaintenanceActions.add(maintenanceAction);
                }
                
                
                // ex oil change done AT 10000 miles. 3000 mile interval. car is now 14000 miles.
                // 13000 < 14000, so OVER DUE
            }
        }
        return overDueMaintenanceActions;
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
    
    /**
     * Used to get maintenance types correlating to specific vehicleType
     * @param incomingVehicleType
     * @return 
     */
    public MaintenanceType[] getMaintenaceTypeArray(VehicleType incomingVehicleType){
        return this.currentGarage.getMaintenaceTypeArray(incomingVehicleType);
    }
    
    public MaintenanceType[] getMaintenaceTypeArray(){
        return this.currentGarage.getMaintenanceTypeArray();
    }
    
    
    public VehicleModel[] getVehicleModelArray(){
        return this.currentGarage.getVehicleModelArray();
    }
    
    public VehicleMaintenanceType[] getVehicleMaintenanceTypesArray(VehicleModel incomingVehicleModel){
        return this.currentGarage.getVehicleMaintenanceTypesArray(incomingVehicleModel);
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
    

    

    
    public void startNewUpdateMileageWindow(Component incomingComponent){
        this.updateMileageWindow = new UpdateMileageWindow(new JFrame(),true,this);
        this.updateMileageWindow.setLocationRelativeTo(incomingComponent);
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
    public void startNewMechanicWindow(Component incomingParent){
        this.mechanicWindow = new MechanicWindow(new JFrame(),true,this);
        this.mechanicWindow.setLocationRelativeTo(incomingParent);
        this.mechanicWindow.setVisible(true);
    }
    
    /**
     * Method to create an Update Mechanic Window, which prompts the user for updated Mechanic details
     *      
     */
    public void startUpdateMechanicWindow(Component incomingParent){
        this.mechanicWindow = new MechanicWindow(new JFrame(),true,this,this.getCurrentMechanic());
        this.mechanicWindow.setLocationRelativeTo(incomingParent);
        this.mechanicWindow.setVisible(true);
    }
    
    public void startNewModificationWindow(Component incomingParent){
        this.modificationWindow = new ModificationWindow(new JFrame(),true,this);
        this.modificationWindow.setLocationRelativeTo(incomingParent);
        this.modificationWindow.setVisible(true);
    }
    
    public void startViewOrEditModificationWindow(Component incomingParent,Modification incomingModification){
        this.modificationWindow = new ModificationWindow(new JFrame(),true,this, incomingModification);
        this.modificationWindow.setLocationRelativeTo(incomingParent);
        this.modificationWindow.setVisible(true);
    }
    
    /**
     * Method to create a new Vehicle Window, which prompts the user for new Vehicle details
     */
    public void startNewVehicleWindow(Component incomingParent){
        this.vehicleWindow = new VehicleWindow(new JFrame(),true,this);
        this.vehicleWindow.setLocationRelativeTo(incomingParent);
        this.vehicleWindow.setVisible(true);       
    }
    
    public void startCloudCreateUserWindow(Component incomingParent){
        this.cloudCreateUserWindow = new CloudCreateUserWindow(new JFrame(), true, this);
        this.cloudCreateUserWindow.setLocationRelativeTo(incomingParent);
        this.cloudCreateUserWindow.setVisible(true);
    }
    
    public void startUpdateVehicleWindow(Component incomingParent){
        this.vehicleWindow = new VehicleWindow(new JFrame(),true,this, this.getCurrentVehicle());
        this.vehicleWindow.setLocationRelativeTo(incomingParent);
        this.vehicleWindow.setVisible(true);
        
        
    }
    
   
    
    /**
     * Method to create a new Customer Window, which prompts user for new Customer details
     * @param parentComponent
     */
    public void startNewCustomerWindow(Component incomingParent){
        this.customerWindow = new CustomerWindow(new JFrame(),true,this);
        this.customerWindow.setLocationRelativeTo(incomingParent);
        this.customerWindow.setVisible(true);
        //Point centerPoint = (parentComponent.getLocation().x,2);
        //int x = parentComponent.getLocation().x / (parentComponent.getWidth()/2);
        //int y = parentComponent.getLocation().y / (parentComponent.getHeight()/2);
        //Point centerPoint = new Point(x,y);
        //System.out.println("x =" + parentComponent.getLocation().x +" y =" + parentComponent.getLocation().y);
        //System.out.println("width is " + parentComponent.getWidth());
        //System.out.println("Location of parent is " + parentComponent.getLocation().toString());
        //this.customerWindow.setLocation(centerPoint);

    }
    
    public void startNewDragStripSlipWindow(Component incomingParent){
        this.dragStripSlipWindow = new DragStripSlipWindow(new JFrame(),true,this);
        this.dragStripSlipWindow.setLocationRelativeTo(incomingParent);
        this.dragStripSlipWindow.setVisible(true);
    }
    
    public void startUpdateDragStripSlipWindow(Component incomingParent,DragStripSlip incomingDragStripSlip){
        this.dragStripSlipWindow = new DragStripSlipWindow(new JFrame(),true,this, incomingDragStripSlip);
        this.dragStripSlipWindow.setLocationRelativeTo(incomingParent);
        this.dragStripSlipWindow.setVisible(true);
    }
    
    public void startVehicleInformationGraphs(Component incomingComponent){
        this.vehicleInformationGraphs = new VehicleInformationGraphs(new JFrame(),true,this);
        this.vehicleInformationGraphs.setLocationRelativeTo(incomingComponent);
        this.vehicleInformationGraphs.setVisible(true);
    }
    
    
    
    /**
     * Method to create a new Update Customer Window, which prompts the user for updated Customer details
     */
    public void startUpdateCustomerWindow(Component incomingParent){
        this.customerWindow = new CustomerWindow(new JFrame(),true,this, this.getCurrentCustomer());
        
        this.customerWindow.setLocationRelativeTo(incomingParent);
        this.customerWindow.setVisible(true);
    }
    
  
        
     /**
     * Private method used to start the welcome window
     * <li> used in the beginning of the program to flash neat graphics, welcome user,  etc
     */
    private void startWelcomeWindow(){
        //this.welcomeWindow = new WelcomeWindow(this);
        //this.welcomeWindow.setVisible(true);
    }  
    
    public void startAboutWindow(Component incomingComponent){
        try {
            this.aboutWindow = new AboutWindow(new JFrame(),true,this);
            this.aboutWindow.setLocationRelativeTo(incomingComponent);
            this.aboutWindow.setVisible(true);
        } catch (URISyntaxException ex) {
            Logger.getLogger(MotoLogEngine.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public void startVehicleTrackersWindow(Component incomingParent){
        this.vehicleTrackersWindow = new VehicleTrackersWindow(new JFrame(),true,this);
        this.vehicleTrackersWindow.setLocationRelativeTo(incomingParent);
        this.vehicleTrackersWindow.setVisible(true);
    }
    
    /**
     * Method used to start the Maintenance Type Window, to create a new Type
     */
    public void startNewMaintenanceTypeWindow(MaintenanceTypesMainWindow incomingMaintenanceTypesMainWindow){
        this.maintenanceTypeWindow = new MaintenanceTypeWindow(new JFrame(),true,incomingMaintenanceTypesMainWindow,this);
        this.maintenanceTypeWindow.setLocationRelativeTo(incomingMaintenanceTypesMainWindow);
        this.maintenanceTypeWindow.setVisible(true);
    }
    
     /**
     * Method used to start the Maintenance Type Window, to create a new Type
     * <li> Vehicle Model Specific!
     * @param incomingVehicleMaintenanceTypesMainWindow
     * @param incomingVehicleModel
     */
    public void startNewVehicleMaintenanceTypeWindow(VehicleMaintenanceTypesMainWindow incomingVehicleMaintenanceTypesMainWindow, VehicleModel incomingVehicleModel){
        this.maintenanceTypeWindow = new MaintenanceTypeWindow(new JFrame(),true,incomingVehicleMaintenanceTypesMainWindow,incomingVehicleModel,this);
        this.maintenanceTypeWindow.setLocationRelativeTo(incomingVehicleMaintenanceTypesMainWindow);
        this.maintenanceTypeWindow.setVisible(true);
    }
    
    
    
    public void startNewVehicleModelWindow(VehicleModelsMainWindow incomingVehicleModelsMainWindow){
        this.vehicleModelsWindow = new VehicleModelsWindow(new JFrame(),true,incomingVehicleModelsMainWindow,this);
        this.vehicleModelsWindow.setLocationRelativeTo(incomingVehicleModelsMainWindow);
        this.vehicleModelsWindow.setVisible(true);
    }
    
    public void startUpdateVehicleModelWindow(VehicleModelsMainWindow incomingVehicleModelsMainWindow,VehicleModel incomingVehicleModel){
        this.vehicleModelsWindow = new VehicleModelsWindow(new JFrame(),true,incomingVehicleModelsMainWindow,this, incomingVehicleModel);
        this.vehicleModelsWindow.setLocationRelativeTo(incomingVehicleModelsMainWindow);
        this.vehicleModelsWindow.setVisible(true);
    }
    
    
     /**
     * Method used to start the Maintenance Type Window, to Update a Type
     * 
     */
    public void startUpdateMaintenanceTypeWindow(MaintenanceTypesMainWindow incomingMaintenanceTypesMainWindow,MaintenanceType originalMaintenanceType){
        this.maintenanceTypeWindow = new MaintenanceTypeWindow(new JFrame(),true,incomingMaintenanceTypesMainWindow,this,originalMaintenanceType);
        this.maintenanceTypeWindow.setLocationRelativeTo(incomingMaintenanceTypesMainWindow);
        this.maintenanceTypeWindow.setVisible(true);
    }
    
    public void startUpdateVehicleMaintenanceTypeWindow(VehicleMaintenanceTypesMainWindow incomingVehicleMaintenanceTypesMainWindow,VehicleMaintenanceType originalVehicleMaintenanceType, VehicleModel incomingVehicleModel){
        this.maintenanceTypeWindow = new MaintenanceTypeWindow(new JFrame(), true, incomingVehicleMaintenanceTypesMainWindow,this,originalVehicleMaintenanceType,incomingVehicleModel);
        this.maintenanceTypeWindow.setLocationRelativeTo(incomingVehicleMaintenanceTypesMainWindow);
        this.maintenanceTypeWindow.setVisible(true);
    }
    
    public void startNewMaintenanceActionWindow(Component incomingParent){
        this.newMaintenenaceActionWindow = new NewMaintenanceActionWindow(new JFrame(),true,this);
        this.newMaintenenaceActionWindow.setLocationRelativeTo(incomingParent);
        this.newMaintenenaceActionWindow.setVisible(true);
    }
    
    public void startNewCloudUserLoginCreationWindow(Component incomingParent){
        this.cloudUserLoginCreationWindow = new CloudUserLoginCreationWindow(new JFrame(),true,this);
        this.cloudUserLoginCreationWindow.setLocationRelativeTo(incomingParent);
        this.cloudUserLoginCreationWindow.setVisible(true);
    }
    
    public void startNewFuelEntryWindow(Component incomingParent){
        this.fuelEntryWindow = new FuelEntryWindow(new JFrame(),true,this);
        this.fuelEntryWindow.setLocationRelativeTo(incomingParent);
        this.fuelEntryWindow.setVisible(true);
    }
    
    public void startViewOrEditFuelEntryWindow(Component incomingParent,FuelEntry incomingFuelEntry){
        this.fuelEntryWindow = new FuelEntryWindow(new JFrame(),true,this, incomingFuelEntry);
        this.fuelEntryWindow.setLocationRelativeTo(incomingParent);
        this.fuelEntryWindow.setVisible(true);
    }
    
    public void startNewWarrantyWindow(Component incomingParent){
        this.warrantyWindow = new WarrantyWindow(new JFrame(),true,this);
        this.warrantyWindow.setLocationRelativeTo(incomingParent);
        this.warrantyWindow.setVisible(true);
    }
    
    public void startViewOrEditWarrantyWindow(Component incomingParent,Warranty incomingWarranty){
        this.warrantyWindow = new WarrantyWindow(new JFrame(),true,this,incomingWarranty);
        this.warrantyWindow.setLocationRelativeTo(incomingParent);
        this.warrantyWindow.setVisible(true);
    }
    
    
    
    
    public void startMaintenanceActionWindow(Component incomingParent,MaintenanceAction incomingMaintenanceAction){
        this.maintenanceActionWindow = new MaintenanceActionWindow(new JFrame(),true,this, incomingMaintenanceAction);
        this.maintenanceActionWindow.setLocationRelativeTo(incomingParent);
        this.maintenanceActionWindow.setVisible(true);
    }
    
    public void startMaintenanceActionsOverdueWindow(Component incomingParent){
        this.maintenanceActionsOverdueWindow = new MaintenanceActionsOverdueWindow(new JFrame(),true,this);
        this.maintenanceActionsOverdueWindow.setLocationRelativeTo(incomingParent);
        this.maintenanceActionsOverdueWindow.setVisible(true);
    }
    
    public void startMaintenanceTypesMainWindow(Component incomingParent){
        this.maintenanceTypesMainWindow = new MaintenanceTypesMainWindow(new JFrame(),true,this);
        this.maintenanceTypesMainWindow.setLocationRelativeTo(incomingParent);
        this.maintenanceTypesMainWindow.setVisible(true);
    }
    
    public void startVehicleMaintenaceTypesMainWindow(Component incomingParent){
        this.vehicleMaintenanceTypesMainWindow = new VehicleMaintenanceTypesMainWindow(new JFrame(), true, this);
        this.vehicleMaintenanceTypesMainWindow.setLocationRelativeTo(incomingParent);
        this.vehicleMaintenanceTypesMainWindow.setVisible(true);
    }
    
    public void startVehicleModelsMainWindow(Component incomingParent){
        this.vehicleModelsMainWindow = new VehicleModelsMainWindow(new JFrame(),true,this);
        this.vehicleModelsMainWindow.setLocationRelativeTo(incomingParent);

        this.vehicleModelsMainWindow.setVisible(true);

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
