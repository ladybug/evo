package org.evosuite.symbolic.solver;

import org.evosuite.symbolic.expr.Comparator;
import org.evosuite.symbolic.expr.Constraint;
import org.evosuite.symbolic.expr.Operator;
import org.evosuite.symbolic.expr.bv.IntegerConstant;
import org.evosuite.symbolic.expr.bv.StringUnaryToIntegerExpression;
import org.evosuite.symbolic.expr.constraint.IntegerConstraint;
import org.evosuite.symbolic.expr.str.StringVariable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public abstract class TestSolverIsInteger extends TestSolver {

	@Test
	public void testIsInteger()
			throws SecurityException, NoSuchMethodException, SolverTimeoutException {

		List<Constraint<?>> constraints = new ArrayList<>();

		constraints.add(new IntegerConstraint(
				new StringUnaryToIntegerExpression(new StringVariable("var0", "-123"), Operator.IS_INTEGER, 0L),
				Comparator.NE, new IntegerConstant(0)));

		Map<String, Object> solution = solve(getSolver(), constraints);
		assertNotNull(solution);
		String var0 = (String) solution.get("var0");

		try {
			Integer.parseInt(var0);
		} catch (NumberFormatException ex) {
			fail();
		}
	}

}
