import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JFrame frame=new JFrame("Easy Calculator"); //creates frame
    JTextField[] txtGrid;

    JButton bntPlus = new JButton("+");
    JButton bntSub = new JButton("-");
    JButton bntPro = new JButton("*");
    JButton bntDiv = new JButton("/");
    JButton bntOk = new JButton("OK");

    public Calculator(){ //constructor
        frame.setLayout(new GridLayout(2, 5)); //set layout
        txtGrid = new JTextField[5];

        String txt[] = {"12", "", "2", "=", ""};
        for(int i=0; i<5; i++){
            txtGrid[i]=new JTextField(txt[i]);
            frame.add(txtGrid[i]); //adds JTextField to grid
        }

        bntPlus.addActionListener(this);
        frame.add(bntPlus);
        
        bntSub.addActionListener(this);
        frame.add(bntSub);
        
        bntPro.addActionListener(this);
        frame.add(bntPro);

        bntDiv.addActionListener(this);
        frame.add(bntDiv);

        bntOk.addActionListener(this);
        frame.add(bntOk);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame
        frame.setVisible(true); //makes frame visible
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        //changes the operation signal
        if (obj == bntPlus) {
            txtGrid[1].setText("+");
        } else if (obj == bntSub) {
            txtGrid[1].setText("-");
        } else if (obj == bntPro) {
            txtGrid[1].setText("*");
        } else if (obj == bntDiv) {
            txtGrid[1].setText("/");
        }
        
        //caculate the result
        if (obj == bntOk) {
            int operator1 = Integer.parseInt(txtGrid[0].getText());
            int operator2 = Integer.parseInt(txtGrid[2].getText());
            String operation = txtGrid[1].getText();
            
            int tmp = 0;
            String result = "Error!";
            boolean flag = true;

            try {
                if(operation.equals("+")) {
                    tmp = operator1 + operator2;
                } else if(operation.equals("-")) {
                    tmp = operator1 - operator2;
                } else if(operation.equals("*")) {
                    tmp = operator1 * operator2;
                } else if(operation.equals("/")) {
                    tmp = operator1 / operator2;
                } else {
                    flag = false;
                }
            } catch (Exception ex) {
                flag = false;
            }

            if (flag) {
                result = Integer.toString(tmp);               
            }

            txtGrid[4].setText(result);
        }
        
    }

    public static void main(String[] args) {
        new Calculator();//makes new ButtonGrid with 2 parameters
    }
}