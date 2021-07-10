# Network

To handle network calls with coroutines.

In your retrofit service class you have to create a `suspend` function that returns `T`

```kotlin
interface UsersService {

    @GET("/users")
    fun getUsers(): List<UserData>
}
```

And then when you call `getUsers()` method it will return an object from the same type of return type, or throw an Exception.

```kotlin
fun getLocalUsers() {
    GlobalScope.launch(Dispatchers.IO) {
        _progressLiveData.postValue(true)
        try {
            _resultLiveData.postValue(service.getUsers())
        } catch(e: Exception) {
            _errorLiveData.postValue(e)
        }
        _progressLiveData.postValue(false)
    }
}
```


# Error Mapper

Already there is an abstract class called `ErrorMapper`, this class can be used to create sub classes for each module to handle general and module specific errors.

**Create your own error mapper**

```kotlin
class AuthenticationErrorMapper : ErrorMapper() {

    override fun handle(throwable: Throwable): NetworkException {
        return when (throwable) {
            is FirebaseInvalidCredentialsException -> {
                MyInvalidCredentialsException()
            }
            is ServerInvalidCredentialsException -> {
                MyInvalidCredentialsException()
            }
            else -> {
                UnexpectedException(throwable)
            }
        }
    }
}
```

In `ErrorMapper` you can just take the exception and map it to another exception, no UI or Business logic.

# Error Handler

There is another class that can handle errors for you in an easily and readable way

```kotlin
val errorHandler = ErrorHandler()
errorHandler.handle(exception)
    .catch(ServerException::class) {
        // Handle error server response
    }
    .catch(NoConnectivityException::class) {
        // Handle no internet connection
    }
    .catch(SmsVerificationCodeExpiredException::class) {
        // Handle SmsVerificationCodeExpiredException
    }
    .catch {
        // Handle unknown exception
    }
```

Always inject `ErrorHandler` to the class instead of creating it inside, it's will help you mock it when you come to unit test

# Handle UI Logic

We are following a practice that separate error handling UI logic from the view itself

```kotlin
class PhoneAuthenticationErrorHandler(
    private val authenticationErrorMapper: AuthenticationErrorMapper,
    private val errorHandler: ErrorHandler
    private val activity: Activity,
) {

    fun <T> handle(error: Resource.Error<T>, retry: () -> Unit = {}, resend: () -> Unit = {}) {
        errorHandler.handle(authenticationErrorMapper.map(error.throwable))
            .catch(ServerException::class) { serverException ->
                val message = serverException.errorResponse.first().message
                Snackbar.make(activity.getParentLayout(), message, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry") { retry() }
                    .show()
            }
            .catch(NoConnectivityException::class) {
                val message = "No internet connection"
                Snackbar.make(activity.getParentLayout(), message, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry") { retry() }
                    .show()
            }
            .catch(SmsVerificationCodeExpiredException::class) {
                val message = "Failed to verify sms code"
                Snackbar.make(activity.getParentLayout(), message, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Resend") { resend() }
                    .show()
            }
            .catch {
                val message = "Oops! something went wrong"
                Snackbar.make(activity.getParentLayout(), message, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry") { retry() }
                    .show()
            }
    }
}
```

We always create a new error handler class for each Activity, Fragment, and View.

Always there is a way to improve the code, so if there is a multiple catch block that shows the same action with a different message, you should find a way to handle it without redundancy.

# Best practices

* Always use `ErrorHandler` to check the exception.
* Don't use third-party exception directly, instead map them before
* Always handle errors in a new class instead of handle them in the view itself
