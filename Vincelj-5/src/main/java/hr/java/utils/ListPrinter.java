package hr.java.utils;

import java.util.List;

public class ListPrinter {
    public static <T> void printList(List<T> list)
    {
        for (T element : list)
        {
            System.out.println(element);
        }

    }
}
