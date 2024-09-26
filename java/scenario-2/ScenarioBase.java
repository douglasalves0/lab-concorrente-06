import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;

import java.util.ArrayList;

public class ScenarioBase {
    public static void main(String[] args) {
        
        System.out.println("Scenario Base of Lab06!");
        
        PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<>(20);

        ScheduledExecutorService producerExecutor = Executors.newScheduledThreadPool(3);
        ExecutorService es = Executors.newFixedThreadPool(3);
        
        ArrayList<TaskProducer> arr = new ArrayList<TaskProducer>();
        int tempos[] = {13, 7, 3};
        for(int i=0;i<3;i++){
            TaskProducer tp = new TaskProducer(q, i);
            arr.add(tp);
            producerExecutor.scheduleAtFixedRate(tp, tempos[i], tempos[i], SECONDS);
        }
        
        for(int i = 0; i < 3; i++) {
            Node n = new Node(q);
            es.execute(n);
        }
        
         while(true){
            System.out.println();
           for(TaskProducer tp: arr) tp.showFinishedTasks();
           try{ Thread.sleep(5000); }catch(Exception e){}
        }

    }
}