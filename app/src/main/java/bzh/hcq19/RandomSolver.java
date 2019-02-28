package bzh.hcq19;

import bzh.hcq19.slideshow.Photo;
import bzh.hcq19.slideshow.Slide;
import bzh.hcq19.slideshow.SlideShowProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class RandomSolver {

    private Logger logger = LoggerFactory.getLogger(getClass());


    List<Photo> entries;

    List<Photo> verticals;
    List<Photo> horizontals;

    List<Slide> sub;

    public RandomSolver(SlideShowProblem pb) {
        entries = pb.allPhotos;

        logger.debug("nb entries : "+entries.size());

        verticals = new ArrayList<>(entries);
        verticals.removeIf(photo -> photo.orientation == Photo.Orientation.HORIZONTAL);
        horizontals = new ArrayList<>(entries);
        horizontals.removeIf(photo -> photo.orientation == Photo.Orientation.VERTICAL);

        logger.debug("nb vertical : "+verticals.size());
        logger.debug("nb horizontal : "+horizontals.size());

        sub = new ArrayList<>();


        while(!verticals.isEmpty() || !horizontals.isEmpty()) {


            if(!horizontals.isEmpty()) {
                int index = (int)(Math.random() * horizontals.size());
                Photo photo = horizontals.remove(index);
                sub.add(new Slide(photo, null));
            }
            if(!verticals.isEmpty()) {
                int index1 = (int)(Math.random() * verticals.size());
                Photo photo1 = verticals.remove(index1);
                int index2 = (int)(Math.random() * verticals.size());
                Photo photo2 = verticals.remove(index2);
                sub.add(new Slide(photo1, photo2));
            }

        }

    }

    public List<Slide> getSolution() {
        return sub;
    }



}
