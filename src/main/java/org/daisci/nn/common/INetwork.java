package java.org.daisci.nn.common;

import java.org.daisci.nn.convolutional.ActivationFunction;
import java.org.daisci.nn.convolutional.LayerActivator;

/**
 * Created by dkudryavtsev on 5/7/15.
 */
public interface INetwork {
    void forwardPropagation(ILayerActivator layerActivator, IActivationFunction aFunc);
    void backPropagation(ILayerActivator layerActivator, IActivationFunction aFunc);
}
