package calculator_pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;


import javax.swing.*;


import java.awt.Font;
import java.awt.GridLayout;
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
    final Color COLOR_BKG = new Color(85, 85, 85);
    final Color COLOR_BTN_DEFAULT = new Color(51, 51, 51);
    final Color COLOR_BTN_DIGIT = new Color(17, 17, 17);
    final Color COLOR_BTN_EQUAL = new Color(51, 124, 129);

    private JFrame frame;
    private JTextField txtDisplay;
    private JLabel displayLabel; // Show current calculation
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
        frame.setResizable(true);
        frame.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));
        //mainPanel.setBackground(COLOR_BKG);


        displayLabel = new JLabel();
        //displayLabel.setForeground(Color.WHITE);
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        displayLabel.setBorder(BorderFactory.createEmptyBorder());
        displayLabel.setPreferredSize(new Dimension(WINDOW_WIDTH - 2 * MARGIN, 35));

        txtDisplay = new JTextField();
        //txtDisplay.setBackground(COLOR_BKG);
        //txtDisplay.setForeground(Color.WHITE);
        txtDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtDisplay.setBorder(BorderFactory.createEmptyBorder());
        txtDisplay.setColumns(10);
        txtDisplay.setPreferredSize(new Dimension(WINDOW_WIDTH - 2 * MARGIN, 70));

        //System.out.println(getClass());





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


        btnC = createButton("C", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDisplay.setText(null);
            }
        });


        btnPercent = createButton("%", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "%";
            }
        });


        btnDivision = createButton("/", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "/";
            }
        });


        //-----------------Row 2---------------------
        DigitBtnHandler digitBtnHandler = new DigitBtnHandler(txtDisplay);
        btn7 = createButton("7", digitBtnHandler);


        btn8 = createButton("8", digitBtnHandler);


        btn9 = createButton("9", digitBtnHandler);


        btnMultiplication = createButton("*", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "*";
            }
        });


        //-----------------Row 3---------------------
        btn4 = createButton("4", digitBtnHandler);


        btn5 = createButton("5", digitBtnHandler);


        btn6 = createButton("6", digitBtnHandler);


        btnMinus = createButton("-", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "-";
            }
        });


        //-----------------Row 4---------------------
        btn1 = createButton("1", digitBtnHandler);

        btn2 = createButton("2", digitBtnHandler);

        btn3 = createButton("3", digitBtnHandler);

        btnPlus = createButton("+", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getClass());
                firstNum = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText("");
                operations = "+";
            }
        });


        //-----------------Row 5---------------------
        btn0 = createButton("0", digitBtnHandler);

        btnDot = createButton(".", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });

        btnPM = createButton("\u00B1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double ops = Double.parseDouble(String.valueOf(txtDisplay.getText()));
                ops = ops * (-1);
                txtDisplay.setText(String.valueOf(ops));
            }
        });

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

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(displayLabel, BorderLayout.NORTH);
        topPanel.add(txtDisplay, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        GridLayout gridLayout = new GridLayout(PAD_ROW, PAD_COL, SPACEING, SPACEING);
        JPanel bottomPanel = new JPanel(gridLayout);
        /*
         * layout
         * -------------------
         * <-| % | C | /
         * 7 | 8 | 9 | *
         * 4 | 5 | 6 | +
         * 1 | 2 | 3 | -
         * +-| 0 | . | =
         */
        bottomPanel.add(btnBackSpace);
        bottomPanel.add(btnPercent);
        bottomPanel.add(btnC);
        bottomPanel.add(btnDivision);
        bottomPanel.add(btn7);
        bottomPanel.add(btn8);
        bottomPanel.add(btn9);
        bottomPanel.add(btnMultiplication);
        bottomPanel.add(btn4);
        bottomPanel.add(btn5);
        bottomPanel.add(btn6);
        bottomPanel.add(btnPlus);
        bottomPanel.add(btn1);
        bottomPanel.add(btn2);
        bottomPanel.add(btn3);
        bottomPanel.add(btnMinus);
        bottomPanel.add(btnPM);
        bottomPanel.add(btn0);
        bottomPanel.add(btnDot);
        bottomPanel.add(btnEqual);

        mainPanel.add(bottomPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton createButton(String btnString, ActionListener actionListener) {
        JButton button = new JButton(btnString);
        button.addActionListener(actionListener);
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(PREFERED_SIZE, PREFERED_SIZE));
        return button;
    }


}
