package math;

import java.util.ArrayList;

public class Expression {
    private ArrayList<Object> expression = new ArrayList<Object>();

    public Expression(String s) throws ParseError {
        s = s.toLowerCase().replace(" ", "");
        String testString;
        for (int index = 1; s.length() > 0;) {
            try {
                testString = s.substring(0, index);
            }
            catch (StringIndexOutOfBoundsException e) {
                expression.add(Double.parseDouble(s));
                break;
            } 
            try {
                Double.parseDouble(testString);
                index++;
            } catch (NumberFormatException e) {
                // case 1: if it gives error after a doing it right at least once, it must be a number
                // removes the number from the beginning of the string
                if (index != 1) {
                    expression.add(Double.parseDouble(s.substring(0, index-1)));
                    s = s.substring(index-1, s.length());
                    index = 1;
                    continue;
                }

                // case 2: it could be a parentheses
                // in this case a new expression is created
                else if (s.charAt(0) == '(') {
                    int parenthesesLevel = 0;
                    try {
                        for (int i = 0; true; i++) {
                            if (s.charAt(i) == '(') parenthesesLevel++;
                            else if (s.charAt(i) == ')') parenthesesLevel--;
                            if (parenthesesLevel == 0) {
                                expression.add(new Expression(s.substring(1, i)));
                                s = s.substring(i+1);
                                break;
                            }
                        }
                    } catch (StringIndexOutOfBoundsException e2) {
                        throw new ParseError();
                    }
                    continue;
                }

                // case 3: it is an operation
                else {
                    int i = -1;
                    char c = '\u0000';
                    try {
                        while ((!(c <= '9' && c >= '0') && c != '(') && i < s.length()-1) {
                            i++;
                            c = s.charAt(i);
                        }
                    } catch (StringIndexOutOfBoundsException e2) {
                        throw new ParseError();
                    }
                    String operation = s.substring(0, i);
                    switch (operation) {
                        case "+":
                            expression.add(Operators.ADD);
                            s = s.substring(i, s.length());
                            continue;
                        case "arccos":
                            expression.add(Operators.ARCCOS);
                            s = s.substring(i, s.length());
                            continue;
                        case "arccot":
                            expression.add(Operators.ARCCOT);
                            s = s.substring(i, s.length());
                            continue;
                        case "arccsc":
                            expression.add(Operators.ARCCSC);
                            s = s.substring(i, s.length());
                            continue;
                        case "arcsec":
                            expression.add(Operators.ARCSEC);
                            s = s.substring(i, s.length());
                            continue;
                        case "arcsin":
                            expression.add(Operators.ARCSIN);
                            s = s.substring(i, s.length());
                            continue;
                        case "arctan":
                            expression.add(Operators.ARCTAN);
                            s = s.substring(i, s.length());
                            continue;
                        case "csc":
                            expression.add(Operators.COSECANT);
                            s = s.substring(i, s.length());
                            continue;
                        case "cos":
                            expression.add(Operators.COSINE);
                            s = s.substring(i, s.length());
                            continue;
                        case "cot":
                            expression.add(Operators.COTANGENT);
                            s = s.substring(i, s.length());
                            continue;
                        case "/":
                            expression.add(Operators.DIVIDE);
                            s = s.substring(i, s.length());
                            continue;
                        case "^":
                            expression.add(Operators.EXPONENT);
                            s = s.substring(i, s.length());
                            continue;
                        case "root_":
                            expression.add(Operators.INVEXP);
                            s = s.substring(i, s.length());
                            continue;
                        case "log_":
                            expression.add(Operators.LOG);
                            s = s.substring(i, s.length());
                            continue;
                        case "mod":
                            expression.add(Operators.MODULUS);
                            s = s.substring(i, s.length());
                            continue;
                        case "*":
                            expression.add(Operators.MULTIPLY);
                            s = s.substring(i, s.length());
                            continue;
                        case "sec":
                            expression.add(Operators.SECANT);
                            s = s.substring(i, s.length());
                            continue;
                        case "sin":
                            expression.add(Operators.SINE);
                            s = s.substring(i, s.length());
                            continue;
                        case "-":
                            expression.add(Operators.SUBTRACT);
                            s = s.substring(i, s.length());
                            continue;
                        case "tan":
                            expression.add(Operators.TANGENT);
                            s = s.substring(i, s.length());
                            continue;
                        case "sqrt":
                            expression.add(Operators.INVEXP);
                            expression.add(2d);
                            s = s.substring(i, s.length());
                            continue;
                        case "cbrt":
                            expression.add(Operators.INVEXP);
                            expression.add(3d);
                            s = s.substring(i, s.length());
                            continue;
                        case "log":
                            expression.add(Operators.LOG);
                            expression.add(10d);
                            s = s.substring(i, s.length());
                            continue;
                        case "ln":
                            expression.add(Operators.LOG);
                            expression.add(Math.E);
                            s = s.substring(i, s.length());
                            continue;
                        case "%":
                            expression.add(Operators.MULTIPLY);
                            expression.add(0.01d);
                            s = s.substring(i, s.length());
                            continue;
                        case "e":
                            expression.add(Math.E);
                            s = s.substring(i, s.length());
                            continue;
                        case "pi":
                            expression.add(Math.PI);
                            s = s.substring(i, s.length());
                            continue;
                    }

                    // case 4: it is a variable
                    if (s.startsWith("x")) {
                        expression.add(new MathVariable("x"));
                        s = s.substring(1);
                        continue;
                    }
                }

                // case 5: it is none of those things
                throw new ParseError();
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

            else if (o instanceof MathVariable)
                result += ((MathVariable) o).getName();

            else if (o instanceof Operators)
                result += Operators.toString((Operators) o);
            
            else if (o instanceof Expression)
                result += "("+((Expression) o).toString()+")";
        }
        return result;
    }

    public class ParseError extends Exception {
        public ParseError() {
            super("Invalid string.");
        }
    }
}
