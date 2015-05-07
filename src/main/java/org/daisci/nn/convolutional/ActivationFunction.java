package java.org.daisci.nn.convolutional;

/**
 * Created by dkudryavtsev on 4/30/15.
 */
public class ActivationFunction {

    public double computeActivity(double signals){
        return (1.7159*Math.tanh(0.66666667 * signals));
        //return 1/(1 + Math.exp(-x));
    }

    public double computeError(double signals) {
        return (0.66666667/1.7159*(1.7159+(signals))*(1.7159-(signals)));  // derivative of the sigmoid as a function of the sigmoid's output
    }
}
