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
        int numCols = (int) Math.ceil(Math.sqrt(NUM_DIVISIONS));
        int numRows = NUM_DIVISIONS / numCols;
        int startRow = 0, endRow = 0, startColumn = 0, endColumn = 0;
        int blockWidthSize = width / numCols;
        int blockHeightSize = height / numRows;
        int currentRow = 0;
        int currentCol = 0;

        for (int i = 0; i < NUM_DIVISIONS; i++) {
            if (currentCol >= numCols){
                currentCol =0;
                currentRow++;
            }
            if (i == id) {
                startRow = currentRow * blockHeightSize;
                endRow = (currentRow + 1) * blockHeightSize;
                startColumn = currentCol * blockWidthSize;
                endColumn = (currentCol + 1) * blockWidthSize;
            }

            currentCol++;
        }


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
