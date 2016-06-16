package tokyo.tommy_kw.musical.util

/**
 * Created by tommy on 15/10/27.
 */
class FakeCrashLibrary {
    companion object {
        fun log(priority: Int, tag: String, message: String) {
        }

        fun logWarning(t: Throwable) {
        }

        fun logError(t: Throwable) {
        }
    }

    private fun FakeCrashLibrary() {
        throw AssertionError("no instance")
    }
}