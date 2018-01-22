import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class EigenValueCalculatorTest {

    public static int[][] MATRIX = new int[][]{{6, -1}, {2, 3}};
    private static int EXPECTED_C = MATRIX[0][0] * MATRIX[1][1] - MATRIX[0][1] * MATRIX[1][0];
    private static int EXPECTED_B = (MATRIX[0][0] + MATRIX[1][1])*-1;
    private static int DETERMINANT = 1;
    private static double[] EIGENVALUES = new double[]{4,5};

    private EigenValueCalculator eigenValueCalculator;

    @Mock
    private MatrixValidator matrixValidator;

    @Before
    public void setUp() throws Exception {
        this.eigenValueCalculator = new EigenValueCalculator(matrixValidator);
    }

    @Test
    public void cIsTheDifferenceOfMultiplicationsOfOppositeCornerValues() throws Exception {
        assertThat(eigenValueCalculator.calculateCForEquation(MATRIX)).isEqualTo(EXPECTED_C);
    }

    @Test
    public void bIsTheSumOf00And11() throws Exception {
        assertThat(eigenValueCalculator.calculateBForEquation(MATRIX)).isEqualTo(EXPECTED_B);
    }

    @Test
    public void determinantFormula() throws Exception {
        assertThat(eigenValueCalculator.solveDeterminant(EXPECTED_B, EXPECTED_C)).isEqualTo(DETERMINANT);
    }

    @Test
    public void equationWithPositiveDeterminant() throws Exception {
        assertThat(eigenValueCalculator.solveQuadraticEquation(DETERMINANT,EXPECTED_B)).containsOnly(EIGENVALUES);
    }

    @Test
    public void equationWith0Determinant() throws Exception {
        assertThat(eigenValueCalculator.solveQuadraticEquation(0,EXPECTED_B)).hasSize(1);
    }

    @Test
    public void calculate() throws Exception {
        mockValidation();
        eigenValueCalculator.calculate(MATRIX);
        assertThat(eigenValueCalculator.getEigenValues()).containsOnly(EIGENVALUES);
    }

    private void mockValidation(){
        Mockito.when(matrixValidator.isMatrixValid(anyObject())).thenReturn(true);
        Mockito.when(matrixValidator.isDeterminantEqualToOrGreaterThan0(anyDouble())).thenReturn(true);
        Mockito.when(matrixValidator.isInputForSqrtEqualToOrGreaterThan0(anyInt(), anyInt())).thenReturn(true);
    }
}

