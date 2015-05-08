package java.org.daisci.nn.common;

/**
 * Created by dkudryavtsev on 5/7/15.
 */
public interface IActivationFunction {
    double computeActivity(double signals);
    double computeError(double signals);
}
