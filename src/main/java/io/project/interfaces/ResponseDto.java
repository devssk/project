package io.project.interfaces;

public record ResponseDto(
        ResponseCode code,
        Object data
) {}
