package java.org.daisci.nn.common;

/**
 * Created by dkudryavtsev on 5/7/15.
 */
public class Driver {
    private IActivationFunction activationFunction;
    private INetwork network;

    public Driver(IActivationFunction activationFunction, INetwork network) {
        this.activationFunction = activationFunction;
        this.network = network;
    }

    public IResult process(IData data){
        ResultVector resultVector = new ResultVector();



        return resultVector;
    }

    public IResult train(IData data, IResult expectedResult){
        ResultVector errorVector = new ResultVector();

        return errorVector;
    }

}
