package java.org.daisci.nn.convolutional;

/**
 * Created by dkudryavtsev on 4/30/15.
 */
public class LayerActivator {

    public void activate(Layer active, Layer toActivate, ActivationFunction aFunc){
        double signalSum = 0;
        double weight = 0;
        for (int a = 0; a < toActivate.getRows()*toActivate.getCols(); a++) {

            int ai = a % toActivate.getRows();
            int aj = a / toActivate.getRows();
            weight = toActivate.getWeight(a % toActivate.getRows(), a / toActivate.getRows());

            for (int i = 0; i < active.getRows(); i++) {
                for (int j = 0; j < active.getCols(); j++) {
                    signalSum += weight * active.getActivity()[i][j];
                }
            }

            toActivate.setActivity(ai, aj, aFunc.computeActivity(signalSum));
        }
    }

}
