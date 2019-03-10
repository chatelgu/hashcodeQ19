package bzh.hcq19;

import bzh.hcq19.helper.Submission;
import bzh.hcq19.slideshow.SlideShowProblem;
import bzh.hcq19.slideshow.SlideshowSubmission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QualifSamuel {

    public static long total;

    public static void main(String argv[]) {
        total = 0;
        new QualifSamuel();
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    public QualifSamuel() {
        logger.debug("Hello HashCode !");
//        parseSimpleProblem();
        solveAll();
    }

    public void parseSimpleProblem() {
    }

    // to solve everything
    public void solveAll() {
        solve("a_example", this::genetic);
        solve("b_lovely_landscapes", this::genetic);
        solve("c_memorable_moments", this::genetic);
        solve("d_pet_pictures", this::genetic);
        solve("e_shiny_selfies", this::genetic);

        logger.debug("score total : " + total);

    }


    public SlideshowSubmission genetic(SlideShowProblem pb) {
        GeneticSolver dum = new GeneticSolver(pb);
        SlideshowSubmission sub = new SlideshowSubmission(dum.getSolution());
        return sub;
    }


    public SlideshowSubmission random(SlideShowProblem pb) {
        RandomSolver dum = new RandomSolver(pb);
        SlideshowSubmission sub = new SlideshowSubmission(dum.getSolution());
        return sub;
    }


    public interface Solver {
        SlideshowSubmission solve(SlideShowProblem problem);
    }

    public void solve(String filename, Solver solver) {
        SlideShowProblem pb = new SlideShowProblem(filename + ".txt");
        Submission sub = solver.solve(pb);
        long score = sub.score();
        logger.debug(filename+" score : " + score);
        total += score;

        sub.writeTo(filename);
    }
}
