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
     * <-| % | C | /
     * 7 | 8 | 9 | *
     * 4 | 5 | 6 | +
     * 1 | 2 | 3 | -
     * +-| 0 | . | =
     */

    // The order of the enums and buttonStrings matches the layout
    private enum BtnEnum {
        BackSpace, Mod, C, Divide, Digit7, Digit8, Digit9, Multiply, Digit4, Digit5, Digit6, Add, Digit1,
        Digit2, Digit3, Subtract, Sign, Digit0, Dot, Equal
    }

    private enum Operation {
        None, Add, Multiply, Subtract, Divide, Mod
    }

    private final String[] buttonStrings = { "\u2190", "%", "C", "/", "7", "8", "9", "*", "4", "5", "6", "+", "1", "2",
            "3", "-", "+/-", "0", ".", "=" };

    private final class DigitBtnHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Just for safety, check first
            if (e.getSource() instanceof JButton) {
                JButton b = (JButton) e.getSource();
                txtDisplay.setText(txtDisplay.getText() + b.getText());
                displayLabel.setText(displayLabel.getText() + b.getText());
            }
        }
    }

    private ArrayList<JButton> buttons = new ArrayList<JButton>();

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
    double result;
    Operation operation = Operation.None;
    String operationString = "";
    boolean dotExists = false;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Calculator app = new Calculator();
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
        mainPanel.setBackground(COLOR_BKG);

        displayLabel = new JLabel();
        displayLabel.setBorder(BorderFactory.createEmptyBorder());
        displayLabel.setPreferredSize(new Dimension(WINDOW_WIDTH - 2 * MARGIN, 35));
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        txtDisplay = new JTextField();
        txtDisplay.setBackground(COLOR_BKG);
        txtDisplay.setForeground(Color.WHITE);
        txtDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        txtDisplay.setFont(new Font("Tahoma", Font.PLAIN, 25));
        txtDisplay.setBorder(BorderFactory.createEmptyBorder());
        txtDisplay.setColumns(10);
        txtDisplay.setPreferredSize(new Dimension(WINDOW_WIDTH - 2 * MARGIN, 70));

        DigitBtnHandler digitBtnHandler = new DigitBtnHandler();
        Font btnFont = new Font("Tahoma", Font.BOLD, 20);

        int numOfButtons = BtnEnum.values().length;
        assert numOfButtons == buttonStrings.length;
        for (int i = 0; i < numOfButtons; i++) {
            JButton btn = new JButton(buttonStrings[i]);
            btn.setFont(btnFont);
            btn.setPreferredSize(new Dimension(PREFERED_SIZE, PREFERED_SIZE));
            btn.setBorder(BorderFactory.createEmptyBorder());
            btn.setForeground(Color.WHITE);
            btn.setBackground(COLOR_BTN_DEFAULT);
            BtnEnum btnEnum = BtnEnum.values()[i];
            switch (btnEnum) {
            case BackSpace:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (txtDisplay.getText().length() > 0) {
                            StringBuilder strB = new StringBuilder(txtDisplay.getText());
                            strB.deleteCharAt(txtDisplay.getText().length() - 1);
                            String updatedText = strB.toString();
                            txtDisplay.setText(updatedText);

                            StringBuilder strB1 = new StringBuilder(displayLabel.getText());
                            strB1.deleteCharAt(displayLabel.getText().length() - 1);
                            String updatedText1 = strB1.toString();
                            displayLabel.setText(updatedText1);
                        }
                    }
                });
                break;
            case Mod:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstNum = Double.parseDouble(txtDisplay.getText());
                        txtDisplay.setText("");
                        operation = Operation.Mod;
                        resetDotFlag();
                        displayLabel.setText(displayLabel.getText() + btn.getText());
                    }
                });
                break;
            case C:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        txtDisplay.setText(null);
                        resetDotFlag();
                        displayLabel.setText("");
                    }
                });
                break;
            case Divide:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstNum = Double.parseDouble(txtDisplay.getText());
                        txtDisplay.setText("");
                        operation = Operation.Divide;
                        resetDotFlag();
                        displayLabel.setText(displayLabel.getText() + btn.getText());
                    }
                });
                break;
            case Multiply:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstNum = Double.parseDouble(txtDisplay.getText());
                        txtDisplay.setText("");
                        operation = Operation.Multiply;
                        resetDotFlag();
                        displayLabel.setText(displayLabel.getText() + btn.getText());
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
                        operation = Operation.Add;
                        resetDotFlag();
                        displayLabel.setText(displayLabel.getText() + btn.getText());
                    }
                });
                break;
            case Subtract:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstNum = Double.parseDouble(txtDisplay.getText());
                        txtDisplay.setText("");
                        operation = Operation.Subtract;
                        resetDotFlag();
                        displayLabel.setText(displayLabel.getText() + btn.getText());
                    }
                });
                break;
            case Equal:
                btn.setBackground(COLOR_BTN_EQUAL);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String answer;
                        secondNum = Double.parseDouble(txtDisplay.getText());
                        if (operation == Operation.Add) {
                            result = firstNum + secondNum;
                        } else if (operation == Operation.Subtract) {
                            result = firstNum - secondNum;
                        } else if (operation == Operation.Multiply) {
                            result = firstNum * secondNum;
                        } else if (operation == Operation.Divide) {
                            result = firstNum / secondNum;
                        } else if (operation == Operation.Mod) {
                            result = firstNum % secondNum;
                        } else {
                            assert false : "Something is wrong";
                        }

                        answer = String.format("%.2f", result);
                        txtDisplay.setText(answer);
                        resetDotFlag();
                        displayLabel.setText(displayLabel.getText() + " = " + answer);
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
                        displayLabel.setText(String.valueOf(ops));
                    }
                });
                break;
            case Dot:
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!dotExists) {
                            txtDisplay.setText(txtDisplay.getText() + btn.getText());
                            dotExists = true;
                            displayLabel.setText(displayLabel.getText() + btn.getText());
                        }
                    }
                });
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
                btn.setBackground(COLOR_BTN_DIGIT);
                break;

            default:
                assert false : "Something is wrong";
            break;
            }
            buttons.add(btn);
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(COLOR_BKG);
        topPanel.add(displayLabel, BorderLayout.NORTH);
        topPanel.add(txtDisplay, BorderLayout.CENTER);
        topPanel.add(new JLabel(" "), BorderLayout.SOUTH); // empty label as padding, not ideal
        mainPanel.add(topPanel, BorderLayout.NORTH);

        GridLayout grid = new GridLayout(PAD_ROW, PAD_COL, SPACEING, SPACEING);
        JPanel padPanel = new JPanel(grid);
        padPanel.setBackground(COLOR_BKG);
        for (int i = 0; i < numOfButtons; i++) {
            padPanel.add(buttons.get(i));
        }
        mainPanel.add(padPanel, BorderLayout.CENTER);

        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    void resetDotFlag() {
        dotExists = false;

    }
}
