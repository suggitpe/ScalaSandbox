# Build a basic Lift web-app and see it running locally

## 1. Define global settings for using Lifty plugin

 - Lifty is a project generation plugin, and as such if we define the plugin in the scope of a project it will be overridden.
	- It is worth noting that if you are developing multiple projects you will need to disable this plugin as it will try to be available to all projects and thus run into SBT version issues.
 - First check that the plugins directory exists in your home directory `mkdir -p ~/.sbt/plugins/`
 - If the directory exists already, check if there are any ".sbt" files already in the directory.  If there are, then for the duration of this exercise, remove them (or rename them using an extension other than ".sbt") to avoid previous SBT configuration interfering with the dependencies for this project.
 - In the plugins directory create a file called plugins.sbt and copy the following into it.

        resolvers += Resolver.url("sbt-plugin-releases", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)

        addSbtPlugin("org.lifty" % "lifty" % "1.7.4")

## 2. Basic project construction ready for Lift

 - A word of warning there is a whole world of versioning pain out there if you are not careful.  You need to make sure that the plugin you want to use is compatible with the version of SBT you are using.  When you have plugins that need different versions of plugins to need to have the checked on one set to the one that most developers will use all the time.
 - Now create an empty directory called the project you want
 - Now create a directory called `project` and in there create a file called `build.properties`
 - The content of this file should be as below.  This defines the version of SBT that we want to use.  Please note that we are using this particular version as the Lifty plugin version only works with this version right now.

        sbt.version=0.11.2

  - To test that the build process is put together correctly you can start SBT in REPL form (run `sbt` in the root of the project) and the type Lifty and tab.  If it starts to auto-complete you know you are all set up.  For some reason it does not show on the tasks listing.

## 3. Lifty navigation

 - All the below assumes you are in the SBT REPL
 - To do anything with Lifty you need to 'learn' templates
 - Lifty's initial set of templates can be learned with:

        > lifty learn lift https://raw.github.com/Lifty/lifty/master/lifty-recipe/lifty.json

 - To list out the available templates with:

        > lifty templates lift

 - You can un-learn/delete the lift template with:

        > lifty delete lift

 - You can then create a new project using:

        > lifty create lift project-blank
        > reload

 - And then start/stop the project with

        > container:start 
        > container:stop

## 4. Create the basic web app project

From your project root directory:

 - From the SBT REPL run

        > lifty create lift project

 - Accept the default for the version of lift
 - Give the base package, eg `org.suggs.sandbox.web.staticdatagui`
 - Set the project name, eg `static-data-gui`
 - Set the version of Scala ... suggest accept defaults
 - Accept defaults for the project
 - Agree to the overrides of the build and plugin files we created earlier (Noted that this may not appear)
 - You now have a generated the basic application
 - Reload and start with

        > reload
        > container:start

 - Navigate to [localhost:8080](http://localhost:8080) and then see the default application

## 5. Configure for use in a IDE
### 5.1 Intellij
 - In the newly created project structure you will find `./project/plugins.sbt`
 - In this file add the following at the end of the file, making sure you leave a blank line before it.  (NB Lift will have added some plugins to this file)

        addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.2.0")

 - This adds the version of the idea plugin into the project.
	- Again it is worth noting that plugins have dependencies and potential version clashes between SBT versions
 - Start `sbt` in the root of the project and allow it time to download the plugins etc and then run

        > gen-idea

 - This will then download the relevant sources/javadoc files and create all the files necessary for the: workspace, project and modules
 - You then just need to open up the project in Intellij and you are there!

### 5.2 Eclipse (Scala IDE)
 - Download and install Scala IDE from [www.scala-ide.org](http://scala-ide.org/download/current.html).  As Scala 2.9 is being used for this example, make sure you download the right version of Scala IDE (version 2.9 and version 2.10 of Scala can't coexist in the IDE)
 - In the file `./project/plugins.sbt` you need to add the following line to load the "SBT Eclipse" plugin.  Note that this is not the most recent version of the plugin (which is 2.2 at the time of writing) because the current versions of the plugin don't support SBT versions older than 0.11.3.

        addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.0.0")
       
 - This adds the plugin to the SBT environment.
 - Start `sbt` in the root of the project and once it has loaded its dependencies run the following command:

        > eclipse

 - This will download further dependencies and generate the Eclipse project files (`.project`, `.classpath` and so on)
 - In Scala IDE, use "File -> Import" to "Import Existing Projects Into Workspace" and browse to your project root directory to load the project.

## 6. Git users

- Just for convenience the following is my .gitignore file

        target/
        .idea/
        .idea_modules/
        *.iml
        *.db
