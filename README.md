# SauceDemo Test Automation Framework

A comprehensive test automation framework for [SauceDemo](https://www.saucedemo.com) e-commerce application using Java, Selenium WebDriver, TestNG, and implementing Page Object Model and Factory design patterns.

## Features

- **Page Object Model (POM)** design pattern for better maintainability
- **Factory Design Pattern** for WebDriver management
- **TestNG** framework for test organization and execution
- **WebDriverManager** for automatic driver management

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser installed

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd saucedemo-taf
   ```

2. **Install dependencies**
   ```bash
   mvn clean install -DskipTests
   ```

3. **Configure test parameters** (Optional)
   - Edit `src/test/resources/config.properties` to change browser, timeouts, etc.

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn clean test -Dtest=OrderPlacementTest
```

### Run with different browser
```bash
mvn clean test -Dbrowser=firefox
```


## Configuration

### Browser Configuration
Edit `src/test/resources/config.properties`:
```properties
browser=chrome  # Options: chrome, firefox, edge
url=https://www.saucedemo.com/
```

```properties
actionDelay=1000  # Delay in milliseconds between actions
```

**Speed Settings:**
- `0` -  Fast (no delay) - for CI/CD
- `1000` - Slow (1 second) - good for watching
- `2000` - Very slow (2 seconds) - for presentations


