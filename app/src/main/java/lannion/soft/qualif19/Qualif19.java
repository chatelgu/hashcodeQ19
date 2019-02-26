package lannion.soft.qualif19;

import lannion.soft.qualif19.helper.Problem;
import lannion.soft.qualif19.helper.Submission;
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
        UnknowProblem pb = new UnknowProblem("unknownFile");
        logger.debug("pb "+pb);
        pb.prettyPrint();
    }

    public void solveSimpleProblem() {
        UnknowSubmission sol = new UnknowSubmission();

        sol.entries.add(new UnknowSubmission.UnknownEntry());
        sol.entries.add(new UnknowSubmission.UnknownEntry());
        sol.entries.add(new UnknowSubmission.UnknownEntry());

        logger.debug("simple score : "+sol.score());
        sol.writeTo("simple");
    }


    // to solve everything
    public void solveAll() {
        solve("file1", pb -> new UnknowSubmission());
        solve("file1", pb -> new UnknowSubmission());
        solve("file1", pb -> new UnknowSubmission());
        solve("file1", pb -> new UnknowSubmission());
    }


    public interface Solver {
        Submission solve(Problem problem);
    }

    public void solve(String filename, Solver solver) {
        UnknowProblem pb = new UnknowProblem(filename + ".in");
        Submission sub = solver.solve(pb);
        logger.debug(filename+" score : " + sub.score());
        sub.writeTo(filename);
    }
}
