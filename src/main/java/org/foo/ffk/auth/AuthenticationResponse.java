package org.foo.ffk.auth;

import org.foo.ffk.dto.CustomerDTO;

public record AuthenticationResponse(String token ,CustomerDTO customerDTO) {
}
