package com.zookao;

import java.lang.instrument.Instrumentation;

public class HelloAgent {
	public static void premain(String agentArgs, Instrumentation inst) {
		SimpleClassTransformer sct = new SimpleClassTransformer();
		inst.addTransformer(sct);
	}
}
