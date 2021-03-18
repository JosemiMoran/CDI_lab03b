import java.util.ArrayList;
import java.util.Arrays;

public class Worker implements Runnable {
    int operation;

    public Worker(int operation) {
        this.operation = operation;
    }

    @Override
    public void run() {
        try {
            System.out.println("Starting run: " + Thread.currentThread().getName());
            ArrayList<Integer> coordinates;
            if (operation == 1) {
                coordinates = MyProblem.myMatrix.divideByRows(Thread.currentThread().getName());
            } else if (operation == 2) {
                coordinates = MyProblem.myMatrix.divideByColumns(Thread.currentThread().getName());
            } else {
                coordinates = MyProblem.myMatrix.divideByBlocks(Thread.currentThread().getName());

            }
            medianFilter(coordinates);
            System.out.println("Ending run: " + Thread.currentThread().getName());
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

        int den = (int) Math.pow(2 * f + 1, 2);
        for (int i = startColumn; i < endColumn; i++) {
            for (int j = startRow; j < endRow; j++) {
                int summation = 0;
                for (int k = -f; k < f; k++) {
                    for (int l = -f; l < f; l++) {
                        x = j + k;
                        y = i + l;

                        if (x >= endRow) x = endRow - 1;
                        if (y >= endColumn) y = endColumn - 1;
                        if (x < 0) x = -x;
                        if (y < 0) y = -y;
                        try {
                            summation += MyProblem.myMatrix.image.getRGB(y, x);
                        } catch (Exception e) {
                            System.out.println("Column=" + i + " Row=" + j + " l= " + l + " k= " + k + " Column+l=" + y + " Row+k= " + x + " StartRow: " + startRow + " StartColumn: " + startColumn + " EndRow: " + endRow + " EndColumn: " + endColumn);
                        }
                    }
                }
                try {
                    MyProblem.myMatrix.imageOut.setRGB(i, j, (int) (Math.floor(summation / (double) den * 100) / 100));
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " " + Thread.currentThread().getName() + " Column: " + i + " Row: " + j + " StartRow: " + startRow + " EndRow: " + endRow + " StartColumn: " + startColumn + " EndColumn: " + endColumn);
                }
            }
        }
    }
}