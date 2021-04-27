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
    stage('Build') {
      dir('./colorpad-app/'){
        steps { sh 'npm run-script build' }
      }
    }
  }
}
