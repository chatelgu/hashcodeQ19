package bzh.hcq19.slideshow;

import bzh.hcq19.helper.Submission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

            Set<String> s2Tags = s2.getTags();
            Set<String> s1Tags = s1.getTags();
            score += Math.min(
                        inter(s1Tags, s2Tags),
                        Math.min(
                                inset1(s1Tags, s2Tags),
                                inset1(s2Tags, s1Tags)
                        ));
        }
        return score;
    }

    int inter(Set<String> h1, Set<String> h2) {
        HashSet<String> tmp = new HashSet<>(h1);
        tmp.retainAll(h2);
        return tmp.size();
    }

    int inset1(Set<String> h1, Set<String> h2) {
        HashSet<String> tmp = new HashSet<>(h1);
        tmp.removeAll(h2);
        return tmp.size();
    }
}
