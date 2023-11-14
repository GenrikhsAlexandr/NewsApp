package com.genrikhsalexandr.headlinesfeature.data.dto;

import java.util.Objects;

public class SourceDto {

    private String id;
    private String name;

    public SourceDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceDto sourceDto = (SourceDto) o;
        return Objects.equals(id, sourceDto.id) &&
                Objects.equals(name, sourceDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SourceDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
