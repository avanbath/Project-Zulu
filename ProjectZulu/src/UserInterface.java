// UserInterface (GUI) - Latest Version

import java.util.*;
import java.util.List;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class UserInterface extends JFrame implements ActionListener {
	private FactoryCommunicator fc;
	private Controller con;

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

	private Boolean provinceFlag = false;
	private Boolean cityFlag = false;
	private Boolean firstRegionRun = true;

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
		// Create an instance of the program form with connections to classes
		UserInterface UI = new UserInterface();
		ControllerIMPL c = new ControllerIMPL();
		VisualMaker fc = new VisualMaker();
		TableManager table = new TableManager();
		OpFacadeInter facade = new OperationFacade();
		DatabaseAdapter dA = new DatabaseAdapter();

		UI.setCon(c);
		UI.setFC(fc);
		c.setOpFacade(facade);
		c.setTableMan(table);
		table.addSubscriber(fc);
		table.setAdapter(dA);
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
		title.setFont(new Font("Sego UI", Font.BOLD, 26));
		title.setHorizontalAlignment(SwingConstants.CENTER);

		introLabel.setText("Please enter your start/end months/years and regions you would like to get NHPI data for");
		introLabel.setFont(introLabel.getFont().deriveFont(14f));
		introLabel.setHorizontalAlignment(SwingConstants.CENTER);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		datesScrollPane.setBorder(new TitledBorder("Time-Series"));
		controlPanel1.setLayout(new BoxLayout(controlPanel1, BoxLayout.Y_AXIS));
		pair1.setLayout(new FlowLayout());
		monthLabel1.setText("Start Month 1:");
		monthLabel1.setPreferredSize(new Dimension(80, 25));
		pair1.add(monthLabel1);
		monthField1.setPreferredSize(new Dimension(90, 25));
		pair1.add(monthField1);
		controlPanel1.add(pair1);
		pair2.setLayout(new FlowLayout());
		monthLabel2.setText("End Month 1:");
		monthLabel2.setPreferredSize(new Dimension(80, 25));
		pair2.add(monthLabel2);
		monthField2.setPreferredSize(new Dimension(90, 25));
		pair2.add(monthField2);
		controlPanel1.add(pair2);
		pair3.setLayout(new FlowLayout());
		yearLabel1.setText("Start Year 1:");
		yearLabel1.setPreferredSize(new Dimension(80, 25));
		pair3.add(yearLabel1);
		yearField1.setPreferredSize(new Dimension(90, 25));
		pair3.add(yearField1);
		controlPanel1.add(pair3);
		pair4.setLayout(new FlowLayout());
		yearLabel2.setText("End Year 1:");
		yearLabel2.setPreferredSize(new Dimension(80, 25));
		pair4.add(yearLabel2);
		yearField2.setPreferredSize(new Dimension(90, 25));
		pair4.add(yearField2);
		controlPanel1.add(pair4);
		datesScrollPane.setViewportView(controlPanel1);
		mainPanel.add(datesScrollPane);

		regionsScrollPane.setBorder(new TitledBorder("Regions"));
		controlPanel2.setLayout(new BoxLayout(controlPanel2, BoxLayout.Y_AXIS));
		pair5.setLayout(new FlowLayout());
		regionLabel1.setText("Region 1:");
		regionLabel1.setPreferredSize(new Dimension(60, 25));
		pair5.add(regionLabel1);
		regionField1.setPreferredSize(new Dimension(200, 25));
		pair5.add(regionField1);
		controlPanel2.add(pair5);
		pair6.setLayout(new FlowLayout());
		regionLabel2.setText("Region 2:");
		regionLabel2.setPreferredSize(new Dimension(60, 25));
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

		seriesArray[0][0] = monthField1;
		seriesArray[0] = Arrays.copyOf(seriesArray[0], seriesArray[0].length + 1);
		seriesArray[0][seriesArray[0].length - 1] = monthField2;
		seriesArray[0] = seriesArray[0] = Arrays.copyOf(seriesArray[0], seriesArray[0].length + 1);
		seriesArray[0][seriesArray[0].length - 1] = yearField1;
		seriesArray[0] = seriesArray[0] = Arrays.copyOf(seriesArray[0], seriesArray[0].length + 1);
		seriesArray[0][seriesArray[0].length - 1] = yearField2;

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
												.addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)
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
		Boolean noRegionErrors = false;
		Boolean noSeriesErrors = false;
		JDialog prompt = new JDialog();
		JButton okButton = new JButton();

		okButton.setText("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(true);
				prompt.dispose();
			}
		});

		if (e.getSource() == submitButton) {
			provinceFlag = false;
			cityFlag = false;

			// Get the user inputs and store them in variables, & show the next window
			for (int i = 0; i < regionsArray.length; i++) {
				String region = regionsArray[i].getText();

				if (region.equals(null) || region.equals("") || region.equals(" ")) {
					noRegionErrors = false;

					showErrorPrompt(prompt, "Do not leave any field(s) blank and try again", okButton);
				}

				else {
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
							noRegionErrors = false;

							showErrorPrompt(prompt, "Enter only provinces or only cities and try again", okButton);
						}

						else if (cityFlag == false){
							noRegionErrors = true;

							con.addRegion(region);
						}
					}

					else if (cityFlag == true) {
						if (provinceFlag == true) {
							noRegionErrors = false;

							showErrorPrompt(prompt, "Enter only provinces or only cities and try again", okButton);
						}

						else if (provinceFlag == false) {
							noRegionErrors = true;

							con.addRegion(region);
						}
					}
				}
			}

			if (noRegionErrors == true) {
				String startMonth = seriesArray[0][0].getText();
				String endMonth = seriesArray[0][1].getText();
				String startYear = seriesArray[0][2].getText();
				String endYear = seriesArray[0][3].getText();

				if (startMonth.equals("") || endMonth.equals("") || startYear.equals("") || endYear.equals("")) {
					noSeriesErrors = false;

					showErrorPrompt(prompt, "Do not leave any field(s) blank and try again", okButton);
				}

				else {
					noSeriesErrors = true;

					con.addTimeSeries(startYear + "-" + startMonth, endYear + "-" + endMonth);

					if (seriesArray.length == 2) {
						startMonth = seriesArray[1][0].getText();
						endMonth = seriesArray[1][1].getText();
						startYear = seriesArray[1][2].getText();
						endYear = seriesArray[1][3].getText();

						if (startMonth.equals("") || endMonth.equals("") || startYear.equals("") || endYear.equals("")) {
							noSeriesErrors = false;

							showErrorPrompt(prompt, "Do not leave any field(s) blank and try again", okButton);
						}

						else {
							con.addTimeSeries(startYear + "-" + startMonth, endYear + "-" + endMonth);
							System.out.println("Time Series Added: " + "{" + startYear + "-" + startMonth + ", " + endYear + "-" + endMonth + "}");
						}
					}
				}
			}

			if (noSeriesErrors == true) {
				frame1.setVisible(false);

				JFrame frame2 = new JFrame();
				JLabel selectLabel = new JLabel();
				JButton mlButton = new JButton();
				JButton tButton = new JButton();
				JButton nhpiButton = new JButton();
				JPanel operPanel = new JPanel();
				JButton backButton = new JButton();

				frame2.setTitle("Project Zulu - NHPI Comparison & Forecasting");
				frame2.setResizable(false);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setLayout(new BoxLayout(frame2.getContentPane(), BoxLayout.Y_AXIS));
				frame2.setSize(400, 200);
				frame2.setLocationRelativeTo(null);

				selectLabel.setText("Select the operation that would you like to perform:");
				selectLabel.setFont(selectLabel.getFont().deriveFont(14f));
				selectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

				mlButton.setText("Generate Forecast Graph");
				mlButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				mlButton.setPreferredSize(new Dimension(250, 25));
				mlButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showGraphSelect(frame2, mlButton.getText());
					}
				});

				tButton.setText("Show Statistical T-Test");
				tButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				tButton.setPreferredSize(new Dimension(250, 25));
				tButton.addActionListener(this);
				tButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showGraphSelect(frame2, tButton.getText());
					}
				});

				nhpiButton.setText("Compare NHPIs");
				nhpiButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				nhpiButton.setPreferredSize(new Dimension(250, 25));
				nhpiButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						showGraphSelect(frame2, nhpiButton.getText());
					}
				});

				operPanel.setLayout(new FlowLayout());
				operPanel.setMaximumSize(new Dimension(250, 105));

				operPanel.add(mlButton);

				if (regionsArray.length == 2) {
					operPanel.add(tButton);
				}
				else {
					operPanel.remove(tButton);
				}

				operPanel.add(nhpiButton);

				backButton.setText("Go Back");
				backButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
				backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
				backButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame2.dispose();
						frame1.setVisible(true);
					}
				});

				frame2.add(selectLabel);
				frame2.add(operPanel);
				frame2.add(backButton);
				frame2.setVisible(true);
			}

			else if (noSeriesErrors == false) {
				frame1.setVisible(false);
			}
		}

		else if (e.getSource() == regionButton) {
			JLabel label = new JLabel("Region " + newRegionCounter + ":", JLabel.RIGHT);
			label.setPreferredSize(new Dimension(60, 25));

			JTextField field = new JTextField();
			field.setPreferredSize(new Dimension(200, 25));

			JPanel newPair = new JPanel();
			newPair.setLayout(new FlowLayout());

			controlPanel2.add(newPair);
			newPair.add(label);
			newPair.add(field);


			JButton removeRButton = new JButton();
			newPair.add(removeRButton);
			removeRButton.setText("Remove Region " + newRegionCounter);
			removeRButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			removeRButton.setPreferredSize(new Dimension(170, 24));
			removeRButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if ((newRegionCounter - 3) == 1) {
						firstRegionRun = true;
					}

					newRegionCounter -= 1;

					controlPanel2.remove(newPair);
					regionsArray = Arrays.copyOf(regionsArray, regionsArray.length - 1);

					frame1.revalidate();
					frame1.repaint();
				}
			});

			regionsArray = Arrays.copyOf(regionsArray, regionsArray.length + 1);
			regionsArray[regionsArray.length - 1] = field;

			if (firstRegionRun == true) {
				firstRegionRun = false;

				newPair.add(removeRButton);
			}

			newRegionCounter += 1;
		}

		else if (e.getSource() == seriesButton) {
			if (newSeriesCounter == 1) {
				JTextField[] tempArray1 = {monthField1, monthField2, yearField1, yearField2};
				seriesArray[0] = tempArray1;
			}

			newSeriesCounter += 1;

			JLabel spacer = new JLabel();
			spacer.setPreferredSize(new Dimension(1, 20));

			JLabel label1 = new JLabel("Start Month 2:", JLabel.RIGHT);
			label1.setPreferredSize(new Dimension(80, 25));
			JLabel label2 = new JLabel("End Month 2:", JLabel.RIGHT);
			label2.setPreferredSize(new Dimension(80, 25));
			JLabel label3 = new JLabel("Start Year 2:", JLabel.RIGHT);
			label3.setPreferredSize(new Dimension(80, 25));
			JLabel label4 = new JLabel("End Year 2:", JLabel.RIGHT);
			label4.setPreferredSize(new Dimension(80, 25));

			JTextField field1 = new JTextField();
			field1.setPreferredSize(new Dimension(90, 25));
			JTextField field2 = new JTextField();
			field2.setPreferredSize(new Dimension(90, 25));
			JTextField field3 = new JTextField();
			field3.setPreferredSize(new Dimension(90, 25));
			JTextField field4 = new JTextField();
			field4.setPreferredSize(new Dimension(90, 25));

			controlPanel1.add(spacer);

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

			JButton removeSButton = new JButton();
			removeSButton.setText("Remove Time-Series");
			removeSButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			removeSButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					controlPanel1.remove(spacer);
					controlPanel1.remove(newPair1);
					controlPanel1.remove(newPair2);
					controlPanel1.remove(newPair3);
					controlPanel1.remove(newPair4);

					seriesArray = Arrays.copyOf(seriesArray, seriesArray.length - 1);

					buttonPanel.remove(removeSButton);
					buttonPanel.add(seriesButton);

					frame1.revalidate();
					frame1.repaint();
				}
			});

			seriesArray = Arrays.copyOf(seriesArray, seriesArray.length + 1);
			JTextField[] tempArray2 = {field1, field2, field3, field4};
			seriesArray[seriesArray.length - 1] = tempArray2;

			buttonPanel.remove(seriesButton);
			buttonPanel.add(removeSButton);
		}

		frame1.revalidate();
		frame1.repaint();
	}

	public void showErrorPrompt(JDialog prompt, String labelText, JButton okButton) {
		// Update UI to show invalid input pop-up depending on error situation
		JLabel invalidLabel = new JLabel(labelText);

		prompt.setTitle("Invalid Input");
		prompt.setResizable(false);
		prompt.setPreferredSize(new Dimension(300, 100));
		prompt.setLayout(new FlowLayout());

		invalidLabel.setText(labelText);

		prompt.add(invalidLabel);
		prompt.add(okButton);
		prompt.pack();
		prompt.setLocationRelativeTo(null);
		prompt.setAlwaysOnTop(true);
		prompt.setVisible(true);
	}

	public void showGraphSelect(JFrame frame2, String operation) {
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
		frame3.setLayout(new BoxLayout(frame3.getContentPane(), BoxLayout.Y_AXIS));
		frame3.setSize(400, 145);
		frame3.setLocationRelativeTo(null);

		graphSelectLabel.setText("Which type of graph would you like to choose?");
		graphSelectLabel.setFont(graphSelectLabel.getFont().deriveFont(14f));
		graphSelectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		gPanel.setLayout(new FlowLayout());
		gPanel.setMaximumSize(new Dimension(400, 50));

		lineG.setText("Line Graph");
		lineG.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		barG.setText("Bar Graph");
		barG.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		gPanel.add(lineG);
		gPanel.add(barG);

		backButton.setText("Go Back");
		backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame3.dispose();
				con.resetData();
				frame1.setVisible(true);
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
				setFinalDisplay(frame3, operation, barG.getText());
			}
		});

		lineG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setFinalDisplay(frame3, operation, lineG.getText());
			}
		});
	}

	public void setFinalDisplay(JFrame prevFrame, String operation, String buttonText) {
		if (buttonText.equals("Bar Graph")) {
			fc.setCreatorBar();
		}

		else {
			fc.setCreatorLine();
		}

		JFrame frame4 = new JFrame();
		JLabel label = new JLabel();
		JButton backButton = new JButton();

		frame4.setResizable(false);
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame4.setLayout(new BorderLayout());

		label.setFont(label.getFont().deriveFont(14f));
		label.setHorizontalAlignment(SwingConstants.CENTER);

		backButton.setText("Go Back");
		backButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame4.dispose();
				con.resetData();
				frame1.setVisible(true);
			}
		});

		List<String> outputs = new ArrayList<String>();

		if (operation.equals("Generate Forecast Graph")) {
			frame4.setTitle("Graph Information & Forecasting");
			label.setPreferredSize(new Dimension(400, 150));
			con.setMLForecast();
		}

		else if (operation.equals("Show Statistical T-Test")) {
			frame4.setTitle("Graph Information & Statistical Test");
			label.setPreferredSize(new Dimension(400, 300));
			con.setStatTest();
		}

		else if (operation.equals("Compare NHPIs")) {
			frame4.setTitle("Graph Information & NHPI Comparison");
			label.setPreferredSize(new Dimension(400, 300));
			con.setCompareNHPI();
		}

		outputs = con.execute();

		Iterator<String> it = outputs.iterator();

		// Add iterator here and show NHPI info depending on operation
		if (operation.equals("Generate Forecast Graph")) {
			label.setText("Here are the generated existing and forecasted graphs for all regions.");
		}

		else {
			while (it.hasNext()) {
				label.setText("<html><center>" + label.getText() + it.next() + "</center></html>");
			}
		}

		prevFrame.setVisible(false);

		frame4.add(label, BorderLayout.CENTER);
		frame4.add(backButton, BorderLayout.SOUTH);
		frame4.pack();
		frame4.setLocationRelativeTo(null);
		frame4.setVisible(true);
	}

	private void setFC(FactoryCommunicator f) {
		this.fc = f;
	}

	private void setCon(Controller c) {
		this.con = c;
	}
}