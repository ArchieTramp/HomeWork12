import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/** -XX:+UseSerialGC -Xmx10m
 * -XX:MaxPermSize -Xmx32m
 * MaxMetaspaceSize -Xmx32m*/

public class MemoryLeak {

    private static final int LOOP = 100_000_000;
    static final ArrayList lister = new ArrayList(100);

    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread();
//        thread.start();
        List<String> list  = new ArrayList<>();
        Stream stream = list.parallelStream();
        System.out.println(list);


        Random random = new Random();
        for (int i = 0; i < LOOP; i++) {
            String str = "" + random.nextInt();
            list.add(str);
            if (i % 10 == 0) {
                Thread.sleep(100);
                list.remove(0);
            }
            
        }
        System.out.println(list.size());
    }


}
