/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeditor.Classes;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public abstract class Question implements Serializable
{
    protected String text,theme;    
    public String getText(){return text;}
    public String getTheme(){return theme;}
}
