import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixValidatorTest {

    private static int[][] INVALID_MATRIX = new int[][]{{6, -1}, {2, }};
    private static int[][] LARGE_MATRIX = new int[][]{{6, -1, 3}, {2, 1, 2}};

    private MatrixValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new MatrixValidator();
    }

    @Test
    public void inputSmallerThan0() throws Exception {
        validator.isInputForSqrtEqualToOrGreaterThan0(1,10);
        assertThat(validator.getValidationResult()).isEqualTo("The square root of a negative num is NaN.");
    }

    @Test
    public void inputGreaterThan0() throws Exception {
        validator.isInputForSqrtEqualToOrGreaterThan0(1,-10);
        assertThat(validator.getValidationResult()).isEqualTo("Matrix is valid!");
    }

    @Test
    public void inputIs0() throws Exception {
        validator.isInputForSqrtEqualToOrGreaterThan0(1,-2);
        assertThat(validator.getValidationResult()).isEqualTo("Matrix is valid!");
    }

    @Test
    public void determinantSmallerThan0() throws Exception {
        validator.isDeterminantEqualToOrGreaterThan0(-1);
        assertThat(validator.getValidationResult()).isEqualTo("Matrix has no EigenValue");
    }

    @Test
    public void determinantGreaterThan0() throws Exception {
        validator.isDeterminantEqualToOrGreaterThan0(1);
        assertThat(validator.getValidationResult()).isEqualTo("Matrix is valid!");
    }

    @Test
    public void determinantIs0() throws Exception {
        validator.isDeterminantEqualToOrGreaterThan0(0);
        assertThat(validator.getValidationResult()).isEqualTo("Matrix is valid!");
    }

    @Test
    public void matrixIsNull() throws Exception {
        validator.isMatrixValid(null);
        assertThat(validator.getValidationResult()).isEqualTo("Matrix is null.");
    }

    @Test
    public void matrixContainsEmptyBuckets() throws Exception {
        validator.isMatrixValid(INVALID_MATRIX);
        assertThat(validator.getValidationResult()).isEqualTo("Matrix is too small.");
    }

    @Test
    public void matrixIsTooLarge() throws Exception {
        validator.isMatrixValid(LARGE_MATRIX);
        assertThat(validator.getValidationResult()).isEqualTo("Matrix is too large.");
    }

}
