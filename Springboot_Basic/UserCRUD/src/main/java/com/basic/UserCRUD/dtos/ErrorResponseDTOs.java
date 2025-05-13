package com.basic.UserCRUD.dtos;

import java.time.LocalDateTime;

public record ErrorResponseDTOs(String errorMessage, String description, LocalDateTime timeStamp) {
}
