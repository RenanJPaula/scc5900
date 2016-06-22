package br.com.usp.dtw;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class OneNearestNeighbor implements NearestNeighbor {
	@Override
	public int computeAccuracyRate(final List<Double[]> trainingSeries, final List<Double[]> testSeries) {
		return computeAccuracyRate(trainingSeries, testSeries, null);
	}
	
	@Override
	public int computeAccuracyRate(final List<Double[]> trainingSeries, final List<Double[]> testSeries, final Integer w) {
		int accuracy = 0;
		for (Double[] rowTestSerie : testSeries) {
			double target = 0, distance = Double.POSITIVE_INFINITY;
			final Double[] testSerie = (Double[]) ArrayUtils.remove(rowTestSerie, 0);
			for (Double[] rowTrainingSerie : trainingSeries) {
				final Double[] trainingSerie = (Double[]) ArrayUtils.remove(rowTrainingSerie, 0);
				final Double dtwDistance;
				if (w == null) {
					dtwDistance = DTW.getInstance().dtwDistance(trainingSerie, testSerie);
				} else {
					dtwDistance = DTW.getInstance().dtwDistance(trainingSerie, testSerie, w);
				}
				if (dtwDistance < distance) {
					distance = dtwDistance;
					target = rowTrainingSerie[0];
				}
			}
			if (rowTestSerie[0].equals(target)) {
				accuracy++;
			}
		}
		return accuracy;
	}
	
	private OneNearestNeighbor() {
		super();
	}

	private static class OneNearestNeighborHolder {
		public static final OneNearestNeighbor INSTANCE = new OneNearestNeighbor();
	}

	public static OneNearestNeighbor getInstance() {
		return OneNearestNeighborHolder.INSTANCE;
	}
}