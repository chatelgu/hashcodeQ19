package bzh.hcq19;

import bzh.hcq19.slideshow.Photo;
import bzh.hcq19.slideshow.Slide;
import bzh.hcq19.slideshow.SlideShowProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuillaumeDummySolver {

    private Logger logger = LoggerFactory.getLogger(getClass());


    List<Photo> entries;

    List<Photo> verticals;
    List<Photo> horizontals;

    List<Slide> sub;

    public GuillaumeDummySolver(SlideShowProblem pb) {
        entries = pb.allPhotos;

        logger.debug("nb entries : "+entries.size());

        verticals = new ArrayList<>(entries);
        verticals.removeIf(photo -> photo.orientation == Photo.Orientation.HORIZONTAL);
        horizontals = new ArrayList<>(entries);
        horizontals.removeIf(photo -> photo.orientation == Photo.Orientation.VERTICAL);

        logger.debug("nb vertical : "+verticals.size());
        logger.debug("nb horizontal : "+horizontals.size());

        Collections.shuffle(verticals);
        Collections.shuffle(horizontals);

        List<Slide> slides_h = new ArrayList<>();
        horizontals.forEach(photo -> slides_h.add(new Slide(photo, null)));

        List<Slide> slides_v = new ArrayList<>();
        for (int i = 0; i < verticals.size(); i += 2) {
            slides_h.add(new Slide(verticals.get(i), verticals.get(i+1)));
        }

        slides_h.addAll(slides_v);


        Collections.shuffle(slides_h);
        sub = slides_h;
    }

    public List<Slide> getSolution() {
        return sub;
    }



}
