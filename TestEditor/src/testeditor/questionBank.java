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
                    if(Questions.get(i).getClass()==testeditor.commonQuestion.class)
                    {
                        commonQuestion inArr=(commonQuestion)Questions.get(i);
                        commonQuestion add=(commonQuestion)q;                        
                        if(inArr.getAnswer(0)==add.getAnswer(0)){contained=true;break;}                            
                    }
                    else if(Questions.get(i).getClass()==testeditor.connectionQuestion.class)
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
             
    
//    public boolean checkContainment(Question q)
//    {
//        ArrayList<Question> matchingTheme = this.getQuestions(q.getTheme());
//        boolean res=true;
//        for(int i=0;i<matchingTheme.size();i++)
//        {
//            if(matchingTheme.get(i).getText() == null ? q.getText() == null : matchingTheme.get(i).getText().equals(q.getText()))
//            {
//                if(q.getClass()==matchingTheme.get(i).getClass())
//                {
//                    if(q.getClass()==testeditor.commonQuestion.class)
//                    {
//                        commonQuestion tmp1=(commonQuestion)q;
//                        commonQuestion tmp2=(commonQuestion)matchingTheme.get(i);
//                        if(tmp1.getNumberOfVariants()==tmp2.getNumberOfVariants())
//                        {
//                            for(int j=0;j<tmp1.getNumberOfVariants();j++)
//                            {
//                                if(!tmp1.getVariants().contains(tmp2.getVariant(j)))
//                                {
//                                    res=false;
//                                    break;
//                                }
//                            }
//                            return res;
//                        }else res=false;
//                    }
//                    else if(q.getClass()==testeditor.connectionQuestion.class)
//                    {
//                        connectionQuestion tmp1=(connectionQuestion)q;
//                        connectionQuestion tmp2=(connectionQuestion)matchingTheme.get(i);
//                        if(tmp1.getNumberOfVariants()==tmp2.getNumberOfVariants())
//                        {
//                            for(int j=0;j<tmp1.getNumberOfVariants();j++)
//                            {
//                                if(!tmp1.getFirstColumnVariants().contains(tmp2.getFirstColumn(j)))
//                                {
//                                    res=false;
//                                    break;
//                                }
//                            }
//                        }else res=false;
//                    }
//                }
//                else res=false;
//            }
//            else res=false;            
//            if(res==true)break;
//        }
//        return res;
//    }
}
