package listner;

import com.adaptavist.tm4j.junit.customformat.CustomFormatConstants;
import com.adaptavist.tm4j.junit.customformat.CustomFormatContainer;
import com.adaptavist.tm4j.junit.customformat.CustomFormatExecution;
import com.adaptavist.tm4j.junit.customformat.CustomFormatTestCase;
import org.junit.platform.commons.util.PreconditionViolationException;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.adaptavist.tm4j.junit.file.CustomFormatFile.generateCustomFormatFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tm4jTestExecutionListener implements TestExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(Tm4jTestExecutionListener.class);

    private CustomFormatContainer customFormatContainer;

    private final Map<String, CustomFormatTestCase> testIdentifierToTestCaseMap = new HashMap<>();


    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        customFormatContainer = new CustomFormatContainer();
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        try {
            generateCustomFormatFile(customFormatContainer);
        } catch (IOException e) {
            LOG.error("Failed to generate tm4j log", e);
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest() && hasTm4jTestCase(testIdentifier)) {
            switch (testExecutionResult.getStatus()) {
                case SUCCESSFUL:
                    CustomFormatExecution passedExecution = new CustomFormatExecution();
                    passedExecution.setResult(CustomFormatConstants.PASSED);
                    passedExecution.setSource(testIdentifier.getDisplayName());
                    System.out.println(testIdentifier.getUniqueId());
                    passedExecution.setTestCase(testIdentifierToTestCaseMap.get(testIdentifier.getUniqueId()));
                    customFormatContainer.addResult(passedExecution);
                    break;


                case FAILED:
                    CustomFormatExecution failedExecution = new CustomFormatExecution();
                    failedExecution.setResult(CustomFormatConstants.FAILED);
                    failedExecution.setSource(testIdentifier.getDisplayName());
                    failedExecution.setTestCase(testIdentifierToTestCaseMap.get(testIdentifier.getUniqueId()));
                    customFormatContainer.addResult(failedExecution);
                    break;
                default:
                    throw new PreconditionViolationException(
                            "Unsupported execution status:" + testExecutionResult.getStatus());
            }
        }
    }

    private boolean hasTm4jTestCase(final TestIdentifier testIdentifier) {
        return testIdentifierToTestCaseMap.containsKey(testIdentifier.getUniqueId());
    }

    @Override
    public void reportingEntryPublished(TestIdentifier testIdentifier, ReportEntry entry) {
        Map<String, String> map = entry.getKeyValuePairs();

        CustomFormatTestCase c = new CustomFormatTestCase();
        if (map.containsKey(Tm4jExtension.TEST_CASE_KEY)) {
            c.setKey(map.get(Tm4jExtension.TEST_CASE_KEY));
        }

        testIdentifierToTestCaseMap.put(testIdentifier.getUniqueId(), c);
    }
}
