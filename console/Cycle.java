/*
 * Micah Friedman
 * Copyright (C) 2023
 */

package console;

import java.util.ArrayList;

public class Cycle {
    public Cycle() {};

    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> descriptions = new ArrayList<String>();
    private ArrayList<Runnable> functions = new ArrayList<Runnable>();

    private int active = 0;
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }

    public void next() {
        next(1);
    }

    public void next(int amount) {
        active += (amount) % names.size();
        if (active == names.size()) active = 0;
    }

    public void add(String name, String description, Runnable whenRun) {
        names.add(name);
        descriptions.add(description);
        functions.add(whenRun);
    }

    public void add(String name, String description) {
        names.add(name);
        descriptions.add(description);
        functions.add(null);
    }

    public void start(int vOffset, String title) {
        console.resetCursor(false);
        System.out.println(title);
        String clearConsole;
        for (int i = 0; i < names.size(); i++) {
            System.out.print(console.setCursor(1+vOffset+i, 1) + names.get(i));
        }
        while (true) {

            int controlChars = 0;

            for (char c : names.get(active).toCharArray()) {
                if (c == '\u001B') controlChars++;
            }
            
            System.out.print(console.setCursor(1+vOffset+active, 1));

            System.out.print(colors.BG_WHITE);
            System.out.print(colors.BLACK);

            System.out.print(names.get(active) + " | " + colors.RESET + descriptions.get(active));

            System.out.print(console.setCursor(1+vOffset+active, names.get(active).length()+1+(controlChars*-5 + (controlChars > 0 ? 1 : 0)))+colors.RESET);

            System.out.print(" | " + descriptions.get(active));

            System.out.print(console.setCursor(names.size()+1+vOffset, 1));

            if (!console.input().equalsIgnoreCase("")) {
                if (functions.get(active) != null) {
                    functions.get(active).run();
                    return;
                }
            }
            System.out.print(console.setCursor(1+vOffset+active, 1));
            System.out.print(colors.RESET);
            System.out.print(names.get(active));

            clearConsole = "";
            for (int i = 0; i < (descriptions.get(active).length() + 3); i++)
                clearConsole += " ";
            System.out.print(clearConsole);

            next();
        }
    }
}