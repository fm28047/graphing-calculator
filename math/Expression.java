package math;

import java.util.ArrayList;

public class Expression {
    private ArrayList<Object> expression = new ArrayList<Object>();

    public Expression(String s) {
        String testString;
        for (int index = 1; s.length() > 0; index++) {
            testString = s.substring(0, index);
            try {
                Double.parseDouble(testString);
            } catch (NumberFormatException e) {
                // if it gives error after a doing it right at least once, it must be a number
                if (index != 1) {
                    expression.add(s.substring(0, index-1));
                    s = s.substring(index-1, s.length());
                }
                else {

                }
            }
        }
    }

    public Expression(ArrayList<Object> e) {
        this.expression = new ArrayList<Object>(e);
    }

    public String toString() {
        String result = "";
        for (Object o : expression) {
            if (o instanceof Double)
                result += o;

            else if (o instanceof Symbols.parentheses)
                switch ((Symbols.parentheses) o) {
                    case LEFT: result += "("; break;
                    case RIGHT: result += ")"; break;
                }

            else if (o instanceof MathVariable)
                result += ((MathVariable) o).getName();

            else if (o instanceof Operators)
                result += Operators.toString((Operators) o);
        }
        return result;
    }
}
