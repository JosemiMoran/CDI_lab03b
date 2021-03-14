import java.util.ArrayList;
import java.util.Arrays;

public class Worker implements Runnable {

    public Worker() {
    }


    @Override
    public void run() {
        try {
            System.out.println("Starting run: " + Thread.currentThread().getName());
            ArrayList<Integer> coordinates = MyProblem.myMatrix.divideByRows(Thread.currentThread().getName());
            medianFilter(coordinates);
        } catch (Exception e) {
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }

    }

    private void medianFilter(ArrayList<Integer> coordinates) {
        int x = 0, y = 0;
        int startRow = coordinates.get(0);
        int endRow = coordinates.get(1);
        int startColumn = coordinates.get(2);
        int endColumn = coordinates.get(3);
        int f = 10;

        //System.out.println("Thread: " + Thread.currentThread().getName() + " Start Row: " + startRow + ", End Row: " + endRow + " Start Column: " + startColumn + ", End Column: " + endColumn);
        int fraction = (int) (1 / (Math.pow(2 * f + 1, 2)));
        for (int i = startRow; i < endRow; i++) {
            for (int j = startColumn; j < endColumn; j++) {
//                System.out.println(Thread.currentThread().getName() + " i: " + i + " j: " + j + " StartRow: " + startRow + " EndRow: " + endRow + " StartColumn: " + startColumn + " EndColumn: " + endColumn);
                try {
                    MyProblem.finalMatrix.imageOut.setRGB(i, j, MyProblem.myMatrix.image.getRGB(i,j));
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " " + Thread.currentThread().getName() + " i: " + i + " j: " + j + " StartRow: " + startRow + " EndRow: " + endRow + " StartColumn: " + startColumn + " EndColumn: " + endColumn);
                }
            }
        }

    }
}
//                int summation = 0;
//                for (int k = -f; k <= f; k++) {
//                    for (int l = -f; l <= f; l++) {
//                        x = i + k;
//                        y = j + l;
//
//                        if (x < 0) {
//                            x = - x;
//                        } else if (x < startRow) {
//                            x = startRow + 1;
//                        } else if (x > endRow) {
//                            x = endRow - 1;
//                        }
//
//                        if (y < 0) {
//                            y = - y;
//                        } else if (y < startColumn) {
//                            y = startColumn + 1;
//                        } else if (y > endColumn) {
//                            y = endColumn - 1;
//                        }
//
//                        try {
//                            summation += MyProblem.myMatrix.image.getRGB(x, y);
//                        } catch (Exception e) {
//                            System.out.println(e.getMessage() + " " + Thread.currentThread().getName() + " x: " + x + " y: " + y + " StartRow: " + startRow + " EndRow: " + endRow + " StartColumn: " + startColumn + " EndColumn: " + endColumn);
//                        }
//
//                    }
//                }
//                int RGB = (summation * fraction);