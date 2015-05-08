package java.org.daisci.nn.common;

/**
 * Created by dkudryavtsev on 5/7/15.
 */
public class ResultVector implements IResult {
    private double[] data;

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }
}
