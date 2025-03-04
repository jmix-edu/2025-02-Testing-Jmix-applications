Project creation specification
================

Project creation BDD test
----------------

We will create a new project, assign a user as a manager, and then check if the project was created
correctly using the Selenide UI testing library.
In the end we will remove test project

* Open application in browser
* Log in as user with "dev2" username and "admin" password
* Open the project list view
* Open the project detail view to create new instance
* Fill form fields with following values: name is "BDD testing", manager name is "admin"
* Save new project
* Make sure the new project with "BDD testing" name is added to project table
* Remove test project
