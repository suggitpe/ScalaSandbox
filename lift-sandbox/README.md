LIFT (2.x) Summary
================================

#  1. Erata
 - project started in 2009

-------------------------
# 2. Behind the scenes
 - Takes a view-first model
 - traditional model uses the controllers to work out which view to allocate (see spring MVC)
 - Lift is opposite in that first selects the view and then resolves the content
 - comprises three key parts: view, snippet and model (VSM)
 - view executes a command on the <stateful> snippet; <statefukl> snippet applies/commits to the model
 - two types of view: template (static) and generated views (dynamic)
 - a view can call a number of snippets
 - Snippets act as go-betweens between the view and the model:
    - rendering xml into the model
    - observing model changes and mediating them back to the view
 - four key modules
    - Lift Core: common utils; actor model (why??); JSON support
    - Lift Web: AJAX; REST; HTTP abstraction; Comet support; security etc
    - Lift Persistence: Lift ORM (Mapper); JPA support (note mutable); Record (functional CRUD module)
    - Lift Modules: extensions such as queuing (AMQP); transations (JTA); Lift state machine; OAuth; Paypal/facebook; etc etc

-------------------------
# 3. Build a basic CRUD based web application using Lift

### 3.1 [Build a basic Lift webapp and see it running locally](docs/BuildInitialDefaulLiftWebapp.md)

-------------------
