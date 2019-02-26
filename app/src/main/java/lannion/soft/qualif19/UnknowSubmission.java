package lannion.soft.qualif19;

import lannion.soft.qualif19.helper.Submission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class UnknowSubmission extends Submission {

    public static class UnknownEntry extends Submission.Entry {
        // store the data for a row of the submission

        @Override
        public void writeTo(OutputStreamWriter writer) throws IOException {
            // TODO write the row line
            writer.write("--- REPLACE THIS ROW ---\n");
        }
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    // store of all the submissions rows
    public List<UnknownEntry> entries = new ArrayList<>();

    @Override
    public List<UnknownEntry> getEntries() {
        return entries;
    }

    // write the submission header
    @Override
    protected void writeHeader(OutputStreamWriter writer) throws IOException {
        // TODO put the good header
        writer.write("*** HEADER TO BE REPLACED ***\n");
    }

    @Override
    public long score() {
        long score = 0L;
        // TODO try to compute the score of the solution to submit the best one
        return score;
    }
}
