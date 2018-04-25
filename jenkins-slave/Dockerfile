FROM jenkins/jnlp-slave:alpine
USER root
RUN apk add --no-cache docker

# keeping user root as I don't want to add jenkins(10000) on my OSX docker user group for access to /var/run/docker.sock
#USER jenkins

ADD entrypoint.sh /usr/local/bin/jenkins-slave-custom

ENTRYPOINT ["/usr/local/bin/jenkins-slave-custom"]