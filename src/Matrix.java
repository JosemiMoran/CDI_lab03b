import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Matrix {
    public BufferedImage image;
    private int NUM_DIVISIONS;
    public int height;
    public int width;
    ArrayList<Integer> coord = new ArrayList<Integer>();

    public Matrix(String file, int numThreads) {
        readImage(file);
        height = image.getHeight();
        width = image.getWidth();
        NUM_DIVISIONS = numThreads;
    }

    public Matrix(int height, int width, int type) {
        this.height = height;
        this.width = width;
        image = new BufferedImage(height, width, type);
    }

    private void readImage(String file) {
        File fileIn = new File(file);
        try {
            image = ImageIO.read(fileIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Type: " + image.getType());
    }

    public ArrayList divideByRows(String name) {
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

    public void divideByColumns(int startColumn) {
        int colSize = width / NUM_DIVISIONS;

    }

    public void divideByBlocks(int startRow, int startColumn) {
        int blockWidth = width / NUM_DIVISIONS;
        int blockHeight = height / NUM_DIVISIONS;

    }

    public void writeImage() {
        File fileOut = new File("my_image.png");
        try {
            ImageIO.write(image, "png", fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
