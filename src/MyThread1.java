public class MyThread1 extends Thread{
    private int id;
    static int runningThreats = 0;
    public MyThread1(int id){
        this.id = id;
    }

    public void run(){
        runningThreats++;
        try{
            Thread.sleep((int) (Math.random() * 1000));
        }catch (Exception e) {}
        System.out.println("Hello World (ID = " + id + ")");
        runningThreats--;
        if(runningThreats == 0){
            System.out.println("all threats stopped");
        }
    }

    public static void main(String[] args) {
        for(int i = 1; i < 10; i++) {
            Thread t = new MyThread1( i );
            t.start();
        }
    }
}
