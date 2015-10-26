package tokyo.tommy_kw.kotlinsample.util

/**
 * Created by tommy on 15/10/27.
 */
class FakeCrashLibrary {
    companion object {
        public fun log(priority: Int, tag: String, message: String) {
        }

        public fun logWarning(t: Throwable) {
        }

        public fun logError(t: Throwable) {
        }
    }
    private fun FakeCrashLibrary() {
        throw AssertionError("no instance")
    }
}