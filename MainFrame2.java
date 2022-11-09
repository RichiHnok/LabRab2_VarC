import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class MainFrame2 extends JFrame {
    public static void main(String[] args){
        MainFrame2 frame = new MainFrame2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;

    private JTextField textFieldOfX;
    private JTextField textFieldOfY;
    private JTextField textFieldOfZ;

    private JTextField resultField;

    private int formulaId;

    private JTextField mem1Field;
    private JTextField mem2Field;
    private JTextField mem3Field;

    // private JTextField mem1Field;
    
    public MainFrame2(){
        super("Вычисление формулы");
        setSize(WIDTH,HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width-WIDTH)/2,(kit.getScreenSize().height-HEIGHT)/2);
        
        //^ Поле для отображения вида активной формулы
        ImageIcon imageOfFormula1 = new ImageIcon("Снимок.BMP");
        ImageIcon imageOfFormula2 = new ImageIcon("Formula2.BMP");
        JLabel formula = new JLabel("");
        formula.setIcon(imageOfFormula1);
    
        Box hboxShowingFormula = Box.createHorizontalBox();
        hboxShowingFormula.add(Box.createHorizontalGlue());
        hboxShowingFormula.add(formula);
        hboxShowingFormula.add(Box.createHorizontalGlue());

        //^ Кнопки выбора активной формулы
        JRadioButton button1 = new JRadioButton("Кнопка 1");
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                formula.setIcon(imageOfFormula1);
            }        
        });        
        JRadioButton button2 = new JRadioButton("Кнопка 2");
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                formula.setIcon(imageOfFormula2);
            }        
        });        
        
        ButtonGroup buttonsFormulaSwitch = new ButtonGroup();
    
        buttonsFormulaSwitch.add(button1);
        buttonsFormulaSwitch.add(button2);
        buttonsFormulaSwitch.setSelected(buttonsFormulaSwitch.getElements().nextElement().getModel(), true);
        
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(button1);
        hboxButtons.add(button2);
        
        //^ Поля для ввода переменных
        textFieldOfX = new JTextField("0", 6);
        textFieldOfX.setMaximumSize(textFieldOfX.getPreferredSize());

        textFieldOfY = new JTextField("0", 6);
        textFieldOfY.setMaximumSize(textFieldOfY.getPreferredSize());

        textFieldOfZ = new JTextField("0", 6);
        textFieldOfZ.setMaximumSize(textFieldOfZ.getPreferredSize());

        //^ Поле результата вычисления по активной формуле
        resultField = new JTextField(10);
        resultField.setMaximumSize(resultField.getPreferredSize());

        //^ Кнопка сброса переменных x,y,z и результата вычисления
        JButton calculateButton = new JButton("Вычислить");
        calculateButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                try{
                    Double x = Double.parseDouble(textFieldOfX.getText());
                    Double y = Double.parseDouble(textFieldOfY.getText());
                    Double z = Double.parseDouble(textFieldOfZ.getText());
                    Double result = .0;
                    switch(formulaId){
                        case(1):
                            result = .0;
                            break;
                        case(2):
                            result = .0;
                    }
                    resultField.setText(result.toString());
                }catch(NumberFormatException exc){
                    JOptionPane.showMessageDialog(MainFrame2.this, "Ошибка записи числа", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        //^ Кнопки выбора активной памяти
        JRadioButton button3 = new JRadioButton("Память 1");
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                formula.setIcon(imageOfFormula2);
            }    
        });    

        JRadioButton button4 = new JRadioButton("Память 2");
        JRadioButton button5 = new JRadioButton("Память 3");
        ButtonGroup buttonsMemorySwitch = new ButtonGroup(){{
            add(button3);
            add(button4);
            add(button5);
        }};
        buttonsMemorySwitch.setSelected(buttonsMemorySwitch.getElements().nextElement().getModel(), false);
        
        //^ Поля для просмотра содержимого ячеек памяти
        mem1Field = new JTextField("0", 10);
        mem1Field.setMaximumSize(mem1Field.getPreferredSize());

        mem2Field = new JTextField("0",10);
        mem2Field.setMaximumSize(mem2Field.getPreferredSize());

        mem3Field = new JTextField("0",10);
        mem3Field.setMaximumSize(mem3Field.getPreferredSize());
        
        //^ Кнопка сброса содержимого поля активной ячейки памяти
        //^ Кнопка суммирования результата с активной ячейкой памяти
        
        // JLabel labelForResult = new JLabel("Результат:");

        // textFieldResult = new JTextField("0", 10);
        // textFieldResult.setMaximumSize(
        //     textFieldResult.getPreferredSize()
        // );
        // Box hboxResult = Box.createHorizontalBox();
        // hboxResult.add(Box.createHorizontalGlue());
        // hboxResult.add(labelForResult);
        // hboxResult.add(Box.createHorizontalStrut(10));
        // hboxResult.add(textFieldResult);
        // hboxResult.add(Box.createHorizontalGlue());
        
        
        // hboxFormulaType.setBorder(
        //     BorderFactory.createLineBorder(Color.YELLOW)
        // );
        //^ компоновка элементов интерфейса
        Box hboxCell1 = Box.createVerticalBox();
        hboxCell1.add(button3);
        hboxCell1.add(mem1Field);
        // hboxCell1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // hboxCell1.setBorder(null);
        
        Box hboxCell2 = Box.createVerticalBox();
        hboxCell2.add(button4);
        hboxCell2.add(mem2Field);
        
        Box hboxCell3 = Box.createVerticalBox();
        hboxCell3.add(button5);
        hboxCell3.add(mem3Field);
        
        Box hboxButtonsMemorySwitch = Box.createHorizontalBox();
        hboxButtonsMemorySwitch.add(hboxCell1);
        hboxButtonsMemorySwitch.add(hboxCell2);
        hboxButtonsMemorySwitch.add(hboxCell3);

        Box contentBox = Box.createVerticalBox();
        contentBox.add(hboxButtons);
        contentBox.add(hboxShowingFormula);
        contentBox.add(hboxButtonsMemorySwitch);
        getContentPane().add(contentBox, BorderLayout.CENTER);

        getContentPane().add(contentBox);
    }
}
