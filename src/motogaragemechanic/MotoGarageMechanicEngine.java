/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package motogaragemechanic;

import windows.WelcomeWindow;

/**
 *
 * @author Mark
 */
public class MotoGarageMechanicEngine {
    private WelcomeWindow welcomeWindow;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MotoGarageMechanicEngine motoGarageMechanicEngine = new MotoGarageMechanicEngine();
        motoGarageMechanicEngine.start();
    }
    
    public void start(){
        this.welcomeWindow = new WelcomeWindow();
        this.welcomeWindow.setVisible(true);
    }
}
