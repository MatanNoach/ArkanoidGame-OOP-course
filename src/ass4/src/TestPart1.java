import java.util.Map;
import java.util.TreeMap;

//ID:316441534
public class TestPart1 {
    public static void main(String[] args) throws Exception {
        Var v1 = new Var("y");
        Var v2 = new Var("x");
        Num n1 = new Num(5.5);
        Expression e = new Minus(new Plus(v1, n1), new Plus(n1, v2));
        Expression e2 = new Minus(v1, n1);
        Map<String, Double> map = new TreeMap<>();
        map.put(v1.getVar(), (double) 25);
        map.put(v2.getVar(), (double) 50);
        System.out.println(e.evaluate(map));
        System.out.println(e2.evaluate(map));
        //trying to fork again
    }
}
