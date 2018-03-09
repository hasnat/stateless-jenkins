import jenkins.model.*
import hudson.security.*
import hudson.tasks.*
import jenkins.branch.*
import jenkins.plugins.git.*
import org.jenkinsci.plugins.workflow.multibranch.*

def projectGit = System.getenv("PROJECT_GIT") ?: ""
def projectName = System.getenv("PROJECT_NAME") ?: "Project Name"
def projectBuild = (System.getenv("PROJECT_BUILD") ?: "").toBoolean()
if (projectGit != "") {
    def instance = Jenkins.getInstance()
    println "Set Multi Branch Project"
    WorkflowMultiBranchProject mp = instance.createProject(WorkflowMultiBranchProject.class, projectName)
    mp.getSourcesList().add(new BranchSource(
        new GitSCMSource(null, projectGit, "", "*", "", false), new DefaultBranchPropertyStrategy(new BranchProperty[0])
    ))
    if (projectBuild) {
        mp.scheduleBuild2(0).getFuture().get()
    }
} else {
    println "Skipping Multi Branch Project Setup as PROJECT_GIT is blank"
}