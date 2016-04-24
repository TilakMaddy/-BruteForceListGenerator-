/**********************************************************************
                                                                      * 
             Tilak Maddy Inc. © Java GUI Studio                       *
                                                                      *  
***********************************************************************/
package password;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Tilak Maddy Inc.©
 */
public class FirstLayoutController implements Initializable {
    
    private static final String BRUTE_FORCE_WORDLIST_URL = "https://raw.githubusercontent.com/danielmiessler/SecLists/master/Passwords/10_million_password_list_top_10000.txt"; //"http://scrapmaker.com/data/wordlists/dictionaries/rockyou.txt";
    private static final String DEVELOPER_NAME = "Tilak Madichetti";    
       
    public TextField firstName;
    public TextField lastName;
    public TextField phNumber;
    public TextField padmesName;
    public ToggleButton gender; 
    public DatePicker bDay;
    public ImageView imageView;
    public Button startMotherhipButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
                
    }    
    
    //Button Clicked on ' Iniciar la nave Nodriza '
    public void startMothership(){  
        
        startMotherhipButton.setText(DEVELOPER_NAME + " is creating brute list for you. Please wait..");
                                      
        /* Optional Fields: Phone Number, Padme's name
           Required Fields: First name, Last Name, Birthday
        */
        
        //Print the received info                 
        System.out.println("--------------------------------");
        System.out.println("FirstName  " + firstName.getText().equals(""));
        System.out.println("LastNamne  " + lastName.getText().equals(""));
        System.out.println("Contact    " + phNumber.getText().equals(""));
        System.out.println("PadmesName " + padmesName.getText().equals(""));
        System.out.println("B-Day      " + bDay.getValue());                
        
        Person victim = null;
        
        //All fields are filled
        if(firstName.getText().length() != 0 && lastName.getText().length() != 0 && phNumber.getText().length() != 0 && padmesName.getText().length() != 0 && String.valueOf(bDay.getValue() == null).trim().equals("false")){
            System.out.println("All Boxes are filled ");
            victim = new Person(firstName.getText(),lastName.getText(), bDay.getValue().toString(), phNumber.getText(), padmesName.getText());
        }
        // Minimum Fields are filled
        else if(firstName.getText().length() != 0 && lastName.getText().length() != 0 && phNumber.getText().length() == 0 && padmesName.getText().length() == 0 && String.valueOf(bDay.getValue() == null).trim().equals("false")){
            System.out.println("Only Minimum Crap filled.");
            victim = new Person(firstName.getText(), lastName.getText(), bDay.getValue().toString());
        }
        // Minimum Fields are filled + Phone number
        else if(firstName.getText().length() != 0 && lastName.getText().length() != 0 && phNumber.getText().length() != 0 && padmesName.getText().length() == 0 && String.valueOf(bDay.getValue() == null).trim().equals("false")){
            System.out.println("Minimum Crap + Phone nuumber filled.");
            victim = new Person(firstName.getText(), lastName.getText(), bDay.getValue().toString(), phNumber.getText());
        }
        // Minimum fields are filled + Padmes Name
        else if(firstName.getText().length() != 0 && lastName.getText().length() != 0 && phNumber.getText().length() == 0 && padmesName.getText().length() != 0 && String.valueOf(bDay.getValue() == null).trim().equals("false")){
            System.out.println("Minimum Crap + Padems name filled.");
            victim = new Person(firstName.getText(), lastName.getText(), bDay.getValue().toString(), padmesName.getText(), true);
        }
        //No minimum fields filled
        else{
             JOptionPane.showMessageDialog(null, "No minimum boxes filled.Required ones are..\n1)First Name\n2)Last Name\n3)Birthday", "Error.", JOptionPane.ERROR_MESSAGE);
        }     
        
        if(victim != null){
            try{
                
                String fPath = "D:/" +  firstName.getText().toLowerCase() + "_" + "wordlist.txt";
                String data = victim.getPasswordList();
                writeToFile(new File(fPath), data);
                
                JOptionPane.showMessageDialog(null, "Password list created  suxxessfully at :\n " + fPath, "Success !", JOptionPane.PLAIN_MESSAGE );
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Unable to create list." , "Fail Big time !", JOptionPane.PLAIN_MESSAGE );
                e.printStackTrace();
            }
        }else{
            
            try{
                JOptionPane.showMessageDialog(null, "Could not create file. Already existing.");
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        
    }    
    
    public boolean writeToFile(File file, String data){
        
        Formatter fileWriter;
        
        try {
            String path = "";
            
            if(!file.exists()){
                
                try {
                    
                    file.createNewFile();
                    path = file.getCanonicalPath();
                    
                } catch (IOException ex) {
                    Logger.getLogger(FirstLayoutController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            fileWriter = new Formatter(path);
            fileWriter.format("%s", data);
            fileWriter.close();            
                     
            return true;
            
        } catch (FileNotFoundException ex) {          
            Logger.getLogger(FirstLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    private class Person {
        
        private final String firstName;
        private final String lastName;
        private final String bDay;
        private String phNumber;
        private String padmesName;
        
        private boolean onlyMin = false;
        private boolean minPhone = false;
        private boolean all = false;
        private boolean minPadme = false;
               

        //All fields constructor
        public Person(String firstName, String lastName, String bDay, String phNumber, String padmesName) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.bDay = bDay;
            this.phNumber = phNumber;
            this.padmesName = padmesName;
            all = true;
        }
        
        //Minimum fields only
        public Person(String firstName, String lastName, String bDay) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.bDay = bDay;               
            onlyMin = true;
        }
        
        //Minimum Fields + Phone number
        public Person(String firstName, String lastName, String bDay, String phNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.bDay = bDay;
            this.phNumber = phNumber;
            minPhone = true;
        }

        //Minimum Fields + Padems Name
        public Person(String firstName, String lastName, String bDay, String padmesName, boolean dummy) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.bDay = bDay;
            this.padmesName = padmesName;
            minPadme = true;
        }
                        
        private String getFirstName() {
            return firstName;
        }
  
        private String getLastName() {
            return lastName;
        }

        private String getbDay() {
            return bDay;
        }
      
        private String getPhNumber() {
            return phNumber;
        }
        
        private String getPadmesName() {
            return padmesName;
        }          
        
        private String getCommonPasswords() {
            
            StringBuilder content = new StringBuilder("");
    
            try {
               
                URL url = new URL(BRUTE_FORCE_WORDLIST_URL);
                URLConnection urlConnection = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line.concat(System.getProperty("line.separator")));
                }
                bufferedReader.close();
                
            } catch (Exception e) {
                System.out.printf("%s", "Error occured.");
                JOptionPane.showMessageDialog(null, "Enter a proper URL", "Fuck you", JOptionPane.ERROR_MESSAGE);
                       
            }
            
            return content.toString();
            
        }
        
        public String getMinimumPasswords(){
            
            String passwords = "";            
            passwords += getCommonPasswords() + System.getProperty("line.separator");
            
            String bYear = bDay.split("-")[0];
            String bMonth = bDay.split("-")[1];
            String bDate = bDay.split("-")[2];
            
            passwords += (firstName + lastName + System.getProperty("line.separator"));            
            passwords += (firstName + bDate + bMonth + System.getProperty("line.separator"));
            passwords += (firstName + bYear + System.getProperty("line.separator"));
            passwords += (bDate + bMonth + bYear + System.getProperty("line.separator"));
            passwords += (lastName + "rox" + System.getProperty("line.separator"));
            passwords += (firstName + "rox" + System.getProperty("line.separator"));
            passwords += (firstName + lastName + "thegreat123" + System.getProperty("line.separator"));
            
            for(int j = 90; j<= 999; j++){
                passwords += (firstName + "thegreat" + String.valueOf(j) + System.getProperty("line.separator"));
            }
            
            passwords += ("welcometo" +firstName + "world" + System.getProperty("line.separator"));
            passwords += (firstName + bDay + bMonth + bYear + System.getProperty("line.separator"));
            
            return passwords;
            
        }
        
        public String getMinPadmesPasswords(){
        
            String passwords = "";
            passwords += getMinimumPasswords() + System.getProperty("line.separator");
            
            passwords += (firstName + "loves" + this.padmesName  + System.getProperty("line.separator"));
            passwords += (firstName + "loves" + this.padmesName + "123" + System.getProperty("line.separator"));
            passwords += ("ilove" + padmesName + System.getProperty("line.separator"));
            passwords += (firstName + padmesName + "roxxx" + System.getProperty("line.separator"));
            passwords += (firstName + padmesName + "rox" + System.getProperty("line.separator"));
            passwords += (padmesName + "loves" + firstName + System.getProperty("line.separator"));           
            
            for(int i = 100;i <= 999; i++){
                passwords += ("ilove" + padmesName + String.valueOf(i) + System.getProperty("line.separator"));
                passwords += (firstName + "loves" + padmesName + String.valueOf(i) + System.getProperty("line.separator"));
            }
            
            return passwords;
        
        }
        
        public String getMinPhonePasswords(){
            
            String passwords = "";
            passwords += getMinimumPasswords() + System.getProperty("line.separator");
            
            passwords += (phNumber + System.getProperty("line.separator"));
            passwords += (firstName + phNumber + System.getProperty("line.separator"));
            passwords += (lastName  + phNumber + System.getProperty("line.separator"));
            passwords += (firstName + lastName + phNumber + System.getProperty("line.separator"));
            
            for (int i = 0; i <= 99; i++){
                passwords += (String.valueOf(i) +  firstName + "rox" + phNumber +  System.getProperty("line.separator"));
            }
            
            return passwords;
            
        }
        
        public String getAllPasswords(){
            
            String passwords = "";            
            passwords += getMinPadmesPasswords();
            
            passwords += (firstName + "loves" + this.padmesName  + System.getProperty("line.separator"));
            passwords += (firstName + "loves" + this.padmesName + "123" + System.getProperty("line.separator"));
            passwords += ("ilove" + padmesName + System.getProperty("line.separator"));
            passwords += (firstName + padmesName + "roxxx" + System.getProperty("line.separator"));
            passwords += (firstName + padmesName + "rox" + System.getProperty("line.separator"));
            passwords += (padmesName + "loves" + firstName + System.getProperty("line.separator"));           
            
            for(int i = 100;i <= 999; i++){
                passwords += ("ilove" + padmesName + String.valueOf(i) + System.getProperty("line.separator"));
                passwords += (firstName + "loves" + padmesName + String.valueOf(i) + System.getProperty("line.separator"));
            }          
            
            passwords += (phNumber + System.getProperty("line.separator"));
            passwords += (firstName + phNumber + System.getProperty("line.separator"));
            passwords += (lastName  + phNumber + System.getProperty("line.separator"));
            passwords += (firstName + lastName + phNumber + System.getProperty("line.separator"));
            
            for (int i = 0; i <= 99; i++){
                passwords += (String.valueOf(i) +  firstName + "rox" + phNumber +  System.getProperty("line.separator"));
            }
            
            // Special effects
            
            passwords += (firstName + padmesName + phNumber + System.getProperty("line.separator"));
            passwords += (lastName + "loves" + padmesName + phNumber + System.getProperty("line.separator"));
            passwords += (padmesName + "loves" + lastName + phNumber + System.getProperty("line.separator"));
            
            return passwords;
            
        }
        
        public String getPasswordList(){
            
            String list = "";
            
            if(onlyMin != false)
                return getMinimumPasswords();
            else if(minPadme != false)
                return getMinPadmesPasswords();
            else if(minPhone != false)
                return getMinPhonePasswords();
            else if(all != false)
                return getAllPasswords();
            
            return "Pass\nNot\nFound!";
            
        }
        
    }
    
    public void closeProgram(){
        
        int choice = JOptionPane.showConfirmDialog(null, "Do You want to exit the program ? ", "Exit Panel", JOptionPane.YES_NO_OPTION);
        
        if(choice == JOptionPane.YES_OPTION){
            
            Stage realStage = (Stage) firstName.getScene().getWindow();
            try {
                realStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("LastLayout.fxml"))));
            } catch (IOException ex) {
                Logger.getLogger(FirstLayoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.exit(0);                        
        }
        
    }
       
}
