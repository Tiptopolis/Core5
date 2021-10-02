package com.uchump.prime._RAUM.RAUM.GFX;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.ShortArray;
import com.uchump.prime._PRIME.C_O.NIX.BoundShape;
import com.uchump.prime._PRIME.N_M.UTIL.V_Operator;

public class PolygonResolver {
	private final IntArray quicksortStack = new IntArray();
	private float[] sortedPoints;
	private final Array<Vector2> hull = new Array<Vector2>();
	private final IntArray indices = new IntArray();
	private final ShortArray originalIndices = new ShortArray(false, 0);

	
	public static BoundShape convexHull(ArrayList<BoundShape> Bs) {
		BoundShape[] ar = new BoundShape[Bs.size()];
		for(int i = 0; i < Bs.size(); i++)
		{
			BoundShape s = Bs.get(i);
			ar[i]=s;
		}
		return convexHull(ar);
	}
	
	public static BoundShape convexHull(BoundShape... Bs) {
		
		PolygonResolver P = new PolygonResolver();
		BoundShape B = new BoundShape();
		Array<Vector3> allPts = new Array<Vector3>(true, 0, Vector3.class);

		for (BoundShape S : Bs) {
			for (Vector3 v : S.shape) {
				allPts.add(v);
			}
		}

		Vector2[] dc = V_Operator.downcast(allPts.toArray());

		Array<Vector2> poly = new Array<Vector2>(true, 0, Vector2.class);
		poly.addAll(P.computePolygon(V_Operator.toStream(dc), true));
		
		
		Vector3[] up = V_Operator.upcast(poly.toArray());

		for (int i = 0; i < up.length - 1; i++) {
			B.shape.add(up[i]);
		}

		return B;
	}

	/** @see #computeHull(float[], int, int, boolean) */
	public Array<Vector2> computePolygon(FloatArray points, boolean sorted) {
		return computeHull(points.items, 0, points.size, sorted);
	}

	/** @see #computeHull(float[], int, int, boolean) */
	public Array<Vector2> computePolygon(float[] polygon, boolean sorted) {
		return computeHull(polygon, 0, polygon.length, sorted);
	}

	/**
	 * Returns a list of points on the convex hull in counter-clockwise order. Note:
	 * the last point in the returned list is the same as the first one.
	 */
	/**
	 * Returns the convex hull polygon for the given point cloud.
	 * 
	 * @param points x,y pairs describing points. Duplicate points will result in
	 *               undefined behavior.
	 * @param sorted If false, the points will be sorted by the x coordinate then
	 *               the y coordinate, which is required by the convex hull
	 *               algorithm. If sorting is done the input array is not modified
	 *               and count additional working memory is needed.
	 * @return pairs of coordinates that describe the convex hull polygon in
	 *         counterclockwise order. Note the returned array is reused for later
	 *         calls to the same method.
	 */
	public Array<Vector2> computeHull(float[] points, int offset, int count, boolean sorted) {
		int end = offset + count;

		if (!sorted) {
			if (sortedPoints == null || sortedPoints.length < count)
				sortedPoints = new float[count];
			System.arraycopy(points, offset, sortedPoints, 0, count);
			points = sortedPoints;
			offset = 0;
			sort(points, count);
		}

		Array<Vector2> hull = this.hull;
		hull.clear();

		// Lower hull.
		for (int i = offset; i < end; i += 2) {
			float x = points[i];
			float y = points[i + 1];
			while (hull.size >= 4 && ccw(x, y) <= 0)
				hull.size -= 2;
			// hull.add(x);
			// hull.add(y);
			hull.add(new Vector2(x, y));
		}

		// Upper hull.
		for (int i = end - 4, t = hull.size + 2; i >= offset; i -= 2) {
			float x = points[i];
			float y = points[i + 1];
			while (hull.size >= t && ccw(x, y) <= 0)
				hull.size -= 2;
			// hull.add(x);
			// hull.add(y);
			hull.add(new Vector2(x, y));

		}

		return hull;
	}

	/** @see #computeIndices(float[], int, int, boolean, boolean) */
	public IntArray computeIndices(FloatArray points, boolean sorted, boolean yDown) {
		return computeIndices(points.items, 0, points.size, sorted, yDown);
	}

	/** @see #computeIndices(float[], int, int, boolean, boolean) */
	public IntArray computeIndices(float[] polygon, boolean sorted, boolean yDown) {
		return computeIndices(polygon, 0, polygon.length, sorted, yDown);
	}

	/**
	 * Computes a hull the same as {@link #computeHull(float[], int, int, boolean)}
	 * but returns indices of the specified points.
	 */
	public IntArray computeIndices(float[] points, int offset, int count, boolean sorted, boolean yDown) {
		if (count > 32767)
			throw new IllegalArgumentException("count must be <= " + 32767);
		int end = offset + count;

		if (!sorted) {
			if (sortedPoints == null || sortedPoints.length < count)
				sortedPoints = new float[count];
			System.arraycopy(points, offset, sortedPoints, 0, count);
			points = sortedPoints;
			offset = 0;
			sortWithIndices(points, count, yDown);
		}

		IntArray indices = this.indices;
		indices.clear();

		Array<Vector2> hull = this.hull;
		hull.clear();

		// Lower hull.
		for (int i = offset, index = i / 2; i < end; i += 2, index++) {
			float x = points[i];
			float y = points[i + 1];
			while (hull.size >= 4 && ccw(x, y) <= 0) {
				hull.size -= 2;
				indices.size--;
			}
			// hull.add(x);
			// hull.add(y);
			hull.add(new Vector2(x, y));
			indices.add(index);
		}

		// Upper hull.
		for (int i = end - 4, index = i / 2, t = hull.size + 2; i >= offset; i -= 2, index--) {
			float x = points[i];
			float y = points[i + 1];
			while (hull.size >= t && ccw(x, y) <= 0) {
				hull.size -= 2;
				indices.size--;
			}
			// hull.add(x);
			// hull.add(y);
			hull.add(new Vector2(x, y));
			indices.add(index);
		}

		// Convert sorted to unsorted indices.
		if (!sorted) {
			short[] originalIndicesArray = originalIndices.items;
			int[] indicesArray = indices.items;
			for (int i = 0, n = indices.size; i < n; i++)
				indicesArray[i] = originalIndicesArray[indicesArray[i]];
		}

		return indices;
	}

	/**
	 * Returns > 0 if the points are a counterclockwise turn, < 0 if clockwise, and
	 * 0 if colinear.
	 */
	private float ccw(float p3x, float p3y) {

		float[] f = V_Operator.toStream(this.hull.toArray());
		FloatArray hull = new FloatArray(f);
		int size = hull.size;
		float p1x = hull.get(size - 4);
		float p1y = hull.get(size - 3);
		float p2x = hull.get(size - 2);
		float p2y = hull.peek();
		return (p2x - p1x) * (p3y - p1y) - (p2y - p1y) * (p3x - p1x);
	}

	/**
	 * Sorts x,y pairs of values by the x value, then the y value.
	 * 
	 * @param count Number of indices, must be even.
	 */
	private void sort(float[] values, int count) {
		int lower = 0;
		int upper = count - 1;
		IntArray stack = quicksortStack;
		stack.add(lower);
		stack.add(upper - 1);
		while (stack.size > 0) {
			upper = stack.pop();
			lower = stack.pop();
			if (upper <= lower)
				continue;
			int i = quicksortPartition(values, lower, upper);
			if (i - lower > upper - i) {
				stack.add(lower);
				stack.add(i - 2);
			}
			stack.add(i + 2);
			stack.add(upper);
			if (upper - i >= i - lower) {
				stack.add(lower);
				stack.add(i - 2);
			}
		}
	}

	private int quicksortPartition(final float[] values, int lower, int upper) {
		float x = values[lower];
		float y = values[lower + 1];
		int up = upper;
		int down = lower;
		float temp;
		while (down < up) {
			while (down < up && values[down] <= x)
				down = down + 2;
			while (values[up] > x || (values[up] == x && values[up + 1] < y))
				up = up - 2;
			if (down < up) {
				temp = values[down];
				values[down] = values[up];
				values[up] = temp;

				temp = values[down + 1];
				values[down + 1] = values[up + 1];
				values[up + 1] = temp;
			}
		}
		if (x > values[up] || (x == values[up] && y < values[up + 1])) {
			values[lower] = values[up];
			values[up] = x;

			values[lower + 1] = values[up + 1];
			values[up + 1] = y;
		}
		return up;
	}

	/**
	 * Sorts x,y pairs of values by the x value, then the y value and stores
	 * unsorted original indices.
	 * 
	 * @param count Number of indices, must be even.
	 */
	private void sortWithIndices(float[] values, int count, boolean yDown) {
		int pointCount = count / 2;
		originalIndices.clear();
		originalIndices.ensureCapacity(pointCount);
		short[] originalIndicesArray = originalIndices.items;
		for (short i = 0; i < pointCount; i++)
			originalIndicesArray[i] = i;

		int lower = 0;
		int upper = count - 1;
		IntArray stack = quicksortStack;
		stack.add(lower);
		stack.add(upper - 1);
		while (stack.size > 0) {
			upper = stack.pop();
			lower = stack.pop();
			if (upper <= lower)
				continue;
			int i = quicksortPartitionWithIndices(values, lower, upper, yDown, originalIndicesArray);
			if (i - lower > upper - i) {
				stack.add(lower);
				stack.add(i - 2);
			}
			stack.add(i + 2);
			stack.add(upper);
			if (upper - i >= i - lower) {
				stack.add(lower);
				stack.add(i - 2);
			}
		}
	}

	private int quicksortPartitionWithIndices(final float[] values, int lower, int upper, boolean yDown,
			short[] originalIndices) {
		float x = values[lower];
		float y = values[lower + 1];
		int up = upper;
		int down = lower;
		float temp;
		short tempIndex;
		while (down < up) {
			while (down < up && values[down] <= x)
				down = down + 2;
			if (yDown) {
				while (values[up] > x || (values[up] == x && values[up + 1] < y))
					up = up - 2;
			} else {
				while (values[up] > x || (values[up] == x && values[up + 1] > y))
					up = up - 2;
			}
			if (down < up) {
				temp = values[down];
				values[down] = values[up];
				values[up] = temp;

				temp = values[down + 1];
				values[down + 1] = values[up + 1];
				values[up + 1] = temp;

				tempIndex = originalIndices[down / 2];
				originalIndices[down / 2] = originalIndices[up / 2];
				originalIndices[up / 2] = tempIndex;
			}
		}
		if (x > values[up] || (x == values[up] && (yDown ? y < values[up + 1] : y > values[up + 1]))) {
			values[lower] = values[up];
			values[up] = x;

			values[lower + 1] = values[up + 1];
			values[up + 1] = y;

			tempIndex = originalIndices[lower / 2];
			originalIndices[lower / 2] = originalIndices[up / 2];
			originalIndices[up / 2] = tempIndex;
		}
		return up;
	}

}
