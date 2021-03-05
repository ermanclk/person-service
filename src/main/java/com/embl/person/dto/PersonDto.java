package com.embl.person.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PersonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @Size(min = 1, max = 60)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 60)
    private String lastName;

    @Min(0)
    private int age;

    private String favoriteColor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public PersonDto(String firstName, String lastName, int age, String favoriteColor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favoriteColor = favoriteColor;
    }

    public PersonDto() {
    }
}
