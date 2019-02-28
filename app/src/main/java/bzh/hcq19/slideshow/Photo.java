package bzh.hcq19.slideshow;

import java.util.ArrayList;
import java.util.List;

public class Photo {

    public enum Orientation {
        HORIZONTAL, VERTICAL;

        String val;


    }

    public int index;

    public Orientation orientation;

    public List<String> tags;

    public Photo() {
        tags = new ArrayList<>();
    }

    public void addTag(String tagName) {
        tags.add(tagName);
    }

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        String result = "Photo{" + index +
                ", " + orientation +
                ", ";

        for(String tag : tags) {
            result += tag+" ";
        }
        return result+"}";
    }
}
