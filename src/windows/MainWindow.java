/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import java.io.File;
import javax.swing.JFileChooser;
import objectmodels.Mechanic;
import engine.MechanicsNotebookEngine;
import informationwindows.DialogType;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
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
    DefaultListModel model = new DefaultListModel();
    //JList maintenanceActionList2;
    
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
        
        MouseListener mouseListener = new MouseAdapter2(this.mechanicsNotebookEngine) {
        public void mouseClicked(MouseEvent mouseEvent) {
            JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 2) {
                int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        System.out.println("Double-clicked on: " + o.toString());
                        // Time to have the ENGINE OPEN THE MAINTENANCE ACTION EDIT WINDOW!!!
                        mechanicsNotebookEngine.startMaintenanceActionWindow((MaintenanceAction)o);

                    }
            }
            
        }
    };
            
        //this.mainTabbedPane.setBackground(Color.black);

        this.mechanicsNotebookEngine = incomingMotoGarageMechanicEngine;
        initComponents();
        maintenanceActionList2.addMouseListener(mouseListener);
        /**
        this.getContentPane().setBackground(Color.gray);
        this.mainTabbedPane.setBackground(Color.gray);
        this.mechanicsPanel.setBackground(Color.gray);
        * **/
        this.setIcon();
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
    }
    
    /**
     * One of the most important Refresh methods, refeshes all the maintenance actions for current vehicle
     */
    private void refreshMaintenanceActions(){
        this.maintenanceActionList2.removeAll();
        model.removeAllElements();
        //this.maintenanceActionList2.removeAll();
        //this.model.removeAllElements();
        //model = new DefaultListModel();
        maintenanceActionList2 = new JList(model);
        
        if(this.mechanicsNotebookEngine.getCurrentVehicle()!=null){
            MaintenanceAction[] currentMaintenanceActions = this.mechanicsNotebookEngine.getCurrentVehicle().getMaintenanceActionsArray();
            for(MaintenanceAction maintenanceAction : currentMaintenanceActions){
                // NEW
                model.addElement(maintenanceAction);
                //maintenanceActionList2 = new JList(model);


            }
        }
    }
    
    /**
     * SUPER IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * **/
    

    
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
        }else{
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
        }else{
        this.currentCustomerFirstNameTextField.setText(currentCustomer.getFirstName());
        this.currentCustomerMiddleInitialTextField.setText(currentCustomer.getMiddleInitial());
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
        this.mechanicPictureLabel.setIcon(null);
        }else{
        if(currentMechanic.getImageIcon()!=null){
            this.mechanicPictureLabel.setIcon(currentMechanic.getImageIcon());
        }
        this.currentMechanicFirstNameTextField.setText(currentMechanic.getFirstName());
        this.currentMechanicMiddleNameTextField.setText(currentMechanic.getMiddleInitial());
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
        editMechanicButton = new javax.swing.JButton();
        deleteMechanicButton = new javax.swing.JButton();
        createNewMechanicButton = new javax.swing.JButton();
        mechanicPictureLabel = new javax.swing.JLabel();
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
        newCustomerButton = new javax.swing.JButton();
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
        newVehicleButton = new javax.swing.JButton();
        currentVehicleColorLabel = new javax.swing.JLabel();
        currentVehicleColorTextField = new javax.swing.JTextField();
        currentVehicleVINLabel = new javax.swing.JLabel();
        currentVehicleVINTextField = new javax.swing.JTextField();
        vehicleMaintenanceActionsPanel = new javax.swing.JPanel();
        maintenanceActionsLabel = new javax.swing.JLabel();
        updateMileageButton = new javax.swing.JButton();
        addMaintenanceActionButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        maintenanceActionList2 = new javax.swing.JList();
        vehicleWarrantiesPanel = new javax.swing.JPanel();
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
        currentMechanicLabel = new javax.swing.JLabel();
        currentMechanicTextField = new javax.swing.JTextField();
        currentCustomerLabel = new javax.swing.JLabel();
        currentCustomerTextField = new javax.swing.JTextField();
        currentVehicleLabel = new javax.swing.JLabel();
        currentVehicleTextField = new javax.swing.JTextField();
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

        editMechanicButton.setText("Edit Mechanic");

        deleteMechanicButton.setText("Delete Mechanic");
        deleteMechanicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMechanicButtonActionPerformed(evt);
            }
        });

        createNewMechanicButton.setText(" New Mechanic");
        createNewMechanicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewMechanicButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mechanicsPanelLayout = new javax.swing.GroupLayout(mechanicsPanel);
        mechanicsPanel.setLayout(mechanicsPanelLayout);
        mechanicsPanelLayout.setHorizontalGroup(
            mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mechanicsPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(mechanicsLabel))
                    .addGroup(mechanicsPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mechanicsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editMechanicButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteMechanicButton, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(createNewMechanicButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currentMechanicFirstNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(currentMechanicMiddleNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(currentMechanicLastNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(currentMechanicDescription, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(55, 55, 55)
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currentMechanicMiddleNameTextField)
                            .addComponent(currentMechanicFirstNameTextField)
                            .addComponent(currentMechanicLastNameTextField)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))))
                .addGap(27, 27, 27)
                .addComponent(mechanicPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        mechanicsPanelLayout.setVerticalGroup(
            mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(mechanicsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mechanicsPanelLayout.createSequentialGroup()
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mechanicsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currentMechanicFirstNameLabel)
                            .addComponent(currentMechanicFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(currentMechanicMiddleNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentMechanicMiddleNameLabel)
                                    .addComponent(createNewMechanicButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentMechanicLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currentMechanicLastNameLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mechanicsPanelLayout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(currentMechanicDescription)
                                        .addGap(5, 5, 5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mechanicsPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(editMechanicButton)
                                        .addGap(18, 18, 18)))
                                .addComponent(deleteMechanicButton))))
                    .addComponent(mechanicPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(303, Short.MAX_VALUE))
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

        newCustomerButton.setText("New Customer");
        newCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCustomerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customersPanelLayout = new javax.swing.GroupLayout(customersPanel);
        customersPanel.setLayout(customersPanelLayout);
        customersPanelLayout.setHorizontalGroup(
            customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customersPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customersPanelLayout.createSequentialGroup()
                        .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(customersComboBox, 0, 184, Short.MAX_VALUE)
                            .addComponent(newCustomerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(currentCustomerLastNameTextField)))
                    .addComponent(customersLabel))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        customersPanelLayout.setVerticalGroup(
            customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customersPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(customersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customerFirstNameLabel)
                        .addComponent(currentCustomerFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(customersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerMiddleInitialLabel)
                    .addComponent(currentCustomerMiddleInitialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newCustomerButton))
                .addGap(18, 18, 18)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerLastNameLabel)
                    .addComponent(currentCustomerLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerDescriptionLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(337, Short.MAX_VALUE))
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

        newVehicleButton.setText("New Vehicle");
        newVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newVehicleButtonActionPerformed(evt);
            }
        });

        currentVehicleColorLabel.setText("Color");

        currentVehicleColorTextField.setEditable(false);
        currentVehicleColorTextField.setFocusable(false);

        currentVehicleVINLabel.setText("VIN");

        currentVehicleVINTextField.setEditable(false);
        currentVehicleVINTextField.setFocusable(false);

        javax.swing.GroupLayout vehicleInfoPanelLayout = new javax.swing.GroupLayout(vehicleInfoPanel);
        vehicleInfoPanel.setLayout(vehicleInfoPanelLayout);
        vehicleInfoPanelLayout.setHorizontalGroup(
            vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehiclesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(currentVehicleJComboBoxLabel))
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(newVehicleButton)))
                .addGap(217, 217, 217)
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addComponent(vehicleMakeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(currentVehicleMakeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehicleInfoPanelLayout.createSequentialGroup()
                        .addComponent(vehicleDescriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addComponent(vehicleModelLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(currentVehicleModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehicleInfoPanelLayout.createSequentialGroup()
                        .addComponent(vehicleYearLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(currentVehicleYearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehicleInfoPanelLayout.createSequentialGroup()
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vehicleOdometerLabel)
                            .addComponent(currentVehicleColorLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(currentVehicleColorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehicleInfoPanelLayout.createSequentialGroup()
                        .addComponent(currentVehicleVINLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currentVehicleOdometerTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(currentVehicleVINTextField))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        vehicleInfoPanelLayout.setVerticalGroup(
            vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(currentVehicleMakeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vehicleMakeLabel))
                        .addGap(16, 16, 16)
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(currentVehicleModelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vehicleModelLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currentVehicleYearTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vehicleYearLabel)))
                    .addGroup(vehicleInfoPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(currentVehicleJComboBoxLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vehiclesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newVehicleButton)))
                .addGap(18, 18, 18)
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentVehicleColorLabel)
                    .addComponent(currentVehicleColorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentVehicleVINLabel)
                    .addComponent(currentVehicleVINTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentVehicleOdometerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicleOdometerLabel))
                .addGap(38, 38, 38)
                .addGroup(vehicleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicleDescriptionLabel))
                .addContainerGap(174, Short.MAX_VALUE))
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

        maintenanceActionList2.setModel(model);
        maintenanceActionList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maintenanceActionList2MouseClicked(evt);
            }
        });
        maintenanceActionList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                maintenanceActionList2ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(maintenanceActionList2);

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
                        .addGap(0, 127, Short.MAX_VALUE))
                    .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        vehicleMaintenanceActionsPanelLayout.setVerticalGroup(
            vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maintenanceActionsLabel)
                .addGap(17, 17, 17)
                .addGroup(vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateMileageButton)
                    .addComponent(addMaintenanceActionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );

        vehiclesTabbedPane.addTab("Vehicle Maintenance Actions", vehicleMaintenanceActionsPanel);

        javax.swing.GroupLayout vehicleWarrantiesPanelLayout = new javax.swing.GroupLayout(vehicleWarrantiesPanel);
        vehicleWarrantiesPanel.setLayout(vehicleWarrantiesPanelLayout);
        vehicleWarrantiesPanelLayout.setHorizontalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        vehicleWarrantiesPanelLayout.setVerticalGroup(
            vehicleWarrantiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        vehiclesTabbedPane.addTab("Vehicle Warranties", vehicleWarrantiesPanel);

        javax.swing.GroupLayout vehiclesPanelLayout = new javax.swing.GroupLayout(vehiclesPanel);
        vehiclesPanel.setLayout(vehiclesPanelLayout);
        vehiclesPanelLayout.setHorizontalGroup(
            vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vehiclesTabbedPane)
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

        newMaintenanceTypeButton.setText("New Maintenance Action Type");
        newMaintenanceTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMaintenanceTypeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout maintenanceActionsTypesLayout = new javax.swing.GroupLayout(maintenanceActionsTypes);
        maintenanceActionsTypes.setLayout(maintenanceActionsTypesLayout);
        maintenanceActionsTypesLayout.setHorizontalGroup(
            maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actionDescriptionLabel)
                    .addComponent(actionMileageIntervalLabel)
                    .addComponent(actionTypeNameLabel)
                    .addComponent(actionTypesLable))
                .addGap(35, 35, 35)
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(maintenanceTypesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(maintenanceTypeTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maintenenaceTypeMileageIntervalTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addComponent(newMaintenanceTypeButton)
                .addContainerGap(236, Short.MAX_VALUE))
        );
        maintenanceActionsTypesLayout.setVerticalGroup(
            maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actionTypesLable)
                    .addComponent(maintenanceTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newMaintenanceTypeButton))
                .addGap(18, 18, 18)
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actionTypeNameLabel)
                    .addComponent(maintenanceTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actionMileageIntervalLabel)
                    .addComponent(maintenenaceTypeMileageIntervalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(actionDescriptionLabel))
                    .addGroup(maintenanceActionsTypesLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(323, Short.MAX_VALUE))
        );

        mainTabbedPane.addTab("Maintenance Action Types", maintenanceActionsTypes);

        currentMechanicLabel.setText("Mechanic");

        currentMechanicTextField.setEditable(false);
        currentMechanicTextField.setFocusable(false);

        currentCustomerLabel.setText("Customer");

        currentCustomerTextField.setEditable(false);
        currentCustomerTextField.setFocusable(false);

        currentVehicleLabel.setText("Vehicle");

        currentVehicleTextField.setEditable(false);
        currentVehicleTextField.setAutoscrolls(false);
        currentVehicleTextField.setFocusable(false);

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
            .addComponent(mainTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentMechanicLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(currentMechanicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(currentCustomerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(currentCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(currentVehicleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(currentVehicleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentMechanicLabel)
                    .addComponent(currentMechanicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentCustomerLabel)
                    .addComponent(currentCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentVehicleLabel)
                    .addComponent(currentVehicleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainTabbedPane)
                .addContainerGap())
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
        }
        this.mechanicsNotebookEngine.openGarage(testFile);
    }//GEN-LAST:event_openMenuItemActionPerformed

    /**
     * Method used when a user hits the Create New Mechanic Button
     * @param evt 
     */
    private void createNewMechanicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewMechanicButtonActionPerformed
        // User hit create new Mechanic button, let's do it!
        this.mechanicsNotebookEngine.startNewMechanicWindow();
    }//GEN-LAST:event_createNewMechanicButtonActionPerformed

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
     * Method used when a user pressed the new Customer Button
     * @param evt 
     */
    private void newCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCustomerButtonActionPerformed
        // User pressed the new customer button, time to enact
        this.mechanicsNotebookEngine.startNewCustomerWindow();
    }//GEN-LAST:event_newCustomerButtonActionPerformed

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

    /**
     * Action used when the Create Vehicle button is pressed
     * @param evt 
     */
    private void newVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newVehicleButtonActionPerformed
        // TODO add your handling code here:
        // check to ensure we have a current Customer, required
        if(this.mechanicsNotebookEngine.getCurrentCustomer()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE,"You must first have a Customer in order to add a Vehicle! Please create a Customer first.");
        }else{
            this.mechanicsNotebookEngine.startNewVehicleWindow();
        }
    }//GEN-LAST:event_newVehicleButtonActionPerformed

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
     * Method used when a user pressed the Delete Mechanic button
     * <li> 1)Ensure there is a CURRENT mechanic
     * <li> 2)Start the Delete Mechanic Window
     * @param evt 
     */
    private void deleteMechanicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMechanicButtonActionPerformed
        // TODO add your handling code here:
        if(this.mechanicsNotebookEngine.getCurrentMechanic()== null){
            this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(DialogType.INFORMATION_MESSAGE,"You have no Mechanics to delete!");
        }else{
            this.mechanicsNotebookEngine.startDeleteMechanicWindow();
        }
    }//GEN-LAST:event_deleteMechanicButtonActionPerformed

    private void maintenanceActionList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_maintenanceActionList2ValueChanged
        // TODO add your handling code here:
        //System.out.println("TESTING HERE!!!!!!!!!!!!!!" + evt.toString());
        //System.out.println(this.maintenanceActionList2.getSelectedValue().toString() + " !!!!!!!!!!!!" );
        //if(evt.getFirstIndex()!=evt.getLastIndex()){
        //    int latestIndex = evt.getLastIndex();
        //    MaintenanceAction selectedMaintenanceAction  = (MaintenanceAction)model.getElementAt(latestIndex);
         //   System.out.println(selectedMaintenanceAction);
        //}

    }//GEN-LAST:event_maintenanceActionList2ValueChanged

    private void maintenanceActionList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maintenanceActionList2MouseClicked
        // TODO add your handling code here:
        //mouseClicked;
        //JList theList = (JList) mouseClicked.getSource();
        //if (mouseEvent.getClickCount() == 2) {
        //  int index = theList.locationToIndex(mouseEvent.getPoint());
        //  if (index >= 0) {
         //   Object o = theList.getModel().getElementAt(index);
         //   System.out.println("Double-clicked on: " + o.toString());
         // }
        
    }//GEN-LAST:event_maintenanceActionList2MouseClicked

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
    private javax.swing.JButton createNewMechanicButton;
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
    private javax.swing.JComboBox customersComboBox;
    private javax.swing.JLabel customersLabel;
    private javax.swing.JPanel customersPanel;
    private javax.swing.JButton deleteMechanicButton;
    private javax.swing.JButton editMechanicButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JList maintenanceActionList2;
    private javax.swing.JLabel maintenanceActionsLabel;
    private javax.swing.JPanel maintenanceActionsTypes;
    private javax.swing.JTextArea maintenanceTypeDescriptionTextArea;
    private javax.swing.JTextField maintenanceTypeTextField;
    private javax.swing.JComboBox maintenanceTypesComboBox;
    private javax.swing.JTextField maintenenaceTypeMileageIntervalTextField;
    private javax.swing.JLabel mechanicPictureLabel;
    private javax.swing.JComboBox mechanicsComboBox;
    private javax.swing.JLabel mechanicsLabel;
    private javax.swing.JMenuBar mechanicsNotebookMenuBar;
    private javax.swing.JPanel mechanicsPanel;
    private javax.swing.JButton newCustomerButton;
    private javax.swing.JMenuItem newGarageMenuItem;
    private javax.swing.JButton newMaintenanceTypeButton;
    private javax.swing.JButton newVehicleButton;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton updateMileageButton;
    private javax.swing.JLabel vehicleDescriptionLabel;
    private javax.swing.JPanel vehicleInfoPanel;
    private javax.swing.JPanel vehicleMaintenanceActionsPanel;
    private javax.swing.JLabel vehicleMakeLabel;
    private javax.swing.JLabel vehicleModelLabel;
    private javax.swing.JLabel vehicleOdometerLabel;
    private javax.swing.JPanel vehicleWarrantiesPanel;
    private javax.swing.JLabel vehicleYearLabel;
    private javax.swing.JComboBox vehiclesComboBox;
    private javax.swing.JPanel vehiclesPanel;
    private javax.swing.JTabbedPane vehiclesTabbedPane;
    // End of variables declaration//GEN-END:variables
}
