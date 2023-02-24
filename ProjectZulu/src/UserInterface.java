import javax.swing.*;

public class UserInterface {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();


        JButton mlForecast = new JButton("ML Forecast");
        JButton statTest = new JButton("Statistical Test");
        JButton compareNHPI = new JButton("Compare NHPI");

        //mlForecast.addActionListener();
        //statTest.addActionListener();
        //compareNHPI.addActionListener();
        
        frame.add(panel);

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

/* THE FOLLOWING SECTION IS TO BE EDITED AND UPDATED ACCORDINGLY. PLEASE REFER TO THIS PORTION OF CODE TO IMPLEMENT THE UI:

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MyPanel extends JPanel {
    private JButton jcomp1;
    private JButton jcomp2;
    private JTextField jcomp3;
    private JTextField jcomp4;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JTextField jcomp7;
    private JTextField jcomp8;
    private JLabel jcomp9;
    private JLabel jcomp10;
    private JLabel jcomp11;
    private JLabel jcomp12;
    private JLabel jcomp13;

    public MyPanel() {
        //construct components
        jcomp1 = new JButton ("Proceed to Visualizations");
        jcomp2 = new JButton ("Add time-series");
        jcomp3 = new JTextField (5);
        jcomp4 = new JTextField (5);
        jcomp5 = new JLabel ("Province/Town 1:");
        jcomp6 = new JLabel ("Proivnce/Town 2:");
        jcomp7 = new JTextField (5);
        jcomp8 = new JTextField (5);
        jcomp9 = new JLabel ("Date 1:");
        jcomp10 = new JLabel ("Date 2:");
        jcomp11 = new JLabel ("National Housing Price Index Comparison");
        jcomp12 = new JLabel (""Where should I live in Canada?"");
        jcomp13 = new JLabel ("Please enter the two Canadian provinces/towns and two dates to compare their NHPIs:");

        //adjust size and set layout
        setPreferredSize (new Dimension (670, 430));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);
        add (jcomp11);
        add (jcomp12);
        add (jcomp13);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (415, 320, 180, 35);
        jcomp2.setBounds (270, 335, 140, 20);
        jcomp3.setBounds (265, 220, 165, 25);
        jcomp4.setBounds (265, 260, 165, 25);
        jcomp5.setBounds (155, 220, 100, 25);
        jcomp6.setBounds (155, 260, 100, 25);
        jcomp7.setBounds (495, 220, 100, 25);
        jcomp8.setBounds (495, 260, 100, 25);
        jcomp9.setBounds (450, 220, 45, 25);
        jcomp10.setBounds (450, 260, 45, 25);
        jcomp11.setBounds (20, 15, 255, 25);
        jcomp12.setBounds (20, 40, 190, 25);
        jcomp13.setBounds (105, 175, 495, 25);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new MyPanel());
        frame.pack();
        frame.setVisible (true);
    }
}

 */