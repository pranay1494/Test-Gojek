/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package  com.example.go_jektest.exceptions

import retrofit2.HttpException

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure(override var message : String = "", var retry : () -> Unit ={}) : Exception() {


    class NetworkConnection(override var message : String = ""): Failure()
    class ServerError(override var message : String = "", val errorCode : String = ""): Failure()
    class AuthenticationError(override var message : String = "") : Failure()

    class HttpFailure(override var message : String = "",val httpException : HttpException) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure(override var message : String = ""): Failure()



}
