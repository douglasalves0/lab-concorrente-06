import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;

import java.util.ArrayList;

public class ScenarioBase {
    public static void main(String[] args) {
        
        System.out.println("Scenario Base of Lab06!");
        
        BlockingQueue<Task> q = new LinkedBlockingQueue<>();

        ScheduledExecutorService producerExecutor = Executors.newScheduledThreadPool(5);
        ExecutorService es = Executors.newFixedThreadPool(3);
        
        ArrayList<TaskProducer> arr = new ArrayList<TaskProducer>();
        for(int i=0;i<5;i++){
            TaskProducer tp = new TaskProducer(q, i);
            arr.add(tp);
            producerExecutor.scheduleAtFixedRate(tp, 5, 5, SECONDS);
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