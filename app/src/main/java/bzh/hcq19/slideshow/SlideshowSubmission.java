package bzh.hcq19.slideshow;

import bzh.hcq19.helper.Submission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SlideshowSubmission extends Submission {


    private Logger logger = LoggerFactory.getLogger(getClass());

    // store of all the submissions rows
    private List<Slide> entries = new ArrayList<>();

    public SlideshowSubmission(List<Slide> entries) {
        this.entries = entries;
    }

    @Override
    public List<Slide> getEntries() {
        return entries;
    }

    // write the submission header
    @Override
    protected void writeHeader(OutputStreamWriter writer) throws IOException {
        writer.write(""+entries.size()+"\n");
    }

    public boolean isValid() {
        HashSet<Integer> pictures = new HashSet<>();
        for (Slide slide : entries) {
            if (pictures.contains(slide.photo1.index)) {
                logger.error("photo "+slide.photo1.index+ " already used");
                return false;
            } else {
                pictures.add(slide.photo1.index);
            }
            if (slide.photo2 != null) {
                if (pictures.contains(slide.photo2.index)) {
                    logger.error("photo "+slide.photo2.index+ " already used");
                    return false;
                } else {
                    pictures.add(slide.photo2.index);
                }
            }
        }
        return true;
    }


    @Override
    public long score() {
        long score = 0L;

        for (int i = 0; i < entries.size()-1; i++) {
            Slide s1 = entries.get(i);
            Slide s2 = entries.get(i+1);
            score += Slide.score(s1, s2);
        }
        return score;
    }

}
