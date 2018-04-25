FROM jenkins/jenkins:lts

# if we want to install via apt
#USER root
#RUN apt-get update && apt-get install -y docker
#USER jenkins

RUN /usr/local/bin/install-plugins.sh git \
    blueocean \
    pipeline-multibranch-defaults \
    workflow-aggregator \
    workflow-multibranch \
    pipeline-aggregator-view \
    workflow-durable-task-step \
    workflow-basic-steps \
    pipeline-stage-step \
    generic-webhook-trigger \
    rebuild
