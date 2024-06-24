package com.acme.tracktorim.services;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WorkActivityServiceTest {

    @Test
    void concatListsTest() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");

        List<String> list2 = new ArrayList<>();
        list2.add("D");
        list2.add("A");
        list2.add("A");

        List<String> unifyList = Stream.concat(list1.stream(), list2.stream()).distinct().toList();
        System.out.println("unifyList = " + unifyList);
        assertEquals(4, unifyList.size());
    }

}