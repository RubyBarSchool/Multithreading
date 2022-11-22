package criticalandsynchronization;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        Thread thread1 = new Thread(incrementingThread);

        Thread thread2 = new Thread(decrementingThread);

        thread1.setName("IncrementingThread");
        thread2.setName("DecrementingThread");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("We currently have "+ inventoryCounter.getItems() + "items");
    }

    public static class DecrementingThread implements Runnable{
        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter){
            this.inventoryCounter = inventoryCounter;
        }


        @Override
        public void run() {
            for(int i=0 ;i<10000 ;i++){
                inventoryCounter.decrement();
            }
        }
    }

    public static class IncrementingThread extends  Thread{
        private InventoryCounter inventoryCounter;

        public IncrementingThread(InventoryCounter inventoryCounter){
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {

            for(int i=0 ;i<10000 ;i++){
                inventoryCounter.increment();
            }
        }
    }


    private static class InventoryCounter{
        private int items = 0;

        public synchronized void increment(){
            System.out.println(Thread.currentThread().getName());
            items++;
        }

        public synchronized void decrement(){
            System.out.println(Thread.currentThread().getName());
            items--;
        }

        public synchronized int getItems(){
            return items;
        }
    }

    private static class InventoryCounterNew{
        private int items = 0;

        Object object = new Object();

        public void increment(){
            synchronized (object){
                System.out.println(Thread.currentThread().getName());
                items++;
            }
        }

        public void decrement(){
            synchronized (object){
                System.out.println(Thread.currentThread().getName());
                items--;
            }
        }

        public int getItems(){
            synchronized (object){
                return items;
            }
        }
    }

}
