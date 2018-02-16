package com.neev.moh.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

@Component
public final class CacheKeyUtils {

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(CacheKeyUtils.class.getName());

	public static <K extends Comparable<K>> String key(Collection<K> col) {
		if (col == null) {
			return "";
		}
		final List<K> sorted = new ArrayList<K>(col);
		if (col.size() > 1) {
			Collections.sort(sorted);
		}
		final StringBuilder b = new StringBuilder("[");
		for (K entry : sorted) {
			if (entry != null) {
				b.append(entry);
				b.append(",");
			}
		}
		b.append("]");
		return b.toString();
	}

	public static String listKey(List<String> list) {
		if (list != null) {
			if (list.size() > 1)
				Collections.sort(list);
			StringBuilder sb = new StringBuilder();
			for (String str : list) {
				sb.append(str);
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	public static <K extends Comparable<K>> String mapKey(Map<K, ?> col) {
		if (col == null) {
			return "";
		}
		final List<K> sorted = new ArrayList<K>(col.keySet());
		if (col.size() > 1) {
			Collections.sort(sorted);
		}
		final StringBuilder b = new StringBuilder("[");
		for (K entry : sorted) {
			if (entry != null) {
				b.append(entry);
				b.append("|");
				b.append(col.get(entry));
				b.append(",");
			}
		}
		b.append("]");
		return encryptedKey(b.toString());
	}

	public static String string(String col) {
		if (col != null)
			return col.replaceAll(" ", "_");
		return "";
	}

	public static String string(boolean col) {
		return col + "";
	}

	public static String encryptedKey(String col) {
		String hashKey = "";
		if (col != null) {
			hashKey = DigestUtils.md5DigestAsHex((col).getBytes());
		}
		logger.log(MohLogger.INFO, "encryptedKey() = " + hashKey);
		return hashKey;
	}

	public static String generateKey(Object... param) {
		StringBuffer buf = new StringBuffer();
		for (Object n : param) {
			if (n != null)
				buf.append(String.valueOf(n));
		}
		return encryptedKey(buf.toString());
	}

	public static void main(String[] args) {
		System.out.println(generateKey("abc", 123, true, null));
	}

}
