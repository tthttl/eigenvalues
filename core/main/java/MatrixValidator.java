public class MatrixValidator {

    private String validationResult;

    public  String getValidationResult(){
        return validationResult;
    }

    public MatrixValidator() {
        this.validationResult = "Matrix is valid!";
    }

    public boolean isMatrixValid(int[][] matrix) {
        if(matrix == null){
            validationResult = "Matrix is null.";
            return false;
        }
        if(matrix[0].length<2 || matrix[1].length<2){
            validationResult = "Matrix is too small.";
            return false;
        }
        if(matrix[0].length>2 || matrix[1].length>2){
            validationResult = "Matrix is too large.";
            return false;
        }
        return true;
    }

    public boolean isInputForSqrtEqualToOrGreaterThan0(int b, int c){
        if ((Math.pow(b, 2) - 4 * c)<0){
            validationResult = "The square root of a negative num is NaN.";
            return false;
        }
        return true;
    }

    public boolean isDeterminantEqualToOrGreaterThan0(double determinant){
        if (determinant<0){
            validationResult = "Matrix has no EigenValue";
            return false;
        }
        return true;
    }



}
