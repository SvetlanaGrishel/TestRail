package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestCase {
    private final String titleTestCase;
    private final String sectionTestCase;
    private final String templateTestCase;
    private final String typeTestCase;
    private final String priorityTestCase;
    private final String assignedToTestCase;
    // private final String estimateTestCase = "10m";
    // private final String referencesTestCase;
    private final String automationTypeTestCase;
    private final String preconditionsTestCase;
    private final String stepsTestCase;
    private final String expectedResultTestCase;
}
