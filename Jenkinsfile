pipeline {
    agent any
    environment {
        ARTIFACTORY_URL = 'http://192.168.0.102:8082/artifactory'
        ARTIFACTORY_CRED = 'jfrog-cred'
        DOCKER_IMAGE = 'ambarbhore1234/rmm-agent'
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
                    def server = Artifactory.newServer url: "${ARTIFACTORY_URL}", credentialsId: "${ARTIFACTORY_CRED}"
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
	stage('Build & Push Image to Docker Hub') {
	    steps {
		withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
			sh '''
			    DOCKER_TAG=$BUILD_NUMBER
			    echo "building an docker image"
			    docker build -t $DOCKER_IMAGE:$DOCKER_TAG .
			    
			    echo "logging into the docker hub"
				
			    echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

			    docker push $DOCKER_IMAGE:$DOCKER_TAG
			'''
		}
	    }
	}
	stage("Deploy to kubernetes") {
		steps {
			withCredentials([file(credentialsId: "${KUBECONFIG_CRED_ID}", variable: 'kubeconfig')]) {
			     sh '''
				export KUBECONFIG=$kubeconfig
		
				kubectl config current-context
				kubectl get nodes
				
				echo "Applying deployment and service"
				kubectl apply -f k8s/deployment.yaml
				kubectl apply -f k8s/service.yaml
				
				echo "updating image with build tag: $BUILD_NUMBER"
				kubectl set image deployment/rmm-agent-jfrog-deploy rmm-agent-jfrog-deploy=$DOCKER_IMAGE:$BUILD_NUMBER --record
			     '''
			}
		}
	}	
    }
}
