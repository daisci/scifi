package java.org.daisci.nn.convolutional;

import java.org.daisci.nn.common.IActivationFunction;
import java.org.daisci.nn.common.ILayerActivator;
import java.org.daisci.nn.common.INetwork;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dkudryavtsev on 4/30/15.
 */
public class Network implements INetwork {
    private List<Layer> layers = new LinkedList<>();
    private InputLayer inputLayer;
    private OutputLayer outputLayer;

    public void forwardPropagation(ILayerActivator layerActivator, IActivationFunction aFunc) {
        Iterator<Layer> layerItr = layers.listIterator();
        if (layerItr.hasNext()) {
            Layer current = layerItr.next();

            while (layerItr.hasNext()) {
                Layer next = layerItr.next();

                layerActivator.activate(current, next, aFunc);

                current = next;
            }
        }

    }

    public void backPropagation(ILayerActivator layerActivator, IActivationFunction aFunc){

    }
}
