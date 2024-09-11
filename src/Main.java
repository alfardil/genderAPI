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
        frame.getContentPane().setBackground(new Color(74, 181, 136));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(2, 2));

        frame.add(new JLabel("Name", SwingConstants.CENTER));

        name = new JTextField("");
        name.setBackground(new Color(74, 181, 136));
        name.setHorizontalAlignment(JTextField.CENTER);
        frame.add(name);

        /*
         * button that will calculate probability of the name
         * being whatever gender in our calculatebutton method
         */
        // bottom left panel
        calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(52, 235, 158));
        calculateButton.setBorderPainted(false);
        calculateButton.setOpaque(true);
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

                    String name = resultData[0].toUpperCase();
                    String gender = resultData[1].toUpperCase();
                    String stringProbability = resultData[2];
                    float probability = Float.parseFloat(stringProbability);

                    resultLabel.setText(
                            "<html><style> p{text-align: center;}</style><p>Results<p>" + "<br>Name: " + name
                                    + "<br>Gender: " + gender
                                    + "<br>Probability: "
                                    + (probability * 100) + "</html>");

                } catch (Exception a) {
                    a.printStackTrace();

                }

                running = false;
            }
        }
    }

}
