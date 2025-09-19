package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

//        System.out.println("Printing all data ...");
//        printAllData(tasksData);
//
//        printDataUsingStreams(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        System.out.println("====================");
        printDeadlinesUsingStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        ArrayList<Task> filterList = filterByStringUsingStream(tasksData,"11");
        printAllData(filterList);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines (using stream): " + countDeadlinesUsingStream(tasksData));

    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter(task -> task instanceof Deadline)
                .count();


        return count;
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using iterations ...");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Printing data using streams ...");
        tasks.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines using iterations...");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {

        System.out.println("Printing deadlines using streams...");
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(filterString.toLowerCase())) {
                filteredList.add(t);
            }
        }
        return filteredList;
    }

    public static ArrayList<Task> filterByStringUsingStream(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(toList());
        return filteredList;
    }
}
