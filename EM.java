package em_algorithm;

import java.io.*;
import java.util.*;

/**
 *
 * @author Naveen.Rokkam
 */
public class EM {

    /*
     Pre: 
     Post:
     */
    private static long nChooseK(int n, int k) {
        long num = 1;
        long result = 0;
        for (int i = 0; i < k; i++) {
            num = num * n;
            n--;
        }
        long denominator = factorial(k);
        result = num / denominator;
        return result;
    }

    /*
     Pre: 
     Post:
     */
    private static long factorial(int k) {
        long result = 1;
        for (result = 1; k > 0; k--) {
            result = result * k;
        }
        return result;
    }

    // Step 1: First Estimated parameters
    public Tools_Parameters current_input_parameters;

    // Observations recorded in different trails
    private final List<Experiment> observations;

    private List<Experiment> expectedCoinA;
    private List<Experiment> expectedCoinB;

    private static java.io.PrintStream o = System.out;

    /*
     Pre: List of all Experiments in an array and Initial Theta Values
     Post: Sets the values to the current class variables Observations & 
    Current_Input_Parameters.
     */
    public EM(List<Experiment> Experiment_observations, Tools_Parameters first_input_parameters) {
        observations = Experiment_observations;
        current_input_parameters = first_input_parameters;
    }

    public Tools_Parameters execute() {
        int iteration = 0;
        while (true) {
            Expectation_Step();
            iteration++;
            Tools_Parameters estimated_new_parameters = Maximization_Step();
            System.out.println("Estimated New Parameters in iteration" +iteration +":   "+estimated_new_parameters);

            if (current_input_parameters.converged(estimated_new_parameters)) {
                break;
            }

            current_input_parameters = estimated_new_parameters;
        }

        return current_input_parameters;
    }

    private void Expectation_Step() {
        expectedCoinA = new ArrayList<Experiment>();
        expectedCoinB = new ArrayList<Experiment>();

        for (Experiment current : observations) {
            int Heads = (int) current.get_Head_Count();
            int Tails = (int) current.get_Tails_Count();

            double probability_CoinA = bionomialProbability(10, Heads, current_input_parameters.getThetaA());
            double probability_CoinB = bionomialProbability(10, Heads, current_input_parameters.getThetaB());

            double total_probability = probability_CoinA + probability_CoinB;

            double completionCoinA = probability_CoinA / total_probability;
            double completionCoinB = probability_CoinB / total_probability;

            Experiment expectedNewCoinA = new Experiment(Heads * completionCoinA,
                    Tails * completionCoinA);

            Experiment expectedNewCoinB = new Experiment(Heads * completionCoinB,
                    Tails * completionCoinB);

            expectedCoinA.add(expectedNewCoinA);
            expectedCoinB.add(expectedNewCoinB);
        }

    }
    /* Post: Returns Theta Values for next iteration    
    */
    private Tools_Parameters Maximization_Step() {
        double sumHeads_CoinA = 0.0;
        double sumHeads_CoinB = 0.0;
        double sumTails_CoinA = 0.0;
        double sumTails_CoinB = 0.0;

        for (Experiment current : expectedCoinA) {
            sumHeads_CoinA += current.get_Head_Count();
            sumTails_CoinA += current.get_Tails_Count();
        }

        for (Experiment current : expectedCoinB) {
            sumHeads_CoinB += current.get_Head_Count();
            sumTails_CoinB += current.get_Tails_Count();
        }

        return new Tools_Parameters((sumHeads_CoinA / (sumHeads_CoinA + sumTails_CoinA)),
                (sumHeads_CoinB / (sumHeads_CoinB + sumTails_CoinB)));

    }

    private static double bionomialProbability(int n, int k, double p) {
        double q = 1.0 - p;
        return nChooseK(n, k) * Math.pow(p, k) * Math.pow(q, n - k);
    }

    /**
     * ****************** M A I N - - F U N C T I O N ***********************
     */
    public static void main(String[] args) {
        List<Experiment> Experiment = new ArrayList<Experiment>();
        Experiment.add(new Experiment("H,T,T,T,H,H,T,H,T,H"));
        Experiment.add(new Experiment("H,H,H,H,T,H,H,H,H,H"));
        Experiment.add(new Experiment("H,T,H,H,H,H,H,T,H,H"));
        Experiment.add(new Experiment("H,T,H,T,T,T,H,H,T,T"));
        Experiment.add(new Experiment("T,H,H,H,T,H,H,H,T,H"));

        Tools_Parameters firstrun = new Tools_Parameters(0.6, 0.5);

        EM em = new EM(Experiment, firstrun);
        Tools_Parameters finalParameters = em.execute();
        System.out.println("The final Results are:" + finalParameters);
    }
}
