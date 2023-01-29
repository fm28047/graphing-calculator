/*
 * Micah Friedman
 * Copyright (C) 2023
 */

package calculator;

import console.*;
import math.Expression;

public class scientific {
    public static void calc() {
        console.resetCursor(true);
        Expression expression;
        String solution, input = "";
        System.out.println("Scientific Calculator");
        System.out.println("Type "+colors.CYAN+"exit"+colors.RESET+" to go back to the menu.");
        System.out.print("| ");
        while (true) {
            try {
                input = console.input();
                if (input.equalsIgnoreCase("exit")) break;
                expression = new Expression(input, console.degrees);
                solution = Double.toString(expression.eval(0));
            } catch (Exception e) {
                solution = "Syntax Error";
            }
            System.out.print("                                        ");
            System.out.print(console.setCursor(4, 1));
            System.out.print(solution);
            String clear = "";
            for (int i = 0; i < input.length(); i++) clear += " ";
            System.out.print(console.setCursor(3, 3)+clear+console.setCursor(3, 3));
        }
        console.mainMenu();
    }
}
