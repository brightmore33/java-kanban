package manager;


import models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private final Map<Integer, Node<Task>> historyMap = new HashMap<>();
    private Node<Task> head;
    private Node<Task> tail;
    private int size = 0;

    public class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> prev;

        public Node(Node<T> prev, T data, Node<T> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    // добавляем запись в конец истории
    private void addLast(Task segment) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, segment, null);

        tail = newNode;

        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        size++;
    }

    // удаляем запись
    private void removeNode(Node<Task> actualNode) {
        if (actualNode == head) {
            head = actualNode.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (actualNode == tail) {
            tail = actualNode.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
        } else {
            actualNode.prev.next = actualNode.next;
            actualNode.next.prev = actualNode.prev;
        }
        historyMap.remove(actualNode.data.getId());
        size--;
    }

    // получаем список истории
    private List<Task> getTasks() {
        List<Task> history = new ArrayList<>();
        Node<Task> actualNode = head;

        while (actualNode != null) {
            history.add(actualNode.data);
            actualNode = actualNode.next;
        }
        return history;
    }

    @Override
    public void add(Task task) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Task> getHistory() {
        return List.of();
    }
}
