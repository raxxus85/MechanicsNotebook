/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import engine.MotoGarageNotebookEngine;
import informationwindows.DialogType;
import java.awt.Toolkit;
import objectmodels.VehicleType;

/**
 *
 * @author Mark
 */
public class VehicleTypesWindow extends javax.swing.JDialog {
    private MotoGarageNotebookEngine motoGarageNotebookEngine;
    private VehicleTypesMainWindow vehicleTypesMainWindow;
    private boolean updatingVehicleType = false;
    private VehicleType originalVehicleType = null;
    
    
    /**
     * DON"T USE!!!!
     * @param parent
     * @param modal 
     */
    public VehicleTypesWindow(java.awt.Frame parent,boolean modal){
        super(parent, modal);
        initComponents();
    }
    
    /**
     * Creates new form vehicleTypesWindow
     */
    public VehicleTypesWindow(java.awt.Frame parent,boolean modal,VehicleTypesMainWindow incomingVehicleTypesMainWindow 
            ,MotoGarageNotebookEngine incomingMotoGarageNotebookEngine) {
        super(parent, modal);
        this.motoGarageNotebookEngine= incomingMotoGarageNotebookEngine;
        this.vehicleTypesMainWindow = incomingVehicleTypesMainWindow;
        initComponents();
        this.setIcon();
        this.setTitle("Create Vehicle Type");
        this.createOrUpdateButton.setText("Create Vehicle Type");
    }
    
    /**
     * Creates new form vehicleTypesWindow
     */
    public VehicleTypesWindow(java.awt.Frame parent,boolean modal,VehicleTypesMainWindow incomingVehicleTypesMainWindow 
            ,MotoGarageNotebookEngine incomingMotoGarageNotebookEngine, VehicleType incomingVehicleType) {
        super(parent, modal);
        this.motoGarageNotebookEngine= incomingMotoGarageNotebookEngine;
        this.vehicleTypesMainWindow = incomingVehicleTypesMainWindow;
        initComponents();
        this.setIcon();
        this.setTitle("Update Vehicle Type");
        this.createOrUpdateButton.setText("Update Vehicle Type");
        
        this.updatingVehicleType = true;
        this.originalVehicleType = incomingVehicleType;
        
        this.makeTextField.setText(incomingVehicleType.getMake());
        this.modelTextField.setText(incomingVehicleType.getModel());

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

        jPanel1 = new javax.swing.JPanel();
        makeLabel = new javax.swing.JLabel();
        modelLabel = new javax.swing.JLabel();
        makeTextField = new javax.swing.JTextField();
        modelTextField = new javax.swing.JTextField();
        createOrUpdateButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        makeLabel.setText("Make");

        modelLabel.setText("Model");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(makeLabel)
                    .addComponent(modelLabel))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(modelTextField)
                    .addComponent(makeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(makeLabel)
                    .addComponent(makeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelLabel)
                    .addComponent(modelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        createOrUpdateButton.setText("Create or Update");
        createOrUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createOrUpdateButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createOrUpdateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createOrUpdateButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void createOrUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createOrUpdateButtonActionPerformed
        // TODO add your handling code here:
        // Basic Checks
        if(this.makeTextField.getText().equals("")|| this.modelTextField.getText().equals("")){
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.WARNING_MESSAGE, "A Vehicle Type requires a Make and Model.");
            return;
        }
        String newMake = this.makeTextField.getText();
        String newModel = this.modelTextField.getText();
        VehicleType newVehicleType = new VehicleType(newMake,newModel);       
        // onto create / update
        if(this.updatingVehicleType){
            boolean updatedVehicleType = this.motoGarageNotebookEngine.updateVehicleType(originalVehicleType, newVehicleType);
        
            if(updatedVehicleType){
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "Vehicle Type has been updated!");
                this.vehicleTypesMainWindow.refreshVehicleTypeTable();
            }else{
                this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.ERROR_MESSAGE, "An error occured attempting to update the Vehicle Type!");
            }
            this.dispose();
        }else{
            this.motoGarageNotebookEngine.createNewVehicleType(newVehicleType);
            this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(this,DialogType.INFORMATION_MESSAGE, "New Vehicle Type," + newVehicleType.toString() + ", has been created!");
            // REFRESH
            this.vehicleTypesMainWindow.refreshVehicleTypeTable();
            this.dispose();
        }
    }//GEN-LAST:event_createOrUpdateButtonActionPerformed

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
            java.util.logging.Logger.getLogger(VehicleTypesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VehicleTypesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VehicleTypesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VehicleTypesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VehicleTypesWindow dialog = new VehicleTypesWindow(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton createOrUpdateButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel makeLabel;
    private javax.swing.JTextField makeTextField;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JTextField modelTextField;
    // End of variables declaration//GEN-END:variables
}