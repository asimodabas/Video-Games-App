# Video-Games-App

Libraries Used
--------------
* [Lifecycles][1] - Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
* [Navigation][2] - Navigating to a destination is done using a NavController, an object that manages app navigation within a NavHost. Each NavHost has its own corresponding NavController. NavController provides a few different ways to navigate to a destination, which are further described in the sections below.
* [Retrofit][3] - Retrofit is a network library used among Android Developers. Easy access to REST APIs, testable and easy to use were factors that made Retrofit so popular.
* [RxJava][4] - RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.
* [Room Database][5] - Apps that handle non-trivial amounts of structured data can benefit greatly from persisting that data locally. The most common use case is to cache relevant pieces of data so that when the device cannot access the network, the user can still browse that content while they are offline.
* [Glide][6] - Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
* [Repository][7] - This guide encompasses best practices and recommended architecture for building robust, high-quality apps.
* [OkHttp][8] - HTTP is the way modern applications network. It’s how we exchange data & media. Doing HTTP efficiently makes your stuff load faster and saves bandwidth.
* [Coil][9] - An image loading library for Android backed by Kotlin Coroutines
* [Dagger-Hilt][10] - Hilt is the recommended solution for dependency injection in Android apps, and works seamlessly with Compose.
* [View Binding][11] - View binding is a feature that allows you to more easily write code that interacts with views. Once view binding is enabled in a module, it generates a binding class for each XML layout file present in that module. An instance of a binding class contains direct references to all views that have an ID in the corresponding layout.
* [Data Binding][12] - The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
* [livedata][13] - LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

App Images
--------------
<p align="center">
  <img src="https://user-images.githubusercontent.com/71982171/235326180-37ad5765-12c5-4322-b9ef-668875c987f2.gif" alt="GIF" />
</p>

MIT License

Copyright (c) 2023 Asım Odabaş

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

[1]: https://developer.android.com/jetpack/androidx/releases/lifecycle
[2]: https://developer.android.com/guide/navigation/navigation-navigate
[3]: https://square.github.io/retrofit/
[4]: https://github.com/ReactiveX/RxJava
[5]: https://developer.android.com/training/data-storage/room
[6]: https://github.com/bumptech/glide
[7]: https://developer.android.com/jetpack/guide#fetch-data
[8]: https://square.github.io/okhttp/
[9]: https://coil-kt.github.io/coil/
[10]: https://developer.android.com/jetpack/compose/libraries#hilt
[11]: https://developer.android.com/topic/libraries/view-binding
[12]: https://developer.android.com/topic/libraries/data-binding
[13]: https://developer.android.com/topic/libraries/architecture/livedata
