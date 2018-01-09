package com.colonolnutty.module.shareddata.models;

import com.colonolnutty.module.shareddata.utils.CNStringUtils;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 1:03 PM
 */
public abstract class BaseConfig {
    public static <T> ArrayList<T> getOrNull(Hashtable<String, ArrayList<Object>> properties, String name) {
        if(CNStringUtils.isNullOrWhitespace(name)
                || properties == null
                || properties.isEmpty()
                || !properties.containsKey(name)) {
            return null;
        }

        ArrayList<Object> arr = properties.get(name);

        if(arr.isEmpty() || arr.size() > 1) {
            return null;
        }

        ArrayList<T> arrOut = new ArrayList<T>();
        for(Object obj : arr) {
            arrOut.add((T) obj);
        }
        return arrOut;
    }

    public static <T> T getSingleOrNull(Hashtable<String, ArrayList<Object>> properties, String name) {
        if(CNStringUtils.isNullOrWhitespace(name)
                || properties == null
                || properties.isEmpty()
                || !properties.containsKey(name)) {
            return null;
        }

        ArrayList<Object> arr = properties.get(name);

        if(arr.isEmpty() || arr.size() > 1) {
            return null;
        }

        return (T)arr.get(0);
    }

    public static Hashtable<String, ArrayList<Object>> parseProperties(String name, String str) {
        String replacedStr = str.replace(name + ": ", "");
        String[] split = replacedStr.split(" ");

        Hashtable<String, ArrayList<Object>> properties = new Hashtable<String, ArrayList<Object>>();
        String currentProperty = null;
        ArrayList<Object> currentValues = null;
        for(int i = 0; i < split.length; i++) {
            String property = split[i];
            if(currentProperty == null || (CNStringUtils.isNullOrWhitespace(property) && !currentProperty.equals("target"))) {
                continue;
            }
            if(!property.startsWith(".")) {
                if(currentValues != null) {
                    property = property.replace("%", "");
                    currentValues.add(parseObject(property));
                }
                continue;
            }
            if(currentProperty != null
                    && currentValues != null
                    && !properties.containsKey(currentProperty)) {
                properties.put(currentProperty, currentValues);
            }
            currentProperty = property.replaceFirst("\\.", "");
            currentValues = new ArrayList<Object>();
        }
        return properties;
    }

    public String formatStrings(String name, boolean escapeStringValue, String... values) {
        if(CNStringUtils.isNullOrWhitespace(name) || values == null || values.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(formatName(name));
        boolean hasValue = false;
        for(String value : values) {
            if(CNStringUtils.isNullOrWhitespace(value)) {
                continue;
            }
            hasValue = true;
            builder.append(" ");
            builder.append(formatValue(value, escapeStringValue));
        }
        if(!hasValue) {
            return "";
        }
        return builder.toString();
    }

    public String formatDoubles(String name, boolean trimPercent, boolean includePercent, Double... values) {
        if(values == null || values.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(formatName(name));
        boolean hasValue = false;
        for(Double value : values) {
            if(value == null) {
                continue;
            }
            builder.append(" ");
            String valStr;
            if(trimPercent) {
                valStr = trimDouble(value);
            }
            else {
                valStr = value.toString();
            }
            hasValue = true;
            builder.append(formatValue(valStr, false));
            if(includePercent) {
                builder.append("%");
            }
        }
        if(!hasValue) {
            return "";
        }
        return builder.toString();
    }

    public String formatObjects(String name, Object... values) {
        if(name == null || values == null && values.length > 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(formatName(name));
        boolean hasValue = false;
        for(Object value : values) {
            if(value == null || value instanceof Double || value instanceof String) {
                continue;
            }
            String valStr = value.toString();
            if(CNStringUtils.isNullOrWhitespace(valStr)) {
                continue;
            }
            hasValue = true;
            builder.append(" ");
            builder.append(formatValue(valStr, false));
        }
        if(!hasValue) {
            return "";
        }
        return builder.toString();
    }

    private String formatValue(String value, boolean escapeValue) {
        if(CNStringUtils.isNullOrWhitespace(value)) {
            return "";
        }
        String valStr = value.toString();
        if(escapeValue && value instanceof String) {
            return "\"" + CNStringUtils.escapeString(valStr) + "\"";
        }
        return valStr;
    }

    private String trimDouble(Double value) {
        if(value == null) {
            return "";
        }
        return value.intValue() + "";
    }

    private String formatName(String name) {
        if(CNStringUtils.isNullOrWhitespace(name)) {
            return "";
        }
        return " ." + name;
    }

    private static Object parseObject(String str) {
        try {
            return Boolean.parseBoolean(str);
        }
        catch(Exception e) {}
        try {
            return Integer.parseInt(str);
        }
        catch(Exception e) {}
        try {
            return Double.parseDouble(str);
        }
        catch(Exception e) {}
        return str;
    }
}
