package com.ideas2it.ems.dto;

/**
 * <p>
 *     This dto represents project details
 *    id - ID of the project
 *    name - Name of the project
 * </p>
 *
 * @author JeevithaKesavaraj
 */

public class ProjectDto {
    private int id;
    private String name;

    public ProjectDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
