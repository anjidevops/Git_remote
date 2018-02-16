package com.neev.moh.logger;

public interface MohLogger {
	public static final int TRACE = 1;
	public static final int INFO = 2;
	public static final int DEBUG = 3;
	public static final int WARN = 4;
	public static final int ERROR = 5;
	public static final int OFF = 6;

	public void log(int level, Object message);
	public void log(int level, Object message, Throwable thrower);
}