package calculator_pack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Calculator {

    /*
     * layout
     * -------------------
     * <-| % | C | %
     * 7 | 8 | 9 | *
     * 4 | 5 | 6 | +
     * 1 | 2 | 3 | -
     * +-| 0 | . | =
     */

    // The order of the enums and buttonStrings matches the layout
    private enum ButtonType {
        BackSpace, Percentage, C, Divide, Digit7, Digit8, Digit9, Multiply, Digit4, Digit5, Digit6, Add, Digit1,
        Digit2, Digit3, Subtract, Sign, Digit0, Dot, Equal
    }

    private final String[] buttonStrings = { "\u2190", "%", "C", "/", "7", "8", "9", "*", "4", "5", "6", "+", "1", "2",
            "3", "-", "+/-", "0", ".", "=" };

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

    private ArrayList<JButton> buttons = new ArrayList<JButton>();

    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 520;
    final int SPACE = 1;
    final int MARGIN = 10;
    final int PAD_COL = 4;
    final int PAD_ROW = 5;
    final int PREFERED_SIZE = (WINDOW_WIDTH - 2 * MARGIN - (PAD_COL - 1) * SPACE) / PAD_COL;

    private JFrame frame;
    private JTextField txtDisplay;
    private JLabel calulationDisplay; // Show current calculation
    double firstNum;
    double secondNum;
    String operation;
    double result;

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
        frame.setResizable(false);
        frame.setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtDisplay = new JTextField();
        txtDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtDisplay.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        txtDisplay.setColumns(10);
        txtDisplay.setPreferredSize(new Dimension(WINDOW_WIDTH - 2 * MARGIN, 70));

        DigitBtnHandler digitBtnHandler = new DigitBtnHandler(txtDisplay);
        Font btnFont = new Font("Tahoma", Font.BOLD, 20);

        int numOfButtons = ButtonType.values().length;
        assert numOfButtons == buttonStrings.length;
        for (int i = 0; i < numOfButtons; i++) {
            JButton btn = new JButton(buttonStrings[i]);
            btn.setFont(btnFont);
            btn.setPreferredSize(new Dimension(PREFERED_SIZE, PREFERED_SIZE));
            btn.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
            ButtonType btnType = ButtonType.values()[i];
            switch (btnType) {
            case BackSpace:
                btn.addActionListener(new ActionListener() {
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
                break;
            case Percentage:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstNum = Double.parseDouble(txtDisplay.getText());
                        txtDisplay.setText("");
                        operation = "%";
                    }
                });
                break;
            case C:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        txtDisplay.setText(null);
                    }
                });
                break;
            case Divide:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstNum = Double.parseDouble(txtDisplay.getText());
                        txtDisplay.setText("");
                        operation = "/";
                    }
                });
                break;
            case Multiply:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstNum = Double.parseDouble(txtDisplay.getText());
                        txtDisplay.setText("");
                        operation = "*";
                    }
                });
                break;
            case Add:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(getClass());
                        firstNum = Double.parseDouble(txtDisplay.getText());
                        txtDisplay.setText("");
                        operation = "+";
                    }
                });
                break;
            case Subtract:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstNum = Double.parseDouble(txtDisplay.getText());
                        txtDisplay.setText("");
                        operation = "-";
                    }
                });
                break;
            case Equal:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String answer;
                        secondNum = Double.parseDouble(txtDisplay.getText());
                        if (operation == "+") {
                            result = firstNum + secondNum;
                        } else if (operation == "-") {
                            result = firstNum - secondNum;
                        } else if (operation == "*") {
                            result = firstNum * secondNum;
                        } else if (operation == "/") {
                            result = firstNum / secondNum;
                        } else if (operation == "%") {
                            result = firstNum % secondNum;
                        } else {
                            assert false : "Something is wrong";
                        }

                        answer = String.format("%.2f", result);
                        txtDisplay.setText(answer);
                    }
                });
                break;
            case Sign:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double ops = Double.parseDouble(String.valueOf(txtDisplay.getText()));
                        ops = ops * (-1);
                        txtDisplay.setText(String.valueOf(ops));
                    }
                });
                break;
            case Dot:
                break;
            case Digit0:
            case Digit1:
            case Digit2:
            case Digit3:
            case Digit4:
            case Digit5:
            case Digit6:
            case Digit7:
            case Digit8:
            case Digit9:
                btn.addActionListener(digitBtnHandler);
                break;

            default:
                assert false : "Something is wrong";
            break;
            }
            buttons.add(btn);
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(MARGIN, MARGIN, MARGIN, MARGIN));

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(txtDisplay, BorderLayout.NORTH);
        topPanel.add(new JLabel(" "), BorderLayout.CENTER); // empty label as padding, not ideal
        mainPanel.add(topPanel, BorderLayout.NORTH);

        GridLayout grid = new GridLayout(PAD_ROW, PAD_COL, SPACE, SPACE);
        JPanel padPanel = new JPanel(grid);
        for (int i = 0; i < numOfButtons; i++) {
            padPanel.add(buttons.get(i));
        }
        mainPanel.add(padPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
