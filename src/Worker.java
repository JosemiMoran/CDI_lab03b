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

        int fraction = (int) (1 / (Math.pow(2 * f + 1, 2)));
        int red, blue, green;

        for (int j = startColumn; j < endColumn; j++) {
            for (int i = startRow; i < endRow; i++) {
                try {
                    int summationRed = 0, summationBlue = 0, summationGreen = 0;
                    for (int l = -f; l <= f; l++) {
                        for (int k = -f; k <= f; k++) {

                            x = i + k;
                            y = j + l;

                            if (x < 0) {
                                x = -x;
                            }
                            if (x > MyProblem.myMatrix.height) {
                                x = x - MyProblem.myMatrix.height;
                            }

                            if (y < 0) {
                                y = -y;
                            }
                            if (y > MyProblem.myMatrix.width) {
                                y = y - MyProblem.myMatrix.width;
                            }

                            try {
                                System.out.println(MyProblem.myMatrix.image.getRGB(y, x)&0xF0F0F0F0);
                                int pixel = MyProblem.myMatrix.image.getRGB(y, x)&0xF0F0F0F0; // getRGB(x,y)
                                int alpha = pixel & 0xff000000;
                                red = (pixel >> 16) & 0xff;
                                green = (pixel >> 8) & 0xff;
                                blue = (pixel) & 0xff;
                                summationRed += red;
                                summationBlue += blue;
                                summationGreen += green;
                            } catch (Exception e) {
                                System.out.println(e.getMessage() + " GetRGB " + Thread.currentThread().getName() + ": x: " + x + " y: " + y + " StartRow: " + startRow + " EndRow: " + endRow + " StartColumn: " + startColumn + " EndColumn: " + endColumn);
                            }
                        }
                    }
                    if (summationBlue > 255) summationBlue = 255;
                    if (summationGreen > 255) summationGreen = 255;
                    if (summationRed > 255) summationRed = 255;
                    int a = 0;
                    int pixel = (a << 24) | (summationRed << 16) | (summationGreen << 8) | summationBlue;
                    MyProblem.myMatrix.imageOut.setRGB(y, x, pixel &0xF0F0F0F0);
                    // int RGB = (* fraction);
                } catch (Exception e) {
                    System.out.println(e.getMessage() + " SetRGB(): " + Thread.currentThread().getName() + " i: " + i + " j: " + j + " StartRow: " + startRow + " EndRow: " + endRow + " StartColumn: " + startColumn + " EndColumn: " + endColumn);
                }
            }
        }

    }
}
