package calculator;

import console.*;
import math.Expression;

public class graphing {
    static double left = -5, bottom = -5, right = 6, top = 6;
    static int graphWidth = 41, graphHeight = 11;
    private final static int HIGHEST_GRAPH_POINT = 8;
    public static void calc() {
        console.resetCursor(true);
        System.out.println("Graphing Calculator");
        System.out.println("Type "+colors.CYAN+"exit"+colors.RESET+" to go back to the menu.");
        System.out.println("Type "+colors.CYAN+"settings"+colors.RESET+" to change the graph properties.");
        System.out.print("| y = ");
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
            } catch (Exception e) {
                if (input.equalsIgnoreCase("settings")) {
                    graphSettings();
                    return;
                }
                input = "Syntax Error";
            }
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
                System.out.print(console.setCursor(4, 7)+clear+console.setCursor(4, 7));
                continue;
            }

            // draw graph

            // 0. draw background
            System.out.print(colors.BG_CYAN + console.setCursor(HIGHEST_GRAPH_POINT, 1));
            String background = "";
            for (int i = 0; i < graphHeight; i++) {
                for (int j = 0; j < graphWidth; j++)
                    System.out.print(" ");
                System.out.println();
            }
            System.out.print(background);
            // 1. draw axes

            // test if axes should be drawn
            boolean drawXAxis, drawYAxis;
            drawYAxis = !(left < 0 && right < 0 || left > 0 && right > 0);
            drawXAxis = !(top < 0 && bottom < 0 || bottom > 0 && top > 0);

            if (drawXAxis) {
                double top2, center, percentage;
                top2 = top - bottom; // makes the baseline equal to zero
                center = 0 - bottom; // gets the new value of the x axis
                percentage = center / top2; // gets the % of the way to the top the axis should be
                /* EXAMPLE:
                 * if bottom is -3 and top is 7
                 * top2 is 10
                 * center is 3
                 * therefore it should draw it 30% of the way there (3/10)
                 */
                int distanceFromBottom = (int) Math.round(graphHeight * percentage);
                System.out.print(console.setCursor(HIGHEST_GRAPH_POINT + (graphHeight - distanceFromBottom) - 1, 1));
                String xAxis = "";
                for (int i = 0; i < graphWidth; i++) xAxis += "-";
                System.out.print(xAxis);


            }

            if (drawYAxis) {
                double right2, center, percentage;
                right2 = right - left; // makes the baseline equal to zero
                center = 0 - left; // gets the new value of the x axis
                percentage = center / right2; // gets the % of the way to the top the axis should be
                /* EXAMPLE:
                 * if bottom is -3 and top is 7
                 * top2 is 10
                 * center is 3
                 * therefore it should draw it 30% of the way there (3/10)
                 */
                int distanceFromLeft = (int) Math.round(graphWidth * percentage);
                for (int i = 0; i < graphHeight; i++) {
                    System.out.print(console.setCursor(HIGHEST_GRAPH_POINT + i, distanceFromLeft + 2));
                    System.out.print("|");
                }                
            }

            // get dimensions of single cell
            double rangeX = right - left;
            double xDim = rangeX / graphWidth;

            // draw the graph
            int x = -201;
            for (double i = left - (2 * xDim); i < right; i += (xDim / 100)) {
                x++;
                double yVal;
                try {
                    yVal = expression.eval(i);
                } catch (Exception e) {
                    continue;
                }
                if (yVal < bottom || yVal > top) {
                    continue;
                }
                if (Double.isNaN(yVal) || Double.isInfinite(yVal)) continue;
                double range = top - bottom; // makes the baseline equal to zero
                double yVal2 = yVal - bottom; // gets the new value of the x axis
                double percentage = yVal2 / range; // gets the % of the way to the top the axis should be

                int cursorX = x/100 + 3;
                int cursorY = HIGHEST_GRAPH_POINT + (graphHeight - (int) Math.round(percentage * graphHeight)) - 1;

                if (cursorX <= graphWidth && cursorY <= graphHeight + HIGHEST_GRAPH_POINT - 1 && cursorY > 7)
                    System.out.print(console.setCursor(cursorY, cursorX) + "X");

            }



            // end drawing graph
            System.out.print(colors.RESET);
            String clear = "";
            for (int i = 0; i < input.length(); i++) clear += " ";
            System.out.print(console.setCursor(4, 7)+clear+console.setCursor(4, 7));
        }
        console.mainMenu();
    }

    public static void graphSettings() {
        console.resetCursor(true);
        Cycle settings = new Cycle();
        settings.add(colors.YELLOW+"Graph Bounds"+colors.RESET, "Change the visible range of the graph.");
        settings.add("X minimum: " + left, "The leftmost end of the graph.", new Runnable() {public void run() {
            System.out.print(console.setCursor(4, 12));
            System.out.print("                                                                                         ");
            System.out.print(console.setCursor(4, 12));
            try {
                double input = Double.parseDouble(console.input());
                left = (input < right) ? input : left;
            } catch (Exception e) {}
            graphSettings();
        }});
        settings.add("X maximum: " + right, "The rightmost end of the graph.", new Runnable() {public void run() {
            System.out.print(console.setCursor(5, 12));
            System.out.print("                                                                                         ");
            System.out.print(console.setCursor(5, 12));
            try {
                double input = Double.parseDouble(console.input());
                right = (input > left) ? input : right;
            } catch (Exception e) {}
            graphSettings();
        }});
        settings.add("Y minimum: " + bottom, "The bottom end of the graph.", new Runnable() {public void run() {
            System.out.print(console.setCursor(6, 12));
            System.out.print("                                                                                         ");
            System.out.print(console.setCursor(6, 12));
            try {
                double input = Double.parseDouble(console.input());
                bottom = (input < top) ? input : bottom;
            } catch (Exception e) {}
            graphSettings();
        }});
        settings.add("Y maximum: " + top, "The top end of the graph.", new Runnable() {public void run() {
            System.out.print(console.setCursor(7, 12));
            System.out.print("                                                                                         ");
            System.out.print(console.setCursor(7, 12));
            try {
                double input = Double.parseDouble(console.input());
                top = (input > bottom) ? input : top;
            } catch (Exception e) {}
            graphSettings();
        }});

        settings.add(colors.YELLOW+"Graph Size"+colors.RESET, "Change the size of the graph window.");

        settings.add("Graph Width: " + graphWidth, "The width of the graph.", new Runnable() {public void run() {
            System.out.print(console.setCursor(9, 14));
            System.out.print("                                                                                         ");
            System.out.print(console.setCursor(9, 14));
            try {
                int input = Integer.parseInt(console.input());
                graphWidth = (input > 0) ? input : graphWidth;
            } catch (Exception e) {}
            graphSettings();
        }});

        settings.add("Graph Height: " + graphHeight, "The height of the graph.", new Runnable() {public void run() {
            System.out.print(console.setCursor(10, 15));
            System.out.print("                                                                                         ");
            System.out.print(console.setCursor(10, 15));
            try {
                int input = Integer.parseInt(console.input());
                graphHeight = (input > 0) ? input : graphHeight;
            } catch (Exception e) {}
            graphSettings();
        }});

        settings.add("Back", "", new Runnable() {public void run() {
            calc();
        }});

        settings.start(2, "Graphing Settings\n-----------------");
    }
}