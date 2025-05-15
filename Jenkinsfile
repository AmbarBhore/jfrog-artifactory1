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
			git 'https://github.com/AmbarBhore/jfrog-artifactory1.git'
		}
             }
	     stage('Build Code') {
		steps {
			sh 'mvn clean package'
		}
	     }
	}
}	
