import java.util.Random;
import java.time.*;

public class Task {
    long id;
    long initial;
    long ending;

    public Task(long id) {
        this.id = id;
        this.initial = Instant.now().toEpochMilli();
        this.ending = -1;
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
}
