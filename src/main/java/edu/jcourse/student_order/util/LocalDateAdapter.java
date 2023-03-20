package edu.jcourse.student_order.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    public static final String PATTERN = "dd.MM.yyyy";

    @Override
    public LocalDate unmarshal(String v) {
        return LocalDate.parse(v, DateTimeFormatter.ofPattern(PATTERN));
    }

    @Override
    public String marshal(LocalDate v) {
        return v.format(DateTimeFormatter.ofPattern(PATTERN));
    }
}
