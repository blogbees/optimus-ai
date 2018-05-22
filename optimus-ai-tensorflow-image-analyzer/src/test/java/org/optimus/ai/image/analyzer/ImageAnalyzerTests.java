package org.optimus.ai.image.analyzer;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.optimus.ai.system.analyzers.Analyzer;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class ImageAnalyzerTests extends TestCase {

	private File inceptionFile;		
	private File labelFile;
	@Before
	public void setUp(){
		inceptionFile = new File("src/test/resources/tensorflow_inception_graph.pb");
		labelFile = new File("src/test/resources/imagenet_comp_graph_label_strings.txt");
	}

	@Test
	public void testImage_Tiger() throws Exception {
		File inputFile = new File("src/test/resources/image_with_cats.jpg");
		Analyzer<byte[], Map<String, Object>> analyzer = new ImageAnalyzer(inceptionFile, labelFile);
		Map<String, Object> imageDetails = analyzer.analyze(Files.readAllBytes(inputFile.toPath()));
		assertNotNull(imageDetails);
		assertNotNull(imageDetails.get("best_match"));
		assertEquals(String.valueOf(imageDetails.get("best_match")), "76");
		assertNotNull(imageDetails.get("best_match_label"));
		assertEquals(String.valueOf(imageDetails.get("best_match_label")), "tiger");
		assertNotNull(imageDetails.get("best_match_probability"));
		assertNotNull(imageDetails.get("labels"));
		assertNotNull(imageDetails.get("labelProbabilities"));
	}
}
