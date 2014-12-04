/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package motogaragemechanic;

import windows.MainWindow;
import windows.NewMechanicWindow;
import windows.WelcomeWindow;

/**
 *
 * @author Mark
 */
public class MotoGarageMechanicEngine {
    //Window Variables
    private WelcomeWindow welcomeWindow;
    private MainWindow mainWindow;
    private NewMechanicWindow newMechanicWindow;
    //Other Variables
    private Garage currentGarage;
    
    
    public MotoGarageMechanicEngine(){
        // TESTING CODE NEW TO REMOVE ONCE WE IMPLEMENT CREATE/ OPEN/ SAVE
        Garage testGarage = new Garage();
        this.currentGarage = testGarage;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MotoGarageMechanicEngine motoGarageMechanicEngine = new MotoGarageMechanicEngine();
        motoGarageMechanicEngine.start();
        //motoGarageMechanicEngine.startMainWindow();
        

    }
    
    private void start(){
        this.welcomeWindow = new WelcomeWindow(this);
        this.welcomeWindow.setVisible(true);
    }
    
    public void startMainWindow(){
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setVisible(true);
    }
    
    public void startNewMechanicWindow(){
        this.newMechanicWindow = new NewMechanicWindow(this);
        this.newMechanicWindow.setVisible(true);
    }
    
    public boolean createNewMechanic(Mechanic incomingMechanic){
        
        // insert logic here to check to see if mechanic exists?
        this.getGarage().addMechanic(incomingMechanic);
        System.out.println(this.getGarage());
        return true;
    }
    
    //ACCESSORS and GETTORS
    private Garage getGarage(){
        return this.currentGarage;
    }
}
