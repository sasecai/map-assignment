package view;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;
    public TextMenu() {
        commands = new HashMap<>();
    }
    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }
    private void printMenu() {
        for(Command c : commands.values()) {
            String line = String.format("%4s : %s", c.getKey(), c.getDescription());
            System.out.println(line);
        }
    }
    public void show() {
        Scanner scn = new Scanner(System.in);
        while(true) {
            printMenu();
            System.out.println("Input the option: ");
            String key = scn.nextLine();
            Command c = commands.get(key);
            if(c == null) {
                System.out.println("Invalid option");
                continue;
            }
            c.execute();
        }
    }
}
