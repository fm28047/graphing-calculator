/*
 * Micah Friedman
 * Copyright (C) 2023
 */

package calculator;

import java.util.ArrayList;

import console.*;
import math.Expression;

public class table {
    public static double deltaX = 1, start = -3;
    public static int length = 7;
    public static void calc() {
        console.resetCursor(true);
        
        System.out.println("Table Calculator");
        System.out.println("Type "+colors.CYAN+"exit"+colors.RESET+" to go back to the menu.");
        System.out.println("Type "+colors.CYAN+"settings"+colors.RESET+" to change the table properties.");
        System.out.print("| f(x) = ");
        String input = "", previousInput = "";
        while (true) {
            Expression expression;
            try {
                expression = new Expression("");
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            try {
                previousInput = input;
                input = console.input();
                if (input.equalsIgnoreCase("exit")) break;
                expression = new Expression(input, console.degrees);
                expression.eval(0);
            } catch (Exception e) {
                if (input.equalsIgnoreCase("settings")) {
                    tableSettings();
                    return;
                }
                input = "Syntax Error";
            }
            input = "f(x) = " + input;
            System.out.print("                                        ");
            System.out.print(console.setCursor(5, 1) + "----------------");
            {
                String a = "";
                for (int i = 0; i < previousInput.length(); i++) a += " ";
                System.out.print(console.setCursor(6, 1)+a+console.setCursor(6, 1));
            }
            System.out.print(input);

            if (input.equals("Syntax Error")) {
                System.out.print(colors.RESET);
                String clear = "";
                for (int i = 0; i < input.length(); i++) clear += " ";
                System.out.print(console.setCursor(4, 10)+clear+console.setCursor(4, 10));
                continue;
            }

            // get solutions
            ArrayList<Double> inputs = new ArrayList<Double>();
            ArrayList<Double> solutions = new ArrayList<Double>();

            int longestInput = 0;

            for (int i = 0; i < length; i++) {
                double xInput = start + (i * deltaX);
                xInput *= Math.pow(10, 10);
                xInput = Math.round(xInput);
                xInput /= Math.pow(10, 10);
                inputs.add(xInput);
                if (Double.toString(xInput).length() > longestInput) longestInput = Double.toString(xInput).length();
                try {
                    solutions.add(expression.eval(xInput));
                } catch (Exception e) {
                    solutions.add(0d);
                }
            }

            System.out.print(console.setCursor(8, 1) + " x");
            System.out.print(console.setCursor(8, longestInput + 1) + " | f(x)");

            // draw empty table
            for (int i = 1; i <= length; i++) {
                System.out.print(console.setCursor(8 + i, 1) + inputs.get(i-1));
                System.out.print(console.setCursor(8 + i, longestInput + 1) + " | " + solutions.get(i-1) + "                                                      ");
            }


            // end drawing table
            System.out.print(colors.RESET);
            String clear = "";
            for (int i = 0; i < input.length(); i++) clear += " ";
            System.out.print(console.setCursor(4, 10)+clear+console.setCursor(4, 10));
        }
        console.mainMenu();
    }

    public static void tableSettings() {
        console.resetCursor(true);
        Cycle settings = new Cycle();
        settings.add("Table Start: " + start, "The first x value of the table.", new Runnable() {public void run() {
            System.out.print(console.setCursor(3, 14));
            System.out.print("                                                                                         ");
            System.out.print(console.setCursor(3, 14));
            try {
                double input = Double.parseDouble(console.input());
                start = input;
            } catch (Exception e) {}
            tableSettings();
        }});
        settings.add("Table Change: " + deltaX, "The difference between two adjacent x values.", new Runnable() {public void run() {
            System.out.print(console.setCursor(4, 15));
            System.out.print("                                                                                         ");
            System.out.print(console.setCursor(4, 15));
            try {
                double input = Double.parseDouble(console.input());
                deltaX = input > 0 ? input : deltaX;
            } catch (Exception e) {}
            tableSettings();
        }});
        settings.add("Table Length: " + length, "The amount of rows visible on the table.", new Runnable() {public void run() {
            System.out.print(console.setCursor(5, 15));
            System.out.print("                                                                                         ");
            System.out.print(console.setCursor(5, 15));
            try {
                int input = Integer.parseInt(console.input());
                length = input > 0 ? input : length;
            } catch (Exception e) {}
            tableSettings();
        }});
        settings.add("Back", "", new Runnable() {public void run() {
            calc();
        }});
        settings.start(2, "Table Settings\n--------------");
    }
}
