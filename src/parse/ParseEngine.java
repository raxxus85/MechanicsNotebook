package parse;

import engine.MotoGarageNotebookEngine;
import informationwindows.DialogType;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.parse4j.Parse;
import org.parse4j.ParseException;
import org.parse4j.ParseFile;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;
import org.parse4j.ParseUser;
import org.parse4j.callback.FindCallback;
import org.parse4j.callback.GetCallback;
import org.parse4j.callback.GetDataCallback;
import org.parse4j.callback.ProgressCallback;
import org.parse4j.callback.SaveCallback;

/**
 *
 * @author Mark
 */
public class ParseEngine {
    private MotoGarageNotebookEngine motoGarageNotebookEngine;
    private ParseUser currentUser = null;
    private ParseObject currentGarage = null;
    private final String appId = "ijXu9OWAFfekiM8hFlLjrHa7A2BK3kWir8X1v5nM";
    private final String restApiAppId ="sB8eqMDf5M8gyEzgkwJ8EzQRhYGaTeAbTPhfhTNE";
    private ParseFile tempFile = null;
    
    // test
    
    private boolean hasGarage = false;
    
    public ParseEngine(MotoGarageNotebookEngine incomingMotoGarageNotebookEngine){
        this.motoGarageNotebookEngine = incomingMotoGarageNotebookEngine;
        Parse.initialize(appId, restApiAppId);
    }
    
    public ParseEngine(){
        Parse.initialize(appId, restApiAppId);
    }
    
    public void setTempFile(ParseFile incomingTempFile){
        this.tempFile = incomingTempFile;
    }
    
    public ParseFile getTempFile(){
        return this.tempFile;
    }
    
    
    public void setCurrentGarage(ParseObject object){
        this.currentGarage= object;
        this.hasGarage = true;
    }
    
    public ParseObject getCurrentGarage(){
        
        return this.currentGarage;
    }
    
    public void setParseUser(ParseUser incomingParseUser){
        this.currentUser = incomingParseUser;
    }
    
    public ParseUser getParseUser(){
        return this.currentUser;
    }
    
    
    /**
     * Method to sing up a Parse.com user
     * @param userName, username which should be EMAIL 
     * @param password
     * @return ParseUser
     */
    public ParseUser signUpUser(String userName, String password){
        ParseUser user = new ParseUser();
        user.setUsername(userName);
        user.setEmail(userName);
        user.setPassword(password);       

        try {
            user.signUp();
            this.currentUser = user;                   
        } catch (ParseException ex) {
            //Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            //this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE, ex.toString());
            if(ex.getCode() == 202){
                //this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE, "Username " + userName + " is already taken! Please try another.");
            }
        }        
        return user;
    }
    
    public void signOutUser() throws ParseException{
        if(this.currentUser==null || this.currentUser.getSessionToken().equals("")){
            System.out.println("THERE IS NO CURRENT USER. OR SESSION.");
            return;
        }

        try {
            this.currentUser.logout();
            this.currentUser = null; // REMOVE WHEN WE USE SESSION TOKENS FOR REALS
        } catch (ParseException ex) {
            Logger.getLogger(ParseEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("User is logged out");

    }
    
    /**
     * Method to sign in a Parse.com user
     * @param userName
     * @param password
     * @return ParseUser
     */
    public ParseUser signInUser(String userName,String password){
        ParseUser user = new ParseUser();
        user.setUsername(userName);
        //user.setEmail(email);
        user.setPassword(password);    
        try {
            //user.signUp();
            user = user.login(userName,password);
            
            //System.out.println(user.getSessionToken().toString());
            //user.signUp()
            user.save();
            //this.createGarage(user);
            //this.ensureUserHasGarage(user);
            this.currentUser = user;
            //user.logout();                  
        } catch (ParseException ex) {
            //Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.out.println("poopies");
            //this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE, ex.toString());
            if(ex.getCode()==101){
                //this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(DialogType.WARNING_MESSAGE, "Invalid login parameters. Either your username or password is incorrect");
            }

        }    
        return user;
    }
    
    
    public void ensureUserHasGarage(ParseUser user) throws ParseException{       
        ParseQuery<ParseObject> basicQuery = ParseQuery.getQuery("Garage");
        basicQuery.whereEqualTo("User", user);
        basicQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> garageList, ParseException e) {
                if (e == null) {
                    if(garageList == null || garageList.isEmpty()){
                        //return true;
                        System.out.println("0 garages found");
                        
                                // build out garage
                        //ParseObject garage = new ParseObject("Garage");
                        //garage.put("User", user);
                        //garage.save();
                        
                        return; // exit, as it's either null or 0, and it'll break...
                    }
                    System.out.println("Retrieved " + garageList.size() + " garages");
                    for (ParseObject garage : garageList) {
			System.out.println(garage.getObjectId());
                        //System.out.println(garage.getRelation("QicblXGx6a"));
                        JSONObject test = garage.getParseData();
                        System.out.println(test.toString());
                        //List testList = garage.getList("QicblXGx6a");
                        
                    }
                } else {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        System.out.println("WHOA WE HAVE : " + basicQuery.toString());
        //basicQuery.
        
    }
    
    public boolean createGarage(ParseUser incomingUser){
        // build out garage
        ParseObject garage = new ParseObject("Garage");
        garage.put("User", incomingUser);
        try{
            garage.save();
            return true;
        }catch (ParseException ex) {
            //Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public void startProgressDialog(String incomingString){
        this.motoGarageNotebookEngine.startProgressDialogWindow(incomingString);
    }
    
    public void stopProgressDialog(){
        this.motoGarageNotebookEngine.stopProgressDialogWindow();
    }
    
    public void saveGarage(byte[] incomingData) throws ParseException{
        this.checkUserGarages();
        System.out.println("Outgoing byte array length is: " + incomingData.length);
        //startProgressDialog(true);
        startProgressDialog("Uploading...");

        ParseFile file = new ParseFile("test2.mnb", incomingData);

        //file.save();

        file.save(new ProgressCallback() {   
        @Override
        public void done(Integer percentDone) {
                //do something
                System.out.println(" PERCENT DONE : " + percentDone);
                if(percentDone==100){
                    //printMessage("File saved to cloud successfully.");
                }else{
    
                    printMessage("File saved to cloud unsuccessfully.");
                }
            
            }
        });

        
//        file.saveInBackground(new SaveCallback() {
//  public void done(ParseException e) {
//    // Handle success or failure here ...
//      System.out.println(" UPLOAD DONE?!?!?!?????");
//  }
//}, new ProgressCallback() {
//  public void done(Integer percentDone) {
//    // Update your progress spinner here. percentDone will be between 0 and 100.
//      System.out.println(percentDone + " : PERCENT DONE!!!!!!!!!!!");
//  }
//});
        
        this.setTempFile(file);
        //file.saveInBackground();
        
        // HERE WE NEED TO CHECK TO SEE IF THERE IS A GARAGE, and if THERE IS, SAVE TO IT INSTEAD OF NEW ONE...
        //this.checkUserGarages();

//        try {
//            Thread.sleep(5000);                 //1000 milliseconds is one second.
//        } catch(InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }
        System.out.println("USER HAS GARAGE? : " + this.hasGarage);
        if(this.hasGarage){    
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Garage");
            // Retrieve the object by id
            query.getInBackground(this.getCurrentGarage().getObjectId(), new GetCallback<ParseObject>() {
            public void done(ParseObject garage, ParseException e) {
                if (e == null) {
                // Now let's update it with some new data. In this case, only cheatMode and score
                // will get sent to the Parse Cloud. playerName hasn't changed.
                    //startProgressDialog("Uploading...");
                    garage.put("GarageFile", getTempFile());
                    garage.saveInBackground();
                    System.out.println("Storing the file to garage..." + garage.getObjectId());
                    }
                }
            });
        }else{
            //startProgressDialog("Uploading...");
            ParseObject garage = new ParseObject("Garage");
            garage.put("User", this.currentUser);
            garage.put("GarageFile", file);
            System.out.println("PRINTING TO NEW GARAGE?WHY?");
            garage.save(); 
            }

        stopProgressDialog(); 
        printMessage("File saved to cloud successfully.");

    }
    
    public void printMessage(String message){
        this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(null, DialogType.INFORMATION_MESSAGE, message);
    }
    
    
    
    public void openGarage(final ParseObject garage){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Garage");
        query.getInBackground(garage.getObjectId(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {

                    ParseFile garageFile = (ParseFile) garage.get("GarageFile");
                    startProgressDialog("Downloading...");
                    try {
                        byte[] testBytes = garageFile.getData();                       
                        
                        //System.out.println(" DID WE DOWNLOAD IT? SIZE IS..." + testBytes.length);
                        setGarage(testBytes);
                        stopProgressDialog();
  
                    } catch (ParseException ex) {
                        Logger.getLogger(ParseEngine.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ParseEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                // something went wrong
                }
            }
        });    
    }
    
    public void setGarage(byte[] bytes) throws IOException{
        //System.out.println("WE ARE OUT OF THAT INNER CLASS BOYYYYYYYYYYYYYYY " +  bytes.length);
        File cloudFile = this.motoGarageNotebookEngine.deserializeTest(bytes);
        this.motoGarageNotebookEngine.openGarage(cloudFile);
        //this.motoGarageNotebookEngine.getDialogFactory().createDialogMessage(null, DialogType.CONFIRM, appId);
    }
    
    
    public void openUsersGarage(){
        // first, list the garages the user has...
        if(this.currentUser!=null){
            ParseQuery<ParseObject> basicQuery = ParseQuery.getQuery("Garage");
            basicQuery.whereEqualTo("User", this.currentUser);
            basicQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> garageList, ParseException e) {
                if (e == null) {
                    //System.out.println("Ok confused... size is.. " + garageList.size());
                    if(garageList.isEmpty()){
                        System.out.println("0 garages found!!! Can't open a garage when one doesn't exist!!");
                        //TODO  should auto create one here?
                    }else if(garageList.size()==1){
                        System.out.println("User has ONE garage. this is a good thing......................");
                        ParseObject garage = garageList.get(0);
                        openGarage(garage);
                    }else{
                        System.out.println("Retrieved " + garageList.size() + " garages!!!!!!!!!");
                        for (ParseObject garage : garageList) {
                            System.out.println(garage.getObjectId());
                            JSONObject test = garage.getParseData();
                            System.out.println(test.toString());
                            System.out.println("WWEWEJOWIPEHJOAWHEOIAWEH:");
                            
                        }
                    }
                    // TEST TEST TEST 
                    //ParseFile garageFile = (ParseFile) garage.get("applicantResumeFile");
                } else {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        System.out.println("WHOA WE HAVEEEE : " + basicQuery.toString());                             
        }else{
            System.out.println("THERE IS NO CURRENT USER! :(");
        }   
    }
    
    /**
     * Method to check the user garages
     * <li> If 0, do...
     * <li> If 1, set the current garage as it (with corresponding objectId)
     * <li> If 1+, .... crap
     */
    public void checkUserGarages(){
        System.out.println("Inside check user garages...");
        // first, list the garages the user has...
        if(this.currentUser!=null){
            ParseQuery<ParseObject> basicQuery = ParseQuery.getQuery("Garage");
            basicQuery.whereEqualTo("User", this.currentUser);
            basicQuery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> garageList, ParseException e) {
                if (e == null) {
                    //System.out.println("Ok confused... size is.. " + garageList.size());
                    if(garageList == null || garageList.isEmpty()){
                        //return true;
                        System.out.println("0 garages found!!! Can't open a garage when one doesn't exist!!");

                    }else if(garageList.size()==1){
                        System.out.println("We found one garage for user, this is good.");
                        ParseObject garage = garageList.get(0);
                        System.out.println("The user garage object id is ..." + garage.getObjectId());
                        //openGarage(garage);
                        //setUserGarageObjectId(garage.getObjectId());
                        setCurrentGarage(garage);
                    }else{
                        System.out.println("Retrieved " + garageList.size() + " garages!!!!!!!!!");
                        for (ParseObject garage : garageList) {
                            System.out.println(garage.getObjectId());
                            JSONObject test = garage.getParseData();
                            System.out.println(test.toString());
                            System.out.println("WWEWEJOWIPEHJOAWHEOIAWEH:");
                            
                        }
                    }
                    // TEST TEST TEST 
                    //ParseFile garageFile = (ParseFile) garage.get("applicantResumeFile");
                } else {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        });
        System.out.println("WHOA WE HAVEEEE : " + basicQuery.toString());                             
        }else{
            System.out.println("THERE IS NO CURRENT USER! :(");
        }   
    }

    //public void setUserGarageObjectId(String objectId){
    //    this.motoGarageNotebookEngine.setGarageObjectId(objectId);
    //}
    
    //public String getUserGarageObjectId(){
    //    return this.motoGarageNotebookEngine.getGarageObjectId();
    //}
    
    @Deprecated
    public void downloadGarage(){
        ParseObject tempGarage = new ParseObject("Garage");
        //garage.put("User", "Joe Smith");
        tempGarage.put("User", this.currentUser);
        //garage.put("GarageFile", file);
        
        ParseFile garage = (ParseFile) tempGarage.get("GarageFile");
        garage.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    // data has the bytes for the resume
                } else {
                    // something went wrong
                }
            }
        });
    }
    
}
