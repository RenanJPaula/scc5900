package br.com.usp.dtw;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class TreeNearestNeighbor implements NearestNeighbor {
	
	@Override
	public int computeAccuracyRate(final List<Double[]> trainingSeries, final List<Double[]> testSeries) {
		return computeAccuracyRate(trainingSeries, testSeries, null);
	}
	
	@Override
	public int computeAccuracyRate(final List<Double[]> trainingSeries, final List<Double[]> testSeries, final Integer w) {
		int accuracy = 0;
		for (int i = 0; i < testSeries.size(); i+=3) {
			final Double[] testSerie1 = testSeries.get(i);
			final Double[] testSerie2 = testSeries.get(i + 1);
			final Double[] testSerie3 = testSeries.get(i + 2);
			final Double[] dtwTestSerie1 = (Double[]) ArrayUtils.remove(testSerie1, 0);
			final Double[] dtwTestSerie2 = (Double[]) ArrayUtils.remove(testSerie2, 0);
			final Double[] dtwTestSerie3 = (Double[]) ArrayUtils.remove(testSerie3, 0);
			double target = 0, distance = Double.POSITIVE_INFINITY;	
			for (int j = 0; j < trainingSeries.size(); j+=3) {
				final Double[] trainingSerie1 = trainingSeries.get(j);
				final Double[] trainingSerie2 = trainingSeries.get(j + 1);
				final Double[] trainingSerie3 = trainingSeries.get(j + 2);
				final Double[] dtwTrainingSerie1 = (Double[]) ArrayUtils.remove(trainingSerie1, 0);
				final Double[] dtwTrainingSerie2 = (Double[]) ArrayUtils.remove(trainingSerie2, 0);
				final Double[] dtwTrainingSerie3 = (Double[]) ArrayUtils.remove(trainingSerie3, 0);
				Double dtwDistance;
				if (w == null) {
					dtwDistance = DTW.getInstance().dtwDistance(dtwTrainingSerie1, dtwTestSerie1);
					dtwDistance += DTW.getInstance().dtwDistance(dtwTrainingSerie2, dtwTestSerie2);
					dtwDistance += DTW.getInstance().dtwDistance(dtwTrainingSerie3, dtwTestSerie3);
				} else {
					dtwDistance = DTW.getInstance().dtwDistance(dtwTrainingSerie1, dtwTestSerie1, w);
					dtwDistance += DTW.getInstance().dtwDistance(dtwTrainingSerie2, dtwTestSerie2, w);
					dtwDistance += DTW.getInstance().dtwDistance(dtwTrainingSerie3, dtwTestSerie3, w);
				}
				if (dtwDistance < distance) {
					distance = dtwDistance;
					target = trainingSerie1[0];
				}
			}
			if (testSerie1[0].equals(target)) {
				accuracy++;
			}
		}
		return accuracy;
	}
	
	private TreeNearestNeighbor() {
		super();
	}

	private static class OneNearestNeighborHolder {
		public static final TreeNearestNeighbor INSTANCE = new TreeNearestNeighbor();
	}

	public static TreeNearestNeighbor getInstance() {
		return OneNearestNeighborHolder.INSTANCE;
	}
}
