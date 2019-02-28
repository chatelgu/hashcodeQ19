package bzh.hcq19.slideshow;

import bzh.hcq19.helper.Problem;
import bzh.hcq19.pizza.PizzaProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class SlideShowProblem extends Problem {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public SlideShowProblem(String filename) {
        super(filename);
    }

    ArrayList<Photo> allPhotos;

    @Override
    protected void parseFile(InputStream file) throws Exception {
        Scanner scanner = new Scanner(file);

        int nbPhoto = scanner.nextInt();

        allPhotos = new ArrayList<>();

        scanner.nextLine(); // skiping end of line

        for (int i=0; i<nbPhoto; i++) {
            String line = scanner.nextLine();
            String[] elems = line.split(" ");

            Photo onePhoto = new Photo();
            onePhoto.setIndex(i);
            onePhoto.setOrientation(elems[0].equals("V") ? Photo.Orientation.VERTICAL : Photo.Orientation.HORIZONTAL);
            for(int j=2; j<elems.length; j++) {
                onePhoto.addTag(elems[j]);
            }
            allPhotos.add(onePhoto);
        }
    }

    @Override
    public String toString() {
        return "UnknowProblem";
    }

    public void prettyPrint() {
        // dump the problem to be sure we have parsed it well
    }

}
