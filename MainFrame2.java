import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.Graphics;
// import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
// import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
// import javax.swing.JTextArea;
import javax.swing.ImageIcon;
// import java.awt.FlowLayout;
// import javax.swing.BoxLayout;

public class MainFrame2 extends JFrame {
    public static void main(String[] args){
        MainFrame2 frame = new MainFrame2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static final int WIDTH = 720;
    private static final int HEIGHT = 400;

    private JTextField textFieldOfX;
    private JTextField textFieldOfY;
    private JTextField textFieldOfZ;

    private JTextField resultField;

    private int formulaId = 1;
    private int memoryId = 1;

    private JTextField mem1Field;
    private JTextField mem2Field;
    private JTextField mem3Field;
    
    public MainFrame2(){
        super("Вычисление формулы");
        setSize(WIDTH,HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width-WIDTH)/2,(kit.getScreenSize().height-HEIGHT)/2);

//@ Реализация основного функционала программы
        //^ Поле для отображения вида активной формулы
        ImageIcon imageOfFormula1 = new ImageIcon("ImgOfFormula1.BMP");
        ImageIcon imageOfFormula2 = new ImageIcon("ImgOfFormula2.BMP");
        JLabel formulaImage = new JLabel("");
        formulaImage.setIcon(imageOfFormula1);
    
        Box hboxShowingFormula = Box.createHorizontalBox();
        hboxShowingFormula.add(Box.createHorizontalGlue());
        hboxShowingFormula.add(formulaImage);
        hboxShowingFormula.add(Box.createHorizontalGlue());

        //^ Радио-кнопки выбора активной формулы
        JRadioButton button1 = new JRadioButton("Формула 1");
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                formulaImage.setIcon(imageOfFormula1);
                formulaId = 1;
            }        
        });        
        JRadioButton button2 = new JRadioButton("Формула 2");
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                formulaImage.setIcon(imageOfFormula2);
                formulaId = 2;
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
        textFieldOfX = new JTextField(6);
        textFieldOfX.setMaximumSize(textFieldOfX.getPreferredSize());

        textFieldOfY = new JTextField(6);
        textFieldOfY.setMaximumSize(textFieldOfY.getPreferredSize());

        textFieldOfZ = new JTextField( 6);
        textFieldOfZ.setMaximumSize(textFieldOfZ.getPreferredSize());

        //^ Поле для вывода результата вычисления по активной формуле
        resultField = new JTextField(15);
        resultField.setMaximumSize(resultField.getPreferredSize());

        //^ Кнопка вычисления значиения значения функции
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
                            result = function1(x,y,z);
                            break;
                        case(2):
                            result = function2(x,y,z);
                    }
                    resultField.setText(result.toString());
                }catch(NumberFormatException exc){
                    JOptionPane.showMessageDialog(MainFrame2.this, "Ошибка записи числа", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        //^ Кнопка сброса переменных x,y,z и результата вычисления
        JButton resetButton = new JButton("Сброс");
        resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                textFieldOfX.setText("");
                textFieldOfY.setText("");
                textFieldOfZ.setText("");
                resultField.setText("");
            }
        });

        //^ Радио-кнопки выбора активной памяти
        JRadioButton button3 = new JRadioButton("Память 1");
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                memoryId = 1;
            }    
        });    

        JRadioButton button4 = new JRadioButton("Память 2");
        button4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                memoryId = 2;
            }    
        });    

        JRadioButton button5 = new JRadioButton("Память 3");
        button5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                memoryId = 3;
            }    
        });    

        ButtonGroup buttonsMemorySwitch = new ButtonGroup(){{
            add(button3);
            add(button4);
            add(button5);
        }};
        buttonsMemorySwitch.setSelected(buttonsMemorySwitch.getElements().nextElement().getModel(), true);
        
        //^ Поля для просмотра содержимого ячеек памяти
        mem1Field = new JTextField("0",30);
        mem1Field.setMaximumSize(mem1Field.getPreferredSize());

        mem2Field = new JTextField("0",30);
        mem2Field.setMaximumSize(mem2Field.getPreferredSize());

        mem3Field = new JTextField("0",30);
        mem3Field.setMaximumSize(mem3Field.getPreferredSize());
        
        //^ Кнопка сброса содержимого поля активной ячейки памяти
        JButton memoryClear = new JButton("MC");
        memoryClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                switch(memoryId){
                    case(1):
                        mem1Field.setText("0");
                        break;
                    case(2):
                        mem2Field.setText("0");
                        break;
                    case(3):
                        mem3Field.setText("0");
                }
            }
        });

        //^ Кнопка суммирования результата с активной ячейкой памяти
        JButton addToMemory = new JButton("M+");
        addToMemory.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
                Double valueOfResult = Double.parseDouble(resultField.getText());
                Double sum = .0;

                switch(memoryId){
                    case(1):
                        Double valueOfMem1 = Double.parseDouble(mem1Field.getText());
                        sum = valueOfResult + valueOfMem1;
                        mem1Field.setText(sum.toString());
                        break;
                    case(2):
                        Double valueOfMem2 = Double.parseDouble(mem2Field.getText());
                        sum = valueOfResult + valueOfMem2;
                        mem2Field.setText(sum.toString());
                        break;
                    case(3):
                        Double valueOfMem3 = Double.parseDouble(mem3Field.getText());
                        sum = valueOfResult + valueOfMem3;
                        mem3Field.setText(sum.toString());
                        break;
                }
            }
        });
        
//@ Создание интерфейса
        //^ Радио-кнопки для переключения формул
        Box boxFunctionSwitch = Box.createHorizontalBox();
        boxFunctionSwitch.add(Box.createHorizontalGlue());
        boxFunctionSwitch.add(button1);
        boxFunctionSwitch.add(button2);
        boxFunctionSwitch.add(Box.createHorizontalGlue());

        //^ Изображенте активной формулы
        Box boxImgOfFormula = Box.createHorizontalBox();
        boxImgOfFormula.add(Box.createHorizontalGlue());
        boxImgOfFormula.add(formulaImage);
        boxImgOfFormula.add(Box.createHorizontalGlue());
        
        //^ Поля для ввода переменных x,y,z
        JLabel labelForX = new JLabel("X:");
        JLabel labelForY = new JLabel("Y:");
        JLabel labelForZ = new JLabel("Z:");

        Box boxVariables = Box.createHorizontalBox();
        // boxVariables.add(Box.createHorizontalGlue());
        
        boxVariables.add(labelForX);
        boxVariables.add(Box.createHorizontalStrut(10));
        boxVariables.add(textFieldOfX);
        boxVariables.add(Box.createHorizontalGlue());
        // boxVariables.add(Box.createHorizontalStrut(40));
        
        boxVariables.add(labelForY);
        boxVariables.add(Box.createHorizontalStrut(10));
        boxVariables.add(textFieldOfY);
        boxVariables.add(Box.createHorizontalGlue());
        // boxVariables.add(Box.createHorizontalStrut(40));

        boxVariables.add(labelForZ);
        boxVariables.add(Box.createHorizontalStrut(10));
        boxVariables.add(textFieldOfZ);

        // boxVariables.add(Box.createHorizontalGlue());
        
        //^ Результат
        JLabel labelForResult = new JLabel("Результат:");

        Box boxResult = Box.createHorizontalBox();
        boxResult.add(Box.createHorizontalGlue());

        boxResult.add(labelForResult);
        boxResult.add(Box.createHorizontalStrut(10));
        boxResult.add(resultField);
        
        boxResult.add(Box.createHorizontalGlue());
        
        //^ Кнопки "Вычислить", "Сброс"
        Box boxCalculateReset = Box.createHorizontalBox();
        boxCalculateReset.add(Box.createHorizontalGlue());
        
        boxCalculateReset.add(calculateButton);
        boxCalculateReset.add(Box.createHorizontalStrut(50));
        boxCalculateReset.add(resetButton);
        
        boxCalculateReset.add(Box.createHorizontalGlue());
        
        //^ Переключение и отображение ячеек памяти
        Box boxMemories = Box.createHorizontalBox();

        Box boxMem1 = Box.createVerticalBox();
        boxMem1.add(button3);
        boxMem1.add(mem1Field);
        // boxMem1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        Box boxMem2 = Box.createVerticalBox();
        boxMem2.add(button4);
        boxMem2.add(mem2Field);

        Box boxMem3 = Box.createVerticalBox();
        boxMem3.add(button5);
        boxMem3.add(mem3Field);
        boxMemories.add(Box.createHorizontalGlue());
        
        boxMemories.add(boxMem1);
        // boxMemories.add(Box.createHorizontalGlue());
        // boxResult.add(Box.createHorizontalStrut(40));

        boxMemories.add(boxMem2);
        // boxMemories.add(Box.createHorizontalGlue());
        // boxResult.add(Box.createHorizontalStrut(40));
        
        boxMemories.add(boxMem3);
        
        boxMemories.add(Box.createHorizontalGlue());
        
        //^ Кнопки очистки и суммирования к памяти
        Box boxMemoryButtons = Box.createHorizontalBox();
        boxMemoryButtons.add(Box.createHorizontalGlue());

        boxMemoryButtons.add(memoryClear);
        boxMemoryButtons.add(Box.createHorizontalStrut(75));
        boxMemoryButtons.add(addToMemory);

        boxMemoryButtons.add(Box.createHorizontalGlue());

        //^ Итоговая компоновка
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(boxFunctionSwitch);
        contentBox.add(boxImgOfFormula);
        contentBox.add(boxVariables);
        contentBox.add(boxResult);
        contentBox.add(boxCalculateReset);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(boxMemories);
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(boxMemoryButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public Double function1(Double x, Double y, Double z){
        return Math.sin(Math.log( y ) + Math.sin( Math.PI*y*y )) * Math.pow( (x*x + Math.sin(z) + Math.pow( Math.E, Math.cos(z) )) ,1/4);
    }

    public Double function2(Double x, Double y, Double z){
        return Math.pow(Math.cos(Math.pow(Math.E, x) + Math.log( Math.pow( 1+y, 2)) + Math.pow(Math.pow(Math.E, Math.cos(x) + Math.pow(Math.sin(Math.PI * z), 2)), 1/2) + Math.pow(1/x, 1/2) + Math.cos(Math.pow(y, 2))),  Math.sin(z));
    }
}
