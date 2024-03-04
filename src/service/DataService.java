package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class DataService {
    private List<User> userList;
    private List<StudentGroup> studentGroupList;

    public void create(String firstName, String lastName, String middleName, Type type) {
        int id = getFreeUserId(type);
        if (Type.STUDENT == type) {
            Student student = new Student(firstName, lastName, middleName, id);
            userList.add(student);
        }
        if (Type.TEACHER == type) {
            Teacher teacher = new Teacher(firstName, lastName, middleName, id);
            userList.add(teacher);
        }
    }

    public User getUserById(Type type, int id) {
        boolean itsStudent = Type.STUDENT == type;
        User currentUser = null;

        for (User user : userList) {
            if (user instanceof Teacher && !itsStudent && ((Teacher) user).getTeacherId() == id) {
                currentUser = user;
            }

            if (user instanceof Student && itsStudent && ((Student) user).getStudentId() == id) {
                currentUser = user;
            }
        }

        return currentUser;
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public List<User> getAllStudent() {
        List<User> students = new ArrayList<>();
        for (User user: userList) {
            if (user instanceof Student) {
                students.add(user);
            }
        }
        return students;
    }

    public StudentGroup createStudentGroup(int teacherId, List<Integer> studentIds) {
        int id = getFreeStudentGroupId();
        User user = getUserById(Type.TEACHER, teacherId);
        Teacher teacher = (Teacher) user;
        List<Student> studentList = getStudentLIstByIds(studentIds);
        return new StudentGroup(teacher, studentList, id);
    }

    public List<Student> getStudentLIstByIds(List<Integer> studentIds) {
        List<Student> studentList = new ArrayList<>();
        for (Integer id: studentIds) {
            User user = getUserById(Type.STUDENT, id);
            Student student = (Student) user;
            studentList.add(student);
        }
        return studentList;
    }


    private int getFreeUserId(Type type) {
        boolean itsStudent = Type.STUDENT == type;
        int lastId = 1;
        for (User user: userList) {
            if (user instanceof Teacher && !itsStudent) {
                lastId = ((Teacher) user).getTeacherId() + 1;
            }

            if (user instanceof Student && itsStudent) {
                lastId = ((Student) user).getStudentId() + 1;
            }
        }
        return lastId;
    }

    private int getFreeStudentGroupId() {
        int lastId = 1;
        for (StudentGroup studentGroup: studentGroupList) {
            lastId = studentGroup.getStudentGroupId() + 1;
        }
        return lastId;
    }
}
