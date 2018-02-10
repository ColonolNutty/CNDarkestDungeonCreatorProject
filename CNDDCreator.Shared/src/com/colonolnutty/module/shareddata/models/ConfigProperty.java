package com.colonolnutty.module.shareddata.models;

import com.colonolnutty.module.shareddata.utils.CNCollectionUtils;
import com.colonolnutty.module.shareddata.utils.CNStringUtils;

import java.util.ArrayList;

/**
 * User: Jack's Computer
 * Date: 01/13/2018
 * Time: 2:31 PM
 */
public class ConfigProperty {
    public PropertyName Name;
    private ArrayList<String> _values;

    public ConfigProperty(PropertyName name, ArrayList<String> values) {
        Name = name;
        _values = values;
    }

    public String getSingleValue() {
        if(_values == null || CNCollectionUtils.hasMultiple(_values)) {
            return null;
        }
        return _values.get(0);
    }

    public ArrayList<String> getValues() {
        return _values;
    }

    public ArrayList<Integer> getIntValues() {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for(String val : _values) {
            values.add(Integer.parseInt(val));
        }
        return values;
    }

    public ArrayList<Double> getDoubleValues() {
        ArrayList<Double> values = new ArrayList<Double>();
        for(String val : _values) {
            values.add(Double.parseDouble(val));
        }
        return values;
    }

    public ArrayList<Boolean> getBooleanValues() {
        ArrayList<Boolean> values = new ArrayList<Boolean>();
        for(String val : _values) {
            String lower = val.toLowerCase();
            if(lower.equals("true")) {
                values.add(true);
            }
            else if(lower.equals("false")) {
                values.add(false);
            }
        }
        return values;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        ValueType valueType = PropertyValueTypes.getValueType(Name);
        builder.append(".");
        builder.append(Name);
        for(String value : _values) {
            builder.append(" ");
            String result = formatValue(value, valueType);
            if (result == null) {
                continue;
            }
            builder.append(result);
        }
        return builder.toString();
    }

    public String formatValue(String value, ValueType valueType) {
        if(value == null || valueType == null) {
            return null;
        }
        switch(valueType) {
            case StrEsc:
                return "\"" + CNStringUtils.escapeString(value) + "\"";
            case DblPerc:
                return value + "%";
            default:
                return value;
        }
    }
}
