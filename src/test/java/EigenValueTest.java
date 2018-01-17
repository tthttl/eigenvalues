import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class EigenValueTest {

    private static int[][] MATRIX = new int[][]{{6, -1}, {2, 3}};
    private static int EXPECTED_C = MATRIX[0][0] * MATRIX[1][1] - MATRIX[0][1] * MATRIX[1][0];
    private static int EXPECTED_B = (MATRIX[0][0] + MATRIX[1][1])*-1;
    private static int DETERMINANT = 1;
    private static double[] EIGENVALUES = new double[]{4,5};
    private static int[][] INVALID_MATRIX = new int[][]{{6, -1}, {2, }};
    private static int[][] LARGE_MATRIX = new int[][]{{6, -1, 3}, {2, 1, 2}};

    @Test
    public void cIsTheDifferenceOfMultiplicationsOfOppositeCornerValues() throws Exception {
        assertThat(EigenValue.calculateCForEquation(MATRIX)).isEqualTo(EXPECTED_C);
    }

    @Test
    public void bIsTheSumOf00And11() throws Exception {
        assertThat(EigenValue.calculateBForEquation(MATRIX)).isEqualTo(EXPECTED_B);
    }

    @Test
    public void determinantFormula() throws Exception {
        assertThat(EigenValue.solveDeterminant(EXPECTED_B, EXPECTED_C)).isEqualTo(DETERMINANT);
    }

    @Test
    public void equationWithPositiveDeterminant() throws Exception {
        assertThat(EigenValue.solveQuadraticEquation(DETERMINANT,EXPECTED_B)).containsOnly(EIGENVALUES);
    }

    @Test
    public void equationWith0Determinant() throws Exception {
        assertThat(EigenValue.solveQuadraticEquation(0,EXPECTED_B)).hasSize(1);
    }

    @Test
    public void equationWithNegativeDeterminant() throws Exception {
        Throwable thrown = catchThrowable(() -> EigenValue.solveQuadraticEquation(-1,EXPECTED_B));
        assertThat(thrown).hasMessage("Matrix has no eigen values.");
    }

    @Test
    public void matrixIsNull() throws Exception {
        Throwable thrown = catchThrowable(() -> EigenValue.validateMatrix(null));
        assertThat(thrown).hasMessage("Matrix is null.");
    }

    @Test
    public void matrixContainsEmptyBuckets() throws Exception {
        Throwable thrown = catchThrowable(() -> EigenValue.validateMatrix(INVALID_MATRIX));
        assertThat(thrown).hasMessage("Matrix contains null.");
    }

    @Test
    public void matrixIsTooLarge() throws Exception {
        Throwable thrown = catchThrowable(() -> EigenValue.validateMatrix(LARGE_MATRIX));
        assertThat(thrown).hasMessage("Matrix is too large.");
    }
}

