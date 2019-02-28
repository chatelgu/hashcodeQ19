package bzh.hcq19;

import bzh.hcq19.slideshow.Photo;
import bzh.hcq19.slideshow.Slide;
import bzh.hcq19.slideshow.SlideShowProblem;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GuillaumeLessDummySolver {

    private Logger logger = LoggerFactory.getLogger(getClass());


    List<Photo> entries;

    List<Photo> verticals;
    List<Photo> horizontals;


    public GuillaumeLessDummySolver(SlideShowProblem pb) {
        entries = pb.allPhotos;

        logger.debug("nb entries : "+entries.size());

        verticals = new ArrayList<>(entries);
        verticals.removeIf(photo -> photo.orientation == Photo.Orientation.HORIZONTAL);
        horizontals = new ArrayList<>(entries);
        horizontals.removeIf(photo -> photo.orientation == Photo.Orientation.VERTICAL);

        logger.debug("nb vertical : "+verticals.size());
        logger.debug("nb horizontal : "+horizontals.size());

    }

    @NotNull
    private HashMap<String, List<Slide>> getTagMap(List<Slide> dummy) {
        final HashMap<String, List<Slide>> map = new HashMap<>();
        dummy.forEach(slide1 -> slide1.getTags().forEach(tag1 -> {
            List<Slide> slideList = map.get(tag1);
            if (slideList == null) {
                slideList = new ArrayList<>();
            }
            slideList.add(slide1);
            map.put(tag1, slideList);
        }));
        return map;
    }

    private Set<Slide> atLeastOneMatch(Slide slide) {
        Set<Slide> oneMatch = new HashSet<>();
        slide.getTags().forEach(tag -> {
            List<Slide> one = tagsMap.get(tag);
            if (one != null) oneMatch.addAll(one);
        });
        //logger.debug("oneMatch size : "+oneMatch.size());
        return oneMatch;
    }



    private Slide findBest(Slide s) {
        return findBestMatch(s, atLeastOneMatch(s));
    }

    Map<String,List<Slide>> tagsMap;

    private void cleanTagsMap(Slide slide) {
        slide.getTags().forEach(tag -> {
            List<Slide> slides = tagsMap.get(tag);
            slides.remove(slide);
        });
    }

    public List<Slide> getLovely() {
        List<Slide> dummy = getDummy();
        tagsMap = getTagMap(dummy);
        List<Slide> sub = new ArrayList<>();

        Slide first = dummy.get(0);
        sub.add(first);
        cleanTagsMap(first);
        dummy.remove(0);
        while (!dummy.isEmpty()) {
            long start = System.currentTimeMillis();
            int size = dummy.size();
            if (size % 100 == 0)
                logger.debug("lovely size : "+ size);

            first = findBest(first);
            if (first == null) {
                first = dummy.get(0);
            }
            dummy.remove(first);
            cleanTagsMap(first);
            sub.add(first);
            long end = System.currentTimeMillis();
            if (size % 100 == 0)
                logger.debug("loop :"+(end-start));
        }
        return sub;
    }

    private Slide findBestMatch(Slide s, Collection<Slide> slides) {
        //logger.debug("findBestMatch size : "+slides.size());
        Slide best = null;
        int bestScore = -1;
        int count = 0;
        for (Slide slide : slides) {
            int score = Slide.score(s, slide);
            if (score > bestScore) {
                best = slide;
                bestScore = score;
            }
            count++;
            if (count > 1000) return best;
        }
        return best;
    }

    public List<Slide> getReallyDummy() {
        List<Slide> slides_h = new ArrayList<>();
        horizontals.forEach(photo -> slides_h.add(new Slide(photo, null)));

        List<Slide> slides_v = new ArrayList<>();
        for (int i = 0; i < verticals.size(); i += 2) {
            slides_h.add(new Slide(verticals.get(i), verticals.get(i+1)));
        }

        slides_h.addAll(slides_v);
        return slides_h;
    }



    public List<Slide> getDummy() {
        List<Slide> sub;

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
        return sub;
    }



    public List<Slide> getForce() {
        List<Slide> sub = new ArrayList<>();
        List<Slide> shuffled;

        shuffled = getDummy();

        Slide first = shuffled.get(0);
        sub.add(first);
        shuffled.remove(0);
        while (!shuffled.isEmpty()) {
            int size = shuffled.size();
            if (size % 100 == 0)
                logger.debug("force size : "+ size);
            first = findBestMatch(first, shuffled);
            shuffled.remove(first);
            sub.add(first);
        }
        return sub;
    }

}
