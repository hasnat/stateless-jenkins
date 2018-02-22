FROM jenkins/jenkins:lts

# if we want to install via apt
#USER root
#RUN apt-get update && apt-get install -y nodejs
#USER jenkins

COPY /init.d/unsecure.groovy /usr/share/jenkins/ref/init.groovy.d/a.unsecure.groovy
COPY /init.d/plugins.groovy /usr/share/jenkins/ref/init.groovy.d/b.plugins.groovy
COPY /init.d/job.groovy /usr/share/jenkins/ref/init.groovy.d/c.job.groovy

RUN /usr/local/bin/install-plugins.sh git \
    pipeline-multibranch-defaults \
    workflow-aggregator \
    workflow-multibranch \
    pipeline-aggregator-view \
    workflow-durable-task-step \
    workflow-basic-steps pipeline-stage-step
