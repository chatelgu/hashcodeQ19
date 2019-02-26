package lannion.soft.qualif19;

import lannion.soft.qualif19.helper.Submition;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class UnknowSubmition extends Submition {

    public static class UnknownEntry extends Submition.Entry {
        // store the data for a row of the solution


        @Override
        public void writeTo(OutputStreamWriter osw) throws IOException {

        }
    }



    public List<UnknownEntry> entries = new ArrayList<>();


    @Override
    public List<UnknownEntry> getEntries() {
        return entries;
    }

    // try to compute the score of the solution to submit the best one
    @Override
    public long score() {
        return 0;
    }
}
