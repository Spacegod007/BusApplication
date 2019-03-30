package local.jordi.busapplication.shared.event;

import java.util.ArrayList;
import java.util.List;

public final class EventContainer <EL extends EventListener<T>, T> {

    private List<EL> eventListeners;

    public EventContainer() {
        eventListeners = new ArrayList<>();
    }

    public void Subscribe(EL listener)
    {
        eventListeners.add(listener);
    }

    public void UnSubscribe(EL listener)
    {
        eventListeners.remove(listener);
    }

    public void Fire(T t)
    {
        for (EL eventListener : eventListeners) {
            eventListener.Fire(t);
        }
    }
}
