import java.util.Scanner;

public class QuizApplication {
    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex; // Zero-based indexing
        int timeLimit; 

        Question(String questionText, String[] options, int correctAnswerIndex, int timeLimit) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
            this.timeLimit = timeLimit;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Question[] questions = {
            new Question("What is the capital of France?",
                    new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 2, 10),
            new Question("Which programming language is used for Android development?",
                    new String[]{"1. Java", "2. Python", "3. C++", "4. Swift"}, 0, 10),
            new Question("What is the largest planet in our Solar System?",
                    new String[]{"1. Earth", "2. Jupiter", "3. Saturn", "4. Venus"}, 1, 10)
        };

        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            System.out.println("\n" + question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }

            System.out.println("You have " + question.timeLimit + " seconds to answer!");

            final boolean[] answered = {false};
            final boolean[] timeOut = {false};
            final String[] userAnswer = {null};

            Thread countdownThread = new Thread(() -> {
                for (int j = question.timeLimit; j > 0; j--) {
                    if (answered[0]) break;
                    System.out.print("\rTime remaining: " + j + " seconds ");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        return; 
                    }
                }
                if (!answered[0]) {
                    timeOut[0] = true;
                    System.out.print("\rTime's up! Moving to the next question.\n");
                }
            });

            countdownThread.start();

            Thread inputThread = new Thread(() -> {
                try {
                    if (!timeOut[0]) {
                        userAnswer[0] = scan.nextLine().trim();
                        answered[0] = true;
                    }
                } catch (Exception e) {
                    System.err.println("Error reading input: " + e.getMessage());
                }
            });

            inputThread.start();
            while (!timeOut[0] && !answered[0]) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }

            countdownThread.interrupt();
            inputThread.interrupt();

            if (answered[0]) {
                try {
                    int userAnswerInt = Integer.parseInt(userAnswer[0]) - 1; // Convert to zero-based
                    if (userAnswerInt == question.correctAnswerIndex) {
                        System.out.println("Correct!\n");
                        score++;
                    } else {
                        System.out.println("Wrong! The correct answer was " 
                            + (question.correctAnswerIndex + 1) + ".\n");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid answer! No points awarded.\n");
                }
            } else if (timeOut[0]) {
                System.out.println("No answer submitted.\n");
            }
        }

        System.out.println("Quiz Ends!!!");
        System.out.println("Your final score: " + score + "/" + questions.length);
        System.out.println("Thank You :) ");

        scan.close();
    }
}