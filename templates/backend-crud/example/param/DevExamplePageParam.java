package vip.xiaonuo.dev.modular.example.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevExamplePageParam {

    @Schema(description = "关键词")
    private String searchKey;

    @Schema(description = "排序字段")
    private String sortField;

    @Schema(description = "排序方式")
    private String sortOrder;
}
