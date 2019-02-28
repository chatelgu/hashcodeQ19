package bzh.hcq19;

import bzh.hcq19.helper.Problem;
import bzh.hcq19.helper.Submission;
import bzh.hcq19.slideshow.SlideShowProblem;
import bzh.hcq19.slideshow.SlideshowSubmission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Qualif19 {

    public static void main(String argv[]) {
        new Qualif19();
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Qualif19() {
        logger.debug("Hello HashCode !");
        //parseSimpleProblem();
        solveSimpleProblem();
    }

    public void parseSimpleProblem() {
        SlideShowProblem pb = new SlideShowProblem("a_example.txt");
        logger.debug("pb "+pb);
        pb.prettyPrint();
    }

    public void solveSimpleProblem() {
        SlideshowSubmission sol = new SlideshowSubmission();
        logger.debug("simple score : "+sol.score());
        sol.writeTo("simple");
    }


    // to solve everything
    public void solveAll() {
        solve("file1", pb -> new SlideshowSubmission());
        solve("file1", pb -> new SlideshowSubmission());
        solve("file1", pb -> new SlideshowSubmission());
        solve("file1", pb -> new SlideshowSubmission());
    }


    public interface Solver {
        Submission solve(Problem problem);
    }

    public void solve(String filename, Solver solver) {
        SlideShowProblem pb = new SlideShowProblem(filename + ".txt");
        Submission sub = solver.solve(pb);
        logger.debug(filename+" score : " + sub.score());
        sub.writeTo(filename);
    }
}
