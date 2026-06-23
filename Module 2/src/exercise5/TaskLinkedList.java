package exercise5;

public class TaskLinkedList {
    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    private Node head;

    public void add(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public Task search(String taskId) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.getTaskId().equals(taskId)) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
    }

    public void traverse() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.task.getTaskId() + " - " + temp.task.getTaskName());
            temp = temp.next;
        }
    }

    public boolean delete(String taskId) {
        if (head == null) {
            return false;
        }
        if (head.task.getTaskId().equals(taskId)) {
            head = head.next;
            return true;
        }
        Node temp = head;
        while (temp.next != null && !temp.next.task.getTaskId().equals(taskId)) {
            temp = temp.next;
        }
        if (temp.next == null) {
            return false;
        }
        temp.next = temp.next.next;
        return true;
    }
}
