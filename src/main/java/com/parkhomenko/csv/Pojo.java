package com.parkhomenko.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;

/**
 * If remove  @JsonProperty than it will not work! Because you have NOT public access on fields.
 * Without @JsonPropertyOrder - it will not work,
 * because building a schema (CsvMapper#schemaFor) is using default serialization settings including ordering.
 */
@JsonPropertyOrder({"age", "phone","email", "name", "date" })
class Pojo {

    @JsonProperty("age")
    Integer age;

    @JsonProperty("phone")
    String phone;

    @JsonProperty("name")
    String name;

    @JsonProperty("date")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    LocalDate date;
}