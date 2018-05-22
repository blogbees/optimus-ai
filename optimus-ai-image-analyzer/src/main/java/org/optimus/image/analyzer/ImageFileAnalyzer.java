package org.optimus.image.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.face.detection.CLMDetectedFace;
import org.openimaj.image.processing.face.detection.CLMFaceDetector;
import org.optimus.ai.system.analyzers.Analyzer;
import org.optimus.ai.system.exceptions.AnalyzerException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ImageFileAnalyzer implements Analyzer<File, Map<String, Object>> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.optimus.ai.system.analyzers.Analyzer#analyze(java.lang.Object)
	 */
	public Map<String, Object> analyze(File input) throws AnalyzerException {
		Map<String, Object> imageDetails = new LinkedHashMap<>();
		try {
			MBFImage image = ImageUtilities.readMBF(input);
			FImage imageByPixels = ImageUtilities.readF(input);
			imageDetails.put("height", image.getHeight());
			imageDetails.put("width", image.getWidth());
			imageDetails.put("colorspace_name", image.getColourSpace().name());
			// extract text
			imageDetails.put("text_in_image", extractText(input));
			// extract faces
			imageDetails.put("faces", extractFaces(imageByPixels));

		} catch (IOException e) {
			throw new AnalyzerException("Exception occured when parsing the file to construct the image", e);
		}
		return imageDetails;
	}

	/**
	 * @param input
	 */
	public void extractDetails(FImage input) {

	}

	/**
	 * @param input
	 */
	public String extractText(File input) throws AnalyzerException {
		ITesseract instance = new Tesseract();
		String result = StringUtils.EMPTY;
		try {
			instance.setLanguage("english");
			instance.setDatapath("src/main/resources/data");
			result = instance.doOCR(input);
		} catch (TesseractException e) {
			throw new AnalyzerException("Exception occured when tessarcting the image", e);
		}
		return result;
	}

	/**
	 * 
	 */
	public void extractMiscInformation() {

	}

	public List<Map<String, Object>> extractFaces(FImage image) {
		Map<String, Object> properties = new LinkedHashMap<>();
		List<Map<String, Object>> faces = new ArrayList<>();
		CLMFaceDetector det3 = new CLMFaceDetector();
		List<CLMDetectedFace> clmDetectedFaces = det3.detectFaces(image);
		for (CLMDetectedFace clmDetectedFace : clmDetectedFaces) {
			properties = new LinkedHashMap<>();
			properties.put("face_confidence", clmDetectedFace.getConfidence());
			properties.put("face_shape_polygon", clmDetectedFace.getShape().asPolygon());
			faces.add(properties);
		}
		return faces;
	}

	public void extractObjects() {
		
	}

	public void extractPersonDetails() {

	}

}
