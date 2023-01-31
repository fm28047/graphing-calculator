/*
 * Micah Friedman
 * Copyright (C) 2023
 */

package math;

public enum Operators {
    // basic
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    MODULUS,
    // other
    EXPONENT,
    INVEXP,
    LOG,
    FACTORIAL,
    ABSOLUTE_VALUE,
    // trig
    SINE,
    COSINE,
    TANGENT,
    ARCSIN,
    ARCCOS,
    ARCTAN,
    SECANT,
    COSECANT,
    COTANGENT,
    ARCSEC,
    ARCCSC,
    ARCCOT;

    public static Double operate(Operators type, double num1, double num2) {
        switch (type) {
            case ADD:
                return num1 + num2;
            case SUBTRACT:
                return operate(ADD, num1, num2 * -1);
            case MULTIPLY:
                return num1 * num2;
            case DIVIDE:
                return operate(MULTIPLY, num1, Math.pow(num2, -1));
            case MODULUS:
                return num1 % num2;
            case EXPONENT:
                return Math.pow(num1, num2);
            case INVEXP:
                return operate(EXPONENT, num2, Math.pow(num1, -1));
            case LOG:
                return Math.log(num2) / Math.log(num1);
            case SINE:
                return Math.sin(num1);
            case COSINE:
                return Math.cos(num1);
            case TANGENT:
                return Math.tan(num1);
            case ARCSIN:
                if (num1 > 1 || num1 < -1) return null;
                return Math.asin(num1);
            case ARCCOS:
                if (num1 > 1 || num1 < -1) return null;
                return Math.acos(num1);
            case ARCTAN:
                return Math.atan(num1);
            case SECANT:
                return Math.pow(Math.cos(num1), -1);
            case COSECANT:
                return Math.pow(Math.sin(num1), -1);
            case COTANGENT:
                return Math.pow(Math.tan(num1), -1);
            case ARCSEC:
                if (num1 < 1 && num1 > -1) return null;
                return Math.acos(1/num1);
            case ARCCSC:
                if (num1 < 1 && num1 > -1) return null;
                return Math.asin(1/num1);
            case ARCCOT:
                return Math.atan(1/num1);
            case FACTORIAL:
                return Gamma.gamma(num1+1);
            case ABSOLUTE_VALUE:
                return Math.abs(num1);
        }
        return 0d;
    }

    public static double operate(Operators type, double num1) {
        return operate(type, num1, 0);
    }

    public static int argsRequired(Operators type) {
        switch (type) {
            case ADD: case SUBTRACT: case MULTIPLY: case DIVIDE: case MODULUS: case EXPONENT: case INVEXP: case LOG: return 2;
            case SINE: case COSINE: case TANGENT: case ARCSIN: case ARCCOS: case ARCTAN: case SECANT: case COSECANT: case COTANGENT: case ARCSEC: case ARCCSC: case ARCCOT: case FACTORIAL: case ABSOLUTE_VALUE: return 1;
        }
        return 0;
    }

    public static String toString(Operators type) {
        switch (type) {
                case ADD:
                    return "+";
                case ARCCOS:
                    return "arccos";
                case ARCCOT:
                    return "arccot";
                case ARCCSC:
                    return "arccsc";
                case ARCSEC:
                    return "arcsec";
                case ARCSIN:
                    return "arcsin";
                case ARCTAN:
                    return "arctan";
                case COSECANT:
                    return "csc";
                case COSINE:
                    return "cos";
                case COTANGENT:
                    return "cot";
                case DIVIDE:
                    return "/";
                case EXPONENT:
                    return "^";
                case INVEXP:
                    return "root_";
                case LOG:
                    return "log_";
                case MODULUS:
                    return "mod";
                case MULTIPLY:
                    return "*";
                case SECANT:
                    return "sec";
                case SINE:
                    return "sin";
                case SUBTRACT:
                    return "-";
                case TANGENT:
                    return "tan";
                case FACTORIAL:
                    return "!";
                case ABSOLUTE_VALUE:
                    return "abs";
        }
        return null;
    }

    public static boolean isTrig(Operators operator) {
        switch (operator) {
            case SINE: case COSINE: case TANGENT: case ARCSIN: case ARCCOS: case ARCTAN: case COSECANT: case SECANT: case COTANGENT: case ARCCSC: case ARCSEC: case ARCCOT: return true;
            default: return false;
        }
    }
}