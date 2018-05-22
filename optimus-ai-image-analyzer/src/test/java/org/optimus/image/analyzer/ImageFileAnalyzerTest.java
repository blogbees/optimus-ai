package org.optimus.image.analyzer;


import java.io.File;
import java.util.Map;

import org.junit.Test;
import org.optimus.ai.system.analyzers.Analyzer;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class ImageFileAnalyzerTest 
    extends TestCase
{
  
	@Test
	public void testImageFile() throws Exception{
		Analyzer<File , Map<String, Object>> analyzer = new ImageFileAnalyzer();
		File file = new File("src/test/resources/image_with_text.jpg");
		Map<String, Object> imageDetails = analyzer.analyze(file);
		System.out.println("Image Details ="+imageDetails);
		assertNotNull(imageDetails);
		assertNotNull(imageDetails.get("height"));
		assertNotNull(imageDetails.get("width"));
		assertNotNull(imageDetails.get("colorspace_name"));
	}
}
