package bzh.hcq19;

import bzh.hcq19.genetic.GeneticAlgo;
import bzh.hcq19.genetic.Solution;
import bzh.hcq19.slideshow.Photo;
import bzh.hcq19.slideshow.Slide;
import bzh.hcq19.slideshow.SlideShowProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GeneticSolver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static int NB_MAX_GEN = 20;

    List<Photo> entries;

    List<Slide> sub;

    public GeneticSolver(SlideShowProblem pb) {
        entries = pb.allPhotos;

        GeneticAlgo genAlgo = new GeneticAlgo(pb);
        logger.debug("nb entries : "+entries.size());
        Solution currentBestSolution = genAlgo.getBestSolution();
        Long bestValue = currentBestSolution.evaluate();
        logger.debug("First random solution score : "+bestValue);

        sub = new ArrayList<>();
        for(Slide s : currentBestSolution.result()) {
            sub.add(new Slide(s.photo1, s.photo2));
        }

        int nbGen = 0;
        long start = 0;
        while (nbGen < NB_MAX_GEN) {
            start = System.currentTimeMillis();
            Solution newSolution = genAlgo.naturalSelection();
            logger.debug("Generation "+nbGen+" score : "+newSolution.evaluate() + " - "+(System.currentTimeMillis()-start)+"ms");
            if(newSolution.value > bestValue) {
                bestValue = newSolution.value;
                logger.debug("New best ! "+bestValue);

                sub = new ArrayList<>();
                for(Slide s : newSolution.result()) {
                    sub.add(new Slide(s.photo1, s.photo2));
                }
            }
            nbGen++;
        }

        logger.debug("Delect solution score : "+bestValue);
    }

    public List<Slide> getSolution() {
        return sub;
    }



}
