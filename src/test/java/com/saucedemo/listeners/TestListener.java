package com.saucedemo.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener for detailed test execution reporting
 */
public class TestListener implements ITestListener {

    private int testCount = 0;
    private int passedCount = 0;
    private int failedCount = 0;
    private int skippedCount = 0;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("==========TEST SUITE STARTED: " + context.getName() + " ==========");
    }

    @Override
    public void onTestStart(ITestResult result) {
        testCount++;
        System.out.println("\n▶ [TEST " + testCount + "] STARTING: " + result.getMethod().getMethodName());
        System.out.println("  Description: " + result.getMethod().getDescription());
        System.out.println("  Class: " + result.getTestClass().getName());
        System.out.println("  ────────────────────────────────────────────────────────────");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passedCount++;
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("  PASSED: " + result.getMethod().getMethodName());
        System.out.println("  Duration: " + duration + "ms");
        System.out.println("  ────────────────────────────────────────────────────────────\n");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failedCount++;
        long duration = result.getEndMillis() - result.getStartMillis();
        System.out.println("  FAILED: " + result.getMethod().getMethodName());
        System.out.println("  Duration: " + duration + "ms");
        System.out.println("  Error: " + result.getThrowable().getMessage());
        System.out.println("  ────────────────────────────────────────────────────────────\n");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skippedCount++;
        System.out.println("  SKIPPED: " + result.getMethod().getMethodName());
        System.out.println("  Reason: " + result.getThrowable());
        System.out.println("  ────────────────────────────────────────────────────────────\n");
    }

    @Override
    public void onFinish(ITestContext context) {
        long duration = context.getEndDate().getTime() - context.getStartDate().getTime();

        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              TEST SUITE COMPLETED: " + context.getName());
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Total Tests:    " + testCount);
        System.out.println("║  ✅ Passed:      " + passedCount);
        System.out.println("║  ❌ Failed:      " + failedCount);
        System.out.println("║  ⚠️  Skipped:     " + skippedCount);
        System.out.println("║  Duration:       " + duration + "ms (" + (duration / 1000) + "s)");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

        System.out.println("📊 Test Reports:");
        System.out.println("  - HTML Report: target/surefire-reports/index.html");
        System.out.println("  - TestNG Report: test-output/index.html");
        System.out.println("\n");
    }
}

