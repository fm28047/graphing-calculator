package math;

import java.util.ArrayList;

public class Expression {
    public ArrayList<Object> expression = new ArrayList<Object>();
    static final String[] SPECIAL_OPERATORS = {"+", "/", "^", "mod", "*", "-", "!"};
    static final Operators[] SPECIAL_OPERATORS2 = {Operators.ADD, Operators.DIVIDE, Operators.EXPONENT, Operators.MODULUS, Operators.MULTIPLY, Operators.SUBTRACT, Operators.FACTORIAL};

    private boolean degreesMode = false;

    // parses string to expression
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
                                expression.add(new Expression(s.substring(1, i), degreesMode));
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
                    boolean specialOperation = false;
                    try {
                        while ((!(c <= '9' && c >= '0') && c != '(') && i < s.length()-1 && !specialOperation) {
                            i++;
                            c = s.charAt(i);
                            for (String j : SPECIAL_OPERATORS)
                                if (s.substring(i).startsWith(j)) {
                                    i+=j.length();
                                    specialOperation = true;
                                    break;
                                }
                            if (s.substring(i).startsWith("pi") && i == 0) {
                                i += 2;
                                specialOperation = true;
                            }
                            else if (s.substring(i).startsWith("e") && i == 0) {
                                i += 1;
                                specialOperation = true;
                            }
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
                        case "abs":
                            expression.add(Operators.ABSOLUTE_VALUE);
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
                            expression.add(Special.E);
                            s = s.substring(i, s.length());
                            continue;
                        case "e":
                            expression.add(Special.E);
                            s = s.substring(i, s.length());
                            continue;
                        case "pi":
                            expression.add(Special.PI);
                            s = s.substring(i, s.length());
                            continue;
                        case "!":
                            expression.add(Operators.FACTORIAL);
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

    public Expression(String s, boolean usingDegrees) throws ParseError {
        this(s);
        degreesMode = usingDegrees;
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
            super("Invalid string to parse.");
        }
    }

    public class CalculationError extends Exception {
        public CalculationError() {
            super();
        }
    }

    public double eval(double valueOfX) throws CalculationError {
        /* order:
         * first, replace variables with their value
         * 1. brackets
         * 2. special functions like sine, sqrt, log, etc.
         * 3. exponents
         * 4. *, /, mod
         * 5. +, -
         */

        ArrayList<Object> solvedExpression = new ArrayList<Object>(expression);

        if (solvedExpression.size() == 0) return 0;

        // 0. replace variable value
        for (int i = 0; i < solvedExpression.size(); i++) {
            if (!(solvedExpression.get(i) instanceof MathVariable) && (solvedExpression.get(i) != Special.E) && (solvedExpression.get(i) != Special.PI)) continue;
            else if (solvedExpression.get(i) == Special.E) solvedExpression.set(i, Math.E);
            else if (solvedExpression.get(i) == Special.PI) solvedExpression.set(i, Math.PI);
            else solvedExpression.set(i, Double.valueOf(valueOfX));
        }

        // 1. brackets - eval other expressions
        for (int i = 0; i < solvedExpression.size(); i++) {
            if (!(solvedExpression.get(i) instanceof Expression)) continue;
            else solvedExpression.set(i, ((Expression) solvedExpression.get(i)).eval(valueOfX));
        }

        // 1.5. factorials
        for (int i = 0; i < solvedExpression.size(); i++) {
            if (solvedExpression.get(i) == Operators.FACTORIAL) {
                try {
                    solvedExpression.set(i-1, Operators.operate(Operators.FACTORIAL, (double) solvedExpression.get(i-1)));
                    solvedExpression.remove(i);
                    i--;
                } catch (Throwable e) {
                    throw new CalculationError();
                }
            }
        }

        // 2. special functions
        for (int i = 0; i < solvedExpression.size(); i++) {
            if (!(solvedExpression.get(i) instanceof Operators)) continue;
            boolean invalid = false;
            for (Operators o : SPECIAL_OPERATORS2) if (o.equals((Operators) solvedExpression.get(i))) invalid = true;
            if (invalid) continue;
            else {
                Operators operator = (Operators) solvedExpression.get(i);
                try {
                    Double solution = 0d;
                    if (Operators.argsRequired(operator) == 1) {
                        double calcNumber = (Double) solvedExpression.get(i+1);
                        if (degreesMode && Operators.isTrig(operator)) calcNumber = Math.toRadians(calcNumber);
                        solution = Operators.operate(operator, calcNumber);
                    }
                    else if (Operators.argsRequired(operator) == 2) solution = Operators.operate(operator, (Double) solvedExpression.get(i+1), (Double) solvedExpression.get(i+2));
                    solvedExpression.set(i, solution);
                } catch (Throwable e) {
                    throw new CalculationError();
                }
                for (int j = 0; j < Operators.argsRequired(operator); j++) solvedExpression.remove(i+1);
            }
        }

        // 2.5. replace subtractions with negative numbers when applicable
        // it does this by finding an operation to the left of a subtraction operation
        for (int i = 0; i < solvedExpression.size(); i++) {
            if (i == 0 || solvedExpression.get(i-1) instanceof Operators)
                if (solvedExpression.get(i) == Operators.SUBTRACT) {
                    solvedExpression.remove(i);
                    // get next double and multiply it by -1
                    boolean error = true;
                    for (int j = 0; j < solvedExpression.size(); j++) {
                        if (solvedExpression.get(j) instanceof Double) {
                            solvedExpression.set(j, ((Double) solvedExpression.get(j)) * -1);
                            i--;
                            error = false;
                            break;
                        }
                    }
                    if (error) throw new CalculationError();
                }
            
        }

        // 3. exponents
        for (int i = 0; i < solvedExpression.size(); i++) {
            if (solvedExpression.get(i).equals(Operators.EXPONENT)) {
                try {
                    Double solution = Operators.operate(Operators.EXPONENT, (Double) solvedExpression.get(i-1), (Double) solvedExpression.get(i+1));
                    solvedExpression.set(i, solution);
                    solvedExpression.remove(i+1);
                    solvedExpression.remove(i-1);
                    i--;
                } catch (Throwable e) {
                    throw new CalculationError();
                } 
            }
        }

        // 4. multiply, divide, mod
        for (int i = 0; i < solvedExpression.size(); i++) {

            // case 1: two numbers next to each other
            if (i+1 < solvedExpression.size() && (solvedExpression.get(i) instanceof Double) && (solvedExpression.get(i+1) instanceof Double)) {
                Double solution = Operators.operate(Operators.MULTIPLY, (Double) solvedExpression.get(i), (Double) solvedExpression.get(i+1));
                solvedExpression.set(i, solution);
                solvedExpression.remove(i+1);
                i--;
            }

            // case 2: multiplication signs
            else if (solvedExpression.get(i).equals(Operators.MULTIPLY) || solvedExpression.get(i).equals(Operators.DIVIDE) || solvedExpression.get(i).equals(Operators.MODULUS)) {
                try {
                    Double solution = Operators.operate((Operators) solvedExpression.get(i), (Double) solvedExpression.get(i-1), (Double) solvedExpression.get(i+1));
                    solvedExpression.set(i, solution);
                    solvedExpression.remove(i+1);
                    solvedExpression.remove(i-1);
                    i--;
                } catch (Throwable e) {
                    throw new CalculationError();
                } 
            }
        }

        // 5. add, subtract
        for (int i = 0; i < solvedExpression.size(); i++) {
            if (solvedExpression.get(i).equals(Operators.ADD) || solvedExpression.get(i).equals(Operators.SUBTRACT)) {
                try {
                    Double solution = Operators.operate((Operators) solvedExpression.get(i), (Double) solvedExpression.get(i-1), (Double) solvedExpression.get(i+1));
                    solvedExpression.set(i, solution);
                    solvedExpression.remove(i+1);
                    solvedExpression.remove(i-1);
                    i--;
                } catch (Throwable e) {
                    throw new CalculationError();
                }
            }
        }

        if (solvedExpression.size() != 1 || !(solvedExpression.get(0) instanceof Double)) {
            System.out.println(this.toStringDebug());
            throw new CalculationError();
        }
        // round to 10 decimal places
        double solution = (double) solvedExpression.get(0);
        if (Double.isFinite(solution)) {
            solution *= Math.pow(10, 10);
            solution = Math.round(solution);
            solution /= Math.pow(10, 10);
        }
        return solution;
    }

    public String toStringDebug() {
        ArrayList<Object> a = new ArrayList<Object>(expression);
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) instanceof Expression) a.set(i, ((Expression)a.get(i)).toStringDebug());
        }
        return a.toString();
    }
}