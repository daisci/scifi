package java.org.daisci.nn.common;

import java.org.daisci.nn.common.layer.ILayer;
import java.org.daisci.nn.common.layer.ILayer2d;

/**
 * Created by dkudryavtsev on 5/7/15.
 */
public interface ILayerActivator {
    void activate(ILayer2d active, ILayer2d toActivate, IActivationFunction aFunc);

}
