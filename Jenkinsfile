pipeline {
  agent {
    label 'nodejs'
  }
  stages {
    stage('Install') {
      steps{
        dir('./colorpad-app/'){ sh 'npm install' }
      }
    }

    stage('Test') {
      parallel {
        stage('Static code analysis') {
          steps{
            dir('./colorpad-app/') { sh 'npm run-script tslint.json' }
          }
        }
        stage('Unit tests') {
          steps{
            dir('./colorpad-app/') { sh 'ng test' }
          }
        }
        stage('End to End tests') {
          steps{
            dir('./colorpad-app/') { sh 'ng e2e' }
          }
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
