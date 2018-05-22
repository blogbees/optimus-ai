package org.optimus.ai.image.analyzer;

import java.io.File;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.optimus.ai.image.analyzer.ImageAnalyzer;
import org.optimus.ai.system.analyzers.Analyzer;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class ImageAnalyzerTests extends TestCase {

	private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.###");

	@Test
	public void testImageFile() throws Exception {
		File inputFile = new File("src/test/resources/image_with_cats.jpg");
		File inceptionFile = new File("src/test/resources/tensorflow_inception_graph.pb");
		File labelFile = new File("src/test/resources/imagenet_comp_graph_label_strings.txt");
		Analyzer<byte[], Map<String, Object>> analyzer = new ImageAnalyzer(inceptionFile, labelFile);
		Map<Double, String> sortedImageDetails = new TreeMap<>(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				return o2.compareTo(o1);
			}
		});
		Map<String, Object> imageDetails = analyzer.analyze(Files.readAllBytes(inputFile.toPath()));
		System.out.println("Image Details =" + imageDetails);
		assertNotNull(imageDetails);
		assertNotNull(imageDetails.get("best_match"));
		assertNotNull(imageDetails.get("best_match_label"));
		assertNotNull(imageDetails.get("best_match_probability"));
		@SuppressWarnings("unchecked")
		List<String> labels = (List<String>) imageDetails.get("labels");
		int i = 0;
		for (String label : labels) {
			sortedImageDetails.put(
					Double.valueOf(DECIMAL_FORMAT.format(((float[]) imageDetails.get("labelProbabilities"))[i])),
					label);
			i++;
		}
		for (Double key : sortedImageDetails.keySet()) {
			System.out.println("Key-" + key + " Label-" + sortedImageDetails.get(key));
		}
	}
}
