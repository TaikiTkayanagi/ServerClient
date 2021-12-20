package Monitor;

public class SampleThread1 extends Thread {
    public void run(){
        int i = 0;
        while(AppFlag.flag){
            try{
                Thread.sleep(10000L);
                System.out.println("HelloWorld" + i);
                i++;
            } catch(Exception e){

            } finally{
            }
        }
        System.out.println("スレッド名:" + this.getName());
        System.out.println("スレッドID:" + this.getId());
    }
}
