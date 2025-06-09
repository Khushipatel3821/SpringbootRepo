package com.basic.UserCRUD.dtos;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String errorMessage, String description, LocalDateTime timeStamp) {
}
