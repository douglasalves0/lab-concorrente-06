import java.util.concurrent.*;


public class Node implements Runnable {
    private BlockingQueue<Task> q;

    public Node(BlockingQueue<Task> q) {
        this.q = q;
    }

    @Override
    public void run() {
        while(true) {
            try{
                q.take().execute();
            }catch (Exception e) {}
        }
    }
}