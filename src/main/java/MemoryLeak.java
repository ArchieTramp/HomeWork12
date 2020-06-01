/**
 * Задание заключалось в переполнении Heap
 * и переполнении Metaspace
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 1. -XX:+UseSerialGC -Xmx100m
 * 2. -XX:MaxMetaspaceSize=10m
 * Дабы ускорить утечку по учебному примеру создан цикл,
 * но еще обернут в parallel stream и
 * просто открываю потоки, которые не закрываю,
 * просто ради нагрузки.
 */

public class MemoryLeak {

    private static final int LOOP = 100_000_000;

    public static void main(String[] args) throws InterruptedException {


        List<String> list = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < LOOP; i++) {
            String str = "" + random.nextInt();
            list.add(str);

            Stream stream = list.parallelStream();
            stream.map(x -> x + "xx");
            System.out.println(list);
            Thread thread = new Thread();
            thread.start();
            if (i % 10 == 0) {
                Thread.sleep(100);
                list.remove(0);
            }

        }
        System.out.println(list.size());
    }


}
