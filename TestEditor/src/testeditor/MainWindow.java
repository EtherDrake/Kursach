/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeditor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;



/**
 *
 * @author admin
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    //Ініціалізація
    public MainWindow() {
        initComponents();
        jLabel1.setText("Назва тесту");
        jLabel2.setText("Тема");
        jLabel3.setText("Предмет");
        jLabel4.setText("Тип питання");
        jLabel5.setText("Кількість варіантів");
        jLabel6.setText("Час на одне питання");
        jSpinner2.setValue(30);
        jSpinner1.setValue(4);
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("1");
        jButton1.setText("Створити");
        jButton2.setText("◄");
        jButton3.setText("►");
        jButton4.setText("Видалити питання");
        jTable1.setRowHeight(40);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prepareModel();
        changeStance(false); 
         
    }
    
    //Зміна стану редагування(тест/питання)
    void changeStance(boolean x){
        
        jLabel2.setEnabled(x);
        jLabel4.setEnabled(x);
        jTextField3.setEnabled(x);
        jTextField4.setEnabled(x);
        jComboBox1.setEnabled(x);
        jButton2.setEnabled(x);
        jButton3.setEnabled(x);
        jButton4.setEnabled(x);
        jTextArea1.setEnabled(x);
        jTable1.setEnabled(x);
        jLabel2.setEnabled(x);
        jLabel4.setEnabled(x);
        jLabel5.setEnabled(x);
        jSpinner1.setEnabled(x);

        jTextField1.setEnabled(!x);
        jTextField2.setEnabled(!x);
        jButton1.setEnabled(!x);
        jLabel1.setEnabled(!x);
        jLabel3.setEnabled(!x);
        jLabel6.setEnabled(!x);
        jSpinner2.setEnabled(!x);
        
    }
    
    void showQuestion(int index)
    {
        Question tmp=arr.get(index);
         jTextArea1.setText(tmp.getText());
                jTextField3.setText(tmp.getTheme());

                if(tmp.getClass()==testeditor.commonQuestion.class)
                {//Звичайне питання
                    jComboBox1.setSelectedIndex(0);
                    commonQuestion commonTmp=(commonQuestion) tmp;
                    DefaultTableModel data = new DefaultTableModel() {
                        Class[] types = new Class [] {java.lang.Boolean.class, java.lang.String.class};

                         @Override
                        public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                        }
                    };
                    data.setColumnCount(2);
                    data.setColumnIdentifiers(new Object[]{"",""});
                    for(int i=0;i<commonTmp.getNumberOfVariants();i++)
                        data.addRow(new Object[] {commonTmp.getAnswer(i), commonTmp.getVariant(i)});
                    jTable1.setModel(data);
                    jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
                    jLabel5.setEnabled(true);
                    jSpinner1.setEnabled(true);
                    jSpinner1.setValue(commonTmp.getNumberOfVariants());
                }
                else if(tmp.getClass()==testeditor.connectionQuestion.class)
                {//Питання з двома колонками
                    jComboBox1.setSelectedIndex(1);
                    connectionQuestion connectionTmp=(connectionQuestion) tmp;
                    DefaultTableModel data = new DefaultTableModel() {
                        Class[] types = new Class [] {java.lang.String.class, java.lang.String.class};                
                    };
                    data.setColumnCount(2);
                    data.setColumnIdentifiers(new Object[]{"Перший стовбчик","Другий стовбчик"});
                    for(int i=0;i<connectionTmp.getNumberOfVariants();i++)
                        data.addRow(new Object[] {connectionTmp.getFirstColumn(i), connectionTmp.getAnswer(i)});
                    jTable1.setModel(data);
                    jLabel5.setEnabled(true);
                    jSpinner1.setEnabled(true);
                    jSpinner1.setValue(4);
                }
                else if(tmp.getClass()==testeditor.printQuestion.class)
                {//Питання з введенням з клавіатури
                    jComboBox1.setSelectedIndex(2);
                    printQuestion printTmp=(printQuestion) tmp;
                    DefaultTableModel data = new DefaultTableModel() {
                        Class[] types = new Class [] {java.lang.String.class};                
                    };
                    data.setColumnCount(1);
                    data.setColumnIdentifiers(new Object[]{""});
                    data.addRow(new Object[] {printTmp.getAnswer()});
                    jTable1.setModel(data);
                    jLabel5.setEnabled(false);
                    jSpinner1.setEnabled(false);
                }
        
    }
    
    void prepareModel()
    {
        //Модель для звичайного питання
        DefaultTableModel data = new DefaultTableModel() {
                Class[] types = new Class [] {java.lang.Boolean.class, java.lang.String.class};
                
                 @Override
                public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
                }
            };
        data.setColumnCount(2);
        data.setColumnIdentifiers(new Object[]{"",""});
        for(int i=0;i<4;i++)data.addRow(new Object[] {false, ""});
        jTable1.setModel(data);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        jLabel5.setEnabled(true);
        jSpinner1.setEnabled(true);
        jSpinner1.setValue(4);
        jTextArea1.setText("");
        jTextField3.setText("");
        
        jTextArea1.setLineWrap(true);
    }

    //Глобальні змінні
    ArrayList<Question> arr=new ArrayList<>();
    String testNameTemp, testSubjectTemp;
    int questionCount,testTimeTemp;
    String questionTextTemp,themeTemp;
    
    boolean [] answers;
    ArrayList<String> variants=new ArrayList<>();
    ArrayList<String> secondVariants=new ArrayList<>();
    ArrayList<String> answersConnection;
    String answerPrint;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jTextField1.setText("jTextField1");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("jLabel2");

        jTextField2.setText("jTextField2");

        jLabel3.setText("jLabel3");

        jTextField3.setText("jTextField3");

        jLabel4.setText("jLabel4");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Вибір правильних", "Зі списком", "З введенням з клавіатури" }));
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBox1FocusLost(evt);
            }
        });
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField4.setText("jTextField4");
        jTextField4.setFocusable(false);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(4, 2, 8, 1));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jMenu1.setText("Файл");

        jMenuItem1.setText("Новий");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Завантажити");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Зберегти");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addComponent(jLabel5)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Завантаження з файла
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFileChooser chooser=new JFileChooser("/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tests", "tst");
        chooser.setFileFilter(filter);
        int returnVal=chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        { //Читання з файла
            String filename=chooser.getSelectedFile().getAbsolutePath();           
            try
            {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream is = new ObjectInputStream(fis);
                Test testTmp=(Test)is.readObject();
                arr=testTmp.getQuestions();
                jTextField2.setText(testTmp.getSubject());
                jTextField1.setText(testTmp.getName());
                jSpinner2.setValue(testTmp.getTime());
                changeStance(true);
                
                jTextField4.setText("1");
                questionCount=arr.size();
                showQuestion(0);
                
            }catch (ClassNotFoundException e){} catch (IOException ex) {}
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    //Задання початкових значеннь для теста(назва, предмет, час на одне питання)
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        testNameTemp=jTextField1.getText();
        testSubjectTemp=jTextField2.getText();
        testTimeTemp=Integer.parseInt(jSpinner2.getValue().toString());
        changeStance(true);
        questionCount=0;
    }//GEN-LAST:event_jButton1ActionPerformed

    //Зміна типа питання
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(jComboBox1.getSelectedIndex()==0)
        {//Звичайне питання
            DefaultTableModel data = new DefaultTableModel() {
                Class[] types = new Class [] {java.lang.Boolean.class, java.lang.String.class};
                
                 @Override
                public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
                }
            };
            data.setColumnCount(2);
            data.setColumnIdentifiers(new Object[]{"",""});
            for(int i=0;i<4;i++)data.addRow(new Object[] {false, ""});
            jTable1.setModel(data);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
            jLabel5.setEnabled(true);
            jSpinner1.setEnabled(true);
            jSpinner1.setValue(4);
        }
        else if(jComboBox1.getSelectedIndex()==1)
        {//Питання з двома колонками
            DefaultTableModel data = new DefaultTableModel() {
                Class[] types = new Class [] {java.lang.String.class, java.lang.String.class};                
            };
            data.setColumnCount(2);
            data.setColumnIdentifiers(new Object[]{"Перший стовбчик","Другий стовбчик"});
            for(int i=0;i<4;i++)data.addRow(new Object[] {"", ""});
            jTable1.setModel(data);
            jLabel5.setEnabled(true);
            jSpinner1.setEnabled(true);
            jSpinner1.setValue(4);
        }
        else
        {//Питання з введенням з клавіатури
            DefaultTableModel data = new DefaultTableModel() {
                Class[] types = new Class [] {java.lang.String.class};                
                @Override
                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }
            };
            data.setColumnCount(1);
            data.setColumnIdentifiers(new Object[]{""});
            data.addRow(new Object[] {""});
            jTable1.setModel(data);
            jLabel5.setEnabled(false);
            jSpinner1.setEnabled(false);
        }    
    }//GEN-LAST:event_jComboBox1ActionPerformed

    //На одне питання назад
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(Integer.parseInt(jTextField4.getText())==1)return;
        else
        {
            if(arr.size()>Integer.parseInt(jTextField4.getText())-1)
            {//Перезапис питання
                int num=Integer.parseInt(jSpinner1.getValue().toString());
                questionTextTemp=jTextArea1.getText();
                themeTemp=jTextField3.getText();

                if(jComboBox1.getSelectedIndex()==0)
                {//Звичайне питання
                    answers=new boolean[num];
                    variants=new ArrayList<>();
                    for(int i=0;i<num;i++)
                    {
                        answers[i]=(boolean)jTable1.getValueAt(i, 0);
                        variants.add(jTable1.getValueAt(i, 1).toString());
                    }
                    arr.set(Integer.parseInt(jTextField4.getText())-1,new commonQuestion(variants, answers, questionTextTemp, themeTemp));
                }
                else if(jComboBox1.getSelectedIndex()==1)
                {//Питання з двома колонками
                    variants=new ArrayList<>();
                    secondVariants=new ArrayList<>();
                    answersConnection=new ArrayList<>();
                    for(int i=0;i<num;i++)
                    {
                        variants.add(jTable1.getValueAt(i, 0).toString());
                        if(!secondVariants.contains(jTable1.getValueAt(i, 1).toString())) 
                            secondVariants.add(jTable1.getValueAt(i, 1).toString());
                        Collections.shuffle(secondVariants);
                        answersConnection.add(jTable1.getValueAt(i, 1).toString());
                    }
                    arr.set(Integer.parseInt(jTextField4.getText())-1, new connectionQuestion(variants, secondVariants, answersConnection, questionTextTemp, themeTemp));
                }
                else
                {//Питання з введенням з клавіатури
                    answerPrint=jTable1.getValueAt(0, 0).toString();
                    arr.set(Integer.parseInt(jTextField4.getText())-1, new printQuestion(answerPrint, questionTextTemp, themeTemp));
                }
            }
            
            jTextField4.setText(String.valueOf(Integer.parseInt(jTextField4.getText())-1));
            showQuestion(Integer.parseInt(jTextField4.getText())-1);
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    //Зміна кількості варіантів відповіді
    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        int num=Integer.parseInt(jSpinner1.getValue().toString());
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        if(num>=jTable1.getRowCount()) model.addRow(new Object[2]);
        else model.setRowCount(num);
        
    }//GEN-LAST:event_jSpinner1StateChanged

    //На одне питання вперед
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int index=Integer.parseInt(jTextField4.getText());
        if(index>=questionCount)
        {//Збереження питання
            int num=Integer.parseInt(jSpinner1.getValue().toString());
            questionTextTemp=jTextArea1.getText();
            themeTemp=jTextField3.getText();

            if(jComboBox1.getSelectedIndex()==0)
            {//Звичайне питання
                answers=new boolean[num];
                variants=new ArrayList<>();
                for(int i=0;i<num;i++)
                {
                    answers[i]=(boolean)jTable1.getValueAt(i, 0);
                    variants.add(jTable1.getValueAt(i, 1).toString());
                }
                arr.add(new commonQuestion(variants, answers, questionTextTemp, themeTemp));            
            }
            else if(jComboBox1.getSelectedIndex()==1)
            {//Питання з двома колонками
                variants=new ArrayList<>();
                secondVariants=new ArrayList<>();
                answersConnection=new ArrayList<>();
                for(int i=0;i<num;i++)
                {
                    variants.add(jTable1.getValueAt(i, 0).toString());
                    if(!secondVariants.contains(jTable1.getValueAt(i, 1).toString())) 
                        secondVariants.add(jTable1.getValueAt(i, 1).toString());
                    Collections.shuffle(secondVariants);
                    answersConnection.add(jTable1.getValueAt(i, 1).toString());
                }
                arr.add(new connectionQuestion(variants, secondVariants, answersConnection, questionTextTemp, themeTemp));
            }
            else
            {//Питання з введенням з клавіатури
                answerPrint=jTable1.getValueAt(0, 0).toString();
                arr.add(new printQuestion(answerPrint, questionTextTemp, themeTemp));
            }

            jComboBox1.setSelectedIndex(0);
            jTextField4.setText(String.valueOf(Integer.parseInt(jTextField4.getText())+1));
            jSpinner1.setValue(4);
            DefaultTableModel data = new DefaultTableModel() {
                    Class[] types = new Class [] {java.lang.Boolean.class, java.lang.String.class};

                     @Override
                    public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                    }
                };
            data.setColumnCount(2);
            data.setColumnIdentifiers(new Object[]{"",""});
            for(int i=0;i<4;i++)data.addRow(new Object[] {false, ""});
            jTable1.setModel(data);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
            jLabel5.setEnabled(true);
            jSpinner1.setEnabled(true);
            jSpinner1.setValue(4);
            questionCount++;
        }
        else
        {
            if(arr.size()>Integer.parseInt(jTextField4.getText())-1)
            {//Перезапис
                int num=Integer.parseInt(jSpinner1.getValue().toString());
                questionTextTemp=jTextArea1.getText();
                themeTemp=jTextField3.getText();

                if(jComboBox1.getSelectedIndex()==0)
                {//Звичайне питання
                    answers=new boolean[num];
                    variants=new ArrayList<>();
                    for(int i=0;i<num;i++)
                    {
                        answers[i]=(boolean)jTable1.getValueAt(i, 0);
                        variants.add(jTable1.getValueAt(i, 1).toString());
                    }
                    arr.set(Integer.parseInt(jTextField4.getText())-1,new commonQuestion(variants, answers, questionTextTemp, themeTemp));
                }
                else if(jComboBox1.getSelectedIndex()==1)
                {//Питання з двома колонками
                    variants=new ArrayList<>();
                    secondVariants=new ArrayList<>();
                    answersConnection=new ArrayList<>();
                    for(int i=0;i<num;i++)
                    {
                        variants.add(jTable1.getValueAt(i, 0).toString());
                        if(!secondVariants.contains(jTable1.getValueAt(i, 1).toString())) 
                            secondVariants.add(jTable1.getValueAt(i, 1).toString());
                        Collections.shuffle(secondVariants);
                        answersConnection.add(jTable1.getValueAt(i, 1).toString());
                    }
                    arr.set(Integer.parseInt(jTextField4.getText())-1, new connectionQuestion(variants, secondVariants, answersConnection, questionTextTemp, themeTemp));
                }
                else
                {//Питання з введенням з клавіатури
                    answerPrint=jTable1.getValueAt(0, 0).toString();
                    arr.set(Integer.parseInt(jTextField4.getText())-1, new printQuestion(answerPrint, questionTextTemp, themeTemp));
                }
            }
            
            jTextField4.setText(String.valueOf(Integer.parseInt(jTextField4.getText())+1));
            showQuestion(Integer.parseInt(jTextField4.getText())-1);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    
    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1FocusLost

    //Збереження файла
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if(arr.size()>0)
        {
            Test toSave=new Test(testSubjectTemp, testNameTemp, testTimeTemp, arr);
            JFileChooser chooser=new JFileChooser("/");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Tests", "tst");
            chooser.setFileFilter(filter);
            int returnVal=chooser.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {//Запис у файл
                String filename=chooser.getSelectedFile().getAbsolutePath();
                String extentionTmp=filename.substring(filename.length()-3, filename.length());
                if(!"tst".equals(extentionTmp))filename+=".tst";
                try 
                {
                    FileOutputStream fos;
                    fos=new FileOutputStream(filename);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(toSave);
                    oos.close();
                    fos.close();
                } 
                catch (FileNotFoundException e) {}
                catch(IOException e){}  
            }      
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        jLabel5.setEnabled(true);
        jSpinner1.setEnabled(true);
        jSpinner1.setValue(4);
        
        jTextArea1.setLineWrap(true);
        
        
        arr=new ArrayList<>();
        questionCount=0;
        jTextArea1.setText("");
        jTextField3.setText("");
        jComboBox1.setSelectedIndex(0);
        jTextField4.setText("1");
        changeStance(false);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int index=Integer.valueOf(jTextField4.getText());
        questionCount--;
        if(arr.size()<=1)
        {
            if(arr.size()==1)
            {
                arr.remove(index-1);
                
                prepareModel();
            }
        }
        else
        {
            
            arr.remove(index-1);
            if(index!=1)
            {
                jTextField4.setText(String.valueOf(index-1));
                showQuestion(index-2);

            }
            else
            {
                showQuestion(0);
            }
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
