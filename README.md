# 🧠 Mind Map Testing Framework
### *Selenium + Java + TestNG + Maven*

<div align="center">

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Test Coverage](https://img.shields.io/badge/coverage-85%25-green)
![License](https://img.shields.io/badge/license-MIT-blue)
![Java](https://img.shields.io/badge/Java-17+-orange)

**Visual Test Design | Smart Automation | CI/CD Ready**

[🚀 Quick Start](#-quick-start) • [📖 Documentation](#-what-is-mind-map-testing) • [🎯 Examples](#-example-test-with-mind-map-annotation) • [🤝 Contributing](#-qa--dev-collaboration-impact)

</div>

---

## 🌟 Overview

Transform your testing strategy with **Mind Map Testing** - a revolutionary approach that combines visual test design with powerful Selenium automation. Say goodbye to scattered test cases and hello to organized, prioritized, and traceable testing!

### ✨ Why Mind Map Testing?

```
🎯 Visual Clarity → See your entire test landscape at a glance
🚀 Smart Prioritization → P0, P1, P2 automation strategy
🔄 Better Collaboration → Bridge the QA-Dev communication gap
📊 Traceability → Every test maps to business flows
⚡ CI/CD Optimized → Run only what matters, when it matters
```

---

## 🌳 What is Mind Map Testing?

Mind Map Testing is a **visual test design technique** that organizes test ideas, priorities, and automation scope in a structured, tree-like representation. Each node represents a feature, sub-feature, or test scenario—allowing teams to see the complete testing landscape at a glance.

### 🎨 Priority System

| Priority | Type | When to Run | Purpose |
|----------|------|-------------|---------|
| **P0** 🔴 | Smoke Tests | Every PR build | Critical paths & blockers |
| **P1** 🟡 | Regression Tests | Nightly builds | Core functionality |
| **P2** 🟢 | Exploratory Tests | Weekly runs | Edge cases & performance |

---

## 🎯 Benefits for Teams

### ✅ For QA Teams

- 🗺️ **Better Visualization** - See test coverage and dependencies clearly
- 🔄 **Reduce Redundancy** - Eliminate duplicate test case creation
- 🚀 **Faster Onboarding** - New QA engineers ramp up quickly
- 🎯 **Smart Prioritization** - Identify automatable, high-impact tests

### 👨‍💻 For Developers

- 👁️ **Visibility** - Know what's covered by automation
- ⚠️ **Risk Awareness** - Identify risk areas before merging
- ⚡ **Optimized CI/CD** - Run only critical tests per release
- 🤝 **Shared Understanding** - Align on testing strategy

---

## 🏗️ Framework Architecture

```
SeleniumMindMap/
│
├── 📁 src/test/java/com/example/
│   ├── 🔥 smoke/              → P0 - Smoke Tests
│   ├── 🔄 regression/         → P1 - Regression Tests
│   ├── 🔍 exploratory/        → P2 - Exploratory Tests
│   ├── 🛠️ utils/              → Waits, Helpers, Traceability
│   └── 📄 pages/              → Page Object Models
│
├── 📁 docs/
│   ├── SauceDemo_MindMap.xmind
│   └── README.md
│
├── 📁 test-output/            → Allure Reports
├── 📄 pom.xml
└── 📄 testng.xml
```

---

## 🚀 Quick Start

### Prerequisites

```bash
☕ Java 17+
📦 Maven 3.6+
🌐 Chrome/Firefox Browser
```

### Installation

```bash
# Clone the repository
git clone https://github.com/yourusername/selenium-mindmap.git

# Navigate to project directory
cd selenium-mindmap

# Install dependencies
mvn clean install
```

### Running Tests

```bash
# Run all regression tests
mvn clean test -DsuiteXmlFile=testng.xml

# Run specific priority groups
mvn clean test -Dgroups=regression

# Run smoke tests only
mvn clean test -Dgroups=smoke,P0

# Run with Allure reports
mvn clean test
allure serve target/allure-results
```

---

## 📘 Example Test with Mind Map Annotation

```java
@MindMap(
    node = "Login -> Invalid", 
    priority = "P1", 
    ci = "Nightly"
)
@Test(groups = {"regression", "P1"})
public void invalidLoginShowsError() {
    // Arrange
    LoginPage login = new LoginPage(driver);
    login.open();
    
    // Act
    login.login("bad_user", "bad_pass");
    
    // Assert
    String err = login.getErrorText();
    Assert.assertTrue(
        err.contains("do not match any user"),
        "Error message should indicate invalid credentials"
    );
}
```

### 🎨 Custom Annotation Benefits

- 🏷️ **Traceability** - Link tests to mind map nodes
- 🎯 **Priority Management** - Clear P0/P1/P2 classification
- 🔄 **CI Integration** - Automatic grouping for pipelines
- 📊 **Reporting** - Enhanced test reports with business context

---

## 💡 Key Features

### 🎯 Visual Traceability
Every test case maps directly to business flows in the mind map. No more wondering "what does this test cover?"

### 🔄 Smart Test Organization
```
✅ Clearly defined automation boundaries (P0, P1, P2)
✅ Logical grouping by feature and priority
✅ Easy to identify gaps in test coverage
```

### 🤝 Enhanced Collaboration
A **shared visual model** that both QA and Dev understand. No more miscommunication!

### ⚡ CI/CD Optimized
```bash
# Run only critical tests on PR
mvn test -Dgroups=smoke,P0

# Run full regression nightly
mvn test -Dgroups=regression,P1

# Run exploratory tests weekly
mvn test -Dgroups=exploratory,P2
```

### 📈 Easy to Scale
As product features evolve, simply update the mind map and corresponding test annotations.

---

## 🔧 Configuration

### TestNG Configuration (`testng.xml`)

```xml
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="MindMap Test Suite" parallel="methods" thread-count="3">
    <test name="Regression Tests">
        <groups>
            <run>
                <include name="regression"/>
                <include name="P1"/>
            </run>
        </groups>
        <packages>
            <package name="com.example.tests.*"/>
        </packages>
    </test>
</suite>
```

### Maven Groups Execution

```bash
# Multiple groups
mvn test -Dgroups=smoke,regression

# Exclude groups
mvn test -DexcludedGroups=exploratory

# Parallel execution
mvn test -Dparallel=methods -DthreadCount=4
```

---

## 📊 Reporting & Traceability

### Allure Reports Integration

```bash
# Generate and view reports
mvn clean test
allure serve target/allure-results
```

**Report Features:**
- ✅ Pass/Fail visualization per mind map node
- 📊 Priority-based test distribution
- 🔄 Historical trends
- 📸 Screenshots on failure
- 🏷️ Mind map node annotations

---

## 🌟 QA & Dev Collaboration Impact

Mind Map Testing **bridges the communication gap** between QA and Dev teams:

| Before Mind Map Testing | After Mind Map Testing |
|------------------------|------------------------|
| 😕 Scattered test cases | 🎯 Organized visual structure |
| ❓ Unclear priorities | ✅ Clear P0/P1/P2 system |
| 🔀 Duplicate tests | 🎯 Consolidated coverage |
| 🤷 "What's automated?" | 📊 Full visibility |
| ⏰ Reactive testing | 🚀 Predictive strategy |

### Single Source of Truth

Both teams share one visual model for:
- ✅ Test priorities
- ✅ Automation scope  
- ✅ Risk assessment
- ✅ Coverage gaps

---

## 📁 Project Structure Details

### Page Object Models
```java
public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    private WebElement usernameField;
    
    @MindMapElement(node = "Login")
    public void login(String user, String pass) {
        // Login implementation
    }
}
```

### Utilities
- `WaitUtils.java` - Smart waits and synchronization
- `TraceabilityGenerator.java` - Mind map to test mapping
- `TestListener.java` - Custom reporting hooks

---

## 🎯 Best Practices

1. **🗺️ Update Mind Map First** - Design tests visually before coding
2. **🏷️ Use Annotations Consistently** - Every test should have `@MindMap`
3. **🎯 Follow Priority Guidelines** - Respect P0/P1/P2 definitions
4. **📊 Review Coverage Regularly** - Use Allure to spot gaps
5. **🤝 Share with Team** - Keep mind map accessible to all
6. **♻️ Refactor with Care** - Update both code and mind map together

---

## 📦 Dependencies

```xml
<!-- Core Dependencies -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.15.0</version>
</dependency>
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.8.0</version>
</dependency>
```

---

## 🤝 Contributing

We welcome contributions! Please:

1. 🍴 Fork the repository
2. 🌿 Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. 💾 Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. 📤 Push to the branch (`git push origin feature/AmazingFeature`)
5. 🎉 Open a Pull Request

---

## 📎 Attachments & Resources

| Resource | Description |
|----------|-------------|
| 🗺️ `SauceDemo_MindMap.xmind` | Visual mind map for P0/P1/P2 flows |
| ⚙️ `testng.xml` | Configured for regression execution |
| 📊 `Allure Reports` | Pass/fail traceability per node |
| 📖 `docs/` | Additional documentation |

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

<div align="center">

### ⭐ If you find this helpful, please give it a star!

**Made with ❤️ by QA Engineers, for QA Engineers**

[⬆ Back to Top](#-mind-map-testing-framework)

</div>
