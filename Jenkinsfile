pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME' // Use the Maven version installed in Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/soham12543/Vtiger_Automation_Selenium_Project.git'
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=RegressionTest.xml'
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    reportDir: '/Vtiger_Automation_Project/Report',
                    reportFiles: 'myreport.html',
                    reportName: 'Report'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution finished.'
        }
    }
}