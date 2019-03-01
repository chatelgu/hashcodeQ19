package bzh.hcq19;

import bzh.hcq19.slideshow.Slide;
import bzh.hcq19.slideshow.SlideShowProblem;
import bzh.hcq19.slideshow.SlideshowSubmission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Qualif19 {

    public static void main(String argv[]) {
        new Qualif19();
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Qualif19() {
        logger.debug("Hello HashCode !");
        showAll();
        //solveAll();
    }

    public void showAll() {
        String pbs[] = new String[] {
                "a_example.txt",
                "b_lovely_landscapes.txt",
                "c_memorable_moments.txt",
                "d_pet_pictures.txt",
                "e_shiny_selfies.txt"};
        for (String pb : pbs) {
            SlideShowProblem problem = new SlideShowProblem(pb);
            logger.debug("pb : "+problem);
        }
    }

    // to solve everything
    public void solveAll() {
        //solve("a_example", this::parseSimpleProblem);
        //solve("b_lovely_landscapes", this::lovely);
        solve("c_memorable_moments", this::force);
        //solve("d_pet_pictures", this::force);
        //solve("e_shiny_selfies", this::force);
    }


    public SlideshowSubmission force(SlideShowProblem pb) {
        GuillaumeLessDummySolver dum = new GuillaumeLessDummySolver(pb);
        SlideshowSubmission sub = new SlideshowSubmission(dum.getForce());
        return sub;
    }


    public SlideshowSubmission reallydummy(SlideShowProblem pb) {
        GuillaumeLessDummySolver dum = new GuillaumeLessDummySolver(pb);
        SlideshowSubmission sub = new SlideshowSubmission(dum.getReallyDummy());
        return sub;
    }


    public SlideshowSubmission lovely(SlideShowProblem pb) {
        GuillaumeLessDummySolver dum = new GuillaumeLessDummySolver(pb);
        SlideshowSubmission sub = new SlideshowSubmission(dum.getLovely());
        return sub;
    }

    public SlideshowSubmission parseSimpleProblem(SlideShowProblem pb) {
        logger.debug(pb.toString());
        pb.prettyPrint();
        List<Slide> entries = new ArrayList<>();
        entries.add(new Slide(pb.allPhotos.get(0), null));
        entries.add(new Slide(pb.allPhotos.get(3), null));
        entries.add(new Slide(pb.allPhotos.get(1), pb.allPhotos.get(2)));
        SlideshowSubmission sol = new SlideshowSubmission(entries);
        logger.debug("simple score : "+sol.score());
        return sol;
    }

    public interface Solver {
        SlideshowSubmission solve(SlideShowProblem problem);
    }

    public void solve(String filename, Solver solver) {
        SlideShowProblem pb = new SlideShowProblem(filename + ".txt");
        logger.debug(pb.toString());
        SlideshowSubmission sub = solver.solve(pb);
        logger.debug(filename+" score : " + sub.score());
        if (sub.isValid()) {
            sub.writeTo(filename);
        } else {
            logger.error("invalid submission");
        }
    }
}
