import java.util.Random;
import java.time.*;

public class Task implements Comparable {
    long id;
    long initial;
    long ending;
    long p;
    public Task(long id, long p) {
        this.id = id;
        this.initial = Instant.now().toEpochMilli();
        this.ending = -1;
        this.p = p;
    }

    public void execute() {
        try {
            // generating a number between 1000 and 15000
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.ending = Instant.now().toEpochMilli();
    }
    
    @Override
    public int compareTo(Object arg0) {
        Task oth = (Task) (arg0);
        if(p < oth.p) return -1;
        else if(p == oth.p) return 0;
        else return 1;
    }
}
