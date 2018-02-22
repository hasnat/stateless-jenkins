# stateless-jenkins

Making use for Jenkinsfile we can ignore jenkins state and spin new instance when required

We can either use stock jenkins/jenkins:lts
required plugins will be installed on compose up

```
docker-compose up
```

If we use Dockerfile we'd be installing (extra + required) plugins on build stage


# env vars

We have all default jenkins/jenkins env vars +

`PROJECT_GIT` specify a project git repo which would be added to jenkins automatically from `init.d/job.groovy` (optional)
`PROJECT_NAME` project name (optional) default=Project Name if `PROJECT_GIT` specified
`PROJECT_BUILD` if true jenkins will load project branches and build automatically
`PROJECT_PLUGINS` any extra plugins to be installed on jenkins init

example
```
      - "PROJECT_GIT=https://github.com/hasnat/stateless-jenkins.git"
      - "PROJECT_NAME=Stateless Jenkins"
      - "PROJECT_BUILD=true"
      - "PROJECT_PLUGINS="
```


# why

So we're not depending on one jenkins, jenkins home don't matter, no back ups needed for jenkins.