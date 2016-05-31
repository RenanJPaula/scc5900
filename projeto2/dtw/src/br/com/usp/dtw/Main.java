package br.com.usp.dtw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

	public static final Pattern SEPARATOR = Pattern.compile(" ");

	public static void main(String[] args) throws ParseException, IOException {
		Options options = new Options();
		options.addOption("h", "help", false, "command overview");
		options.addOption("trf", "trainingfile", true, "traning file path [required]");
		options.addOption("tef", "testfile", true, "test file path [required]");
		options.addOption("w", "bandwidth", true, "band width value");
		options.addOption("3d", "tridimentional", false, "active tridimentional approach");

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);

		final boolean help = cmd.hasOption("h");
		final String trainingFile = cmd.getOptionValue("trf");
		final String testFile = cmd.getOptionValue("tef");
		final Integer bandWidth = cmd.hasOption("w") ? Integer.valueOf(cmd.getOptionValue("w")) : -1;
		final boolean is3d = cmd.hasOption("3d");

		if (help) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("DTW", options);
		} else if (trainingFile == null || testFile == null) {
			System.err.println("Traning file and Test file is required. See more in -h or --help option.");
		} else {
			final List<Double[]> trainingValues = Main.fileToTemporalValues(trainingFile);
			final List<Double[]> testValues = Main.fileToTemporalValues(testFile);

			final NearestNeighbor classificassion = is3d ? TreeNearestNeighbor.getInstance() : OneNearestNeighbor.getInstance();

			final long startTime = System.nanoTime();

			final double accuracyRate;

			if (bandWidth >= 0) {
				accuracyRate = classificassion.computeAccuracyRate(trainingValues, testValues, bandWidth);
			} else {
				accuracyRate = classificassion.computeAccuracyRate(trainingValues, testValues);
			}

			final long endTime = System.nanoTime();

			final int total = is3d ? testValues.size() / 3 : testValues.size();
			System.out.printf("Razão de Acertos: %.0f/%d%s", accuracyRate, total, System.lineSeparator());

			final double acurracyPercentage = accuracyRate / total * 100D;
			System.out.printf("Taxa de Acertos: %.2f%%%s", acurracyPercentage, System.lineSeparator());

			final long elapsedTime = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
			System.out.printf("Tempo de Execucao: %.3fs", elapsedTime / 1000D);

		}
	}

	private static List<Double[]> fileToTemporalValues(final String fileUri) throws IOException {
		final Path path = Paths.get(fileUri);
		final List<Double[]> temporalSeries = new ArrayList<>();
		Files.lines(path).forEach(row -> {
			final Double[] serie = Stream.of(SEPARATOR.split(row)).map(Double::parseDouble).toArray(Double[]::new);
			temporalSeries.add(serie);
		});
		return temporalSeries;
	}

}
