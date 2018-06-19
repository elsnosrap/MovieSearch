# Overview

The code you submit for this exercise is intended as a starting point to help us better understand how you work through engineering problems. There is no preferred solution, but you’ll want to be able to walk us through your implementation and explain the technical choices you made.

You can solve this problem using any combination of Android libraries or technologies you’d like. Android Studio 3.+ is preferred. Demonstrating a solid understanding of object-oriented software development and architecture is more important than the specific languages or technologies used.

When we evaluate your submission we’ll be looking at general code quality and awareness of development patterns. Because our apps are quite extensive, it’s very important to us that candidates write code that is well modularized so it can easily be extended and built upon in the future -- even for an exercise. 

This exercise is not intended to take more than an evening, so please don’t take more than a few hours on this. In the ReadMe or code comments, you can address what additional steps you would take to improve your code if given unlimited time.

Please use a GitHub repo to share your response with us.

# Instructions

We’d like you to create an Android app that allows the user to search for a movie by title. The requirements are as follows:

Use OMDB movie database: https://www.omdbapi.com/

With this API Key: \<redacted\> 

Display the results to the user, with at least the image of the movie poster (or any image provided by the api) and the title of the movie. There is no requirement for any persistence.

A future app is planned where you will have to use the same model from the api endpoint.


# ReadMe.md Requirements

Your repository should include a ReadMe.md file. Please take some time to write thoughtful answers, as it will help us understand your thought process and technical ability. Your ReadMe should answer the following questions:

## At a high level, how does your app work?
The app creates a network layer used for making API calls during app creation. It's a singleton instance that's used throughout the app. The way it's structured allows for unit tests to be easily written. The approach I took can probably be implemented using Dagger instead, however I personally haven't used that library too much. I wanted to make the app unit-testable and reverted to techniques I'm most familiar with.


##  What design decisions did you make and why?
I followed the MVVM pattern which we typically use at Brightcove. This means putting business logic in a ViewModel class which could have unit tests written for it. It also makes use of data binding to set values. I also opted for using RecyclerViews, ConstraintLayouts, and other common libraries (such as OkHttp, Retrofit, rxJava and Glide) because these tools are what I'm familiar with.

## What design patterns or architecture might be necessary in the future?
It might be necessary to create separate modules to break logic out of the single application, thus preventing a huge, monolithic app where it could be difficult to figure out what code belongs where.

## How would we extend your app if we had to add functionality?
To start with, the `ui` package would probably need to be split up break functionality out into more common packages. If more functionality were added, I would think new classes might be added to existing packages. Down the road, as the app grew in size, it might be necessary to create separate modules for things like networking, binding utilities, etc.

## What documentation, websites, papers, etc. did you consult for this assignment?
I referenced Android's documentation, StackOverflow for bugs I ran into, and existing code for how certain things need to be implemented.

## What third-party libraries or other tools does your application use? How did you choose each library or framework you used?
OkHttp, Retrofit, Glide, rxJava, Gson, and Robolectric. These are all libraries I'm quite familiar with so I just went with what I'm used to.

## How long did you spend on this exercise? If you had unlimited time to spend on this, how would you spend it and how would you prioritize each item?
It took me about 8 hours. I know that's more than "one evening", but I did struggle with some project set up. Specifically, creating the framework to allow for unit tests via mocking web server responses took a few hours. When it came time for me to start building the UI, that took a few hours too, as I struggled with "where to start from". The reason I struggled with these two tasks is because I haven't started a new project from scratch in a long time, and I also haven't been as technically involved in writing code over the past year. I'd estimate only around 25% of my time over the past year has been writing code.

If I had more time on this project, I'd love to do more thorough bug testing. I never really pushed the bounds of this app, so I would expect some pretty obvious bugs if I were to test orientation change (for example). I'd also love to use some newer Android libraries, such as the paging library, so I could scroll through _all_ the results, not just the first page. I would prioritize the items I worked on by first making sure the app is stable. Then I would work to add new features, ensuring each new feature is thoroughly tested before releasing it into the wild.

## If you were going to implement a level of automated testing to prepare this for a production environment, how would you go about doing so?
I already did that. :-) I would like to write some automated tests for the `SearchViewModel` class, so I can validate when empty text would show up, or when the recycler view should show up. I'd also like to write more unit tests around the JSON parsing, to make sure the app can handle garbage responses.
