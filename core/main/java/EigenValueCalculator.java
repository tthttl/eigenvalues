public class EigenValueCalculator {

    private MatrixValidator matrixValidator;
    private double[] eigenValues;

    public EigenValueCalculator(MatrixValidator matrixValidator) {
        this.matrixValidator = matrixValidator;
    }

    public void calculate(int[][] matrix) {
        if (matrixValidator.isMatrixValid(matrix)) {
            int b = calculateBForEquation(matrix);
            int c = calculateCForEquation(matrix);
            if (matrixValidator.isInputForSqrtEqualToOrGreaterThan0(b, c)) {
                double determinant = solveDeterminant(b, c);
                if (matrixValidator.isDeterminantEqualToOrGreaterThan0(determinant)) {
                    this.eigenValues = solveQuadraticEquation(determinant, b);
                }
            }
        }
    }

    public int calculateBForEquation(int[][] matrix) {
        return (matrix[0][0] + matrix[1][1]) * -1;
    }

    public int calculateCForEquation(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    //(-b+/- Math.sqrt(Math.pow(b, 2) - 4 * ac))/2 ; a is always 1
    public double[] solveQuadraticEquation(double determinant, int b) {
        if (determinant > 0) {
            return new double[]{((b * -1) + determinant) / 2, ((b * -1) - determinant) / 2};
        } else {
            return new double[]{((b * -1) + determinant) / 2};
        }
    }

    public double solveDeterminant(int b, int c) {
        return Math.sqrt(Math.pow(b, 2) - 4 * c);
    }

    public void printEigenValues() {
        if (eigenValues != null) {
            for (int i = 0; i < eigenValues.length; i++) {
                System.out.println("Eigen values: " + (i + 1) + ".: " + eigenValues[i]);
            }
        }
    }

    public double[] getEigenValues() {
        return eigenValues;
    }
}

