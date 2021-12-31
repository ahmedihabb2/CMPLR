pipeline {
    agent {
        label "beta"
    
    }
    environment{
        LOGIN_SERVER = "beta"
        WEBHOOK_URL = credentials('Beta_Discord')

    }
    stages {
        stage("fetch"){
            steps{
                echo "========Executing Fetch========"
                git branch: "dev", url: "https://CMPLR-Technologies@dev.azure.com/CMPLR-Technologies/CMPLR-Technologies.Mobile.Android-Native/_git/CMPLR-Technologies.Mobile.Android-Native"
            }
            post{
                success{
                    echo "=======fetch executed successfully========"
                discordSend description: "Jenkins Pipeline Build", thumbnail: "https://jenkins.io/images/logos/ninja/256.png",footer: "Fetch is successfull", result: currentBuild.currentResult, title: JOB_NAME, webhookURL: WEBHOOK_URL

                }
                failure{
                    echo "========fetch execution failed========"
                    discordSend description: "Jenkins Pipeline Build", thumbnail: "https://jenkins.io/images/logos/ninja/256.png" ,footer: "Fetch execution failed", result: currentBuild.currentResult, title: JOB_NAME, webhookURL: WEBHOOK_URL
                    
                }
            }
        }
    }
}