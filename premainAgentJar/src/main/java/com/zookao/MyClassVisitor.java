package com.zookao;

import jdk.internal.org.objectweb.asm.*;

public class MyClassVisitor extends ClassVisitor implements Opcodes {
	private static String methodName;

	public MyClassVisitor(ClassVisitor cv) {
		super(ASM5, cv);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		cv.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
		if (name.equals("test") && mv != null) {
			System.out.println("loading class com/zookao/AopInteceptor");
			methodName = name;
			mv = new MyMethodVisitor(mv);
		}
		return mv;
	}

	class MyMethodVisitor extends MethodVisitor implements Opcodes {
		public MyMethodVisitor(MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}
		
		public void visitCode() {
			super.visitCode();
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			// 将待输出信息放入栈顶
			mv.visitLdcInsn("method " + methodName + " is starting");
			//调用println方法
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "()V", false);
		}

		public void visitInsn(int opcode) {
		}
	}
}