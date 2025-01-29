pipeline {
    agent any  // Run on any available agent

    tools {
        maven "Maven" // Install the Maven version configured as "Maven" and add it to the path.
    }

    environment {
        PROJECT_DIR = "${workspace}" // Adjust for Windows file paths
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the Git repository to the workspace
                git url: 'https://github.com/rakesh-vardan/Learn_TestAutomationFramework', branch: 'main'
            }
        }

        stage('Verify Directory Structure') {
            steps {
                // Debugging: List files in the workspace to check if pom.xml exists
                script {
                    echo "Listing files in the project directory: ${PROJECT_DIR}"
                    bat "dir ${PROJECT_DIR}" // Windows command to list directory contents
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                // Run mvn clean install from the root directory of the project
                dir("${PROJECT_DIR}") {
                    script {
                        echo "Running mvn clean install"
                        bat "mvn clean install -DskipTests"  // Run Maven command on Windows
                    }
                }
            }
        }

        stage('Run API Tests') {
            steps {
                // Run the REST Assured API tests
                dir("${PROJECT_DIR}") {
                    script {
                        echo "Running mvn test"
                        bat "mvn clean test -DsuiteXMLFile=testng.xml"  // Run Maven test on Windows
                    }
                }
            }
        }
    }

    post {
        always {
            // Publish the Allure report (ensure correct path)
            publishHTML([
            reportName: 'TestReport',  // Name that will be displayed in Jenkins
            reportDir: 'target',        // Directory where the report is located
            reportFiles: 'extent-reports.html',  // Name of the HTML report file (adjust as per your actual file name)
            keepAll: false,               // Keep previous reports (set to 'false' to overwrite)
            allowMissing: true,
            alwaysLinkToLastBuild: true
        ])


            // Clean workspace after build
            cleanWs()
        }

        success {
            echo "Tests and reports were successfully generated!"
        }

        failure {
            echo "Tests failed. Please check the logs for details."
        }
    }
}