import math.*;
import math.Expression.CalculationError;
import math.Expression.ParseError;

import java.util.Scanner;

public class Main {

    public static String input() {
        Scanner s = new Scanner(System.in);
        String a = s.nextLine();
        s.close();
        return a;
    }
    public static void main(String[] args) throws ParseError, CalculationError{
        Expression e = new Expression(input());
        System.out.println(e.eval(0)); // TODO fix -3(5^2 - 2(5-2) / 3 + 2)
    }
}