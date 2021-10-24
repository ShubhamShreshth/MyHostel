# MyHostel

> An Android Application that connects hostelites and their wardens.

### Problem Statement :

A lot of students face problem due to the unresolved hostel issues. Issues like unhygenic food, safety, laundry etc are very common in hostels in India. A physical call to the respective authorities is required to solve the issue which can lead to ambiguity.

### Problems MyHostel solves : 

MyHostel App solves above listed problems by allowing hostelites to file complaint against service in the hostel with a description explaining the same and post it on the app. The wardens / hostel authorities will recieve these complaints along with the student name, room number, etc. which makes it easy for the warden / hostel authorities to have an overview of what services to be looked into.

### Features of the application : Student View -

- _Register_ using your name, email, room number, building number, roll number, branch, password.
- _Login_ using your email, password.
- _File a complaint_ amongst the various categories listed along with description and photo associated with it.
- Check the _status_ of your complaint.
- _Get notified_ when your complaint is approved or denied.
- _View_ your _profile_.

### Features of the application : Admin View -

- _Register_ using your name, email, building number, password.
- _Login_ using your email, password.
- _View complaints_ along with their description and photo associated with it.
- _Approv or Reject_ a complaint with reason.
- _Update the status_ once the problem is resolved.
- _View_ your _profile_.

### Concepts used :

- **Authentication** : For any social media application wherein multiple users can connect to each other, authentication is a must. Else, the application may be used in ways it's not supposed to be used. Therefore, we have used FirebaseAuth service for authenticating the users.
- **DBMS** : The complete backend of this application is dependent on a cloud-hosted database known as Firebase. Databases are powerful tools which help us store data of various types in an organized way. Firebase offers a lot of features for easy manipulation of the database using Android Studio.
- **Fragmentation** : Instead of using different activities for different functions of the application, we have used fragments.

### Please note :

- First of all, connect your app to Firebase. Then, add the required Firebase SDKs for the Firebase services being used in the app(_FirebaseAuth, FireStore_). This can be done either by adding the required dependencies manually(these can be found at [_Firebase Documentation_](https://firebase.google.com/docs)) in the gradle files which will download the SDKs, or by using the IDE to add the SDKs automatically(go to _Tools>Firebase_ to do so).
- Update `AndroidManifest.xml` file as per the one present in this repository. Do note that all the activities present in the project are mentioned in this file.
- Update _app>src>main>res>drawable_ folder. This folder contains the xml files used.
- Update _app>src>main>res>font_ folder. This folder contains the external fonts used.
- Update _res>values>colors.xml_ file. This folder contains the external colors used.
- Update _res>values>strings.xml_ file. This folder contains the external strings used.
