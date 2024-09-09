import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static JFrame frame;
    private static JTextField name;
    private static JButton calculateButton;
    private static JLabel resultLabel;
    private static boolean running = false;

    public static void main(String[] args) {

        // make the ui and stuff
        frame = new JFrame("Name to Gender");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(2, 2));

        frame.add(new JLabel("Name", SwingConstants.CENTER));

        name = new JTextField("");
        name.setHorizontalAlignment(JTextField.CENTER);
        frame.add(name);

        /*
         * button that will calculate probability of the name
         * being whatever gender in our calculatebutton method
         */
        // bottom left panel
        calculateButton = new JButton("Calculate");
        frame.add(calculateButton);
        calculateButton.addActionListener(new CalculateButton());

        // bottom right panel will show our results
        resultLabel = new JLabel("Results", SwingConstants.CENTER);
        frame.add(resultLabel);

        frame.setVisible(true);

    }

    private static class CalculateButton implements ActionListener {
        // button that calculates probability of name gender when clicked
        // action event used to detect a click
        public void actionPerformed(ActionEvent e) {
            if (!running) {
                running = true;

                String addName = name.getText();
                try {
                    String apiUrl = "https://api.genderize.io/?name=" + addName; // replace with actual API URL
                    String jsonResponse = ApiRequest.getApiResponse(apiUrl);

                    String[] resultData = JsonParser.responseJson(jsonResponse);

                    resultLabel.setText("Name: " + resultData[0] + "\nGender:" + resultData[1] + "\nProbability: "
                            + resultData[2]);

                } catch (Exception a) {
                    a.printStackTrace();

                }

                running = false;
            }
        }
    }

}
