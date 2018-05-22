package org.optimus.ai.system.analyzers;

public interface Analyzer<INPUT, OUTPUT> {

	public abstract OUTPUT analyze(INPUT input) throws Exception;

}
