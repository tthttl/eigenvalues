public class EigenValue {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{2, -1}, {-1, 2}};
        getEigenValues(matrix);
    }

    public static void getEigenValues(int[][] matrix) {
        validateMatrix(matrix);
        int b = calculateBForEquation(matrix);
        double determinant = solveDeterminant(b, calculateCForEquation(matrix));
        double[] result = solveQuadraticEquation(determinant, b);
        printEigenValues(result);
    }

    public static void validateMatrix(int[][] matrix) {
        if(matrix == null){
            throw new RuntimeException("Matrix is null.");
        }
        if(matrix[0].length<2 || matrix[1].length<2){
            throw new RuntimeException("Matrix contains null.");
        }
        if(matrix[0].length>2 || matrix[1].length>2){
            throw new RuntimeException("Matrix is too large.");
        }
    }

    public static int calculateBForEquation(int[][] matrix) {
        return (matrix[0][0] + matrix[1][1]) * -1;
    }

    public static int calculateCForEquation(int[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    //(-b+/- Math.sqrt(Math.pow(b, 2) - 4 * ac))/2 ; a is always 1
    public static double[] solveQuadraticEquation(double determinant, int b) {
        if (determinant > 0) {
            return new double[]{((b * -1) + determinant) / 2, ((b * -1) - determinant) / 2};
        }
        if (determinant == 0) {
            return new double[]{((b * -1) + determinant) / 2};
        }
        throw new RuntimeException("Matrix has no eigen values.");
    }

    public static double solveDeterminant(int b, int c) {
        return Math.sqrt(Math.pow(b, 2) - 4 * c);
    }

    private static void printEigenValues(double[] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.println("Eigen values: " + (i + 1) + ".: " + result[i]);
        }
    }


}

