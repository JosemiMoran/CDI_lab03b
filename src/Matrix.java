import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Matrix {
    public BufferedImage image;
    public BufferedImage imageOut;
    private int NUM_DIVISIONS;
    public int height;
    public int width;
    ArrayList<Integer> coord = new ArrayList<Integer>();

    public Matrix(String file, int numThreads) {
        File fileIn = new File(file);
        try {
            image = ImageIO.read(fileIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        height = image.getHeight();
        width = image.getWidth();
        NUM_DIVISIONS = numThreads;
    }

    public Matrix(int heightOut, int widthOut, int typeOut) {
        imageOut = new BufferedImage(widthOut, heightOut, typeOut);
    }

    public ArrayList<Integer> divideByRows(String name) {
        coord.clear();
        int id = Integer.parseInt(name.split(" ")[1]);
        int rowSize = height / NUM_DIVISIONS;
        int startRow = id * rowSize;
        int endRow = (id + 1) * rowSize;
        int startColumn = 0;
        int endColumn = width;

        coord.add(startRow);
        coord.add(endRow);
        coord.add(startColumn);
        coord.add(endColumn);
        return coord;
    }

    public ArrayList<Integer> divideByColumns(String name) {
        coord.clear();
        int id = Integer.parseInt(name.split(" ")[1]);
        int colSize = width / NUM_DIVISIONS;
        int startRow = 0;
        int endRow = height;
        int startColumn = id * colSize;
        int endColumn = (id + 1) * colSize;
        coord.add(startRow);
        coord.add(endRow);
        coord.add(startColumn);
        coord.add(endColumn);
        return coord;
    }

    public ArrayList<Integer> divideByBlocks(String name) {
        coord.clear();
        int id = Integer.parseInt(name.split(" ")[1]);
        int blockWidthSize = width / NUM_DIVISIONS;
        int blockHeightSize = height / NUM_DIVISIONS;
        int startRow = id * blockHeightSize;
        int endRow = (id + 1) * blockHeightSize;
        int startColumn = id * blockWidthSize;
        int endColumn = (id + 1) * blockWidthSize;

        coord.add(startRow);
        coord.add(endRow - 1);
        coord.add(startColumn);
        coord.add(endColumn - 1);
        return coord;
    }

    public void writeImage() {
        File fileOut = new File("my_image.png");
        try {
            ImageIO.write(imageOut, "png", fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
