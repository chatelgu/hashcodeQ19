package bzh.hcq19.slideshow;

import java.util.List;

public class Photo {

    enum Orientation {
        HORIZONTAL, VERTICAL;

        String val;

    }

    Orientation orientation;

    List<String> tags;

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
