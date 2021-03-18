import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MyProblem {
    public static Matrix myMatrix;

    public static void main(String[] args) {
        System.out.println("Starting main");
        int numThreads = Integer.parseInt(args[0]);
        ArrayList<Thread> workerArrayList = new ArrayList<>(numThreads);
        myMatrix = new Matrix(args[1], numThreads);
        System.out.println("Height: " + myMatrix.image.getHeight());
        System.out.println("Width: " + myMatrix.image.getWidth());
        int operationSelected = Integer.parseInt(args[2]);
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new Worker(operationSelected), "Thread " + i);
            System.out.println("Creating: " + thread.getName());
            workerArrayList.add(thread); // Adding the thread into the arraylist
        }

        for (Thread thread : workerArrayList) {
            thread.start();
        }

        for (Thread thread : workerArrayList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            myMatrix.writeImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The program has ended");

    }
}
