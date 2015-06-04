/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import engine.MotoGarageNotebookEngine;
import informationwindows.DialogType;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import objectmodels.MaintenanceAction;
import objectmodels.MaintenanceType;
import objectmodels.Mechanic;
import objectmodels.Vehicle;
import objectmodels.VehicleMaintenanceType;
import objectmodels.VehicleModel;

/**
 *
 * @author Mark
 */
public class MaintenanceActionWindow extends javax.swing.JDialog {
    MotoGarageNotebookEngine mechanicsNotebookEngine;
    MaintenanceAction maintenanceAction;
    
    /**
     * Creates new form MaintenanceActionWindow
     */
    public MaintenanceActionWindow() {
        initComponents();
    }
    
    /**
     * Creates new form MaintenanceActionWindow
     */
    public MaintenanceActionWindow(java.awt.Frame parent,boolean modal,MotoGarageNotebookEngine incomingMechanicsNotebookEngine, MaintenanceAction incomingMaintenanceAction) {
        super(parent, modal);
        this.mechanicsNotebookEngine = incomingMechanicsNotebookEngine;
        this.maintenanceAction = incomingMaintenanceAction;
        initComponents();
        this.setIcon();
        this.setTitle("View / Edit Maintenance Action");
        // Update Fields
        this.vehicleTextField.setText(incomingMaintenanceAction.getVehicle().toString());
        this.odometerTextField.setText(incomingMaintenanceAction.getOdometer().toString());
        this.notesTextArea.setText(incomingMaintenanceAction.getNotes()); 
        

        // maintenance types stuff
        if(this.mechanicsNotebookEngine.getMaintenaceTypeArray().length>0){
            maintenanceTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(mechanicsNotebookEngine.getMaintenaceTypeArray()));
            this.maintenanceTypeJComboBox.setEnabled(true);
            this.generalRadioButton.setEnabled(true);
        }else{
            this.maintenanceTypeJComboBox.setEnabled(false);
            this.generalRadioButton.setEnabled(false);
            this.generalRadioButton.setSelected(false);
        }
        // vehicle specific stuff
        if(!this.mechanicsNotebookEngine.getCurrentVehicle().getVehicleModel().getVehicleMaintenanceTypesList().isEmpty()){
            VehicleModel currentVehicleModel = this.mechanicsNotebookEngine.getCurrentVehicle().getVehicleModel();
            vehicleMaintenanceTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(mechanicsNotebookEngine.getVehicleMaintenanceTypesArray(currentVehicleModel)));

            this.vehicleMaintenanceTypeJComboBox.setEnabled(true);
            this.modelSpecificRadioButton.setEnabled(true);
        }else{
            this.vehicleMaintenanceTypeJComboBox.setEnabled(false);
            this.modelSpecificRadioButton.setEnabled(false);
            this.modelSpecificRadioButton.setSelected(false);
        }
        
        boolean isVehicleMaintenanceAction = incomingMaintenanceAction.getMaintenanceType() instanceof VehicleMaintenanceType;
        if(isVehicleMaintenanceAction){
            VehicleMaintenanceType currentVehicleMaintenanceType = (VehicleMaintenanceType)incomingMaintenanceAction.getMaintenanceType();
            VehicleModel currentVehicleModel = this.mechanicsNotebookEngine.getCurrentVehicle().getVehicleModel();
            Object[] testArray = mechanicsNotebookEngine.getVehicleMaintenanceTypesArray(currentVehicleModel);
            DefaultComboBoxModel test1 = new javax.swing.DefaultComboBoxModel<>(testArray);
            this.vehicleMaintenanceTypeJComboBox.setModel(test1);
            this.vehicleMaintenanceTypeJComboBox.setSelectedItem(currentVehicleMaintenanceType);
            this.modelSpecificRadioButton.setSelected(true);
            this.generalRadioButton.setSelected(false);
        }else{
            // general maintenance type
            MaintenanceType currentMaintenanceType = incomingMaintenanceAction.getMaintenanceType();
            Object[] testArray = mechanicsNotebookEngine.getMaintenaceTypeArray();
            DefaultComboBoxModel test1 = new javax.swing.DefaultComboBoxModel<>(testArray);
            //DefaultComboBoxModel test1 = new javax.swing.DefaultComboBoxModel<>(mechanicsNotebookEngine.getMechanicArray());
            this.maintenanceTypeJComboBox.setModel(test1);
            this.maintenanceTypeJComboBox.setSelectedItem(currentMaintenanceType);
            this.modelSpecificRadioButton.setSelected(false);
            this.generalRadioButton.setSelected(true);
        }
        

        
        // mechanic
        Mechanic currentMechanic = incomingMaintenanceAction.getMechanic();
        DefaultComboBoxModel test = new javax.swing.DefaultComboBoxModel<>(mechanicsNotebookEngine.getMechanicArray());
        this.mechanicJComboBox.setModel(test);
        this.mechanicJComboBox.setSelectedItem(currentMechanic);
        
        // date
        this.datePerformedDatePicker.setDate(incomingMaintenanceAction.getDate());
    }
    
    private void setIcon(){
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mechanicIcon.png")));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/MGFavicon.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        ImageIcon test = new javax.swing.ImageIcon(getClass().getResource("/MGLogoTrans2.png"));
        jPanel1 = new ImagePanel(test.getImage());
        vehicleTextField = new javax.swing.JTextField();
        vehicleLabel = new javax.swing.JLabel();
        mechanicJComboBox = new javax.swing.JComboBox();
        maintenanceTypeJComboBox = new javax.swing.JComboBox();
        odometerTextField = new javax.swing.JTextField();
        mechanicLabel = new javax.swing.JLabel();
        maintenanceTypeLabel = new javax.swing.JLabel();
        odometerLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        datePerformedDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        notesTextArea = new javax.swing.JTextArea();
        notesLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        vehicleMaintenanceTypeJComboBox = new javax.swing.JComboBox();
        modelSpecificRadioButton = new javax.swing.JRadioButton();
        generalRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        vehicleTextField.setEditable(false);
        vehicleTextField.setFocusable(false);

        vehicleLabel.setText("Vehicle");

        mechanicJComboBox.setModel(new javax.swing.DefaultComboBoxModel());

        maintenanceTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel());

        mechanicLabel.setText("Mechanic");

        maintenanceTypeLabel.setText("General Maintenance Type");

        odometerLabel.setText("Odometer");

        jLabel1.setText("Date Performed");

        notesTextArea.setColumns(20);
        notesTextArea.setRows(5);
        notesTextArea.setLineWrap(true);
        jScrollPane1.setViewportView(notesTextArea);

        notesLabel.setText("Notes");

        jLabel2.setText("Model Specific Maintenance Type");

        vehicleMaintenanceTypeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        modelSpecificRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelSpecificRadioButtonActionPerformed(evt);
            }
        });

        generalRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mechanicLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(notesLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(vehicleLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(generalRadioButton)
                            .addComponent(modelSpecificRadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(maintenanceTypeLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(odometerLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(datePerformedDatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(odometerTextField)
                    .addComponent(maintenanceTypeJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mechanicJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vehicleTextField)
                    .addComponent(vehicleMaintenanceTypeJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vehicleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mechanicLabel)
                    .addComponent(mechanicJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(maintenanceTypeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(maintenanceTypeLabel))
                    .addComponent(generalRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vehicleMaintenanceTypeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(modelSpecificRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(odometerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(odometerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datePerformedDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(notesLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addGap(111, 111, 111))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        Vehicle newVehicle = this.maintenanceAction.getVehicle();
        Mechanic newMechanic = (Mechanic) this.mechanicJComboBox.getSelectedItem();
        Integer newOdometer = Integer.parseInt(this.odometerTextField.getText());
        String newNotes = this.notesTextArea.getText();
        Date newDate = this.datePerformedDatePicker.getDate();
        MaintenanceAction newMaintenanceAction;
        if(this.generalRadioButton.isSelected()){
            MaintenanceType newMaintenanceType = (MaintenanceType)this.maintenanceTypeJComboBox.getSelectedItem();
            newMaintenanceAction = new MaintenanceAction(newMechanic, newVehicle,newMaintenanceType,newOdometer,newDate,newNotes );
            this.mechanicsNotebookEngine.editMaintenanceAction(maintenanceAction, newMaintenanceAction);
        }else{
            VehicleMaintenanceType newVehicleMaintenanceType = (VehicleMaintenanceType)this.vehicleMaintenanceTypeJComboBox.getSelectedItem();
            newMaintenanceAction = new MaintenanceAction(newMechanic, newVehicle,newVehicleMaintenanceType,newOdometer,newDate,newNotes );
            this.mechanicsNotebookEngine.editMaintenanceAction(maintenanceAction, newMaintenanceAction);
        } 


        if(this.mechanicsNotebookEngine.getCurrentVehicle().getOdometer()<(newMaintenanceAction.getOdometer())){
                this.mechanicsNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "Vehicle Odometer updated from " 
                        + this.mechanicsNotebookEngine.getCurrentVehicle().getOdometer().toString() + " miles to " +
                newMaintenanceAction.getOdometer().toString() + " miles.");
                this.mechanicsNotebookEngine.updateVehicleMileage(newMaintenanceAction.getOdometer());
        }
        this.dispose();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void generalRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalRadioButtonActionPerformed
        // TODO add your handling code here:
        this.generalRadioButton.setSelected(true);
        this.modelSpecificRadioButton.setSelected(false);
    }//GEN-LAST:event_generalRadioButtonActionPerformed

    private void modelSpecificRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelSpecificRadioButtonActionPerformed
        // TODO add your handling code here:
        this.generalRadioButton.setSelected(false);
        this.modelSpecificRadioButton.setSelected(true);
    }//GEN-LAST:event_modelSpecificRadioButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MaintenanceActionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaintenanceActionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaintenanceActionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaintenanceActionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MaintenanceActionWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private org.jdesktop.swingx.JXDatePicker datePerformedDatePicker;
    private javax.swing.JRadioButton generalRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox maintenanceTypeJComboBox;
    private javax.swing.JLabel maintenanceTypeLabel;
    private javax.swing.JComboBox mechanicJComboBox;
    private javax.swing.JLabel mechanicLabel;
    private javax.swing.JRadioButton modelSpecificRadioButton;
    private javax.swing.JLabel notesLabel;
    private javax.swing.JTextArea notesTextArea;
    private javax.swing.JLabel odometerLabel;
    private javax.swing.JTextField odometerTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel vehicleLabel;
    private javax.swing.JComboBox vehicleMaintenanceTypeJComboBox;
    private javax.swing.JTextField vehicleTextField;
    // End of variables declaration//GEN-END:variables
}
