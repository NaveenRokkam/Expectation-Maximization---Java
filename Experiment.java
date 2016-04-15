/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package em_algorithm;

/**
 * What is the number of heads and tails in a trial.
 * This is either the given observed observation for the first experiment
 * and later on the calculated observations.
 * @author Naveen.Rokkam
 */
public class Experiment {
    double count_Heads = 0;
    double count_Tails = 0;
    
    /*
    Pre: Input is a string of H & T denoting the result of coin tosses
    Post: Total Number of heads and tails in the 10 tosses of experiment
    */    
    public Experiment(String exp_result){
        for (int i=0; i<exp_result.length();i++){
            char outcome = exp_result.charAt(i);            
            if( outcome == 'H'){
                count_Heads++;
            }            
            if( outcome == 'T'){
                count_Tails++;
            }            
        }
    }
    
    /*
    Pre: 
    Post:
    */
    public Experiment(double count_H, double count_T){
        count_Heads = count_H;
        count_Tails = count_T;
    }
    
    /*
    Pre: 
    Post:
    */
    public double get_Head_Count(){
        return count_Heads;                
    }
    
    /*
    Pre: 
    Post:
    */
    public double  get_Tails_Count(){
        return count_Tails;                
    }
    
    /*
    Pre: 
    Post:
    */
    public String toString(){
        return String.format("Total Heads: %f, Total Tails: %f",count_Heads, count_Tails);
    }
}
