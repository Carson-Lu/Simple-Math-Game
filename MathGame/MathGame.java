// Importing Packages
import java.awt.event.*; // Allows for events
import javax.swing.*; // User Interface
import java.awt.*; // User interface (Abstract Windowing Toolkit)


public class MathGame{

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    private Integer numCorrect, numIncorrect, num1, num2, answer;
    private String operation;



    public MathGame() {

        JFrame frame = new JFrame("Math Game");
        JPanel panel;
        JTextField textField;
        JButton newQButton, resetButton;
        JLabel labelQuestion, labelCorrect, labelIncorrect, labelOptions;
        JCheckBox boxAdd, boxSub, boxMul, boxDiv;



        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // Change x and y coordinate when adding GridBagLayout components
        if (RIGHT_TO_LEFT) {
            panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.BOTH;
        }

        if (shouldWeightX) {
            c.weightx = 0.5;
            c.weighty = 0.5;
        }

        // Textfield
        textField = new JTextField(20);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; // x location
        c.gridy = 2; // y location
        c.gridwidth = 2; // width
        c.gridheight = 1; // height
        c.ipady = 0; // Extra vertical space
        c.insets = new Insets(25, 5, 25, 5); // Whitespace
        panel.add(textField, c);

        // Buttons
        newQButton = new JButton("New Question");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(25, 5, 25, 5);
        panel.add(newQButton, c);

        resetButton = new JButton("Reset");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(20, 15, 20, 15);
        panel.add(resetButton, c);

        // Labels
        labelQuestion = new JLabel("Question text", SwingConstants.CENTER);
        labelQuestion.setVerticalAlignment(JLabel.BOTTOM);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 2;
        c.ipady = 0;
        c.insets = new Insets(25, 5, 5, 5);
        panel.add(labelQuestion, c);

        labelCorrect = new JLabel("Correct: 0");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(labelCorrect, c);

        labelIncorrect = new JLabel("Incorrect: 0");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(labelIncorrect, c);

        labelOptions = new JLabel("Options:");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(labelOptions, c);

        // Checkboxes
        boxAdd = new JCheckBox("Addition");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(boxAdd, c);

        boxSub = new JCheckBox("Subtraction");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(boxSub, c);

        boxMul = new JCheckBox("Multiplication");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(boxMul, c);

        boxDiv = new JCheckBox("Division");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipady = 0;
        c.insets = new Insets(5, 5, 5, 5);
        panel.add(boxDiv, c);


        // Key Listener, only allows a number <= 7 digits long to be entered into textField
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ((textField.getText().length() == 0) && (c == '-')) { 
            
                } else if ((textField.getText().length() > 6) || (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE))) {
                    e.consume(); 
                } 
            }
        });

        newQButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Random number [0, 3]
                Double ranOp = Math.random() * 3;
                Integer op = ranOp.intValue();

                operation = generateOperation(op, boxAdd.isSelected(), boxSub.isSelected(), boxMul.isSelected(), boxDiv.isSelected());

                Double ran1, ran2;

                switch(operation){
                    case "+":    
                        ran1 = Math.random() * 999;
                        ran2 = Math.random() * 999;

                        num1 = ran1.intValue();
                        num2 = ran2.intValue();

                        answer = num1 + num2;
                        
                        labelQuestion.setText(num1 + " + " + num2);

                        break;

                    case "-":
                        ran1 = Math.random() * 999;
                        ran2 = Math.random() * 999;
                        
                        num1 = ran1.intValue();
                        num2 = ran2.intValue();

                        answer = num1 - num2;

                        labelQuestion.setText(num1 + " - " + num2);

                        break;

                    case "*":

                        ran1 = Math.random() * 99;
                        ran2 = Math.random() * 99;

                        num1 = ran1.intValue();
                        num2 = ran2.intValue();

                        answer = num1 * num2;

                        labelQuestion.setText(num1 + " * " + num2);

                        break;

                    case "/":

                        ran1 = Math.random() * 999;
                        num1 = ran1.intValue();

                        boolean notDivisible = true;

                        while(notDivisible) {

                            ran2 = (Math.random() * 999) + 1;
                            num2 = ran2.intValue();

                            if ((num1 % num2) == 0 ) {
                                notDivisible = false;
                            }
                        }

                        answer = num1 / num2;

                        labelQuestion.setText(num1 + " / " + num2);

                        break;

                    case "Invalid": 
                        labelQuestion.setText("Please check a box");
                        break;
                        
                    default: 
                        break;
                }

            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numCorrect = 0;
                numIncorrect = 0;
                labelCorrect.setText("Correct: " + numCorrect);
                labelIncorrect.setText("Incorrect: " + numIncorrect);

            }
        });

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });


        frame.add(panel);
        
        // Setting JFrame Properties
        frame.setResizable(false); // Frame is not resizable
        frame.setMinimumSize(new Dimension(520, 300)); 
        frame.setVisible(true); // Frame is visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

    }




    public static String generateOperation(Integer opNum, boolean add, boolean sub, boolean mul, boolean div) {
        String[] arrayOperation = new String[]{"+", "-", "*", "/"};
        boolean[] operationTicked = new boolean[]{add, sub, mul, div};

        if (add || sub || mul || div) {
            if (operationTicked[opNum]) {
                return arrayOperation[opNum];
            } else {
                opNum++;
                if (opNum > 3) {
                    return generateOperation(0, add, sub, mul, div);
                } else {
                    return generateOperation(opNum, add, sub, mul, div);
                }
            }
        }

        return "Invalid";
    }



    // Generates new Question 
    // 
    public static void generateQuestion(Integer num1, Integer num2, boolean add, boolean sub, boolean mul, boolean div) {







    

    }

    public void checkAnswer() {

    }


    
    public static void main(String[] args) {
        MathGame myGame = new MathGame();


        // Testing generateOperation
        System.out.println(generateOperation(0, true, true, true, true));
        System.out.println(generateOperation(1, true, true, true, true));
        System.out.println(generateOperation(2, true, true, true, true));
        System.out.println(generateOperation(3, true, true, true, true));
        System.out.println(generateOperation(0, false, true, true, true)); // should produce -
        System.out.println(generateOperation(3, true, false, false, false)); // should produce +
        System.out.println(generateOperation(2, false, true, false, false)); // should produce -


    }
}
