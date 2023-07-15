package com.nagarro.bloggingapp.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResourceNotFound extends RuntimeException {

    private String resource;
    private String name;
    private Long val;

    public ResourceNotFound(String resource, String name, Long val) {

        super(String.format("Exception in %s resource for %s : %s value not found ",
                resource, name, val));
        this.resource = resource;
        this.name = name;
        this.val = val;
    }

}
