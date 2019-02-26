package lannion.soft.qualif19.pizza;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PizzaMainTest {


    @Test
    public void checkAPizza() {
        PizzaProblem.Pizza pizza = new PizzaProblem("pizza/a_example.in").pizza;
        PizzaProblem.Cell cell00 = pizza.get(0, 0);
        assertEquals(cell00.type, PizzaProblem.Cell.Type.Tomato);
        PizzaProblem.Cell cell03 = pizza.get(0, 3);
        assertEquals(cell03.type, PizzaProblem.Cell.Type.Tomato);
        PizzaProblem.Cell cell13 = pizza.get(1, 3);
        assertEquals(cell13.type, PizzaProblem.Cell.Type.Mushroom);
        PizzaProblem.Cell cell14 = pizza.get(1, 4);
        assertEquals(cell14.type, PizzaProblem.Cell.Type.Tomato);

    }
}