package vip.xiaonuo.dev.modular.example.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevExampleEditParam {

    @Schema(description = "主键")
    @NotBlank(message = "id不能为空")
    private String id;

    @Schema(description = "名称")
    @NotBlank(message = "name不能为空")
    private String name;

    @Schema(description = "编码")
    @NotBlank(message = "code不能为空")
    private String code;

    @Schema(description = "排序")
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    @Schema(description = "备注")
    private String remark;
}
