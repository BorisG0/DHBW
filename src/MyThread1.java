public class MyThread1 extends Thread{
    private int id;
    public MyThread1(int id){
        this.id = id;
    }

    public void run(){
        try{
            Thread.sleep((int) (Math.random() * 10000));
        }catch (Exception e) {}
        System.out.println("Hello World (ID = " + id + ")");
    }

    public static void main(String[] args) {
        Thread[] threads = new MyThread1[9];
        for(int i = 0; i < 9; i++) {
            Thread t = new MyThread1( i );
            t.start();
            threads[i] = t;
        }

        for(Thread t: threads){
            try {
                t.join();
            }catch (Exception e){
                System.out.println(e);
            }
        }

        System.out.println("alle fertig");
    }
}
