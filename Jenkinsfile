pipeline {
    agent any
    environment {
        ARTIFACTORY_URL = 'http://192.168.0.102:8082/artifactory'
        ARTIFACTORY_CRED = 'jfrog-cred'
        DOCKER_IMAGE = 'rmm-agent'
    }
    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', credentialsId: 'github-config', url: 'https://github.com/AmbarBhore/jfrog-artifactory1.git'
            }
        }
        stage('Build Code') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Deploy Jar to JFrog') {
            steps {
                script {
                    def server = Artifactory.server("${ARTIFACTORY_CRED}")
                    def uploadSpec = """{
                        "files": [{
                            "pattern": "target/*.jar",
                            "target": "maven-local/com/ambar/rmm/"
                        }]
                    }"""
                    def buildInfo = Artifactory.newBuildInfo()
                    server.upload spec: uploadSpec, buildInfo: buildInfo
                    server.publishBuildInfo buildInfo
                }
            }
        }
    }
}
