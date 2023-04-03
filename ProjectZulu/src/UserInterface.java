import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame implements ActionListener {
	DatabaseAdapter db = new DatabaseAdapter();
	
	private String[] regions, cities;
	
	private JFrame frame1;
	private JLabel title, introLabel, monthLabel1, monthLabel2, yearLabel1, yearLabel2, regionLabel1, regionLabel2;
    private JTextField monthField1, monthField2, yearField1, yearField2, regionField1, regionField2;
    private JPanel mainPanel, buttonPanel, controlPanel1, controlPanel2, pair1, pair2, pair3, pair4, pair5, pair6;
    private JScrollPane datesScrollPane, regionsScrollPane;
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
    	frame1 = new JFrame();
    	title = new JLabel();
    	introLabel = new JLabel();
    	mainPanel = new JPanel();
    	datesScrollPane = new JScrollPane();
    	controlPanel1 = new JPanel();
    	pair1 = new JPanel();
    	monthLabel1 = new JLabel("", JLabel.RIGHT);
    	monthField1 = new JTextField();
    	pair2 = new JPanel();
    	monthLabel2 = new JLabel("", JLabel.RIGHT);
    	monthField2 = new JTextField();
    	pair3 = new JPanel();
    	yearLabel1 = new JLabel("", JLabel.RIGHT);
    	yearField1 = new JTextField();
    	pair4 = new JPanel();
    	yearLabel2 = new JLabel("", JLabel.RIGHT);
    	yearField2 = new JTextField();
    	regionsScrollPane = new JScrollPane();
    	controlPanel2 = new JPanel();
    	pair5 = new JPanel();
    	regionLabel1 = new JLabel("", JLabel.RIGHT);
    	regionField1 = new JTextField();
    	pair6 = new JPanel();
    	regionLabel2 = new JLabel("", JLabel.RIGHT);
    	regionField2 = new JTextField();
    	buttonPanel = new JPanel();
    	submitButton = new JButton();
    	regionButton = new JButton();
    	seriesButton = new JButton();
    	
    	Container frame1ContentPane = frame1.getContentPane();
    	
    	frame1.setTitle("Project Zulu - NHPI Comparison & Forecasting");
    	frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        title.setText("Project Zulu - NHPI Comparison & Forecasting");
        title.setFont(new Font("Sego UI", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        introLabel.setText("Please enter your start/end months/years and regions you would like to get NHPI data for.");
        introLabel.setFont(introLabel.getFont().deriveFont(14f));
        introLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        
        datesScrollPane.setBorder(new TitledBorder("Time-Series"));
        controlPanel1.setLayout(new BoxLayout(controlPanel1, BoxLayout.Y_AXIS));
        pair1.setLayout(new FlowLayout());
        monthLabel1.setText("Start Month 1:");
        monthLabel1.setPreferredSize(new Dimension(85, 25));
        pair1.add(monthLabel1);
        monthField1.setPreferredSize(new Dimension(200, 25));
        pair1.add(monthField1);
        controlPanel1.add(pair1);
        pair2.setLayout(new FlowLayout());
        monthLabel2.setText("End Month 1:");
        monthLabel2.setPreferredSize(new Dimension(85, 25));
        pair2.add(monthLabel2);
        monthField2.setPreferredSize(new Dimension(200, 25));
        pair2.add(monthField2);
        controlPanel1.add(pair2);
        pair3.setLayout(new FlowLayout());
        yearLabel1.setText("Start Year 1:");
        yearLabel1.setPreferredSize(new Dimension(85, 25));
        pair3.add(yearLabel1);
        yearField1.setPreferredSize(new Dimension(200, 25));
        pair3.add(yearField1);
        controlPanel1.add(pair3);
        pair4.setLayout(new FlowLayout());
        yearLabel2.setText("End Year 1:");
        yearLabel2.setPreferredSize(new Dimension(85, 25));
        pair4.add(yearLabel2);
        yearField2.setPreferredSize(new Dimension(200, 25));
        pair4.add(yearField2);
        controlPanel1.add(pair4);
        datesScrollPane.setViewportView(controlPanel1);
        mainPanel.add(datesScrollPane);
        
        regionsScrollPane.setBorder(new TitledBorder("Regions"));
        controlPanel2.setLayout(new BoxLayout(controlPanel2, BoxLayout.Y_AXIS));
        pair5.setLayout(new FlowLayout());
        regionLabel1.setText("Region 1:");
        regionLabel1.setPreferredSize(new Dimension(85, 25));
        pair5.add(regionLabel1);
        regionField1.setPreferredSize(new Dimension(200, 25));
        pair5.add(regionField1);
        controlPanel2.add(pair5);
        pair6.setLayout(new FlowLayout());
        regionLabel2.setText("Region 2:");
        regionLabel2.setPreferredSize(new Dimension(85, 25));
        pair6.add(regionLabel2);
        regionField2.setPreferredSize(new Dimension(200, 25));
        pair6.add(regionField2);
        controlPanel2.add(pair6);
        regionsScrollPane.setViewportView(controlPanel2);
        mainPanel.add(regionsScrollPane);
        
        buttonPanel.setLayout(new GridLayout());
        submitButton.setText("Submit");
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);
        regionButton.setText("Add Region");
        regionButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        regionButton.addActionListener(this);
        buttonPanel.add(regionButton);
        seriesButton.setText("Add Time-Series");
        seriesButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        seriesButton.addActionListener(this);
        buttonPanel.add(seriesButton);
        
    	regionsArray[0] = regionField1;
        regionsArray = Arrays.copyOf(regionsArray, regionsArray.length + 1);
        regionsArray[regionsArray.length - 1] = regionField2;
        
		GroupLayout frame1ContentPaneLayout = new GroupLayout(frame1ContentPane);
		frame1ContentPane.setLayout(frame1ContentPaneLayout);
		frame1ContentPaneLayout.setHorizontalGroup(
		frame1ContentPaneLayout.createParallelGroup()
			.addComponent(introLabel, GroupLayout.Alignment.CENTER, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addGroup(GroupLayout.Alignment.CENTER, frame1ContentPaneLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(frame1ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
			.addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(buttonPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addGroup(GroupLayout.Alignment.LEADING, frame1ContentPaneLayout.createSequentialGroup()
			.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE)
			.addGap(0, 0, Short.MAX_VALUE)))
			.addContainerGap())
		);
		frame1ContentPaneLayout.setVerticalGroup(
		frame1ContentPaneLayout.createParallelGroup()
			.addGroup(GroupLayout.Alignment.TRAILING, frame1ContentPaneLayout.createSequentialGroup()
			.addContainerGap()
			.addComponent(title, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(introLabel)
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
			.addContainerGap())
		);
        
        frame1.pack();
        frame1.setLocationRelativeTo(frame1.getOwner());
        frame1.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
        	// Get the user inputs and store them in variables, & show the next window
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
        	
        	frame1.dispose();
        	
        	JFrame frame2 = new JFrame();
        	JLabel selectLabel = new JLabel();
        	JPanel operPanel = new JPanel();
        	JButton mlButton = new JButton();
        	JButton tButton = new JButton();
        	JButton nhpiButton = new JButton();
        	
        	frame2.setTitle("Project Zulu - NHPI Comparison & Forecasting");
        	frame2.setResizable(false);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setLayout(new FlowLayout());
            frame2.setSize(500, 400);
            frame2.setLocationRelativeTo(null);
            
            selectLabel.setText("Select the operation that would you like to perform: ");
            selectLabel.setFont(selectLabel.getFont().deriveFont(14f));
            selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            operPanel.setLayout(new FlowLayout());
            operPanel.setPreferredSize(new Dimension(250, 90));
            
            mlButton.setText("Generate Forecast Graph");
            mlButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            mlButton.setPreferredSize(new Dimension(250, 25));
            mlButton.addActionListener(this);
            operPanel.add(mlButton);
            
            tButton.setText("Show Statistical T-Test");
            tButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            tButton.setPreferredSize(new Dimension(250, 25));
            tButton.addActionListener(this);
            operPanel.add(tButton);
            
            nhpiButton.setText("Compare NHPIs");
            nhpiButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            nhpiButton.setPreferredSize(new Dimension(250, 25));
            nhpiButton.addActionListener(this);
            operPanel.add(nhpiButton);
            
            frame2.add(operPanel);
            frame2.setVisible(true);
        }
        
        else if (e.getSource() == regionButton) {
			JLabel label = new JLabel("Region " + newRegionCounter + ":", JLabel.RIGHT);
			label.setPreferredSize(new Dimension(85, 25));
			
			JTextField field = new JTextField();
			field.setPreferredSize(new Dimension(200, 25));
			
			JPanel newPair = new JPanel();
			newPair.setLayout(new FlowLayout());
			
			controlPanel2.add(newPair);
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
			
			JLabel label1 = new JLabel("Start Month " + newSeriesCounter + ":", JLabel.RIGHT);
			label1.setPreferredSize(new Dimension(85, 25));
			JLabel label2 = new JLabel("End Month " + newSeriesCounter + ":", JLabel.RIGHT);
			label2.setPreferredSize(new Dimension(85, 25));
			JLabel label3 = new JLabel("Start Year " + newSeriesCounter + ":", JLabel.RIGHT);
			label3.setPreferredSize(new Dimension(85, 25));
			JLabel label4 = new JLabel("End Year " + newSeriesCounter + ":", JLabel.RIGHT);
			label4.setPreferredSize(new Dimension(85, 25));
			
			JTextField field1 = new JTextField();
			field1.setPreferredSize(new Dimension(200, 25));
			JTextField field2 = new JTextField();
			field2.setPreferredSize(new Dimension(200, 25));
			JTextField field3 = new JTextField();
			field3.setPreferredSize(new Dimension(200, 25));
			JTextField field4 = new JTextField();
			field4.setPreferredSize(new Dimension(200, 25));
			
			JPanel newPair1 = new JPanel();
			newPair1.setLayout(new FlowLayout());
			controlPanel1.add(newPair1);
			newPair1.add(label1);
			newPair1.add(field1);
			
			JPanel newPair2 = new JPanel();
			newPair2.setLayout(new FlowLayout());
			controlPanel1.add(newPair2);
			newPair2.add(label2);
			newPair2.add(field2);
			
			JPanel newPair3 = new JPanel();
			newPair3.setLayout(new FlowLayout());
			controlPanel1.add(newPair3);
			newPair3.add(label3);
			newPair3.add(field3);
			
			JPanel newPair4 = new JPanel();
			newPair4.setLayout(new FlowLayout());
			controlPanel1.add(newPair4);
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