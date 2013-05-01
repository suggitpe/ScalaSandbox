# Add scaffolding to the application
* Do we need application scaffolding? Not really but to be honest with you if you don't use it you will forevermore be plumbing in the same boiler plate code.  So to my mind we are probably well advised to use it, at least initially until you find incompatable use cases.

* Generally Lift has two types of application scaffolfing:
    * Prototype traits
    * CRUDify

## 1. Prototype traits
* Reminder that Traits are a way to bring in common functionality into classes.  Fundamentally they encapsulate members and functionality together.
* Lift makes heavy use of prototype traits for common functions.  The most common one to introduce is the MegaProtoUser (ignore the fact that it sounds like a toy from the 1980s).
* If we wanted to create a domain model called User we might do so as below:

        class User extends MegaProtoUser[User]
        with CreatedUpdated {
            def getSingleton = User
        }

        object User extends User
        with KeyedMetaMapper[Long, User]
        with MetaMegaProtoUser[User] {
            override def dbTableName = "user"
            override val basePath = "account" :: Nil
            override def skipEmailValidation = true  
        }
        
 * In the above we have created a simple domain object called `User` and then overridden a few bits of default behaviour such as email validation and the database table name.  The important bits are that it extended the `ProtoUser` classes from the `mapper` package.
 * Additionally we have stated through the `basePath` value that the URl for alteration of this model class should be prefixed with `account`, ie http://localhost:8080/account/edit to edit the account.
 * The `ProtoUser` class additionally provides a number of snippets for adding in pages for managing the `User`, such as signing up, managing passwords etc.  To add these we need to add the `User.menus` to the `Boot` class as bellow:
 
        val entries = List(
            Menu("Home") / "index"
        ) ::: User.menus
        
 * NB: the SiteMap entries should already exist in the `Boot` class and define the index page.
 * If you now add start the container you should see a number of new menu items and as you change the state of the session (eg login), the menu context should automagically change to reflect the change.
 * However if you open one of the pages it should only render the xml (and looks terrible).  To fix this we need to 'wrap' the xml with the lift template.  To do this we need to do add the following method to the `User` class:
 
        override def screenWrap: Box[Node] =
            Full(
                <lift:surround with="default" at="content">
                    <div id="box1" class="topbg">
                        <lift:msgs showAll="true"/>
                        <lift:bind/>
                    </div>
                    <lift:with-param name="sidebar">
                        <lift:embed what="_light_baszket"/>
                    </lift:with-param>
                </lift:surround>
            )
        
 * If you start the container now you should see the add user page etc looks better (well at leat in tune with the rest of the application)
 * This is exposing one of the really important facets of Lift.  In the above xml we are essentially asking for the xml to be 'wrapped' with the `default` template and using `content` as the bookmark for wrapping.  If you open up the application code and in the src/main/webapp/templates-hidden directory you will see `default.html`.  Open this up and you will find the below:
 
            <div class="column span-17 last">
                <lift:bind name="content" />
            </div>

 * If you then view the source code for the login page (http://localhost:8080/account/login) you will see the `div` section and then the class generated html 'filling'.

## 2. CRUDify

