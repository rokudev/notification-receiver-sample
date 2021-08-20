# notification-receiver-sample

This is a sample application that shows how to securely receive and process notifications sent by Roku. The notifications come in the form of JWT messages. This sample application shows how to verify the signature of a JWT message to ensure that the message is created by Roku and no one else. You can use this sample application as a starting point and customize it by adding your own logic for processing the notifications.

When you are ready to deploy the application and to start receiving notifications from Roku, be sure either that the application communicates over HTTPS or that the application is fronted by an HTTPS reverse proxy. The sample application does not include an example with regard to HTTPS. Feel free to adapt the sample application to the deployment scenario that suits your organization best.

Developers interested in learning more about this feature should contact their account manager at Roku.