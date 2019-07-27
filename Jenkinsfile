pipeline {
    parameters {
        string(name: 'port', defaultValue: '22224', description: 'Port where news publisher will be run')
    }
    options {
        timeout(time: 1, unit: 'HOURS')
    }
    agent {
        node {
            label 'master'
            customWorkspace "${workspace}/${env.BRANCH_NAME}"
        }
    }
    stages {
        stage("Build") {
            steps {
                dir('NewsPublisher') {
                    sh 'docker build -t news-publisher . --build-arg host_proxy=fwproxy-dev.telekom.de --build-arg port_proxy=8080'
                }
            }
        }
        stage("Deploy") {
            steps {
                script {
                    try {
                        sh "docker stop news-publisher"
                        sh "docker rm news-publisher"
                    } catch (exc) {
                        echo "Container doesn't exist!"
                    }
                }
                sh "docker run --name news-publisher -p ${port}:8080 -d news-publisher"
            }
        }
    }
}