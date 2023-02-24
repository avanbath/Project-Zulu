import javax.swing.*;

public class UserInterface {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();


        JButton mlForecast = new JButton("ML Forecast");
        JButton statTest = new JButton("Statistical Test");
        JButton compareNHPI = new Jbutton("Compare NHPI");

        mlForecast.addActionListener();
        statTest.addActionListener();
        compareNHPI.addActionListener();
        
        frame.add(panel);

        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}