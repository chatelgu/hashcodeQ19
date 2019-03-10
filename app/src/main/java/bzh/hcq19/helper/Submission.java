package bzh.hcq19.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;

public abstract class Submission {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static abstract class Entry {
        public abstract void writeTo(OutputStreamWriter osw) throws IOException;
    }

    public abstract List<? extends Entry> getEntries();

    public Submission() {}

    public void writeTo(String filename) {
        try {
            String fileName = "./"+filename+"___"+score()+"___"+getTimestampExt()+".sol";
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }

            System.out.println("Filename = "+fileName);
            System.out.println("file = "+file);


            file.createNewFile();



            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
            writeHeader(writer);
            writeEntries(writer);
            writer.close();
        } catch (Exception e) {
            logger.error("can't write solution :(", e);
        }
    }

    private String getTimestampExt() {
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat formater = new SimpleDateFormat("H-mm-ss");
        return formater.format(now);
    }

    // if any needed, write the Submission header
    protected void writeHeader(OutputStreamWriter writer) throws IOException {
        // no header to put
    }

    private void writeEntries(OutputStreamWriter writer) throws IOException {
        for (Entry entry : getEntries()) {
            entry.writeTo(writer);
        }
    }

    // try to compute the score of the solution
    public abstract long score();
}
