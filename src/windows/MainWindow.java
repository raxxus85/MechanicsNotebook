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
import java.awt.Font;
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
        
        // TEST CODE
        maintenanceActionsTable.addMouseListener(new MouseAdapter2(this.mechanicsNotebookEngine) {
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable) me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    MaintenanceAction [] currentMaintenanceActions = mechanicsNotebookEngine.getCurrentVehicle().getMaintenanceActionsArray();
                    mechanicsNotebookEngine.startMaintenanceActionWindow(currentMaintenanceActions[row]);
                }
            }
        });
        DefaultTableModel tableModel = new DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Odometer", "Maintenance Type", "Notes", "Performed By"
            }
                
                ) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
                if(row==0){
                    return true;
                }
            return false;
            }
    };
    maintenanceActionsTable.setModel(tableModel);
    
    createMenuBar();
    }

   
    
    protected void createMenuBar() {

        // VEHICLES
    ImageIcon vehicleAdd = new ImageIcon(getClass().getResource("/vehicle32x32ADD.png"));
    Action actionVehicleAdd = new AbstractAction("New", vehicleAdd) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("vehicle add");
      }
    };
    ImageIcon vehicleEdit = new ImageIcon(getClass().getResource("/vehicle32x32EDIT.png"));
    Action actionVehicleEdit = new AbstractAction("New", vehicleEdit) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("vehicle edit");
      }
    };
    ImageIcon vehicleRemove = new ImageIcon(getClass().getResource("/vehicle32x32REMOVE.png"));
    Action actionVehicleRemove = new AbstractAction("New", vehicleRemove) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("vehicle remove");
      }
    };
        // CUSTOMERS

    ImageIcon customerEdit = new ImageIcon(getClass().getResource("/customer32x32EDIT.png"));
    Action actionCustomerEdit = new AbstractAction("New", customerEdit) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("customer edit");
      }
    };

    ImageIcon customerAdd = new ImageIcon(getClass().getResource("/customer32x32ADD.png"));
    Action actionCustomerAdd = new AbstractAction("Open...", customerAdd) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("customer add");
      }
    };

    ImageIcon customerRemove = new ImageIcon(getClass().getResource("/customer32x32REMOVE.png"));
    Action actionCustomerRemove = new AbstractAction("Save...", customerRemove) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("customer remove");
      }
    };
    
    // MECHANICS
    ImageIcon mechanicEdit = new ImageIcon(getClass().getResource("/mechanic32x32EDIT.png"));
    Action actionMechanicEdit = new AbstractAction("New", mechanicEdit) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("mechanic edit");
      }
    };

    ImageIcon mechanicAdd = new ImageIcon(getClass().getResource("/mechanic32x32ADD.png"));
    Action actionMechanicAdd = new AbstractAction("Open...", mechanicAdd) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("mechanic add");
      }
    };

    ImageIcon mechanicRemove = new ImageIcon(getClass().getResource("/mechanic32x32REMOVE.png"));
    Action actionMechanicRemove = new AbstractAction("Save...", mechanicRemove) {
      public void actionPerformed(ActionEvent e) {
        System.out.println("mechanic remove");
      }
    };
    
    //item = mFile.add(actionSave);
    //mFile.add(item);

    //mFile.addSeparator();

    Action actionExit = new AbstractAction("Exit") {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    };
    //item = mFile.add(actionExit);
    //item.setMnemonic('x');
    //menuBar.add(mFile);

    //mainToolBar = new JToolBar();
    JButton btn1 = mainToolBar.add(actionCustomerEdit);
    btn1.setToolTipText("Edit Customer");
    JButton btn2 = mainToolBar.add(actionCustomerAdd);
    btn2.setToolTipText("Add Customer");
    JButton btn3 = mainToolBar.add(actionCustomerRemove);
    btn3.setToolTipText("Remove Customer");

    JButton vehicleAddButton = mainToolBar.add(actionVehicleAdd);
    vehicleAddButton.setToolTipText("Add Vehicle");
    JButton vehicleEditButton = mainToolBar.add(actionVehicleEdit);
    vehicleEditButton.setToolTipText("Edit Vehicle");
    JButton vehicleRemoveButton = mainToolBar.add(actionVehicleRemove);
    vehicleRemoveButton.setToolTipText("Remove Vehicle");
    
    //test
    mechanicToolBar.add(actionMechanicAdd);
    mechanicToolBar.add(actionMechanicEdit);
    mechanicToolBar.add(actionMechanicRemove);
    
    customerToolBar.add(actionCustomerAdd);
    customerToolBar.add(actionCustomerEdit);
    customerToolBar.add(actionCustomerRemove);
    
    vehicleToolBar.add(actionVehicleAdd);
    vehicleToolBar.add(actionVehicleEdit);
    vehicleToolBar.add(actionVehicleRemove);
    //vehicleToolBar.add(removeVehicleButton);

    JSeparator jSeparator1 = new javax.swing.JToolBar.Separator();
    //jSeparator1.setOrientation(VERTICAL);
    jSeparator1.setVisible(true);
    mainToolBar.add(jSeparator1);

   
        
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
        this.refreshMaintenaceTypeTab();
        this.refreshCurrentObjects();
        
    }
    
    private void refreshMaintenaceTypeTab(){
        this.refreshMaintenanceTypeComboBox();
        this.refreshMaintenanceTypeInformation();
    }
    
    private void refreshMaintenanceTypeInformation(){
        if(this.maintenanceTypesComboBox.getSelectedItem()!=null){
            MaintenanceType currentMaintenanceType = (MaintenanceType)this.maintenanceTypesComboBox.getSelectedItem();
            this.maintenanceTypeTextField.setText(currentMaintenanceType.getMaintenanceTypeName());
            this.maintenenaceTypeMileageIntervalTextField.setText(currentMaintenanceType.getMileageInterval().toString());
            this.maintenanceTypeDescriptionTextArea.setText(currentMaintenanceType.getDescription().toString());
        }else{
            this.maintenanceTypeTextField.setText("");
            this.maintenenaceTypeMileageIntervalTextField.setText("");
            this.maintenanceTypeDescriptionTextArea.setText("");
        }

    }
    
    private void refreshMaintenanceTypeComboBox(){
        if(this.maintenanceTypesComboBox.getSelectedItem()!=null){
            MaintenanceType currentMaintenanceType = (MaintenanceType)this.maintenanceTypesComboBox.getSelectedItem();
            this.maintenanceTypesComboBox.removeAllItems();
            maintenanceTypesComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.mechanicsNotebookEngine.getMaintenaceTypeArray()));
            this.maintenanceTypesComboBox.setSelectedItem(currentMaintenanceType);
        }else{
            maintenanceTypesComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.mechanicsNotebookEngine.getMaintenaceTypeArray()));
        }
    }
    
    private void refreshVehiclesTab(){
        this.refreshVehiclesComboBox();
        this.refreshVehiclesInformation();
        this.refreshMaintenanceActions();
        this.refreshVehicleList();
    }
    
    /**
     * One of the most important Refresh methods, refreshes all the maintenance actions for current vehicle
     */
    private void refreshMaintenanceActions(){
        DefaultTableModel model = (DefaultTableModel) maintenanceActionsTable.getModel();
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        if(this.mechanicsNotebookEngine.getCurrentVehicle()!=null && this.mechanicsNotebookEngine.getCurrentVehicle().getSortedMaintenanceActionsArray().length >0){
            int newRowCount = this.mechanicsNotebookEngine.getCurrentVehicle().getSortedMaintenanceActionsArray().length;
            MaintenanceAction[] maintenanceArray = this.mechanicsNotebookEngine.getCurrentVehicle().getSortedMaintenanceActionsArray();
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]maintenanceActionObject = maintenanceArray[i].getMaintenaceActionObject();

                model.addRow(maintenanceActionObject);                
            }
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
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        if(this.mechanicsNotebookEngine.getMechanicArray().length >0){
            Mechanic[] mechanics = this.mechanicsNotebookEngine.getMechanicArray();
            int newRowCount = mechanics.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]mechanicObject = mechanics[i].getMechanicObject();
                model.addRow(mechanicObject);       
                
            }
        }
    }
    
    /**
     * Method used to update the Vehicle List
     */
    private void refreshVehicleList(){
        DefaultTableModel model = (DefaultTableModel) vehiclesTable.getModel();
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        // time to add the vehicles
        if(this.mechanicsNotebookEngine.getCurrentCustomer()!=null &&this.mechanicsNotebookEngine.getCurrentCustomer().getVehicleArray().length >0){
            Vehicle[] vehicles = this.mechanicsNotebookEngine.getVehicleArray();
            int newRowCount = vehicles.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]vehicleObject = vehicles[i].getVehicleObject();
                model.addRow(vehicleObject);                      
            }
        }
    }
    
    
    private void refreshVehiclesInformation(){
        System.out.println("REFRESH VEHICLES INFO BIENG CALLED");
        //Vehicle currentVehicle = (Vehicle)this.customersComboBox.getSelectedItem();
        Vehicle currentVehicle = this.mechanicsNotebookEngine.getCurrentVehicle();
        if(currentVehicle == null){
        this.currentVehicleMakeTextField.setText("");
        this.currentVehicleModelTextField.setText("");
        this.currentVehicleYearTextField.setText("");
        this.currentVehicleColorTextField.setText("");
        this.currentVehicleVINTextField.setText("");
        this.currentVehicleOdometerTextField.setText("");
        this.currentVehicleDescriptionTextArea.setText("");
        this.vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage.png")));
        }else{
        if(currentVehicle.getImageIcon()!=null){
            this.vehiclePictureLabel.setIcon(currentVehicle.getImageIcon());
        }else{
            this.vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage.png")));
        }
        this.currentVehicleMakeTextField.setText(currentVehicle.getMake());
        this.currentVehicleModelTextField.setText(currentVehicle.getModel());
        this.currentVehicleYearTextField.setText(currentVehicle.getYear().toString());
        this.currentVehicleColorTextField.setText(currentVehicle.getColor().toString());
        this.currentVehicleVINTextField.setText(currentVehicle.getVIN().toString());
        
        this.currentVehicleOdometerTextField.setText(currentVehicle.getOdometer().toString());
        this.currentVehicleDescriptionTextArea.setText(currentVehicle.getDescription());
        }
    }
    
    private void refreshVehiclesComboBox(){
        System.out.println("REFRESH VEHICLES COMBO BEING CALLED");
        boolean hasVehicles = false;
        Vehicle currentVehicle = null;
        // should we do this for vehicles? ehhh? brain hurts
        if(this.vehiclesComboBox.getSelectedItem() !=null){
            currentVehicle =  (Vehicle) this.vehiclesComboBox.getSelectedItem();
            hasVehicles = true;
        }
        this.vehiclesComboBox.removeAllItems();
        vehiclesComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.mechanicsNotebookEngine.getVehicleArray()));
        if(hasVehicles){
            vehiclesComboBox.setSelectedItem(currentVehicle);
        }
    }
    
    /**
     * Method to refresh the current display objects, ie Mechanic, Customer, Vehicle
     */
    private void refreshCurrentObjects(){
        System.out.println("REFRESH CURRENT OBJECTS BEING CALLED");
        // Refresh Mechanic 
        if(this.mechanicsNotebookEngine.getCurrentMechanic()!= null){
            this.currentMechanicTextField.setText(this.mechanicsNotebookEngine.getCurrentMechanic().toString());
        }else{
            this.currentMechanicTextField.setText("");
        }
        // Refresh Customer
        if(this.mechanicsNotebookEngine.getCurrentCustomer()!= null){
            this.currentCustomerTextField.setText(this.mechanicsNotebookEngine.getCurrentCustomer().toString());
        }else{
            this.currentCustomerTextField.setText("");
        }
        // Refresh Vehicle
        if(this.mechanicsNotebookEngine.getCurrentVehicle()!= null){
            this.currentVehicleTextField.setText(this.mechanicsNotebookEngine.getCurrentVehicle().toString());
        }else{
            this.currentVehicleTextField.setText("");
        }
    }
    
    /**
     * Method used to refresh the entire Customers Tabs
     */
    private void refreshCustomersTab(){
        this.refreshCustomerComboBox();
        this.refreshCustomerInformation();
        this.refreshCustomerList();
    }
    
    /**
     * Method used to update the Customer List (JTable)
     */
    private void refreshCustomerList(){
        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();
        // time to remove the maintenance actions here
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        if(this.mechanicsNotebookEngine.getCustomerArray().length >0){
            Customer[] customers = this.mechanicsNotebookEngine.getCustomerArray();
            int newRowCount = customers.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]customerObject = customers[i].getCustomerObject();
                model.addRow(customerObject);                      
            }
        }
    }
    
    /**
     * Method used to refresh the Customers Combo Box
     */
    private void refreshCustomerComboBox(){
        System.out.println("REFRESH CUSTOMER COMBO BOX BEING CALLED");
        boolean hasCustomers = false;
        Customer currentCustomer = null;
        if(this.customersComboBox.getSelectedItem() != null){
            currentCustomer = (Customer)this.customersComboBox.getSelectedItem();
            hasCustomers = true;
        }
        this.customersComboBox.removeAllItems();
        customersComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.mechanicsNotebookEngine.getCustomerArray()));
        if(hasCustomers){
            customersComboBox.setSelectedItem(currentCustomer);
        }
    }
    
        /**
     * Method to refresh the current Customers information (name, description, etc)
     */
    private void refreshCustomerInformation(){
        System.out.println("REFRESH CUSTOMER INFO BEING CALLED");
        Customer currentCustomer = (Customer)this.customersComboBox.getSelectedItem();
        if(currentCustomer == null){
        this.currentCustomerFirstNameTextField.setText("");
        this.currentCustomerMiddleInitialTextField.setText("");
        this.currentCustomerLastNameTextField.setText("");
        this.currentCustomerDescriptionTextArea.setText("");
        this.customerPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage.png")));
        }else{
        if(currentCustomer.getImageIcon()!=null){
            this.customerPictureLabel.setIcon(currentCustomer.getImageIcon());
        }else{
            this.customerPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage.png")));
        }
        this.currentCustomerFirstNameTextField.setText(currentCustomer.getFirstName());
        this.currentCustomerMiddleInitialTextField.setText(currentCustomer.getMiddleName());
        this.currentCustomerLastNameTextField.setText(currentCustomer.getLastName());
        this.currentCustomerDescriptionTextArea.setText(currentCustomer.getDescription());
        }
    }
    
    /**
     * Method used to refresh everything within the Mechanics Tab
     */
    private void refreshMechanicsTab(){
        this.refreshMechanicComboBox();
        this.refreshMechanicInformation();
        this.refreshMechanicsList();
    }
    

    
    /**
     * Method to refresh the Mechanics Combo Box
     * 
     */
    private void refreshMechanicComboBox(){
        System.out.println("REFRESH MECHANIC COMBOBOX() BEING CALLED HERE!!!!!!!!");
        boolean hasMechanics = false;
        Mechanic currentMechanic = null;
        if(this.mechanicsComboBox.getSelectedItem() != null){
            currentMechanic = (Mechanic)this.mechanicsComboBox.getSelectedItem();
            hasMechanics = true;
        }else{
 
        }
        this.mechanicsComboBox.removeAllItems();
        mechanicsComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.mechanicsNotebookEngine.getMechanicArray()));
        if(hasMechanics){
            mechanicsComboBox.setSelectedItem(currentMechanic);
        }
    }
    
    /**
     * Method to refresh the current Mechanic's information (name, description, etc)
     */
    private void refreshMechanicInformation(){
        System.out.println("REFRESH MECHANIC INFORMATION BEING CALLED");
        Mechanic currentMechanic = (Mechanic)this.mechanicsComboBox.getSelectedItem();
        if(currentMechanic == null){
        this.currentMechanicFirstNameTextField.setText("");
        this.currentMechanicMiddleNameTextField.setText("");
        this.currentMechanicLastNameTextField.setText("");
        this.currentMechanicDescriptionTextArea.setText("");
        this.mechanicPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage.png")));
        }else{
        if(currentMechanic.getImageIcon()!=null){
            this.mechanicPictureLabel.setIcon(currentMechanic.getImageIcon());
        }else{
            mechanicPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage.png")));
        }
        this.currentMechanicFirstNameTextField.setText(currentMechanic.getFirstName());
        this.currentMechanicMiddleNameTextField.setText(currentMechanic.getMiddleName());
        this.currentMechanicLastNameTextField.setText(currentMechanic.getLastName());
        this.currentMechanicDescriptionTextArea.setText(currentMechanic.getDescription());
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
        mechanicsPanel = new javax.swing.JPanel();
        mechanicsComboBox = new javax.swing.JComboBox();
        mechanicsLabel = new javax.swing.JLabel();
        currentMechanicFirstNameLabel = new javax.swing.JLabel();
        currentMechanicFirstNameTextField = new javax.swing.JTextField();
        currentMechanicMiddleNameTextField = new javax.swing.JTextField();
        currentMechanicLastNameTextField = new javax.swing.JTextField();
        currentMechanicMiddleNameLabel = new javax.swing.JLabel();
        currentMechanicLastNameLabel = new javax.swing.JLabel();
        currentMechanicDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        currentMechanicDescriptionTextArea = new javax.swing.JTextArea();
        mechanicPictureLabel = new javax.swing.JLabel();
        mechanicPictureTextLabel = new javax.swing.JLabel();
        addMechanicButton = new customclasses.MechanicButton(ButtonActions.ADD);
        editMechanicButton = new customclasses.MechanicButton(ButtonActions.EDIT);
        removeMechanicButton = new customclasses.MechanicButton(ButtonActions.REMOVE);
        customersPanel = new javax.swing.JPanel();
        customersLabel = new javax.swing.JLabel();
        customersComboBox = new javax.swing.JComboBox();
        customerFirstNameLabel = new javax.swing.JLabel();
        customerMiddleInitialLabel = new javax.swing.JLabel();
        customerLastNameLabel = new javax.swing.JLabel();
        customerDescriptionLabel = new javax.swing.JLabel();
        currentCustomerFirstNameTextField = new javax.swing.JTextField();
        currentCustomerMiddleInitialTextField = new javax.swing.JTextField();
        currentCustomerLastNameTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        currentCustomerDescriptionTextArea = new javax.swing.JTextArea();
        customerPictureLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        addCustomerButton = new customclasses.CustomerButton(ButtonActions.ADD);
        editCustomerButton = new customclasses.CustomerButton(ButtonActions.EDIT);
        deleteCustomerButton = new customclasses.CustomerButton(ButtonActions.REMOVE);
        vehiclesPanel = new javax.swing.JPanel();
        vehiclesTabbedPane = new javax.swing.JTabbedPane();
        vehicleInfoPanel = new javax.swing.JPanel();
        vehiclesComboBox = new javax.swing.JComboBox();
        currentVehicleJComboBoxLabel = new javax.swing.JLabel();
        currentVehicleMakeTextField = new javax.swing.JTextField();
        currentVehicleModelTextField = new javax.swing.JTextField();
        currentVehicleYearTextField = new javax.swing.JTextField();
        currentVehicleOdometerTextField = new javax.swing.JTextField();
        vehicleMakeLabel = new javax.swing.JLabel();
        vehicleModelLabel = new javax.swing.JLabel();
        vehicleYearLabel = new javax.swing.JLabel();
        vehicleOdometerLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        currentVehicleDescriptionTextArea = new javax.swing.JTextArea();
        vehicleDescriptionLabel = new javax.swing.JLabel();
        currentVehicleColorLabel = new javax.swing.JLabel();
        currentVehicleColorTextField = new javax.swing.JTextField();
        currentVehicleVINLabel = new javax.swing.JLabel();
        currentVehicleVINTextField = new javax.swing.JTextField();
        vehiclePictureTextLabel = new javax.swing.JLabel();
        vehiclePictureLabel = new javax.swing.JLabel();
        addVehicleButton = new customclasses.VehicleButton(ButtonActions.ADD);
        editVehicleButton = new customclasses.VehicleButton(ButtonActions.EDIT);
        removeVehicleButton = new customclasses.VehicleButton(ButtonActions.REMOVE);
        jToolBar1 = new javax.swing.JToolBar();
        removeVehicleButton2 = new customclasses.VehicleButton(ButtonActions.REMOVE);
        vehicleMaintenanceActionsPanel = new javax.swing.JPanel();
        maintenanceActionsLabel = new javax.swing.JLabel();
        updateMileageButton = new javax.swing.JButton();
        addMaintenanceActionButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        maintenanceActionsTable = new javax.swing.JTable();
        vehicleWarrantiesPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        maintenanceActionsTypes = new javax.swing.JPanel();
        maintenanceTypesComboBox = new javax.swing.JComboBox();
        actionTypesLable = new javax.swing.JLabel();
        actionTypeNameLabel = new javax.swing.JLabel();
        actionMileageIntervalLabel = new javax.swing.JLabel();
        maintenanceTypeTextField = new javax.swing.JTextField();
        maintenenaceTypeMileageIntervalTextField = new javax.swing.JTextField();
        actionDescriptionLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        maintenanceTypeDescriptionTextArea = new javax.swing.JTextArea();
        newMaintenanceTypeButton = new javax.swing.JButton();
        editMaintenanceTypeButton = new javax.swing.JButton();
        deleteMaintenanceTypeButton = new javax.swing.JButton();
        currentMechanicLabel = new javax.swing.JLabel();
        currentMechanicTextField = new javax.swing.JTextField();
        currentCustomerLabel = new javax.swing.JLabel();
        currentCustomerTextField = new javax.swing.JTextField();
        currentVehicleLabel = new javax.swing.JLabel();
        currentVehicleTextField = new javax.swing.JTextField();
        mainToolBar = new javax.swing.JToolBar();
        mechanicPanelNew = new javax.swing.JPanel();
        mechanicToolBar = new javax.swing.JToolBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        mechanicsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        customerPanelNew = new javax.swing.JPanel();
        customerToolBar = new javax.swing.JToolBar();
        jScrollPane7 = new javax.swing.JScrollPane();
        customersTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        vehiclePanelNew = new javax.swing.JPanel();
        vehicleToolBar = new javax.swing.JToolBar();
        jScrollPane8 = new javax.swing.JScrollPane();
        vehiclesTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
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

        mechanicsComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.mechanicsNotebookEngine.getMechanicArray()));
        mechanicsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mechanicsComboBoxActionPerformed(evt);
            }
        });

        mechanicsLabel.setText("Mechanics");

        currentMechanicFirstNameLabel.setText("First Name");

        currentMechanicFirstNameTextField.setEditable(false);
        currentMechanicFirstNameTextField.setFocusable(false);

        currentMechanicMiddleNameTextField.setEditable(false);
        currentMechanicMiddleNameTextField.setFocusable(false);

        currentMechanicLastNameTextField.setEditable(false);
        currentMechanicLastNameTextField.setFocusable(false);

        currentMechanicMiddleNameLabel.setText("Middle Name");

        currentMechanicLastNameLabel.setText("Last Name");

        currentMechanicDescription.setText("Description");

        currentMechanicDescriptionTextArea.setEditable(false);
        currentMechanicDescriptionTextArea.setColumns(20);
        currentMechanicDescriptionTextArea.setRows(5);
        currentMechanicDescriptionTextArea.setFocusable(false);
        jScrollPane1.setViewportView(currentMechanicDescriptionTextArea);

        mechanicPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage.png"))); // NOI18N

        mechanicPictureTextLabel.setText("Mechanic Picture");

        addMechanicButton.setToolTipText("Add a Mechanic");
        addMechanicButton.setMaximumSize(new java.awt.Dimension(32, 32));
        addMechanicButton.setMinimumSize(new java.awt.Dimension(32, 32));
        addMechanicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMechanicButtonActionPerformed(evt);
            }
        });

        editMechanicButton.setToolTipText("Edit a Mechanic");
        editMechanicButton.setMaximumSize(new java.awt.Dimension(32, 32));
        editMechanicButton.setMinimumSize(new java.awt.Dimension(32, 32));
        editMechanicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMechanicButtonActionPerformed(evt);
            }
        });

        removeMechanicButton.setToolTipText("Remove a Mechanic");
        removeMechanicButton.setMaximumSize(new java.awt.Dimension(32, 32));
        removeMechanicButton.setMinimumSize(new java.awt.Dimension(32, 32));
        removeMechanicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeMechanicButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mechanicsPanelLayout = new javax.swing.GroupLayout(mechanicsPanel);
        mechanicsPanel.setLayout(mechanicsPanelLayout);
        mechanicsPanelLayout.setHorizontalGroup(
            mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mechanicsPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mechanicsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                                .addComponent(addMechanicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editMechanicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeMechanicButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currentMechanicMiddleNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(currentMechanicFirstNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mechanicsPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currentMechanicLastNameLabel)
                            .addComponent(currentMechanicDescription)))
                    .addGroup(mechanicsPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(mechanicsLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentMechanicMiddleNameTextField)
                    .addComponent(currentMechanicLastNameTextField)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(currentMechanicFirstNameTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mechanicPictureTextLabel)
                    .addComponent(mechanicPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        mechanicsPanelLayout.setVerticalGroup(
            mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mechanicsPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mechanicsLabel)
                            .addComponent(mechanicPictureTextLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mechanicPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mechanicsPanelLayout.createSequentialGroup()
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(mechanicsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editMechanicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(removeMechanicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addMechanicButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(currentMechanicFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentMechanicFirstNameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(currentMechanicMiddleNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentMechanicMiddleNameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(currentMechanicLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentMechanicLastNameLabel))))
                        .addGap(394, 394, 394)
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currentMechanicDescription))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Mechanics", mechanicsPanel);

        customersLabel.setText("Customers");

        customersComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.mechanicsNotebookEngine.getCustomerArray()));
        customersComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customersComboBoxActionPerformed(evt);
            }
        });

        customerFirstNameLabel.setText("First Name");

        customerMiddleInitialLabel.setText("Middle Initial");

        customerLastNameLabel.setText("Last Name");

        customerDescriptionLabel.setText("Description");

        currentCustomerFirstNameTextField.setEditable(false);
        currentCustomerFirstNameTextField.setFocusable(false);
        currentCustomerFirstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentCustomerFirstNameTextFieldActionPerformed(evt);
            }
        });

        currentCustomerMiddleInitialTextField.setEditable(false);
        currentCustomerMiddleInitialTextField.setFocusable(false);

        currentCustomerLastNameTextField.setEditable(false);
        currentCustomerLastNameTextField.setFocusable(false);

        currentCustomerDescriptionTextArea.setEditable(false);
        currentCustomerDescriptionTextArea.setColumns(20);
        currentCustomerDescriptionTextArea.setRows(5);
        currentCustomerDescriptionTextArea.setFocusable(false);
        jScrollPane2.setViewportView(currentCustomerDescriptionTextArea);

        customerPictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage.png"))); // NOI18N

        jLabel1.setText("Customer Picture");

        addCustomerButton.setToolTipText("Add a Customer");
        addCustomerButton.setMaximumSize(new java.awt.Dimension(32, 32));
        addCustomerButton.setMinimumSize(new java.awt.Dimension(32, 32));
        addCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerButtonActionPerformed(evt);
            }
        });

        editCustomerButton.setToolTipText("Edit Customer");
        editCustomerButton.setMaximumSize(new java.awt.Dimension(32, 32));
        editCustomerButton.setMinimumSize(new java.awt.Dimension(32, 32));
        editCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCustomerButtonActionPerformed(evt);
            }
        });

        deleteCustomerButton.setToolTipText("Remove Customer");
        deleteCustomerButton.setMaximumSize(new java.awt.Dimension(32, 32));
        deleteCustomerButton.setMinimumSize(new java.awt.Dimension(32, 32));
        deleteCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customersPanelLayout = new javax.swing.GroupLayout(customersPanel);
        customersPanel.setLayout(customersPanelLayout);
        customersPanelLayout.setHorizontalGroup(
            customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customersPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customersLabel)
                    .addGroup(customersPanelLayout.createSequentialGroup()
                        .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(customersPanelLayout.createSequentialGroup()
                                .addComponent(addCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(65, 65, 65)
                        .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customerFirstNameLabel)
                            .addComponent(customerMiddleInitialLabel)
                            .addComponent(customerLastNameLabel)
                            .addComponent(customerDescriptionLabel))
                        .addGap(62, 62, 62)
                        .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(currentCustomerFirstNameTextField)
                            .addComponent(currentCustomerMiddleInitialTextField)
                            .addComponent(currentCustomerLastNameTextField))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(customerPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        customersPanelLayout.setVerticalGroup(
            customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customersPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customersLabel)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customersPanelLayout.createSequentialGroup()
                        .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(customerFirstNameLabel)
                                .addComponent(currentCustomerFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(customersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(customersPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(customerMiddleInitialLabel)
                                    .addComponent(currentCustomerMiddleInitialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(customersPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deleteCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(31, 31, 31)
                        .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customerLastNameLabel)
                            .addComponent(currentCustomerLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(customerPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerDescriptionLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Customers", customersPanel);

        vehiclesComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.mechanicsNotebookEngine.getVehicleArray()));
        vehiclesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehiclesComboBoxActionPerformed(evt);
            }
        });

        currentVehicleJComboBoxLabel.setText("Customer Vehicles");

        currentVehicleMakeTextField.setEditable(false);
        currentVehicleMakeTextField.setFocusable(false);

        currentVehicleModelTextField.setEditable(false);
        currentVehicleModelTextField.setFocusable(false);

        currentVehicleYearTextField.setEditable(false);
        currentVehicleYearTextField.setFocusable(false);

        currentVehicleOdometerTextField.setEditable(false);
        currentVehicleOdometerTextField.setFocusable(false);

        vehicleMakeLabel.setText("Make");

        vehicleModelLabel.setText("Model");

        vehicleYearLabel.setText("Year");

        vehicleOdometerLabel.setText("Odometer");

        currentVehicleDescriptionTextArea.setEditable(false);
        currentVehicleDescriptionTextArea.setColumns(20);
        currentVehicleDescriptionTextArea.setRows(5);
        currentVehicleDescriptionTextArea.setFocusable(false);
        jScrollPane4.setViewportView(currentVehicleDescriptionTextArea);

        vehicleDescriptionLabel.setText("Description");

        currentVehicleColorLabel.setText("Color");

        currentVehicleColorTextField.setEditable(false);
        currentVehicleColorTextField.setFocusable(false);

        currentVehicleVINLabel.setText("VIN");

        currentVehicleVINTextField.setEditable(false);
        currentVehicleVINTextField.setFocusable(false);

        vehiclePictureTextLabel.setText("Vehicle Picture");

        vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage.png"))); // NOI18N

        addVehicleButton.setToolTipText("Add a Vehicle");
        addVehicleButton.setMaximumSize(new java.awt.Dimension(32, 32));
        addVehicleButton.setMinimumSize(new java.awt.Dimension(32, 32));
        addVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVehicleButtonActionPerformed(evt);
            }
        });

        editVehicleButton.setToolTipText("Edit a Vehicle");
        editVehicleButton.setMaximumSize(new java.awt.Dimension(32, 32));
        editVehicleButton.setMinimumSize(new java.awt.Dimension(32, 32));
        editVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVehicleButtonActionPerformed(evt);
            }
        });

        removeVehicleButton.setToolTipText("Remove a Vehicle");
        removeVehicleButton.setMaximumSize(new java.awt.Dimension(32, 32));
        removeVehicleButton.setMinimumSize(new java.awt.Dimension(32, 32));
        removeVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeVehicleButtonActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setFloatable(false);

        removeVehicleButton.setToolTipText("Remove a Vehicle");
        removeVehicleButton2.setMaximumSize(new java.awt.Dimension(32, 32));
        removeVehicleButton2.setMinimumSize(new java.awt.Dimension(32, 32));
        removeVehicleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeVehicleButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(removeVehicleButton2);

        javax.swing.GroupLayout vehicleInfoPanelLayout = new javax.swing.GroupLayout(vehicleInfoPanel);
        vehicleInfoPanel.setLayout(vehicleInfoPanelLayout);
        vehicleInfoPanelLayout.setHorizontalGroup(
            vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                                            .addGap(44, 44, 44)
                                            .addComponent(currentVehicleJComboBoxLabel))
                                        .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                                            .addComponent(vehiclesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(51, 51, 51)
                                            .addComponent(vehicleMakeLabel))
                                        .addComponent(currentVehicleColorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(currentVehicleVINLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(vehicleOdometerLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                                        .addComponent(addVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(editVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(removeVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(vehicleModelLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(vehicleYearLabel, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentVehicleModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentVehicleMakeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentVehicleYearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentVehicleColorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentVehicleVINTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentVehicleOdometerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                                .addGap(214, 214, 214)
                                .addComponent(vehicleDescriptionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehicleInfoPanelLayout.createSequentialGroup()
                                .addComponent(vehiclePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehicleInfoPanelLayout.createSequentialGroup()
                                .addComponent(vehiclePictureTextLabel)
                                .addGap(93, 93, 93))))
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        vehicleInfoPanelLayout.setVerticalGroup(
            vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(vehiclePictureTextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vehiclePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(currentVehicleJComboBoxLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vehiclesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removeVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currentVehicleMakeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vehicleMakeLabel))
                        .addGap(18, 18, 18)
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                                .addComponent(vehicleModelLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(currentVehicleYearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vehicleYearLabel)))
                            .addComponent(currentVehicleColorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currentVehicleColorLabel)
                            .addComponent(currentVehicleModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentVehicleVINLabel)
                    .addComponent(currentVehicleVINTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentVehicleOdometerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicleOdometerLabel))
                .addGap(8, 8, 8)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicleDescriptionLabel)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        vehiclesTabbedPane.addTab("Vehicle Info", vehicleInfoPanel);

        maintenanceActionsLabel.setText("Maintenace Actions");

        updateMileageButton.setText("Update Mileage");
        updateMileageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMileageButtonActionPerformed(evt);
            }
        });

        addMaintenanceActionButton.setText("Add Maintenance Action");
        addMaintenanceActionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaintenanceActionButtonActionPerformed(evt);
            }
        });

        maintenanceActionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Odometer", "Maintenance Type", "Notes", "Performed By"
            }
        ));
        jScrollPane6.setViewportView(maintenanceActionsTable);

        javax.swing.GroupLayout vehicleMaintenanceActionsPanelLayout = new javax.swing.GroupLayout(vehicleMaintenanceActionsPanel);
        vehicleMaintenanceActionsPanel.setLayout(vehicleMaintenanceActionsPanelLayout);
        vehicleMaintenanceActionsPanelLayout.setHorizontalGroup(
            vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addGroup(vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                        .addGroup(vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                                .addGap(326, 326, 326)
                                .addComponent(maintenanceActionsLabel))
                            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                                .addGap(201, 201, 201)
                                .addComponent(updateMileageButton)
                                .addGap(178, 178, 178)
                                .addComponent(addMaintenanceActionButton)))
                        .addGap(0, 84, Short.MAX_VALUE))
                    .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6)))
                .addContainerGap())
        );
        vehicleMaintenanceActionsPanelLayout.setVerticalGroup(
            vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maintenanceActionsLabel)
                .addGap(23, 23, 23)
                .addGroup(vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateMileageButton)
                    .addComponent(addMaintenanceActionButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );

        vehiclesTabbedPane.addTab("Maintenance Actions", vehicleMaintenanceActionsPanel);

        javax.swing.GroupLayout vehicleWarrantiesPanelLayout = new javax.swing.GroupLayout(vehicleWarrantiesPanel);
        vehicleWarrantiesPanel.setLayout(vehicleWarrantiesPanelLayout);
        vehicleWarrantiesPanelLayout.setHorizontalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 729, Short.MAX_VALUE)
        );
        vehicleWarrantiesPanelLayout.setVerticalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        vehiclesTabbedPane.addTab("Warranties", vehicleWarrantiesPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 729, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        vehiclesTabbedPane.addTab("Modifications", jPanel1);

        javax.swing.GroupLayout vehiclesPanelLayout = new javax.swing.GroupLayout(vehiclesPanel);
        vehiclesPanel.setLayout(vehiclesPanelLayout);
        vehiclesPanelLayout.setHorizontalGroup(
            vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vehiclesTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        vehiclesPanelLayout.setVerticalGroup(
            vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vehiclesTabbedPane)
        );

        mainTabbedPane.addTab("Vehicles", vehiclesPanel);

        maintenanceTypesComboBox.setModel(new javax.swing.DefaultComboBoxModel(mechanicsNotebookEngine.getMaintenaceTypeArray()));
        maintenanceTypesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maintenanceTypesComboBoxActionPerformed(evt);
            }
        });

        actionTypesLable.setText("Action Types");

        actionTypeNameLabel.setText("Action Type Name");

        actionMileageIntervalLabel.setText("Mileage Interval");

        maintenanceTypeTextField.setEditable(false);
        maintenanceTypeTextField.setFocusable(false);

        maintenenaceTypeMileageIntervalTextField.setEditable(false);
        maintenenaceTypeMileageIntervalTextField.setFocusable(false);

        actionDescriptionLabel.setText("Description");

        maintenanceTypeDescriptionTextArea.setEditable(false);
        maintenanceTypeDescriptionTextArea.setColumns(20);
        maintenanceTypeDescriptionTextArea.setRows(5);
        maintenanceTypeDescriptionTextArea.setFocusable(false);
        jScrollPane5.setViewportView(maintenanceTypeDescriptionTextArea);

        newMaintenanceTypeButton.setText("New");
        newMaintenanceTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMaintenanceTypeButtonActionPerformed(evt);
            }
        });

        editMaintenanceTypeButton.setText("Edit");
        editMaintenanceTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMaintenanceTypeButtonActionPerformed(evt);
            }
        });

        deleteMaintenanceTypeButton.setText("Delete");
        deleteMaintenanceTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMaintenanceTypeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout maintenanceActionsTypesLayout = new javax.swing.GroupLayout(maintenanceActionsTypes);
        maintenanceActionsTypes.setLayout(maintenanceActionsTypesLayout);
        maintenanceActionsTypesLayout.setHorizontalGroup(
            maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                        .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, maintenanceActionsTypesLayout.createSequentialGroup()
                                .addComponent(maintenanceTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(actionTypeNameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(maintenanceTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                                .addComponent(editMaintenanceTypeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(338, 338, 338)
                                .addComponent(actionMileageIntervalLabel)
                                .addGap(18, 18, 18)
                                .addComponent(maintenenaceTypeMileageIntervalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                                .addComponent(deleteMaintenanceTypeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(actionDescriptionLabel)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(115, 115, 115))
                    .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                        .addComponent(actionTypesLable)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                        .addComponent(newMaintenanceTypeButton)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        maintenanceActionsTypesLayout.setVerticalGroup(
            maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(actionTypesLable)
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maintenanceTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(actionTypeNameLabel)))
                    .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(maintenanceTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newMaintenanceTypeButton)
                .addGap(12, 12, 12)
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maintenenaceTypeMileageIntervalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actionMileageIntervalLabel)
                    .addComponent(editMaintenanceTypeButton))
                .addGap(5, 5, 5)
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(actionDescriptionLabel)
                        .addComponent(deleteMaintenanceTypeButton)))
                .addContainerGap(223, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Maintenance Action Types", maintenanceActionsTypes);

        currentMechanicLabel.setText("Mechanic");

        currentMechanicTextField.setEditable(false);
        currentMechanicTextField.setFocusable(false);

        currentCustomerLabel.setText("Customer");

        currentCustomerTextField.setEditable(false);
        currentCustomerTextField.setFocusable(false);

        currentVehicleLabel.setText("Vehicle");

        currentVehicleTextField.setAutoscrolls(false);
        currentVehicleTextField.setFocusable(false);

        mainToolBar.setRollover(true);

        mechanicPanelNew.setBorder(compound);

        mechanicToolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        mechanicToolBar.setRollover(true);
        mechanicToolBar.setFloatable(false);

        mechanicsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "First", "Middle", "Last", "Description"
            }
        ));
        jScrollPane3.setViewportView(mechanicsTable);

        jLabel2.setText("Mechanics");

        javax.swing.GroupLayout mechanicPanelNewLayout = new javax.swing.GroupLayout(mechanicPanelNew);
        mechanicPanelNew.setLayout(mechanicPanelNewLayout);
        mechanicPanelNewLayout.setHorizontalGroup(
            mechanicPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel2)
                .addContainerGap(160, Short.MAX_VALUE))
            .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                .addComponent(mechanicToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );
        mechanicPanelNewLayout.setVerticalGroup(
            mechanicPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mechanicPanelNewLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(mechanicPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mechanicToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7))
        );

        customerPanelNew.setBorder(compound);

        customerToolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        customerToolBar.setRollover(true);
        customerToolBar.setFloatable(false);

        customersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "First", "Middle", "Last", "Description"
            }
        ));
        jScrollPane7.setViewportView(customersTable);

        jLabel3.setText("Customers");

        javax.swing.GroupLayout customerPanelNewLayout = new javax.swing.GroupLayout(customerPanelNew);
        customerPanelNew.setLayout(customerPanelNewLayout);
        customerPanelNewLayout.setHorizontalGroup(
            customerPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelNewLayout.createSequentialGroup()
                .addComponent(customerToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerPanelNewLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(121, 121, 121))
        );
        customerPanelNewLayout.setVerticalGroup(
            customerPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerPanelNewLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        vehiclePanelNew.setBorder(compound);

        vehicleToolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        vehicleToolBar.setRollover(true);
        vehicleToolBar.setFloatable(false);

        vehiclesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Make", "Model", "Year", "Vin"
            }
        ));
        jScrollPane8.setViewportView(vehiclesTable);

        jLabel4.setText("Vehicles");

        javax.swing.GroupLayout vehiclePanelNewLayout = new javax.swing.GroupLayout(vehiclePanelNew);
        vehiclePanelNew.setLayout(vehiclePanelNewLayout);
        vehiclePanelNewLayout.setHorizontalGroup(
            vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                .addComponent(vehicleToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        vehiclePanelNewLayout.setVerticalGroup(
            vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(vehicleToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
        );

        fileMenu.setText("File");

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
            .addComponent(mainToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(currentMechanicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(customerPanelNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mechanicPanelNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vehiclePanelNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 172, Short.MAX_VALUE)
                        .addComponent(currentCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(currentVehicleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(253, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(currentMechanicLabel)
                .addGap(226, 226, 226)
                .addComponent(currentCustomerLabel)
                .addGap(184, 184, 184)
                .addComponent(currentVehicleLabel)
                .addContainerGap(541, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(mainToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentVehicleLabel)
                    .addComponent(currentCustomerLabel)
                    .addComponent(currentMechanicLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentMechanicTextField)
                    .addComponent(currentCustomerTextField)
                    .addComponent(currentVehicleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mechanicPanelNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(customerPanelNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vehiclePanelNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
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

    private void currentCustomerFirstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentCustomerFirstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentCustomerFirstNameTextFieldActionPerformed

    /**
     * Method called when action on Customer Combo Box
     * <li> if selected Customer == current, do NOTHING!
     * <li> if not equal, 
     * @param evt 
     */
    private void customersComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customersComboBoxActionPerformed
        // TODO add your handling code here:      
        if(this.customersComboBox.getSelectedItem()!=null){
            Customer customerSelected = (Customer)this.customersComboBox.getSelectedItem();
            if(customerSelected.equals(this.mechanicsNotebookEngine.getCurrentCustomer())){
                return;
            }else{
                this.mechanicsNotebookEngine.setCurrentCustomer(customerSelected);
                //this.customersComboBox.setSelectedItem(customerSelected); // necessary?
                this.refresh();
            }  
        }
    }//GEN-LAST:event_customersComboBoxActionPerformed

    /**
     * Method used when action on Mechanics Combo Box
     * <li>if selected mechanic == current mechanic, DO NOTHING
     * <li>else, set the current mechanic as the user selected one
     * @param evt 
     */
    private void mechanicsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mechanicsComboBoxActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsComboBox.getSelectedItem()!=null){
            Mechanic mechanicSelected = (Mechanic)this.mechanicsComboBox.getSelectedItem();
            if(mechanicSelected.equals(this.mechanicsNotebookEngine.getCurrentMechanic())){
                return;
            }else{
                this.mechanicsNotebookEngine.setCurrentMechanic(mechanicSelected);
                this.refresh();
            }  
        }
    }//GEN-LAST:event_mechanicsComboBoxActionPerformed

    private void vehiclesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehiclesComboBoxActionPerformed
        // TODO add your handling code here:
        if(this.vehiclesComboBox.getSelectedItem()!= null){
            Vehicle vehicleSelected = (Vehicle) this.vehiclesComboBox.getSelectedItem();
            if(vehicleSelected.equals(this.mechanicsNotebookEngine.getCurrentVehicle())){
                return;
            }else{
                this.mechanicsNotebookEngine.setCurrentVehicle(vehicleSelected);
                this.refresh();;
            }
        }
    }//GEN-LAST:event_vehiclesComboBoxActionPerformed

    private void newMaintenanceTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMaintenanceTypeButtonActionPerformed
        // TODO add your handling code here:
        this.mechanicsNotebookEngine.startNewMaintenanceTypeWindow();
    }//GEN-LAST:event_newMaintenanceTypeButtonActionPerformed

    private void maintenanceTypesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintenanceTypesComboBoxActionPerformed
        // TODO add your handling code here:
        this.maintenanceTypesComboBox.setSelectedItem(this.maintenanceTypesComboBox.getSelectedItem());
        this.refresh();
    }//GEN-LAST:event_maintenanceTypesComboBoxActionPerformed

    private void addMaintenanceActionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMaintenanceActionButtonActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentVehicle()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must have a Vehicle to add a Maintenance Action!");
            return;
        }else if(this.mechanicsNotebookEngine.getCurrentMechanic() == null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must have a current Mechanic to create a Maintenance Action!");
        }else if(!this.mechanicsNotebookEngine.hasMaintenanceTypes()){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must have at least one Maintenance Type before adding a Maintenace Action! Please create a Maintenance Type first.");
        }           
        else{
            this.mechanicsNotebookEngine.startNewMaintenanceActionWindow();
        }
    }//GEN-LAST:event_addMaintenanceActionButtonActionPerformed

    private void updateMileageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMileageButtonActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentVehicle()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must have a Vehicle to add a Maintenance Action!");
        }else{
            this.mechanicsNotebookEngine.startNewUpdateMileageWindow();
        }
    }//GEN-LAST:event_updateMileageButtonActionPerformed

    
    /**
     * Method used when a user hits the Edit Maintenance Type button
     * <li> will pass along the selected Maintenance Action to the Engine to be processed
     * @param evt 
     */
    private void editMaintenanceTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMaintenanceTypeButtonActionPerformed
        // TODO add your handling code here:
        if(this.maintenanceTypesComboBox.getSelectedItem()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, "You don't have any Maintenance Types to edit!");
            return;
        }
        MaintenanceType selectedMaintenanceType = (MaintenanceType)this.maintenanceTypesComboBox.getSelectedItem();
        this.mechanicsNotebookEngine.startUpdateMaintenanceTypeWindow(selectedMaintenanceType);
    }//GEN-LAST:event_editMaintenanceTypeButtonActionPerformed

    private void deleteMaintenanceTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMaintenanceTypeButtonActionPerformed
        // TODO add your handling code here:
        if(this.maintenanceTypesComboBox.getSelectedItem()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, "You don't have any Maintenance Types to delete!");
            return;
        }
        boolean sureToDelete = this.mechanicsNotebookEngine.getDialogFactory().createConfirmMessage("Are you sure you wish to delete the Maintenance Type? This is permanent!");
            if(sureToDelete){
                MaintenanceType maintenanceTypeToDelete = (MaintenanceType)this.maintenanceTypesComboBox.getSelectedItem();
                if(this.mechanicsNotebookEngine.deleteMaintenanceType(maintenanceTypeToDelete)){
                    this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, "Maintenance Type deleted successfully!");
                }else{
                    this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.ERROR_MESSAGE, "Error attempting to delete Maintenace Type! Please report!");
                }
            }            
        
    }//GEN-LAST:event_deleteMaintenanceTypeButtonActionPerformed

    private void addVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVehicleButtonActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentCustomer()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must first have a Customer in order to add a Vehicle! Please create a Customer first.");
        }else{
            this.mechanicsNotebookEngine.startNewVehicleWindow();
        }
    }//GEN-LAST:event_addVehicleButtonActionPerformed

    private void removeVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeVehicleButtonActionPerformed
        // TODO add your handling code here:
         if(this.mechanicsNotebookEngine.getCurrentVehicle()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You have not selected a Vehicle to delete!");
        }else{
            boolean sureToDelete = this.mechanicsNotebookEngine.getDialogFactory().createConfirmMessage("Are you sure you wish to delete the current Vehicle? This is permanent!");
            if(sureToDelete){
                this.mechanicsNotebookEngine.deleteCurrentVehicle();
            }            
        }
    }//GEN-LAST:event_removeVehicleButtonActionPerformed

    private void editVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVehicleButtonActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentVehicle()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, "You don't have a Vehicle to edit!");
        }else{
            this.mechanicsNotebookEngine.startUpdateVehicleWindow();
        }
    }//GEN-LAST:event_editVehicleButtonActionPerformed

    private void addMechanicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMechanicButtonActionPerformed
        // User hit create new Mechanic button, let's do it!
        this.mechanicsNotebookEngine.startNewMechanicWindow();
    }//GEN-LAST:event_addMechanicButtonActionPerformed

    private void editMechanicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMechanicButtonActionPerformed
        if(this.mechanicsNotebookEngine.getCurrentMechanic()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, "You don't have a Mechanic to edit!");
        }else{
            this.mechanicsNotebookEngine.startUpdateMechanicWindow();
        }
    }//GEN-LAST:event_editMechanicButtonActionPerformed

    private void removeMechanicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeMechanicButtonActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentMechanic()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You have not selected a Mechanic to delete!");
        }else{
            boolean sureToDelete = this.mechanicsNotebookEngine.getDialogFactory().createConfirmMessage("Are you sure you wish to delete the current Mechanic? This is permanent!");
            if(sureToDelete){
                this.mechanicsNotebookEngine.deleteCurrentMechanic();
            }            
        }
    }//GEN-LAST:event_removeMechanicButtonActionPerformed

    private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustomerButtonActionPerformed
        // User pressed the new customer button, time to enact
        this.mechanicsNotebookEngine.startNewCustomerWindow();
    }//GEN-LAST:event_addCustomerButtonActionPerformed

    private void editCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCustomerButtonActionPerformed
        if(this.mechanicsNotebookEngine.getCurrentCustomer()==null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE, "You don't have a Customer to edit!");
        }else{
            this.mechanicsNotebookEngine.startUpdateCustomerWindow();
        }
    }//GEN-LAST:event_editCustomerButtonActionPerformed

    private void deleteCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerButtonActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentCustomer()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You have not selected a Customer to delete!");
        }else{
            boolean sureToDelete = this.mechanicsNotebookEngine.getDialogFactory().createConfirmMessage("Are you sure you wish to delete the current Customer? This is permanent!");
            if(sureToDelete){
                this.mechanicsNotebookEngine.deleteCurrentCustomer();
            }            
        }
    }//GEN-LAST:event_deleteCustomerButtonActionPerformed

    private void removeVehicleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeVehicleButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeVehicleButton2ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel actionDescriptionLabel;
    private javax.swing.JLabel actionMileageIntervalLabel;
    private javax.swing.JLabel actionTypeNameLabel;
    private javax.swing.JLabel actionTypesLable;
    private javax.swing.JButton addCustomerButton;
    private javax.swing.JButton addMaintenanceActionButton;
    private javax.swing.JButton addMechanicButton;
    private javax.swing.JButton addVehicleButton;
    private javax.swing.JTextArea currentCustomerDescriptionTextArea;
    private javax.swing.JTextField currentCustomerFirstNameTextField;
    private javax.swing.JLabel currentCustomerLabel;
    private javax.swing.JTextField currentCustomerLastNameTextField;
    private javax.swing.JTextField currentCustomerMiddleInitialTextField;
    private javax.swing.JTextField currentCustomerTextField;
    private javax.swing.JLabel currentMechanicDescription;
    private javax.swing.JTextArea currentMechanicDescriptionTextArea;
    private javax.swing.JLabel currentMechanicFirstNameLabel;
    private javax.swing.JTextField currentMechanicFirstNameTextField;
    private javax.swing.JLabel currentMechanicLabel;
    private javax.swing.JLabel currentMechanicLastNameLabel;
    private javax.swing.JTextField currentMechanicLastNameTextField;
    private javax.swing.JLabel currentMechanicMiddleNameLabel;
    private javax.swing.JTextField currentMechanicMiddleNameTextField;
    private javax.swing.JTextField currentMechanicTextField;
    private javax.swing.JLabel currentVehicleColorLabel;
    private javax.swing.JTextField currentVehicleColorTextField;
    private javax.swing.JTextArea currentVehicleDescriptionTextArea;
    private javax.swing.JLabel currentVehicleJComboBoxLabel;
    private javax.swing.JLabel currentVehicleLabel;
    private javax.swing.JTextField currentVehicleMakeTextField;
    private javax.swing.JTextField currentVehicleModelTextField;
    private javax.swing.JTextField currentVehicleOdometerTextField;
    private javax.swing.JTextField currentVehicleTextField;
    private javax.swing.JLabel currentVehicleVINLabel;
    private javax.swing.JTextField currentVehicleVINTextField;
    private javax.swing.JTextField currentVehicleYearTextField;
    private javax.swing.JLabel customerDescriptionLabel;
    private javax.swing.JLabel customerFirstNameLabel;
    private javax.swing.JLabel customerLastNameLabel;
    private javax.swing.JLabel customerMiddleInitialLabel;
    private javax.swing.JPanel customerPanelNew;
    private javax.swing.JLabel customerPictureLabel;
    private javax.swing.JToolBar customerToolBar;
    private javax.swing.JComboBox customersComboBox;
    private javax.swing.JLabel customersLabel;
    private javax.swing.JPanel customersPanel;
    private javax.swing.JTable customersTable;
    private javax.swing.JButton deleteCustomerButton;
    private javax.swing.JButton deleteMaintenanceTypeButton;
    private javax.swing.JButton editCustomerButton;
    private javax.swing.JButton editMaintenanceTypeButton;
    private javax.swing.JButton editMechanicButton;
    private javax.swing.JButton editVehicleButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JToolBar mainToolBar;
    private javax.swing.JLabel maintenanceActionsLabel;
    private javax.swing.JTable maintenanceActionsTable;
    private javax.swing.JPanel maintenanceActionsTypes;
    private javax.swing.JTextArea maintenanceTypeDescriptionTextArea;
    private javax.swing.JTextField maintenanceTypeTextField;
    private javax.swing.JComboBox maintenanceTypesComboBox;
    private javax.swing.JTextField maintenenaceTypeMileageIntervalTextField;
    private javax.swing.JPanel mechanicPanelNew;
    private javax.swing.JLabel mechanicPictureLabel;
    private javax.swing.JLabel mechanicPictureTextLabel;
    private javax.swing.JToolBar mechanicToolBar;
    private javax.swing.JComboBox mechanicsComboBox;
    private javax.swing.JLabel mechanicsLabel;
    private javax.swing.JMenuBar mechanicsNotebookMenuBar;
    private javax.swing.JPanel mechanicsPanel;
    private javax.swing.JTable mechanicsTable;
    private javax.swing.JMenuItem newGarageMenuItem;
    private javax.swing.JButton newMaintenanceTypeButton;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JButton removeMechanicButton;
    private javax.swing.JButton removeVehicleButton;
    private javax.swing.JButton removeVehicleButton2;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton updateMileageButton;
    private javax.swing.JLabel vehicleDescriptionLabel;
    private javax.swing.JPanel vehicleInfoPanel;
    private javax.swing.JPanel vehicleMaintenanceActionsPanel;
    private javax.swing.JLabel vehicleMakeLabel;
    private javax.swing.JLabel vehicleModelLabel;
    private javax.swing.JLabel vehicleOdometerLabel;
    private javax.swing.JPanel vehiclePanelNew;
    private javax.swing.JLabel vehiclePictureLabel;
    private javax.swing.JLabel vehiclePictureTextLabel;
    private javax.swing.JToolBar vehicleToolBar;
    private javax.swing.JPanel vehicleWarrantiesPanel;
    private javax.swing.JLabel vehicleYearLabel;
    private javax.swing.JComboBox vehiclesComboBox;
    private javax.swing.JPanel vehiclesPanel;
    private javax.swing.JTabbedPane vehiclesTabbedPane;
    private javax.swing.JTable vehiclesTable;
    // End of variables declaration//GEN-END:variables
}
