package brocodex.vpn.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConfigDTO {
    @NotBlank
    private String config;
}
