import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame {
    private JTextField display;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
        setSize(500, 600); // Set initial size
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void initComponents() {
        display = new JTextField();
        display.setEditable(false); // Make the display non-editable
        display.setHorizontalAlignment(JTextField.RIGHT); // Align text to the right
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the display
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));

        // Define buttons
        String[] buttonLabels = {
                "(", ")", "mc", "m+", "m-",
                "mr", "C", "+/-", "%", "/",
                "2nd", "x^2", "x^3", "x^y",
                "e^x", "10^x", "7", "8", "9", "*",
                "1/x", "√", "∛", "y√x", "ln",
                "log", "4", "5", "6", "-",
                "x!", "sin", "cos", "tan",
                "e", "EE", "1", "2", "3", "+",
                "Rad", "sinh", "cosh", "tanh",
                "π", "Rand", "←","0" , "."
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            setButtonStyle(button, label);
            buttonPanel.add(button);
        }

        // Add the equal sign button (spanning two columns)
        JButton equalButton = new JButton("=");
        equalButton.addActionListener(new ButtonClickListener());
        setButtonStyle(equalButton, "=");
        buttonPanel.add(equalButton);
        equalButton.setPreferredSize(new Dimension(0, 0));

        add(buttonPanel, BorderLayout.CENTER);
    }

    // Action listener for all buttons
    class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "=":
                    evaluateExpression();
                    break;
                case ".":
                    display.setText(display.getText()+".");
                    break;
                case "C":
                    display.setText("");
                    break;
                case "+/-":
                    negateNumber();
                    break;
                case "√":
                    
                    double root = Math.sqrt(Double.parseDouble(display.getText()));
                    display.setText(Double.toString(root));
                    break;
                 case "∛":
                    double cubeRoot = Math.cbrt(Double.parseDouble(display.getText()));
                    display.setText(Double.toString(cubeRoot));
                        break;
                case "x^2":
                double squareValue = Double.parseDouble(display.getText());
                    display.setText(Double.toString(squareValue*squareValue)); 
                    break;

                case "x^3":
                double cubicValue = Double.parseDouble(display.getText());
                display.setText(Double.toString(cubicValue*cubicValue*cubicValue));
                    break;

                case "x^y":
                double x = Double.parseDouble(display.getText());System.out.println(x);

                double y = Double.parseDouble(display.getText());System.out.println(y);
                double powerResult = Math.pow(x,y);
                display.setText(Double.toString(powerResult));
                    break;

                case "e^x":
                double exponent = Double.parseDouble(display.getText()) ;
                display.setText(Double.toString(Math.pow(Math.E,exponent)));
                    break;

                case "10^x":
                double tenthPow = Double.parseDouble(display.getText()) ;
                display.setText(Double.toString(Math.pow(10,tenthPow)));
                    break;
                case "1/x":
                double inverse = 1/(Double.parseDouble(display.getText())) ;
                display.setText(Double.toString(inverse));
                    break;
                case "y√x":
                    display.setText(display.getText() + "√");
                    double number = Double.parseDouble(display.getText());
                    double ythroot = Math.pow(number ,(1.0/10));
                display.setText(Double.toString(ythroot));
                    break;

                case "ln":
                double lnValue =Math.log(Double.parseDouble(display.getText()));
                display.setText(Double.toString(lnValue));
                    break;

                case "log":
                double logValue =Math.log10(Double.parseDouble(display.getText()));
                display.setText(Double.toString(logValue));
                    break;
                case "x!":
                    calculateFactorial();
                    break;
                case "sin":
                    applyTrigOperation(Math::sin);
                    break;
                case "cos":
                    applyTrigOperation(Math::cos);
                    break;
                case "tan":
                    applyTrigOperation(Math::tan);
                    break;
                case "e":
                    display.setText(display.getText() + "e");
                    break;
                case "EE":
                    display.setText(display.getText() + "E");
                    break;
                case "Rad":
                    double rand = Math.toRadians(Double.parseDouble(display.getText()));
                    display.setText(Double.toString(rand));
                    break;
                case "sinh":
                double sinInverse = Math.sinh(Double.parseDouble(display.getText()));
                display.setText(Double.toString(sinInverse));
                    break;
                case "cosh":
                double cosInverse = Math.cosh(Double.parseDouble(display.getText()));
                display.setText(Double.toString(cosInverse));
                    break;
                case "tanh":
                    double tanInverse = Math.tanh(Double.parseDouble(display.getText()));
                    display.setText(Double.toString(tanInverse));
                    break;
                case "π":
                double pi = Math.PI;
                    display.setText(display.getText() + "\u03C0");
                    display.setText(Double.toString(pi));
                    break;
                case "Rand":
                    display.setText(display.getText() + "Rand");
                    break;
                case "←":
                    eraseLastCharacter();
                    break;
                default:
                    display.setText(display.getText() + command);
                    break;
            }
        }
    }

    // Method to evaluate arithmetic expressions
    private void evaluateExpression() {
        String expression = display.getText();
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Object result = engine.eval(expression);
            display.setText(result.toString());
        } catch (ScriptException e) {
            display.setText("Error");
        }
    }

    // Method to apply unary operations
   /*  private void applyUnaryOperation(double i) {
        try {
            double operand = Double.parseDouble(display.getText());
            double result = i.apply(operand);
            display.setText(Double.toString(result));
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }*/

    // Method to calculate factorial
    private void calculateFactorial() {
        try {
            int n = Integer.parseInt(display.getText());
            if (n < 0) {
                display.setText("Error");
                return;
            }
            long factorial = 1;
            for (int i = 2; i <= n; i++) {
                factorial *= i;
            }
            display.setText(Long.toString(factorial));
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }

    // Method to negate a number
    private void negateNumber() {
        try {
            double value = Double.parseDouble(display.getText());
            value = -value;
            display.setText(Double.toString(value));
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }

    // Method to erase the last character in the display
    private void eraseLastCharacter() {
        String currentText = display.getText();
        if (!currentText.isEmpty()) {
            display.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    // Method to apply trigonometric operations
    private void applyTrigOperation(TrigOperation operation) {
        try {
            double operand = Double.parseDouble(display.getText());
            double result = operation.apply(Math.toRadians(operand)); // Ensure input is in radians
            display.setText(Double.toString(result));
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }

    // Functional interface for unary operations
    @FunctionalInterface
    interface UnaryOperation {
        double apply(double x);
    }

    // Functional interface for trigonometric operations
    @FunctionalInterface
    interface TrigOperation {
        double apply(double x);
    }

    // Method to set button style
    private void setButtonStyle(JButton button, String label) {
        if (label.matches("[0-9.]")) {
            button.setBackground(Color.GRAY);
        } else if (label.equals("(") || label.equals(")")) {
            button.setBackground(new Color(64,64,64));
        } else if (label.matches("[*/\\-+=]")) {
            button.setBackground(new Color(240,160,0));
        } else {
            button.setBackground(Color.DARK_GRAY);
        }
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        // Set fixed size for buttons
        button.setPreferredSize(new Dimension(50, 50));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScientificCalculator::new);
    }
}