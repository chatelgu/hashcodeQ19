package bzh.hcq19.slideshow;

import bzh.hcq19.helper.Submission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SlideshowSubmission extends Submission {

    public static class Slide extends Submission.Entry {
        // store the data for a row of the submission

        Photo photo1;
        Photo photo2;

        public Slide(Photo photo1, Photo photo2) {
            this.photo1 = photo1;
            this.photo2 = photo2;
        }

        @Override
        public void writeTo(OutputStreamWriter writer) throws IOException {
            // TODO write the row line
            if (photo2 != null) {
                writer.write(""+photo1.getIndex()+" "+photo2.getIndex()+"\n");
            } else {
                writer.write(""+photo1.getIndex()+"\n");
            }
        }

        public boolean isValid() {
            if (photo2 != null) {
                return photo1.getOrientation() == Photo.Orientation.VERTICAL &&
                        photo2.getOrientation() == Photo.Orientation.VERTICAL;
            } else {
                return photo1.orientation == Photo.Orientation.HORIZONTAL;
            }
        }
    }

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
        // TODO put the good header
        writer.write(""+entries.size()+"\n");
    }

    @Override
    public long score() {
        long score = 0L;
        // TODO try to compute the score of the solution to submit the best one
        return score;
    }
}
