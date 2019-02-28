package bzh.hcq19.slideshow;

import bzh.hcq19.helper.Submission;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Slide extends Submission.Entry {
    // store the data for a row of the submission

    Photo photo1;
    Photo photo2;

    public Slide(Photo photo1, Photo photo2) {
        this.photo1 = photo1;
        this.photo2 = photo2;
        assert(isValid());
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

    public Set<String> getTags() {
        if (photo2 != null) {
            HashSet<String> tags = new HashSet<>();
            tags.addAll(photo1.tags);
            tags.addAll(photo2.tags);
            return tags;
        } else {
            HashSet<String> tags = new HashSet<>();
            tags.addAll(photo2.tags);
            return tags;
        }
    }
}
