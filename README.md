# Wunelliuatrestful

Simple Restful app made with services and broadcast receiver. 
The app works with the android framework services and doesn's use third party libs appart from Retrofit2. 
The flow:
Two buttons give the user the option to get the routes availabe. This info is provided by the server so in order to get the data we will start
a service that will get the routes. Since we are using an IntentService we dont need to worry about run the task to call the server in a
background. Our adapter will make the GET call with retrofit and parse the data from the server, then as we are implementing 
the Parcelable interface in the model, we will send back to the activity the data through an intent (as extra). Then the activity will 
just update the view displaying a list with the start and the end fo the route. 
If click in any other row  another activity will start and it will display 2 markers with the start and the end (the call to google directionts is 
not implement, so at the moment we dont display the route between the markers).
The other button will POST the location in the server. 
In order to do that we will use two services, the first one will be triggered when press the button. That service will implement a 
LocationListener that will provide us the current location and will send it back to the activity using a broadcast receiver again. 
Then the activity will start another service to post the UserLocation in the server.
In both case, for GET and POST, when the receiver gets data from the intent we will use an interface to pass the data to the activity. 
The idea was to have a clean activty using android components to abstract the logic.
