package vip.xiaonuo.dev.modular.example.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevExampleIdParam {

    @Schema(description = "主键")
    @NotBlank(message = "id不能为空")
    private String id;
}
