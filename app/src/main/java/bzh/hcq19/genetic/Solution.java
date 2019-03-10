package bzh.hcq19.genetic;

import bzh.hcq19.slideshow.Photo;
import bzh.hcq19.slideshow.Slide;
import bzh.hcq19.slideshow.SlideShowProblem;

import java.util.*;

public class Solution implements Comparable<Solution> {

    private SlideShowProblem problem;
    private List<Slide> allSlides;
    public long value;
    List<Photo> verticals;
    List<Photo> horizontals;


    Solution(SlideShowProblem pb) {
        allSlides = new ArrayList<>();
        problem = pb;
        verticals = new ArrayList<>(problem.allPhotos);
        verticals.removeIf(photo -> photo.orientation == Photo.Orientation.HORIZONTAL);
        horizontals = new ArrayList<>(problem.allPhotos);
        horizontals.removeIf(photo -> photo.orientation == Photo.Orientation.VERTICAL);
    }

    Solution(SlideShowProblem pb, List<Slide> base) {
        allSlides = new ArrayList<>();
        for(Slide s : base) {
            allSlides.add(s);
        }

        problem = pb;
        verticals = new ArrayList<>(problem.allPhotos);
        verticals.removeIf(photo -> photo.orientation == Photo.Orientation.HORIZONTAL);
        horizontals = new ArrayList<>(problem.allPhotos);
        horizontals.removeIf(photo -> photo.orientation == Photo.Orientation.VERTICAL);
    }

    public void generaterandom() {
        allSlides = new ArrayList<>();

        while(!verticals.isEmpty() || !horizontals.isEmpty()) {
            if(!horizontals.isEmpty()) {
                int index = (int)(Math.random() * horizontals.size());
                Photo photo = horizontals.remove(index);
                allSlides.add(new Slide(photo, null));
            }
            if(!verticals.isEmpty()) {
                int index1 = (int)(Math.random() * verticals.size());
                Photo photo1 = verticals.remove(index1);
                int index2 = (int)(Math.random() * verticals.size());
                Photo photo2 = verticals.remove(index2);
                allSlides.add(new Slide(photo1, photo2));
            }
        }
        value = evaluate();
        verticals = new ArrayList<>(problem.allPhotos);
        verticals.removeIf(photo -> photo.orientation == Photo.Orientation.HORIZONTAL);
        horizontals = new ArrayList<>(problem.allPhotos);
        horizontals.removeIf(photo -> photo.orientation == Photo.Orientation.VERTICAL);
    }

    public long evaluate() {
        value = 0L;
        for (int i = 0; i < allSlides.size()-1; i++) {
            Slide s1 = allSlides.get(i);
            Slide s2 = allSlides.get(i+1);
            value += Slide.score(s1, s2);
        }
        return value;
    }

    public Slide get(int i) {
        return allSlides.get(i);
    }

    @Override
    public int compareTo(Solution o) {
        return (int)(o.value - value);
    }

    public int size() {
        return allSlides.size();
    }

    public List<Slide> result() {
        return allSlides;
    }

    public void removeDuplicate() {
        LinkedList<Photo> verticalsNotUsed = new LinkedList<>(verticals);
        LinkedList<Photo> horizontalsNotUsed = new LinkedList<>(horizontals);

        List<Integer> verticals1DoubleIndex = new ArrayList<>();
        List<Integer> verticals2DoubleIndex = new ArrayList<>();
        List<Integer> horizontalsDoubleIndex = new ArrayList<>();

        for(int i = 0; i < allSlides.size(); i++) {
            Slide slide = allSlides.get(i);
            if(slide.photo2 == null) {
                if(!horizontalsNotUsed.remove(slide.photo1)) {
                    horizontalsDoubleIndex.add(i);
                }
            } else {
                if(!verticalsNotUsed.remove(slide.photo1)) {
                    verticals1DoubleIndex.add(i);
                }
                if(!verticalsNotUsed.remove(slide.photo2)) {
                    verticals2DoubleIndex.add(i);
                }
            }
        }

        for(int i=0; i < horizontalsDoubleIndex.size() && !horizontalsNotUsed.isEmpty(); i++) {
            Integer index = horizontalsDoubleIndex.get(i);
            try {
                allSlides.get(index).photo1 = horizontalsNotUsed.pop();
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
        for(int i=0; i < verticals1DoubleIndex.size() && !verticalsNotUsed.isEmpty(); i++) {
            Integer index = verticals1DoubleIndex.get(i);
            try {
                allSlides.get(index).photo1 = verticalsNotUsed.pop();
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
        for(int i=0; i < verticals2DoubleIndex.size() && !verticalsNotUsed.isEmpty(); i++) {
            Integer index = verticals2DoubleIndex.get(i);
            try {
                allSlides.get(index).photo2 = verticalsNotUsed.pop();

            } catch (Exception e) {
                System.out.println("Error");
            }
        }

        value = evaluate();
    }
}
