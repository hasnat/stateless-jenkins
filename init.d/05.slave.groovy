/*


Adds a slave to master jenkins
master also generates a key for slave to use
slave/master are using same docker volume to share the key


 */

import hudson.model.*
import jenkins.model.*
import hudson.slaves.*
import hudson.slaves.EnvironmentVariablesNodeProperty.Entry

Slave agent = new DumbSlave(
        "docker-agent",     // if changing this make sure you edit the secret accordingly
        "/home/jenkins/agent",
        new JNLPLauncher())
agent.nodeDescription = "Node for all your docker needs"
agent.numExecutors = 4
agent.labelString = "docker-agent"
agent.mode = Node.Mode.NORMAL
agent.retentionStrategy = new RetentionStrategy.Always()

List<Entry> env = new ArrayList<Entry>();
env.add(new Entry("key1","value1"))
env.add(new Entry("key2","value2"))
EnvironmentVariablesNodeProperty envPro = new EnvironmentVariablesNodeProperty(env)

agent.getNodeProperties().add(envPro)

Jenkins.instance.addNode(agent)


new File("/var/jenkins_secrets").mkdir()
new File("/var/jenkins_secrets/secret").newWriter().withWriter { w ->
    w << "export JENKINS_SECRET=" + jenkins.slaves.JnlpSlaveAgentProtocol.SLAVE_SECRET.mac(agent.labelString)
}

println "Node docker-agent has been created successfully."