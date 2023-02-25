import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame implements ActionListener {
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
            String month1 = monthField1.getText();
            String month2 = monthField2.getText();
            int year1 = Integer.parseInt(yearField1.getText());
            int year2 = Integer.parseInt(yearField2.getText());
            String region1 = regionField1.getText();
            String region2 = regionField2.getText();

            // Pass the user inputs to the database Java file to query results and send them to the visualizations creator
            // ...

            // Display a confirmation message
            JOptionPane.showMessageDialog(this, "Data submitted to server.");
        }
    }
}