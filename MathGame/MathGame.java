// Importing Packages
import java.awt.event.*; // Allows for events
import javax.swing.*; // User Interface
import java.awt.*; // User interface (Abstract Windowing Toolkit)


public class MathGame{



    public MathGame() {

        JFrame frame = new JFrame("Math Game");
        JPanel panel, uInputPanel;
        JTextField textField = new JTextField(20);
        JButton newQButton, resetButton;
        JLabel labelQuestion, labelCorrect, labelIncorrect, labelOptions;
        JCheckBox boxAdd, boxSub, boxMul, boxDiv;

        panel = new JPanel();
        uInputPanel = new JPanel();

        GroupLayout layout = new GroupLayout(panel);
        FlowLayout fLayout = new FlowLayout();

        newQButton = new JButton("New Question");

        labelQuestion = new JLabel("Question text");
        labelIncorrect = new JLabel("Incorrect text");
        labelOptions = new JLabel("Options:");

        boxAdd = new JCheckBox("Addition");
        boxSub = new JCheckBox("Subtraction");
        boxMul = new JCheckBox("Multiplication");
        boxDiv = new JCheckBox("Division");

        uInputPanel.setLayout(fLayout);
        uInputPanel.add(textField);
        uInputPanel.add(newQButton);

        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(labelQuestion)
                    .addComponent(uInputPanel)
                    .addComponent(labelIncorrect))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(labelOptions)
                    .addComponent(boxAdd)
                    .addComponent(boxSub)
                    .addComponent(boxMul)
                    .addComponent(boxDiv))
        );


        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuestion)
                    .addComponent(labelOptions))
                .addComponent(boxAdd)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(uInputPanel)
                    .addComponent(boxSub))
                .addComponent(boxMul)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIncorrect)
                    .addComponent(boxDiv))
        );


        // Key Listener, only allows a number <= 6 digits long to be entered into textField
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((textField.getText().length() > 5) || (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE))) {
                    e.consume(); 
                } 
            }
        });

        newQButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });


        frame.add(panel);
        
        // Setting JFrame Properties
        frame.setResizable(true); // Frame is not resizable
        frame.setMinimumSize(new Dimension(500, 200)); // Frame size is automatically at 250x250
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

        Double ran1 = Math.random() * 999;
        Double ran2 = Math.random() * 999;
        Double ranOp = Math.random() * 3;

        // Converts random numbers to Integer
        num1 = ran1.intValue();
        num2 = ran2.intValue();
        Integer op = ranOp.intValue();

        String operation = generateOperation(op, add, sub, mul, div);



    

    }

    public void checkAnswer() {

    }

    public static void pain(Integer number) {
        number = 23;
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
