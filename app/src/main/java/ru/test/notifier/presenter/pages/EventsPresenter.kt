package ru.test.notifier.presenter.pages

import ru.test.notifier.NotifierApplication
import ru.test.notifier.R
import ru.test.notifier.data.db.DataBaseRepository
import ru.test.notifier.domain.model.EventModel
import ru.test.notifier.domain.model.PersonEventModel
import ru.test.notifier.ui.model.CachedModel

class EventsPresenter (private val view: ContentView) {

    private val storage = DataBaseRepository.getInstance()
    private val resources = NotifierApplication.getInstance().getAppResources()
    private var deletedItem: CachedModel<PersonEventModel>? = null

    fun getData() = storage.getAllEvents()

    fun storeEvent(model: EventModel?) = model?.let{ storage.storeEvent(model) } ?: run {
        view.showError(resources.getString(R.string.not_correct_data))
    }

    fun deleteEvent(position: Int, model:PersonEventModel){
        deletedItem = CachedModel(position, model)
        // TODO: make restore function
    }

    fun restoreDeleted() = deletedItem?.let{
        storage.deleteEventById(it.model.eventId)
        view.restoreItem(it.position, it.model)
        deletedItem = null
    } ?: run { view.showError(resources.getString(R.string.data_missing)) }


    interface ContentView {
        fun showError(message: String)
        fun restoreItem(position: Int, model:PersonEventModel)
    }

}
