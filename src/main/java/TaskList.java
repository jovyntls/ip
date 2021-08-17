public class TaskList {
    private Task[] tasks;
    private static final int MAX_TASKS = 200;
    private int counter;

    /**
     * A constructor for a TaskList which contains Tasks.
     */
    public TaskList() {
        this.counter = 0;
        this.tasks = new Task[MAX_TASKS];
    }

    /**
     * Given a string, creates a Task from that string and adds it to the list of task
     * @param taskTitle a String of the title of the task to be added.
     */
    public void addTask(String taskTitle) {
        Task task = new Task(taskTitle);
        this.tasks[counter] = task;
        counter ++;
    }

    /**
     * Given the index number of a task, marks that task as completed
     *
     * @param taskIndex an int representing the index of the task
     * @return the String representation of the completed task
     */
    public String completeTask(int taskIndex) {
        Task task = this.tasks[taskIndex];
        // Assumes that the task exists.
        task.completeTask();
        return task.toString();
    }

    /**
     * Returns a string representing the list of tasks.
     *
     * @return A string representing the task list
     */
    @Override
    public String toString() {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < counter; i++) {
            int index = i + 1;
            output += "\n" + index + "." + this.tasks[i].toString();
        }
        return output;
    }
}
