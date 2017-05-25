1. The architecture that I have used is MVP, so that it is easier to test. However, I have used a different structure folder, to allow users to easily find the files ( so, activites folder for activities, components folder for all DI Component interfaces, etc). Some like this folder structure, whilst, I'm sure, others will not.
Here is a list of the folders:
activites: contains all activities
adapters: contains the recycler view adpater
components: contains the DI component interfaces
interceptors: where we subscribe the the observable
models: contains our POJOs
modules: contains the DI module classes
observables: contains the observable ( here, it is the lines we read from a file)
presenters: the presenters of MVP
views: the view interfaces we present to the presenter.
2. The MainViews defines the interface that provides the functionality for this app. We can use this to guide the TDD. So, first, we create a test called, testReadFile, which fails. We then write the code for it until it passes and then refactor the code, as required.
3. I have used interfaces ( e.g. for the activity , we present instead, to the presenter, a MainViews interface), since, this is more fully testable, easily extensible, complies with best object-oriented practices ( SOLID ). Explanation: If we pass an instance of the activity through to the presenter, then the presenter then knows about the activity, then if the activity is complicated, and if we write a test for the presenter, then we have to write a mock version of the activity, which is difficult to do, so, we give the presenter an interface, then the presenter doesn’t know who implements the interface, and doesn’t care , and the test doesn’t care either, but the test can control and examine the view, to check if certain things have passed/failed.
4. This architecture facilitates TDD. Why because in TDD we follow 3 steps, 1) Write a failing test. We use the Views interface, to write tests for displayWords when the file is read succesfully, and checkig the value of the error message, displayed in through the call  showMessage(String msg).
5. write code to pass that test, 3) refactor. Since, we use interfaces, which is our contract, the interfaces don’t change. The contract , of how to use the code doesn’t change. Hence, it won’t break the tests, when we refactor the code. The internals of the code is hidden from the tests. 
6. We inject dependencies into the Activity, etc. Dependency Injection is a form of Dependency Inversion ( SOLID ). Without it, the code would contain lots of code passing dependencies, eg InputStreamReader uses context, which would have to be passed through a few layers, without Dagger 2.
7. It uses the following libraries: mockito, espresso, rx java2, dagger 2, butterknife, retrolambda.
8. Regarding Espresso, unfortunately, RecyclerView does not inherit from AdapterView (it’s a direct subclass of ViewGroup instead), so you can’t use onData with it; hence need to write lots of boilerplate code, called idling resource, to ensure recycler_view is loaded before checking size, or value checking of rows.
9. I use static access to the ApplicationComponent, for dependency injection, to allow ItemsInteractorImpl to inject the context.
10. I will shortly update this file, to compare the solution I used over the other solutions.
