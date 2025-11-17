package com.webapp.usermanagementservice.dto;

import com.webapp.usermanagementservice.model.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdateRequest {
    @NotNull
    private Status status;
}
