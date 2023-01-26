package math;

import java.util.ArrayList;

public class Expression {
    private ArrayList<Object> expression = new ArrayList<Object>();

    public Expression(String e) {
        for (int index = 1; e.length() > 0; index++) {
            
        }
    }

    public Expression(ArrayList<Object> e) {
        this.expression = new ArrayList<Object>(e);
    }

    public String toString() {
        String result = "";
        for (Object o : expression) {
            if (o instanceof Double) result += o;

            else if (o instanceof Symbols.parentheses)
                switch ((Symbols.parentheses) o) {
                    case LEFT: result += "("; break;
                    case RIGHT: result += ")"; break;
                }

            else if (o instanceof Symbols.variables)
                switch ((Symbols.variables) o) {
                    case X: result += "x"; break;
                }

            else if (o instanceof Operators) switch ((Operators) o) {
                case ADD:
                    result += "+";
                    break;
                case ARCCOS:
                    result += "arccos";
                    break;
                case ARCSIN:
                    result += "arcsin";
                    break;
                case ARCTAN:
                    result += "arctan";
                    break;
                case COSECANT:
                    result += "csc";
                    break;
                case COSINE:
                    result += "cos";
                    break;
                case COTANGENT:
                    result += "cot";
                    break;
                case DIVIDE:
                    result += "/";
                    break;
                case EXPONENT:
                    result += "^";
                    break;
                case INVEXP:
                    result += "root_";
                    break;
                case LOG:
                    result += "log_";
                    break;
                case MODULUS:
                    result += "mod";
                    break;
                case MULTIPLY:
                    result += "*";
                    break;
                case SECANT:
                    result += "sec";
                    break;
                case SINE:
                    result += "sin";
                    break;
                case SUBTRACT:
                    result += "-";
                    break;
                case TANGENT:
                    result += "tan";
                    break;
            };
        }
        return result;
    }
}
