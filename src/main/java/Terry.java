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
            if(input.startsWith("bye")){
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
            else if (input.startsWith("unmark")) {
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
            else if (input.startsWith("todo")) {
                System.out.println(line);
                System.out.println(space + " Got it. I've added this task:");
                tasks.addTodo(input.substring(5));
                System.out.println(space + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
                System.out.println(line + "\n");
            }
            else if (input.startsWith("deadline")) {
                System.out.println(line);
                System.out.println(space + " Got it. I've added this task:");
                String[] deadline = input.substring(9).split("/");
                tasks.addDealine(deadline[0], deadline[1]);
                System.out.println(space + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
                System.out.println(line + "\n");
            }
            else if (input.startsWith("event")) {
                System.out.println(line);
                System.out.println(space + " Got it. I've added this task:");
                String[] deadline = input.substring(6).split("/");
                deadline[1] = deadline[1].substring(5);
                deadline[2] = deadline[2].substring(3);
                tasks.addEvent(deadline[0], deadline);
                System.out.println(space + " Now you have " + tasks.getTaskCount() + " tasks in the list.");
                System.out.println(line + "\n");
            }
        }
    }
}
