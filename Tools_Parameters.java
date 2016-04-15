/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package em_algorithm;

/**
 * This Class Contains Theta Values which are first time random guess values
 * then the next iteration values these values are calculated based on EM 
 * algorithm
 * @author Naveen.Rokkam
 */
public class Tools_Parameters {
   
    double thetaA =0; // Probability of getting a head on Coin A
    double thetaB =0; // Probability of getting a head on Coin B
    double precision = 0.00001; // The precision which we want to achieve 
                                // between two consecutive iterations.
               
    /*
    Pre: Initial Values of Theta
    Post: Variables of class are initialized with these initial values
    */
    public Tools_Parameters(double initial_thetaA, double initial_thetaB) {
        thetaA = initial_thetaA; 
        thetaB = initial_thetaB;
    }
    
    /* 
    Pre: New Theta Values from EM
    Post: Checks whether the current Iteration Theta is within the precision 
    Margin when compared to new Theta from EM.
    */
    public boolean converged( Tools_Parameters new_em_estimated) {
        return Math.abs(thetaA - new_em_estimated.thetaA ) < precision &&
                Math.abs(thetaB - new_em_estimated.thetaB) < precision;
    }
    
    /* Post: Fetch current Value of Theta A
    */
    public double getThetaA(){
        return thetaA;
    }
    
    /* Post: Fetch current Value of Theta B
    */
    public double getThetaB(){
        return thetaB;
    }
    
    /* Overriding the object String Function */
    @Override
    public String toString(){
        return String.format("Theta A = %f , Theta B = %f",thetaA, thetaB);
    }         
}
