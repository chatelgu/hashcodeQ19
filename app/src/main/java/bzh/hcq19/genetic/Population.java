package bzh.hcq19.genetic;

import bzh.hcq19.slideshow.SlideShowProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Population {

    List<Solution> allSolutions;
    private SlideShowProblem problem;
    int populationSize;

    public Population(int populationSize, SlideShowProblem problem) {
        this.problem = problem;
        this.populationSize = populationSize;
        allSolutions = new ArrayList<>();
    }

    public void initRandom() {
        IntStream.range(0,populationSize).forEach(i -> {
            Solution s = new Solution(problem);
            s.generaterandom();
            allSolutions.add(s);
        });
    }

    public Solution get(int i) {
        return allSolutions.get(i);
    }

    public void set(int i, Solution s) {
        allSolutions.set(i, s);
    }

    public void add(Solution s) {
        allSolutions.add(s);
    }

    public void sortAll() {
        Collections.sort(allSolutions);
    }

    public Solution getBestSolution() {
        return allSolutions.get(0);
    }

    public void evaluateAll() {
        for(Solution s : allSolutions) {
            s.evaluate();
        }
    }
}
