package com.divideae.divideae.domain.user;

public record RegisterDTO(String login, String password, UserRole role, String CPF, String nome, String datanascimento, ChavePixType chavepix) {
}
