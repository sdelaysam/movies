package tools.log

import timber.log.Timber

class TimberPrintTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val msg = message.replace("\n", System.getProperty("line.separator")!!)
        if (t != null) {
            System.err.println(msg)
        } else {
            println(msg)
        }
    }
}