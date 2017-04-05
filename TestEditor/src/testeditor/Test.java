/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeditor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Test implements Serializable{
    private String Subject;
    private String Name;
    private ArrayList<Question> Questions;
    private int Time;
    private double value;
    private boolean training;
    
    //Конструктор
    public Test(String subj,String n,int t,ArrayList<Question> quest)
    {
        Subject=subj;
        Name=n;
        Time=t;
        Questions=quest;
        value=100/Questions.size();
    }
    
    //Конструктор для побудови теста на основі існуючого
    public Test(int n, Test test)
    {
        Subject=test.getSubject();
        Name=test.getName();
        Time=test.getTime();
        Questions = new ArrayList<>();
        
        ArrayList<Question> tmp=test.getQuestions();
        ArrayList<Integer> usedIndex=new ArrayList<>();
        
        for(int i=0;i<n;i++)
        {
            Random randob = new Random();
            int index=randob.nextInt(tmp.size());
            while(usedIndex.contains(index)) index=randob.nextInt(tmp.size());
            usedIndex.add(index);
            Questions.add(tmp.get(index));
        }
        value=100/Questions.size();
    }
    
    public ArrayList<Question> getQuestions(){return Questions;}
    public String getSubject(){return Subject;}
    public String getName(){return Name;}
    public int getTime(){return Time;}
    public double getValue(){return value;}
    
    public void setTraining(boolean t){training=t;}
}
