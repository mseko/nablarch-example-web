package com.nablarch.example.app.web.action;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public abstract class ParentAction {

    @Produces(MediaType.APPLICATION_JSON)
    public String parentMethod() {
        return "parent method!!";
    }
}
