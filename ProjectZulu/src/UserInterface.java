import java.sql.*;
import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame implements ActionListener {
	DatabaseAdapter db = new DatabaseAdapter();
	
	private JFrame frame1;
	private JLabel introLabel, monthLabel1, monthLabel2, yearLabel1, yearLabel2, regionLabel1, regionLabel2;
    private JTextField monthField1, monthField2, yearField1, yearField2, regionField1, regionField2;
    private JPanel mainPanel, buttonPanel, pair1, pair2, pair3, pair4, pair5, pair6;
    private JButton submitButton, regionButton, seriesButton;
    
    private JTextField[] regionsArray = {null};
    private JTextField[][] seriesArray = {{null}};
    
    private int newRegionCounter = 3;
    private int newSeriesCounter = 1;
    
    public static void main(String[] args) {
        // Create an instance of the program form
        UserInterface UserInterface = new UserInterface();
    }
    
    public UserInterface() {
        // Set up the form
    	frame1 = new JFrame("Project Zulu");
    	frame1.setLayout(new FlowLayout());
    	frame1.setSize(510, 370);
    	//frame1.setResizable(false);
        frame1.setTitle("Project Zulu - NHPI Comparison & Forecasting");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize the labels
        introLabel = new JLabel("Please enter your start/end months/years and region(s) you would like to get data for.");
        monthLabel1 = new JLabel("Start Month:", JLabel.RIGHT);
        monthLabel1.setPreferredSize(new Dimension(85, 20));
        monthLabel2 = new JLabel("End Month:", JLabel.RIGHT);
        monthLabel2.setPreferredSize(new Dimension(85, 20));
        yearLabel1 = new JLabel("Start Year:", JLabel.RIGHT);
        yearLabel1.setPreferredSize(new Dimension(85, 20));
        yearLabel2 = new JLabel("End Year:", JLabel.RIGHT);
        yearLabel2.setPreferredSize(new Dimension(85, 20));
        regionLabel1 = new JLabel("Region 1:", JLabel.RIGHT);
        regionLabel1.setPreferredSize(new Dimension(85, 20));
        regionLabel2 = new JLabel("Region 2:", JLabel.RIGHT);
        regionLabel2.setPreferredSize(new Dimension(85, 20));
        
        // Initialize the text fields
        monthField1 = new JTextField(25);
        monthField2 = new JTextField(25);
        yearField1 = new JTextField(25);
        yearField2 = new JTextField(25);
        regionField1 = new JTextField(25);
        regionField2 = new JTextField(25);
        
        // Initialize the panels
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        pair1 = new JPanel();
        pair1.setLayout(new FlowLayout());
        pair2 = new JPanel();
        pair2.setLayout(new FlowLayout());
        pair3 = new JPanel();
        pair3.setLayout(new FlowLayout());
        pair4 = new JPanel();
        pair4.setLayout(new FlowLayout());
        pair5 = new JPanel();
        pair5.setLayout(new FlowLayout());
        pair6 = new JPanel();
        pair6.setLayout(new FlowLayout());
        
        // Initialize the buttons
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        regionButton = new JButton("Add Region");
        regionButton.addActionListener(this);
        seriesButton = new JButton("Add Time-Series");
        seriesButton.addActionListener(this);
        
        // Add the form components to the frame
        frame1.add(introLabel);
        frame1.add(mainPanel);
        
        mainPanel.add(pair1);
        pair1.add(monthLabel1);
        pair1.add(monthField1);
        mainPanel.add(pair2);
        pair2.add(monthLabel2);
        pair2.add(monthField2);
        mainPanel.add(pair3);
        pair3.add(yearLabel1);
        pair3.add(yearField1);
        mainPanel.add(pair4);
        pair4.add(yearLabel2);
        pair4.add(yearField2);
        
        mainPanel.add(pair5);
        pair5.add(regionLabel1);
        pair5.add(regionField1);
        
    	regionsArray[0] = regionField1;
        
        mainPanel.add(pair6);
        pair6.add(regionLabel2);
        pair6.add(regionField2);
        
        regionsArray = Arrays.copyOf(regionsArray, regionsArray.length + 1);
        regionsArray[regionsArray.length - 1] = regionField2;
        
        frame1.add(buttonPanel);
        buttonPanel.add(submitButton);
        buttonPanel.add(regionButton);
        buttonPanel.add(seriesButton);
        
        // Show the form & its components
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
        	// Get the user inputs and store them in variables
        	for (int i = 0; i < regionsArray.length; i++) {
        		String startMonth = monthField1.getText();
        		String endMonth = monthField2.getText();
                int startYear = Integer.parseInt(yearField1.getText());
                int endYear = Integer.parseInt(yearField2.getText());
                    
                String region = regionsArray[i].getText();
                    
                ResultSet resultSetAnyRegion = db.callDB(startMonth, endMonth, startYear, endYear, region);
                    
                if (seriesArray.length == 2) {
                	startMonth = seriesArray[1][0].getText();
                    endMonth = seriesArray[1][1].getText();
                    startYear = Integer.parseInt(seriesArray[1][2].getText());
                    endYear = Integer.parseInt(seriesArray[1][3].getText());
                        
                    ResultSet resultSetS2AnyRegion = db.callDB(startMonth, endMonth, startYear, endYear, region);
                }
        	}
        }
        
        else if (e.getSource() == regionButton) {
			JLabel label = new JLabel("Region " + newRegionCounter + ":", JLabel.RIGHT);
			label.setPreferredSize(new Dimension(85, 20));
			
			JTextField field = new JTextField(25);
			
			JPanel newPair = new JPanel();
			newPair.setLayout(new FlowLayout());
			
			mainPanel.add(newPair);
			newPair.add(label);
			newPair.add(field);
			
			regionsArray = Arrays.copyOf(regionsArray, regionsArray.length + 1);
			regionsArray[regionsArray.length - 1] = field;
			
			newRegionCounter += 1;
        }
        
        else if (e.getSource() == seriesButton) {
        	if (newSeriesCounter == 1) {
        		JTextField[] tempArray1 = {monthField1, monthField2, yearField1, yearField2};
        		seriesArray[0] = tempArray1;
        	}
        	
        	newSeriesCounter += 1;
			
			JLabel label1 = new JLabel("Start Month " + newSeriesCounter + ": ", JLabel.RIGHT);
			label1.setPreferredSize(new Dimension(85, 20));
			JLabel label2 = new JLabel("End Month " + newSeriesCounter + ": ", JLabel.RIGHT);
			label2.setPreferredSize(new Dimension(85, 20));
			JLabel label3 = new JLabel("Start Year " + newSeriesCounter + ": ", JLabel.RIGHT);
			label3.setPreferredSize(new Dimension(85, 20));
			JLabel label4 = new JLabel("End Year " + newSeriesCounter + ": ", JLabel.RIGHT);
			label4.setPreferredSize(new Dimension(85, 20));
			
			JTextField field1 = new JTextField(25);
			JTextField field2 = new JTextField(25);
			JTextField field3 = new JTextField(25);
			JTextField field4 = new JTextField(25);
			
			JPanel newPair1 = new JPanel();
			newPair1.setLayout(new FlowLayout());
			mainPanel.add(newPair1);
			newPair1.add(label1);
			newPair1.add(field1);
			
			JPanel newPair2 = new JPanel();
			newPair2.setLayout(new FlowLayout());
			mainPanel.add(newPair2);
			newPair2.add(label2);
			newPair2.add(field2);
			
			JPanel newPair3 = new JPanel();
			newPair3.setLayout(new FlowLayout());
			mainPanel.add(newPair3);
			newPair3.add(label3);
			newPair3.add(field3);
			
			JPanel newPair4 = new JPanel();
			newPair4.setLayout(new FlowLayout());
			mainPanel.add(newPair4);
			newPair4.add(label4);
			newPair4.add(field4);
			
			seriesArray = Arrays.copyOf(seriesArray, seriesArray.length + 1);
			JTextField[] tempArray2 = {field1, field2, field3, field4};
			seriesArray[seriesArray.length - 1] = tempArray2;
			
			buttonPanel.remove(seriesButton);
        }
        
        frame1.revalidate();
        frame1.repaint();
    }
}