package com.example.gitrepoinspector.model.DTOs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class GitRepository {
    @JsonProperty(value = "name", index = 1)
    String repositoryName;
    @JsonProperty(value = "fork", access = JsonProperty.Access.WRITE_ONLY)
    boolean fork;
    @JsonProperty(value = "owner", access = JsonProperty.Access.WRITE_ONLY)
    Owner owner;
    @JsonProperty(index = 2)
    List<GitBranch> branches;

    @JsonProperty(index = 0)
    @JsonGetter
    public String getOwnerName() {
        if (owner == null) {
            return null;
        }
        return owner.getLogin();

    }
}
