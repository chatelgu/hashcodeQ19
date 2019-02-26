package bzh.hcq19.pizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzaMain {

    public static void main(String argv[]) {
        new PizzaMain();
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    private PizzaMain() {
        logger.debug("Hello HashCode !");
        solveAPizza();

        logger.debug("bPizza : "+getBPizza());
        logger.debug("cPizza : "+getCPizza());
        logger.debug("dPizza : "+getDPizza());
    }


    public PizzaProblem getBPizza() {
        return new PizzaProblem("pizza/b_small.in");
    }
    public PizzaProblem getCPizza() {
        return new PizzaProblem("pizza/c_medium.in");
    }
    public PizzaProblem getDPizza() {
        return new PizzaProblem("pizza/d_big.in");
    }


    public void solveAPizza() {
        PizzaProblem pizza = new PizzaProblem("pizza/a_example.in");
        logger.debug("aPizza : "+ pizza);
        pizza.pp();

        PizzaSubmission sol = new PizzaSubmission();
        sol.entries.add(new PizzaSubmission.PizzaEntry(0, 0, 2, 1));
        sol.entries.add(new PizzaSubmission.PizzaEntry(0, 2, 2, 2));
        sol.entries.add(new PizzaSubmission.PizzaEntry(0, 3, 2, 4));

        logger.debug("aPizza score: "+sol.score());
        sol.writeTo("pizza_a");
    }

}
