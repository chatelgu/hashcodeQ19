package bzh.hcq19.genetic;

import bzh.hcq19.slideshow.Slide;
import bzh.hcq19.slideshow.SlideShowProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgo {
    private Population population;
    private SlideShowProblem problem;
    public static final int ELITE_CHROMOSOME = 1;
    public static final int TOURNAMENT_SELECTION_SIZE = 5;

    public static int POPULATION_SIZE = 100;

    public GeneticAlgo(SlideShowProblem problem) {
        this.problem = problem;
        this.population = new Population(POPULATION_SIZE,problem);
        this.population.initRandom();
    }

    public Solution naturalSelection () {
        this.population = generateNextGeneration();
        return getBestSolution();
    }

    public Solution getBestSolution() {
        this.population.sortAll();
        return this.population.getBestSolution();
    }

    private Solution tournamentSelection () {
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE, problem);
        Random random = new Random();
        for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++) {
            tournamentPopulation.add(this.population.get(random.nextInt(POPULATION_SIZE)));
        }
        tournamentPopulation.sortAll();
        return tournamentPopulation.getBestSolution();
    }

    private Solution crossOver (Solution parent1, Solution parent2) {
        List<Slide> newSlide = new ArrayList<>();
        Random random = new Random();
        int minSize = Math.min(parent1.size(),parent2.size());
        int marker = random.nextInt(minSize);
        for (int i = 0; i < minSize; i++) {
            if (i < marker) {
                newSlide.add(parent1.get(i));
            } else {
                newSlide.add(parent2.get(i));
            }
        }
        Solution offspring = new Solution(problem,newSlide);
        return offspring;
    }

    private Solution mutate (Solution solution) {
        long start = System.currentTimeMillis();
        Random random = new Random();
        int nbSwap = random.nextInt(Math.max(1, solution.size()/10));
        for (int i = 0; i < nbSwap; i++) {
            Collections.swap(solution.result(), random.nextInt(solution.size()), random.nextInt(solution.size()));
        }
        return solution;
    }

    private Population generateNextGeneration () {

        Population nextGenerationPopulation = new Population(POPULATION_SIZE, problem);
        for (int i = 0; i < ELITE_CHROMOSOME; i++) {
            Solution mix =  mutate(this.population.get(i));
            mix.removeDuplicate();
            nextGenerationPopulation.add(mutate(this.population.get(i)));
        }

        for (int i = ELITE_CHROMOSOME; i < POPULATION_SIZE; i++) {
            Solution parent1 = tournamentSelection();
            Solution parent2 = tournamentSelection();
            Solution cross = crossOver(parent1, parent2);
            Solution mix = mutate(cross);
            mix.removeDuplicate();
            nextGenerationPopulation.add(mix);
        }

        nextGenerationPopulation.evaluateAll();

        return nextGenerationPopulation;
    }
}
