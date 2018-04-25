#!/usr/bin/env sh

# secret is obtained via jenkins script console `jenkins.slaves.JnlpSlaveAgentProtocol.SLAVE_SECRET.mac("docker-agent")`
chmod +x /var/jenkins_secrets/secret || echo "Please wait waiting for copy of key from jenkins-master"
source /var/jenkins_secrets/secret || true

jenkins-slave