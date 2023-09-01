package com.example.gitrepoinspector.model.DTOs;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class GitBranch {
    @JsonProperty(value = "name", index = 0)
    String name;
    @JsonProperty(value = "commit", access = JsonProperty.Access.WRITE_ONLY)
    Commit commit;

    @JsonProperty(index = 1)
    @JsonGetter
    public String getSha() {
        if (commit == null) {
            return null;
        }
        return commit.getSha();
    }
}
