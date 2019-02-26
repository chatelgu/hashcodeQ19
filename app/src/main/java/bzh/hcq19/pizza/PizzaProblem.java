package bzh.hcq19.pizza;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bzh.hcq19.helper.Problem;

public class PizzaProblem extends Problem {

    public static class Cell {
        public enum Type { Mushroom, Tomato }

        public final Type type;
        public final int x;
        public final int y;

        public Cell(Type type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    public class Pizza {
        public final int row;
        public final int column;

        protected final List<List<Cell>> cells = new ArrayList<>();

        public Pizza(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public Cell get(int i, int j) {
            return cells.get(i).get(j);
        }
    }

    public int minElement; // per slice
    public int maxSize; // of slice

    public Pizza pizza;

    public PizzaProblem(String filename) {
        super(filename);
    }

    @Override
    protected void parseFile(InputStream file) throws Exception {
        Scanner scanner = new Scanner(file);
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        pizza = new Pizza(row, column);

        minElement = scanner.nextInt();
        maxSize = scanner.nextInt();

        scanner.nextLine(); // skiping end of line

        for (int i=0; i<pizza.row; i++) {
            String line = scanner.nextLine();
            pizza.cells.add(parseLine(line, i));
        }
    }

    private List<Cell> parseLine(String line, int rowIndex) {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < line.getBytes().length; i++) {
            cells.add(new Cell(line.getBytes()[i] == 'T' ? Cell.Type.Tomato : Cell.Type.Mushroom, rowIndex, i));
        }
        return cells;
    }

    @Override
    public String toString() {
        return "pizza ("+pizza.row+", "+pizza.column+") at least "+minElement+" in max "+maxSize+" elements slice";
    }

    public void pp() {
        for (List<Cell> line : pizza.cells) {
            for (Cell cell : line) {
                System.out.print(cell.type == Cell.Type.Mushroom ? 'M' : 'T');
            }
            System.out.println();
        }
    }
}
