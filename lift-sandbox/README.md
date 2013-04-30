# LIFT (2.x) Summary

##  1. Erata
 - the Lift project started in 2009

## 2. Behind the scenes
 - Takes a view-first model
 - Traditional model uses the controllers to work out which view to allocate (see spring MVC)
 - Lift is opposite in that first selects the view and then resolves the content
 - Comprises three key parts: view, snippet and model (VSM)
 - View executes a command on the "stateful" snippet; "stateful" snippet applies/commits to the model
 - Two types of view: template (static) and generated views (dynamic)
 - A view can call a number of snippets
 - Snippets act as go-betweens between the view and the model:
    - Rendering xml into the model
    - Observing model changes and mediating them back to the view
 - Four key modules
    - Lift Core: common utils; actor model (why??); JSON support
    - Lift Web: AJAX; REST; HTTP abstraction; Comet support; security etc
    - Lift Persistence: Lift ORM (Mapper); JPA support (note mutable); Record (functional CRUD module)
    - Lift Modules: extensions such as queuing (AMQP); transactions (JTA); Lift state machine; OAuth; Paypal/facebook; etc etc

## 3. Build a basic CRUD based web application using Lift

**3.1** [Build a basic Lift web-app and see it running locally](docs/1-BuildInitialDefaulLiftWebapp.md)

**3.2** [Build out the database schema with Mapper](docs/2-BuildMapperSchema.md)

**3.3** [Add in the scaffolding to the webapp](docs/3-Scaffolding.md)
