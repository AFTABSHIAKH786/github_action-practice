# 📊 SonarCloud Integration with GitHub Actions & JaCoCo Code Coverage
(Note This documentation if edited using ChatGPT)
This project demonstrates how to set up a GitHub Actions CI workflow for a Java Maven project to:

- Run builds
- Perform code analysis with SonarCloud
- Measure and report code coverage using JaCoCo

---

## 📂 Project Structure

```
.
├── .github
│   └── workflows
│       └── maven-ci.yml
├── src
│   └── main/java/com/example
│       └── NumberGuessGame.java
├── pom.xml
└── ...
```

---

## 🚀 GitHub Actions Workflow

**Workflow File:** `.github/workflows/maven-ci.yml`

```yaml
name: Maven CI

on:
  push:
    branches: [ "main" ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps: 
    - name: Checkout repository
      uses: actions/checkout@v4

    - name: Set up JDK 21
      uses: actions/setup-java@v4
      with: 
        distribution: 'temurin'
        java-version: '21'

    - name: Testing the code
      run: mvn clean install

  test: 
    runs-on: ubuntu-latest

    steps: 
    - name: Checkout repository
      uses: actions/checkout@v4

    - name: Set up JDK 21
      uses: actions/setup-java@v4
      with: 
        distribution: 'temurin'
        java-version: '21'

    - name: Build with Maven
      run: mvn clean test

  sonar_upload:
    runs-on: ubuntu-latest

    steps: 
    - name: Checkout repository
      uses: actions/checkout@v4

    - name: Set up JDK 21
      uses: actions/setup-java@v4
      with: 
        distribution: 'temurin'
        java-version: '21'

    - name: Build and analyze
      env:
        SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
      run: mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=AFTABSHIAKH786_github_action-practice -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
```

---

## 📊 SonarCloud Setup

1. [Create an account](https://sonarcloud.io)
2. Create a new project and connect your GitHub repository
3. Note your **Project Key**
4. Generate a SonarCloud Token:
    - Go to **My Account → Security**
    - Generate and copy the token
5. Add the token as a GitHub Secret:
    - Go to your repo → **Settings → Secrets and variables → Actions**
    - Click **New secret**
    - Name: `SONAR_TOKEN`
    - Value: *(your SonarCloud token)*

---

## 📈 JaCoCo Code Coverage Integration

Add the following configuration to your `pom.xml`:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.jacoco</groupId>
      <artifactId>jacoco-maven-plugin</artifactId>
      <version>0.8.11</version>
      <executions>
        <execution>
          <goals>
            <goal>prepare-agent</goal>
          </goals>
        </execution>
        <execution>
          <id>report</id>
          <phase>verify</phase>
          <goals>
            <goal>report</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

This generates a code coverage report at:

```
target/site/jacoco/jacoco.xml
```

SonarCloud reads this report using:

```
-Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
```

---

## 📊 Viewing the Analysis on SonarCloud

After pushing changes to the main branch:

- Visit your SonarCloud project dashboard
- See:
    - Code quality metrics: Bugs, code smells, vulnerabilities
    - Code coverage percentage under **Measures → Coverage**

---

## 📚 References

- [SonarCloud Documentation](https://docs.sonarcloud.io/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [JaCoCo Maven Plugin](https://www.jacoco.org/jacoco/trunk/doc/maven.html)