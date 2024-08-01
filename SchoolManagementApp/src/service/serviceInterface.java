package service;

import java.util.*;

import  student.student;


public interface serviceInterface {
    boolean insertSt(student s);
    void display();
    void displayById(int id);
    int deleteSt(int id);
    int updateStudent(student s);
    int feeUpdateStudent(int id, int fee);
    int courseUpdateStudent(int id, ArrayList<String> courses);
    String coursesToString(ArrayList<String> courses);
}
