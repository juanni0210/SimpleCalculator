package calculator_pack;

import java.awt.Dimension;
import java.awt.EventQueue;


import javax.swing.*;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable.BinaryOp.Add;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



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

    //    private String[] btnString = { "\u2190", "C", "%", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+",
    //            "0", ".", "\u00B1", "=" };

    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 520;
    final int SPACEING = 1;
    final int MARGIN = 5;
    final int PAD_COL = 4;
    final int PAD_ROW = 5;
    final int PREFERED_SIZE = (WINDOW_WIDTH - 2 * MARGIN - (PAD_COL - 1) * SPACEING) / PAD_COL;
    private JFrame frame;
    private JTextField txtDisplay;
    double firstNum;
    double secondNum;
    String operations;
    double result;
    String answer;


    /*
     * layout
     * -------------------
     * <-| % | C | /
     * 7 | 8 | 9 | *
     * 4 | 5 | 6 | +
     * 1 | 2 | 3 | -
     * +-| 0 | . | =
     */
    private JButton btnC;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn0;
    private JButton btnDot;
    private JButton btnPM;
    private JButton btnEqual;
    private JButton btnPercent;
    private JButton btnPlus;
    private JButton btnMinus;
    private JButton btnMultiplication;
    private JButton btnDivision;
    private JButton btnBackSpace;
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
        frame.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
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
        btnBackSpace = createButton("\u2190", new ActionListener() {
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
        frame.getContentPane().add(btnBackSpace);

        btnC = createButton("C", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDisplay.setText(null);
            }
        });
        frame.getContentPane().add(btnC);

        btnPercent = createButton("%", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "%";
            }
        });
        frame.getContentPane().add(btnPercent);

        btnDivision = createButton("/", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "/";
            }
        });
        frame.getContentPane().add(btnDivision);

        //-----------------Row 2---------------------
        DigitBtnHandler digitBtnHandler = new DigitBtnHandler(txtDisplay);
        btn7 = createButton("7", digitBtnHandler);
        frame.getContentPane().add(btn7);

        btn8 = createButton("8", digitBtnHandler);
        frame.getContentPane().add(btn8);

        btn9 = createButton("9", digitBtnHandler);
        frame.getContentPane().add(btn9);

        btnMultiplication = createButton("*", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "*";
            }
        });
        frame.getContentPane().add(btnMultiplication);

        //-----------------Row 3---------------------
        btn4 = createButton("4", digitBtnHandler);
        frame.getContentPane().add(btn4);

        btn5 = createButton("5", digitBtnHandler);
        frame.getContentPane().add(btn5);

        btn6 = createButton("6", digitBtnHandler);
        frame.getContentPane().add(btn6);

        btnMinus = createButton("-", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "-";
            }
        });
        frame.getContentPane().add(btnMinus);

        //-----------------Row 4---------------------
        btn1 = createButton("1", digitBtnHandler);
        frame.getContentPane().add(btn1);

        btn2 = createButton("2", digitBtnHandler);
        frame.getContentPane().add(btn2);

        btn3 = createButton("3", digitBtnHandler);
        frame.getContentPane().add(btn3);

        btnPlus = createButton("+", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getClass());
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "+";
            }
        });
        frame.getContentPane().add(btnPlus);

        //-----------------Row 5---------------------
        btn0 = createButton("0", digitBtnHandler);
        frame.getContentPane().add(btn0);

        btnDot = createButton(".", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });
        frame.getContentPane().add(btnDot);

        btnPM = createButton("\u00B1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double ops = Double.parseDouble(String.valueOf(txtDisplay.getText()));
                ops = ops * (-1);
                txtDisplay.setText(String.valueOf(ops));
            }
        });
        frame.getContentPane().add(btnPM);

        btnEqual = createButton("=", new ActionListener() {
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
        frame.getContentPane().add(btnEqual);
    }

    private JButton createButton(String btnString, ActionListener actionListener) {
        JButton button = new JButton(btnString);
        button.addActionListener(actionListener);
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(PREFERED_SIZE, PREFERED_SIZE));
        return button;
    }


}
