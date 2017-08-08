node {
    catchError {
        // compile project
        stage('Compile') {
                sh "echo 'starting compile'"
            }
        
        // running test 
        // building image and push to acceptance
        // running acceptance api test
        parallel firstBranch: {
            stage ('Testing') 
            {
                sh "echo 'running all tests.'"
                sh "sleep 120"
                sh "echo ''all tests are passed."
            }
        }, secondBranch: {
            stage ('building image') 
            {
                sh "echo 'creating container images.'"
                sh "sleep 30"
            }

            stage ('deploy to  acceptance') 
            {
                sh "echo 'pushing image to acceptance'"
                sh "sleep 30"
            }

            stage ('running api acceptance testing') 
            {
                sh "echo 'running api acceptance testing'"
                sh "sleep 10"
            }
        }


        stage('Deploy production') {
            sh "echo 'deploy to production'"
        }

        currentBuild.result = "SUCCESS"
    }

    switch (currentBuild.result) {
        case "SUCCESS":
            sh "echo 'build successed'"
            break;

        case "FAILURE":
            sh "echo 'build failed'"
            break;

        default:
            sh "echo 'Undetermined build result'"
            break;
    }
}
