import java.util.ArrayList;

public class Worker implements Runnable{
    public static Matrix myMatrix = MyProblem.myMatrix;
    private Matrix finalMatrix;
    public int startRow;
    private int startColumn;
    private ArrayList coord = new ArrayList<Integer>();
    public Worker(Matrix finalMatrix){
        finalMatrix = myMatrix;
    };

    @Override
    public void run() {
        System.out.println("Starting run: " + Thread.currentThread().getName());
        coord = myMatrix.divideByRows(Thread.currentThread().getName());
        medianFilter(coord);
    }

    private void medianFilter(ArrayList<Integer> coord) {
        //TODO median filter summation.

    }
}
