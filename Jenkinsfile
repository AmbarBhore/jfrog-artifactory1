pipeline {
	agent any
	environment {
		ARTIFACTORY_URL='http://192.168.0.102:8082/artifactory'
		ARTIFACTORY_CRED=credentials('jfrog-cred')
		DOCKER_IMAGE='rmm-agent'
	}
	stages{
	    stage('checkout code') {
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
				def server = Artifactory.newServer url: 'http://192.168.0.102:8082/artifactory', credentialsId: 'jfrog-cred'
				def uploadSpec = """{
					"files":[{
					    "pattern":"target/*.jar",
					    "target":"maven-local/com/ambar/rmm/"
					 }] 
				}"""
				server.upload spec: uploadSpec
			}
		}
	     }			
	}
}	
