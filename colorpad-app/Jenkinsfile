pipeline {
  agent {
    docker { image 'node:latest' }
  }
  stages {
    stage('Install') {
      steps{
        dir('./colorpad-app/'){ sh 'npm install' }
    }

    stage('Test') {
      parallel {
        stage('Static code analysis') {
          dir('./colorpad-app/'){
            steps { sh 'npm run-script tslint.json' }
          }
        }
        stage('Unit tests') {
          dir('./colorpad-app/'){
            steps { sh 'ng test' }
          }
        }
        stage('End to End tests') {
          dir('./colorpad-app/'){
            steps { sh 'ng e2e' }
          }
        }
      }
    }

    stage('Build') {
      dir('./colorpad-app/'){
        steps { sh 'npm run-script build' }
      }
    }
  }
}
