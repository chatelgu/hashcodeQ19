package lannion.soft.qualif19;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Qualif19 {

    public static void main(String argv[]) {
        new Qualif19();
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Qualif19() {
        logger.debug("Hello HashCode !");
    }
}
