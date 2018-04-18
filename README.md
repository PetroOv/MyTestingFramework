Requriments: JDK 1.8, Maven 3

**Run API tests**

`mvn clean -Dtype=API test`

**Run UI tests**

`mvn clean -Dtype=UI test`

Supported browsers: Chrome, Firefox, Edge(only if run on Windows 10).
Default browser: Chrome;

To change browser use '-Dbrowser' initial parameter.

Example: `mvn clean test -Dtype=UI -Dbrowser=Firefox`

**Generate report**

`mvn allure:serve`
