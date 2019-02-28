package bzh.hcq19;

import bzh.hcq19.helper.Submission;
import bzh.hcq19.slideshow.Slide;
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
        solveAll();
    }

    // to solve everything
    public void solveAll() {
        solve("a_example", this::parseSimpleProblem);
        solve("b_lovely_landscapes", this::dummy);
        solve("c_memorable_moments", this::dummy);
        solve("d_pet_pictures", this::dummy);
        solve("e_shiny_selfies", this::dummy);
    }

    public SlideshowSubmission dummy(SlideShowProblem pb) {
        GuillaumeDummySolver dum = new GuillaumeDummySolver(pb);
        SlideshowSubmission sub = new SlideshowSubmission();
        sub.entries.addAll(dum.getSolution());
        return sub;
    }

    public SlideshowSubmission parseSimpleProblem(SlideShowProblem pb) {
        logger.debug("pb "+pb);
        pb.prettyPrint();
        SlideshowSubmission sol = new SlideshowSubmission();
        sol.entries.add(new Slide(pb.allPhotos.get(0), null));
        sol.entries.add(new Slide(pb.allPhotos.get(3), null));
        sol.entries.add(new Slide(pb.allPhotos.get(1), pb.allPhotos.get(2)));
        logger.debug("simple score : "+sol.score());
        return sol;
    }

    public interface Solver {
        SlideshowSubmission solve(SlideShowProblem problem);
    }

    public void solve(String filename, Solver solver) {
        SlideShowProblem pb = new SlideShowProblem(filename + ".txt");
        Submission sub = solver.solve(pb);
        logger.debug(filename+" score : " + sub.score());
        sub.writeTo(filename);
    }
}
