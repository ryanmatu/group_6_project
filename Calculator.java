import javax.swing.*;
import java.lang.Math;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{

    JFrame frame;
    JTextField textField;
    JButton [] functionalitiesButtons = new JButton[13];
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subButton,mulButton,divButton,decButton,equButton,delButton,clrButton;
    JButton sinButton,cosButton,tanButton,powerButton,factButton,piButton,logButton,lnButton,eButton,modButton,inverseButton,sqrtButton,cbrtButton;
    JPanel panel;
    Font myFont = new Font("arial" , Font.BOLD, 30);
    
    double num1=0 ,num2=0,result=0;
    char operator;

    Calculator(){
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 600);
        frame.setLayout(null);


        textField = new JTextField();
        textField.setBounds(50, 25,500,50);
        textField.setFont(myFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");
        decButton = new JButton(".");
        equButton = new JButton("=");
        logButton = new JButton("log");
        lnButton = new JButton("ln");
        piButton = new JButton("π");
        eButton = new JButton("e");
        sinButton =new JButton("sin");
        cosButton = new JButton("cos");
        tanButton = new JButton("tan");
        modButton = new JButton("%");
        inverseButton = new JButton("(-)");
        sqrtButton = new JButton("√");
        cbrtButton = new JButton("∛");
        powerButton = new JButton("x^?");
        factButton = new JButton("!");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = equButton;
        functionButtons[4] = decButton;
        functionButtons[5] = delButton;
        functionButtons[6] = clrButton;
        functionButtons[7] = divButton;

        functionalitiesButtons[0] = powerButton;
        functionalitiesButtons[1] = sinButton;
        functionalitiesButtons[2] = cosButton;
        functionalitiesButtons[3] = tanButton;
        functionalitiesButtons[4] = lnButton;
        functionalitiesButtons[5] = factButton;
        functionalitiesButtons[6] = logButton;
        functionalitiesButtons[7] = piButton;
        functionalitiesButtons[8] = sqrtButton;
        functionalitiesButtons[9] = eButton;
        functionalitiesButtons[10] = cbrtButton;
        functionalitiesButtons[11] = inverseButton;
        functionalitiesButtons[12] = modButton;
       

        for(int i =0;i<8;i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(new Color(255, 159, 9));
            functionButtons[i].setForeground(Color.WHITE);
        }
        for (int i=0;i<10;i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(68, 69, 66));
            numberButtons[i].setForeground(Color.WHITE);
        }

        for(int i =0;i<13;i++){
            functionalitiesButtons[i].addActionListener(this);
            functionalitiesButtons[i].setFont(myFont);
            functionalitiesButtons[i].setFocusable(false);
            functionalitiesButtons[i].setBackground(new Color(68, 69, 66));
            functionalitiesButtons[i].setForeground(Color.WHITE);
        }
        delButton.setBounds(50,430,145,50);
        clrButton.setBounds(205,430,145,50);
        
        panel = new JPanel();
        panel.setBounds(50,100,600,400);
        panel.setLayout(new GridLayout(5,5,10,10));
        panel.setBackground(Color.black);
        
        panel.add(functionButtons[8]);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(functionButtons[9]);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(functionButtons[10]);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);


    }
public static void main(String[] args){
    new Calculator();
}
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }

            textField.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textField.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
        if(e.getSource() == logButton){
            double operand = Double.parseDouble(textField.getText());
            result = Math.log10(operand);
            textField.setText(String.valueOf(result));
        }
        if(e.getSource() == lnButton){
            double operand = Double.parseDouble(textField.getText());
            result = Math.log(operand);
            textField.setText(String.valueOf(result));
        }
        if(e.getSource() == piButton){
            textField.setText(String.valueOf(Math.PI));
        }
        if(e.getSource() == eButton){
            textField.setText(String.valueOf(Math.E));
        }
 
    }

}

