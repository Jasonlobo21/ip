import java.util.Scanner;

public class Terry {
    public static void main(String[] args) {
        String space = "    ";
        String line = space + "____________________________________";
        System.out.println(line);
        System.out.println(space + " Hello! I'm Terry\n" +
                space + " What can I do for you?");
        System.out.println(line + "\n");

        Scanner in = new Scanner(System.in);
        boolean isLooping = true;

        TaskList tasks = new TaskList(100);

        while(isLooping){
            String input = in.nextLine();
            if(input.contains("bye")){
                isLooping = false;
                System.out.println(line);
                System.out.println(space + " Bye. Hope to see you again soon!");
                System.out.println(line);
            }
            else if(input.equals("list")){
                System.out.println(line);
                tasks.listTasks();
                System.out.println(line + "\n");
            }
            else if (input.contains("unmark")) {
                System.out.println(line);
                System.out.println(space + " OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(input.substring(7));
                tasks.unmarkTask(index);
                System.out.println(line + "\n");
            }
            else if(input.startsWith("mark")){
                System.out.println(line);
                System.out.println(space + " Nice! I've marked this task as done:");
                int index = Integer.parseInt(input.substring(5));
                tasks.markTask(index);
                System.out.println(line + "\n");
            }
            else {
                //add input to list
                tasks.addTask(input);
                System.out.println(line);
                System.out.println(space + " added: " + input);
                System.out.println(line + "\n");
            }
        }
    }
}
