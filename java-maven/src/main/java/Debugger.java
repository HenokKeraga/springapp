import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Debugger {

  public static void main(String[] args) {
    int[] result= IntStream.of(10,20,55,30,40,50,1)
            .flatMap(t-> IntStream.of(t))
            .map(d->d*2)
            .distinct()
            .sorted()
            .toArray();
// creating an Integer stream
    Stream<Integer>
            s = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    // using Collectors partitioningBy()
    // method to split the stream of elements into
    // 2 parts, greater than 3 and less than 3.
    Map<Boolean, List<Integer>>
            map = s.collect(
            Collectors.partitioningBy(num -> num > 3));

    // Displaying the result as a map
    // true if greater than 3, false otherwise
    System.out.println("Elements in stream "
            + "partitioned by "
            + "less than equal to 3: \n"
            + map);


    
    Map<Boolean, List<Integer>> collect = Stream.of(10, 20, 55, 30, 40, 50, 1).collect(Collectors.partitioningBy(i -> i.getClass().equals(Integer.class)));
    System.out.println("*******************");
    System.out.println(collect);
    var integers = collect.get(true);
    System.out.println(integers);
  }

}
