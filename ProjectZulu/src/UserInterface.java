import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame implements ActionListener {
	String provinces[] = {"Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador", "Nova Scotia", "Ontario", "Quebec", "Prince Edward Island", "Saskatchewan"};
    	 String cities[] = {"Calgary, Alberta", "Charlottetown, Prince Edward Island", "Edmonton, Alberta", "Greater Sudbury, Ontario","Guelph, Ontario", "Halifax, Nova Scotia", "Hamilton, Ontario", 
       		"Kelowna, British Columbia", "Kitchener-Cambridge-Waterloo, Ontario", "London, Ontario","Montreal, Quebec","Oshawa, Ontario","Ottawa, Ontario","Quebec, Quebec","Regina, Saskatchewan",
       		"Saint John, Fredericton, and Moncton, New Brunswick","Saskatoon, Saskatchewan","Sherbrooke, Quebec","St. Catharines-Niagara, Ontario","St. John's, Newfoundland and Labrador","Toronto, Ontario",
       		"Trois-Rivieres, Quebec","Vancouver, British Columbia","Victoria, British Columbia","Windsor, Ontario","Winnipeg, Manitoba"
       		};
	DataManager db = new DataManager();
	statTest test = new statTest();
	// Text fields for user inputs
    private JTextField monthField1, monthField2, yearField1, yearField2, regionField1, regionField2;
    // Submit button
    private JButton submitButton;
    
    public static void main(String[] args) {
        // Create an instance of the form
        UserInterface UserInterface = new UserInterface();
    }
    
    public UserInterface() {
        // Set up the form
        setTitle("Project Zulu - NHPI Comparer");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        
        // Initialize the text fields
        monthField1 = new JTextField();
        monthField2 = new JTextField();
        yearField1 = new JTextField();
        yearField2 = new JTextField();
        regionField1 = new JTextField();
        regionField2 = new JTextField();
        
        // Initialize the submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        
        // Add the form components to the frame
        add(new JLabel("Start Month:", JLabel.CENTER));
        add(monthField1);
        add(new JLabel("End Month:", JLabel.CENTER));
        add(monthField2);
        add(new JLabel("Start Year:", JLabel.CENTER));
        add(yearField1);
        add(new JLabel("End Year:", JLabel.CENTER));
        add(yearField2);
        add(new JLabel("Region 1:", JLabel.CENTER));
        add(regionField1);
        add(new JLabel("Region 2:", JLabel.CENTER));
        add(regionField2);
        add(submitButton);
        
        // Show the form
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Get the user inputs and store them in variables
            String startMonth = monthField1.getText();
            String endMonth = monthField2.getText();
            int startYear = Integer.parseInt(yearField1.getText());
            int endYear = Integer.parseInt(yearField2.getText());
            String firstRegion = regionField1.getText();
            String secondRegion = regionField2.getText();
           
            
            if((validInput(firstRegion, secondRegion))&&sameLevel(firstRegion, secondRegion)) {
            	ResultSet resultSetFirstRegion = db.callDB(startMonth, endMonth, startYear, endYear, firstRegion);
            	ResultSet resultSetSecondRegion = db.callDB(startMonth, endMonth, startYear, endYear, secondRegion);
            	
            	//test.tTEST(db.NHPIDataPerTimePeriod(resultSetFirstRegion),db.NHPIDataPerTimePeriod(resultSetSecondRegion));
            	
            }
            else {
            	if(!sameLevel(firstRegion, secondRegion))
            		System.out.println("The locations do not have the same level");
            	if(!validInput(firstRegion, secondRegion)){
            		System.out.println("The locations are not valid, please insert a valid location");
            	}
            }
  
        }
    }
    
    private boolean validInput(String region, String region2) {
        boolean foundStr1 = false;
        boolean foundStr2 = false;
        for (String str : provinces) {
            if (str.equals(region)) {
                foundStr1 = true;
            }
            if (str.equals(region2)) {
                foundStr2 = true;
            }
        }
        if (foundStr1 && foundStr2) {
            return true;
        }
        for (String str : cities) {
      	  if (str.equals(region)) {
                foundStr1 = true;
            }
            if (str.equals(region2)) {
                foundStr2 = true;
            }
        }
        if (foundStr1 && foundStr2) {
            return true;
        }
        return false;
          
      }
    
    private boolean sameLevel(String region, String region2) {
      boolean foundStr1 = false;
      boolean foundStr2 = false;
      for (String str : provinces) {
          if (str.equals(region)) {
              foundStr1 = true;
          }
          if (str.equals(region2)) {
              foundStr2 = true;
          }
      }
      if (foundStr1 && foundStr2) {
          return true;
      }
      for (String str : cities) {
    	  if (str.equals(region)) {
              foundStr1 = true;
          }
          if (str.equals(region2)) {
              foundStr2 = true;
          }
      }
      if (foundStr1 && foundStr2) {
          return true;
      }
      return false;
        
    }
}
