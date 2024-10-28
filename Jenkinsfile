pipeline {
    agent any
    stages {
        stage('RA_RestAssuredProjeto') {
            steps {
                script {
                    bat "mvn clean test"
                }
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
        unsuccessful {
            emailext attachLog: true, compressLog: true, body: "Rest Assured Projeto com erro \n\n Verifique a build $BUILD_URL para ver o resultado.", to: "jenkins@solus.inf.br",
                subject: "FALHOU - $JOB_BASE_NAME"
        }
        fixed {
            emailext attachLog: true, compressLog: true, body: "Rest Assured Projeto se recuperou do erro \n\n Verifique a build $BUILD_URL para ver o resultado.", to: "jenkins@solus.inf.br",
                subject: "CORRIGIDO - $JOB_BASE_NAME"
        }
    }
}
