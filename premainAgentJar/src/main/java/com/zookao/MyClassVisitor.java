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
			mv.visitLdcInsn("loading class com/zookao/AopInteceptor");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
			mv.visitMethodInsn(INVOKESTATIC, "com/zookao/AopInteceptor", "before", "()V", false);
		}

		public void visitInsn(int opcode) {
			if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {
				mv.visitMethodInsn(INVOKESTATIC, "com/zookao/AopInteceptor", "after", "()V", false);
			}
			mv.visitInsn(opcode);
		}
	}
}