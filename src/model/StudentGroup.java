package model;

import java.util.List;

public class StudentGroup {
    private int studentGroupId;
    private Teacher teacher;
    private List<Student> studentList;

    public int getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public StudentGroup(Teacher teacher, List<Student> studentList, int id) {
        this.teacher = teacher;
        this.studentList = studentList;
        this.studentGroupId = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "teacher=" + teacher +
                ", studentList=" + studentList +
                '}';
    }
}
