This repository is a Mashup application created using Mule which downloads the popular photos from Instagram and Flickr and uploads it to the Google Drive

1) Deploy the application to the server and go to the URL,
http://localhost:8081

2) It will take you to the Instagram login URL. Login to Instagram.

3) Once your authentication is successful Instagram will ask permission for the Instagrm2Drive app with permission to access basic information.

4) After you authorize you will be redirected to the Google Login

5) After your authentication is successful Google will ask permission for the instagram2Drive app with permission to view, edit, delete files created by this app.

6) Once you authorize the main processing will begin. It will download popular photos from instagram.com and flickr.com and will upload to the following folders in your Google Drive respectively,
instagram2drive
flickr2drive
Monitor the upload progress in console.



###################
Environment:
###################
JDK 1.7
Mule Server  3.6.1 EE
