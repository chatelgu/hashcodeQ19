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
            HashSet<String> tags = new HashSet<>(photo1.tags);
            return tags;
        }
    }

    public static int score(Slide s1, Slide s2) {
        Set<String> s2Tags = s2.getTags();
        Set<String> s1Tags = s1.getTags();
        return Math.min(
                inter(s1Tags, s2Tags),
                        Math.min(
                                inset1(s1Tags, s2Tags),
                                inset1(s2Tags, s1Tags)
                        ));
    }

    static int inter(Set<String> h1, Set<String> h2) {
        HashSet<String> tmp = new HashSet<>(h1);
        tmp.retainAll(h2);
        return tmp.size();
    }

    static int inset1(Set<String> h1, Set<String> h2) {
        HashSet<String> tmp = new HashSet<>(h1);
        tmp.removeAll(h2);
        return tmp.size();
    }

}
