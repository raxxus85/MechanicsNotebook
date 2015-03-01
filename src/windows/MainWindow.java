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
        this.refreshMaintenaceTypeTab();
        
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
        this.refreshMaintenanceActions();
        this.refreshVehicleList();
        this.refreshCurrentVehicleInformation();
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
                currentRowSelected = mechanicsTable.getSelectedRow();
                this.mechanicsNotebookEngine.setCurrentMechanic(mechanics[0]);
            }else if(currentRowSelected==-1 && this.mechanicsNotebookEngine.getCurrentMechanic() == null){
                ListSelectionModel selectionModel =mechanicsTable.getSelectionModel();
                selectionModel.setSelectionInterval(0, 0);
                currentRowSelected = mechanicsTable.getSelectedRow();
                this.mechanicsNotebookEngine.setCurrentMechanic(mechanics[0]);
            }
                 
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
            Vehicle[] vehicles = this.mechanicsNotebookEngine.getVehicleArray();
            int newRowCount = vehicles.length;
            for (int i = 0  ; i <newRowCount ; i++) {
                Object[]vehicleObject = vehicles[i].getVehicleObject();
                model.addRow(vehicleObject);                      
            }
            
            if(this.mechanicsNotebookEngine.getVehicleArray().length >0){
            Vehicle[] vehicle = this.mechanicsNotebookEngine.getVehicleArray();  //?????????????????????          
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
        vehicleWarrantiesPanel = new javax.swing.JPanel();
        modificationsPanel = new javax.swing.JPanel();
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
        mainToolBar = new javax.swing.JToolBar();
        ImageIcon maintenanceActionAdd = new ImageIcon(getClass().getResource("/maintenanceAction32x32ADD.png"));
        Action actionMaintenanceActionAdd = new AbstractAction("New", maintenanceActionAdd) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("MaintenanceActionAdd add");
            }
        };
        addMaintenanceActionButton = mainToolBar.add(actionMaintenanceActionAdd);
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
        ImageIcon vehicleDelete = new ImageIcon(getClass().getResource("/vehicle32x32Remove.png"));
        Action actionVehicleDelete = new AbstractAction("New", vehicleDelete) {
            public void actionPerformed(ActionEvent e) {
                System.out.println("vehicle Remove");
            }
        };
        vehicleDeleteButtonNew = vehicleToolBar.add(actionVehicleDelete);
        jScrollPane8 = new javax.swing.JScrollPane();
        vehiclesTable = new javax.swing.JTable();
        vehiclesLabel = new javax.swing.JLabel();
        currentVehicleMaintenanceAlertLabel = new javax.swing.JLabel();
        currentVehiclePanel = new javax.swing.JPanel();
        vehiclePictureLabel = new javax.swing.JLabel();
        currentVehicleLabel = new javax.swing.JLabel();
        vehicleNameLabel = new javax.swing.JLabel();
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Odometer", "Maintenance Type", "Notes", "Performed By"
            }
        ));
        maintenanceActionsTable.setOpaque(true);
        maintenanceActionsTable.setFillsViewportHeight(true);
        maintenanceActionsTable.setBackground(Color.WHITE);
        jScrollPane6.setViewportView(maintenanceActionsTable);

        maintenanceActionsToolBar.setRollover(true);
        maintenanceActionsToolBar.setFloatable(false);

        addMaintenanceActionButtonToolBar.setToolTipText("Add Maintenance Action to Current Vehicle");
        addMaintenanceActionButtonToolBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMaintenanceActionButtonToolBarActionPerformed(evt);
            }
        });
        maintenanceActionsToolBar.add(addMaintenanceActionButtonToolBar);

        editMaintenanceActionButtonToolBar.setToolTipText("Edit Maintenance Action");
        maintenanceActionsToolBar.add(editMaintenanceActionButtonToolBar);

        deleteMaintenanceActionButtonToolBar.setToolTipText("Delete Maintenance Action");
        maintenanceActionsToolBar.add(deleteMaintenanceActionButtonToolBar);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(compound);
        maintenanceActionsToolBar.add(jSeparator1);

        updateOdometerActionButtonToolBar.setToolTipText("Update Odometer");
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
                .addComponent(maintenanceActionsToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout vehicleWarrantiesPanelLayout = new javax.swing.GroupLayout(vehicleWarrantiesPanel);
        vehicleWarrantiesPanel.setLayout(vehicleWarrantiesPanelLayout);
        vehicleWarrantiesPanelLayout.setHorizontalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        vehicleWarrantiesPanelLayout.setVerticalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Warranties", vehicleWarrantiesPanel);

        javax.swing.GroupLayout modificationsPanelLayout = new javax.swing.GroupLayout(modificationsPanel);
        modificationsPanel.setLayout(modificationsPanelLayout);
        modificationsPanelLayout.setHorizontalGroup(
            modificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        modificationsPanelLayout.setVerticalGroup(
            modificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        mainTabbedPane.addTab("Modifications", modificationsPanel);

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
                .addContainerGap(243, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Maintenance Types", maintenanceActionsTypes);

        mainToolBar.setRollover(true);
        mainToolBar.setFloatable(false);

        addMaintenanceActionButton.setToolTipText("Add Maintenance Action to Current Vehicle");
        mainToolBar.add(addMaintenanceActionButton);

        mechanicPanelNew.setBorder(compound);

        mechanicToolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        mechanicToolBar.setRollover(true);
        mechanicToolBar.setFloatable(false);
        mechanicToolBar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                mechanicToolBarAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        mechanicAddButtonNew.setToolTipText("Add a Mechanic");
        mechanicAddButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mechanicAddButtonNewActionPerformed(evt);
            }
        });
        mechanicToolBar.add(mechanicAddButtonNew);

        mechanicButtonEditNew.setToolTipText("View / Edit Selected Mechanic");
        mechanicButtonEditNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mechanicButtonEditNewActionPerformed(evt);
            }
        });
        mechanicToolBar.add(mechanicButtonEditNew);

        mechanicDeleteButtonNew.setToolTipText("Delete Selected Mechanic");
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
                .addGap(146, 146, 146)
                .addComponent(mechanicsLabel))
            .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                .addComponent(mechanicToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mechanicPanelNewLayout.setVerticalGroup(
            mechanicPanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(mechanicsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(mechanicPanelNewLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(mechanicToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        customerPanelNew.setBorder(compound);

        customerToolBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        customerToolBar.setRollover(true);
        customerToolBar.setFloatable(false);

        customerAddButtonNew.setToolTipText("Add Customer");
        customerAddButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerAddButtonNewActionPerformed(evt);
            }
        });
        customerToolBar.add(customerAddButtonNew);

        customerEditButtonNew.setToolTipText("View / Edit Customer");
        customerEditButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerEditButtonNewActionPerformed(evt);
            }
        });
        customerToolBar.add(customerEditButtonNew);

        customerDeleteButtonNew.setToolTipText("Delete Customer");
        customerDeleteButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerDeleteButtonNewActionPerformed(evt);
            }
        });
        customerToolBar.add(customerDeleteButtonNew);

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
                .addComponent(customerToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerPanelNewLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(customersLabel)
                .addGap(121, 121, 121))
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
        vehicleAddButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleAddButtonNewActionPerformed(evt);
            }
        });
        vehicleToolBar.add(vehicleAddButtonNew);

        vehicleEditButtonNew.setToolTipText("View / Edit Vehicle");
        vehicleEditButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleEditButtonNewActionPerformed(evt);
            }
        });
        vehicleToolBar.add(vehicleEditButtonNew);

        vehicleDeleteButtonNew.setToolTipText("Delete Selected Vehicle");
        vehicleDeleteButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleDeleteButtonNewActionPerformed(evt);
            }
        });
        vehicleToolBar.add(vehicleDeleteButtonNew);

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

        currentVehicleMaintenanceAlertLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/maintenanceAction32x32EDIT.png"))); // NOI18N

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
                .addComponent(vehiclesLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                    .addGap(177, 177, 177)
                    .addComponent(currentVehicleMaintenanceAlertLabel)
                    .addContainerGap(178, Short.MAX_VALUE)))
        );
        vehiclePanelNewLayout.setVerticalGroup(
            vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(vehiclesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(vehicleToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
            .addGroup(vehiclePanelNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(vehiclePanelNewLayout.createSequentialGroup()
                    .addGap(80, 80, 80)
                    .addComponent(currentVehicleMaintenanceAlertLabel)
                    .addContainerGap(80, Short.MAX_VALUE)))
        );

        currentVehiclePanel.setBorder(compound);

        vehiclePictureLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/noImage50x50.png"))); // NOI18N

        currentVehicleLabel.setText("Vehicle");

        vehicleNameLabel.setText("No Vehicle Selected");

        javax.swing.GroupLayout currentVehiclePanelLayout = new javax.swing.GroupLayout(currentVehiclePanel);
        currentVehiclePanel.setLayout(currentVehiclePanelLayout);
        currentVehiclePanelLayout.setHorizontalGroup(
            currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                .addComponent(vehiclePictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                        .addComponent(currentVehicleLabel)
                        .addGap(161, 161, 161))
                    .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                        .addComponent(vehicleNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        currentVehiclePanelLayout.setVerticalGroup(
            currentVehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentVehiclePanelLayout.createSequentialGroup()
                .addComponent(currentVehicleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGap(18, 18, 18)
                        .addComponent(mechanicNameLabel))
                    .addComponent(mechanicPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                    .addComponent(mainTabbedPane)))
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

    private void mechanicToolBarAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_mechanicToolBarAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_mechanicToolBarAncestorAdded

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

    private void newMaintenanceTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMaintenanceTypeButtonActionPerformed
        // TODO add your handling code here:
        this.mechanicsNotebookEngine.startNewMaintenanceTypeWindow();
    }//GEN-LAST:event_newMaintenanceTypeButtonActionPerformed

    private void maintenanceTypesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maintenanceTypesComboBoxActionPerformed
        // TODO add your handling code here:
        this.maintenanceTypesComboBox.setSelectedItem(this.maintenanceTypesComboBox.getSelectedItem());
        this.refresh();
    }//GEN-LAST:event_maintenanceTypesComboBoxActionPerformed

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
            return;
        }else if(this.mechanicsNotebookEngine.getCurrentMechanic() == null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must have a current Mechanic to create a Maintenance Action!");
        }else if(!this.mechanicsNotebookEngine.hasMaintenanceTypes()){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must have at least one Maintenance Type before adding a Maintenace Action! Please create a Maintenance Type first.");
        }
        else{
            this.mechanicsNotebookEngine.startNewMaintenanceActionWindow();
        }
    }//GEN-LAST:event_addMaintenanceActionButtonToolBarActionPerformed

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
    private javax.swing.JButton addMaintenanceActionButton;
    private javax.swing.JButton addMaintenanceActionButtonToolBar;
    private javax.swing.JLabel currentCustomerLabel;
    private javax.swing.JPanel currentCustomerPanel;
    private javax.swing.JLabel currentMechanicLabel;
    private javax.swing.JPanel currentMechanicPanel;
    private javax.swing.JLabel currentVehicleLabel;
    private javax.swing.JLabel currentVehicleMaintenanceAlertLabel;
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
    private javax.swing.JButton deleteMaintenanceTypeButton;
    private javax.swing.JButton editMaintenanceActionButtonToolBar;
    private javax.swing.JButton editMaintenanceTypeButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JToolBar mainToolBar;
    private javax.swing.JTable maintenanceActionsTable;
    private javax.swing.JToolBar maintenanceActionsToolBar;
    private javax.swing.JPanel maintenanceActionsTypes;
    private javax.swing.JTextArea maintenanceTypeDescriptionTextArea;
    private javax.swing.JTextField maintenanceTypeTextField;
    private javax.swing.JComboBox maintenanceTypesComboBox;
    private javax.swing.JTextField maintenenaceTypeMileageIntervalTextField;
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
    private javax.swing.JButton newMaintenanceTypeButton;
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
