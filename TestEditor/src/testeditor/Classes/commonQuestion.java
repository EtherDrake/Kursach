/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeditor.Classes;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class commonQuestion extends Question
{
    
    protected ArrayList<String> Variants;
    protected boolean[] Answers;
    
    public commonQuestion(ArrayList<String>var,boolean[]answ,String txt,String thm)
    {
        text=txt;
        theme=thm;
        Answers=answ;
        Variants=var;        
    }
    
    public double Check(boolean[] answ)
    {
        double result=1,part=1/Answers.length;
        for(int i=0;i<Answers.length;i++) if(answ[i]!=Answers[i]) result-=part;        
        return result;
    }
    public int getNumberOfVariants(){return Variants.size();}
    public boolean getAnswer(int i){return Answers[i];}
    public String getVariant(int i){return Variants.get(i);}
    public ArrayList<String> getVariants(){return Variants;}
    
}
