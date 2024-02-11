package org.example.rectangle;

import javafx.util.StringConverter;

public class RoundedDoubleStringConverter extends StringConverter<Number> {
    private int decimalPlaces;

    public RoundedDoubleStringConverter(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    @Override
    public String toString(Number object) {
        if (object == null) {
            return "";
        }
        return String.format("%." + decimalPlaces + "f", object.doubleValue());
    }

    @Override
    public Number fromString(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}