/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import engine.MotoLogEngine;
import informationwindows.DialogType;
import java.awt.Toolkit;
import objectmodels.MaintenanceType;
import objectmodels.VehicleMaintenanceType;
import objectmodels.VehicleModel;
import objectmodels.VehicleType;

/**
 *
 * @author Mark
 */
public class MaintenanceTypeWindow extends javax.swing.JDialog {
    private MotoLogEngine motoLogEngine;
    private MaintenanceTypesMainWindow maintenanceTypesMainWindow;
    private VehicleMaintenanceTypesMainWindow vehicleMaintenanceTypesMainWindow;
    boolean updatingMaintenanceType = false;
    MaintenanceType originalMaintenanceType = null;
    VehicleMaintenanceType originalVehicleMaintenanceType = null;
    private VehicleModel vehicleModel = null;
    private boolean vehicleSpecific = false;

    /**
     * Creates new form MaintenanceTypeWindow
     */
    public MaintenanceTypeWindow() {
        initComponents();
    }
    
    /**
     * Creates new form MaintenanceTypeWindow, for creating Maintenance Type
     * @param parent
     * @param modal
     * @param incomingMaintenanceTypesMainWindow
     * @param incomingMechanicsNotebookEngine
     */
    public MaintenanceTypeWindow(java.awt.Frame parent,boolean modal,
            MaintenanceTypesMainWindow incomingMaintenanceTypesMainWindow,MotoLogEngine incomingMotoLogEngine) {
        super(parent, modal);
        this.motoLogEngine= incomingMotoLogEngine;
        this.maintenanceTypesMainWindow = incomingMaintenanceTypesMainWindow;
        initComponents();
        this.setIcon();
        this.setTitle("Create Maintenance Type");
        // set motorcycle by default!
        this.motorcycleRadioButton.setSelected(true);
    }
    
    /**
     * Creates new form MaintenanceTypeWindow, for creating Maintenance Type
     * <li> Vehicle Model Specific!
     * @param parent
     * @param modal
     * @param incomingVehicleMaintenanceTypesMainWindow
     * @param incomingVehicleModel
     * @param incomingMechanicsNotebookEngine
     */
    public MaintenanceTypeWindow(java.awt.Frame parent,boolean modal,
            VehicleMaintenanceTypesMainWindow incomingVehicleMaintenanceTypesMainWindow,VehicleModel incomingVehicleModel, MotoLogEngine incomingMechanicsNotebookEngine) {
        super(parent, modal);
        this.motoLogEngine= incomingMechanicsNotebookEngine;
        //this.maintenanceTypesMainWindow = incomingMaintenanceTypesMainWindow;
        this.vehicleMaintenanceTypesMainWindow = incomingVehicleMaintenanceTypesMainWindow;
        initComponents();
        this.setIcon();
        this.setTitle("Create Vehicle Model SpecificMaintenance Type");
        this.vehicleModel =  incomingVehicleModel;
        //this.vehicleSpecific = true;
        // updating a VEHICLE MODEL SPECIFIC, they CAN"T CHOOSE TYPE HERE
        if(this.vehicleModel.getVehicleType().equals(VehicleType.CARORTRUCK)){
            this.carTruckButton.setEnabled(false);
            this.motorcycleRadioButton.setEnabled(false);
            this.carTruckButton.setSelected(true);
            this.motorcycleRadioButton.setSelected(false);
        }else{
            this.carTruckButton.setEnabled(false);
            this.motorcycleRadioButton.setEnabled(false);
            this.carTruckButton.setSelected(false);
            this.motorcycleRadioButton.setSelected(true);
        }
        
    }
    
    /**
     * Creates new form MaintenanceTypeWindow for Updating Maintenance Type
     */
    public MaintenanceTypeWindow(java.awt.Frame parent,boolean modal,MaintenanceTypesMainWindow incomingMaintenanceTypesMainWindow,MotoLogEngine incomingMechanicsNotebookEngine, MaintenanceType incomingMaintenanceType) {
        super(parent, modal);
        this.motoLogEngine= incomingMechanicsNotebookEngine;
        this.maintenanceTypesMainWindow = incomingMaintenanceTypesMainWindow;
        initComponents();
        this.setIcon();
        this.setTitle("Update Maintenance Type");
        this.createOrUpdateMaintenanceTypeButton.setText("Update Maintenance Type");
        
        this.updatingMaintenanceType = true;
        this.originalMaintenanceType = incomingMaintenanceType;
        
        this.maintenanceTypeNameTextField.setText(incomingMaintenanceType.getMaintenanceTypeName());
        this.maintenanceTypeIntervalTextField.setText(incomingMaintenanceType.getMileageInterval().toString());
        this.maintenanceTypeDescriptionTextArea.setText(incomingMaintenanceType.getDescription());

        if(incomingMaintenanceType.getVehicleType().equals(VehicleType.CARORTRUCK)){
            this.carTruckButton.setSelected(true);
            this.motorcycleRadioButton.setEnabled(false);
            this.carTruckButton.setEnabled(false);
        }else{
            this.motorcycleRadioButton.setSelected(true);
            this.motorcycleRadioButton.setEnabled(false);
            this.carTruckButton.setEnabled(false);
        }
        // recurring stuff
        if(incomingMaintenanceType.getMileageInterval()== 0){
            this.updateRecurring();
        }
        // vehicle type stuff
        if(this.originalMaintenanceType.getVehicleType().equals(VehicleType.CARORTRUCK)){
            this.carTruckButton.setSelected(true);
        }else{
            this.motorcycleRadioButton.setSelected(true);
        }
    }
    
    private void updateRecurring(){
            this.nonrecurringCheckBox.setSelected(true);
            this.maintenanceTypeIntervalTextField.setText("0");
            this.maintenanceTypeIntervalTextField.setEnabled(false);

    }
    
    /**
     * Creates new form MaintenanceTypeWindow for Updating Maintenance Type
     * <li> for Vehicle Specific Maintenance Types!
     * @param parent
     * @param modal
     * @param incomingVehicleMaintenanceTypesMainWindow
     * @param incomingVehicleMaintenanceType
     * @param incomingMechanicsNotebookEngine
     * @param incomingVehicleModel
     */
    public MaintenanceTypeWindow(java.awt.Frame parent,boolean modal,VehicleMaintenanceTypesMainWindow incomingVehicleMaintenanceTypesMainWindow,MotoLogEngine incomingMechanicsNotebookEngine, 
            VehicleMaintenanceType incomingVehicleMaintenanceType, VehicleModel incomingVehicleModel) {
        super(parent, modal);
        this.motoLogEngine= incomingMechanicsNotebookEngine;
        this.vehicleMaintenanceTypesMainWindow = incomingVehicleMaintenanceTypesMainWindow;
        initComponents();
        this.setIcon();
        this.setTitle("Update Maintenance Type");
        this.createOrUpdateMaintenanceTypeButton.setText("Update Maintenance Type");
        this.vehicleModel =  incomingVehicleModel;
        
        this.updatingMaintenanceType = true;
        this.originalVehicleMaintenanceType = incomingVehicleMaintenanceType;
        
        this.maintenanceTypeNameTextField.setText(incomingVehicleMaintenanceType.getMaintenanceTypeName());
        this.maintenanceTypeIntervalTextField.setText(incomingVehicleMaintenanceType.getMileageInterval().toString());
        this.maintenanceTypeDescriptionTextArea.setText(incomingVehicleMaintenanceType.getDescription());
        
        // disable bubbles

        if(incomingVehicleModel.getVehicleType().equals(VehicleType.CARORTRUCK)){
            this.carTruckButton.setSelected(true);
            this.motorcycleRadioButton.setEnabled(false);
            this.carTruckButton.setEnabled(false);
        }else{
            this.motorcycleRadioButton.setSelected(true);
            this.motorcycleRadioButton.setEnabled(false);
            this.carTruckButton.setEnabled(false);
        }
        
        // recurring stuff
        if(incomingVehicleMaintenanceType.getMileageInterval()== 0){
            this.updateRecurring();
        }
    }
    
    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(this.motoLogEngine.getMotoLogIcon())));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createOrUpdateMaintenanceTypeButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        maintenanceTypeNameTextField = new javax.swing.JTextField();
        maintenanceTypeNameLabel = new javax.swing.JLabel();
        maintenanceTypeIntervalTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        maintenanceTypeDescriptionTextArea = new javax.swing.JTextArea();
        maintenanceTypeIntervalLabel = new javax.swing.JLabel();
        maintenanceTypeDescriptionLabel = new javax.swing.JLabel();
        nonrecurringCheckBox = new javax.swing.JCheckBox();
        motorcycleRadioButton = new javax.swing.JRadioButton();
        carTruckButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        createOrUpdateMaintenanceTypeButton.setText("Create Maintenance Type");
        createOrUpdateMaintenanceTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOrUpdateMaintenanceTypeButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        maintenanceTypeNameLabel.setText("Type Name");

        maintenanceTypeDescriptionTextArea.setColumns(20);
        maintenanceTypeDescriptionTextArea.setRows(5);
        maintenanceTypeDescriptionTextArea.setLineWrap(true);
        jScrollPane1.setViewportView(maintenanceTypeDescriptionTextArea);

        maintenanceTypeIntervalLabel.setText("Recommended Interval");

        maintenanceTypeDescriptionLabel.setText("Description");

        nonrecurringCheckBox.setText("Nonrecurring");
        nonrecurringCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonrecurringCheckBoxActionPerformed(evt);
            }
        });

        motorcycleRadioButton.setText("Motorcycle");
        motorcycleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motorcycleRadioButtonActionPerformed(evt);
            }
        });

        carTruckButton.setText("Car / Truck");
        carTruckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carTruckButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maintenanceTypeIntervalLabel)
                            .addComponent(maintenanceTypeNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maintenanceTypeDescriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nonrecurringCheckBox)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(maintenanceTypeNameTextField)
                            .addComponent(maintenanceTypeIntervalTextField)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(motorcycleRadioButton)
                        .addGap(46, 46, 46)
                        .addComponent(carTruckButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(motorcycleRadioButton)
                    .addComponent(carTruckButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maintenanceTypeNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maintenanceTypeNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maintenanceTypeIntervalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maintenanceTypeIntervalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nonrecurringCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maintenanceTypeDescriptionLabel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(createOrUpdateMaintenanceTypeButton)
                .addGap(74, 74, 74)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createOrUpdateMaintenanceTypeButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void createOrUpdateMaintenanceTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOrUpdateMaintenanceTypeButtonActionPerformed
        // Basic Checks
        if(this.maintenanceTypeNameTextField.getText().equals("")|| this.maintenanceTypeIntervalTextField.equals("") || this.maintenanceTypeDescriptionTextArea.equals("")){
            this.motoLogEngine.getDialogFactory().createDialogMessage(this,DialogType.WARNING_MESSAGE, "A Maintenance Type requires a Name, Recommended Interval, and Description.");
            return;
        }      
        String newName = this.maintenanceTypeNameTextField.getText();
        Integer newInterval = Integer.parseInt(this.maintenanceTypeIntervalTextField.getText());
        String newDescription = this.maintenanceTypeDescriptionTextArea.getText();
        
        
        // onto create / update
        if(this.vehicleModel==null){
            MaintenanceType newMaintenanceType = null;//= new MaintenanceType(newName,newInterval,newDescription);
            if(this.motorcycleRadioButton.isSelected()){
                newMaintenanceType = new MaintenanceType(newName,newInterval,newDescription,VehicleType.MOTORCYCLE);
            }else{
                newMaintenanceType = new MaintenanceType(newName,newInterval,newDescription,VehicleType.CARORTRUCK);
            }
            //MaintenanceType newMaintenanceType = new MaintenanceType(newName,newInterval,newDescription);
            if(this.updatingMaintenanceType){
                boolean updatedMaintenanceType = this.motoLogEngine.updateMaintenanceType(originalMaintenanceType, newMaintenanceType);
                if(updatedMaintenanceType){
                    this.motoLogEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "Maintenance Type has been updated!");
                    this.maintenanceTypesMainWindow.refreshMaintenanceTypeTable();
                }else{
                    this.motoLogEngine.getDialogFactory().createDialogMessage(this,DialogType.ERROR_MESSAGE, "An error occured attempting to update the Maintenance Type!");
                }
                this.dispose();
            }else{
                this.motoLogEngine.createNewMaintenanceType(newMaintenanceType);
                this.motoLogEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "New Maintenance Type," + newMaintenanceType.toString() + ", has been created!");
                // REFRESH
                this.maintenanceTypesMainWindow.refreshMaintenanceTypeTable();
                this.dispose();
            }
        }else{
            VehicleMaintenanceType newVehicleMaintenanceType = new VehicleMaintenanceType(this.vehicleModel,newName,newInterval,newDescription);
            if(this.updatingMaintenanceType){
                boolean updatedVehicleMaintenanceType = this.motoLogEngine.updateVehicleMaintenanceType(originalVehicleMaintenanceType, newVehicleMaintenanceType, this.vehicleModel);
                if(updatedVehicleMaintenanceType){
                    this.motoLogEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "Vehicle Model Specific Maintenance Type has been updated!");
                    this.vehicleMaintenanceTypesMainWindow.refreshVehicleMaintenanceTypesTable();
                }else{
                    this.motoLogEngine.getDialogFactory().createDialogMessage(this,DialogType.ERROR_MESSAGE, "An error occured attempting to update the Vehicle Specific Maintenance Type!!");
                }
                this.dispose();
            }else{
                this.motoLogEngine.createNewVehicleMaintenanceType(newVehicleMaintenanceType,this.vehicleModel);
                this.motoLogEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "New Vehicle Specific Maintenance Type," + newVehicleMaintenanceType.toString() + ", has been created!");
                // REFRESH
                this.vehicleMaintenanceTypesMainWindow.refreshVehicleMaintenanceTypesTable();
                this.dispose();
            }
        }
    }//GEN-LAST:event_createOrUpdateMaintenanceTypeButtonActionPerformed

    private void nonrecurringCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonrecurringCheckBoxActionPerformed
        // TODO add your handling code here:
        if(this.nonrecurringCheckBox.isSelected()){
            this.maintenanceTypeIntervalTextField.setText("0");
            this.maintenanceTypeIntervalTextField.setEnabled(false);
        }else{
            this.maintenanceTypeIntervalTextField.setText("");
            this.maintenanceTypeIntervalTextField.setEnabled(true);
        }
    }//GEN-LAST:event_nonrecurringCheckBoxActionPerformed

    private void motorcycleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_motorcycleRadioButtonActionPerformed
        // TODO add your handling code here:
        this.carTruckButton.setSelected(false);
    }//GEN-LAST:event_motorcycleRadioButtonActionPerformed

    private void carTruckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carTruckButtonActionPerformed
        // TODO add your handling code here:
        this.motorcycleRadioButton.setSelected(false);
    }//GEN-LAST:event_carTruckButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MaintenanceTypeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaintenanceTypeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaintenanceTypeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaintenanceTypeWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MaintenanceTypeWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton carTruckButton;
    private javax.swing.JButton createOrUpdateMaintenanceTypeButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel maintenanceTypeDescriptionLabel;
    private javax.swing.JTextArea maintenanceTypeDescriptionTextArea;
    private javax.swing.JLabel maintenanceTypeIntervalLabel;
    private javax.swing.JTextField maintenanceTypeIntervalTextField;
    private javax.swing.JLabel maintenanceTypeNameLabel;
    private javax.swing.JTextField maintenanceTypeNameTextField;
    private javax.swing.JRadioButton motorcycleRadioButton;
    private javax.swing.JCheckBox nonrecurringCheckBox;
    // End of variables declaration//GEN-END:variables
}
