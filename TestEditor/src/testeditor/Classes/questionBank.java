/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeditor.Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author admin
 */
public class questionBank implements Serializable
{
    private ArrayList<Question> Questions;
    
    public questionBank()
    {
        Questions=new ArrayList<>();
    }
    
    public boolean add(Question q)
    {
        boolean contained=false;
        for(int i=0;i<Questions.size();i++)
        {
            if(Questions.get(i).getClass()==q.getClass())
            {
                if(Questions.get(i).getText().equals(q.getText()))
                {
                    if(Questions.get(i).getClass()==testeditor.Classes.commonQuestion.class)
                    {
                        commonQuestion inArr=(commonQuestion)Questions.get(i);
                        commonQuestion add=(commonQuestion)q;                        
                        if(inArr.getAnswer(0)==add.getAnswer(0)){contained=true;break;}                            
                    }
                    else if(Questions.get(i).getClass()==testeditor.Classes.connectionQuestion.class)
                    {
                        connectionQuestion inArr=(connectionQuestion)Questions.get(i);
                        connectionQuestion add=(connectionQuestion)q;
                        if(inArr.getFirstColumn(0).equals(add.getFirstColumn(0))){contained=true;break;}
                    }
                    else {contained=true;break;}
                }
            }
        }
        
        if(!contained)Questions.add(q);
        return contained;
    }
    
    public ArrayList<Question> getQuestions(String theme,int number)
    {
        ArrayList<Question> resTmp=new ArrayList<>();
        ArrayList<Question> res=new ArrayList<>();
        ArrayList<Integer> usedIndex=new ArrayList<>();
        
        //Вибірка питань на задану тему
        for(int i=0;i<Questions.size();i++)
        {
            if(Questions.get(i).getTheme() == null ? theme == null : Questions.get(i).getTheme().equals(theme))
                resTmp.add(Questions.get(i));
        }
        
        //Вибірка заданої кількості питань
        for(int i=0;i<number;i++)
        {
            Random randob = new Random();
            int index=randob.nextInt(resTmp.size());
            while(usedIndex.contains(index)) index=randob.nextInt(resTmp.size());
            usedIndex.add(index);
            res.add(resTmp.get(index));
        }        
        return res;
    }
    
    public ArrayList<String> getThemes()
    {
         ArrayList<String> themes=new ArrayList<>();
         for(int i=0;i<Questions.size();i++)
         {
             if(themes.contains(Questions.get(i).getTheme())) continue;
             else themes.add(Questions.get(i).getTheme());
             
             
         }
         return themes;
    }
    
    public int getNumberOfQuestions(String theme)
    {
        return getQuestions(theme).size();
    }
    
    public ArrayList<Question> getQuestions(String theme)
    {
        ArrayList<Question> res=new ArrayList<>();
        
        //Вибірка питань на задану тему
        for(int i=0;i<Questions.size();i++)
        {
            if(Questions.get(i).getTheme() == null ? theme == null : Questions.get(i).getTheme().equals(theme))
                res.add(Questions.get(i));
        }
      
        return res;
    }
    
     public ArrayList<Question> getQuestions(){return Questions;}
     public void Clear(){Questions.clear();}
     public void Clear(String theme)
     {
         for(int i=0;i<Questions.size();i++)
            if(Questions.get(i).getTheme().equals(theme))Questions.remove(i);
     }
      
}
