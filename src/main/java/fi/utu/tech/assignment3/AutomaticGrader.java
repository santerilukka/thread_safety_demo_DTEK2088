package fi.utu.tech.assignment3;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Automatic Grader thread - grades a list of
 * Submissions and adds them to gradedSubmissions list
 */
public class AutomaticGrader extends Thread {

    private List<Submission> ungradedSubmissions;
    // BlockingQueue List tilalle
    private BlockingQueue<Submission> gradedSubmissions;
    private Random rnd = new Random();

    /**
     * Initialize AutomaticGrader
     * @param ungradedSubmissions List of ungraded submissions to be graded by this grader
     * @param gradedSubmissions Reference to a shared list where all the graded submissions will be added
     */
    public AutomaticGrader(List<Submission> ungradedSubmissions, BlockingQueue<Submission> gradedSubmissions) {
        super();
        // Set a more descriptive name for grader threads
        setName("Grader" + getName());
        this.ungradedSubmissions = ungradedSubmissions;
        this.gradedSubmissions = gradedSubmissions;
    }

    /**
     * Start grading
     */
    @Override
    public void run() {
        for (var s : ungradedSubmissions) {
            try {
                gradedSubmissions.put(grade(s)); // .put() blockaa jos jono on täynnä
            } catch (InterruptedException e) {
                System.out.println("Who dared to interrupt my grading?!");
            }
        }

    }

    /**
     * Grade a submission
     * @param s Submission to be graded
     * @return The graded submission
     */
    public Submission grade(Submission s) throws InterruptedException {
        Thread.sleep(s.getDifficulty());
        return s.grade(rnd.nextInt(6));
    }


}
