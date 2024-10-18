import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args){
        Session session = new Session();
        Random random = new Random();

        //populate it with 20 students and dummy scores
        for(int i=0; i<10; i++){
            PartTimeStudent ptStudent =  new PartTimeStudent("Part-Time student" + (i+1));
            List<Integer> ptScores =  generateRandomScores(random,15);
            ptStudent.setQuizScores(ptScores);
            session.addStudent(ptStudent);
        }

        for (int i=0; i<10; i++){
            FullTimeStudent ftStudent = new FullTimeStudent("Full-Time student" + (i+1));
            List<Integer> ftScores = generateRandomScores(random, 15);
            ftStudent.setQuizScores(ftScores);
            List<Integer> examSores = generateRandomScores(random,2);
            ftStudent.setExamScores(examSores);
            session.addStudent(ftStudent);

        }

        //Call all public methods of Session
        session.calculateAverageQuizScores();
        session.printSortedQuizScores();
        session.printPartTimeStudents();
        session.printFullTimeExamScores();

    }

    private  static List<Integer> generateRandomScores(Random random , int count){
        List<Integer> scores = new ArrayList<>();
        for(int i = 0; i < count; i++ ){
            scores.add(random.nextInt(101));
        }
        return  scores;
    }

}
