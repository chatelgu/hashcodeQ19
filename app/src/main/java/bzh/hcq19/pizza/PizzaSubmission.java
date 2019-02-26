package bzh.hcq19.pizza;

import bzh.hcq19.helper.Submission;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class PizzaSubmission extends Submission {

    public static class PizzaEntry extends Submission.Entry {

        int top;
        int left;
        int bottom;
        int right;

        public PizzaEntry(int top, int left, int bottom, int right) {
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
        }

        @Override
        public void writeTo(OutputStreamWriter osw) throws IOException {
            osw.write(""+top+" "+left+" "+bottom+" "+right+"\n");
        }

        public int size() {
            return (bottom - top + 1) * (right - left + 1);
        }
    }

    public List<PizzaEntry> entries = new ArrayList<>();

    public PizzaSubmission() {
        super();
    }

    @Override
    public List<PizzaEntry> getEntries() {
        return entries;
    }

    @Override
    protected void writeHeader(OutputStreamWriter writer) throws IOException {
        writer.write(""+entries.size()+"\n");
    }

    @Override
    public long score() {
        long score = 0L;
        for (PizzaEntry entry : entries) {
            score += entry.size();
        }
        return score;
    }

}
