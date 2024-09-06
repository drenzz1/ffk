package org.foo.ffk.auth;

import lombok.Builder;
import org.foo.ffk.dto.CustomerDTO;
@Builder

public record AuthenticationResponse(String accesstoken ,String refreshToken,CustomerDTO customerDTO) {
}
