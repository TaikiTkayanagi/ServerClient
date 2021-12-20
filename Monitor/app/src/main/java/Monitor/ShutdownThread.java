package Monitor;

public class ShutdownThread extends Thread {
    public Thread t1;

    public ShutdownThread(Thread t1){
        this.t1 = t1;
    }

    public void run(){
        AppFlag.flag = false;
        try{
            t1.join();
            System.out.println("スレッドID:" + this.getId());
            System.out.println("スレッドID:" + this.getName());
            System.out.println("call ShutdownHook");
        } catch(Exception e){
        }
    }
}
