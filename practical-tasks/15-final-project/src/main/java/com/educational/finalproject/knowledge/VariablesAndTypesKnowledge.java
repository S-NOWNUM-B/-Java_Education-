package com.educational.finalproject.knowledge;

import java.util.HashMap;
import java.util.Map;

/**
 * # Переменные и типы данных (Variables & Types)
 *
 * ### Целочисленные
 * *   `byte`: 1 байт (-128...127)
 * *   `short`: 2 байта
 * *   `int`: 4 байта (основной тип)
 * *   `long`: 8 байт (суффикс `L`)
 *
 * ### С плавающей точкой
 * *   `float`: 4 байта (суффикс `f`)
 * *   `double`: 8 байт (основной тип)
 *
 * ### Прочие
 * *   `char`: 2 байта (Unicode символ)
 * *   `boolean`: `true`/`false`
 *
 * ### Ссылочные типы
 * Классы, интерфейсы, массивы. Хранят ссылку на объект в куче (Heap).
 */
public class VariablesAndTypesKnowledge {
    private byte smallNumber;
    private short mediumNumber;
    private int standardNumber;
    private long largeNumber;
    private float decimalFloat;
    private double decimalDouble;
    private char character;
    private boolean flag;
    private String text;

    public byte getSmallNumber() { return smallNumber; }
    public void setSmallNumber(byte smallNumber) { this.smallNumber = smallNumber; }

    public short getMediumNumber() { return mediumNumber; }
    public void setMediumNumber(short mediumNumber) { this.mediumNumber = mediumNumber; }

    public int getStandardNumber() { return standardNumber; }
    public void setStandardNumber(int standardNumber) { this.standardNumber = standardNumber; }

    public long getLargeNumber() { return largeNumber; }
    public void setLargeNumber(long largeNumber) { this.largeNumber = largeNumber; }

    public float getDecimalFloat() { return decimalFloat; }
    public void setDecimalFloat(float decimalFloat) { this.decimalFloat = decimalFloat; }

    public double getDecimalDouble() { return decimalDouble; }
    public void setDecimalDouble(double decimalDouble) { this.decimalDouble = decimalDouble; }

    public char getCharacter() { return character; }
    public void setCharacter(char character) { this.character = character; }

    public boolean isFlag() { return flag; }
    public void setFlag(boolean flag) { this.flag = flag; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Map<String, Object> getAllDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("byte", smallNumber);
        details.put("short", mediumNumber);
        details.put("int", standardNumber);
        details.put("long", largeNumber);
        details.put("float", decimalFloat);
        details.put("double", decimalDouble);
        details.put("char", character);
        details.put("boolean", flag);
        details.put("String", text);
        return details;
    }
}
