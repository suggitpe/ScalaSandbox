# Building the Lift database schema with Mapper and Schemifier

##  1. Add in the Mapper dependency
 * Before we can start to introduce a new schema we need to bring in the library to support this.  The default case for a ORM is `Mapper`.
 * To add in the Mapper library add the following to the build.sbt file:
        
        libraryDependencies += "net.liftweb" %% "lift-mapper" % "2.4-M5" % "compile->default"

## 2. Adding tables to the schema
 * To add a domain object (which maps to a table) to the schema we use the Lifty plugin again, but instead of adding a whole project we just want to add in a table as below:

        > lifty create lift mapper

 * You will need to add in the name of the base package (e.g. `org.suggs.sandbox.web.staticdatagui`)
 * Then give it the name of the domain object (e.g. `Supplier`)
 * Finally you will be asked for the internal package (hit enter for the default)
 * If you then look in the model package of your project you will see the Scala class/object combo.
    * NB if you find that in the IDE the class is not compiling you will need to re-generate your IDE files (i.e. `gen-idea`)
 * Mapper always works on the basis that for every model object you have a object and a class
    * The class is for the underlying data model class itself 
    * The companion object is for static methods (such as `findAll`)

            class Supplier extends LongKeyedMapper[Supplier] {}
            object Supplier extends Supplier with LongKeyedMetaMapper[Supplier]
        

## 3. Adding attributes to the domain model
 * Once you have added the class to the model package you now need to add attributes to the class.
 * You can optionally define a few additional attributes to the the object for persistence:

        class Supplier extends LongKeyedMapper[Supplier]
            with IdPK
            with CreatedUpdated {
            ...
        }

 * In the above we have defined a persistent object that is:
    * primary keyed using a Long in the id column
    * audited with a createdAt and updatedAt datetime fields
    * related to using it's ID

 * The following are example attributes that could be added:

        object name extends MappedString(this, 150)
        object email extends MappedEmail(this, 200)
        object information extends MappedText(this)
        object trueOrFalse extends MappedBoolean(this)
        object dosh extends MappedDouble(this)

## 4. Building relationships between objects


