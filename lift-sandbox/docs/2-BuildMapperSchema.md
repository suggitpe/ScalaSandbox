G# Building the Lift database schema with Mapper and Schemifier

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

 * The following are example attributes that could be added:

        object name extends MappedString(this, 150)
        object email extends MappedEmail(this, 200)
        object information extends MappedText(this)
        object trueOrFalse extends MappedBoolean(this)
        object dosh extends MappedDouble(this)
        object startsAt extends MappedDateTime(this)

## 4. Building relationships between objects
 * Suppose we want to create a relationship from the Supplier above to an Auction object where a single Supplier can have many auctions related to it
 * First we need to create an `Auction` object as we did above (adding in a supplier object and a close_date)
 * Then we need to update the Supplier object as follows:
    * First we need to update the class signature with the relationship trait to indicate that this class is related to another one

            class Supplier extends LongKeyedMapper[Supplier]
                with IdPK
                with CreatedUpdated 
                with OneToMany[Long, Supplier] {
                ...
            }

 * Then we need to add in the field level relationship in the class

        object auctions extends MappedOneToMany(Auction, Auction.supplier, OrderBy(Auction.endsAt, Descending))
            with Owned[Auction]
            with Cascade[Auction]
  
 * Then in the related class we need to add in a foreign key to the supplier

            class Auction extends LongKeyedMapper[Auction]
                with IdPK with CreatedUpdated {
                    object supplier extends MappedLongForeignKey(this, Supplier) {
                       override def dbCoumnName = "supplier_id"
                    }
                }

 * That should be it for the basic relationships


## 5. Build the database connection
 * In order to actually connect to and use a database we also need to add in a database connection.  For now we will use the h2 database as it's ridiculouslt simple to get going.  See further below for how to configure Oracle databases.
 * In the below code there is a basic snippet that can be altered to fit the relevant database.   Key thing to notice is that it allows a preference of a JNDI configured database.

        if (!DB.jndiJdbcConnAvailable_?) {
          val databaseContext =
            new StandardDBVendor(
              Props.get("db.driver") openOr "org.h2.Driver",
              Props.get("db.url") openOr "jdbc:h2:local_db.db;AUTO_SERVER=TRUE",
              Props.get("db.user"),
              Props.get("db.password"))

          DB.defineConnectionManager(DefaultConnectionIdentifier, databaseContext)

          LiftRules.unloadHooks.append(() => databaseContext.closeAllConnections_!())
        }

 * You can see in the above the use of the `StandardDBVendor` class that will provide a large chunk of defaulted behaviour, including connection pooling/reaping etc.

## 6. Binding the model objects into the Lift framework
 * Now that we have defined the underlying objects, we can either manage the database schema manually, or we can enlist a schema generation process
 * So we can focus on Lift rather than a data we will use `Schemifier`.  This can be added to the mix by adding the following to the `Boot` class.

        Schemifier.schemify(true, Schemifier.infoF _, Auction, Supplier)

 * Key thing from the above is that we are defining all of the tables that we wish to include in the database.

## 7. Things to watch out for (that no one tells you about)
 * If you get exceptions thrown that the JNDI name cannot be found you need to ensure that the db connection is defined before the schemifier in `Boot.scala`

## 8. Configuring an Oracle database

 * TODO: or someone do for me pls?