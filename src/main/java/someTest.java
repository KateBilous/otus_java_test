import com.sun.tools.javac.util.List;

public class someTest {

    public static void main(String[] args) {
        List<Integer> dig = List.of(1, -3, 6, -7);
        Integer sum = 0;
        for (int d : dig) {
            if (d < 0) {
                sum += d;
            }

        }
    }
}
