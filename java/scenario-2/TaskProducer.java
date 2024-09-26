import java.util.ArrayList;
import java.util.concurrent.*;

public class TaskProducer implements Runnable {
    private PriorityBlockingQueue<Task> q;
    ArrayList<Task> a;
    private int i = 0;
    private int id;
    public TaskProducer(PriorityBlockingQueue<Task> q, int id){
        this.q = q;
        this.id = id;
        a = new ArrayList<Task>();
    }
    @Override
    public void run(){
        Task t = new Task(i++, id);
        try{
            q.put(t);
            a.add(t);
        }catch(Exception e){}
    }
    public void showFinishedTasks(){
        System.out.println("Status of producer " + this.id + ":");
        long sum = 0, cnt = 0;
        for(Task t: a){
            if(t.ending == -1) continue;
            long time = (t.ending - t.initial);
            System.out.println("  * Task " + t.id + " executed in " + time + "ms");
            sum += time;
            cnt++;
        }
        if(cnt != 0) System.out.println("Time avg: " + (double)sum / (double)cnt + "ms");
        else System.out.println("No executed tasks :(");
        System.out.println("------------------------------------------------------------------");
    }
}