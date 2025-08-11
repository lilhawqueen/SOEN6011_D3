# Exponential Calculator (JavaFX)

## Overview
The **Exponential Calculator** is a JavaFX desktop application that computes: a*b^x.

It features a clean and accessible user interface with input validation, error display, and compliance with **Checkstyle** coding standards and most **PMD** static code analysis rules.

Originally based on the Deliverable 2 project, this version has been progressively improved to eliminate Checkstyle issues and reduce PMD violations from 24 down to just 2.

---

## Features
- **Accurate Calculation** of `multiplier × (base ^ exponent)`
- **Validation**:
  - Prevents empty or invalid numeric input
  - Requires base to be greater than zero
- **Accessibility**:
  - Proper labeling of inputs
  - Descriptive button actions
- **Code Quality**:
  - Zero Checkstyle errors/warnings
  - Nearly all PMD violations resolved
- JavaFX grid layout for an organized UI

---

## Requirements
- Java 11+ (JavaFX-compatible version)
- JavaFX SDK
- Optional: PMD and Checkstyle CLI tools for static analysis

---

## Installation & Running

### Clone the Repository

Replace `/path/to/javafx-sdk` with the JavaFX SDK directory on your machine.

---

## Development History

### Commit 1 – Initial Project (Deliverable 2)
Version 1.0.0
Imported the original JavaFX calculator from Deliverable 2 with no style changes.

### Commit 2 – Checkstyle Compliance
Version 1.0.1
- Reformatted code to meet the Checkstyle ruleset
- Added/expanded JavaDocs
- Renamed variables for clarity
- Removed/reordered imports
- Achieved **0 Checkstyle errors/warnings**

### Commit 3 – PMD Improvements
Version 1.0.2
- Reduced PMD violations **from 24 to 2**
- Made eligible locals/parameters `final`
- Removed unused assignments/variables
- Factored out duplicate string literals
- Removed empty control structures
- Improved empty string checks and exception handling

### Commit 4 - JUnit 5 test suite for core functions
Versio 1.0.3
- Added unit tests for ln(), exp(), and parseInput() covering:
  • Valid inputs with expected results
  • Edge cases (e.g., ln(1) = 0, exp(0) = 1)
  • Invalid inputs (negative, zero for ln, NaN and non-numeric strings for parseInput)
- Verified correctness using known Math library outputs as reference
- Covered exception handling to ensure robustness
- Used reflection to access parseInput() for testing without changing visibility

