/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;


import customclasses.ButtonActions;
import customclasses.VehicleButton;
import java.io.File;
import javax.swing.JFileChooser;
import objectmodels.Mechanic;
import engine.MechanicsNotebookEngine;
import informationwindows.DialogType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import objectmodels.Customer;
import objectmodels.MaintenanceAction;
import objectmodels.MaintenanceType;
import objectmodels.MouseAdapter2;
import objectmodels.Vehicle;

/**
 *
 * @author Mark
 */
public class MainWindow extends javax.swing.JFrame {
    private MechanicsNotebookEngine mechanicsNotebookEngine;
    //DefaultListModel model = new DefaultListModel();
    DefaultTableModel tableModel = new DefaultTableModel();
    //JList maintenanceActionList2;
    
    // borders for panels
    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
    Border compound = BorderFactory.createCompoundBorder(
                          raisedbevel, loweredbevel);
    
    // variables
    int maxColumnWidth = 65;

    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }
    
     /**
     * Creates new form MainWindow
     */
    public MainWindow(MechanicsNotebookEngine incomingMotoGarageMechanicEngine) {
        this.mechanicsNotebookEngine = incomingMotoGarageMechanicEngine;
        initComponents();   
        //this.getContentPane().setBackground(Color.black);
        //this.mainTabbedPane.setBackground(Color.black);
        //this.mechanicsPanel.setBackground(Color.black);
        this.setIcon();
        
        
        // mechanics table model
    DefaultTableModel vehiclesTableModel = new DefaultTableModel(
        new Object [][] {
        },
        new String [] {
            "Make", "Model", "Year", "Odometer"
        }) {           
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };
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
    mechanicsTable.setModel(mechanicsTableModel);
        
        
        DefaultTableModel maintenanceActionsTableModel = new DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Odometer", "Maintenance Type", "Notes", "Performed By"
            }
                
                ) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
    };
    maintenanceActionsTable.setModel(maintenanceActionsTableModel);
    
    // end main constructor
    }
    
    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mechanicIcon.png")));
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
        
    }
    

    private void refreshVehiclesTab(){
        this.refreshMaintenanceActions();
        this.refreshVehicleList();
        this.refreshCurrentVehicleInformation();
    }
    
    /**
     * One of the most important Refresh methods, refreshes all the maintenance actions for current vehicle
     */
    private void refreshMaintenanceActions(){
        
        DefaultTableModel model = (DefaultTableModel) maintenanceActionsTable.getModel();
        maintenanceActionsTable.getColumnModel().getColumn(0).setMaxWidth(maxColumnWidth);
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        if(this.mechanicsNotebookEngine.getCurrentVehicle()!=null && this.mechanicsNotebookEngine.getCurrentVehicle().getSortedMaintenanceActionsArray().length >0){
            addMaintenanceActionButtonToolBar.setEnabled(true);
            editMaintenanceActionButtonToolBar.setEnabled(true);
            deleteMaintenanceActionButtonToolBar.setEnabled(true);
            updateOdometerActionButtonToolBar.setEnabled(true);
            int newRowCount = this.mechanicsNotebookEngine.getCurrentVehicle().getSortedMaintenanceActionsArray().length;
            MaintenanceAction[] maintenanceArray = this.mechanicsNotebookEngine.getCurrentVehicle().getSortedMaintenanceActionsArray();
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]maintenanceActionObject = maintenanceArray[i].getMaintenaceActionObject();

                model.addRow(maintenanceActionObject);                
            }
        }else if(this.mechanicsNotebookEngine.getCurrentVehicle()!=null){
            addMaintenanceActionButtonToolBar.setEnabled(true);
            updateOdometerActionButtonToolBar.setEnabled(true);
            
            editMaintenanceActionButtonToolBar.setEnabled(false);
            deleteMaintenanceActionButtonToolBar.setEnabled(false);            
        }else{
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
        System.out.println("REFRESH MECHINCS LIST BEING CALLED");
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
        if(this.mechanicsNotebookEngine.getMechanicArray().length >0){
            mechanicButtonEditNew.setEnabled(true);
            mechanicDeleteButtonNew.setEnabled(true);
            Mechanic[] mechanics = this.mechanicsNotebookEngine.getMechanicArray();
            int newRowCount = mechanics.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]mechanicObject = mechanics[i].getMechanicObject();
                model.addRow(mechanicObject);       
            }
            // since there ARE mechanics, let's ensure the 'selected' remains selected
            if(currentRowSelected>-1 && this.mechanicsNotebookEngine.getCurrentMechanic()!=null){
                ListSelectionModel selectionModel =mechanicsTable.getSelectionModel();
                selectionModel.setSelectionInterval(currentRowSelected, currentRowSelected);

            }else if((currentRowSelected==-1) &&this.mechanicsNotebookEngine.getCurrentMechanic()!=null ){
                ListSelectionModel selectionModel =mechanicsTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                //currentRowSelected = mechanicsTable.getSelectedRow();
                this.mechanicsNotebookEngine.setCurrentMechanic(mechanics[0]);
            }else if(currentRowSelected==-1 && this.mechanicsNotebookEngine.getCurrentMechanic() == null){
                ListSelectionModel selectionModel =mechanicsTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                // = mechanicsTable.getSelectedRow();
                this.mechanicsNotebookEngine.setCurrentMechanic(mechanics[0]);
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
        System.out.println("REFRESH VEHICLE  LIST BEING CALLED");
        DefaultTableModel model = (DefaultTableModel) vehiclesTable.getModel();
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        Integer currentRowSelected = vehiclesTable.getSelectedRow();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        // time to add the vehicles
        if(this.mechanicsNotebookEngine.getCurrentCustomer()!=null &&this.mechanicsNotebookEngine.getCurrentCustomer().getVehicleArray().length >0){
            vehicleAddButtonNew.setEnabled(true);
            vehicleEditButtonNew.setEnabled(true);
            vehicleDeleteButtonNew.setEnabled(true);
            Vehicle[] vehicles = this.mechanicsNotebookEngine.getVehicleArray();
            int newRowCount = vehicles.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]vehicleObject = vehicles[i].getVehicleObject();
                model.addRow(vehicleObject);                      
                }
            
            if(this.mechanicsNotebookEngine.getVehicleArray().length >0){
            //Vehicle[] vehicle = this.mechanicsNotebookEngine.getVehicleArray();  //?????????????????????          
            // since there ARE customers, let's ensure the 'selected' remains selected
            if(currentRowSelected==-1 && this.mechanicsNotebookEngine.getCurrentVehicle()!=null){
                ListSelectionModel selectionModel =vehiclesTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
            }else if((currentRowSelected>-1) && this.mechanicsNotebookEngine.getCurrentVehicle()!=null ){
                ListSelectionModel selectionModel =vehiclesTable.getSelectionModel();
                selectionModel.setSelectionInterval(currentRowSelected, currentRowSelected);
            }else if(currentRowSelected==-1 && this.mechanicsNotebookEngine.getCurrentVehicle() == null){
                ListSelectionModel selectionModel =vehiclesTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                this.mechanicsNotebookEngine.setCurrentVehicle(vehicles[0]);
                }
            }
        }else if(this.mechanicsNotebookEngine.getCurrentCustomer()!=null &&this.mechanicsNotebookEngine.getCurrentCustomer().getVehicleArray().length ==0){
            vehicleAddButtonNew.setEnabled(true);
            vehicleEditButtonNew.setEnabled(false);
            vehicleDeleteButtonNew.setEnabled(false);
        }else if(this.mechanicsNotebookEngine.getCurrentCustomer()==null){
            vehicleAddButtonNew.setEnabled(false);
            vehicleEditButtonNew.setEnabled(false);
            vehicleDeleteButtonNew.setEnabled(false);
        }
    }
    
    
    /**
     * Method used to refresh the entire Customers Tabs
     */
    private void refreshCustomersTab(){
        this.refreshCustomerList();
        this.refreshCurrentCustomerInformation();
    }
    
    /**
     * Method used to update the Customer List (JTable)
     */
    private void refreshCustomerList(){
        System.out.println("REFRESH CUSTOMER  LIST BEING CALLED");
        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        Integer currentRowSelected = customersTable.getSelectedRow();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        if(this.mechanicsNotebookEngine.getCustomerArray().length >0){
            customerEditButtonNew.setEnabled(true);
            customerDeleteButtonNew.setEnabled(true);
            Customer[] customers = this.mechanicsNotebookEngine.getCustomerArray();
            int newRowCount = customers.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]customerObject = customers[i].getCustomerObject();
                model.addRow(customerObject);                      
            }
            
            // since there ARE customers, let's ensure the 'selected' remains selected
            if(currentRowSelected==-1 && this.mechanicsNotebookEngine.getCurrentCustomer()!=null){
                ListSelectionModel selectionModel =customersTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
            }else if((currentRowSelected>-1) && this.mechanicsNotebookEngine.getCurrentCustomer()!=null ){
                ListSelectionModel selectionModel =customersTable.getSelectionModel();
                selectionModel.setSelectionInterval(currentRowSelected, currentRowSelected);
            }else if(currentRowSelected==-1 && this.mechanicsNotebookEngine.getCurrentCustomer() == null){
                ListSelectionModel selectionModel =customersTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                this.mechanicsNotebookEngine.setCurrentCustomer(customers[0]);
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
        this.refreshCurrentMechanicInformation();
        this.refreshMechanicsList();
    }
    
    private void refreshCurrentMechanicInformation(){
        if(this.mechanicsNotebookEngine.getCurrentMechanic()!=null){
            Mechanic currentMechanic= this.mechanicsNotebookEngine.getCurrentMechanic();
            if(currentMechanic.getImageIcon()!=null){               
                ImageIcon imageIcon = this.mechanicsNotebookEngine.getCurrentMechanic().getImageIcon(); // load the image to a imageIcon
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
        if(this.mechanicsNotebookEngine.getCurrentCustomer()!=null){
            Customer currentCustomer= this.mechanicsNotebookEngine.getCurrentCustomer();
            if(currentCustomer.getImageIcon()!=null){               
                ImageIcon imageIcon = this.mechanicsNotebookEngine.getCurrentCustomer().getImageIcon(); // load the image to a imageIcon
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
        if(this.mechanicsNotebookEngine.getCurrentVehicle()!=null){
            Vehicle currentVehicle= this.mechanicsNotebookEngine.getCurrentVehicle();
            if(currentVehicle.getImageIcon()!=null){               
                ImageIcon imageIcon = this.mechanicsNotebookEngine.getCurrentVehicle().getImageIcon(); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it 
                Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                imageIcon = new ImageIcon(newimg);  // transform it back
                this.vehiclePictureLabel.setIcon(imageIcon);
            }else{
                this.vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N);
            }
            this.vehicleNameLabel.setText(currentVehicle.getYear().toString() + " " +currentVehicle.getMake() + " " + currentVehicle.getModel());
            this.currentVehicleOdometerLabel.setText("Odometer: " + currentVehicle.getOdometer().toString());
        }else{  
            this.vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N);
            this.vehicleNameLabel.setText("No Vehicle Selected");
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
        jSeparator1 = new javax.swing.JSeparator();
        ImageIcon updateOdometerActionToolBar = new ImageIcon(getClass().getResource("/odometer32x32ADD.png"));
        Action actionUpdateOdometerActionToolBar = new AbstractAction("New", updateOdometerActionToolBar) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ODOMETER UPDATE");
            }
        };
        updateOdometerActionButtonToolBar = maintenanceActionsToolBar.add(actionUpdateOdometerActionToolBar);
        fuelEntriesPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        fuelEntriesTable = new javax.swing.JTable();
        fuelEntryToolBar = new javax.swing.JToolBar();
        fuelEntryAddButton = new javax.swing.JButton();
        fuelEntryEditButton = new javax.swing.JButton();
        fuelEntryDeleteButton = new javax.swing.JButton();
        vehicleWarrantiesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        modificationsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        mainToolBar = new javax.swing.JToolBar();
        ImageIcon maintenanceActionAdd = new ImageIcon(getClass().getResource("/maintenanceAction32x32ADD.png"));
        Action actionMaintenanceActionAdd = new AbstractAction("New", maintenanceActionAdd) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("MaintenanceActionAdd add");
            }
        };
        addMaintenanceActionButton = mainToolBar.add(actionMaintenanceActionAdd);
        maintenanceAlertButton = new javax.swing.JButton();
        ImageIcon maintenanceTypeEdit = new ImageIcon(getClass().getResource("/maintenanceType32x32EDIT.png"));
        Action actionMaintenanceTypeEdit = new AbstractAction("New", maintenanceTypeEdit) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("MaintenanceTypes Edid!");
            }
        };
        editMaintenanceTypesButton = mainToolBar.add(actionMaintenanceTypeEdit);
        fuelEntryMainToolBarAddButton = new javax.swing.JButton();
        importGarageButton = new javax.swing.JButton();
        exportGarageButton = new javax.swing.JButton();
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
        mechanicsLabel = new javax.swing.JLabel();
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
        customersLabel = new javax.swing.JLabel();
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
        vehiclesLabel = new javax.swing.JLabel();
        currentVehiclePanel = new javax.swing.JPanel();
        vehiclePictureLabel = new javax.swing.JLabel();
        currentVehicleLabel = new javax.swing.JLabel();
        vehicleNameLabel = new javax.swing.JLabel();
        currentVehicleOdometerLabel = new javax.swing.JLabel();
        currentCustomerPanel = new javax.swing.JPanel();
        customerPictureLabel = new javax.swing.JLabel();
        customerNameLabel = new javax.swing.JLabel();
        currentCustomerLabel = new javax.swing.JLabel();
        currentMechanicPanel = new javax.swing.JPanel();
        mechanicNameLabel = new javax.swing.JLabel();
        mechanicPictureLabel = new javax.swing.JLabel();
        currentMechanicLabel = new javax.swing.JLabel();
        mechanicsNotebookMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newGarageMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        fileMenuSeparator = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mechanic's Notebook");
        setMinimumSize(new java.awt.Dimension(800, 600));

        maintenanceActionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Odometer", "Maintenance Type", "Notes", "Performed By"
            }
        ));
        maintenanceActionsTable.setOpaque(true);
        maintenanceActionsTable.setFillsViewportHeight(true);
        maintenanceActionsTable.setBackground(Color.WHITE);
        maintenanceActionsTable.getColumnModel().getColumn(1).setMaxWidth(6);
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

        editMaintenanceActionButtonToolBar.setToolTipText("Edit Maintenance Action");
        editMaintenanceActionButtonToolBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32EDIT.png"))); // NOI18N
        editMaintenanceActionButtonToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMaintenanceActionButtonToolBarActionPerformed(evt);
            }
        });
        maintenanceActionsToolBar.add(editMaintenanceActionButtonToolBar);

        deleteMaintenanceActionButtonToolBar.setToolTipText("Delete Maintenance Action");
        deleteMaintenanceActionButtonToolBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32DELETE.png"))); // NOI18N
        deleteMaintenanceActionButtonToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMaintenanceActionButtonToolBarActionPerformed(evt);
            }
        });
        maintenanceActionsToolBar.add(deleteMaintenanceActionButtonToolBar);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(compound);

        Dimension size = new Dimension(
            2,
            jSeparator1.getMaximumSize().height);
        jSeparator1.setMaximumSize(size);
        maintenanceActionsToolBar.add(jSeparator1);

        updateOdometerActionButtonToolBar.setToolTipText("Update Odometer");
        updateOdometerActionButtonToolBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/odometer32x32ADD.png"))); // NOI18N
        updateOdometerActionButtonToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateOdometerActionButtonToolBarActionPerformed(evt);
            }
        });
        maintenanceActionsToolBar.add(updateOdometerActionButtonToolBar);

        javax.swing.GroupLayout vehicleMaintenanceActionsPanelLayout = new javax.swing.GroupLayout(vehicleMaintenanceActionsPanel);
        vehicleMaintenanceActionsPanel.setLayout(vehicleMaintenanceActionsPanelLayout);
        vehicleMaintenanceActionsPanelLayout.setHorizontalGroup(
            vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maintenanceActionsToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        vehicleMaintenanceActionsPanelLayout.setVerticalGroup(
            vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maintenanceActionsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Maintenance Actions", vehicleMaintenanceActionsPanel);

        fuelEntriesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Odometer", "Gallons", "Cost Per Gallon"
            }
        ));
        fuelEntriesTable.setOpaque(true);
        fuelEntriesTable.setFillsViewportHeight(true);
        fuelEntriesTable.setBackground(Color.WHITE);
        jScrollPane4.setViewportView(fuelEntriesTable);

        fuelEntryToolBar.setRollover(true);
        fuelEntryToolBar.setFloatable(false);

        fuelEntryAddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fuelEntry32x32ADD.png"))); // NOI18N
        fuelEntryAddButton.setToolTipText("Add Fuel Entry");
        fuelEntryToolBar.add(fuelEntryAddButton);

        fuelEntryEditButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fuelEntry32x32EDIT.png"))); // NOI18N
        fuelEntryEditButton.setToolTipText("View / Edit Selected Fuel Entry");
        fuelEntryToolBar.add(fuelEntryEditButton);

        fuelEntryDeleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fuelEntry32x32DELETE.png"))); // NOI18N
        fuelEntryDeleteButton.setToolTipText("Delete Selected Fuel Entry");
        fuelEntryToolBar.add(fuelEntryDeleteButton);

        javax.swing.GroupLayout fuelEntriesPanelLayout = new javax.swing.GroupLayout(fuelEntriesPanel);
        fuelEntriesPanel.setLayout(fuelEntriesPanelLayout);
        fuelEntriesPanelLayout.setHorizontalGroup(
            fuelEntriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addGroup(fuelEntriesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fuelEntryToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        fuelEntriesPanelLayout.setVerticalGroup(
            fuelEntriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fuelEntriesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fuelEntryToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Fuel Entries", fuelEntriesPanel);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout vehicleWarrantiesPanelLayout = new javax.swing.GroupLayout(vehicleWarrantiesPanel);
        vehicleWarrantiesPanel.setLayout(vehicleWarrantiesPanelLayout);
        vehicleWarrantiesPanelLayout.setHorizontalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );
        vehicleWarrantiesPanelLayout.setVerticalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehicleWarrantiesPanelLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        mainTabbedPane.addTab("Warranties", vehicleWarrantiesPanel);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout modificationsPanelLayout = new javax.swing.GroupLayout(modificationsPanel);
        modificationsPanel.setLayout(modificationsPanelLayout);
        modificationsPanelLayout.setHorizontalGroup(
            modificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );
        modificationsPanelLayout.setVerticalGroup(
            modificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modificationsPanelLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        mainTabbedPane.addTab("Modifications", modificationsPanel);

        mainToolBar.setRollover(true);
        mainToolBar.setFloatable(false);

        addMaintenanceActionButton.setToolTipText("Add Maintenance Action to Current Vehicle");
        addMaintenanceActionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32ADD.png"))); // NOI18N
        addMaintenanceActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaintenanceActionButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(addMaintenanceActionButton);

        maintenanceAlertButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32ALERTNONE.png"))); // NOI18N
        maintenanceAlertButton.setToolTipText("There are 0 maintenance reminders for this Vehicle");
        mainToolBar.add(maintenanceAlertButton);

        editMaintenanceTypesButton.setToolTipText("View / Edit Maintenance Types");
        editMaintenanceTypesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceType32x32EDIT.png"))); // NOI18N
        editMaintenanceTypesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMaintenanceTypesButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(editMaintenanceTypesButton);

        fuelEntryMainToolBarAddButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fuelEntry32x32ADD.png"))); // NOI18N
        fuelEntryMainToolBarAddButton.setToolTipText("Add Fuel Entry");
        mainToolBar.add(fuelEntryMainToolBarAddButton);

        importGarageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/garage32x32IMPORT.png"))); // NOI18N
        importGarageButton.setToolTipText("Import Garage");
        importGarageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importGarageButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(importGarageButton);

        exportGarageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/garage32x32EXPORT.png"))); // NOI18N
        exportGarageButton.setToolTipText("Export Garage");
        exportGarageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportGarageButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(exportGarageButton);

        helpButton.setToolTipText("Help");
        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/help32x32.png"))); // NOI18N
        mainToolBar.add(helpButton);

        mechanicPanelNew.setBorder(compound);

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
        });
        jScrollPane3.setViewportView(mechanicsTable);

        mechanicsLabel.setText("Mechanics");

        javax.swing.GroupLayout mechanicPanelNewLayout = new javax.swing.GroupLayout(mechanicPanelNew);
        mechanicPanelNew.setLayout(mechanicPanelNewLayout);
        mechanicPanelNewLayout.setHorizontalGroup(
            mechanicPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(mechanicsLabel))
            .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                .addComponent(mechanicToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mechanicPanelNewLayout.setVerticalGroup(
            mechanicPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(mechanicsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mechanicPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mechanicToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        customerPanelNew.setBorder(compound);

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
        });
        jScrollPane7.setViewportView(customersTable);

        customersLabel.setText("Customers");

        javax.swing.GroupLayout customerPanelNewLayout = new javax.swing.GroupLayout(customerPanelNew);
        customerPanelNew.setLayout(customerPanelNewLayout);
        customerPanelNewLayout.setHorizontalGroup(
            customerPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelNewLayout.createSequentialGroup()
                .addComponent(customerToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(customerPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerPanelNewLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(customersLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(customerPanelNewLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );
        customerPanelNewLayout.setVerticalGroup(
            customerPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerPanelNewLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(customersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(customerToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        vehiclePanelNew.setBorder(compound);

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
        });
        jScrollPane8.setViewportView(vehiclesTable);

        vehiclesLabel.setText("Vehicles");

        javax.swing.GroupLayout vehiclePanelNewLayout = new javax.swing.GroupLayout(vehiclePanelNew);
        vehiclePanelNew.setLayout(vehiclePanelNewLayout);
        vehiclePanelNewLayout.setHorizontalGroup(
            vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(vehiclesLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                .addComponent(vehicleToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        vehiclePanelNewLayout.setVerticalGroup(
            vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(vehiclesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(vehicleToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        currentVehiclePanel.setBorder(compound);

        vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N

        currentVehicleLabel.setText("Vehicle");

        vehicleNameLabel.setText("No Vehicle Selected");

        currentVehicleOdometerLabel.setText("Odometer:");

        javax.swing.GroupLayout currentVehiclePanelLayout = new javax.swing.GroupLayout(currentVehiclePanel);
        currentVehiclePanel.setLayout(currentVehiclePanelLayout);
        currentVehiclePanelLayout.setHorizontalGroup(
            currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                .addComponent(vehiclePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                        .addComponent(vehicleNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                        .addComponent(currentVehicleLabel)
                        .addGap(39, 39, 39)
                        .addComponent(currentVehicleOdometerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        currentVehiclePanelLayout.setVerticalGroup(
            currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                .addGroup(currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentVehicleLabel)
                    .addComponent(currentVehicleOdometerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vehicleNameLabel))
            .addComponent(vehiclePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        currentCustomerPanel.setBorder(compound);

        customerPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N

        customerNameLabel.setText("No Customer Selected");

        currentCustomerLabel.setText("Customer");

        javax.swing.GroupLayout currentCustomerPanelLayout = new javax.swing.GroupLayout(currentCustomerPanel);
        currentCustomerPanel.setLayout(currentCustomerPanelLayout);
        currentCustomerPanelLayout.setHorizontalGroup(
            currentCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentCustomerPanelLayout.createSequentialGroup()
                .addComponent(customerPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(currentCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(currentCustomerPanelLayout.createSequentialGroup()
                        .addComponent(currentCustomerLabel)
                        .addGap(148, 148, 148))
                    .addGroup(currentCustomerPanelLayout.createSequentialGroup()
                        .addComponent(customerNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        currentCustomerPanelLayout.setVerticalGroup(
            currentCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentCustomerPanelLayout.createSequentialGroup()
                .addComponent(currentCustomerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerNameLabel))
            .addComponent(customerPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        currentMechanicPanel.setBorder(compound);

        mechanicNameLabel.setText("No Mechanic Selected");

        mechanicPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N

        currentMechanicLabel.setText("Mechanic");

        javax.swing.GroupLayout currentMechanicPanelLayout = new javax.swing.GroupLayout(currentMechanicPanel);
        currentMechanicPanel.setLayout(currentMechanicPanelLayout);
        currentMechanicPanelLayout.setHorizontalGroup(
            currentMechanicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, currentMechanicPanelLayout.createSequentialGroup()
                .addComponent(mechanicPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(currentMechanicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentMechanicLabel)
                    .addComponent(mechanicNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        currentMechanicPanelLayout.setVerticalGroup(
            currentMechanicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, currentMechanicPanelLayout.createSequentialGroup()
                .addGroup(currentMechanicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(currentMechanicPanelLayout.createSequentialGroup()
                        .addComponent(currentMechanicLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mechanicNameLabel))
                    .addComponent(mechanicPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        fileMenu.setText("File");
        fileMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuActionPerformed(evt);
            }
        });

        newGarageMenuItem.setText("New");
        newGarageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGarageMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newGarageMenuItem);

        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

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
        fileMenu.add(fileMenuSeparator);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mechanicsNotebookMenuBar.add(fileMenu);

        helpMenu.setText("Help");

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentCustomerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(currentVehiclePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(currentMechanicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mechanicPanelNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerPanelNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vehiclePanelNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainTabbedPane))
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Private method used to OPEN a GARAGE!!
     * @param evt 
     */
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File testFile = chooser.getSelectedFile();
        
        if(testFile != null){
            String filePath = testFile.getAbsolutePath();
            try {
                System.out.println(filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.mechanicsNotebookEngine.openGarage(testFile);
        }
        //this.mechanicsNotebookEngine.openGarage(testFile);
    }//GEN-LAST:event_openMenuItemActionPerformed

    /**
     * User hit the Exit on the File Menu..
     * @param evt 
     */
    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // KILL the program
        System.exit(0);
        
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * Private Method to "Save" the garage, should:
     * <li> if current garage has never been saved, ie new garage, show save as menu
     * <li> if user opened an existing garage (or already save), should automatically save in last position
     * @param evt 
     */
    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        
        if(chooser.getSelectedFile() != null){
            File testFile = chooser.getSelectedFile();
            String filePath = testFile.getAbsolutePath();
            try {
                System.out.println(filePath);
            } catch (Exception ex) {
                System.out.println("dialog must have closed?");
                ex.printStackTrace();
            }
            this.mechanicsNotebookEngine.saveGarage(testFile);
        }
        
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void newGarageMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGarageMenuItemActionPerformed
        // TODO add your handling code here:
        //this.motoGarageMechanicEngine.startNewGarageWindow();
        // NO it should 
        // 1) ask to save current data
        // 2) clear all data
        // 3) new garage object! nothing tied to it!
        
        this.mechanicsNotebookEngine.createDefaultGarage();
        this.refresh();
    }//GEN-LAST:event_newGarageMenuItemActionPerformed

    /**
     * Private method to "Save As". Should:
     * <li> Prompt a Save dialog to allow the user to choose where and what to save
     * @param evt 
     */
    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
                // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        File testFile = chooser.getSelectedFile();
        
        if(testFile != null){
            String filePath = testFile.getAbsolutePath();
            try {
                System.out.println(filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.mechanicsNotebookEngine.saveGarage(testFile);
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    /**
     * Method used when a customer hits the About Menu Item
     * @param evt 
     */
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // Time to open the About Window
        this.mechanicsNotebookEngine.startAboutWindow();
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    
    private void vehicleAddButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicleAddButtonNewActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentCustomer()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must first have a Customer in order to add a Vehicle! Please create a Customer first.");
        }else{
            this.mechanicsNotebookEngine.startNewVehicleWindow();
        }
    }//GEN-LAST:event_vehicleAddButtonNewActionPerformed

    private void mechanicAddButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mechanicAddButtonNewActionPerformed
        // User hit create new Mechanic button, let's do it!
        this.mechanicsNotebookEngine.startNewMechanicWindow();
    }//GEN-LAST:event_mechanicAddButtonNewActionPerformed

    private void mechanicButtonEditNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mechanicButtonEditNewActionPerformed
        if(this.mechanicsNotebookEngine.getCurrentMechanic()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, "You don't have a Mechanic to edit!");
        }else{
            this.mechanicsNotebookEngine.startUpdateMechanicWindow();
        }
    }//GEN-LAST:event_mechanicButtonEditNewActionPerformed

    private void mechanicDeleteButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mechanicDeleteButtonNewActionPerformed
        if(this.mechanicsNotebookEngine.getCurrentMechanic()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You have not selected a Mechanic to delete!");
        }else{
            boolean sureToDelete = this.mechanicsNotebookEngine.getDialogFactory().createConfirmMessage("Are you sure you wish to delete the current Mechanic? This is permanent!");
            if(sureToDelete){
                this.mechanicsNotebookEngine.deleteCurrentMechanic();
            }            
        }
    }//GEN-LAST:event_mechanicDeleteButtonNewActionPerformed

    private void customerAddButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerAddButtonNewActionPerformed
        // User pressed the new customer button, time to enact
        this.mechanicsNotebookEngine.startNewCustomerWindow();
    }//GEN-LAST:event_customerAddButtonNewActionPerformed

    private void customerEditButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerEditButtonNewActionPerformed
        if(this.mechanicsNotebookEngine.getCurrentCustomer()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, "You don't have a Customer to edit!");
        }else{
            this.mechanicsNotebookEngine.startUpdateCustomerWindow();
        }
    }//GEN-LAST:event_customerEditButtonNewActionPerformed

    private void customerDeleteButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerDeleteButtonNewActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentCustomer()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You have not selected a Customer to delete!");
        }else{
            boolean sureToDelete = this.mechanicsNotebookEngine.getDialogFactory().createConfirmMessage("Are you sure you wish to delete the current Customer? This is permanent!");
            if(sureToDelete){
                this.mechanicsNotebookEngine.deleteCurrentCustomer();
            }            
        }
    }//GEN-LAST:event_customerDeleteButtonNewActionPerformed

    private void vehicleDeleteButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicleDeleteButtonNewActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentVehicle()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You have not selected a Vehicle to delete!");
        }else{
            boolean sureToDelete = this.mechanicsNotebookEngine.getDialogFactory().createConfirmMessage("Are you sure you wish to delete the current Vehicle? This is permanent!");
            if(sureToDelete){
                this.mechanicsNotebookEngine.deleteCurrentVehicle();
            }            
        }
    }//GEN-LAST:event_vehicleDeleteButtonNewActionPerformed

    private void vehicleEditButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicleEditButtonNewActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentVehicle()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, "You don't have a Vehicle to edit!");
        }else{
            this.mechanicsNotebookEngine.startUpdateVehicleWindow();
        }
    }//GEN-LAST:event_vehicleEditButtonNewActionPerformed

    private void mechanicsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mechanicsTableMouseClicked
        // TODO add your handling code here:
        int rowSelected = mechanicsTable.getSelectedRow();
            if(rowSelected>-1){
                Mechanic[] mechanics = mechanicsNotebookEngine.getMechanicArray();
                Mechanic selectedMechanic = mechanics[rowSelected];
                mechanicsNotebookEngine.setCurrentMechanic(selectedMechanic);
            }            
    }//GEN-LAST:event_mechanicsTableMouseClicked

    private void customersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customersTableMouseClicked
        // TODO add your handling code here:
        int rowSelected = customersTable.getSelectedRow();
            if(rowSelected>-1){
                Customer[] customers = mechanicsNotebookEngine.getCustomerArray();
                Customer selectedCustomer = customers[rowSelected];
                mechanicsNotebookEngine.setCurrentCustomer(selectedCustomer);
            }                      
    }//GEN-LAST:event_customersTableMouseClicked

    private void vehiclesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehiclesTableMouseClicked
        // TODO add your handling code here:
        int rowSelected = vehiclesTable.getSelectedRow();
            if(rowSelected>-1){
                Vehicle[] vehicles = mechanicsNotebookEngine.getVehicleArray();
                Vehicle selectedVehicle = vehicles[rowSelected];
                mechanicsNotebookEngine.setCurrentVehicle(selectedVehicle);
            }
    }//GEN-LAST:event_vehiclesTableMouseClicked

    private void updateOdometerActionButtonToolBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateOdometerActionButtonToolBarActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentVehicle()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You can't update mileage without a Vehicle!");
        }else{
            this.mechanicsNotebookEngine.startNewUpdateMileageWindow();
        }
    }//GEN-LAST:event_updateOdometerActionButtonToolBarActionPerformed

    private void addMaintenanceActionButtonToolBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaintenanceActionButtonToolBarActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentVehicle()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must have a Vehicle to add a Maintenance Action!");
        }else if(this.mechanicsNotebookEngine.getCurrentMechanic() == null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must have a current Mechanic to create a Maintenance Action!");
        }else if(!this.mechanicsNotebookEngine.hasMaintenanceTypes()){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must have at least one Maintenance Type before adding a Maintenace Action! Please create a Maintenance Type first.");
        }else{
            this.mechanicsNotebookEngine.startNewMaintenanceActionWindow();
        }
    }//GEN-LAST:event_addMaintenanceActionButtonToolBarActionPerformed

    private void addMaintenanceActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaintenanceActionButtonActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentVehicle()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You must have a Vehicle to add a Maintenance Action!");
            return;
        }else if(this.mechanicsNotebookEngine.getCurrentMechanic() == null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You must have a current Mechanic to create a Maintenance Action!");
        }else if(!this.mechanicsNotebookEngine.hasMaintenanceTypes()){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You must have at least one Maintenance Type before adding a Maintenace Action! Please create a Maintenance Type first.");
        }
        else{
            this.mechanicsNotebookEngine.startNewMaintenanceActionWindow();
        }
    }//GEN-LAST:event_addMaintenanceActionButtonActionPerformed

    private void editMaintenanceTypesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMaintenanceTypesButtonActionPerformed
        // TODO add your handling code here:
        this.mechanicsNotebookEngine.startMaintenanceTypesMainWindow();
    }//GEN-LAST:event_editMaintenanceTypesButtonActionPerformed

    private void editMaintenanceActionButtonToolBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMaintenanceActionButtonToolBarActionPerformed
        // TODO add your handling code here:
        int rowSelected = maintenanceActionsTable.getSelectedRow();

            if(rowSelected>-1){
                MaintenanceAction[] currentMaintenanceActions = this.mechanicsNotebookEngine.getCurrentVehicle().getMaintenanceActionsArray();
                MaintenanceAction selectedMaintenanceAction = currentMaintenanceActions[rowSelected];                
                this.mechanicsNotebookEngine.startMaintenanceActionWindow(selectedMaintenanceAction);
            }else{
                this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You have not selected a Maintenance Action to Update.");
            }        
    }//GEN-LAST:event_editMaintenanceActionButtonToolBarActionPerformed

    private void deleteMaintenanceActionButtonToolBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMaintenanceActionButtonToolBarActionPerformed
        // TODO add your handling code here:
        int rowSelected = maintenanceActionsTable.getSelectedRow();        
        if(rowSelected>-1){
            boolean sureToDelete = this.mechanicsNotebookEngine.getDialogFactory().createConfirmMessage("Are you sure you wish to delete the Maintenance Action? This is permanent!");
            if(sureToDelete){
                MaintenanceAction[] currentMaintenanceActions = this.mechanicsNotebookEngine.getCurrentVehicle().getMaintenanceActionsArray();
                MaintenanceAction selectedMaintenanceAction = currentMaintenanceActions[rowSelected]; 
                //this.mechanicsNotebookEngine.getCurrentVehicle().deleteMaintenanceAction(selectedMaintenanceAction);
                this.mechanicsNotebookEngine.deleteMaintenaceAction(selectedMaintenanceAction);
            }    
        }else{
                this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You have not selected a Maintenance Action to Delete.");
            }  
        
    }//GEN-LAST:event_deleteMaintenanceActionButtonToolBarActionPerformed

    private void fileMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileMenuActionPerformed

    private void importGarageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importGarageButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Import Garage");
        
        
        chooser.showOpenDialog(this);
        File testFile = chooser.getSelectedFile();
        
        if(testFile != null){
            String filePath = testFile.getAbsolutePath();
            try {
                System.out.println(filePath);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.mechanicsNotebookEngine.openGarage(testFile);
        }
    }//GEN-LAST:event_importGarageButtonActionPerformed

    private void exportGarageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportGarageButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Export Garage");
        chooser.showSaveDialog(this);
        
        if(chooser.getSelectedFile() != null){
            File testFile = chooser.getSelectedFile();
            String filePath = testFile.getAbsolutePath();
            try {
                System.out.println(filePath);
            } catch (Exception ex) {
                System.out.println("dialog must have closed?");
                ex.printStackTrace();
            }
            this.mechanicsNotebookEngine.saveGarage(testFile);
        }
    }//GEN-LAST:event_exportGarageButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addMaintenanceActionButton;
    private javax.swing.JButton addMaintenanceActionButtonToolBar;
    private javax.swing.JLabel currentCustomerLabel;
    private javax.swing.JPanel currentCustomerPanel;
    private javax.swing.JLabel currentMechanicLabel;
    private javax.swing.JPanel currentMechanicPanel;
    private javax.swing.JLabel currentVehicleLabel;
    private javax.swing.JLabel currentVehicleOdometerLabel;
    private javax.swing.JPanel currentVehiclePanel;
    private javax.swing.JButton customerAddButtonNew;
    private javax.swing.JButton customerDeleteButtonNew;
    private javax.swing.JButton customerEditButtonNew;
    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JPanel customerPanelNew;
    private javax.swing.JLabel customerPictureLabel;
    private javax.swing.JToolBar customerToolBar;
    private javax.swing.JLabel customersLabel;
    private javax.swing.JTable customersTable;
    private javax.swing.JButton deleteMaintenanceActionButtonToolBar;
    private javax.swing.JButton editMaintenanceActionButtonToolBar;
    private javax.swing.JButton editMaintenanceTypesButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JButton exportGarageButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator;
    private javax.swing.JPanel fuelEntriesPanel;
    private javax.swing.JTable fuelEntriesTable;
    private javax.swing.JButton fuelEntryAddButton;
    private javax.swing.JButton fuelEntryDeleteButton;
    private javax.swing.JButton fuelEntryEditButton;
    private javax.swing.JButton fuelEntryMainToolBarAddButton;
    private javax.swing.JToolBar fuelEntryToolBar;
    private javax.swing.JButton helpButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton importGarageButton;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
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
    private javax.swing.JLabel mechanicsLabel;
    private javax.swing.JMenuBar mechanicsNotebookMenuBar;
    private javax.swing.JTable mechanicsTable;
    private javax.swing.JPanel modificationsPanel;
    private javax.swing.JMenuItem newGarageMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton updateOdometerActionButtonToolBar;
    private javax.swing.JButton vehicleAddButtonNew;
    private javax.swing.JButton vehicleDeleteButtonNew;
    private javax.swing.JButton vehicleEditButtonNew;
    private javax.swing.JPanel vehicleMaintenanceActionsPanel;
    private javax.swing.JLabel vehicleNameLabel;
    private javax.swing.JPanel vehiclePanelNew;
    private javax.swing.JLabel vehiclePictureLabel;
    private javax.swing.JToolBar vehicleToolBar;
    private javax.swing.JPanel vehicleWarrantiesPanel;
    private javax.swing.JLabel vehiclesLabel;
    private javax.swing.JTable vehiclesTable;
    // End of variables declaration//GEN-END:variables
}
