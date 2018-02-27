#!groovy

node {
    properties([
        parameters([
            string(
                defaultValue: '0.9',
                description: 'Env',
                name : 'env'
            )
        ]),
        pipelineTriggers([])
    ])
    stage('Build') {
        echo "done ${params.env}"
    }
}