package console;

import java.util.Scanner;

import calculator.*;


public class console {
    public static boolean degrees = false;
    static Scanner s = new Scanner(System.in);

    public static void start() {
        resetCursor(true);
        mainMenu();
    }
    
    public static String input() {
        String a = s.hasNextLine() ? s.nextLine() : "";
        return a;
    }

    public static String setCursor(int row, int column) {
        return String.format("%c[%d;%df", 0x1B, row, column);
    }

    public static void resetCursor(boolean clearConsole) {
        System.out.print("\033[H\033[2J");
        if (clearConsole) System.out.flush();
    }

    public static void mainMenu() {
        resetCursor(true);
        Cycle mainMenu = new Cycle();
        mainMenu.add("Help", "Type anything and then press enter to open a menu item. Press enter to cycle through the menu.", new Runnable(){public void run(){help();}});
        mainMenu.add("Scientific", "Open a scientific calculator.", new Runnable(){public void run(){scientific.calc();}});
        mainMenu.add("Graphing", "Open a graphing calculator.", new Runnable(){public void run(){graphing.calc();}});
        mainMenu.add("Table", "Open a table viewer.", new Runnable(){public void run(){table.calc();}});
        mainMenu.add("Options", "Calculator options", new Runnable(){public void run(){options();}});
        mainMenu.add("Exit", "", new Runnable(){public void run(){}});
        mainMenu.start(2, "Calculator\n----------");
    }

    private static void help() {
        resetCursor(true);
        Cycle helpMenu = new Cycle();
        helpMenu.add("Navigating Menus", "Type anything and then press enter to open a menu item. Press enter to cycle through the menu.", new Runnable(){public void run(){
            help();
        }});
        helpMenu.add("Math Operations", "View all available operations.", new Runnable(){public void run(){
            System.out.println("Operations - Basic");
            System.out.println("a + b | The sum of a and b");
            System.out.println("a - b | The difference of a and b");
            System.out.println("a * b | The product of a and b");
            System.out.println("a / b | A divided by b\n");
            System.out.println("Operations - Scientific");
            System.out.println("sqrt(a) | The square root of a");
            System.out.println("cbrt(a) | The cube root of a");
            System.out.println("root_n(a) | The nth root of a");
            System.out.println("log(a) | Log base 10 of a");
            System.out.println("ln(a) | Log base e of a");
            System.out.println("log_n(a) | Log base n of a");
            System.out.println("a ^ b | a to the power of b");
            System.out.println("a! | The factorial of a");
            System.out.println("a mod b | The modulo of a and b");
            System.out.println("abs(a) | The absolute value of a\n");
            System.out.println("Operations - Trigonometric");
            System.out.println("Trig functions are in radians by default. This can be changed in the settings.");
            System.out.println("sin(a) | The sine of a");
            System.out.println("cos(a) | The cosine of a");
            System.out.println("tan(a) | The tangent of a");
            System.out.println("arcsin(a) | The inverse sine of a");
            System.out.println("arccos(a) | The inverse cosine of a");
            System.out.println("arctan(a) | The inverse tangent of a");
            System.out.println("csc(a) | The cosecant of a");
            System.out.println("sec(a) | The secant of a");
            System.out.println("cot(a) | The cotangent of a");
            System.out.println("arccsc(a) | The inverse cosecant of a");
            System.out.println("arcsec(a) | The inverse secant of a");
            System.out.println("arccot(a) | The inverse cotangent of a\n");
            System.out.println("Press enter to return to the help menu.");
            input();
            help();
        }});
        helpMenu.add("Back", "", new Runnable(){public void run(){mainMenu();}});
        helpMenu.start(2, "Help\n----");
    }

    private static void options() {
        Cycle optionsMenu = new Cycle();
        optionsMenu.add("Trig Mode: " + (degrees ? "Degrees" : "Radians"), "Change the unit used for trig functions.", new Runnable(){public void run(){
            degrees = !degrees;
            options();
        }});
        optionsMenu.add("Exit", "", new Runnable(){public void run(){mainMenu();}});

        optionsMenu.start(2, "Options\n-------");
    }
}