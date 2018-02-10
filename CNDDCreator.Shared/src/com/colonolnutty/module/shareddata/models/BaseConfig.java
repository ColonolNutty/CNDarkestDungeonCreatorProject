package com.colonolnutty.module.shareddata.models;

import com.colonolnutty.module.shareddata.utils.CNStringUtils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Jack's Computer
 * Date: 01/07/2018
 * Time: 1:03 PM
 */
public class BaseConfig {
    private String _name;
    private ArrayList<PropertyName> _propertyNames;
    private ArrayList<ConfigProperty> _properties;

    protected BaseConfig(String name, ArrayList<ConfigProperty> properties) {
        _name = name;
        _properties = properties;
    }

    public void setProperties(ArrayList<ConfigProperty> properties) {
        _properties = properties;
    }

    public ConfigProperty getProperty(PropertyName propertyName) {
        if(_properties == null) {
            return null;
        }
        ConfigProperty property = null;
        for(ConfigProperty prop : _properties) {
            if(prop.Name.equals(propertyName)) {
                property = prop;
                break;
            }
        }
        return property;
    }

    public String getConfigName() {
        return _name;
    }

    public ArrayList<PropertyName> getPropertyNames() {
        if(_propertyNames != null) {
            return _propertyNames;
        }
        if(_properties == null) {
            _propertyNames = new ArrayList<PropertyName>();
            return _propertyNames;
        }
        _propertyNames = new ArrayList<PropertyName>();

        for(ConfigProperty prop : _properties) {
            if(!_propertyNames.contains(prop.Name)) {
                _propertyNames.add(prop.Name);
            }
        }
        return _propertyNames;
    }

    public static BaseConfig parse(String str) {
        String[] split = str.split(":");
        String configName = split[0];
        String strValue = split[1];

        String mainPatternStr = "\\.(\\w+)((?:(?:\\s\"\\w+(?:\\s+\\w+)?\")+)|(?:(?:\\s\\d+?(?:\\.?\\d+)?%?)+)|\\s\\w+|\\s+)";

        Pattern pattern = Pattern.compile(mainPatternStr);

        String valuesPatternStr = "(?:(\"\\w+(?:\\s+\\w+)?\"))|(?:(\\d+?(?:\\.?\\d+)?%?))|(?:(\\w+))";
        Pattern valuesPattern = Pattern.compile(valuesPatternStr);

        ArrayList<ConfigProperty> properties = new ArrayList<ConfigProperty>();

        Matcher m = pattern.matcher(strValue);
        while(m.find()) {
            for(int i = 1; i < m.groupCount(); i++) {
                PropertyName name = null;
                try {
                    name = Enum.valueOf(PropertyName.class, m.group(1));
                }
                catch(Exception e) { continue; }
                String valuesStr = m.group(2).trim();
                Matcher valueMatches = valuesPattern.matcher(valuesStr);

                ArrayList<String> values = new ArrayList<String>();
                if(CNStringUtils.isNullOrWhitespace(valuesStr)) {
                    values.add(" ");
                    properties.add(new ConfigProperty(name, values));
                    continue;
                }
                while(valueMatches.find()) {
                    String strMatch = valueMatches.group(1);
                    String numberMatch = valueMatches.group(2);
                    String wordMatch = valueMatches.group(3);
                    if(strMatch != null) {
                        values.add(strMatch.trim().replace("\"", ""));
                    }
                    else if(numberMatch != null) {
                        values.add(numberMatch.trim().replace("%", ""));
                    }
                    else if(wordMatch != null) {
                        values.add(wordMatch.trim());
                    }
                }
                properties.add(new ConfigProperty(name, values));
            }
        }
        return new BaseConfig(configName, properties);
    }

    public static BaseConfig parseEmpty() {
        return new BaseConfig("Empty", null);
    }

    protected ArrayList<String> copy(String... arr) {
        ArrayList<String> otherArr = new ArrayList<String>();

        for(String val : arr) {
            otherArr.add(val);
        }
        return otherArr;
    }

    protected  <T> ArrayList<T> copy(ArrayList<BaseConfig> arr) {
        ArrayList<T> otherArr = new ArrayList<T>();

        for(BaseConfig val : arr) {
            otherArr.add((T) val.copy());
        }
        return otherArr;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getConfigName());
        builder.append(":");

        for(ConfigProperty property : _properties) {
            builder.append(" ");
            builder.append(property.toString());
        }
        return builder.toString();
    }
}
