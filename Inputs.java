import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Inputs {
  private Scanner scanner;

  private double[][] matrix;

  private int volume;

  private double[] rowB;

  public Inputs() {
    System.out.println("Please select the way to enter the matrix (file/keyboard/random)");
    scanner = new Scanner(System.in);
    boolean tmp = true;
    while (tmp) {
      String answer = scanner.nextLine();
      switch (answer) {
        case "file":
          System.out.println("Please enter the path of file.");
          fileInput(new File(scanner.next()));
          tmp = false;
          break;

        case "keyboard":
          keyboardInput();
          tmp = false;
          break;

        case "random":
          randomInput();
          tmp = false;
          break;

        case "kb":
          keyboardInput();
          tmp = false;
          break;

        default:
          System.out.println("Please try again!");
      }
    }
  }

  private void keyboardInput() {
    checkVolume();
    matrix = new double[volume][volume + 1];
    System.out.println("Please enter matrix:");
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        try {
          matrix[i][j] = scanner.nextDouble();
        } catch (InputMismatchException e) {
          System.out.println("Oops! There is a problem");
          System.exit(0);
        }
      }
    }
  }

  private void fileInput(File file) {
    try {
      scanner = new Scanner(file);
      volume = Integer.parseInt(scanner.next());
      matrix = new double[volume][volume + 1];
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
          matrix[i][j] = Double.parseDouble(scanner.next());
        }
      }

    } catch (NoSuchElementException | FileNotFoundException | NumberFormatException e) {
      e.printStackTrace();
      System.out.println("Oops! Your file can't be read.");
      System.exit(0);
    }
  }

  private void randomInput() {
    checkVolume();
    matrix = new double[volume][volume + 1];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = Math.random() * 100;
      }
    }
  }

  private void checkVolume() {
    scanner = new Scanner(System.in);
    System.out.println("Please set the size of matrix:");
    while (true) {
      String temp = scanner.next();
      try {
        volume = Integer.parseInt(temp);
        break;
      } catch (NumberFormatException e) {
        System.out.println("Please set int!");
      }
      if (volume <= 0 || volume > 20) {
        System.out.println("The size of matrix should be greater than 0 and less than 20!");
        continue;
      }
      break;
    }
  }

  public Scanner getScanner() {
    return scanner;
  }

  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  public double[][] getMatrix() {
    return matrix;
  }

  public void setMatrix(double[][] matrix) {
    this.matrix = matrix;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public double[] getRowB() {
    return rowB;
  }

  public void setRowB(double[] rowB) {
    this.rowB = rowB;
  }
}
