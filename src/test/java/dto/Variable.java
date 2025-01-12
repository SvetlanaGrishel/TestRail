package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Variable {
    @Builder.Default
    private final String labelVariable = "LABEL";
    @Builder.Default
    private final String systemNameVariable = "SYSTEM_NAME";
    @Builder.Default
    private final String typeVariable = "String";
    @Builder.Default
    private final String descriptionVariable = "Test description";
}
