/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeditor;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class connectionQuestion extends Question
{
    protected ArrayList<String> firstColumn;
    protected ArrayList<String> secondColumn;
    protected ArrayList<String> Answers;
    
    public connectionQuestion(ArrayList<String> first, ArrayList<String> second, ArrayList<String> answ, String txt, String thm)
    {
        firstColumn=first;
        secondColumn=second;
        Answers=answ;
        text=txt;
        theme=thm;
    }
    
    public double Check(ArrayList<String> answ)
    {
        double result=1,part=1/Answers.size();
        
        for(int i=0;i<Answers.size();i++) 
                if(answ.get(i) == null ? Answers.get(i) != null : !answ.get(i).equals(Answers.get(i)))
                    result-=part;   
        
        return result;
    } 
    public int getNumberOfVariants(){return firstColumn.size();}
    public String getFirstColumn(int i){return firstColumn.get(i);}
    public String getSecondColumn(int i){return secondColumn.get(i);}
    public ArrayList<String> getFirstColumnVariants(){return firstColumn;}
    public ArrayList<String> getSecondColumnVariants(){return secondColumn;}
    public String getAnswer(int i){return Answers.get(i);}
}
