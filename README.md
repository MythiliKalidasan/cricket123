# Cricket Project

(TODO: Add a brief description of the project)

## Code Quality

This project uses several tools to maintain code quality and consistency:

*   **EditorConfig:** Ensures basic consistency (indentation, whitespace, line endings) across different editors. Configuration is in `.editorconfig`.
*   **Checkstyle:** Enforces Java coding style conventions based on the Google Java Style Guide (adapted for Java 7). Configuration is in `config/checkstyle/checkstyle.xml`.
*   **PMD:** Performs static analysis to detect potential bugs, unused code, suboptimal code, and other issues. Configuration is in `config/pmd/ruleset.xml`.

### Running Checks

To run both Checkstyle and PMD checks, execute the following Gradle command from the project root:

```bash
./gradlew :app:check
```
or on Windows:
```bash
gradlew.bat :app:check
```

Checkstyle violations will be printed to the console. PMD results can be found in the generated HTML report located at `app/build/reports/pmd/pmd.html`.

*(Note: PMD is currently configured with `ignoreFailures = true`, so PMD violations will be reported but won't fail the build.)*
