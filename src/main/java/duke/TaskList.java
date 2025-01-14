package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TaskList represents all the tasks contained in a Duke Chatbot.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * A constructor for a duke.TaskList which contains Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Converts a taskList to a text format that can be saved in a txt file.
     * @return a String that represents the savable data of a duke.TaskList.
     */
    public String toSaveData() {
        String data = "";
        for (Task task : tasks) {
            data += task.toSaveData();
        }
        return data;
    }

    /**
     * Adds an existing task to the list of tasks.
     * @param task The task to be added to the list of tasks.
     */
    public void addTask(Task task) throws DukeException {
        System.out.println(task.toString());
        if (checkDuplicatedTask(task)) {
            throw new DukeException("This task already exists in your list!");
        }
        tasks.add(task);
    }

    /**
     * Indicates if a given task already exists in the task list.
     * @param task The task to be checked for duplicates
     * @return a boolean representing if the task is duplicated
     */
    public boolean checkDuplicatedTask(Task task) {
        return tasks.stream().anyMatch(x -> x.toString().equals(task.toString()));
    }

    /**
     * Given a string, creates a To-do from that string and adds it to the list of task.
     * @param taskTitle a String of the title of the To-do to be added.
     * @return the newly created To-do
     */
    public String addNewTodo(String taskTitle) {
        Todo todo = new Todo(taskTitle);
        addTask(todo);
        return "Got it. I've added this task:\n\t" + todo.toString() + countTasks();
    }

    /**
     * Given a string, creates a Deadline from that string and adds it to the list of task.
     * @param taskTitle a String of the title of the Deadline to be added.
     * @return the newly created Deadline.
     */
    public String addNewDeadline(String taskTitle) {
        int delimiter = taskTitle.indexOf("/by ");
        LocalDate due = LocalDate.parse(taskTitle.substring(delimiter + 4));
        Deadline deadline = new Deadline(taskTitle.substring(0, delimiter), due);
        addTask(deadline);
        return "Got it. I've added this task:\n\t" + deadline.toString() + countTasks();
    }

    /**
     * Given a string, creates a Deadline from that string and adds it to the list of task.
     * @param taskTitle a String of the title of the Deadline to be added.
     * @return the newly created Deadline.
     */
    public String addNewEvent(String taskTitle) {
        int delimiter = taskTitle.indexOf("/at ");
        LocalDate due = LocalDate.parse(taskTitle.substring(delimiter + 4));
        Event event = new Event(taskTitle.substring(0, delimiter), due);
        addTask(event);
        return "Got it. I've added this task:\n\t" + event.toString() + countTasks();
    }

    /**
     * Given the index number of a task, marks that task as completed.
     *
     * @param taskNumber an int representing the index of the task
     * @return the String representation of the completed task
     */
    public String completeTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        // Assumes that the task exists.
        Task task = tasks.get(taskIndex);
        task.completeTask();
        return "Nice! I've marked this task as done:\n\t" + task.toString();
    }

    /**
     * Tells the user how many tasks there are in the list.
     *
     * @return A string that contains the number of tasks in the list.
     */
    public String countTasks() {
        return String.format("\nNow you have %d tasks in the list.", this.tasks.size());
    }

    /**
     * Returns the list of tasks that match a given keyword or phrase.
     * @param keyword a String that must be contained by tasks.
     * @return a filtered list of tasks that contain the keyword.
     */
    public String findTasks(String keyword) {
        TaskList filteredList = new TaskList();
        for (Task task : this.tasks) {
            if (task.titleContains(keyword)) {
                filteredList.addTask(task);
            }
        }
        return String.format("I've filtered tasks containing '%s'.\n", keyword)
                + filteredList.toString();
    }

    /**
     * Deletes a task when given its index number.
     *
     * @param taskNumber an int representing the index of the task
     * @return the String representation of the deleted task
     */
    public String deleteTask(int taskNumber) throws DukeException {
        int taskIndex = taskNumber - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("The task you are trying to delete does not exist. :(");
        }
        return "Noted. I've removed this task:\n\t"
                + tasks.remove(taskIndex).toString()
                + countTasks();
    }

    /**
     * Returns a string representing the list of tasks.
     *
     * @return A string representing the task list
     */
    @Override
    public String toString() {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            int indexNumber = i + 1;
            output += "\n" + indexNumber + "." + tasks.get(i).toString();
        }
        return output;
    }
}
