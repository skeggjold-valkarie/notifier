package ru.test.notifier.ui.adapters

import android.graphics.Bitmap
import ru.test.notifier.NotifierApplication
import ru.test.notifier.ui.extensions.toBitmap
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

class BitmapCache {

    private var currentSize = 0
    private val context = NotifierApplication.getInstance().applicationContext
    private val map = ConcurrentHashMap<Int, Bitmap>()
    private val queue = ConcurrentLinkedQueue<Int>()

    @Synchronized
    operator fun get(id: Int): Bitmap? = if (map.contains(id)) map[id] else store(id)

    @Synchronized
    private fun freeSpace(space: Int) {
        if (space > MAX_CACHE_SIZE) throw IllegalArgumentException(HUGE_SIZE_EXCEPTION)

        while (space + currentSize > MAX_CACHE_SIZE || queue.isEmpty()) {
            queue.poll()?.also { map.remove(it); countSize() }
        }
    }

    private fun store(key: Int): Bitmap? = context.toBitmap(key)?.also { value ->
        freeSpace(value.byteCount)
        synchronized(this) {
            queue.add(key)
            map[key] = value
            countSize()
        }
    }

    private fun countSize() = map.values.sumOf { it.byteCount }.also { currentSize = it }

    companion object{
        const val MAX_CACHE_SIZE = 1024 * 1024
        const val HUGE_SIZE_EXCEPTION = "Huge size Exception"
    }
}
