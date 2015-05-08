package java.org.daisci.nn.common;

/**
 * Created by dkudryavtsev on 5/7/15.
 */
public class DataVector2d implements IData{
    private double[][] data;
    private int rows;
    private int cols;

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
