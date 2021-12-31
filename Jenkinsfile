pipeline {
    agent {
        label "beta"
    
    }
    environment{
        LOGIN_SERVER = "beta"
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
                    discordSend description: "Jenkins Pipeline Build", footer: "Fetch executed successfully", result: currentBuild.currentResult, title: JOB_NAME, webhookURL: "https://discord.com/api/webhooks/926441648605528114/L_GjAOFAUJGwUt0_N9Wu58T0OTR5OksSXvgiZnnWruTfVmuLJpTjDQvB7bDaaBypUxjE"

                }
                failure{
                    echo "========fetch execution failed========"
                    discordSend description: "Jenkins Pipeline Build", footer: "Fetch execution failed", result: currentBuild.currentResult, title: JOB_NAME, webhookURL: "https://discord.com/api/webhooks/926441648605528114/L_GjAOFAUJGwUt0_N9Wu58T0OTR5OksSXvgiZnnWruTfVmuLJpTjDQvB7bDaaBypUxjE"
                    
                }
            }
        }
    }
}