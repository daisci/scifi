package java.org.daisci.nn.common.layer;

/**
 * Created by dkudryavtsev on 5/7/15.
 */
public interface ILayer2d extends ILayer{
    double[][] getActivity();

    double getActivity(int row, int col);
    double setActivity(int row, int col, double value);

    double[][] getWeight();

    double getWeight(int row, int col);
    double setWeight(int row, int col, double value);

    int getCols();

    int getRows();

}
