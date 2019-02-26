package lannion.soft.qualif19;

import lannion.soft.qualif19.helper.Problem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Scanner;

public class UnknowProblem extends Problem {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public UnknowProblem(String filename) {
        super(filename);
    }

    @Override
    protected void parseFile(InputStream file) throws Exception {
        Scanner scanner = new Scanner(file);

        // TODO read the data from the scanner
        // scanner.nextInt();
    }

    @Override
    public String toString() {
        return "UnknowProblem";
    }

    public void prettyPrint() {
        // dump the problem to be sure we have parsed it well
    }

}
