package org.optimus.ai.image.analyzer;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.optimus.ai.system.analyzers.Analyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tensorflow.TensorFlow;

public class ImageAnalyzer implements Analyzer<byte[], Map<String, Object>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageAnalyzer.class);

	private File inceptionFile = null;

	private File graphFile = null;

	public ImageAnalyzer(final File inceptionFile, final File graphFile) {
		this.inceptionFile = inceptionFile;
		this.graphFile = graphFile;
	}

	public ImageAnalyzer() {
		try {
			this.inceptionFile = Paths
					.get(getClass().getClassLoader().getResource("tensorflow_inception_graph.pb").toURI()).toFile();
			this.graphFile = Paths
					.get(getClass().getClassLoader().getResource("imagenet_comp_graph_label_strings.txt").toURI())
					.toFile();
		} catch (final URISyntaxException e) {
			LOGGER.error("Exception occurred in initializing ImageAnalyzer", e);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> analyze(byte[] input) throws Exception {
		LOGGER.info("Using TensorFlow version :"+TensorFlow.version());
		Map<String, Object> returnResults = new LinkedHashMap<>();
		Map<String, Object> results = ImageInception.analyze(input, Files.readAllBytes(inceptionFile.toPath()),
				graphFile);
		if (results != null) {
			int bestIndex = ImageInception.maxIndex((float[]) results.get("labelProbabilities"));
			returnResults.put("best_match", bestIndex);
			returnResults.put("best_match_label", ((List<String>) results.get("labels")).get(bestIndex));
			returnResults.put("best_match_probability", ((float[]) results.get("labelProbabilities"))[bestIndex]);
			returnResults.putAll(results);
		}
		return returnResults;
	}

	public File getInceptionFile() {
		return inceptionFile;
	}

	public void setInceptionFile(File inceptionFile) {
		this.inceptionFile = inceptionFile;
	}

	public File getGraphFile() {
		return graphFile;
	}

	public void setGraphFile(File graphFile) {
		this.graphFile = graphFile;
	}

}
