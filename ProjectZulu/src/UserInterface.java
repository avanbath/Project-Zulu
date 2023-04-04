import java.util.*;
import java.util.List;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame implements ActionListener {
	// Invoke FactoryCommunicator & Controller Java interfaces
	FactoryCommunicator fc;
	Controller con;
	
	// Arrays for error-checking
	private String[] provinces = {"Alberta", "British Columbia", "Manitoba", "New Brunswick", "Newfoundland and Labrador",
			"Nova Scotia", "Ontario", "Quebec", "Prince Edward Island", "Saskatchewan"
			};
	private String[] cities = {"Calgary, Alberta", "Charlottetown, Prince Edward Island", "Edmonton, Alberta", "Greater Sudbury, Ontario",
			"Guelph, Ontario", "Halifax, Nova Scotia", "Hamilton, Ontario", "Kelowna, British Columbia", "Kitchener-Cambridge-Waterloo, Ontario",
			"London, Ontario", "Montreal, Quebec", "Oshawa, Ontario", "Ottawa, Ontario", "Quebec, Quebec", "Regina, Saskatchewan", 
			"Saint John, Fredericton, and Moncton, New Brunswick", "Saskatoon, Saskatchewan", "Sherbrooke, Quebec", "St. Catharines-Niagara, Ontario", 
			"St. John's, Newfoundland and Labrador","Toronto, Ontario", "Trois-Rivieres, Quebec", "Vancouver, British Columbia", 
			"Victoria, British Columbia", "Windsor, Ontario", "Winnipeg, Manitoba"
            };
	
	// Flags for error-checking
	private Boolean provinceFlag = false;
	private Boolean cityFlag = false;
	
	// Major components used to display UI, initialized here
	private JFrame frame1;
	private JLabel title, introLabel, monthLabel1, monthLabel2, yearLabel1, yearLabel2, regionLabel1, regionLabel2;
    private JTextField monthField1, monthField2, yearField1, yearField2, regionField1, regionField2;
    private JPanel mainPanel, buttonPanel, controlPanel1, controlPanel2, pair1, pair2, pair3, pair4, pair5, pair6;
    private JScrollPane datesScrollPane, regionsScrollPane;
    private JButton submitButton, regionButton, seriesButton;
    
    // regionsArray and seriesArray store regions and series respectively
    private JTextField[] regionsArray = {null};
    private JTextField[][] seriesArray = {{null}};
    
    private int newRegionCounter = 3;
    private int newSeriesCounter = 1;
    
    public static void main(String[] args) {
        // Create an instance of the program form and Controller/FactoryCommunicator interfaces for use
        UserInterface UI = new UserInterface();
		ControllerIMPL c = new ControllerIMPL();
		FactoryCommunicator fc = new VisualMaker();
		
		UI.setCon(c);
		UI.setFC(fc);
    }
    
    // Main UI method, handles most UI tasks
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
    	
    	// Set the main window's properties
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
        
        // GroupLayout custom settings for the main window
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
    
    // This method handles all button presses & other functional logic in the program to display results
    @Override
    public void actionPerformed(ActionEvent e) {
    	Boolean noError = false;
    	JDialog prompt = new JDialog();
    	JLabel invalidLabel = new JLabel();
    	JButton okButton = new JButton();
    	
        okButton.setText("OK");
        okButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		frame1.setVisible(true);
        		prompt.dispose();
        	}
        });
        
        // If the Submit button is clicked, go to the next window for operation selection, then show the results as per requirements
        if (e.getSource() == submitButton) {
        	provinceFlag = false;
        	cityFlag = false;
        	
        	// Validity check to see if a province and city were both entered, which is illegal
        	for (int i = 0; i < regionsArray.length; i++) {
        		String region = regionsArray[i].getText();
        		
                for (int j = 0; j < provinces.length; j++) {
                	if (region.equals(provinces[j])) {
                		provinceFlag = true;
                	}
                }
                
                for (int k = 0; k < cities.length; k++) {
                	if (region.equals(cities[k]))  {
                		cityFlag = true;
                	}
                }
                
            	if (provinceFlag == true) {
            		if (cityFlag == true) {
            			noError = false;
            			
            			// Update UI to show error pop-up
            			prompt.setTitle("Invalid Input");
            	    	prompt.setResizable(false);
            	    	prompt.setPreferredSize(new Dimension(300, 100));
            	    	prompt.setLayout(new FlowLayout());
            	    	
            	        invalidLabel.setText("Please enter only provinces or only cities");
            	        
            	        prompt.add(invalidLabel);
            	        prompt.add(okButton);
            	        prompt.pack();
            	        prompt.setLocationRelativeTo(null);
            	        prompt.setAlwaysOnTop(true);
            	        prompt.setVisible(true);
            		}
            		
            		else {
                		noError = true;
                		
                    	String startMonth = monthField1.getText();
                    	String endMonth = monthField2.getText();
                        String startYear = yearField1.getText();
                        String endYear = yearField2.getText();
                        
                        con.addRegion(region);
                        con.addTimeSeries(startYear + "-" + startMonth, endYear + "-" + endMonth);
                        
                        if (seriesArray.length == 2) {
                        	startMonth = seriesArray[1][0].getText();
                            endMonth = seriesArray[1][1].getText();
                            startYear = seriesArray[1][2].getText();
                            endYear = seriesArray[1][3].getText();
                            
                            con.addRegion(region);
                            con.addTimeSeries(startYear + "-" + startMonth, endYear + "-" + endMonth);
                        }
            		}
            	}
            	
            	else if (cityFlag == true) {
            		if (provinceFlag == true) {
            			noError = false;
            			
            			// Update UI to show error pop-up
            			prompt.setTitle("Invalid Input");
            	    	prompt.setResizable(false);
            	    	prompt.setPreferredSize(new Dimension(300, 100));
            	    	prompt.setLayout(new FlowLayout());
            	    	
            	        invalidLabel.setText("Please enter only provinces or only cities");
            	        
            	        prompt.add(invalidLabel);
            	        prompt.add(okButton);
            	        prompt.pack();
            	        prompt.setLocationRelativeTo(null);
            	        prompt.setAlwaysOnTop(true);
            	        prompt.setVisible(true);
            		}
            		
            		else if (provinceFlag == false) {
                		noError = true;
                		
                    	String startMonth = monthField1.getText();
                    	String endMonth = monthField2.getText();
                        String startYear = yearField1.getText();
                        String endYear = yearField2.getText();
                        
                        con.addRegion(region);
                        con.addTimeSeries(startYear + "-" + startMonth, endYear + "-" + endMonth);
                        
                        if (seriesArray.length == 2) {
                        	startMonth = seriesArray[1][0].getText();
                            endMonth = seriesArray[1][1].getText();
                            startYear = seriesArray[1][2].getText();
                            endYear = seriesArray[1][3].getText();
                            
                            con.addRegion(region);
                            con.addTimeSeries(startYear + "-" + startMonth, endYear + "-" + endMonth);
                        }
            		}
            	}
            }
        	
        	if (noError == true) {
            	frame1.setVisible(false);
        		
                JFrame frame2 = new JFrame();
                JLabel selectLabel = new JLabel();
                JPanel operPanel = new JPanel();
                JButton mlButton = new JButton();
                JButton tButton = new JButton();
                JButton nhpiButton = new JButton();
                JButton backButton = new JButton();
                	
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
                mlButton.addActionListener(new ActionListener() {
                	@Override
                	public void actionPerformed(ActionEvent e) {    		
                		frame2.setVisible(false);
                		
                        JFrame frame3 = new JFrame();
                        JLabel graphSelectLabel = new JLabel();
                        JPanel gPanel = new JPanel();
                        JButton lineG = new JButton();
                        JButton barG = new JButton();
                        JButton backButton = new JButton();
                        
                        frame3.setTitle("Select Graph");
                        frame3.setResizable(false);
                        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame3.setLayout(new FlowLayout());
                        frame3.setSize(600, 120);
                        frame3.setLocationRelativeTo(null);
                		
                		graphSelectLabel.setText("Which type of graph would you like to choose?:");
                        graphSelectLabel.setFont(selectLabel.getFont().deriveFont(14f));
                        graphSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        
                        gPanel.setLayout(new FlowLayout());
                        
                        lineG.setText("Line Graph");
                        lineG.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                        barG.setText("Bar Graph");
                        barG.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                        
                        gPanel.add(lineG);
                        gPanel.add(barG);
                        
                        backButton.setText("Go Back");
                        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                        backButton.addActionListener(new ActionListener() {
                        	@Override
                        	public void actionPerformed(ActionEvent e) {
                        		frame3.dispose();
                        		frame2.setVisible(true);
                        	}
                        });
                		
                        frame3.add(graphSelectLabel);
                        frame3.add(gPanel);
                        frame3.add(backButton);
                        
                        frame3.setVisible(true);
                        
                        // barG, lineG add action event handlers to show graph
                        barG.addActionListener(new ActionListener() {
                        	@Override
                        	public void actionPerformed(ActionEvent e) {
                        		fc.setCreatorBar();
                        		
                        		List<String> outputs = new ArrayList<String>();
                        		Iterator<String> it = outputs.iterator();
                        		
                        		con.setMLForecast();
                        		outputs = con.execute();
                        		
                        		frame3.setVisible(false);
                        		
                                JFrame frame4 = new JFrame();
                                JLabel mlLabel = new JLabel();
                                JButton backButton = new JButton();
                                	
                                frame4.setTitle("Graph Information & Forecasting");
                                frame4.setResizable(false);
                                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame4.setLayout(new FlowLayout());
                                frame4.setSize(600, 600);
                                frame4.setLocationRelativeTo(null);
                                    
                                mlLabel.setText("Here are the generated existing and forecasted graphs for all regions.");
                                mlLabel.setFont(selectLabel.getFont().deriveFont(14f));
                                mlLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                
                                backButton.setText("Go Back");
                                backButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                                //backButton.setPreferredSize(new Dimension(150, 125));
                                backButton.addActionListener(new ActionListener() {
                                	@Override
                                	public void actionPerformed(ActionEvent e) {
                                		frame4.dispose();
                                		frame3.setVisible(true);
                                	}
                                });
                                
                                frame4.add(mlLabel);
                                frame4.add(backButton);
                                frame4.setVisible(true);
                        	}
                        });
                        
                        lineG.addActionListener(new ActionListener() {
                        	@Override
                        	public void actionPerformed(ActionEvent e) {
                        		fc.setCreatorLine();
                        		
                        		List<String> outputs = new ArrayList<String>();
                        		Iterator<String> it = outputs.iterator();
                        		
                        		con.setMLForecast();
                        		outputs = con.execute();
                        		
                        		frame3.setVisible(false);
                        		
                                JFrame frame4 = new JFrame();
                                JLabel mlLabel = new JLabel();
                                JButton backButton = new JButton();
                                	
                                frame4.setTitle("Graph Information & Forecasting");
                                frame4.setResizable(false);
                                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame4.setLayout(new FlowLayout());
                                frame4.setSize(600, 600);
                                frame4.setLocationRelativeTo(null);
                                    
                                mlLabel.setText("Here are the generated existing and forecasted graphs for all regions.");
                                mlLabel.setFont(selectLabel.getFont().deriveFont(14f));
                                mlLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                
                                backButton.setText("Go Back");
                                backButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                                //backButton.setPreferredSize(new Dimension(150, 125));
                                backButton.addActionListener(new ActionListener() {
                                	@Override
                                	public void actionPerformed(ActionEvent e) {
                                		frame4.dispose();
                                		frame3.setVisible(true);
                                	}
                                });

                                frame4.add(mlLabel);
                                frame4.add(backButton);
                                frame4.setVisible(true);
                        	}
                        });
                	}
                });
                operPanel.add(mlButton);
                    
                tButton.setText("Show Statistical T-Test");
                tButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                tButton.setPreferredSize(new Dimension(250, 25));
                tButton.addActionListener(this);
                tButton.addActionListener(new ActionListener() {
                	@Override
                	public void actionPerformed(ActionEvent e) {    		
                		frame2.setVisible(false);
                		
                        JFrame frame3 = new JFrame();
                        JLabel graphSelectLabel = new JLabel();
                        JPanel gPanel = new JPanel();
                        JButton lineG = new JButton();
                        JButton barG = new JButton();
                        JButton backButton = new JButton();
                        
                        frame3.setTitle("Select Graph");
                        frame3.setResizable(false);
                        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame3.setLayout(new FlowLayout());
                        frame3.setSize(600, 120);
                        frame3.setLocationRelativeTo(null);
                		
                		graphSelectLabel.setText("Which type of graph would you like to choose?:");
                        graphSelectLabel.setFont(selectLabel.getFont().deriveFont(14f));
                        graphSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        
                        gPanel.setLayout(new FlowLayout());
                        
                        lineG.setText("Line Graph");
                        lineG.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                        barG.setText("Bar Graph");
                        barG.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                        
                        gPanel.add(lineG);
                        gPanel.add(barG);
                        
                        backButton.setText("Go Back");
                        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                        backButton.addActionListener(new ActionListener() {
                        	@Override
                        	public void actionPerformed(ActionEvent e) {
                        		frame3.dispose();
                        		frame2.setVisible(true);
                        	}
                        });
                		
                        frame3.add(graphSelectLabel);
                        frame3.add(gPanel);
                        frame3.add(backButton);
                        
                        frame3.setVisible(true);
                        
                        // barG, lineG add action event handlers to show graph
                        barG.addActionListener(new ActionListener() {
                        	@Override
                        	public void actionPerformed(ActionEvent e) {
                        		fc.setCreatorBar();
                        		
                        		List<String> outputs = new ArrayList<String>();
                        		Iterator<String> it = outputs.iterator();
                        		
                        		con.setStatTest();
                        		outputs = con.execute();
                        		
                        		frame3.setVisible(false);
                        		
                                JFrame frame4 = new JFrame();
                                JLabel tLabel = new JLabel();
                                JButton backButton = new JButton();
                                	
                                frame4.setTitle("Graph Information & Statistical Test");
                                frame4.setResizable(false);
                                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame4.setLayout(new FlowLayout());
                                frame4.setSize(600, 600);
                                frame4.setLocationRelativeTo(null);
                                    
                                // Add iterator here and show StatTest info
                                while(it.hasNext()) {
                                	tLabel.setText(it.next());
                                }
                                
                                tLabel.setFont(selectLabel.getFont().deriveFont(14f));
                                tLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                
                                backButton.setText("Go Back");
                                backButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                                //backButton.setPreferredSize(new Dimension(150, 125));
                                backButton.addActionListener(new ActionListener() {
                                	@Override
                                	public void actionPerformed(ActionEvent e) {
                                		frame4.dispose();
                                		frame3.setVisible(true);
                                	}
                                });
                                
                                frame4.add(tLabel);
                                frame4.add(backButton);
                                frame4.setVisible(true);
                        	}
                        });
                        
                        lineG.addActionListener(new ActionListener() {
                        	@Override
                        	public void actionPerformed(ActionEvent e) {
                        		fc.setCreatorLine();
                        		List<String> outputs = new ArrayList<String>();
                        		Iterator<String> it = outputs.iterator();
                        		
                        		con.setStatTest();
                        		outputs = con.execute();
                        		
                        		frame3.setVisible(false);
                        		
                                JFrame frame4 = new JFrame();
                                JLabel tLabel = new JLabel();
                                JButton backButton = new JButton();
                                	
                                frame4.setTitle("Graph Information & Statistical Test");
                                frame4.setResizable(false);
                                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame4.setLayout(new FlowLayout());
                                frame4.setSize(600, 600);
                                frame4.setLocationRelativeTo(null);
                                    
                                // Add iterator here and show StatTest info
                                while(it.hasNext()) {
                                	tLabel.setText(it.next());
                                }
                                
                                tLabel.setFont(selectLabel.getFont().deriveFont(14f));
                                tLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                
                                backButton.setText("Go Back");
                                backButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                                //backButton.setPreferredSize(new Dimension(150, 125));
                                backButton.addActionListener(new ActionListener() {
                                	@Override
                                	public void actionPerformed(ActionEvent e) {
                                		frame4.dispose();
                                		frame3.setVisible(true);
                                	}
                                });
                                
                                frame4.add(tLabel);
                                frame4.add(backButton);
                                frame4.setVisible(true);
                        	}
                        });
                	}
                });
                operPanel.add(tButton);
                    
                nhpiButton.setText("Compare NHPIs");
                nhpiButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                nhpiButton.setPreferredSize(new Dimension(250, 25));
                nhpiButton.addActionListener(new ActionListener() {
                	@Override
                	public void actionPerformed(ActionEvent e) {    		
                		frame2.setVisible(false);
                		
                        JFrame frame3 = new JFrame();
                        JLabel graphSelectLabel = new JLabel();
                        JPanel gPanel = new JPanel();
                        JButton lineG = new JButton();
                        JButton barG = new JButton();
                        JButton backButton = new JButton();
                        
                        frame3.setTitle("Select Graph");
                        frame3.setResizable(false);
                        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame3.setLayout(new FlowLayout());
                        frame3.setSize(600, 120);
                        frame3.setLocationRelativeTo(null);
                		
                		graphSelectLabel.setText("Which type of graph would you like to choose?:");
                        graphSelectLabel.setFont(selectLabel.getFont().deriveFont(14f));
                        graphSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        
                        gPanel.setLayout(new FlowLayout());
                        
                        lineG.setText("Line Graph");
                        lineG.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                        barG.setText("Bar Graph");
                        barG.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                        
                        gPanel.add(lineG);
                        gPanel.add(barG);
                        
                        backButton.setText("Go Back");
                        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                        backButton.addActionListener(new ActionListener() {
                        	@Override
                        	public void actionPerformed(ActionEvent e) {
                        		frame3.dispose();
                        		frame2.setVisible(true);
                        	}
                        });
                		
                        frame3.add(graphSelectLabel);
                        frame3.add(gPanel);
                        frame3.add(backButton);
                        
                        frame3.setVisible(true);
                        
                        // barG, lineG add action event handlers to show graph
                        barG.addActionListener(new ActionListener() {
                        	@Override
                        	public void actionPerformed(ActionEvent e) {
                        		fc.setCreatorBar();
                        		
                        		List<String> outputs = new ArrayList<String>();
                        		Iterator<String> it = outputs.iterator();
                        		
                        		con.setCompareNHPI();
                        		outputs = con.execute();
                        		
                        		frame3.setVisible(false);
                        		
                                JFrame frame4 = new JFrame();
                                JLabel nhpiLabel = new JLabel();
                                JButton backButton = new JButton();
                                	
                                frame4.setTitle("Graph Information & NHPI Comparison");
                                frame4.setResizable(false);
                                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame4.setLayout(new FlowLayout());
                                frame4.setSize(600, 600);
                                frame4.setLocationRelativeTo(null);
                                    
                                // Add iterator here and show NHPI info
                                while(it.hasNext()) {
                                	nhpiLabel.setText(it.next());
                                }
                                
                                nhpiLabel.setFont(selectLabel.getFont().deriveFont(14f));
                                nhpiLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                
                                backButton.setText("Go Back");
                                backButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                                //backButton.setPreferredSize(new Dimension(150, 125));
                                backButton.addActionListener(new ActionListener() {
                                	@Override
                                	public void actionPerformed(ActionEvent e) {
                                		frame4.dispose();
                                		frame3.setVisible(true);
                                	}
                                });
                                
                                frame4.add(nhpiLabel);
                                frame4.add(backButton);
                                frame4.setVisible(true);
                        	}
                        });
                        
                        lineG.addActionListener(new ActionListener() {
                        	@Override
                        	public void actionPerformed(ActionEvent e) {
                        		fc.setCreatorLine();
                        		
                        		List<String> outputs = new ArrayList<String>();
                        		Iterator<String> it = outputs.iterator();
                        		
                        		con.setStatTest();
                        		outputs = con.execute();
                        		
                        		frame3.setVisible(false);
                        		
                                JFrame frame4 = new JFrame();
                                JLabel nhpiLabel = new JLabel();
                                JButton backButton = new JButton();
                                	
                                frame4.setTitle("Graph Information & NHPI Comparison");
                                frame4.setResizable(false);
                                frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame4.setLayout(new FlowLayout());
                                frame4.setSize(600, 600);
                                frame4.setLocationRelativeTo(null);
                                    
                                // Add iterator here and show NHPI info
                                while(it.hasNext()) {
                                	nhpiLabel.setText(it.next());
                                }
                                
                                nhpiLabel.setFont(selectLabel.getFont().deriveFont(14f));
                                nhpiLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                
                                backButton.setText("Go Back");
                                backButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                                //backButton.setPreferredSize(new Dimension(150, 125));
                                backButton.addActionListener(new ActionListener() {
                                	@Override
                                	public void actionPerformed(ActionEvent e) {
                                		frame4.dispose();
                                		frame3.setVisible(true);
                                	}
                                });
                                
                                frame4.add(nhpiLabel);
                                frame4.add(backButton);
                                frame4.setVisible(true);
                        	}
                        });
                	}
                });
                operPanel.add(nhpiButton);
                
                backButton.setText("Go Back");
                backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                backButton.addActionListener(new ActionListener() {
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		frame2.dispose();
                		frame1.setVisible(true);
                	}
                });
                    
                frame2.add(operPanel);
                frame2.add(backButton);
                frame2.setVisible(true);
        	}
        	
        	else if (noError == false) {
        		frame1.setVisible(false);
        	}
        }
        
        // If the Add Region button is clicked, then show an extra region field to type into
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
        
        // If the Add Time-Series button is clicked, then show 4 extra date fields to type into
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
    
    // Below are setters for the interfaces used by the UI to communicate as per the UML design
    public void setFC(FactoryCommunicator f) {
    	this.fc = f;
    }
    
    public void setCon(Controller c) {
    	this.con = c;
    }
}