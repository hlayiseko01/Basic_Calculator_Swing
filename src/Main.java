import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main  {
    private static JTextField textField;
    private static double currentResult = 0;
    private static String currentOperator = "";
    private static boolean startNewNumber = true;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);


        // Create the main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();

        // Create and configure the text field
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT); // Optional: align text to the right

        // Add the text field to the main panel with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2; // Adjust this value to change the height proportion of the text field
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5); // Optional: add some padding around the text field
        mainPanel.add(textField, gbc);

        // Create the buttons
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton divide = new JButton("/");
        JButton multiply = new JButton("*");
        JButton equal = new JButton("=");
        JButton decimal = new JButton(".");
        JButton clear = new JButton("C");

        // Create a panel with GridLayout for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBackground(Color.black);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        buttonPanel.add(multiply);

        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(divide);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(minus);

        buttonPanel.add(equal);
        buttonPanel.add(button0);
        buttonPanel.add(decimal);
        buttonPanel.add(plus);

        buttonPanel.add(clear);

        // Add the button panel to the main panel with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8; // Adjust this value to change the height proportion of the button panel
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonPanel, gbc);

        // Add the main panel to the frame
        frame.add(mainPanel);

        // Create the ActionListener using a lambda expression
        ActionListener listener = e -> {
            String command = e.getActionCommand();

            // Handle numeric buttons and decimal point
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                if (startNewNumber) {
                    textField.setText(command);
                    startNewNumber = false;
                } else {
                    textField.setText(textField.getText() + command);
                }
            } else {
                // Handle operator buttons
                if (command.equals("=")) {
                    calculate(Double.parseDouble(textField.getText()));
                    textField.setText("" + currentResult);
                    currentOperator = "";
                    startNewNumber = true;
                }else if (command.equals("C")) {
                    // Handle clear button
                    textField.setText("");
                    currentResult = 0;
                    currentOperator = "";
                    startNewNumber = true;
                }  else {
                    if (!currentOperator.equals("")) {
                        calculate(Double.parseDouble(textField.getText()));
                        textField.setText("" + currentResult);
                    } else {
                        currentResult = Double.parseDouble(textField.getText());
                    }
                    currentOperator = command;
                    startNewNumber = true;
                }
            }
        };

        JButton[] buttons = {button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
                plus, minus, divide, multiply, equal, decimal};
        for (JButton button : buttons) {
            button.setFocusable(false);
            button.addActionListener(listener);
        }

        // Make the frame visible
        frame.setVisible(true);
    }
    private static void calculate(double number) {
        switch (currentOperator) {
            case "+":
                currentResult += number;
                break;
            case "-":
                currentResult -= number;
                break;
            case "X":
                currentResult *= number;
                break;
            case "/":
                currentResult /= number;
                break;
        }

}}