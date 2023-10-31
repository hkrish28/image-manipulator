This is an image processing application that lets users run certain operations on supported images. A list of all the supported commands can be found in this document.

The program is run using the ProgramRunner class. ProgramRunner is nothing but a class that solely exists for the purpose of initializing the view and controller and classes and then let the ScriptController take over. 

All Classes, Interfaces used:

<b>ProgramRunner</b> - Class
The sole responsibility of this class is to get the application running and let the controller take over the reins. 

<b>ImageProcessingController</b> - Interface
This controller interface lists down the public methods (which is currently one - to execute a given command) which should be implemented by every concrete class for the interface.

<b>ScriptController</b> - Class
This concrete implementation of the controller interface supports a list of commands that can be inputted by the user. The user can either run commands from the console or could embed them in a script file and run the script file using the controller.

<b>View</b> - Interface
This interface represents a view that is used for displaying contents to the users

<b>ViewImpl</b> - Class
This implementation of view is a simple view that only displays messages prompting users to enter input and for displaying the status of the operation.

<b></b>

