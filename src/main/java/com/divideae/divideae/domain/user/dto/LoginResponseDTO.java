package com.divideae.divideae.domain.user.dto;

public record LoginResponseDTO(String id, String login, String name, String pixKey, boolean isProfileComplete, String token) {
}
