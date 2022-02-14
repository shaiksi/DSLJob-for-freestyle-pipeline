job('simple-maven-app'){
    scm{
        git{
            branch('master')
            remote{
                credentials('myGitHubCredentials')
                github('shaiksi/simple-java-maven-app', protocol='https', host='github.com')
            }
        }
    }
    steps{
        shell{
            command('echo "workspace: ${WORKSPACE}";echo "Jenkins job name: ${JOB_NAME}"')

        }
        maven{
            goals('clean package')
            mavenInstallation('Maven3.8')
            properties(skipTests: true)
        }
        maven{
            goals('test')
            mavenInstallation('Maven3.8')
        }
        publishers {
            archiveArtifacts('target/*.jar')
            archiveJunit('target/surefire-reports/**/*.xml')

        }
    
    }
}
pipelineJob('simple-maven-DSLJob'){
    description('this is my pipeline job')
    definition{
            cpsScm{
                scm{
                    git{
                        branch('master')
                        remote{
                            credentials('myGitHubCredentials')
                            github('shaiksi/simple-java-maven-app', protocol='https', host='github.com')
                        }   
                    }
                }
            scriptPath('Jenkinsfile')
            lightweight(lightweight = true)
            }
        
    }
}

