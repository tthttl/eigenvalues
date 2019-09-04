public class Client {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{2, -1}, {-1, 2}};
        MatrixValidator matrixValidator = new MatrixValidator();
        EigenValueCalculator eigenValueCalculator = new EigenValueCalculator(matrixValidator);
        eigenValueCalculator.calculate(matrix);
        eigenValueCalculator.printEigenValues();
    }

}
