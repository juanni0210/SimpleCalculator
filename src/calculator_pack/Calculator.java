package calculator_pack;

import java.awt.EventQueue;


import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.sun.xml.internal.ws.client.SenderException;


public class Calculator {


    private final class DigitBtnHandler implements ActionListener {

        private JTextField textField;

        public DigitBtnHandler(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(getClass());
            // Just for safety, check first
            if (e.getSource() instanceof JButton) {
                JButton b = (JButton) e.getSource();
                textField.setText(textField.getText() + b.getText());
            }
        }
    }

    private JFrame frame;
    private JTextField txtDisplay;
    double firstNum;
    double secondNum;
    String operations;
    double result;
    String answer;

    private JButton btn8;
    private JButton btn9;
    private JButton btnMinus;
    private JButton btnBackSpace;
    private JButton btnC;
    private JButton btnPercent;
    private JButton btnPlus;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btnMultiplication;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btnDivision;
    private JButton btn0;
    private JButton btnDot;
    private JButton btnPM;
    private JButton btnEqual;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Calculator window = new Calculator();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Calculator() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 342, 523);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        txtDisplay = new JTextField();
        txtDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtDisplay.setBounds(12, 13, 300, 53);
        txtDisplay.setBorder(BorderFactory.createEmptyBorder());
        frame.getContentPane().add(txtDisplay);
        txtDisplay.setColumns(10);

        System.out.println(getClass());

        //-----------------Row 1---------------------
        btnBackSpace = new JButton("\u2190"); //uF0E7
        btnBackSpace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getClass());
                String backSpace = null;
                if (txtDisplay.getText().length() > 0) {
                    StringBuilder strB = new StringBuilder(txtDisplay.getText());
                    strB.deleteCharAt(txtDisplay.getText().length() - 1);
                    backSpace = strB.toString();
                    txtDisplay.setText(backSpace);
                }
            }
        });
        btnBackSpace.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBackSpace.setBounds(12, 82, 70, 70);
        frame.getContentPane().add(btnBackSpace);

        btnC = new JButton("C");
        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDisplay.setText(null);
            }
        });
        btnC.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnC.setBounds(89, 82, 70, 70);
        frame.getContentPane().add(btnC);

        btnPercent = new JButton("%");
        btnPercent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "%";
            }
        });
        btnPercent.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnPercent.setBounds(166, 82, 70, 70);
        frame.getContentPane().add(btnPercent);

        btnDivision = new JButton("/");
        btnDivision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "/";
            }
        });
        btnDivision.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDivision.setBounds(243, 82, 70, 70);
        frame.getContentPane().add(btnDivision);

        //-----------------Row 2---------------------
        JButton btn7 = new JButton("7");
        DigitBtnHandler digitBtnHandler = new DigitBtnHandler(txtDisplay);
        btn7.addActionListener(digitBtnHandler);
        btn7.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn7.setBounds(12, 159, 70, 70);
        frame.getContentPane().add(btn7);

        btn8 = new JButton("8");
        btn8.addActionListener(digitBtnHandler);
        btn8.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn8.setBounds(89, 159, 70, 70);
        frame.getContentPane().add(btn8);

        btn9 = new JButton("9");
        btn9.addActionListener(digitBtnHandler);
        btn9.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn9.setBounds(166, 159, 70, 70);
        frame.getContentPane().add(btn9);

        btnMultiplication = new JButton("*");
        btnMultiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "*";
            }
        });

        btnMultiplication.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnMultiplication.setBounds(243, 159, 70, 70);
        frame.getContentPane().add(btnMultiplication);

        //-----------------Row 3---------------------
        btn4 = new JButton("4");
        btn4.addActionListener(digitBtnHandler);
        btn4.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn4.setBounds(12, 236, 70, 70);
        frame.getContentPane().add(btn4);

        btn5 = new JButton("5");
        btn5.addActionListener(digitBtnHandler);
        btn5.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn5.setBounds(89, 236, 70, 70);
        frame.getContentPane().add(btn5);

        btn6 = new JButton("6");
        btn6.addActionListener(digitBtnHandler);
        btn6.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn6.setBounds(166, 236, 70, 70);
        frame.getContentPane().add(btn6);

        btnMinus = new JButton("-");
        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "-";
            }
        });
        btnMinus.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnMinus.setBounds(243, 236, 70, 70);
        frame.getContentPane().add(btnMinus);

        //-----------------Row 4---------------------
        btn1 = new JButton("1");
        btn1.addActionListener(digitBtnHandler);
        btn1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn1.setBounds(12, 313, 70, 70);
        frame.getContentPane().add(btn1);

        btn2 = new JButton("2");
        btn2.addActionListener(digitBtnHandler);
        btn2.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn2.setBounds(89, 313, 70, 70);
        frame.getContentPane().add(btn2);

        btn3 = new JButton("3");
        btn3.addActionListener(digitBtnHandler);
        btn3.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn3.setBounds(166, 313, 70, 70);
        frame.getContentPane().add(btn3);

        btnPlus = new JButton("+");
        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getClass());
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "+";
            }
        });
        btnPlus.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnPlus.setBounds(243, 313, 70, 70);
        frame.getContentPane().add(btnPlus);

        //-----------------Row 5---------------------
        btn0 = new JButton("0");
        btn0.addActionListener(digitBtnHandler);
        btn0.setFont(new Font("Tahoma", Font.BOLD, 20));
        btn0.setBounds(12, 390, 70, 70);
        frame.getContentPane().add(btn0);

        btnDot = new JButton(".");
        btnDot.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDot.setBounds(89, 390, 70, 70);
        frame.getContentPane().add(btnDot);

        btnPM = new JButton("\u00B1");
        btnPM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double ops = Double.parseDouble(String.valueOf(txtDisplay.getText()));
                ops = ops * (-1);
                txtDisplay.setText(String.valueOf(ops));
            }
        });
        btnPM.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnPM.setBounds(166, 390, 70, 70);
        frame.getContentPane().add(btnPM);

        btnEqual = new JButton("=");
        btnEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer;
                secondNum = Double.parseDouble(txtDisplay.getText());
                if (operations == "+") {
                    result = firstNum + secondNum;
                    answer = String.format("%.2f", result);
                    txtDisplay.setText(answer);
                } else if (operations == "-") {
                    result = firstNum - secondNum;
                    answer = String.format("%.2f", result);
                    txtDisplay.setText(answer);
                } else if (operations == "*") {
                    result = firstNum * secondNum;
                    answer = String.format("%.2f", result);
                    txtDisplay.setText(answer);
                } else if (operations == "/") {
                    result = firstNum / secondNum;
                    answer = String.format("%.2f", result);
                    txtDisplay.setText(answer);
                } else if (operations == "%") {
                    result = firstNum % secondNum;
                    answer = String.format("%.2f", result);
                    txtDisplay.setText(answer);
                }
            }
        });
        btnEqual.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEqual.setBounds(243, 390, 70, 70);
        frame.getContentPane().add(btnEqual);
    }
}
