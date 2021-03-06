# HackerNewsApp
Hacker News Android App - Made with Kotlin

 Simple app that request, the latests HackerNews posts and allows to delete items from the client.
 The news are saved into a local database.
 
 Features:
* List view of articles about Android recently published in Hacker News
* Load latest articles when pull down (Pull to refresh)
* You can delete articles to not receive them anymore by sliding it to the left (Swipe to delete)
* You can view the articles within the application (WebView)

Libraries:
* [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service.
* [Moshi](https://github.com/square/moshi) which handles the deserialization of the returned JSON to Kotlin data objects. 
* [Room](https://developer.android.com/training/data-storage/room) for local database storage.
  
Components from the Jetpack library:
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding adapters
* [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) with the SafeArgs plugin for parameter passing between fragments


# ScreenShots
<p float="left">
  <img src="https://github.com/fernandoehs/HackerNewsApp/blob/main/app/src/main/1637955170477.jpg" width="200" />
  <img src="https://github.com/fernandoehs/HackerNewsApp/blob/main/app/src/main/1637955170454.jpg" width="200" />
  <img src="https://github.com/fernandoehs/HackerNewsApp/blob/main/app/src/main/1637955170430.jpg" width="200" />
</p>
<p float="left">
  <img src="https://github.com/fernandoehs/HackerNewsApp/blob/main/app/src/main/hn.gif" width="200" />
 <img src="https://github.com/fernandoehs/HackerNewsApp/blob/main/app/src/main/hn1.gif" width="200" />
</p>





