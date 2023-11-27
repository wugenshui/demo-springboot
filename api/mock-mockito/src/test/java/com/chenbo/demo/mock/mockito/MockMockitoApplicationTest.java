package com.github.wugenshui.mock.mockito;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class MockMockitoApplicationTest {

    @Test
    public void listTest() {
        // mock creation
        List mockedList = mock(List.class);

        // using mock object
        mockedList.add("one");
        mockedList.clear();

        // verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void linkedListTest() {
        // You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        // following prints "first"
        System.out.println(mockedList.get(0));

        // following throws runtime exception
        try {
            System.out.println(mockedList.get(1));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        // following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        // Although it is possible to verify a stubbed invocation, usually it's just redundant
        // If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        // If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    @Test
    public void httpTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getAttribute("a")).thenReturn("10");
        when(request.getAttribute("b")).thenReturn("20");

        String a = request.getAttribute("a").toString();
        String b = request.getAttribute("b").toString();
        int sum = Integer.parseInt(a) + Integer.parseInt(b);
        request.setAttribute("sum", sum);
        System.out.println(sum);
    }

}