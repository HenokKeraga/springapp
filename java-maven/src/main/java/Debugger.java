import java.util.stream.IntStream;

public class Debugger {

  public static void main(String[] args) {
    int[] result= IntStream.of(10,20,55,30,40,50,1)
            .flatMap(t-> IntStream.of(t))
            .map(d->d*2)
            .distinct()
            .sorted()
            .toArray();
  }

}
