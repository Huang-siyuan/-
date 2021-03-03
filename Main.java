public class Main {
  public static void main(String args[]) {

    Excution computing = new Excution();
    System.out.println("This is the basic matrix.");
    computing.printMatrix();
    System.out.println();
    computing.compute();
    computing.printDetA();
    computing.computeVarVector();
    computing.printVarVector();
    computing.computeRVector();
    computing.printRVector();

    System.out.println("Спасибо за внимание!");
  }
}
