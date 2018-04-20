package com.intothemobile.fwk.support.jquery;

import org.springframework.util.StringUtils;

import com.intothemobile.fwk.ancestors.ItmParam;

public class JTableUtil {
	public static void setPagingEntity(ItmParam pagingParam, JTableParam jTable) {
		if (pagingParam == null || jTable == null) return;
		
		long p = jTable.getJtStartIndex() / jTable.getJtPageSize();
		pagingParam.getPage().setPage(p+1);
		pagingParam.getPage().setRequestedRows(jTable.getJtPageSize());
		
		if (jTable.getJtSorting() != null) {
			String[] s = jTable.getJtSorting().split(" ");
			
			pagingParam.getPage().setSortIndex(fromBeanCamel(s[0]));
			if (s.length > 0) {
				pagingParam.getPage().setSortOrder(s[1]);
			}
		}
	}
	
	public static String toCamelCase(String s) {
		return toCamelCase(s, "_", 0);
	}

	public static String toBeanCamel(String s) {
		return toCamelCase(s, "_", 1);
	}

	public static String fromBeanCamel(String s) {
		return fromCamelCase(s, "_", 1);
	}
	
	private static String fromCamelCase(String s, String delim, int start) {
		if (s == null) return null;
		
		String splitString = "";
		for (int i = 0; i < s.length(); i++) {
			String ss = s.charAt(i) + "";
			
			if (i >= start && ss.toUpperCase().equals(ss))
				splitString += delim + ss;
			else
				splitString += ss.toUpperCase();
		}

		return splitString;
	}
	
	private static String toCamelCase(String s, String delim, int start) {
		if (StringUtils.isEmpty(s)) {
			return "";
		}
		
		String[] parts = s.split(delim);
		String camelCaseString = "";
		if (parts.length > start) {
			for (int i=start; i < parts.length; i++) {
				String part = parts[i];
				if (part != null && part.trim().length() > 0)
					camelCaseString += toProperCase(part);
				else
					camelCaseString += part;
			}
		} else {
			camelCaseString = toProperCase(s);
		}
		
		return camelCaseString;
	}

	private static String toProperCase(String s) {
		return s.trim().substring(0, 1).toUpperCase() + s.trim().substring(1).toLowerCase();

	}
}
