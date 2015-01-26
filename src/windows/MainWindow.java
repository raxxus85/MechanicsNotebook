/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import java.io.File;
import javax.swing.JFileChooser;
import objectmodels.Mechanic;
import engine.MotoGarageMechanicEngine;
import informationwindows.DialogType;
import javax.swing.ImageIcon;
import objectmodels.Customer;

/**
 *
 * @author Mark
 */
public class MainWindow extends javax.swing.JFrame {
    private MotoGarageMechanicEngine motoGarageMechanicEngine;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
    }
    
     /**
     * Creates new form MainWindow
     */
    public MainWindow(MotoGarageMechanicEngine incomingMotoGarageMechanicEngine) {
        this.motoGarageMechanicEngine = incomingMotoGarageMechanicEngine;
        initComponents();
        //this.setIconImage(new ImageIcon(getClass().getResource("/<resource_path" + "/image.png")).getImage());
        getClass().getResource("/image.png");
    }

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * !!!Critical method, used to refresh everything after a change (adding a mechanic, etc)!!!
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public void refresh(){
        this.refreshMechanicsTab();
        this.refreshCustomersTab();
        this.refreshCurrentObjects();
    }
    
    /**
     * Method to refresh the current display objects, ie Mechanic, Customer, Vehicle
     */
    public void refreshCurrentObjects(){
        // Refresh Mechanic 
        if(this.motoGarageMechanicEngine.getCurrentMechanic()!= null){
            this.currentMechanicTextField.setText(this.motoGarageMechanicEngine.getCurrentMechanic().toString());
        }
        // Refresh Customer
        if(this.motoGarageMechanicEngine.getCurrentCustomer()!= null){
            this.currentCustomerTextField.setText(this.motoGarageMechanicEngine.getCurrentCustomer().toString());
        }
        
        // Refresh Vehicle
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
        boolean hasCustomers = false;
        Customer currentCustomer = null;
        if(this.customersComboBox.getSelectedItem() != null){
            currentCustomer = (Customer)this.customersComboBox.getSelectedItem();
            hasCustomers = true;
        }
        this.customersComboBox.removeAllItems();
        customersComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.motoGarageMechanicEngine.getCustomerArray()));
        if(hasCustomers){
            customersComboBox.setSelectedItem(currentCustomer);
        }
    }
    
        /**
     * Method to refresh the current Customers information (name, description, etc)
     */
    private void refreshCustomerInformation(){
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
        boolean hasMechanics = false;
        Mechanic currentMechanic = null;
        if(this.mechanicsComboBox.getSelectedItem() != null){
            currentMechanic = (Mechanic)this.mechanicsComboBox.getSelectedItem();
            hasMechanics = true;
        }
        this.mechanicsComboBox.removeAllItems();
        //mechanicsComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.motoGarageMechanicEngine.getMechanicNameArray()));
        mechanicsComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.motoGarageMechanicEngine.getMechanicArray()));
        if(hasMechanics){
            mechanicsComboBox.setSelectedItem(currentMechanic);
        }
        
    }
    
    /**
     * Method to refresh the current Mechanic's information (name, description, etc)
     */
    private void refreshMechanicInformation(){
        Mechanic currentMechanic = (Mechanic)this.mechanicsComboBox.getSelectedItem();
        if(currentMechanic == null){
        this.currentMechanicFirstNameTextField.setText("");
        this.currentMechanicMiddleNameTextField.setText("");
        this.currentMechanicLastNameTextField.setText("");
        this.currentMechanicDescriptionTextArea.setText("");
        }else{
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

        mechanicsTabbedPane = new javax.swing.JTabbedPane();
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
        vehiclesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        vehicleMakeLabel = new javax.swing.JLabel();
        vehicleYearLabel = new javax.swing.JLabel();
        vehicleModelLabel = new javax.swing.JLabel();
        vehicleOdometerLabel = new javax.swing.JLabel();
        vehicleNotesLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        vehicleMaintenanceActionsPanel = new javax.swing.JPanel();
        maintenanceActionsLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        maintenanceActionsTypes = new javax.swing.JPanel();
        newCustomerButton = new javax.swing.JButton();
        newVehicleButton = new javax.swing.JButton();
        createNewMechanicButton = new javax.swing.JButton();
        currentMechanicLabel = new javax.swing.JLabel();
        currentMechanicTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        currentCustomerTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        currentVehicleTextField = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newGarageMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mechanic's Notebook");
        setMinimumSize(new java.awt.Dimension(800, 600));

        mechanicsComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.motoGarageMechanicEngine.getMechanicArray()));
        mechanicsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mechanicsComboBoxActionPerformed(evt);
            }
        });

        mechanicsLabel.setText("Mechanics");

        currentMechanicFirstNameLabel.setText("First Name");

        currentMechanicMiddleNameLabel.setText("Middle Name");

        currentMechanicLastNameLabel.setText("Last Name");

        currentMechanicDescription.setText("Description");

        currentMechanicDescriptionTextArea.setColumns(20);
        currentMechanicDescriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(currentMechanicDescriptionTextArea);

        javax.swing.GroupLayout mechanicsPanelLayout = new javax.swing.GroupLayout(mechanicsPanel);
        mechanicsPanel.setLayout(mechanicsPanelLayout);
        mechanicsPanelLayout.setHorizontalGroup(
            mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mechanicsLabel)
                    .addGroup(mechanicsPanelLayout.createSequentialGroup()
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                                .addGap(222, 222, 222)
                                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(currentMechanicMiddleNameLabel)
                                    .addComponent(currentMechanicLastNameLabel)
                                    .addComponent(currentMechanicDescription)))
                            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                                .addComponent(mechanicsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(currentMechanicFirstNameLabel)))
                        .addGap(55, 55, 55)
                        .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(currentMechanicMiddleNameTextField)
                            .addComponent(currentMechanicFirstNameTextField)
                            .addComponent(currentMechanicLastNameTextField)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))))
                .addContainerGap(142, Short.MAX_VALUE))
        );
        mechanicsPanelLayout.setVerticalGroup(
            mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mechanicsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(mechanicsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mechanicsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentMechanicFirstNameLabel)
                    .addComponent(currentMechanicFirstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentMechanicMiddleNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentMechanicMiddleNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentMechanicLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentMechanicLastNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mechanicsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentMechanicDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(272, Short.MAX_VALUE))
        );

        mechanicsTabbedPane.addTab("Mechanics", mechanicsPanel);

        customersLabel.setText("Customers");

        customersComboBox.setModel(new javax.swing.DefaultComboBoxModel(this.motoGarageMechanicEngine.getCustomerArray()));
        customersComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customersComboBoxActionPerformed(evt);
            }
        });

        customerFirstNameLabel.setText("First Name");

        customerMiddleInitialLabel.setText("Middle Initial");

        customerLastNameLabel.setText("Last Name");

        customerDescriptionLabel.setText("Description");

        currentCustomerFirstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentCustomerFirstNameTextFieldActionPerformed(evt);
            }
        });

        currentCustomerDescriptionTextArea.setColumns(20);
        currentCustomerDescriptionTextArea.setRows(5);
        jScrollPane2.setViewportView(currentCustomerDescriptionTextArea);

        javax.swing.GroupLayout customersPanelLayout = new javax.swing.GroupLayout(customersPanel);
        customersPanel.setLayout(customersPanelLayout);
        customersPanelLayout.setHorizontalGroup(
            customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customersPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customersPanelLayout.createSequentialGroup()
                        .addComponent(customersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(229, Short.MAX_VALUE))
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
                    .addComponent(currentCustomerMiddleInitialTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerLastNameLabel)
                    .addComponent(currentCustomerLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(customersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerDescriptionLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(291, Short.MAX_VALUE))
        );

        mechanicsTabbedPane.addTab("Customers", customersPanel);

        jLabel1.setText("Customer Vehicles");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(this.motoGarageMechanicEngine.getVehicleArray()));

        vehicleMakeLabel.setText("Make");

        vehicleYearLabel.setText("Year");

        vehicleModelLabel.setText("Model");

        vehicleOdometerLabel.setText("Odometer");

        vehicleNotesLabel.setText("Notes");

        javax.swing.GroupLayout vehiclesPanelLayout = new javax.swing.GroupLayout(vehiclesPanel);
        vehiclesPanel.setLayout(vehiclesPanelLayout);
        vehiclesPanelLayout.setHorizontalGroup(
            vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicleNotesLabel)
                    .addGroup(vehiclesPanelLayout.createSequentialGroup()
                        .addGroup(vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(vehicleMakeLabel)
                            .addComponent(vehicleModelLabel)
                            .addComponent(vehicleYearLabel)
                            .addComponent(vehicleOdometerLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox1, 0, 107, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4))))
                .addContainerGap(581, Short.MAX_VALUE))
        );
        vehiclesPanelLayout.setVerticalGroup(
            vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclesPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vehicleMakeLabel)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicleModelLabel)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicleYearLabel)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(vehiclesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicleOdometerLabel))
                .addGap(18, 18, 18)
                .addComponent(vehicleNotesLabel)
                .addContainerGap(316, Short.MAX_VALUE))
        );

        mechanicsTabbedPane.addTab("Vehicles", vehiclesPanel);

        maintenanceActionsLabel.setText("Maintenace Actions");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout vehicleMaintenanceActionsPanelLayout = new javax.swing.GroupLayout(vehicleMaintenanceActionsPanel);
        vehicleMaintenanceActionsPanel.setLayout(vehicleMaintenanceActionsPanelLayout);
        vehicleMaintenanceActionsPanelLayout.setHorizontalGroup(
            vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addComponent(maintenanceActionsLabel)
                .addContainerGap(376, Short.MAX_VALUE))
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        vehicleMaintenanceActionsPanelLayout.setVerticalGroup(
            vehicleMaintenanceActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleMaintenanceActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maintenanceActionsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addContainerGap())
        );

        mechanicsTabbedPane.addTab("Vehicle Maintenance Actions", vehicleMaintenanceActionsPanel);

        javax.swing.GroupLayout maintenanceActionsTypesLayout = new javax.swing.GroupLayout(maintenanceActionsTypes);
        maintenanceActionsTypes.setLayout(maintenanceActionsTypesLayout);
        maintenanceActionsTypesLayout.setHorizontalGroup(
            maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
        );
        maintenanceActionsTypesLayout.setVerticalGroup(
            maintenanceActionsTypesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        mechanicsTabbedPane.addTab("Maintenance Action Types", maintenanceActionsTypes);

        newCustomerButton.setText("New Customer");
        newCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCustomerButtonActionPerformed(evt);
            }
        });

        newVehicleButton.setText("New Vehicle");
        newVehicleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newVehicleButtonActionPerformed(evt);
            }
        });

        createNewMechanicButton.setText(" New Mechanic");
        createNewMechanicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewMechanicButtonActionPerformed(evt);
            }
        });

        currentMechanicLabel.setText("Current Mechanic:");

        jLabel3.setText("Current Customer:");

        jLabel4.setText("Current Vehicle:");

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
        fileMenu.add(jSeparator1);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        jMenuBar1.add(fileMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuBar1.add(helpMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mechanicsTabbedPane)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createNewMechanicButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newCustomerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newVehicleButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(currentMechanicLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(currentMechanicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentCustomerTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(currentVehicleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentMechanicLabel)
                    .addComponent(currentMechanicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(currentCustomerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(currentVehicleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newVehicleButton)
                    .addComponent(newCustomerButton)
                    .addComponent(createNewMechanicButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mechanicsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        this.motoGarageMechanicEngine.openGarage(testFile);
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void createNewMechanicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewMechanicButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageMechanicEngine.startNewMechanicWindow();
    }//GEN-LAST:event_createNewMechanicButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        this.dispose();
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
            this.motoGarageMechanicEngine.saveGarage(testFile);
        }
        
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void newGarageMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGarageMenuItemActionPerformed
        // TODO add your handling code here:
        //this.motoGarageMechanicEngine.startNewGarageWindow();
        // NO it should 
        // 1) ask to save current data
        // 2) clear all data
        // 3) new garage object! nothing tied to it!
        
        this.motoGarageMechanicEngine.createDefaultGarage();
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
        this.motoGarageMechanicEngine.saveGarage(testFile);
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void newCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCustomerButtonActionPerformed
        // TODO add your handling code here:
        this.motoGarageMechanicEngine.startNewCustomerWindow();
    }//GEN-LAST:event_newCustomerButtonActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
        this.motoGarageMechanicEngine.startAboutWindow();
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void currentCustomerFirstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentCustomerFirstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentCustomerFirstNameTextFieldActionPerformed

    private void customersComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customersComboBoxActionPerformed
        // TODO add your handling code here:

        Customer customerSelected = (Customer)this.customersComboBox.getSelectedItem();
        //this.currentMechanicTextField.setText(mechanicSelected.toString());
        this.motoGarageMechanicEngine.setCurrentCustomer(customerSelected);
        
        this.refresh();
    }//GEN-LAST:event_customersComboBoxActionPerformed

    private void mechanicsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mechanicsComboBoxActionPerformed
        // TODO add your handling code here:

        // should we change to "current" mechanic then? yes right now
        Mechanic mechanicSelected = (Mechanic)this.mechanicsComboBox.getSelectedItem();
        //this.currentMechanicTextField.setText(mechanicSelected.toString());
        this.motoGarageMechanicEngine.setCurrentMechanic(mechanicSelected);
        
        // user possibly changed the mechanic, time to refresh
        this.refresh(); 
    }//GEN-LAST:event_mechanicsComboBoxActionPerformed

    private void newVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newVehicleButtonActionPerformed
        // TODO add your handling code here:
        // check to ensure we have a current Customer, required
        if(this.motoGarageMechanicEngine.getCurrentCustomer()== null){
            this.motoGarageMechanicEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE, "You must first have a Customer in order to add a Vehicle! Please create a Customer first.");
        }else{
            this.motoGarageMechanicEngine.startNewVehicleWindow();
        }
    }//GEN-LAST:event_newVehicleButtonActionPerformed

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
    private javax.swing.JButton createNewMechanicButton;
    private javax.swing.JTextArea currentCustomerDescriptionTextArea;
    private javax.swing.JTextField currentCustomerFirstNameTextField;
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
    private javax.swing.JTextField currentVehicleTextField;
    private javax.swing.JLabel customerDescriptionLabel;
    private javax.swing.JLabel customerFirstNameLabel;
    private javax.swing.JLabel customerLastNameLabel;
    private javax.swing.JLabel customerMiddleInitialLabel;
    private javax.swing.JComboBox customersComboBox;
    private javax.swing.JLabel customersLabel;
    private javax.swing.JPanel customersPanel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel maintenanceActionsLabel;
    private javax.swing.JPanel maintenanceActionsTypes;
    private javax.swing.JComboBox mechanicsComboBox;
    private javax.swing.JLabel mechanicsLabel;
    private javax.swing.JPanel mechanicsPanel;
    private javax.swing.JTabbedPane mechanicsTabbedPane;
    private javax.swing.JButton newCustomerButton;
    private javax.swing.JMenuItem newGarageMenuItem;
    private javax.swing.JButton newVehicleButton;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JPanel vehicleMaintenanceActionsPanel;
    private javax.swing.JLabel vehicleMakeLabel;
    private javax.swing.JLabel vehicleModelLabel;
    private javax.swing.JLabel vehicleNotesLabel;
    private javax.swing.JLabel vehicleOdometerLabel;
    private javax.swing.JLabel vehicleYearLabel;
    private javax.swing.JPanel vehiclesPanel;
    // End of variables declaration//GEN-END:variables
}
