package bzh.hcq19.slideshow;

import bzh.hcq19.helper.Problem;
import bzh.hcq19.pizza.PizzaProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SlideShowProblem extends Problem {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public SlideShowProblem(String filename) {
        super(filename);
    }

    ArrayList<Photo> allPhotos;
    Set<String> allTag;

    @Override
    protected void parseFile(InputStream file) throws Exception {
        Scanner scanner = new Scanner(file);

        int nbPhoto = scanner.nextInt();

        allPhotos = new ArrayList<>();
        allTag = new HashSet<>();

        scanner.nextLine(); // skiping end of line

        for (int i=0; i<nbPhoto; i++) {
            String line = scanner.nextLine();
            String[] elems = line.split(" ");

            Photo onePhoto = new Photo();
            onePhoto.setIndex(i);
            onePhoto.setOrientation(elems[0].equals("V") ? Photo.Orientation.VERTICAL : Photo.Orientation.HORIZONTAL);
            for(int j=2; j<elems.length; j++) {
                onePhoto.addTag(elems[j]);
                allTag.add(elems[j]);
            }
            allPhotos.add(onePhoto);
        }
    }

    @Override
    public String toString() {
        return "UnknowProblem";
    }

    public void prettyPrint() {
        for(int i=0; i<allPhotos.size() && i < 20; i++) {
            logger.info(allPhotos.get(i).toString());
        }
    }

}
