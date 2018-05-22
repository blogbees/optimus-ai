package org.optimus.ai.scanner;

public interface Scanner<INPUT, OUTPUT> {

	public abstract OUTPUT scan(INPUT input);

}
