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
    // trig
    SINE,
    COSINE,
    TANGENT,
    ARCSIN,
    ARCCOS,
    ARCTAN,
    SECANT,
    COSECANT,
    COTANGENT;

    public static double operate(Operators type, double num1, double num2) {
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
                return operate(EXPONENT, num1, Math.pow(num2, -1));
            case LOG:
                return Math.log(num1) / Math.log(num2);
            case SINE:
                return Math.sin(num1);
            case COSINE:
                return Math.cos(num1);
            case TANGENT:
                return Math.tan(num1);
            case ARCSIN:
                return Math.asin(num1);
            case ARCCOS:
                return Math.acos(num1);
            case ARCTAN:
                return Math.atan(num1);
            case SECANT:
                return Math.pow(Math.cos(num1), -1);
            case COSECANT:
                return Math.pow(Math.sin(num1), -1);
            case COTANGENT:
                return Math.pow(Math.tan(num1), -1);
            default:
                return 0;
        }
    }

    public static int argsRequired(Operators type) {
        switch (type) {
            case ADD: case SUBTRACT: case MULTIPLY: case DIVIDE: case MODULUS: case EXPONENT: case INVEXP: case LOG: return 2;
            case SINE: case COSINE: case TANGENT: case ARCSIN: case ARCCOS: case ARCTAN: case SECANT: case COSECANT: case COTANGENT: return 1;
            default: return 0;
        }
    }
}