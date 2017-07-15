package mine.java.table.drivens.java.java8;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2017/7/15.
 */
public class WayOfUseOptional {

    public static void main(String[] args) {
        Stream<String> names = Stream.of("Lamurudu", "Okanbi", "Oduduwa");
        Optional<String> longest = names
                .filter(name -> name.startsWith("L"))
                .findFirst();

        longest.ifPresent(name -> {
            String s = name.toUpperCase();
            System.out.println("The longest name is "+ s);
        });
    }


}
