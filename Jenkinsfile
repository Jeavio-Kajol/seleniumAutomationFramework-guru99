pipeline {
    agent any

    environment {
        TESTNG_XML = 'testng.xml'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/kajolgupta994/Selenium-Automation-Framework-herokuapp.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn test -Dsurefire.suiteXmlFiles=${env.TESTNG_XML}"
            }
        }

        stage('Publish TestNG Reports') {
            steps {
                publishTestNG testResultsPattern: '**/target/surefire-reports/testng-results.xml', escapeTestDescp: true, escapeExceptionMsg: true
            }
        }
    }
}
