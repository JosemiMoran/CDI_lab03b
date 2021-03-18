import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Matrix {
    public BufferedImage image;
    public BufferedImage imageOut;

    private int NUM_DIVISIONS;
    private int height;
    private int width;

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

        imageOut = new BufferedImage(width, height, image.getType());

    }

    public ArrayList<Integer> divideByRows(String name) {
        ArrayList<Integer> coord = new ArrayList<Integer>();
        int id = Integer.parseInt(name.split(" ")[1]);
        int rowSize = height / NUM_DIVISIONS;
        int startRow = id * rowSize;
        int endRow = (id + 1) * rowSize;
        int startColumn = 0;
        int endColumn = width;

        coord.add(startRow);
        if (id == NUM_DIVISIONS - 1) coord.add(height);
        else coord.add(endRow);
        coord.add(startColumn);
        coord.add(endColumn);
        return coord;
    }

    public ArrayList<Integer> divideByColumns(String name) {
        ArrayList<Integer> coord = new ArrayList<Integer>();
        int id = Integer.parseInt(name.split(" ")[1]);
        int colSize = width / NUM_DIVISIONS;
        int startRow = 0;
        int endRow = height;
        int startColumn = id * colSize;
        int endColumn = (id + 1) * colSize;
        coord.add(startRow);
        coord.add(endRow);
        coord.add(startColumn);
        if (id == NUM_DIVISIONS - 1) coord.add(width);
        else coord.add(endColumn);
        return coord;
    }

    public ArrayList<Integer> divideByBlocks(String name) {
        ArrayList<Integer> coord = new ArrayList<Integer>();
        int id = Integer.parseInt(name.split(" ")[1]);
        int blockWidthSize = width / NUM_DIVISIONS;
        int blockHeightSize = height / NUM_DIVISIONS;
        int startRow = id * blockHeightSize;
        int endRow = (id + 1) * blockHeightSize;
        int startColumn = id * blockWidthSize;
        int endColumn = (id + 1) * blockWidthSize;



        coord.add(startRow);
        if (id == NUM_DIVISIONS - 1) coord.add(height);
        else coord.add(endRow);
        coord.add(startColumn);
        if (id == NUM_DIVISIONS - 1) coord.add(width);
        else coord.add(endColumn);
        return coord;
    }

    public void writeImage() throws IOException {
        File fileOut = new File("OutputImage.png");
        try {
            ImageIO.write(imageOut, "png", fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
