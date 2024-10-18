import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Session {
    private List<Student> students;

    public Session(){
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (students.size() < 20) {
            students.add(student);
        } else {
            throw new IllegalArgumentException("Cannot add more than 20 students");
        }
    }

    public void calculateAverageQuizScores() {
        System.out.println("The average quiz score for each student：");
        for (Student student : students) {
            System.out.printf("%s: %.2f%n", student.getName(), student.calculateAverageQuizScore());
        }
    }

    public void printSortedQuizScores(){
        for (Student student : students){
            List<Integer>sortedScores = new ArrayList<>(student.getQuizScores());
            Collections.sort(sortedScores);
            System.out.printf("The quiz scores of %s (in ascending order) ：%s%n", student.getName(), sortedScores);
        }
    }

    public void printPartTimeStudents(){
        System.out.print("The names of those Part-Time students are\n");
        for (Student student : students){
            if(student instanceof PartTimeStudent){
                System.out.print(student.getName() + ",  ");
            }
        }
        System.out.println();
    }

    public void printFullTimeExamScores(){
        for (Student student : students){
            if(student instanceof FullTimeStudent){
                FullTimeStudent ftStudent = (FullTimeStudent) student;
                System.out.printf(" The exam scores of %s is %s%n", ftStudent.getName(), ftStudent.getExamScores());
            }
        }
    }

}
