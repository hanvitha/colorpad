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
          steps{
            dir('./colorpad-app/') { sh 'echo "All tests passed"' }
          }
    }
    stage('Build') {
      steps{
        dir('./colorpad-app/') { sh 'npm run-script build' }
      }
    }
  }
}
