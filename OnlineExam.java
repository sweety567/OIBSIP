import java.util.Scanner;

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Question {
    String questionText;
    String[] options;
    int correctOption;

    Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class Exam {
    Question[] questions;
    int totalQuestions;
    int totalTime; // in seconds

    Exam(Question[] questions, int totalTime) {
        this.questions = questions;
        this.totalQuestions = questions.length;
        this.totalTime = totalTime;
    }
}

public class OnlineExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User Authentication
        User currentUser = login(scanner);

        // Update Profile and Password
        updateProfile(scanner, currentUser);

        // Load Questions for the Exam
        Question[] questions = loadQuestions();
        Exam exam = new Exam(questions, 300); // 5 minutes exam time

        // Take the Exam
        takeExam(scanner, currentUser, exam);

        // Close session and Logout
        logout();
    }

    private static User login(Scanner scanner) {
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();


        return new User(username, password);
    }

    private static void updateProfile(Scanner scanner, User currentUser) {
        System.out.println("Update your profile (e.g., change password, etc.)");
        // Add logic to update the user's profile
    }

    private static Question[] loadQuestions() {
        // For simplicity, creating sample questions here
        Question[] questions = new Question[3];
        questions[0] = new Question("Which of the following is not a Java features?", new String[]{"A. Dynamic", "B. Architecture Neutral", "C. Use of pointers" , "D.  Object-oriented"}, 2);
        questions[1] = new Question("_____ is used to find and fix bugs in the Java programs.", new String[]{"A. JDK", "B. JVM", "C. JRE","D. JDB"}, 3);
        questions[2] = new Question("Find a correct c keyword below.", new String[]{"A. Breaker", "B. goto", "C. shorter","D. default"}, 3);

        return questions;
    }

    private static void takeExam(Scanner scanner, User currentUser, Exam exam) {
        System.out.println("Welcome, " + currentUser.username + ", to the online exam!");

        // Display questions and get answers
        for (int i = 0; i < exam.totalQuestions; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + exam.questions[i].questionText);
            for (String option : exam.questions[i].options) {
                System.out.println(option);
            }

            System.out.println("Enter your answer (A, B, C or D): ");
            String userAnswer = scanner.nextLine();

            // Validate answer
            if (userAnswer.equalsIgnoreCase(getOptionLetter(exam.questions[i].correctOption))) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        }

        System.out.println("Exam completed! Thank you, " + currentUser.username + "!");
    }

    private static void logout() {
        System.out.println("Logging out. Goodbye!");

    }

    private static String getOptionLetter(int optionIndex) {
        return String.valueOf((char) ('A' + optionIndex));
    }
}
