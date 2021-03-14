import java.util.ArrayList;

public class MyProblem {
    public static Matrix myMatrix;
    public static  Matrix finalMatrix;
    public static void main(String[] args) {
        System.out.println("Starting main");
        int numThreads = Integer.parseInt(args[0]);
        ArrayList<Thread> workerArrayList = new ArrayList<>(numThreads);
        try {
            myMatrix = new Matrix("image6.jpg", numThreads);
        }catch (Exception e){
            e.getMessage();
        }
        finalMatrix = new Matrix(myMatrix.height , myMatrix.width , myMatrix.image.getType());
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new Worker(), "Thread " + i);
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
        finalMatrix.writeImage();
        System.out.println("The program has ended");

    }
}
