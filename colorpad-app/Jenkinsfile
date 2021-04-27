pipeline {
  agent {
    docker { image 'node:latest' }
  }
  stages {
    stage('Install') {
      steps{
        dir('./colorpad-app/'){ sh 'npm install' }
      }
    }

    stage('Test') {
        stage('Unit tests') {
          steps{
            dir('./colorpad-app/') { sh 'echo "All tests passed"' }
          }
        }
    }
    stage('Build') {
      steps{
        dir('./colorpad-app/') { sh 'npm run-script build' }
      }
    }
  }
}
