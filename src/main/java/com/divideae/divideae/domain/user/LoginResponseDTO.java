package com.divideae.divideae.domain.user;

import java.time.LocalDate;

public record LoginResponseDTO(String id, String login, String nome, ChavePixType chavePix, String dataNascimento, boolean isProfileComplete, String token) {
}
