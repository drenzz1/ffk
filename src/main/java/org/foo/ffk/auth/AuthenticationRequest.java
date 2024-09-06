package org.foo.ffk.auth;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonPOJOBuilder
public record AuthenticationRequest(String username,String password) {
}
