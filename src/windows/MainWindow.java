/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;


import java.io.File;
import javax.swing.JFileChooser;
import objectmodels.Mechanic;
import engine.MotoGarageNotebookEngine;
import informationwindows.DialogType;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import objectmodels.Customer;
import objectmodels.DragStripSlip;
import objectmodels.FuelEntry;
import objectmodels.MaintenanceAction;
import objectmodels.Modification;
import objectmodels.Vehicle;
import objectmodels.Warranty;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.parse4j.ParseException;

/**
 *
 * @author Mark
 */
public class MainWindow extends javax.swing.JFrame {
    private MotoGarageNotebookEngine motoGarageNotebookEngine;
    //DefaultListModel model = new DefaultListModel();
    DefaultTableModel tableModel = new DefaultTableModel();
    //JList maintenanceActionList2;
    
    // borders for panels
    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
    Border compound = BorderFactory.createCompoundBorder(
                          raisedbevel, loweredbevel);
    
    // variables
    int maxColumnWidth = 80;
    
    // INFO for Help Button
    final URI uri = new URI("http://www.motogaragechi.com/motogarage-notebook-help/");
    class OpenUrlAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                open(uri);
            }
        }
    
    private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
        } catch (IOException e) {
            /* TODO: error handling */ 
        }
        } else {
        /* TODO: error handling */ 
        }
    }
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() throws URISyntaxException{
        initComponents();
    }
    
     /**
     * Creates new form MainWindow
     */
    public MainWindow(MotoGarageNotebookEngine incomingMotoGarageMechanicEngine) throws URISyntaxException{
        
        this.motoGarageNotebookEngine = incomingMotoGarageMechanicEngine;
        //TESTING
        //ImagePanel panel = new ImagePanel(new ImageIcon("images/background.png").getImage());
//        ImageIcon test = new javax.swing.ImageIcon(getClass().getResource("/motoGarageLogoTrans.png"));
//        ImagePanel panel = new ImagePanel(test.getImage());
        //mechanicButtonEditNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mechanic32x32EDIT.png")));
        //TESTING
        initComponents();   
        
        
        //this.getContentPane().setBackground(Color.black);
        //this.mainTabbedPane.setBackground(Color.black);
        //this.mechanicsPanel.setBackground(Color.black);
        this.setIcon();
        //this.currentMechanicPanel.setBackground(Color.red);
        

        
        
        // mechanics table model
    DefaultTableModel vehiclesTableModel = new DefaultTableModel(
        new Object [][] {
        },
        new String [] {
            "Make", "Model", "Year"
        }) {           
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };
    vehiclesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    vehiclesTable.setModel(vehiclesTableModel);
        
        
        // mechanics table model
    DefaultTableModel customersTableModel = new DefaultTableModel(
        new Object [][] {
        },
        new String [] {
            "First", "Middle", "Last"
        }) {           
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };
    customersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    customersTable.setModel(customersTableModel);
        
        
    // mechanics table model
    DefaultTableModel mechanicsTableModel = new DefaultTableModel(
        new Object [][] {
        },
        new String [] {
            "First", "Middle", "Last"
        }) {           
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };
    mechanicsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    mechanicsTable.setModel(mechanicsTableModel);
        
        
        DefaultTableModel maintenanceActionsTableModel = new DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Odometer", "Date","Maintenance Type", "Notes", "Performed By","Model Specific"
            }
                
                ) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
    };
    maintenanceActionsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    maintenanceActionsTable.setModel(maintenanceActionsTableModel);
    
    // end main constructor
    }
    
    private void setIcon(){
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mechanicIcon.png")));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/MGFavicon.png")));
    }

 
    
    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * !!!Critical method, used to refresh everything after a change (adding a mechanic, etc)!!!
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public void refresh(){
        System.out.println("Refresh method being called....");
        this.refreshMechanicsTab();
        this.refreshCustomersTab();
        this.refreshVehiclesTab();
        this.refreshModifications();
        this.refreshVehicleMaintenanceTypeButton();
        
        
        //this.refreshMainWindowStatusBar();
        
        // TESTING TESTING DELETE

    }
    
    /**
     * Method to update the Vehicle Specific Maintenance Types Button
     * <li> Only enabled IF there are Vehicle Models to add it to
     */
    private void refreshVehicleMaintenanceTypeButton(){
        if(this.motoGarageNotebookEngine.getVehicleModelArray().length>0){
            vehicleMaintenanceTypeButton.setEnabled(true);
        }else{
            vehicleMaintenanceTypeButton.setEnabled(false);
        }
    }
    
    private void refreshMainWindowStatusBar(){
      //JXStatusBar bar = new JXStatusBar();
      
      //JLabel statusLabel = new JLabel("Ready");
      //this.mainWindowStatusBar.add(statusLabel, new JXStatusBar()); //weight of 0.0 and no insets
      //JProgressBar pbar = new JProgressBar();
      //this.mainWindowStatusBar.add(pbar); //weight of 0.0 and no insets
        //this.mainWindowStatusBar.s
    }

    private void refreshVehiclesTab(){
        System.out.println("Vehicles tab being refreshed..");
        this.refreshCurrentVehicleInformation();
        this.refreshVehicleList();
        
        this.refreshVehicleTrackers();
        
        this.refreshMaintenanceActions();
        this.refreshFuelEntries();
        this.refreshWarranties();
        this.refreshModifications();
        this.refreshDragStripSlips();
    }
    

    
    private void refreshVehicleTrackers(){
        if(this.motoGarageNotebookEngine.getVehicleTrackersChanged()){
            
        
            Boolean fuelEntries = this.motoGarageNotebookEngine.getFuelEntriesEnabled();
            Boolean warranties = this.motoGarageNotebookEngine.getWarrantiesEnabled();
            Boolean modifications = this.motoGarageNotebookEngine.getModificationsEnabled();
            Boolean dragStripSlips = this.motoGarageNotebookEngine.getDragStripSlipsEnabled();
            if(fuelEntries){
                this.mainTabbedPane.addTab("Fuel Entries", fuelEntriesPanel);
                this.fuelEntryMainToolBarAddButton.setEnabled(true);
            }else{
                this.mainTabbedPane.remove(fuelEntriesPanel);
                this.fuelEntryMainToolBarAddButton.setEnabled(false);
            }
            if(warranties){
                this.mainTabbedPane.addTab("Warranties", vehicleWarrantiesPanel);
            }else{
                this.mainTabbedPane.remove(vehicleWarrantiesPanel);
            }
            if(modifications){
                this.mainTabbedPane.addTab("Modifications", modificationsPanel);
            }else{
                this.mainTabbedPane.remove(modificationsPanel);
            }
            if(dragStripSlips){
                this.mainTabbedPane.addTab("Drag Strip Slips", dragStripSlipsPanel);
            }else{
                this.mainTabbedPane.remove(dragStripSlipsPanel);
            }
            this.motoGarageNotebookEngine.setVehicleTrackersChanged(false);
        }
    }

    private void refreshDragStripSlips(){
        DefaultTableModel model = (DefaultTableModel) dragStripSlipsTable.getModel();
        // time to remove the fuel entries here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        // IF there is a vehicle and it has drag strip slips      
        if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null && this.motoGarageNotebookEngine.getCurrentVehicle().getDragStripSlipList().size() >0){
            dragStripSlipAddButton.setEnabled(true);
            dragStripSlipEditButton.setEnabled(true);
            dragStripSlipDeleteButton.setEnabled(true);
            
            // NO IDEA HOW TO SORT UNTIL WE FIGURE OUT DATE DUDE
            int newRowCount = this.motoGarageNotebookEngine.getCurrentVehicle().getDragStripSlipArray().length;
            DragStripSlip[] dragStripSlipArrayArray = this.motoGarageNotebookEngine.getCurrentVehicle().getDragStripSlipArray();
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]dragStripSlipObject = dragStripSlipArrayArray[i].getDragStripSlipObject();
                model.addRow(dragStripSlipObject);                
            }
        }else if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null){
            // if there is a vehicle, but no drag strip slips
            dragStripSlipAddButton.setEnabled(true);
            dragStripSlipEditButton.setEnabled(false);
            dragStripSlipDeleteButton.setEnabled(false);
           
        }else{
            // if no vehicle
            dragStripSlipAddButton.setEnabled(false);
            dragStripSlipEditButton.setEnabled(false);
            dragStripSlipDeleteButton.setEnabled(false);
        }
    }
    
    private void refreshModifications(){
        DefaultTableModel model = (DefaultTableModel) modificationsTable.getModel();
        // time to remove the fuel entries here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        // IF there is a vehicle and it has modifications      
        if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null && this.motoGarageNotebookEngine.getCurrentVehicle().getModifications().size() >0){
            addModificationButton.setEnabled(true);
            editModificationButton.setEnabled(true);
            deleteModificationButton.setEnabled(true);
            
            // NO IDEA HOW TO SORT UNTIL WE FIGURE OUT DATE DUDE
            int newRowCount = this.motoGarageNotebookEngine.getCurrentVehicle().getModificationArray().length;
            Modification[] modificationArray = this.motoGarageNotebookEngine.getCurrentVehicle().getModificationArray();
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]modificationObject = modificationArray[i].getModificationObject();
                model.addRow(modificationObject);                
            }
        }else if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null){
            // if there is a vehicle, but no warranties
            addModificationButton.setEnabled(true);
            editModificationButton.setEnabled(false);
            deleteModificationButton.setEnabled(false);
           
        }else{
            // if no vehicle
            addModificationButton.setEnabled(false);
            editModificationButton.setEnabled(false);
            deleteModificationButton.setEnabled(false);
        }
        
    }
    
    private void refreshWarranties(){
        warrantiesTable.getColumnModel().getColumn(1).setMaxWidth(maxColumnWidth);
        DefaultTableModel model = (DefaultTableModel) warrantiesTable.getModel();
        // time to remove the fuel entries here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        
        // IF there is a vehicle and it has warranties      
        if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null && this.motoGarageNotebookEngine.getCurrentVehicle().getWarranties().size() >0){
            addWarrantyButton.setEnabled(true);
            editWarrantyButton.setEnabled(true);
            deleteWarrantyButton.setEnabled(true);
            
            // NO IDEA HOW TO SORT UNTIL WE FIGURE OUT DATE DUDE
            int newRowCount = this.motoGarageNotebookEngine.getCurrentVehicle().getWarrantyArray().length;
            Warranty[] warrantyArray = this.motoGarageNotebookEngine.getCurrentVehicle().getWarrantyArray();
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]warrantyObject = warrantyArray[i].getWarrantyObject();
                model.addRow(warrantyObject);                
            }
        }else if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null){
            // if there is a vehicle, but no warranties
            addWarrantyButton.setEnabled(true);
            editWarrantyButton.setEnabled(false);
            deleteWarrantyButton.setEnabled(false);
           
        }else{
            // if no vehicle
            addWarrantyButton.setEnabled(false);
            editWarrantyButton.setEnabled(false);
            deleteWarrantyButton.setEnabled(false);
        }
    }
    
    
    private void refreshFuelEntries(){
        DefaultTableModel model = (DefaultTableModel) fuelEntriesTable.getModel();
        fuelEntriesTable.getColumnModel().getColumn(0).setMaxWidth(maxColumnWidth);
        fuelEntriesTable.getColumnModel().getColumn(1).setMaxWidth(maxColumnWidth);
        // time to remove the fuel entries here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        Boolean fuelEntries = this.motoGarageNotebookEngine.getFuelEntriesEnabled();
        // IF there is a vehicle and it has fuel entries
        //System.out.println("TEST");
        if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null && this.motoGarageNotebookEngine.getCurrentVehicle().getFuelEntries().size() >0){
            // main tool bar  
            
            if(fuelEntries){
                fuelEntryMainToolBarAddButton.setEnabled(true);
            }
            
                    
            fuelEntryAddButton.setEnabled(true);
            fuelEntryEditButton.setEnabled(true);
            fuelEntryDeleteButton.setEnabled(true);
            

            int newRowCount = this.motoGarageNotebookEngine.getCurrentVehicle().getSortedFuelEntryArray().length;
            FuelEntry[] fuelEntryArray = this.motoGarageNotebookEngine.getCurrentVehicle().getSortedFuelEntryArray();
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]fuelEntryObject = fuelEntryArray[i].getFuelEntryObject();

                model.addRow(fuelEntryObject);                
            }
        }else if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null){
            // if there is a vehicle, but no fuel entries
            // main tool bar        
            if(fuelEntries){
                fuelEntryMainToolBarAddButton.setEnabled(true);
            }

            
            fuelEntryAddButton.setEnabled(true);
            fuelEntryEditButton.setEnabled(false);
            fuelEntryDeleteButton.setEnabled(false);
           
        }else{
            // if no vehicle
            // main tool bar            
            fuelEntryMainToolBarAddButton.setEnabled(false);
            
            fuelEntryAddButton.setEnabled(false);
            fuelEntryEditButton.setEnabled(false);
            fuelEntryDeleteButton.setEnabled(false);
        }
    }
    
    /**
     * One of the most important Refresh methods, refreshes all the maintenance actions for current vehicle
     */
    private void refreshMaintenanceActions(){
        
        DefaultTableModel model = (DefaultTableModel) maintenanceActionsTable.getModel();
        maintenanceActionsTable.getColumnModel().getColumn(0).setMaxWidth(maxColumnWidth);
        maintenanceActionsTable.getColumnModel().getColumn(1).setMaxWidth(maxColumnWidth);
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        // IF there is a vehicle and it has maintenance actions
        if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null && this.motoGarageNotebookEngine.getCurrentVehicle().getSortedMaintenanceActionsArray().length >0){
            // main tool bar
            addMaintenanceActionButton.setEnabled(true);
            
            addMaintenanceActionButtonToolBar.setEnabled(true);
            editMaintenanceActionButtonToolBar.setEnabled(true);
            deleteMaintenanceActionButtonToolBar.setEnabled(true);
            updateOdometerActionButtonToolBar.setEnabled(true);

            graphsButton.setEnabled(true);
            int newRowCount = this.motoGarageNotebookEngine.getCurrentVehicle().getSortedMaintenanceActionsArray().length;
            MaintenanceAction[] maintenanceArray = this.motoGarageNotebookEngine.getCurrentVehicle().getSortedMaintenanceActionsArray();
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]maintenanceActionObject = maintenanceArray[i].getMaintenaceActionObject();

                model.addRow(maintenanceActionObject);                
            }
        }else if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null){
            // if there is a vehicle
            // main tool bar
            addMaintenanceActionButton.setEnabled(true);
            graphsButton.setEnabled(true);

            
            addMaintenanceActionButtonToolBar.setEnabled(true);
            updateOdometerActionButtonToolBar.setEnabled(true);
            

            
            editMaintenanceActionButtonToolBar.setEnabled(false);
            deleteMaintenanceActionButtonToolBar.setEnabled(false);            
        }else{
            // if no vehicle
            // main tool bar
            addMaintenanceActionButton.setEnabled(false);
            graphsButton.setEnabled(false);

            
            addMaintenanceActionButtonToolBar.setEnabled(false);
            editMaintenanceActionButtonToolBar.setEnabled(false);
            deleteMaintenanceActionButtonToolBar.setEnabled(false);
            updateOdometerActionButtonToolBar.setEnabled(false);

        }
    }    
    
    /**
     * Method use to update the Mechanic List
     */
    private void refreshMechanicsList(){
        DefaultTableModel model = (DefaultTableModel) mechanicsTable.getModel();
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        Integer currentRowSelected = mechanicsTable.getSelectedRow();
        // remove all mechanics
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        // if we have mechanics, re-add them
        if(this.motoGarageNotebookEngine.getMechanicArray().length >0){
            mechanicButtonEditNew.setEnabled(true);
            mechanicDeleteButtonNew.setEnabled(true);
            Mechanic[] mechanics = this.motoGarageNotebookEngine.getMechanicArray();
            int newRowCount = mechanics.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]mechanicObject = mechanics[i].getMechanicObject();
                model.addRow(mechanicObject);       
            }
            // since there ARE mechanics, let's ensure the 'selected' remains selected
            if(currentRowSelected>-1 && this.motoGarageNotebookEngine.getCurrentMechanic()!=null){
                ListSelectionModel selectionModel =mechanicsTable.getSelectionModel();
                selectionModel.setSelectionInterval(currentRowSelected, currentRowSelected);

            }else if((currentRowSelected==-1) &&this.motoGarageNotebookEngine.getCurrentMechanic()!=null ){
                ListSelectionModel selectionModel =mechanicsTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                //currentRowSelected = mechanicsTable.getSelectedRow();
                this.motoGarageNotebookEngine.setCurrentMechanic(mechanics[0]);
            }else if(currentRowSelected==-1 && this.motoGarageNotebookEngine.getCurrentMechanic() == null){
                ListSelectionModel selectionModel =mechanicsTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                // = mechanicsTable.getSelectedRow();
                this.motoGarageNotebookEngine.setCurrentMechanic(mechanics[0]);
            }                
        }
        else{
            mechanicButtonEditNew.setEnabled(false);
            mechanicDeleteButtonNew.setEnabled(false);
        }
    }
    
    /**
     * Method used to update the Vehicle List
     */
    private void refreshVehicleList(){
        DefaultTableModel model = (DefaultTableModel) vehiclesTable.getModel();
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        Integer currentRowSelected = vehiclesTable.getSelectedRow();

        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        // time to add the vehicles
        if(this.motoGarageNotebookEngine.getCurrentCustomer()!=null &&this.motoGarageNotebookEngine.getCurrentCustomer().getVehicleArray().length >0){
            vehicleAddButtonNew.setEnabled(true);
            vehicleEditButtonNew.setEnabled(true);
            vehicleDeleteButtonNew.setEnabled(true);
            Vehicle[] vehicles = this.motoGarageNotebookEngine.getVehicleArray();
            int newRowCount = vehicles.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]vehicleObject = vehicles[i].getVehicleObject();
                model.addRow(vehicleObject);                      
                }
            
            if(this.motoGarageNotebookEngine.getVehicleArray().length >0){
            //Vehicle[] vehicle = this.motoGarageNotebookEngine.getVehicleArray();  //?????????????????????          
            // since there ARE customers, let's ensure the 'selected' remains selected
            if(currentRowSelected==-1 && this.motoGarageNotebookEngine.getCurrentVehicle()!=null){
                ListSelectionModel selectionModel =vehiclesTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
            }else if((currentRowSelected>-1) && this.motoGarageNotebookEngine.getCurrentVehicle()!=null ){
                ListSelectionModel selectionModel =vehiclesTable.getSelectionModel();
                selectionModel.setSelectionInterval(currentRowSelected, currentRowSelected);
            }else if(currentRowSelected==-1 && this.motoGarageNotebookEngine.getCurrentVehicle() == null){
                ListSelectionModel selectionModel =vehiclesTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                this.motoGarageNotebookEngine.setCurrentVehicle(vehicles[0]);
                }
            }
        }else if(this.motoGarageNotebookEngine.getCurrentCustomer()!=null &&this.motoGarageNotebookEngine.getCurrentCustomer().getVehicleArray().length ==0){
            vehicleAddButtonNew.setEnabled(true);
            vehicleEditButtonNew.setEnabled(false);
            vehicleDeleteButtonNew.setEnabled(false);
        }else if(this.motoGarageNotebookEngine.getCurrentCustomer()==null){
            vehicleAddButtonNew.setEnabled(false);
            vehicleEditButtonNew.setEnabled(false);
            vehicleDeleteButtonNew.setEnabled(false);
        }
    }
    
    
    /**
     * Method used to refresh the entire Customers Tabs
     * <li> Here we will refresh the maintenance alerts
     */
    private void refreshCustomersTab(){
        System.out.println("Refresh customers tab being called");
        this.refreshCustomerList();
        this.refreshCurrentCustomerInformation();
        
        // testing
        this.refreshMaintenanceAlerts();
    }
    
    
    private void refreshMaintenanceAlerts(){
        // if no vehicles, no maintenance tasks due
//        if(this.motoGarageNotebookEngine.getCurrentCustomer().getVehicles().isEmpty()){
//            maintenanceAlertButton.setEnabled(false);
//        }
        if(this.motoGarageNotebookEngine.hasOverDueMaintenanceActions()){
            maintenanceAlertButton.setEnabled(true);
            maintenanceAlertButton.setToolTipText("You have Maintenance Actions over due!");
        }else{
            maintenanceAlertButton.setEnabled(false);
            maintenanceAlertButton.setToolTipText("There are no overdue Maintenance Actions.");
        }

    }
    
    /**
     * Method used to update the Customer List (JTable)
     */
    private void refreshCustomerList(){
        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        Integer currentRowSelected = customersTable.getSelectedRow();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        if(this.motoGarageNotebookEngine.getCustomerArray().length >0){
            customerEditButtonNew.setEnabled(true);
            customerDeleteButtonNew.setEnabled(true);
            Customer[] customers = this.motoGarageNotebookEngine.getCustomerArray();
            int newRowCount = customers.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]customerObject = customers[i].getCustomerObject();
                model.addRow(customerObject);                      
            }
            
            // since there ARE customers, let's ensure the 'selected' remains selected
            if(currentRowSelected>-1 && this.motoGarageNotebookEngine.getCurrentCustomer()!=null){
                //System.out.println("SHIT!");
                //System.out.println(currentRowSelected);
                ListSelectionModel selectionModel =customersTable.getSelectionModel();
                selectionModel.setSelectionInterval(currentRowSelected, currentRowSelected); // 
          
            }else if((currentRowSelected==-1) && this.motoGarageNotebookEngine.getCurrentCustomer()!=null ){
                //System.out.println("SHIT!!!!");
                //System.out.println(currentRowSelected);
                ListSelectionModel selectionModel =customersTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                this.motoGarageNotebookEngine.setCurrentCustomer(customers[0]);
            }else if(currentRowSelected==-1 && this.motoGarageNotebookEngine.getCurrentCustomer() == null){
                //System.out.println("SHIT!!!!!!!!!!!!!");
                ListSelectionModel selectionModel =customersTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                this.motoGarageNotebookEngine.setCurrentCustomer(customers[0]);
            }
        }else{
            customerEditButtonNew.setEnabled(false);
            customerDeleteButtonNew.setEnabled(false);
        }
    }
    
    
    /**
     * Method used to refresh everything within the Mechanics Tab
     */
    private void refreshMechanicsTab(){
        System.out.println("Refresh mechanics tab being called.");
        this.refreshCurrentMechanicInformation();
        this.refreshMechanicsList();
    }
    
    private void refreshCurrentMechanicInformation(){
        if(this.motoGarageNotebookEngine.getCurrentMechanic()!=null){
            //System.out.println("FAIL 696");
            Mechanic currentMechanic= this.motoGarageNotebookEngine.getCurrentMechanic();
            if(currentMechanic.getImageIcon()!=null){               
                ImageIcon imageIcon = this.motoGarageNotebookEngine.getCurrentMechanic().getImageIcon(); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it 
                Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                imageIcon = new ImageIcon(newimg);  // transform it back
                this.mechanicPictureLabel.setIcon(imageIcon);
            }else{
                this.mechanicPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N);
            }
            this.mechanicNameLabel.setText(currentMechanic.getFirstName() + " " +currentMechanic.getMiddleName() + " " + currentMechanic.getLastName());
        }else{
            this.mechanicPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N);
            this.mechanicNameLabel.setText("No Mechanic Selected");
        }
    }
    
    private void refreshCurrentCustomerInformation(){
        if(this.motoGarageNotebookEngine.getCurrentCustomer()!=null){
            //System.out.println("FAIL 716");
            Customer currentCustomer= this.motoGarageNotebookEngine.getCurrentCustomer();
            if(currentCustomer.getImageIcon()!=null){               
                ImageIcon imageIcon = this.motoGarageNotebookEngine.getCurrentCustomer().getImageIcon(); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it 
                Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                imageIcon = new ImageIcon(newimg);  // transform it back
                this.customerPictureLabel.setIcon(imageIcon);
            }else{
                this.customerPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N);
            }
            this.customerNameLabel.setText(currentCustomer.getFirstName() + " " +currentCustomer.getMiddleName() + " " + currentCustomer.getLastName());
        }else{
            this.customerPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N);
            this.customerNameLabel.setText("No Customer Selected");
        }
    }
    
    private void refreshCurrentVehicleInformation(){
        ArrayList<JLabel> listOfLabels = new ArrayList<>();
            listOfLabels.add(this.oneOdoLabel);
            listOfLabels.add(this.twoOdoLabel);
            listOfLabels.add(this.threeOdoLabel);
            listOfLabels.add(this.fourOdoLabel);
            listOfLabels.add(this.fiveOdoLabel);
            listOfLabels.add(this.sixOdoLabel); 
            
        if(this.motoGarageNotebookEngine.getCurrentVehicle()!=null){
            Vehicle currentVehicle= this.motoGarageNotebookEngine.getCurrentVehicle();
            if(currentVehicle.getImageIcon()!=null){               
                ImageIcon imageIcon = this.motoGarageNotebookEngine.getCurrentVehicle().getImageIcon(); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it 
                Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                imageIcon = new ImageIcon(newimg);  // transform it back
                this.vehiclePictureLabel.setIcon(imageIcon);
            }else{
                this.vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N);
            }
            this.vehicleNameLabel.setText(currentVehicle.getYear().toString() + " " +currentVehicle.getVehicleModel().getMake() + " " + currentVehicle.getVehicleModel().getModel());
            //this.currentVehicleOdometerLabel.setText("Odometer: " + currentVehicle.getOdometer().toString());
            
            // SET THE ODOMETER
            
            // reset the odometer
            for (JLabel item : listOfLabels) {
                item.setIcon(new javax.swing.ImageIcon(getClass().getResource(this.motoGarageNotebookEngine.getOdoImagePath(0))));
            }          
            
            Integer currentOdometer = currentVehicle.getOdometer();
            String testString = String.valueOf(currentOdometer);
            int length = testString.length();          
            int currentPlace = 6-length ; // with 4, that'd be 3rd...         
            int i = 0;
            while(i<length){
                int odo = Integer.parseInt(Character.toString(testString.charAt(i)));
                listOfLabels.get(currentPlace).setIcon(new javax.swing.ImageIcon(getClass().getResource(this.motoGarageNotebookEngine.getOdoImagePath(odo))));
                i++;
                currentPlace++;
            }
            
        }else{  
            this.vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N);
            this.vehicleNameLabel.setText("No Vehicle Selected");

            // reset the odometer
            for (JLabel item : listOfLabels) {
                item.setIcon(new javax.swing.ImageIcon(getClass().getResource(this.motoGarageNotebookEngine.getOdoImagePath(0))));
            } 
            
        }
        
    }
    

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        mainTabbedPane = new javax.swing.JTabbedPane();
        vehicleMaintenanceActionsPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        maintenanceActionsTable = new javax.swing.JTable();
        maintenanceActionsToolBar = new javax.swing.JToolBar();
        ImageIcon maintenanceActionAddToolBar = new ImageIcon(getClass().getResource("/maintenanceAction32x32ADD.png"));
        Action actionMaintenanceActionAddToolBar = new AbstractAction("New", maintenanceActionAddToolBar) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("MaintenanceActionAdd add");
            }
        };
        addMaintenanceActionButtonToolBar = maintenanceActionsToolBar.add(actionMaintenanceActionAddToolBar);
        ImageIcon maintenanceActionEditToolBar = new ImageIcon(getClass().getResource("/maintenanceAction32x32EDIT.png"));
        Action actionMaintenanceActionEditToolBar = new AbstractAction("New", maintenanceActionEditToolBar) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("MaintenanceActionEdit");
            }
        };
        editMaintenanceActionButtonToolBar = maintenanceActionsToolBar.add(actionMaintenanceActionEditToolBar);
        ImageIcon maintenanceActionDeleteToolBar = new ImageIcon(getClass().getResource("/maintenanceAction32x32DELETE.png"));
        Action actionMaintenanceActionDeleteToolBar = new AbstractAction("New", maintenanceActionDeleteToolBar) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("MaintenanceActionEdit");
            }
        };
        deleteMaintenanceActionButtonToolBar = maintenanceActionsToolBar.add(actionMaintenanceActionDeleteToolBar);
        fuelEntriesPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        fuelEntriesTable = new javax.swing.JTable();
        fuelEntryToolBar = new javax.swing.JToolBar();
        fuelEntryAddButton = new javax.swing.JButton();
        fuelEntryEditButton = new javax.swing.JButton();
        fuelEntryDeleteButton = new javax.swing.JButton();
        vehicleWarrantiesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        warrantiesTable = new javax.swing.JTable();
        warrantyEntryToolBar = new javax.swing.JToolBar();
        addWarrantyButton = new javax.swing.JButton();
        editWarrantyButton = new javax.swing.JButton();
        deleteWarrantyButton = new javax.swing.JButton();
        modificationsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        modificationsTable = new javax.swing.JTable();
        modificationEntryToolBar = new javax.swing.JToolBar();
        addModificationButton = new javax.swing.JButton();
        editModificationButton = new javax.swing.JButton();
        deleteModificationButton = new javax.swing.JButton();
        dragStripSlipsPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        dragStripSlipsTable = new javax.swing.JTable();
        dragStripSlipToolBar = new javax.swing.JToolBar();
        dragStripSlipAddButton = new javax.swing.JButton();
        dragStripSlipEditButton = new javax.swing.JButton();
        dragStripSlipDeleteButton = new javax.swing.JButton();
        mainToolBar = new javax.swing.JToolBar();
        openGarageButton = new javax.swing.JButton();
        saveGarageButton = new javax.swing.JButton();
        loadFromCloudButton = new javax.swing.JButton();
        saveToCloudButton = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        editVehicleModelsButton = new javax.swing.JButton();
        ImageIcon maintenanceTypeEdit = new ImageIcon(getClass().getResource("/maintenanceType32x32EDIT.png"));
        Action actionMaintenanceTypeEdit = new AbstractAction("New", maintenanceTypeEdit) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("MaintenanceTypes Edid!");
            }
        };
        editMaintenanceTypesButton = mainToolBar.add(actionMaintenanceTypeEdit);
        vehicleMaintenanceTypeButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        ImageIcon updateOdometerActionToolBar = new ImageIcon(getClass().getResource("/odometer32x32ADD.png"));
        Action actionUpdateOdometerActionToolBar = new AbstractAction("New", updateOdometerActionToolBar) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ODOMETER UPDATE");
            }
        };
        updateOdometerActionButtonToolBar = maintenanceActionsToolBar.add(actionUpdateOdometerActionToolBar);
        ImageIcon maintenanceActionAdd = new ImageIcon(getClass().getResource("/maintenanceAction32x32ADD.png"));
        Action actionMaintenanceActionAdd = new AbstractAction("New", maintenanceActionAdd) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("MaintenanceActionAdd add");
            }
        };
        addMaintenanceActionButton = mainToolBar.add(actionMaintenanceActionAdd);
        maintenanceAlertButton = new javax.swing.JButton();
        fuelEntryMainToolBarAddButton = new javax.swing.JButton();
        graphsButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        helpButton = new javax.swing.JButton();
        mechanicPanelNew = new javax.swing.JPanel();
        mechanicToolBar = new javax.swing.JToolBar();
        ImageIcon mechanicAdd = new ImageIcon(getClass().getResource("/mechanic32x32ADD.png"));
        Action actionMechanicAdd = new AbstractAction("New", mechanicAdd) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mechanic add");
            }
        };
        mechanicAddButtonNew = mechanicToolBar.add(actionMechanicAdd);
        ImageIcon mechanicEdit = new ImageIcon(getClass().getResource("/mechanic32x32EDIT.png"));
        Action actionMechanicEdit = new AbstractAction("New", mechanicEdit) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mechanic edit");
            }
        };
        mechanicButtonEditNew = mechanicToolBar.add(actionMechanicEdit);
        ImageIcon mechanicDelete = new ImageIcon(getClass().getResource("/mechanic32x32REMOVE.png"));
        Action actionMechanicDelete = new AbstractAction("New", mechanicDelete) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mechanic delete");
            }
        };
        mechanicDeleteButtonNew = mechanicToolBar.add(actionMechanicDelete);
        jScrollPane3 = new javax.swing.JScrollPane();
        mechanicsTable = new javax.swing.JTable();
        customerPanelNew = new javax.swing.JPanel();
        customerToolBar = new javax.swing.JToolBar();
        ImageIcon customerAdd = new ImageIcon(getClass().getResource("/customer32x32ADD.png"));
        Action actionCustomerAdd = new AbstractAction("New", customerAdd) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("customer add");
            }
        };
        customerAddButtonNew = customerToolBar.add(actionCustomerAdd);
        ImageIcon customerEdit = new ImageIcon(getClass().getResource("/customer32x32EDIT.png"));
        Action actionCustomerEdit = new AbstractAction("New", customerEdit) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("customer edit");
            }
        };
        customerEditButtonNew = customerToolBar.add(actionCustomerEdit);
        ImageIcon customerDelete = new ImageIcon(getClass().getResource("/customer32x32REMOVE.png"));
        Action actionCustomerDelete = new AbstractAction("New", customerDelete) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("customer delete");
            }
        };
        customerDeleteButtonNew = customerToolBar.add(actionCustomerDelete);
        jScrollPane7 = new javax.swing.JScrollPane();
        customersTable = new javax.swing.JTable();
        vehiclePanelNew = new javax.swing.JPanel();
        vehicleToolBar = new javax.swing.JToolBar();
        ImageIcon vehicleAdd = new ImageIcon(getClass().getResource("/vehicle32x32ADD.png"));
        Action actionVehicleAdd = new AbstractAction("New", vehicleAdd) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("vehicle add");
            }
        };
        vehicleAddButtonNew = vehicleToolBar.add(actionVehicleAdd);
        ImageIcon vehicleEdit = new ImageIcon(getClass().getResource("/vehicle32x32EDIT.png"));
        Action actionVehicleEdit = new AbstractAction("New", vehicleEdit) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("vehicle Edit");
            }
        };
        vehicleEditButtonNew = vehicleToolBar.add(actionVehicleEdit);
        ImageIcon vehicleDelete = new ImageIcon(getClass().getResource("/vehicle32x32REMOVE.png"));
        Action actionVehicleDelete = new AbstractAction("New", vehicleDelete) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("vehicle Remove");
            }
        };
        vehicleDeleteButtonNew = vehicleToolBar.add(actionVehicleDelete);
        jScrollPane8 = new javax.swing.JScrollPane();
        vehiclesTable = new javax.swing.JTable();
        currentVehiclePanel = new javax.swing.JPanel();
        vehiclePictureLabel = new javax.swing.JLabel();
        vehicleNameLabel = new javax.swing.JLabel();
        currentVehicleOdometerLabel = new javax.swing.JLabel();
        odometerPanel = new javax.swing.JPanel();
        oneOdoLabel = new javax.swing.JLabel();
        twoOdoLabel = new javax.swing.JLabel();
        threeOdoLabel = new javax.swing.JLabel();
        fourOdoLabel = new javax.swing.JLabel();
        fiveOdoLabel = new javax.swing.JLabel();
        sixOdoLabel = new javax.swing.JLabel();
        currentCustomerPanel = new javax.swing.JPanel();
        customerPictureLabel = new javax.swing.JLabel();
        customerNameLabel = new javax.swing.JLabel();
        currentMechanicPanel = new javax.swing.JPanel();
        mechanicNameLabel = new javax.swing.JLabel();
        mechanicPictureLabel = new javax.swing.JLabel();
        mechanicsNotebookMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newGarageMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        openMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        loginMenuItem = new javax.swing.JMenuItem();
        logoutMenuItem = new javax.swing.JMenuItem();
        fileMenuSeparator = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        optionsMenu = new javax.swing.JMenu();
        VehicleTrackersMenuItem = new javax.swing.JMenuItem();
        viewEditMaintenanceTypesMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MotoGarage Notebook");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        //mainTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        maintenanceActionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Odometer", "Date","Maintenance Type", "Notes", "Performed By","Vehicle Specific"
            }
        ));
        maintenanceActionsTable.setOpaque(true);
        maintenanceActionsTable.setFillsViewportHeight(true);
        maintenanceActionsTable.setBackground(Color.WHITE);
        //maintenanceActionsTable.getColumnModel().getColumn(0).setMaxWidth(80);
        jScrollPane6.setViewportView(maintenanceActionsTable);

        maintenanceActionsToolBar.setRollover(true);
        maintenanceActionsToolBar.setFloatable(false);

        addMaintenanceActionButtonToolBar.setToolTipText("Add Maintenance Action to Current Vehicle");
        addMaintenanceActionButtonToolBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32ADD.png"))); // NOI18N
        addMaintenanceActionButtonToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaintenanceActionButtonToolBarActionPerformed(evt);
            }
        });
        maintenanceActionsToolBar.add(addMaintenanceActionButtonToolBar);

        editMaintenanceActionButtonToolBar.setToolTipText("View / Edit Selected Maintenance Action");
        editMaintenanceActionButtonToolBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32EDIT.png"))); // NOI18N
        editMaintenanceActionButtonToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMaintenanceActionButtonToolBarActionPerformed(evt);
            }
        });
        maintenanceActionsToolBar.add(editMaintenanceActionButtonToolBar);

        deleteMaintenanceActionButtonToolBar.setToolTipText("Delete Selected Maintenance Action");
        deleteMaintenanceActionButtonToolBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32DELETE.png"))); // NOI18N
        deleteMaintenanceActionButtonToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMaintenanceActionButtonToolBarActionPerformed(evt);
            }
        });
        maintenanceActionsToolBar.add(deleteMaintenanceActionButtonToolBar);

        javax.swing.GroupLayout vehicleMaintenanceActionsPanelLayout = new javax.swing.GroupLayout(vehicleMaintenanceActionsPanel);
        vehicleMaintenanceActionsPanel.setLayout(vehicleMaintenanceActionsPanelLayout);
        vehicleMaintenanceActionsPanelLayout.setHorizontalGroup(
            vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maintenanceActionsToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        vehicleMaintenanceActionsPanelLayout.setVerticalGroup(
            vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maintenanceActionsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Maintenance Actions", vehicleMaintenanceActionsPanel);

        fuelEntriesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Odometer", "Date","Gallons", "Cost Per Gallon","Total Cost"
            }
        ));
        fuelEntriesTable.setOpaque(true);
        fuelEntriesTable.setFillsViewportHeight(true);
        fuelEntriesTable.setBackground(Color.WHITE);
        fuelEntriesTable.getColumnModel().getColumn(0).setMaxWidth(80);
        jScrollPane4.setViewportView(fuelEntriesTable);

        fuelEntryToolBar.setRollover(true);
        fuelEntryToolBar.setFloatable(false);

        fuelEntryAddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fuelEntry32x32ADD.png"))); // NOI18N
        fuelEntryAddButton.setToolTipText("Add Fuel Entry");
        fuelEntryAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fuelEntryAddButtonActionPerformed(evt);
            }
        });
        fuelEntryToolBar.add(fuelEntryAddButton);

        fuelEntryEditButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fuelEntry32x32EDIT.png"))); // NOI18N
        fuelEntryEditButton.setToolTipText("View / Edit Selected Fuel Entry");
        fuelEntryEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fuelEntryEditButtonActionPerformed(evt);
            }
        });
        fuelEntryToolBar.add(fuelEntryEditButton);

        fuelEntryDeleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fuelEntry32x32DELETE.png"))); // NOI18N
        fuelEntryDeleteButton.setToolTipText("Delete Selected Fuel Entry");
        fuelEntryDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fuelEntryDeleteButtonActionPerformed(evt);
            }
        });
        fuelEntryToolBar.add(fuelEntryDeleteButton);

        javax.swing.GroupLayout fuelEntriesPanelLayout = new javax.swing.GroupLayout(fuelEntriesPanel);
        fuelEntriesPanel.setLayout(fuelEntriesPanelLayout);
        fuelEntriesPanelLayout.setHorizontalGroup(
            fuelEntriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
            .addGroup(fuelEntriesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fuelEntryToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        fuelEntriesPanelLayout.setVerticalGroup(
            fuelEntriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fuelEntriesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fuelEntryToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Fuel Entries", fuelEntriesPanel);

        warrantiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Part", "Date", "Warranty Duration", "Description", "Cost"
            }
        ));
        warrantiesTable.setOpaque(true);
        warrantiesTable.setFillsViewportHeight(true);
        warrantiesTable.setBackground(Color.WHITE);
        jScrollPane1.setViewportView(warrantiesTable);

        warrantyEntryToolBar.setRollover(true);
        warrantyEntryToolBar.setFloatable(false);

        addWarrantyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warrantyType32x32ADD.png"))); // NOI18N
        addWarrantyButton.setToolTipText("Add a Warranty");
        addWarrantyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWarrantyButtonActionPerformed(evt);
            }
        });
        warrantyEntryToolBar.add(addWarrantyButton);

        editWarrantyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warrantyType32x32EDIT.png"))); // NOI18N
        editWarrantyButton.setToolTipText("View / Edit Selected Warranty");
        editWarrantyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editWarrantyButtonActionPerformed(evt);
            }
        });
        warrantyEntryToolBar.add(editWarrantyButton);

        deleteWarrantyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/warrantyType32x32DELETE.png"))); // NOI18N
        deleteWarrantyButton.setToolTipText("Delete Selected Warranty");
        deleteWarrantyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteWarrantyButtonActionPerformed(evt);
            }
        });
        warrantyEntryToolBar.add(deleteWarrantyButton);

        javax.swing.GroupLayout vehicleWarrantiesPanelLayout = new javax.swing.GroupLayout(vehicleWarrantiesPanel);
        vehicleWarrantiesPanel.setLayout(vehicleWarrantiesPanelLayout);
        vehicleWarrantiesPanelLayout.setHorizontalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
            .addGroup(vehicleWarrantiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(warrantyEntryToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        vehicleWarrantiesPanelLayout.setVerticalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehicleWarrantiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(warrantyEntryToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Warranties", vehicleWarrantiesPanel);

        modificationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Part", "Date", "Warranty Duration", "Description","Cost"
            }
        ));
        modificationsTable.setOpaque(true);
        modificationsTable.setFillsViewportHeight(true);
        modificationsTable.setBackground(Color.WHITE);
        modificationsTable.getColumnModel().getColumn(1).setMaxWidth(80);
        jScrollPane2.setViewportView(modificationsTable);

        modificationEntryToolBar.setRollover(true);
        modificationEntryToolBar.setFloatable(false);

        addModificationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modification32x32ADD.png"))); // NOI18N
        addModificationButton.setToolTipText("Add a Modification");
        addModificationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addModificationButtonActionPerformed(evt);
            }
        });
        modificationEntryToolBar.add(addModificationButton);

        editModificationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modification32x32EDIT.png"))); // NOI18N
        editModificationButton.setToolTipText("View / Edit Selected Modification");
        editModificationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editModificationButtonActionPerformed(evt);
            }
        });
        modificationEntryToolBar.add(editModificationButton);

        deleteModificationButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modification32x32DELETE.png"))); // NOI18N
        deleteModificationButton.setToolTipText("Delete Selected Modification");
        deleteModificationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteModificationButtonActionPerformed(evt);
            }
        });
        modificationEntryToolBar.add(deleteModificationButton);

        javax.swing.GroupLayout modificationsPanelLayout = new javax.swing.GroupLayout(modificationsPanel);
        modificationsPanel.setLayout(modificationsPanelLayout);
        modificationsPanelLayout.setHorizontalGroup(
            modificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
            .addGroup(modificationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modificationEntryToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        modificationsPanelLayout.setVerticalGroup(
            modificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modificationEntryToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Modifications", modificationsPanel);

        dragStripSlipsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Date", "R/T", "60'", "330'", "1/8" , "1/8 MPH", "1000", "1/4", "1/4 MPH"
            }
        ));
        dragStripSlipsTable.setOpaque(true);
        dragStripSlipsTable.setFillsViewportHeight(true);
        dragStripSlipsTable.setBackground(Color.WHITE);
        jScrollPane5.setViewportView(dragStripSlipsTable);

        dragStripSlipToolBar.setRollover(true);
        dragStripSlipToolBar.setFloatable(false);

        dragStripSlipAddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dragStripSlip32x32ADD.png"))); // NOI18N
        dragStripSlipAddButton.setToolTipText("Add a Drag Strip Slip");
        dragStripSlipAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dragStripSlipAddButtonActionPerformed(evt);
            }
        });
        dragStripSlipToolBar.add(dragStripSlipAddButton);

        dragStripSlipEditButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dragStripSlip32x32EDIT.png"))); // NOI18N
        dragStripSlipEditButton.setToolTipText("View / Edit Selected Drag Strip Slip");
        dragStripSlipEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dragStripSlipEditButtonActionPerformed(evt);
            }
        });
        dragStripSlipToolBar.add(dragStripSlipEditButton);

        dragStripSlipDeleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dragStripSlip32x32DELETE.png"))); // NOI18N
        dragStripSlipDeleteButton.setToolTipText("Delete Selected Drag Strip Slip");
        dragStripSlipDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dragStripSlipDeleteButtonActionPerformed(evt);
            }
        });
        dragStripSlipToolBar.add(dragStripSlipDeleteButton);

        javax.swing.GroupLayout dragStripSlipsPanelLayout = new javax.swing.GroupLayout(dragStripSlipsPanel);
        dragStripSlipsPanel.setLayout(dragStripSlipsPanelLayout);
        dragStripSlipsPanelLayout.setHorizontalGroup(
            dragStripSlipsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
            .addGroup(dragStripSlipsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dragStripSlipToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dragStripSlipsPanelLayout.setVerticalGroup(
            dragStripSlipsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dragStripSlipsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dragStripSlipToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Drag Strip Slips", dragStripSlipsPanel);

        mainToolBar.setRollover(true);
        mainToolBar.setFloatable(false);

        openGarageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/motoGarage32x32OPEN.png"))); // NOI18N
        openGarageButton.setFocusable(false);
        openGarageButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openGarageButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openGarageButton.setToolTipText("Open a Garage");
        openGarageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openGarageButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(openGarageButton);

        saveGarageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/motoGarage32x32SAVE.png"))); // NOI18N
        saveGarageButton.setFocusable(false);
        saveGarageButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveGarageButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveGarageButton.setToolTipText("Save Current Garage");
        saveGarageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveGarageButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(saveGarageButton);

        loadFromCloudButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/motoGarageCloud32x32DOWNLOAD.png"))); // NOI18N
        loadFromCloudButton.setToolTipText("Load Garage from Cloud");
        loadFromCloudButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFromCloudButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(loadFromCloudButton);

        saveToCloudButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/motoGarageCloud32x32UPLOAD.png"))); // NOI18N
        saveToCloudButton.setToolTipText("Save Garage to Cloud");
        saveToCloudButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveToCloudButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(saveToCloudButton);
        mainToolBar.add(jSeparator7);

        editVehicleModelsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vehicleModel32x32EDIT.png"))); // NOI18N
        editVehicleModelsButton.setFocusable(false);
        editVehicleModelsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editVehicleModelsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editVehicleModelsButton.setToolTipText("View / Edit Vehicle Models");
        editVehicleModelsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVehicleModelsButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(editVehicleModelsButton);

        editMaintenanceTypesButton.setToolTipText("View / Edit General Maintenance Types");
        editMaintenanceTypesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceType32x32EDIT.png"))); // NOI18N
        editMaintenanceTypesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMaintenanceTypesButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(editMaintenanceTypesButton);

        vehicleMaintenanceTypeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vehicleMaintenanceType32x32EDIT.png"))); // NOI18N
        vehicleMaintenanceTypeButton.setFocusable(false);
        vehicleMaintenanceTypeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        vehicleMaintenanceTypeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        vehicleMaintenanceTypeButton.setToolTipText("View and Edit Vehicle Model Specific Maintenance Types");
        vehicleMaintenanceTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleMaintenanceTypeButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(vehicleMaintenanceTypeButton);
        mainToolBar.add(jSeparator2);

        updateOdometerActionButtonToolBar.setToolTipText("Update Odometer");
        updateOdometerActionButtonToolBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/odometer32x32ADD.png"))); // NOI18N
        updateOdometerActionButtonToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateOdometerActionButtonToolBarActionPerformed(evt);
            }
        });
        mainToolBar.add(updateOdometerActionButtonToolBar);

        addMaintenanceActionButton.setToolTipText("Add Maintenance Action to Current Vehicle");
        addMaintenanceActionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32ADD.png"))); // NOI18N
        addMaintenanceActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaintenanceActionButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(addMaintenanceActionButton);

        maintenanceAlertButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32ALERT.png"))); // NOI18N
        maintenanceAlertButton.setToolTipText("There are 0 maintenance reminders for this Vehicle");
        maintenanceAlertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintenanceAlertButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(maintenanceAlertButton);

        fuelEntryMainToolBarAddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fuelEntry32x32ADD.png"))); // NOI18N
        fuelEntryMainToolBarAddButton.setToolTipText("Add Fuel Entry");
        fuelEntryMainToolBarAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fuelEntryMainToolBarAddButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(fuelEntryMainToolBarAddButton);

        graphsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graph32x32EDIT.png"))); // NOI18N
        graphsButton.setToolTipText("Vehicle Information Graphs");
        graphsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphsButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(graphsButton);
        mainToolBar.add(jSeparator3);

        helpButton.setToolTipText("Help");
        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/help32x32.png"))); // NOI18N
        helpButton.addActionListener(new OpenUrlAction());
        helpButton.setToolTipText("Visit help online on MotoGarage Forums");
        mainToolBar.add(helpButton);

        mechanicPanelNew.setBorder(compound);
        mechanicPanelNew.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Mechanics"));

        mechanicToolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        mechanicToolBar.setRollover(true);
        mechanicToolBar.setFloatable(false);

        mechanicAddButtonNew.setToolTipText("Add a Mechanic");
        mechanicAddButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mechanic32x32ADD.png"))); // NOI18N
        mechanicAddButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mechanicAddButtonNewActionPerformed(evt);
            }
        });
        mechanicToolBar.add(mechanicAddButtonNew);

        mechanicButtonEditNew.setToolTipText("View / Edit Selected Mechanic");
        mechanicButtonEditNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mechanic32x32EDIT.png"))); // NOI18N
        mechanicButtonEditNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mechanicButtonEditNewActionPerformed(evt);
            }
        });
        mechanicToolBar.add(mechanicButtonEditNew);

        mechanicDeleteButtonNew.setToolTipText("Delete Selected Mechanic");
        mechanicDeleteButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mechanic32x32REMOVE.png"))); // NOI18N
        mechanicDeleteButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mechanicDeleteButtonNewActionPerformed(evt);
            }
        });
        mechanicToolBar.add(mechanicDeleteButtonNew);

        mechanicsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "First", "Middle", "Last", "Description"
            }
        ));
        mechanicsTable.setOpaque(true);
        mechanicsTable.setFillsViewportHeight(true);
        mechanicsTable.setBackground(Color.WHITE);
        mechanicsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mechanicsTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mechanicsTableMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(mechanicsTable);

        javax.swing.GroupLayout mechanicPanelNewLayout = new javax.swing.GroupLayout(mechanicPanelNew);
        mechanicPanelNew.setLayout(mechanicPanelNewLayout);
        mechanicPanelNewLayout.setHorizontalGroup(
            mechanicPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                .addComponent(mechanicToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mechanicPanelNewLayout.setVerticalGroup(
            mechanicPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(mechanicToolBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
        );

        customerPanelNew.setBorder(compound);
        customerPanelNew.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Customers"));

        customerToolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        customerToolBar.setRollover(true);
        customerToolBar.setFloatable(false);

        customerAddButtonNew.setToolTipText("Add Customer");
        customerAddButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customer32x32ADD.png"))); // NOI18N
        customerAddButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerAddButtonNewActionPerformed(evt);
            }
        });
        customerToolBar.add(customerAddButtonNew);

        customerEditButtonNew.setToolTipText("View / Edit Customer");
        customerEditButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customer32x32EDIT.png"))); // NOI18N
        customerEditButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerEditButtonNewActionPerformed(evt);
            }
        });
        customerToolBar.add(customerEditButtonNew);

        customerDeleteButtonNew.setToolTipText("Delete Customer");
        customerDeleteButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customer32x32REMOVE.png"))); // NOI18N
        customerDeleteButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerDeleteButtonNewActionPerformed(evt);
            }
        });
        customerToolBar.add(customerDeleteButtonNew);

        customersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "First", "Middle", "Last", "Description"
            }
        ));
        customersTable.setOpaque(true);
        customersTable.setFillsViewportHeight(true);
        customersTable.setBackground(Color.WHITE);
        customersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customersTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                customersTableMouseReleased(evt);
            }
        });
        jScrollPane7.setViewportView(customersTable);

        javax.swing.GroupLayout customerPanelNewLayout = new javax.swing.GroupLayout(customerPanelNew);
        customerPanelNew.setLayout(customerPanelNewLayout);
        customerPanelNewLayout.setHorizontalGroup(
            customerPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelNewLayout.createSequentialGroup()
                .addComponent(customerToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        customerPanelNewLayout.setVerticalGroup(
            customerPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        vehiclePanelNew.setBorder(compound);
        vehiclePanelNew.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Vehicles"));

        vehicleToolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        vehicleToolBar.setRollover(true);
        vehicleToolBar.setFloatable(false);

        vehicleAddButtonNew.setToolTipText("Add Vehicle");
        vehicleAddButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vehicle32x32ADD.png"))); // NOI18N
        vehicleAddButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleAddButtonNewActionPerformed(evt);
            }
        });
        vehicleToolBar.add(vehicleAddButtonNew);

        vehicleEditButtonNew.setToolTipText("View / Edit Vehicle");
        vehicleEditButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vehicle32x32EDIT.png"))); // NOI18N
        vehicleEditButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleEditButtonNewActionPerformed(evt);
            }
        });
        vehicleToolBar.add(vehicleEditButtonNew);

        vehicleDeleteButtonNew.setToolTipText("Delete Selected Vehicle");
        vehicleDeleteButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vehicle32x32REMOVE.png"))); // NOI18N
        vehicleDeleteButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleDeleteButtonNewActionPerformed(evt);
            }
        });
        vehicleToolBar.add(vehicleDeleteButtonNew);

        vehiclesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Make", "Model", "Year", "Vin"
            }
        ));
        vehiclesTable.setOpaque(true);
        vehiclesTable.setFillsViewportHeight(true);
        vehiclesTable.setBackground(Color.WHITE);
        vehiclesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vehiclesTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vehiclesTableMouseReleased(evt);
            }
        });
        jScrollPane8.setViewportView(vehiclesTable);

        javax.swing.GroupLayout vehiclePanelNewLayout = new javax.swing.GroupLayout(vehiclePanelNew);
        vehiclePanelNew.setLayout(vehiclePanelNewLayout);
        vehiclePanelNewLayout.setHorizontalGroup(
            vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                .addComponent(vehicleToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        vehiclePanelNewLayout.setVerticalGroup(
            vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(vehicleToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        //currentVehiclePanel.setBorder(compound);
        currentVehiclePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Selected Vehicle"));

        vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N

        vehicleNameLabel.setText("No Vehicle Selected");

        currentVehicleOdometerLabel.setText("Odometer:");

        oneOdoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oneOdometer.png"))); // NOI18N

        twoOdoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/twoOdometer.png"))); // NOI18N

        threeOdoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/threeOdometer.png"))); // NOI18N

        fourOdoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fourOdometer.png"))); // NOI18N

        fiveOdoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fiveOdometer.png"))); // NOI18N

        sixOdoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sixOdometer.png"))); // NOI18N

        javax.swing.GroupLayout odometerPanelLayout = new javax.swing.GroupLayout(odometerPanel);
        odometerPanel.setLayout(odometerPanelLayout);
        odometerPanelLayout.setHorizontalGroup(
            odometerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(odometerPanelLayout.createSequentialGroup()
                .addComponent(oneOdoLabel)
                .addGap(0, 0, 0)
                .addComponent(twoOdoLabel)
                .addGap(0, 0, 0)
                .addComponent(threeOdoLabel)
                .addGap(0, 0, 0)
                .addComponent(fourOdoLabel)
                .addGap(0, 0, 0)
                .addComponent(fiveOdoLabel)
                .addGap(0, 0, 0)
                .addComponent(sixOdoLabel))
        );
        odometerPanelLayout.setVerticalGroup(
            odometerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(odometerPanelLayout.createSequentialGroup()
                .addGroup(odometerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sixOdoLabel)
                    .addComponent(fiveOdoLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(odometerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(oneOdoLabel)
                        .addComponent(twoOdoLabel)
                        .addComponent(threeOdoLabel)
                        .addComponent(fourOdoLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout currentVehiclePanelLayout = new javax.swing.GroupLayout(currentVehiclePanel);
        currentVehiclePanel.setLayout(currentVehiclePanelLayout);
        currentVehiclePanelLayout.setHorizontalGroup(
            currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                .addComponent(vehiclePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicleNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                        .addComponent(currentVehicleOdometerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(odometerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 57, Short.MAX_VALUE)))
                .addContainerGap())
        );
        currentVehiclePanelLayout.setVerticalGroup(
            currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                .addGroup(currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                        .addComponent(vehicleNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currentVehicleOdometerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(odometerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(vehiclePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //currentCustomerPanel.setBorder(compound);
        currentCustomerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Selected Customer"));

        customerPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N

        customerNameLabel.setText("No Customer Selected");

        javax.swing.GroupLayout currentCustomerPanelLayout = new javax.swing.GroupLayout(currentCustomerPanel);
        currentCustomerPanel.setLayout(currentCustomerPanelLayout);
        currentCustomerPanelLayout.setHorizontalGroup(
            currentCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentCustomerPanelLayout.createSequentialGroup()
                .addComponent(customerPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );
        currentCustomerPanelLayout.setVerticalGroup(
            currentCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(customerNameLabel)
        );

        //currentMechanicPanel.setBorder(compound);
        currentMechanicPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Selected Mechanic"));

        mechanicNameLabel.setText("No Mechanic Selected");

        mechanicPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N

        javax.swing.GroupLayout currentMechanicPanelLayout = new javax.swing.GroupLayout(currentMechanicPanel);
        currentMechanicPanel.setLayout(currentMechanicPanelLayout);
        currentMechanicPanelLayout.setHorizontalGroup(
            currentMechanicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, currentMechanicPanelLayout.createSequentialGroup()
                .addComponent(mechanicPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mechanicNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        currentMechanicPanelLayout.setVerticalGroup(
            currentMechanicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mechanicPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(mechanicNameLabel)
        );

        fileMenu.setText("File");

        newGarageMenuItem.setText("New");
        newGarageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGarageMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newGarageMenuItem);
        fileMenu.add(jSeparator5);

        openMenuItem.setText("Load");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);
        fileMenu.add(jSeparator6);

        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setText("Save As");
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);
        fileMenu.add(jSeparator4);

        loginMenuItem.setText("Login");
        loginMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginMenuItemActionPerformed(evt);
            }
        });
        loginMenuItem.setEnabled(true);
        fileMenu.add(loginMenuItem);

        logoutMenuItem.setText("Logout");
        logoutMenuItem.setEnabled(true);
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(logoutMenuItem);
        fileMenu.add(fileMenuSeparator);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mechanicsNotebookMenuBar.add(fileMenu);

        optionsMenu.setText("Options");

        VehicleTrackersMenuItem.setText("Edit Vehicle Trackers");
        VehicleTrackersMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VehicleTrackersMenuItemActionPerformed(evt);
            }
        });
        optionsMenu.add(VehicleTrackersMenuItem);

        viewEditMaintenanceTypesMenuItem.setText("View / Edit General Maintenance Types");
        viewEditMaintenanceTypesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEditMaintenanceTypesMenuItemActionPerformed(evt);
            }
        });
        optionsMenu.add(viewEditMaintenanceTypesMenuItem);

        mechanicsNotebookMenuBar.add(optionsMenu);

        helpMenu.setText("Help");

        helpMenuItem.setText("Help");
        helpMenuItem.addActionListener(new OpenUrlAction());
        helpMenu.add(helpMenuItem);
        helpMenu.add(jSeparator1);

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        mechanicsNotebookMenuBar.add(helpMenu);

        setJMenuBar(mechanicsNotebookMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(currentMechanicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentCustomerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentVehiclePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(vehiclePanelNew, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mechanicPanelNew, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerPanelNew, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(currentCustomerPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentMechanicPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentVehiclePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mechanicPanelNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerPanelNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vehiclePanelNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainTabbedPane))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Private method used to OPEN a GARAGE!!
     * @param evt 
     */
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        JFileChooser chooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MotoGarage Notebook Save Files", "mnb");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showDialog(this, "Load Garage");

        File testFile = chooser.getSelectedFile();
        
        if(testFile != null && returnVal == 0){
            String filePath = testFile.getAbsolutePath();
            try {
                System.out.println(filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.motoGarageNotebookEngine.openGarage(testFile);
        }

    }//GEN-LAST:event_openMenuItemActionPerformed

    /**
     * User hit the Exit on the File Menu..
     * @param evt 
     */
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // KILL the program
        boolean sureToExit = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Exiting before saving will erase any progess made. Do you wish to continue?");
        if(sureToExit){
            System.exit(0);
        }        
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * Private Method to "Save" the garage, should:
     * <li> if current garage has never been saved, ie new garage, show save as menu
     * <li> if user opened an existing garage (or already save), should automatically save in last position
     * @param evt 
     */
    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
        if(this.motoGarageNotebookEngine.getSaveFile()!=null){
            File fileToSave = this.motoGarageNotebookEngine.getSaveFile();
            this.motoGarageNotebookEngine.saveGarage(fileToSave);
        }else{
            JFileChooser chooser = new JFileChooser();

            // .mnb FILTER
            // Set extension filter
            FileNameExtensionFilter filter = new FileNameExtensionFilter("MotoGarage Notebook Save Files", "mnb");
            chooser.setFileFilter(filter);

            int returnVal = chooser.showDialog(this, "Save Garage");
   
            // if returnVal == 0, user pressed accept
            if(returnVal==0){
                File testFile = chooser.getSelectedFile();
                this.motoGarageNotebookEngine.saveGarage(testFile);
            }
        }      
    }//GEN-LAST:event_saveMenuItemActionPerformed

    /**
     * User wants a new garage
     * 
     * @param evt 
     */
    private void newGarageMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGarageMenuItemActionPerformed
        // TODO add your handling code here:
        //this.motoGarageMechanicEngine.startNewGarageWindow();
        // Now it should 
        // <li> ask to save current data
        // <li> clear all data
        // <li> new garage object! nothing tied to it!
        
        if(this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Are you sure you wish to create a new garage? Any existing changes won't be saved!")){
           this.motoGarageNotebookEngine.createDefaultGarage(); 
           this.refresh();
        }
    }//GEN-LAST:event_newGarageMenuItemActionPerformed

    /**
     * Method used when a customer hits the About Menu Item
     * @param evt 
     */
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // Time to open the About Window
        this.motoGarageNotebookEngine.startAboutWindow(this);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    
    private void vehicleAddButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicleAddButtonNewActionPerformed
        // TODO add your handling code here:
        if(this.motoGarageNotebookEngine.getCurrentCustomer()== null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.WARNING_MESSAGE,"You must first have a Customer in order to add a Vehicle! Please create a Customer first.");
        }else{
             if(this.motoGarageNotebookEngine.hasVehicleModels()){
                 this.motoGarageNotebookEngine.startNewVehicleWindow(this);
             }else{
                 this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.WARNING_MESSAGE,"You must first have at least one Vehicle Model in order to add a Vehicle! Please create a Vehicle Model first.");
             }
        }
    }//GEN-LAST:event_vehicleAddButtonNewActionPerformed

    private void mechanicAddButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mechanicAddButtonNewActionPerformed
        // User hit create new Mechanic button, let's do it!
        this.motoGarageNotebookEngine.startNewMechanicWindow(this);
    }//GEN-LAST:event_mechanicAddButtonNewActionPerformed

    private void mechanicButtonEditNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mechanicButtonEditNewActionPerformed
        if(this.motoGarageNotebookEngine.getCurrentMechanic()==null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "You don't have a Mechanic to edit!");
        }else{
            this.motoGarageNotebookEngine.startUpdateMechanicWindow(this);
        }
    }//GEN-LAST:event_mechanicButtonEditNewActionPerformed

    private void mechanicDeleteButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mechanicDeleteButtonNewActionPerformed
        if(this.motoGarageNotebookEngine.getCurrentMechanic()== null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Mechanic to delete!");
        }else{
            boolean sureToDelete = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Are you sure you wish to delete the current Mechanic? This is permanent!");
            if(sureToDelete){
                this.motoGarageNotebookEngine.deleteCurrentMechanic();
            }            
        }
    }//GEN-LAST:event_mechanicDeleteButtonNewActionPerformed

    private void customerAddButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerAddButtonNewActionPerformed
        // User pressed the new customer button, time to enact
        this.motoGarageNotebookEngine.startNewCustomerWindow(this);
    }//GEN-LAST:event_customerAddButtonNewActionPerformed

    private void customerEditButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerEditButtonNewActionPerformed
        if(this.motoGarageNotebookEngine.getCurrentCustomer()==null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "You don't have a Customer to edit!");
        }else{
            this.motoGarageNotebookEngine.startUpdateCustomerWindow(this);
        }
    }//GEN-LAST:event_customerEditButtonNewActionPerformed

    private void customerDeleteButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerDeleteButtonNewActionPerformed
        // TODO add your handling code here:
        if(this.motoGarageNotebookEngine.getCurrentCustomer()== null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Customer to delete!");
        }else{
            boolean sureToDelete = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Are you sure you wish to delete the current Customer? This is permanent!");
            if(sureToDelete){
                this.motoGarageNotebookEngine.deleteCurrentCustomer();
            }            
        }
    }//GEN-LAST:event_customerDeleteButtonNewActionPerformed

    private void vehicleDeleteButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicleDeleteButtonNewActionPerformed
        // TODO add your handling code here:
        if(this.motoGarageNotebookEngine.getCurrentVehicle()== null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Vehicle to delete!");
        }else{
            boolean sureToDelete = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Are you sure you wish to delete the current Vehicle? This is permanent!");
            if(sureToDelete){
                this.motoGarageNotebookEngine.deleteCurrentVehicle();
            }            
        }
    }//GEN-LAST:event_vehicleDeleteButtonNewActionPerformed

    private void vehicleEditButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicleEditButtonNewActionPerformed
        // TODO add your handling code here:
        if(this.motoGarageNotebookEngine.getCurrentVehicle()==null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "You don't have a Vehicle to edit!");
        }else{
            this.motoGarageNotebookEngine.startUpdateVehicleWindow(this);
        }
    }//GEN-LAST:event_vehicleEditButtonNewActionPerformed

    private void mechanicsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mechanicsTableMouseClicked
        // TODO add your handling code here:
        int rowSelected = mechanicsTable.getSelectedRow();
            if(rowSelected>-1){
                Mechanic[] mechanics = motoGarageNotebookEngine.getMechanicArray();
                Mechanic selectedMechanic = mechanics[rowSelected];
                motoGarageNotebookEngine.setCurrentMechanic(selectedMechanic);
            }            
    }//GEN-LAST:event_mechanicsTableMouseClicked

    private void customersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customersTableMouseClicked
        // TODO add your handling code here:
        int rowSelected = customersTable.getSelectedRow();
            if(rowSelected>-1){
                Customer[] customers = motoGarageNotebookEngine.getCustomerArray();
                Customer selectedCustomer = customers[rowSelected];
                motoGarageNotebookEngine.setCurrentCustomer(selectedCustomer);
            }                      
    }//GEN-LAST:event_customersTableMouseClicked

    private void vehiclesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehiclesTableMouseClicked
        // TODO add your handling code here:
        int rowSelected = vehiclesTable.getSelectedRow();
            if(rowSelected>-1){
                Vehicle[] vehicles = motoGarageNotebookEngine.getVehicleArray();
                Vehicle selectedVehicle = vehicles[rowSelected];
                motoGarageNotebookEngine.setCurrentVehicle(selectedVehicle);
            }
    }//GEN-LAST:event_vehiclesTableMouseClicked

    private void updateOdometerActionButtonToolBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateOdometerActionButtonToolBarActionPerformed
        // TODO add your handling code here:
        if(this.motoGarageNotebookEngine.getCurrentVehicle()==null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.WARNING_MESSAGE,"You can't update mileage without a Vehicle!");
        }else{
            this.motoGarageNotebookEngine.startNewUpdateMileageWindow(this);
        }
    }//GEN-LAST:event_updateOdometerActionButtonToolBarActionPerformed

    private void addMaintenanceActionButtonToolBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaintenanceActionButtonToolBarActionPerformed
        // TODO add your handling code here:
        if(this.motoGarageNotebookEngine.getCurrentVehicle()==null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.WARNING_MESSAGE,"You must have a Vehicle to add a Maintenance Action!");
        }else if(this.motoGarageNotebookEngine.getCurrentMechanic() == null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.WARNING_MESSAGE,"You must have a current Mechanic to create a Maintenance Action!");
        }else if(!this.motoGarageNotebookEngine.hasMaintenanceTypes()){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.WARNING_MESSAGE,"You must have at least one Maintenance Type before adding a Maintenace Action! Please create a Maintenance Type first.");
        }else{
            this.motoGarageNotebookEngine.startNewMaintenanceActionWindow(this);
        }
    }//GEN-LAST:event_addMaintenanceActionButtonToolBarActionPerformed

    private void addMaintenanceActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaintenanceActionButtonActionPerformed
        // TODO add your handling code here:
        if(this.motoGarageNotebookEngine.getCurrentVehicle()==null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You must have a Vehicle to add a Maintenance Action!");
            return;
        }else if(this.motoGarageNotebookEngine.getCurrentMechanic() == null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You must have a current Mechanic to create a Maintenance Action!");
        }else if(!this.motoGarageNotebookEngine.hasMaintenanceTypes()){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You must have at least one Maintenance Type before adding a Maintenace Action! Please create a Maintenance Type first.");
        }
        else{
            this.motoGarageNotebookEngine.startNewMaintenanceActionWindow(this);
        }
    }//GEN-LAST:event_addMaintenanceActionButtonActionPerformed

    private void editMaintenanceTypesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMaintenanceTypesButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startMaintenanceTypesMainWindow(this);
    }//GEN-LAST:event_editMaintenanceTypesButtonActionPerformed

    private void editMaintenanceActionButtonToolBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMaintenanceActionButtonToolBarActionPerformed
        // TODO add your handling code here:
        int rowSelected = maintenanceActionsTable.getSelectedRow();

            if(rowSelected>-1){
                MaintenanceAction[] currentMaintenanceActions = this.motoGarageNotebookEngine.getCurrentVehicle().getMaintenanceActionsArray();
                MaintenanceAction selectedMaintenanceAction = currentMaintenanceActions[rowSelected];                
                this.motoGarageNotebookEngine.startMaintenanceActionWindow(this,selectedMaintenanceAction);
            }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Maintenance Action to Update.");
            }        
    }//GEN-LAST:event_editMaintenanceActionButtonToolBarActionPerformed

    private void deleteMaintenanceActionButtonToolBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMaintenanceActionButtonToolBarActionPerformed
        // TODO add your handling code here:
        int rowSelected = maintenanceActionsTable.getSelectedRow();        
        if(rowSelected>-1){
            boolean sureToDelete = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Are you sure you wish to delete the Maintenance Action? This is permanent!");
            if(sureToDelete){
                MaintenanceAction[] currentMaintenanceActions = this.motoGarageNotebookEngine.getCurrentVehicle().getMaintenanceActionsArray();
                MaintenanceAction selectedMaintenanceAction = currentMaintenanceActions[rowSelected]; 
                //this.motoGarageNotebookEngine.getCurrentVehicle().deleteMaintenanceAction(selectedMaintenanceAction);
                this.motoGarageNotebookEngine.deleteMaintenaceAction(selectedMaintenanceAction);
            }    
        }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Maintenance Action to Delete.");
            }  
        
    }//GEN-LAST:event_deleteMaintenanceActionButtonToolBarActionPerformed

    private void loadFromCloudButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFromCloudButtonActionPerformed
        // TODO add your handling code here:

        if(this.motoGarageNotebookEngine.getCurrentParseUser()==null){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this, DialogType.INFORMATION_MESSAGE, "There is no Cloud User logged in to import a Garage from the MotoGarage Cloud!");
        }else{
            boolean sureToDownload = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Downloading from the cloud will erase any unsaved progress. Do you wish to continue?");
            if(sureToDownload){
                this.motoGarageNotebookEngine.openFromCloud();
            }
        }
        
        
        //JFileChooser chooser = new JFileChooser();
        //chooser.setDialogTitle("Import Garage");
        
        
        //chooser.showOpenDialog(this);
        //File testFile = chooser.getSelectedFile();
        
        //if(testFile != null){
        //    String filePath = testFile.getAbsolutePath();
        //    try {
        //        System.out.println(filePath);
        //    } catch (Exception ex) {
        //        ex.printStackTrace();
        //    }
        //    this.motoGarageNotebookEngine.openGarage(testFile);
        //}
    }//GEN-LAST:event_loadFromCloudButtonActionPerformed

    /**
     * Method called when a user pressed the 'save to cloud' button
     * @param evt 
     */
    private void saveToCloudButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveToCloudButtonActionPerformed
        // check to ensure there is a logged in parse user first
        
        if(this.motoGarageNotebookEngine.getCurrentParseUser()!=null){
            // ensure there is a valid session token
            if(this.motoGarageNotebookEngine.getCurrentParseUser().getSessionToken()!=null){
                try {
                    //this.motoGarageNotebookEngine.startProgressDialogWindow("Uploading...");
                    try {
                        boolean sureToUpload = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Uploading to the cloud will update and overwrite your current garage. Do you wish to continue?");
                        
                        if(sureToUpload){
                            //this.motoGarageNotebookEngine.startProgressDialogWindow("Uploading...");
//            if(1==1){
//                return;
//            }     
                            //this.motoGarageNotebookEngine.startProgressDialogWindow("Uploading...");
                            this.motoGarageNotebookEngine.saveToCloud();
                        }
                        
                    } catch (FileNotFoundException | ClassNotFoundException ex) {
                        //Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException | ParseException ex) {
                    //Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println("User doesn't have session token?");
            }
        }else{
            System.out.println("User is NULL?!");
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this, DialogType.INFORMATION_MESSAGE, "There is no Cloud User logged in to save a Garage to the MotoGarage Cloud!");
        }
        //JFileChooser chooser = new JFileChooser();
        //chooser.setDialogTitle("Export Garage");
        //chooser.showSaveDialog(this);
        
        //if(chooser.getSelectedFile() != null){
        //    File testFile = chooser.getSelectedFile();
        //    String filePath = testFile.getAbsolutePath();
        //    try {
        //        System.out.println(filePath);
        //    } catch (Exception ex) {
        //        System.out.println("dialog must have closed?");
        //        ex.printStackTrace();
        //    }
        //    this.motoGarageNotebookEngine.saveGarage(testFile);
        //}
    }//GEN-LAST:event_saveToCloudButtonActionPerformed

    private void fuelEntryAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fuelEntryAddButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startNewFuelEntryWindow(this);
    }//GEN-LAST:event_fuelEntryAddButtonActionPerformed

    private void fuelEntryEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fuelEntryEditButtonActionPerformed
        // TODO add your handling code here:
        int rowSelected = fuelEntriesTable.getSelectedRow();
            if(rowSelected>-1){
                FuelEntry[] currentFuelEntries = this.motoGarageNotebookEngine.getCurrentVehicle().getFuelEntriesArray();
                FuelEntry selectedFuelEntry = currentFuelEntries[rowSelected];                
                this.motoGarageNotebookEngine.startViewOrEditFuelEntryWindow(this,selectedFuelEntry);
            }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Fuel Entry to Update.");
            } 
    }//GEN-LAST:event_fuelEntryEditButtonActionPerformed

    private void fuelEntryDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fuelEntryDeleteButtonActionPerformed
        // TODO add your handling code here:
        int rowSelected = fuelEntriesTable.getSelectedRow();        
        if(rowSelected>-1){
            boolean sureToDelete = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Are you sure you wish to delete the Fuel Entry? This is permanent!");
            if(sureToDelete){
                FuelEntry[] currentFuelEntries = this.motoGarageNotebookEngine.getCurrentVehicle().getFuelEntriesArray();
                FuelEntry selectedFuelEntry = currentFuelEntries[rowSelected]; 
                //this.motoGarageNotebookEngine.getCurrentVehicle().deleteMaintenanceAction(selectedMaintenanceAction);
                this.motoGarageNotebookEngine.deleteFuelEntry(selectedFuelEntry);
            }    
        }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Maintenance Action to Delete.");
            }  
    }//GEN-LAST:event_fuelEntryDeleteButtonActionPerformed

    private void addWarrantyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addWarrantyButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startNewWarrantyWindow(this);
    }//GEN-LAST:event_addWarrantyButtonActionPerformed

    private void deleteWarrantyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteWarrantyButtonActionPerformed
        // TODO add your handling code here:
        int rowSelected = warrantiesTable.getSelectedRow();        
        if(rowSelected>-1){
            boolean sureToDelete = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Are you sure you wish to delete the Warranty? This is permanent!");
            if(sureToDelete){
                Warranty[] currentWarranties = this.motoGarageNotebookEngine.getCurrentVehicle().getWarrantyArray();
                Warranty selectedWarranty = currentWarranties[rowSelected]; 
                //this.motoGarageNotebookEngine.getCurrentVehicle().deleteMaintenanceAction(selectedMaintenanceAction);
                this.motoGarageNotebookEngine.deleteWarranty(selectedWarranty);
            }    
        }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Warranty to Delete.");
            }  
    }//GEN-LAST:event_deleteWarrantyButtonActionPerformed

    private void editWarrantyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editWarrantyButtonActionPerformed
        // TODO add your handling code here:
              int rowSelected = warrantiesTable.getSelectedRow();
            if(rowSelected>-1){
                Warranty[] currentWarranties = this.motoGarageNotebookEngine.getCurrentVehicle().getWarrantyArray();
                Warranty selectedWarranty = currentWarranties[rowSelected];                
                this.motoGarageNotebookEngine.startViewOrEditWarrantyWindow(this,selectedWarranty);
            }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Warranty to View / Update.");
            } 
    }//GEN-LAST:event_editWarrantyButtonActionPerformed

    private void addModificationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addModificationButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startNewModificationWindow(this);
    }//GEN-LAST:event_addModificationButtonActionPerformed

    private void deleteModificationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteModificationButtonActionPerformed
        // TODO add your handling code here:
        int rowSelected = modificationsTable.getSelectedRow();        
        if(rowSelected>-1){
            boolean sureToDelete = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Are you sure you wish to delete the Modification? This is permanent!");
            if(sureToDelete){
                Modification[] currentModifications = this.motoGarageNotebookEngine.getCurrentVehicle().getModificationArray();
                Modification selectedModification = currentModifications[rowSelected]; 

                this.motoGarageNotebookEngine.deleteModification(selectedModification);
            }    
        }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Modification to Delete.");
            }  
    }//GEN-LAST:event_deleteModificationButtonActionPerformed

    private void editModificationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editModificationButtonActionPerformed
        // TODO add your handling code here:
            int rowSelected = modificationsTable.getSelectedRow();
            if(rowSelected>-1){
                Modification[] currentModifications = this.motoGarageNotebookEngine.getCurrentVehicle().getModificationArray();
                Modification selectedModification = currentModifications[rowSelected];                
                this.motoGarageNotebookEngine.startViewOrEditModificationWindow(this,selectedModification);
            }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Modification to View / Update.");
            } 
    }//GEN-LAST:event_editModificationButtonActionPerformed

    private void VehicleTrackersMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VehicleTrackersMenuItemActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startVehicleTrackersWindow(this);
    }//GEN-LAST:event_VehicleTrackersMenuItemActionPerformed

    private void dragStripSlipAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dragStripSlipAddButtonActionPerformed
        // TODO add your handling code here:

        this.motoGarageNotebookEngine.startNewDragStripSlipWindow(this);
    }//GEN-LAST:event_dragStripSlipAddButtonActionPerformed

    private void dragStripSlipDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dragStripSlipDeleteButtonActionPerformed
        // TODO add your handling code here:
        int rowSelected = dragStripSlipsTable.getSelectedRow();        
        if(rowSelected>-1){
            boolean sureToDelete = this.motoGarageNotebookEngine.getDialogFactory().createConfirmMessage(this,"Are you sure you wish to delete the Drag Strip Slip? This is permanent!");
            if(sureToDelete){
                DragStripSlip[] currentDragStripSlips = this.motoGarageNotebookEngine.getCurrentVehicle().getDragStripSlipArray();
                DragStripSlip selectedDragStripSlip = currentDragStripSlips[rowSelected]; 

                this.motoGarageNotebookEngine.deleteDragStripSlip(selectedDragStripSlip);
            }    
        }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Drag Strip Slip to Delete.");
            }  
    }//GEN-LAST:event_dragStripSlipDeleteButtonActionPerformed

    private void dragStripSlipEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dragStripSlipEditButtonActionPerformed
        // TODO add your handling code here:
            int rowSelected = dragStripSlipsTable.getSelectedRow();
            if(rowSelected>-1){
                DragStripSlip[] currentDragStripSlips = this.motoGarageNotebookEngine.getCurrentVehicle().getDragStripSlipArray();
                DragStripSlip selectedDragStripSlip = currentDragStripSlips[rowSelected];                
                this.motoGarageNotebookEngine.startUpdateDragStripSlipWindow(this,selectedDragStripSlip);
            }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE,"You have not selected a Drag Strip Slip to View / Update.");
            } 
    }//GEN-LAST:event_dragStripSlipEditButtonActionPerformed

    private void fuelEntryMainToolBarAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fuelEntryMainToolBarAddButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startNewFuelEntryWindow(this);
    }//GEN-LAST:event_fuelEntryMainToolBarAddButtonActionPerformed

    private void graphsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphsButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startVehicleInformationGraphs(this);
    }//GEN-LAST:event_graphsButtonActionPerformed

    private void mechanicsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mechanicsTableMouseReleased
        // TODO add your handling code here:
                // TODO add your handling code here:
        int rowSelected = mechanicsTable.getSelectedRow();
            if(rowSelected>-1){
                Mechanic[] mechanics = motoGarageNotebookEngine.getMechanicArray();
                Mechanic selectedMechanic = mechanics[rowSelected];
                motoGarageNotebookEngine.setCurrentMechanic(selectedMechanic);
            }  
    }//GEN-LAST:event_mechanicsTableMouseReleased

    private void customersTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customersTableMouseReleased
        // TODO add your handling code here:
        int rowSelected = customersTable.getSelectedRow();
        if(rowSelected>-1){
            Customer[] customers = motoGarageNotebookEngine.getCustomerArray();
            Customer selectedCustomer = customers[rowSelected];
            motoGarageNotebookEngine.setCurrentCustomer(selectedCustomer);
        }    
    }//GEN-LAST:event_customersTableMouseReleased

    private void vehiclesTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehiclesTableMouseReleased
        // TODO add your handling code here:
        int rowSelected = vehiclesTable.getSelectedRow();
            if(rowSelected>-1){
                Vehicle[] vehicles = motoGarageNotebookEngine.getVehicleArray();
                Vehicle selectedVehicle = vehicles[rowSelected];
                motoGarageNotebookEngine.setCurrentVehicle(selectedVehicle);
            }
    }//GEN-LAST:event_vehiclesTableMouseReleased

    private void loginMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginMenuItemActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startNewCloudUserLoginCreationWindow(this);
    }//GEN-LAST:event_loginMenuItemActionPerformed

    private void viewEditMaintenanceTypesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEditMaintenanceTypesMenuItemActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startMaintenanceTypesMainWindow(this);
    }//GEN-LAST:event_viewEditMaintenanceTypesMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        // TODO add your handling code here:

        JFileChooser chooser = new JFileChooser();
        //chooser.showSaveDialog(this);
        //chooser.setDialogTitle("Save Garage");
        
        
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MotoGarage Notebook Save Files", "mnb");
        chooser.setFileFilter(filter);
        
        int returnVal = chooser.showDialog(this, "Save Garage");
        
        if(chooser.getSelectedFile() != null && returnVal ==0){
            File testFile = chooser.getSelectedFile();
            String filePath = testFile.getAbsolutePath();
            // TRIM .mnb if already exists

            this.motoGarageNotebookEngine.saveGarage(testFile);
        }
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void editVehicleModelsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVehicleModelsButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startVehicleModelsMainWindow(this);
        
    }//GEN-LAST:event_editVehicleModelsButtonActionPerformed

    private void logoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMenuItemActionPerformed
        try {
            // TODO add your handling code here:
            this.motoGarageNotebookEngine.signOutUser();
        } catch (ParseException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_logoutMenuItemActionPerformed

    private void vehicleMaintenanceTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicleMaintenanceTypeButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startVehicleMaintenaceTypesMainWindow(this);
    }//GEN-LAST:event_vehicleMaintenanceTypeButtonActionPerformed

    private void maintenanceAlertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintenanceAlertButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageNotebookEngine.startMaintenanceActionsOverdueWindow(this);
    }//GEN-LAST:event_maintenanceAlertButtonActionPerformed

    private void openGarageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openGarageButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("MotoGarage Notebook Save Files", "mnb");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showDialog(this, "Load Garage");

        File testFile = chooser.getSelectedFile();
        
        if(testFile != null && returnVal == 0){
            String filePath = testFile.getAbsolutePath();
            try {
                System.out.println(filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.motoGarageNotebookEngine.openGarage(testFile);
        }

    }//GEN-LAST:event_openGarageButtonActionPerformed

    private void saveGarageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveGarageButtonActionPerformed
        // TODO add your handling code here:
        if(this.motoGarageNotebookEngine.getSaveFile()!=null){
            File fileToSave = this.motoGarageNotebookEngine.getSaveFile();
            this.motoGarageNotebookEngine.saveGarage(fileToSave);
        }else{
            JFileChooser chooser = new JFileChooser();

            // .mnb FILTER
            // Set extension filter
            FileNameExtensionFilter filter = new FileNameExtensionFilter("MotoGarage Notebook Save Files", "mnb");
            chooser.setFileFilter(filter);

            int returnVal = chooser.showDialog(this, "Save Garage");
   
            // if returnVal == 0, user pressed accept
            if(returnVal==0){
                File testFile = chooser.getSelectedFile();
                this.motoGarageNotebookEngine.saveGarage(testFile);
            }
        }  
    }//GEN-LAST:event_saveGarageButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws URISyntaxException{
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new MainWindow().setVisible(true);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem VehicleTrackersMenuItem;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addMaintenanceActionButton;
    private javax.swing.JButton addMaintenanceActionButtonToolBar;
    private javax.swing.JButton addModificationButton;
    private javax.swing.JButton addWarrantyButton;
    private javax.swing.JPanel currentCustomerPanel;
    private javax.swing.JPanel currentMechanicPanel;
    private javax.swing.JLabel currentVehicleOdometerLabel;
    private javax.swing.JPanel currentVehiclePanel;
    private javax.swing.JButton customerAddButtonNew;
    private javax.swing.JButton customerDeleteButtonNew;
    private javax.swing.JButton customerEditButtonNew;
    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JPanel customerPanelNew;
    private javax.swing.JLabel customerPictureLabel;
    private javax.swing.JToolBar customerToolBar;
    private javax.swing.JTable customersTable;
    private javax.swing.JButton deleteMaintenanceActionButtonToolBar;
    private javax.swing.JButton deleteModificationButton;
    private javax.swing.JButton deleteWarrantyButton;
    private javax.swing.JButton dragStripSlipAddButton;
    private javax.swing.JButton dragStripSlipDeleteButton;
    private javax.swing.JButton dragStripSlipEditButton;
    private javax.swing.JToolBar dragStripSlipToolBar;
    private javax.swing.JPanel dragStripSlipsPanel;
    private javax.swing.JTable dragStripSlipsTable;
    private javax.swing.JButton editMaintenanceActionButtonToolBar;
    private javax.swing.JButton editMaintenanceTypesButton;
    private javax.swing.JButton editModificationButton;
    private javax.swing.JButton editVehicleModelsButton;
    private javax.swing.JButton editWarrantyButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator;
    private javax.swing.JLabel fiveOdoLabel;
    private javax.swing.JLabel fourOdoLabel;
    private javax.swing.JPanel fuelEntriesPanel;
    private javax.swing.JTable fuelEntriesTable;
    private javax.swing.JButton fuelEntryAddButton;
    private javax.swing.JButton fuelEntryDeleteButton;
    private javax.swing.JButton fuelEntryEditButton;
    private javax.swing.JButton fuelEntryMainToolBarAddButton;
    private javax.swing.JToolBar fuelEntryToolBar;
    private javax.swing.JButton graphsButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JButton loadFromCloudButton;
    private javax.swing.JMenuItem loginMenuItem;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JToolBar mainToolBar;
    private javax.swing.JTable maintenanceActionsTable;
    private javax.swing.JToolBar maintenanceActionsToolBar;
    private javax.swing.JButton maintenanceAlertButton;
    private javax.swing.JButton mechanicAddButtonNew;
    private javax.swing.JButton mechanicButtonEditNew;
    private javax.swing.JButton mechanicDeleteButtonNew;
    private javax.swing.JLabel mechanicNameLabel;
    private javax.swing.JPanel mechanicPanelNew;
    private javax.swing.JLabel mechanicPictureLabel;
    private javax.swing.JToolBar mechanicToolBar;
    private javax.swing.JMenuBar mechanicsNotebookMenuBar;
    private javax.swing.JTable mechanicsTable;
    private javax.swing.JToolBar modificationEntryToolBar;
    private javax.swing.JPanel modificationsPanel;
    private javax.swing.JTable modificationsTable;
    private javax.swing.JMenuItem newGarageMenuItem;
    private javax.swing.JPanel odometerPanel;
    private javax.swing.JLabel oneOdoLabel;
    private javax.swing.JButton openGarageButton;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JButton saveGarageButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton saveToCloudButton;
    private javax.swing.JLabel sixOdoLabel;
    private javax.swing.JLabel threeOdoLabel;
    private javax.swing.JLabel twoOdoLabel;
    private javax.swing.JButton updateOdometerActionButtonToolBar;
    private javax.swing.JButton vehicleAddButtonNew;
    private javax.swing.JButton vehicleDeleteButtonNew;
    private javax.swing.JButton vehicleEditButtonNew;
    private javax.swing.JPanel vehicleMaintenanceActionsPanel;
    private javax.swing.JButton vehicleMaintenanceTypeButton;
    private javax.swing.JLabel vehicleNameLabel;
    private javax.swing.JPanel vehiclePanelNew;
    private javax.swing.JLabel vehiclePictureLabel;
    private javax.swing.JToolBar vehicleToolBar;
    private javax.swing.JPanel vehicleWarrantiesPanel;
    private javax.swing.JTable vehiclesTable;
    private javax.swing.JMenuItem viewEditMaintenanceTypesMenuItem;
    private javax.swing.JTable warrantiesTable;
    private javax.swing.JToolBar warrantyEntryToolBar;
    // End of variables declaration//GEN-END:variables
}
