/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeditor;

/**
 *
 * @author admin
 */
public class printQuestion extends Question
{
    
    protected String Answer;
    
    public printQuestion(String answ,String txt, String thm)
    {
        Answer=answ.toLowerCase();
        text=txt;
        theme=thm;
    }

    public double Check(String answ)
    {
        answ=answ.toLowerCase();
        if(Answer == null ? answ != null : !Answer.equals(answ)) return 1;
        else return 0;
    }
    
    public String getAnswer(){return Answer;}
}
