import jenkins.model.*
import hudson.security.*
import hudson.tasks.*
import jenkins.branch.*
import jenkins.plugins.git.*
import hudson.plugins.git.GitSCM
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition

def instance = Jenkins.getInstance()
"playground \
example1 \
example2 \
example3 \
example4 \
example5 \
example6 \
example7".split().each {
    println "Set Example Project" + it
    WorkflowJob wp = instance.createProject(WorkflowJob.class, it)

    wp.setDefinition(
            new CpsScmFlowDefinition(new GitSCM("file:///usr/local/jenkins-projects/" + it), "Jenkinsfile")
    )
}
