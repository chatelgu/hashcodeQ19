package bzh.hcq19.slideshow;

import bzh.hcq19.helper.Submission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SlideshowSubmission extends Submission {


    private Logger logger = LoggerFactory.getLogger(getClass());

    // store of all the submissions rows
    public List<Slide> entries = new ArrayList<>();

    @Override
    public List<Slide> getEntries() {
        return entries;
    }

    // write the submission header
    @Override
    protected void writeHeader(OutputStreamWriter writer) throws IOException {
        writer.write(""+entries.size()+"\n");
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
