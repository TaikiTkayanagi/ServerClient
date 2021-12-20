package Monitor;

public class SampleRunnable implements Runnable {
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("HelloRunnable" + i);
        }
    }
}
