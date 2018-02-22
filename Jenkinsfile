#!groovy

node {
    properties([
        parameters([
            string(
                defaultValue: '0.9',
                description: 'Env',
                env : 'version'
            )
        ]),
        pipelineTriggers([])
    ])
    stage('Build') {
        withEnv(["versinn=${version}"]) {
            echo "done ${params.env}"
        }
    }
}