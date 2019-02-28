package bzh.hcq19.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public abstract class Problem {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String filename;

    public Problem(String filename) {
        this.filename = filename;
        logger.debug("parse from file "+filename);
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        InputStream file = loader.getResourceAsStream(filename);
        try {
            parseFile(file);
            file.close();
        } catch (Exception e) {
            logger.error("can't read problem :(", e);
        }
    }

    protected abstract void parseFile(InputStream file) throws Exception;

    @Override
    public String toString() {
        return "Problem : "+filename;
    }
}
