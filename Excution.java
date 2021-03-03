import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;

public class Excution {

  private double[][] matrix;
  private double detA;
  private double[] x_vector;
  private double[] r_vector;

  private Inputs inputs;

  public Excution() {
    inputs = new Inputs();
    matrix = inputs.getMatrix();
    detA = 1;
  }

  public void compute() {
    int volume = inputs.getVolume();

    for (int k = 0; k < volume; k++) {
      for (int i = k + 1; i < matrix.length; i++) {
        double matrix_i_k = matrix[i][k];
        if (k == 0 && i == 0) {continue;}

        for (int j = k; j < matrix[i].length; j++) {  //If matrix[k][k] = 0, then exchange row
          int tmp = 1;
          while(matrix[k][k] == 0){
            if(matrix[k+tmp][k] != 0){
              double[] mid;
              mid = matrix[k];
              matrix[k] = matrix[k + tmp];
              matrix[k + tmp] = mid;
              matrix_i_k = matrix[i][k];
              System.out.println("-------------I'm split line!-------------");
              printMatrix();
            }
            tmp++;
          }

          matrix[i][j] = matrix[i][j] - matrix[k][j] * matrix_i_k / matrix[k][k];
          Double matrix_i_j = matrix[i][j];
          if (matrix_i_j.isNaN()) {matrix[i][j] = 0;}
        }
        System.out.println("-------------I'm split line!-------------");
        printMatrix();
      }
      detA = detA * matrix[k][k];
    }
    System.out.println("---------------It's over!----------------");
//    detA = Math.abs(detA);
  }

  public void printMatrix() {
    for (double[] doubles : matrix) {
      for (double aDouble : doubles) {
        System.out.printf("%.2f", aDouble);
        System.out.print(" ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public void printRoundMatrix() {
    for (double[] doubles : matrix) {
      for (double aDouble : doubles) {
        System.out.print(Math.round(aDouble) + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public void printDetA() {
    System.out.println("det A: " + Math.abs(detA) + "\t");
  }

  public void computeVarVector() {
    if (detA != 0) {
      int volume = inputs.getVolume();
      double[] vector = new double[volume];
      vector[vector.length - 1] = 0;
      for (int k = volume - 1; k >= 0; k--) {
        double sum = 0;
        for (int j = volume - 1; j > k; j--) {
          sum = sum + vector[j] * matrix[k][j];
        }
        vector[k] = (matrix[k][volume] - sum) / matrix[k][k];
      }
      x_vector = vector;
    }
  }

  public void printVarVector() {
    if (detA == 0) {
      System.out.println("The determinant is 0, the roots are infinite or they do not exist.");
    } else {
      System.out.println("Вектор неизвестных: " + Arrays.toString(x_vector));
    }
  }

  public void computeRVector() {
    int volume = inputs.getVolume();
    double[] vector = new double[volume];
    double[][] old_matrix = inputs.getMatrix();
    if (detA != 0) {
      for (int k = 0; k < old_matrix.length; k++) {
        double sum = 0;
        for (int j = 0; j < volume; j++) {
          sum = sum + x_vector[j] * old_matrix[k][j];
        }
        vector[k] = old_matrix[k][volume] - sum;
      }
    }
    r_vector = vector;
  }

  public void printRVector() {
    if (detA == 0) {
      System.out.println("Вектор невязок отсутствует");
    } else {
      System.out.println("Вектор невязок: " + Arrays.toString(r_vector));
    }
  }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double getDetA() {
        return detA;
    }

    public void setDetA(double detA) {
        this.detA = detA;
    }

    public double[] getX_vector() {
        return x_vector;
    }

    public void setX_vector(double[] x_vector) {
        this.x_vector = x_vector;
    }

    public double[] getR_vector() {
        return r_vector;
    }

    public void setR_vector(double[] r_vector) {
        this.r_vector = r_vector;
    }

    public Inputs getInputs() {
        return inputs;
    }

    public void setInputs(Inputs inputs) {
        this.inputs = inputs;
    }
}
