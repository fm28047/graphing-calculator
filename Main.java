import math.*;

public class Main {
    public static void main(String[] args) {
        // System.out.println(Operators.operate(Operators.MODULUS, 5, 3));
        java.util.ArrayList<Object> a = new java.util.ArrayList<Object>();
        Object[] o = {
            5.5,
            Operators.ADD,
            3.5,
            Operators.SINE,
            Symbols.parentheses.LEFT,
            Math.PI,
            Symbols.parentheses.RIGHT
        };
        for (Object b : o) {
            a.add(b);
        }
        Expression e;
        try {
            e = new Expression("xmodroot_1.5(4^2)^csc(lnx)");
        } catch (Expression.ParseError e2) {
            e2.printStackTrace();
            return;
        }
        System.out.println(e.toString());
    }
}